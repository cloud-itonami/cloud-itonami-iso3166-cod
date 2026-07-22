(ns marketentry.facts
  "Per-jurisdiction public-procurement market-entry regulatory catalog
  -- the G2-style spec-basis table the Market-Entry Compliance Governor
  checks every `:jurisdiction/assess` proposal against ('did the advisor
  cite an OFFICIAL public source for this jurisdiction's requirements,
  or did it invent one?').

  Democratic Republic of the Congo's real market-entry surface (curl/
  WebFetch-verified 2026-07-22 against armp-rdc.cd, dgi.gouv.cd,
  leganet.cd and ohada.org's own official hosting; the primary
  procurement law's own PDF text was downloaded directly from ARMP's own
  site and read in full via `pdftotext -layout`, 31 pages -- HIGH
  confidence, primary text, unless a specific note below says
  otherwise):

  - **The primary law is Loi n°10/010 du 27 avril 2010 relative aux
    marchés publics** (downloaded directly from
    armp-rdc.cd/wp-content/uploads/2021/12/Lois-relative-aux-marchs-
    publicsx.pdf, read in full). Its own EXPOSE DES MOTIFS states this
    law replaces the obsolete Ordonnance-loi n°69-054 du 5 décembre
    1969, and its own Art. 84 confirms the abrogation. Chapitre 5 (Art.
    13-14) STRICTLY SEPARATES two functions, each assigned to a
    DIFFERENT body -- the same 'régulation vs contrôle a priori' split
    this loop has found in other OHADA-family siblings, but with a
    DIFFERENT institutional wiring than Benin's ARMP/DNCMP split (see
    below):
      1. Art. 13: 'contrôle a priori' (ex-ante procedural control) is
         assigned to 'un service relevant du ministère ayant le budget
         dans ses attributions' -- the real-world body performing this
         role is the Direction Générale de Contrôle des Marchés Publics
         (DGCMP), independently confirmed live at dgcmp.cd (site
         currently in maintenance mode, but its own maintenance-page
         metadata names it in full: 'D.G.C.M.P - Direction Générale du
         Contrôle des Marchés Publics'). This iteration did NOT find a
         specific decree number creating DGCMP by name (an honest,
         explicitly-flagged gap) -- Art. 13's own text is the primary
         grounding used here, corroborated by DGCMP's own live site
         confirming the name/role.
      2. Art. 14: 'régulation' (ex-post control, audit, training,
         capacity-building) is assigned to 'une institution' whose
         creation/organization is fixed by décret du Premier ministre --
         this is the Autorité de Régulation des Marchés Publics (ARMP),
         independently confirmed live at armp-rdc.cd, whose own
         'Liste noire' page (armp-rdc.cd/liste-noire/) cites Loi n°10/010
         du 27 avril 2010 as its own foundational legal instrument,
         confirming ARMP itself (not a separate body) is the Art. 14
         regulatory institution.
  - **This iteration specifically investigated, rather than assumed,
    WHICH body operates the e-procurement portal -- and found a
    GENUINELY DIFFERENT institutional shape than Benin's.** In Benin,
    ARMP regulates/adjudicates but a DIFFERENT body (DNCMP) operates the
    e-procurement portal (SIGMAP/marches-publics.bj). In DRC, ARMP
    itself DIRECTLY operates the live e-procurement portal at
    marche.armp-rdc.cd (WebFetch-confirmed: publishes calls for tender,
    provisional/definitive awards and infructuosité declarations across
    60+ contracting authorities including BCECO, REGIDESO, FNPSS). ARMP
    is separately piloting a fuller Système Intégré de Gestion des
    Marchés Publics (SIGMAP) rollout -- its own site describes
    'lancement des activités du centre de service partagé du système
    intégré de gestion des marchés publics au centre financier de
    Kinshasa' -- but this iteration did NOT independently confirm
    SIGMAP is live nationwide (an honest, explicitly-flagged gap;
    marche.armp-rdc.cd's own notice-publication portal IS confirmed
    live and is what `:national-spec` below cites).
  - **Business/company registration**: DRC acceded to OHADA -- verified
    directly at ohada.org/etats-parties-rdc/, own text: 'Ratification :
    27 juin 2012 / Dépôt Instruments : 13 juillet 2012 / Entrée en
    vigueur : 12 septembre 2012'. RCCM (Registre du Commerce et du
    Crédit Mobilier) immatriculation runs through OHADA's Acte Uniforme
    relatif au Droit Commercial Général (AUDCG, a SUPRANATIONAL
    instrument -- see `statute.facts` for the OHADA direct-effect
    finding, same as every OHADA-member sibling) via the national
    Guichet Unique de Création d'Entreprise (GUCE), the same
    'single-window' pattern Benin's APIEx/GUFE uses, but a DIFFERENT
    implementing instrument: Décret n°12/045 du 1er novembre 2012
    portant création, organisation et fonctionnement d'un Guichet
    Unique de Création d'Entreprise, whose procedures are fixed by
    Arrêté ministériel n°035/CAB/MIN/J&DH/2013 du 4 mars 2013 (Ministre
    de la Justice et Droits Humains -- downloaded and read in full
    directly from leganet.cd). This iteration read the arrêté's own
    primary text: Art. 3, RCCM immatriculation/inscription modificative
    may NOT exceed 3 business days from a complete file; Art. 13-14, a
    NOTAIRE authenticates the statutes and a GREFFIER DIVISIONNAIRE
    performs the RCCM immatriculation (both judicial officers, the same
    'RCCM registration is a judicial act' shape Benin's greffe du
    Tribunal de Commerce finding established); Art. 16, publication in
    the Journal Officiel within 24 hours; Art. 20, GUCE registration
    data is transmitted to the Direction Générale des Impôts (DGI), the
    Office National de l'Emploi, l'Inscription générale du Travail,
    l'Institut National de la Sécurité Sociale (INSS) and l'Agence
    Nationale de Promotion des Investissements (ANAPI), among others --
    the same 'one guichet, multiple downstream registrations' shape
    Benin's APIEx/GUFE uses, though the DOWNSTREAM bodies named differ.
    NOTE: the domain `anapi.org` (unlike `anapi.cd`, the real, live
    ANAPI site) has been compromised/expired and now hosts unrelated
    third-party spam content -- this catalog and `README.md` never cite
    `anapi.org`, only the verified `anapi.cd`.
  - **Tax registration is the Direction Générale des Impôts (DGI),
    Ministère des Finances** -- confirmed directly at dgi.gouv.cd/
    a-propos/: DGI was created by Décret n°17/2003 du 2 mars 2003,
    modified by Décret n°22/52 du 30 décembre 2022, operating with
    administrative and financial autonomy under the Minister of
    Finance. DGI issues the Numéro d'Identification Fiscale (NIF) --
    DGI's own site states directly 'Le Numéro d'Identification Fiscale
    est indispensable pour toute activité économique', via a live
    online e-NIF portal (e-nif.dgirdc.cd) and a dedicated
    dgi-immatriculation.cd portal. This iteration did NOT independently
    verify the SPECIFIC law/decree/arrêté number that creates the NIF
    obligation itself (as distinct from DGI's own 2003 creation decree)
    -- dgi-immatriculation.cd renders as a JS single-page-app with no
    server-rendered legal-citation text reachable via direct fetch, and
    the 'Code des Impôts' page similarly did not expose a specific
    citation to this iteration's fetch. An honest, explicitly-flagged
    gap: `:corporate-number-legal-basis` below cites DGI's OWN creation
    decree (verified) plus DGI's own site statement that NIF is
    mandatory, but does NOT claim a specific NIF-creating law/decree
    number this iteration did not independently read.
  - **`:national-regional-preference-*` grounds this vertical's
    FLAGSHIP governor check** (see `marketentry.governor` /
    `marketentry.registry`) -- read directly from Loi n°10/010's own
    Section 5 ('De la préférence nationale et régionale'), Art. 37: a
    SIX-TIER ranked bid-evaluation preference, applied 'lors de la
    passation d'un marché public, soit par appel d'offres soit de gré à
    gré', in this exact priority order: (1) a Congolese natural person;
    (2) a Congolese SME majority-owned by Congolese natural/legal
    persons; (3) a Congolese legal person; (4) groupings of enterprises
    associating Congolese enterprises or providing for subcontracting
    to nationals (per Art. 59); (5) a foreign natural/legal person
    justifying economic activity on Congolese territory; (6) a foreign
    natural/legal person from a State party to a treaty/agreement with
    DRC granting such preference. Own text: 'Elle consiste en un
    abattement sur l'offre financière du soumissionnaire. Dans tous les
    cas, elle devra être indiquée et quantifiée dans l'appel d'offres.'
    -- i.e. the RANKING/CLASSIFICATION into one of these 6 categories
    is FIXED by the law's own text, but the abattement's exact
    MAGNITUDE is deliberately left discretionary, quantified per-tender
    in the cahier des charges. This governor's flagship check therefore
    recomputes ONLY the tier classification (a genuinely different
    check SHAPE from Benin's Art. 77 -- Benin recomputes a PRICE the
    engagement is entitled to; this recomputes which of 6 RANKED
    CATEGORIES an engagement's own declared nationality/ownership facts
    place it in), honestly declining to invent the discretionary
    abattement percentage -- the SAME honest-scope-narrowing discipline
    Benin's own catalog used for Art. 77's OTHER, discretionary branch.
    DRC's own Art. 61 (a foreign candidate committing to subcontract
    >=30% of contract value to a Congolese enterprise, or field >=40%
    Congolese experts in its key team, 'pourra bénéficier d'une marge de
    préférence sous forme d'abattement qui ne pourra être supérieure à
    cinq pourcent') is the DIRECT DRC-law analogue of Benin's Art. 77
    second branch -- capped at 5% but not fixed -- and is, for the same
    reason, deliberately NOT modeled here.
  - **`rep-spec-basis` is REAL and NON-nil for COD** -- a genuinely
    different finding than Benin, whose Art. 61/62 extends exclusion
    grounds to CONSORTIUM MEMBERS AND SUBCONTRACTORS (not the bidder's
    own officers). DRC's Loi n°10/010 Art. 23(a) (qualification
    criteria) lists, among the candidate's own qualification conditions:
    'l'absence de disqualification ou de condamnation de l'entreprise
    candidate OU DE SES DIRIGEANTS liée à la passation des marchés
    publics ou à leur activité professionnelle' -- i.e. DRC's law
    directly extends disqualification/conviction grounds to the
    CANDIDATE'S OWN DIRECTORS/OFFICERS, the shape Benin's docstring
    explicitly notes it did NOT find for Benin. Art. 23(a) ALSO
    requires 'la situation vis-à-vis des services d'impôts, des
    douanes et des organismes de protection sociale' (tax, customs and
    social-security good standing) -- grounding the INSS
    (Institut National de la Sécurité Sociale) evidence item below.
    This iteration did NOT independently verify INSS's own founding
    legal instrument (an honest gap; INSS's existence and role are
    corroborated by Art. 20 of the GUCE arrêté above, and inss.cd
    resolves as a live, real domain even though its content could not
    be fetched -- a 500 Internal Server Error, not a DNS/nonexistence
    failure).
  - **ARMP also runs a real, live debarment mechanism** -- Loi n°10/010
    Art. 80 (acts of impropriety) + Art. 81 (own primary text): sanctions
    pronounced BY ARMP are 'l'exclusion temporaire de la commande
    publique' (capped at a maximum of five years) OR, on repeat offence,
    'la déchéance définitive' (permanent forfeiture, pronounced by the
    competent jurisdiction at ARMP's request); 'L'Institution dresse
    périodiquement la liste des personnes physiques ou morales déchues
    du droit de concourir au marché public... publiée au journal des
    marchés publics.' This iteration independently confirmed this is a
    LIVE mechanism, not merely a paper provision: armp-rdc.cd/liste-noire/
    is a real, actively-maintained page (its own page metadata:
    published 2022-02-19, last modified 2024-10-22) with a table
    structure (Nom/Adresse/Nationalité/Numéro de décision/Date début
    (visibilité)/Date Fin (visibilité)/Motif) that directly mirrors
    Art. 81's own 'temporary, time-bounded exclusion, periodically
    updated' text -- though no populated entries were visible in this
    iteration's fetch (an honest, explicitly-flagged gap: the
    MECHANISM's reality is confirmed, no specific excluded entity is
    claimed). `:required-evidence` below cites this as a checklist item
    (non-exclusion confirmation) rather than building a dedicated
    governor check on it -- the same 'evidence checklist item, not a
    standalone recompute' treatment Benin's IFU-verification receives
    for a real-but-non-flagship mechanism.
  - **COREF (Comité d'Orientation de la Réforme des Finances Publiques,
    coref.cd) is REAL but is NOT DRC's public-procurement regulator** --
    this iteration specifically checked, because an earlier stub in
    this repo's own README.md/organization.edn named 'ARMP / COREF'
    together as if jointly responsible for procurement. COREF's own
    site describes it as a BROADER public-finance-reform steering
    committee ('doter la RDC d'un système de gestion des finances
    publiques performant...') coordinating 8 reform axes (budget,
    fiscal system, expenditure, accounting, financial control, fiscal
    decentralization, participatory governance, digitization) and
    piloting the World-Bank-funded ENCORE project; its own 'Publications
    Marchés' section is COREF publishing ITS OWN project procurement
    notices, not evidence of a procurement-regulation mandate. This
    catalog corrects that: `:owner-authority` is ARMP alone, grounded in
    Loi n°10/010 Art. 14, not COREF.

  Coverage is reported HONESTLY (see `coverage`): a jurisdiction not in
  this table has NO spec-basis, full stop -- the advisor must not
  fabricate one, and the governor holds if it tries.")

