(ns marketentry.registry
  "Pure-function market-entry filing-draft + filing-submit record
  construction -- an append-only market-entry book-of-record draft.

  Like every sibling actor's registry, there is no single international
  reference-number standard for a public-procurement market-entry
  filing -- every jurisdiction assigns its own format. This namespace
  does NOT invent one; it builds a jurisdiction-scoped sequence number
  and validates the record's required fields, the same honest,
  non-fabricating discipline `marketentry.facts` uses.

  `engagement-fee-matches-claim?` is an HONEST reapplication of the
  SAME ground-truth-recompute DISCIPLINE sibling actors use (verify a
  claimed monetary total against the entity's own recorded quantity x
  unit fields), reapplied to a market-entry engagement fee line.

  `compute-preference-tier` / `preference-tier-mismatches-claim?` are
  THIS vertical's own new ground-truth check, grounding COD's flagship
  governor check (`marketentry.governor`): Loi n°10/010 du 27 avril
  2010 (Code des Marchés Publics), Section 5 ('De la préférence
  nationale et régionale'), Art. 37 -- a SIX-TIER ranked bid-evaluation
  preference (see `marketentry.facts` for the full citation and the
  6 tiers' own text). This is a DIFFERENT check SHAPE from every prior
  sibling: Bulgaria's ЗОП Art. 54(5) de-minimis is a PERCENTAGE-OF-
  TURNOVER ELIGIBILITY formula, Albania's Neni 76(2)(c) carve-out is a
  FLAT-CONSTANT ELIGIBILITY threshold, Azerbaijan's/Armenia's flagship
  checks are BOOLEAN registry-membership ELIGIBILITY reads, Antigua and
  Barbuda's vendor-class check is a THREE-TIER ELIGIBILITY-THRESHOLD
  classification, Bhutan's FDI check is a SET-MEMBERSHIP sector-
  eligibility gate, and Benin's Art. 77 mechanism is a BID-EVALUATION
  PRICE ADJUSTMENT (a single percentage recompute). COD's Art. 37 is
  none of those: it is a RANKED MULTI-CATEGORY CLASSIFICATION test --
  given an engagement's own declared nationality/ownership facts
  (6 boolean conditions, in the LAW'S OWN priority order), independently
  recompute WHICH of the 6 ranked tiers applies (or none), and HARD-hold
  if the engagement's own claimed tier does not match. Deliberately NOT
  modeled: the abattement's exact MONETARY MAGNITUDE, which Art. 37's
  own text leaves discretionary ('quantifiée dans l'appel d'offres') --
  the same honest scope-narrowing Benin's catalog applied to its own
  Art. 77 discretionary branch, and which applies identically to COD's
  own Art. 61 (foreign-subcontracting preference, capped at 5% but not
  fixed) -- neither this namespace nor `marketentry.governor` invents a
  number the law itself leaves to each autorité contractante's
  discretion.

  This namespace is pure data + pure functions -- no I/O, no network
  call to any real procurement portal. It builds the RECORD an
  operator would keep, not the act of submitting a portal registration
  itself (that is `marketentry.operation`'s `:filing/submit`, always
  human-gated -- see README Actuation)."
  (:require [clojure.string :as str]))

(defn- unsigned-certificate
  "Every certificate this actor produces is UNSIGNED -- signature is
  the market-entry operator's act, not this actor's."
  [kind subject record-id]
  {"@context" ["https://www.w3.org/ns/credentials/v2"]
   "type" ["VerifiableCredential" kind]
   "credentialSubject" {"id" subject "record" record-id}
   "proof" nil
   "issued_by_registry" false
   "status" "draft-unsigned"})

(defn- zero-pad [n w]
  (let [s (str n)]
    (str (apply str (repeat (max 0 (- w (count s))) "0")) s)))

(def ^:private money-scale
  "Sub-minor-unit scale used when comparing two money amounts: 1/10000 of
  a unit. Coarser than double representation error by many orders of
  magnitude, finer than any real currency's minor unit (2 decimals for
  most, 3 for KWD/BHD/OMR, 0 for JPY/KRW)."
  10000)

(defn- money=
  "Exact-at-money-precision equality for two amounts.

  `==` on raw doubles is NOT the right comparison for money. With
  whole-unit fees the two agree, but as soon as an amount carries
  cents the sum `base + rate x months` is routinely not the double
  nearest the true total, and a CORRECT claim compares false: measured
  on this exact shape, 40,989 of 327,060 cent-denominated combinations
  (12.5%) were rejected while being right, against 0 of 327,060 in
  whole units.

  Rounding both sides to `money-scale` before comparing removes the
  representation error while preserving every distinction money can
  actually carry."
  [x y]
  (and (number? x) (number? y)
       (= (Math/round (* money-scale (double x)))
          (Math/round (* money-scale (double y))))))

(defn compute-engagement-fee
  "The ground-truth engagement fee for `engagement`'s own `:base-fee`
  and `:monitoring-months` x `:monthly-rate` -- a single flat
  base + months x rate calculation, not a full pricing engine."
  [{:keys [base-fee monthly-rate monitoring-months]}]
  ;; nil when any field is not a number: an un-recomputable engagement is
  ;; un-verifiable, which is neither `correct` nor a ClassCastException
  ;; thrown out of the caller.
  (when (and (number? base-fee) (number? monthly-rate) (number? monitoring-months))
    (+ (double base-fee)
       (* (double monthly-rate) (double monitoring-months)))))

(defn engagement-fee-matches-claim?
  "Does `engagement`'s own `:claimed-fee` equal the independently
  recomputed `compute-engagement-fee`?"
  [{:keys [claimed-fee] :as engagement}]
  (money= claimed-fee (compute-engagement-fee engagement)))

(defn compute-preference-tier
  "The ground-truth Loi n°10/010 Art. 37 preference tier `engagement`
  is entitled to, given its own declared boolean facts for the 6
  ranked categories (checked in the LAW'S OWN priority order -- the
  LOWEST-numbered / highest-priority true condition wins): 1 Congolese
  natural person, 2 Congolese-majority-owned SME, 3 Congolese legal
  person, 4 grouping with Congolese participation/subcontracting, 5
  foreign entity with Congolese economic activity, 6 foreign entity
  from a treaty-preference State. Returns nil when none of the 6
  conditions is declared true -- a wholly foreign bidder with no
  treaty preference and no declared Congolese economic activity is
  entitled to NO Art. 37 preference at all, and this is not an error."
  [{:keys [congolese-natural-person?
           congolese-sme-majority-congolese-capital?
           congolese-legal-person?
           grouping-congolese-participation-or-subcontracting?
           foreign-congolese-economic-activity?
           foreign-treaty-preference?]}]
  (cond
    (true? congolese-natural-person?) 1
    (true? congolese-sme-majority-congolese-capital?) 2
    (true? congolese-legal-person?) 3
    (true? grouping-congolese-participation-or-subcontracting?) 4
    (true? foreign-congolese-economic-activity?) 5
    (true? foreign-treaty-preference?) 6
    :else nil))

(defn preference-tier-mismatches-claim?
  "Does `engagement`'s own declared `:claimed-preference-tier` differ
  from the INDEPENDENTLY recomputed Art. 37 tier? Catches BOTH
  directions honestly: a foreign bidder claiming a higher-priority tier
  it is not entitled to, and a Congolese bidder being denied the tier
  it IS entitled to. `nil` claimed-tier vs. a `nil` recomputed tier
  (no preference at all) is NOT a mismatch."
  [{:keys [claimed-preference-tier] :as engagement}]
  (not= claimed-preference-tier (compute-preference-tier engagement)))

(defn register-draft
  "Validate + construct the FILING-DRAFT registration DRAFT -- the
  market-entry operator's own act of preparing a marche.armp-rdc.cd
  tender-response package. Pure function -- does not touch any real
  procurement portal."
  [engagement-id jurisdiction sequence]
  (when-not (and engagement-id (not= engagement-id ""))
    (throw (ex-info "draft: engagement_id required" {})))
  (when-not (and jurisdiction (not= jurisdiction ""))
    (throw (ex-info "draft: jurisdiction required" {})))
  (when (< sequence 0)
    (throw (ex-info "draft: sequence must be >= 0" {})))
  (let [draft-number (str (str/upper-case jurisdiction) "-DFT-" (zero-pad sequence 6))
        record {"record_id" draft-number
                "kind" "filing-draft"
                "engagement_id" engagement-id
                "jurisdiction" jurisdiction
                "immutable" true}]
    {"record" record "draft_number" draft-number
     "certificate" (unsigned-certificate "FilingDraft" draft-number draft-number)}))

(defn register-submit
  "Validate + construct the FILING-SUBMIT registration DRAFT -- the
  market-entry operator's own act of actually submitting a portal
  registration (always human-gated upstream)."
  [engagement-id jurisdiction sequence]
  (when-not (and engagement-id (not= engagement-id ""))
    (throw (ex-info "submit: engagement_id required" {})))
  (when-not (and jurisdiction (not= jurisdiction ""))
    (throw (ex-info "submit: jurisdiction required" {})))
  (when (< sequence 0)
    (throw (ex-info "submit: sequence must be >= 0" {})))
  (let [submit-number (str (str/upper-case jurisdiction) "-SUB-" (zero-pad sequence 6))
        record {"record_id" submit-number
                "kind" "filing-submit"
                "engagement_id" engagement-id
                "jurisdiction" jurisdiction
                "immutable" true}]
    {"record" record "submit_number" submit-number
     "certificate" (unsigned-certificate "FilingSubmit" submit-number submit-number)}))

(defn append [history result]
  (conj (vec history) (get result "record")))
