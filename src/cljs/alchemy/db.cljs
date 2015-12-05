(ns alchemy.db)

(def database {"steam" #{"fire" "water"}
               "dust" #{"earth" "air"}
               "smoke" #{"fire" "dust"}
               "clouds" #{"water" "air"}
               "gold" #{"water" "fire" "earth" "air"}})
           
(def default-items (sorted-set "fire" "water" "earth" "air"))

(def default-db
  {:database database
   :available default-items
   :selected #{}
   :last-matched nil})