(def catalog
  "iso3 -> requirement map. `:required-evidence` mirrors the generic
  intake/portal-registration/filing evidence set; `:legal-basis` /
  `:owner-authority` / `:provenance` are the G2 citation the governor
  requires before any `:jurisdiction/assess` proposal can commit.
  `:national-regional-preference-*` grounds this vertical's flagship
  governor check (`preference-tier-mismatches-claim?` in
  `marketentry.registry`) -- a RANKED-CLASSIFICATION check on the
  engagement's own declared nationality/ownership facts against Loi
  n°10/010 Art. 37's 6-tier priority order, genuinely different from
  every prior sibling's check shape (see namespace docstring). COD
  carries a NON-nil `rep-spec-basis` -- a genuinely different finding
  from Benin (see namespace docstring)."
  {"COD" {:name "Democratic Republic of the Congo"
          :owner-authority "Autorité de Régulation des Marchés Publics (ARMP) -- the ex-post regulatory institution created under Loi n°10/010 Art. 14 ('institution chargée notamment du contrôle a posteriori des marchés publics... de l'audit, de la formation et de renforcement des capacités'), organized by décret du Premier ministre, operating under the Primature (Prime Minister's office)"
          :legal-basis "Loi n°10/010 du 27 avril 2010 relative aux marchés publics -- Art. 13 (contrôle a priori assigned to a service under the ministry in charge of the budget, i.e. DGCMP) + Art. 14 (creates ARMP, the ex-post regulatory institution) + Art. 84 (abrogates the prior Ordonnance-loi n°69-054 du 5 décembre 1969)"
          :national-spec "marche.armp-rdc.cd -- a live e-procurement notice portal (calls for tender, provisional/definitive awards, infructuosité declarations across 60+ contracting authorities), operated DIRECTLY by ARMP itself (a DIFFERENT institutional shape from Benin, where the portal is operated by a body SEPARATE from the regulator). Ex-ante procedural control ('contrôle a priori') is separately performed by the Direction Générale de Contrôle des Marchés Publics (DGCMP, dgcmp.cd), per Art. 13"
          :provenance "https://armp-rdc.cd/wp-content/uploads/2021/12/Lois-relative-aux-marchs-publicsx.pdf"
          :required-evidence ["RCCM registration record (Registre du Commerce et du Crédit Mobilier -- immatriculation via the Guichet Unique de Création d'Entreprise (GUCE), performed by a Notaire + Greffier divisionnaire, under OHADA's Acte Uniforme relatif au Droit Commercial Général, Décret n°12/045 du 1er novembre 2012)"
                              "NIF record (Numéro d'Identification Fiscale -- Direction Générale des Impôts (DGI), via the e-NIF online portal, e-nif.dgirdc.cd)"
                              "marche.armp-rdc.cd tender-participation / contracting-authority registration record (Autorité de Régulation des Marchés Publics)"
                              "Confirmation the candidate (and its dirigeants) is not on ARMP's published exclusion list ('liste noire'), Loi n°10/010 Art. 80/81"
                              "INSS (Institut National de la Sécurité Sociale) good-standing confirmation, Loi n°10/010 Art. 23(a) 'situation vis-à-vis des... organismes de protection sociale'"
                              "Authorized-representative confirmation record"]
          :corporate-number-owner-authority "Direction Générale des Impôts (DGI)"
          :corporate-number-legal-basis "Décret n°17/2003 du 2 mars 2003 portant création de la Direction Générale des Impôts, modifié par le Décret n°22/52 du 30 décembre 2022 -- DGI operates under the Ministère des Finances with administrative and financial autonomy and issues the Numéro d'Identification Fiscale (NIF); DGI's own site states 'Le Numéro d'Identification Fiscale est indispensable pour toute activité économique'. This iteration did NOT independently verify the specific law/decree creating the NIF obligation itself (an honest gap, see namespace docstring)"
          :corporate-number-provenance "https://dgi.gouv.cd/ ; https://dgi.gouv.cd/a-propos/ ; https://e-nif.dgirdc.cd/"
          :business-registration-owner-authority "Guichet Unique de Création d'Entreprise (GUCE) -- RCCM immatriculation is executed by a Notaire (authentication) + Greffier divisionnaire (immatriculation), both judicial officers, per OHADA's Acte Uniforme relatif au Droit Commercial Général"
          :business-registration-legal-basis "Décret n°12/045 du 1er novembre 2012 portant création, organisation et fonctionnement d'un Guichet Unique de Création d'Entreprise; procedures fixed by Arrêté ministériel n°035/CAB/MIN/J&DH/2013 du 4 mars 2013 (Ministre de la Justice et Droits Humains) -- own primary text: Art. 3 (RCCM immatriculation/inscription modificative within a statutory maximum of 3 business days from a complete file); Art. 20 (GUCE registration data transmitted to DGI, l'Office National de l'Emploi, l'Inscription générale du Travail, l'Institut National de la Sécurité Sociale (INSS) and l'Agence Nationale de Promotion des Investissements (ANAPI), among others)"
          :business-registration-provenance "https://www.leganet.cd/Legislation/Droit%20Public/EPub/AM.35.04.03.2013.htm"
          :rep-owner-authority "Autorité de Régulation des Marchés Publics (ARMP) -- Loi n°10/010's own Art. 23(a) qualification criteria"
          :rep-legal-basis "Loi n°10/010 du 27 avril 2010, Art. 23(a): candidate qualification criteria include 'l'absence de disqualification ou de condamnation de l'entreprise candidate ou DE SES DIRIGEANTS liée à la passation des marchés publics ou à leur activité professionnelle' -- extends disqualification/conviction grounds to the CANDIDATE'S OWN DIRECTORS/OFFICERS, a DIFFERENT scope than Benin's Art. 61/62 (which extends instead to consortium members and subcontractors, not the bidder's own officers)"
          :rep-provenance "https://armp-rdc.cd/wp-content/uploads/2021/12/Lois-relative-aux-marchs-publicsx.pdf"
          :national-regional-preference-owner-authority "Autorité de Régulation des Marchés Publics (ARMP) -- the Code's regulator; the abattement's magnitude is applied and quantified by each autorité contractante, per tender, in the cahier des charges"
          :national-regional-preference-legal-basis "Loi n°10/010 du 27 avril 2010, Section 5 ('De la préférence nationale et régionale'), Art. 37: a 6-tier ranked bid-evaluation preference (abattement sur l'offre financière) applied in priority order to (1) a Congolese natural person, (2) a Congolese SME majority-owned by Congolese persons, (3) a Congolese legal person, (4) groupings with Congolese participation/subcontracting, (5) a foreign entity with Congolese economic activity, (6) a foreign entity from a treaty-preference State -- the RANKING is fixed by the law's own text; the abattement's magnitude is discretionary, 'indiquée et quantifiée dans l'appel d'offres' (NOT modeled here, the same honest scope-narrowing Benin's catalog applied to its own Art. 77 discretionary branch)"
          :national-regional-preference-tiers [{:tier 1 :key :congolese-natural-person? :label "Personne physique de nationalité congolaise"}
                                               {:tier 2 :key :congolese-sme-majority-congolese-capital? :label "PME congolaise dont le capital est détenu majoritairement par des personnes physiques de nationalité congolaise ou des personnes morales de droit congolais"}
                                               {:tier 3 :key :congolese-legal-person? :label "Personne morale de droit congolais"}
                                               {:tier 4 :key :grouping-congolese-participation-or-subcontracting? :label "Groupement d'entreprises associant des entreprises congolaises ou prévoyant une sous-traitance aux nationaux (Art. 59)"}
                                               {:tier 5 :key :foreign-congolese-economic-activity? :label "Personne physique ou morale étrangère justifiant d'une activité économique sur le territoire congolais"}
                                               {:tier 6 :key :foreign-treaty-preference? :label "Personne physique ou morale étrangère ressortissante d'un État partie à un traité/accord reconnaissant cette préférence"}]
          :national-regional-preference-provenance "https://armp-rdc.cd/wp-content/uploads/2021/12/Lois-relative-aux-marchs-publicsx.pdf"}
   "USA" {:name "United States"
          :owner-authority "U.S. General Services Administration (GSA) / SAM.gov"
          :legal-basis "Federal Acquisition Regulation (FAR); System for Award Management"
          :national-spec "SAM.gov entity registration + NAICS self-certification"
          :provenance "https://sam.gov/"
          :required-evidence ["EIN record"
                              "SAM.gov registration record"
                              "State business registration record"
                              "Authorized-representative record"]}
   "DEU" {:name "Germany"
          :owner-authority "Beschaffungsamt des BMI / e-Vergabe platforms"
          :legal-basis "Gesetz gegen Wettbewerbsbeschränkungen (GWB) / VgV"
          :national-spec "e-Vergabe supplier registration under EU procurement directives"
          :provenance "https://www.evergabe-online.de/"
          :required-evidence ["Handelsregister extract"
                              "e-Vergabe registration record"
                              "USt-IdNr record"
                              "Authorized-representative record"]}})

