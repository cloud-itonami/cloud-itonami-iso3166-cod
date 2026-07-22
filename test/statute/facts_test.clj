(ns statute.facts-test
  (:require [clojure.string :as str]
            [clojure.test :refer [deftest is]]
            [statute.facts :as facts]))

(deftest cod-has-spec-basis
  (let [sb (facts/spec-basis "COD")]
    (is (= 2 (count sb)))
    (is (every? #(str/starts-with? (:statute/url %) "https://") sb))
    (is (every? :statute/law-number sb))))

(deftest unknown-jurisdiction-has-no-spec-basis
  (is (nil? (facts/spec-basis "ATL")))
  (is (nil? (facts/spec-basis "ZZZ"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["COD" "JPN" "ATL"])]
    (is (= 3 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["ATL" "JPN"] (:missing-jurisdictions c)))))

(deftest by-topic-filters
  (is (= ["cod.code-du-travail"]
         (mapv :statute/id (facts/by-topic "COD" :labor))))
  (is (= ["cod.ohada-auscgie"]
         (mapv :statute/id (facts/by-topic "COD" :corporate-governance))))
  (is (empty? (facts/by-topic "COD" :environment)))
  (is (empty? (facts/by-topic "ATL" :labor))))
