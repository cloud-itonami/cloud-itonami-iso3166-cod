# cloud-itonami-iso3166-cod

**COD**: Democratic Republic of the Congo (capital Kinshasa) --
**NOT** the Republic of the Congo / Congo-Brazzaville (ISO code
**COG**, capital Brazzaville, a separate country with its own separate
public-procurement regulator, confusingly also abbreviated "ARMP", at
`armp.cg`). Every regulatory citation in this repo (`src/marketentry/
facts.cljc`, `organization.edn`) is `armp-rdc.cd`-domain / DRC-specific
-- do not let a Congo-Brazzaville (COG) fact leak in here, and do not
cite `armp.cg` from this repo. `:implemented` -- "Actors" pattern
market-entry / public-procurement compliance service (Governor + LLM
advisor + langgraph-clj StateGraph + append-only audit ledger + Store).
**Seven** governor checks -- see `src/marketentry/governor.cljc` for
why (two distinct jurisdiction-specific hard checks: the flagship
Loi n°10/010 Art. 37 national/regional preference-tier recompute, and
an independently-confirmed NIF/DGI tax-id check).

```
clojure -M:dev:test
```

- ARMP (Autorité de Régulation des Marchés Publics, Loi n°10/010 du 27
  avril 2010) public procurement -- ARMP itself operates the live
  e-procurement notice portal at `marche.armp-rdc.cd`; ex-ante control
  is separately performed by DGCMP. Note: COREF (Comité d'Orientation
  de la Réforme des Finances Publiques) is a real body but is a
  broader public-finance-reform coordinator, NOT the procurement
  regulator -- see `src/marketentry/facts.cljc` for why it is not
  cited as `:owner-authority`.
- RCCM company registration via the Guichet Unique de Création
  d'Entreprise (GUCE, Décret n°12/045 du 1er novembre 2012) + NIF tax
  registration (Direction Générale des Impôts, DGI); Loi n°10/010 Art.
  37's 6-tier national/regional bid-evaluation preference gate

AGPL-3.0-or-later.

## Culture catalog

This repo carries a **country-level regional-culture catalog**
(ADR-2607171400 addendum 2, `cloud-itonami-municipality-culture-catalog`
Wave 1, in `com-junkawasaki/root`) — national dishes, protected products,
beverages, crafts, festivals and heritage sites for the Democratic
Republic of the Congo:

- `src/culture/facts.cljc` — the catalog, source of truth (keyed by
  uppercase ISO3, mirroring `statute.facts`).
- `schema/culture.edn` — DataScript schema.
- `data/culture-tx.edn` — derived DataScript tx-data (regenerated from
  the catalog, never hand-edited).

City-level counterparts live in the `cloud-itonami-municipality-*` repos.
Same provenance discipline as the compliance catalogs: every entry cites a
source URL that was actually fetched and read on `:culture/retrieved-at`;
summaries state only what the cited source confirms. An item not in
`culture.facts/catalog` has no spec-basis — never fabricate one.