(defn spec-basis
  "The jurisdiction's requirement map, or nil -- nil means NO spec-basis,
  and the governor must hold any proposal that tries to assess or file
  on it."
  [iso3]
  (get catalog iso3))

(defn coverage
  "Honest coverage report: how many of the requested jurisdictions actually
  have a spec-basis entry. Never report a missing jurisdiction as covered."
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-cod R0: " (count catalog)
                 " jurisdictions seeded with an official spec-basis. "
                 "This is a starting catalog for market-entry navigation, "
                 "not a survey of all ~194 jurisdictions -- extend "
                 "`marketentry.facts/catalog`, never fabricate a "
                 "jurisdiction's requirements.")})))

(defn required-evidence-satisfied?
  "Does `submitted` (a set/coll of evidence keywords or strings) satisfy
  every evidence item listed for `iso3`? Missing spec-basis -> never
  satisfied."
  [iso3 submitted]
  (when-let [{:keys [required-evidence]} (spec-basis iso3)]
    (let [need (count required-evidence)
          have (count (filter (set submitted) required-evidence))]
      (= need have))))

(defn evidence-checklist [iso3]
  (:required-evidence (spec-basis iso3) []))

(defn rep-spec-basis
  "The jurisdiction's representative-related requirement map, or nil when
  this catalog has no such regime. For COD this is REAL and non-nil --
  see the `catalog` docstring's finding on Art. 23(a)'s extension of
  disqualification/conviction grounds to the candidate's OWN
  directors/officers (a different scope than Benin's consortium/
  subcontractor extension)."
  [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:rep-owner-authority sb)
      (select-keys sb [:rep-owner-authority :rep-legal-basis :rep-provenance]))))

