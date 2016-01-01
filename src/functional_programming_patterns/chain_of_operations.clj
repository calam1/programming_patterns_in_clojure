(ns functional-programming-patterns.chain-of-operations)

(def v1
  {:title "Pianocat Plays Carnegie Hall"
   :type :cat
   :length 300})

(def v2
  {:title "Paint Drying"
   :type :home-improvement
   :length 600})

(def v3
  {:title "Fuzzy McMittens Live at the Apollo"
   :type :cat
   :length 200})

(def videos [v1 v2 v3])

(defn cat-time
  [videos]
  (apply +
         (map :length (filter (fn [video] (= :cat (:type video)))
                              videos))))

(defn easier-to-read-cat-time
  [videos]
  (->> (filter (fn [video] (= :cat (:type video))) videos)
       (map :length)
       (apply +)))

(defn easier-to-read-cat-time-2
  [videos]
  (->> videos
       (filter (fn [video] (= :cat (:type video))) )
       (map :length)
       (apply +)))
