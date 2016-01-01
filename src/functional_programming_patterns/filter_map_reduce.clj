(ns functional-programming-patterns.filter-map-reduce)

(defn calculate-discount
  [prices]
  (reduce +
          (map (fn [price] (* price 0.10))
               (filter (fn [price] (>= price 20.0)) prices))))

(defn easier-to-read-calculate-discount
  [prices]
  (->> (filter (fn [price] (>= price 20.0)) prices)
      (map (fn [price] (* price 0.10)))
      (reduce +)))

(def sample-data [20.0 4.5 50.0 15.75 30.0 3.50])