(defn corporate-number-spec-basis
  "The jurisdiction's corporate-number / tax-id regime, or nil."
  [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:corporate-number-owner-authority sb)
      (select-keys sb [:corporate-number-owner-authority
                       :corporate-number-legal-basis
                       :corporate-number-provenance]))))

(defn business-registration-spec-basis
  "The jurisdiction's business (state) registration regime, or nil. DRC's
  business/company registration runs through the Guichet Unique de
  Création d'Entreprise (GUCE) -- see namespace docstring for the
  Décret n°12/045 du 1er novembre 2012 grounding."
  [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:business-registration-owner-authority sb)
      (select-keys sb [:business-registration-owner-authority
                       :business-registration-legal-basis
                       :business-registration-provenance]))))

(defn national-regional-preference-spec-basis
  "The jurisdiction's national/regional preference regime, or nil. For
  COD this is HIGH confidence, grounded directly in Loi n°10/010 Art.
  37's own primary text -- the flagship check this vertical adds (a
  RANKED-CLASSIFICATION recompute, see `marketentry.registry`) is
  grounded here, not copied from a sibling's citation."
  [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:national-regional-preference-owner-authority sb)
      (select-keys sb [:national-regional-preference-owner-authority
                       :national-regional-preference-legal-basis
                       :national-regional-preference-tiers
                       :national-regional-preference-provenance]))))
