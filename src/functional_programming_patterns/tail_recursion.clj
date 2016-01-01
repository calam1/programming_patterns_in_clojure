(ns functional-programming-patterns.tail-recursion)

(defn make-people
  (loop [first-names first names last-names last-names people []]
  [first-names last-names]
  (if (seq first-names)
    (recur
     (rest first-names)
     (rest last-names)
     (conj
      people
      {:first (first first first-names) :last (first last-names)}))
    people)))

(defn shorter-make-people
  [first-names last-names]
  (for [[first last] (partition 2 (interleave first-names last-names))]
    {:first first :last last}))


