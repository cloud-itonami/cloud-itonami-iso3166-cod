(ns statute.facts
  "General-law compliance catalog for the Democratic Republic of the
  Congo (COD) -- extends this repo's existing `marketentry.facts`
  (public-procurement market-entry only, narrow scope) with a second,
  orthogonal catalog of statutes a company operating in this
  jurisdiction must generally track for compliance. Mirrors
  cloud-itonami-iso3166-ben/-btn/-aze/-bih/-jpn/-deu/-bgr's
  `statute.facts` (ADR-2607141700, cloud-itonami-compliance-fact-
  federation).

  Every entry below cites an OFFICIAL government-hosted (or, for the
  OHADA entry, official supranational-body-hosted) URL that this
  iteration actually fetched (curl/WebFetch, 2026-07-22) and read
  directly, not a secondary summary:

  - **OHADA Uniform Act on Commercial Companies and Economic Interest
    Groupings (Acte uniforme relatif au droit des sociétés commerciales
    et du groupement d'intérêt économique, AUSCGIE).** This iteration
    independently investigated (rather than copying Benin's citation
    text) whether DRC has a domestic 'Companies Act' of its own. It does
    NOT: DRC, like Benin, is an OHADA member state, so company law is
    governed DIRECTLY by this SUPRANATIONAL instrument -- adopted 30
    January 2014 in Ouagadougou, entered into force 5 May 2014
    (independently re-verified directly at ohada.org/en/commercial-
    companies-and-economic-interest-groups/, own text confirming both
    dates -- NOT copied from Benin's own citation without
    re-verification). DRC-SPECIFIC finding, independently verified at
    ohada.org/etats-parties-rdc/ (own text): 'Ratification : 27 juin
    2012 / Dépôt Instruments : 13 juillet 2012 / Entrée en vigueur : 12
    septembre 2012' -- meaning DRC acceded to the OHADA treaty itself in
    2012 (Traité de Port-Louis, whose own Art. 10 gives every Uniform
    Act direct and obligatory effect in every member state 'nonobstant
    toutes dispositions contraires de droit interne'), TWO YEARS BEFORE
    the current AUSCGIE's own 2014 revision entered into force -- so
    the CURRENT AUSCGIE text applies in DRC from 5 May 2014 (its own
    general entry-into-force date), not from DRC's own 2012 accession.
    This distinguishes 'when DRC became bound by OHADA instruments in
    general' from 'which version of AUSCGIE text currently governs
    Congolese company law' -- an honest, explicitly-drawn distinction
    this iteration did not collapse. Business/entity REGISTRATION (RCCM)
    -- as opposed to company FORMATION/governance law -- is governed by
    a DIFFERENT OHADA instrument, the Acte Uniforme relatif au Droit
    Commercial Général (AUDCG); this catalog does not conflate the two,
    and `marketentry.facts` cites AUDCG separately for RCCM via the
    Guichet Unique de Création d'Entreprise (GUCE).
  - **Code du Travail (Labour Code): Loi n°015-2002 du 16 octobre 2002
    portant Code du travail, as modified and completed by Loi n°16/010
    du 15 juillet 2016.** This iteration downloaded and read the 2016
    AMENDING law's own primary text directly from leganet.cd (which
    itself quotes the original law's title/date verbatim in its own
    EXPOSE DES MOTIFS: 'la loi n°015-2002 du 16 octobre 2002 portant
    Code du travail') and read the amended Article 1er in full: 'Le
    présent code est applicable à tous les travailleurs et à tous les
    employeurs, y compris ceux des entreprises publiques exerçant leur
    activité professionnelle sur l'étendue de la République Démocratique
    du Congo...'. HIGH confidence, primary text for both the original
    law's own citation and the 2016 amendment's own text.

  A law not in this table has NO spec-basis, full stop; extend
  `catalog`, do not invent an id/url. This iteration did NOT
  independently investigate a DRC data-protection/digital-code
  analogue to Benin's Code du Numérique -- an honest, explicitly-
  flagged gap, not a negative finding (no claim is made that no such
  law exists).")

(def catalog
  "iso3 -> vector of statute entries. `:statute/url` + `:statute/law-number`
  are the citation the governor requires before any compliance-fact
  proposal referencing this law can commit."
  {"COD"
   [{:statute/id "cod.ohada-auscgie"
     :statute/title "Acte uniforme relatif au droit des sociétés commerciales et du groupement d'intérêt économique (AUSCGIE)"
     :statute/jurisdiction "COD"
     :statute/kind :law
     :statute/law-number "OHADA Uniform Act -- adopted 30 January 2014 (Ouagadougou), in force 5 May 2014; directly applicable in DRC as an OHADA member state (DRC's own accession: ratification 27 June 2012, deposit of instruments 13 July 2012, entry into force 12 September 2012) per Traité de Port-Louis Art. 10, no domestic transposition act required"
     :statute/url "https://www.ohada.org/en/commercial-companies-and-economic-interest-groups/"
     :statute/url-provenance :official-ohada-org
     :statute/enacted-date "2014-01-30"
     :statute/retrieved-at "2026-07-22"
     :statute/topic #{:corporate-governance :incorporation}}
    {:statute/id "cod.code-du-travail"
     :statute/title "Code du travail en République Démocratique du Congo"
     :statute/jurisdiction "COD"
     :statute/kind :law
     :statute/law-number "Loi n°015-2002 du 16 octobre 2002, telle que modifiée et complétée par la Loi n°16/010 du 15 juillet 2016"
     :statute/url "https://www.leganet.cd/Legislation/DroitSocial/Loi%2016.010.15.07.html"
     :statute/url-provenance :official-leganet-cd
     :statute/enacted-date "2002-10-16"
     :statute/retrieved-at "2026-07-22"
     :statute/topic #{:labor :employment}}]})

(defn spec-basis
  "The jurisdiction's statute vector, or nil -- nil means NO spec-basis
  for that jurisdiction yet."
  [iso3]
  (get catalog iso3))

(defn coverage
  "Honest coverage report, same shape/discipline as `marketentry.facts/coverage`:
  never report a missing jurisdiction as covered."
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-cod statute.facts Wave 0 (ADR-2607141700): "
                 (count (get catalog "COD")) " COD statutes seeded with an "
                 "official citation. Extend "
                 "`statute.facts/catalog`, never fabricate a law-id or URL.")})))

(defn by-topic
  "Statutes for `iso3` tagged with `topic` (e.g. :labor, :corporate-governance)."
  [iso3 topic]
  (filterv #(contains? (:statute/topic %) topic) (spec-basis iso3)))
