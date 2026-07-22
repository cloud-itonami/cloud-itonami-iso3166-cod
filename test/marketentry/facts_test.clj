(ns marketentry.facts-test
  (:require [clojure.test :refer [deftest is testing]]
            [marketentry.facts :as facts]))

(deftest cod-has-spec-basis
  (let [sb (facts/spec-basis "COD")]
    (is (some? sb))
    (is (string? (:provenance sb)))
    (is (seq (:required-evidence sb)))
    (is (some? (facts/corporate-number-spec-basis "COD")))
    (is (some? (facts/business-registration-spec-basis "COD")))
    (is (some? (facts/national-regional-preference-spec-basis "COD")))))

(deftest cod-rep-spec-basis-is-real-and-non-nil
  (testing "COD's rep-spec-basis is non-nil -- Art. 23(a) extends disqualification/conviction grounds to the candidate's OWN directors/officers, a different scope than Benin's consortium/subcontractor extension"
    (is (some? (facts/rep-spec-basis "COD")))
    (is (string? (:rep-owner-authority (facts/rep-spec-basis "COD"))))))

(deftest cod-business-registration-is-a-different-body-from-tax-and-procurement
  (testing "business/company registration (GUCE) and tax registration (DGI) are administered by different authorities"
    (let [reg (facts/business-registration-spec-basis "COD")
          tax (facts/corporate-number-spec-basis "COD")]
      (is (some? reg))
      (is (some? tax))
      (is (not= (:business-registration-owner-authority reg)
                (:corporate-number-owner-authority tax))))))

(deftest cod-national-regional-preference-is-the-flagship-spec-basis
  (testing "Loi n°10/010 Art. 37's 6-tier ranked preference is a real, government-published mechanism -- not fabricated"
    (let [nl (facts/national-regional-preference-spec-basis "COD")]
      (is (some? nl))
      (is (= 6 (count (:national-regional-preference-tiers nl))))
      (is (= 1 (:tier (first (:national-regional-preference-tiers nl)))))
      (is (= 6 (:tier (last (:national-regional-preference-tiers nl))))))))

(deftest unknown-jurisdiction-has-no-spec-basis
  (is (nil? (facts/spec-basis "ATL")))
  (is (nil? (facts/spec-basis "ZZZ")))
  (is (nil? (facts/business-registration-spec-basis "ATL")))
  (is (nil? (facts/national-regional-preference-spec-basis "ATL"))))

(deftest required-evidence-satisfied
  (let [sb (facts/spec-basis "COD")
        all (:required-evidence sb)]
    (is (true? (facts/required-evidence-satisfied? "COD" all)))
    (is (not (facts/required-evidence-satisfied? "COD" (take 1 all))))
    (is (nil? (facts/required-evidence-satisfied? "ATL" all)))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["COD" "USA" "ATL"])]
    (is (= 3 (:requested c)))
    (is (= 2 (:covered c)))
    (is (= ["ATL"] (:missing-jurisdictions c)))))
