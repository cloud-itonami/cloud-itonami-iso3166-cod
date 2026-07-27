(ns marketentry.registry-test
  (:require [clojure.test :refer [deftest is testing]]
            [marketentry.registry :as registry]))

(deftest engagement-fee-recompute
  (let [e {:base-fee 500000 :monthly-rate 30000 :monitoring-months 12 :claimed-fee 860000.0}]
    (is (== 860000.0 (registry/compute-engagement-fee e)))
    (is (true? (registry/engagement-fee-matches-claim? e))))
  (let [bad {:base-fee 500000 :monthly-rate 30000 :monitoring-months 12 :claimed-fee 999000.0}]
    (is (false? (registry/engagement-fee-matches-claim? bad)))))

(deftest register-draft-and-submit
  (let [d (registry/register-draft "eng-1" "COD" 0)
        s (registry/register-submit "eng-1" "COD" 0)]
    (is (= "COD-DFT-000000" (get d "draft_number")))
    (is (= "COD-SUB-000000" (get s "submit_number")))
    (is (nil? (get-in d ["certificate" "proof"])))
    (is (= "draft-unsigned" (get-in s ["certificate" "status"])))))

(deftest register-requires-ids
  (is (thrown? Exception (registry/register-draft "" "COD" 0)))
  (is (thrown? Exception (registry/register-submit "eng-1" "" 0))))

(deftest preference-tier-recompute
  (testing "Loi n°10/010 Art. 37 -- the lowest-numbered (highest-priority) true condition wins"
    (is (= 1 (registry/compute-preference-tier {:congolese-natural-person? true
                                                :congolese-legal-person? true})))
    (is (= 2 (registry/compute-preference-tier {:congolese-sme-majority-congolese-capital? true
                                                :congolese-legal-person? true})))
    (is (= 3 (registry/compute-preference-tier {:congolese-legal-person? true})))
    (is (= 4 (registry/compute-preference-tier {:grouping-congolese-participation-or-subcontracting? true})))
    (is (= 5 (registry/compute-preference-tier {:foreign-congolese-economic-activity? true})))
    (is (= 6 (registry/compute-preference-tier {:foreign-treaty-preference? true})))
    (is (nil? (registry/compute-preference-tier {}))
        "a wholly foreign bidder with no treaty preference and no declared Congolese economic activity gets no Art. 37 preference at all"))
  (testing "preference-tier-mismatches-claim? catches both directions"
    (is (false? (registry/preference-tier-mismatches-claim?
                 {:congolese-legal-person? true :claimed-preference-tier 3})))
    (is (true? (registry/preference-tier-mismatches-claim?
                {:congolese-legal-person? true :claimed-preference-tier 1}))
        "claiming a higher-priority tier than entitled to")
    (is (true? (registry/preference-tier-mismatches-claim?
                {:congolese-natural-person? true :claimed-preference-tier 3}))
        "denied a tier the bidder IS entitled to")
    (is (false? (registry/preference-tier-mismatches-claim? {:claimed-preference-tier nil}))
        "no preference claimed and none computed is not a mismatch")))

;; ---------------------------------------------------------------------------
;; Money is compared at money precision, not at double precision
;; ---------------------------------------------------------------------------

(deftest whole-unit-fees-were-already-correct-and-stay-correct
  (testing "the seeded shape: base + rate x months in whole currency units"
    (is (registry/engagement-fee-matches-claim?
         {:base-fee 500000 :monthly-rate 30000 :monitoring-months 12
           :claimed-fee 860000.0}))))

(deftest cent-denominated-fees-are-no-longer-rejected-while-correct
  (testing "`(== (double claimed) (+ (double base) (* (double rate) (double months))))`
            rejected CORRECT totals once an amount carried cents -- 40,989 of
            327,060 combinations (12.5%), against 0 of 327,060 in whole units"
    (let [bad (for [m (range 1 37)
                    bc (range 10000 90000 2100)
                    rc (range 500 6000 210)
                    :let [truth (/ (+ bc (* rc m)) 100.0)]
                    :when (not (registry/engagement-fee-matches-claim?
                                {:base-fee (/ bc 100.0) :monthly-rate (/ rc 100.0)
                                  :monitoring-months m :claimed-fee truth}))]
                [m (/ bc 100.0) (/ rc 100.0) truth])]
      (is (empty? bad) (str "false rejections: " (count bad) " e.g. " (first bad))))))

(deftest a-genuinely-wrong-fee-is-still-caught
  (testing "rounding to money precision must not blunt the check"
    (is (not (registry/engagement-fee-matches-claim?
              {:base-fee 500000 :monthly-rate 30000 :monitoring-months 12
                :claimed-fee 860000.01})))
    (is (not (registry/engagement-fee-matches-claim?
              {:base-fee 500000 :monthly-rate 30000 :monitoring-months 12
                :claimed-fee 859999.99})))))

(deftest an-unverifiable-fee-never-matches
  (testing "un-verifiable is not the same as correct, and not a crash"
    (is (not (registry/engagement-fee-matches-claim?
              {:base-fee 500000 :monthly-rate 30000 :monitoring-months 12})))
    (is (not (registry/engagement-fee-matches-claim?
              {:base-fee "500000" :monthly-rate 30000 :monitoring-months 12
                :claimed-fee 860000.0})))
    (is (nil? (registry/compute-engagement-fee {:base-fee 500000 :monthly-rate 30000})))))
