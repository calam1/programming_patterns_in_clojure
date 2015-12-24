(ns oo-patterns.functional-interface)

;; This example replaces java Comparator

(def p1 {:first-name "Michael" :last-name "Bevilacqua"})
(def p2 {:first-name "Pedro" :last-name "Vasquez"})
(def p3 {:first-name "Robert" :last-name "Aarons"})

(def people [p3 p2 p1])


;; sort people without java comparator
;(sort (fn [p1 p2] (compare (p1 :first-name) (p2 :first-name))) people)

(def p4 {:first-name "Aaron" :middle-name "Jeffery" :last-name "Smith"})
(def p5 {:first-name "Aaron" :middle-name "Bailey" :last-name "Zanthar"})
(def p6 {:first-name "Brian" :middle-name "Adams" :last-name "Smith"})

; complicated sort
(defn complicated-sort
  [p1 p2]
  (let [first-name-compare (compare (p1 :first-name) (p2 :first-name))
        middle-name-compare (compare (p1 :middle-name) (p2 :middle-name))
        last-name-compare (compare (p1 :last-name) (p2 :last-name))]
    (cond
     (not (= 0 first-name-compare)) first-name-compare
     (not (= 0 last-name-compare)) last-name-compare
     :else middle-name-compare)))

(def peopleComplex [p6 p5 p4])

;;sort with a more complicated scenario 
;(sort complicated-sort peopleComplex)


;; Notes
;;Since Functional Interface is implemented with a class, it defines a type and can use common object-oriented features such as subclassing and polymorphism. Higher-order functions cannot. This is actually a strength of higher-order functions over Functional Interface: you don’t need a new type for each type of Functional Interface when just the existing function types will do.
