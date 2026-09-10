(ns culture.facts
  "Country-level regional-culture catalog for the Democratic Republic of
  the Congo (COD) -- national dishes, protected products, beverages,
  crafts, festivals and heritage sites, per ADR-2607171400 addendum 2
  (cloud-itonami-municipality-culture-catalog Wave 1, in
  com-junkawasaki/root). Sibling namespace to `marketentry.facts` /
  `statute.facts` (ADR-2607141700); city-level counterparts live in the
  cloud-itonami-municipality-* repos.

  Catalog is keyed by UPPERCASE ISO3 (mirrors `statute.facts`); entries
  carry no :culture/municipality (that attribute is city-level only).
  COD is the DR Congo (Kinshasa); the Republic of the Congo
  (Brazzaville) is COG, a separate sibling catalog.

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of culture entries."
  {"COD"
   [{:culture/id "cod.dish.moambe-chicken"
     :culture/name "Moambe chicken"
     :culture/name-local "Poulet à la moambé"
     :culture/country "COD"
     :culture/kind :dish
     :culture/summary "Savoury chicken dish made with palm butter, regarded as the national dish of the Democratic Republic of the Congo; also a national dish of the Republic of the Congo and Angola."
     :culture/url "https://en.wikipedia.org/wiki/Moambe_chicken"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "cod.dish.chikwangue"
     :culture/name "Chikwangue"
     :culture/name-local "Kwanga"
     :culture/country "COD"
     :culture/kind :dish
     :culture/summary "Starchy fermented-cassava staple wrapped in leaves and steamed or boiled, eaten across Central Africa including the Democratic Republic of the Congo, where it is known in Lingala as kwanga."
     :culture/url "https://en.wikipedia.org/wiki/Chikwangue"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "cod.dish.maboke"
     :culture/name "Maboké"
     :culture/country "COD"
     :culture/kind :dish
     :culture/summary "Central African dish of fish wrapped and cooked in cassava or banana leaves, mainly eaten in the Central African Republic and the Democratic Republic of the Congo, using many fish species from the Congo River."
     :culture/url "https://en.wikipedia.org/wiki/Maboké"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "cod.beverage.lotoko"
     :culture/name "Lotoko"
     :culture/country "COD"
     :culture/kind :beverage
     :culture/summary "Home-distilled maize moonshine of the Democratic Republic of the Congo, exceeding 50% alcohol; officially banned yet produced widely across the country."
     :culture/url "https://en.wikipedia.org/wiki/Lotoko"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "cod.craft.kuba-textiles"
     :culture/name "Kuba textiles"
     :culture/country "COD"
     :culture/kind :craft
     :culture/summary "Elaborately decorated raffia cloths of the Kuba people with geometric embroidery, appliqué and cut-pile designs, unique to the Democratic Republic of the Congo."
     :culture/url "https://en.wikipedia.org/wiki/Kuba_textiles"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "cod.heritage.virunga"
     :culture/name "Virunga National Park"
     :culture/country "COD"
     :culture/kind :heritage
     :culture/summary "National park in the Albertine Rift Valley of the eastern Democratic Republic of the Congo, home to mountain gorillas; a UNESCO World Heritage Site since 1979, endangered-listed since 1994."
     :culture/url "https://en.wikipedia.org/wiki/Virunga_National_Park"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "cod.heritage.congolese-rumba"
     :culture/name "Congolese rumba"
     :culture/country "COD"
     :culture/kind :heritage
     :culture/summary "Dance-music genre that emerged in Léopoldville (now Kinshasa) and Brazzaville, added in December 2021 to the UNESCO list of intangible cultural heritage jointly for the Democratic Republic of the Congo and the Republic of the Congo."
     :culture/url "https://en.wikipedia.org/wiki/Congolese_rumba"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

(defn spec-basis [iso3] (get catalog iso3))

(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-cod culture catalog "
                 "(ADR-2607171400 addendum 2, Wave 1): " (count (get catalog "COD"))
                 " COD entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [iso3 kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis iso3)))
