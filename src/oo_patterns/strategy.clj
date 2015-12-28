(ns oo-patterns.strategy)

;; this is similar to template and functional interface, etc.
;; function composition
;; apparently the same issues you encounter in OO/Imperative languages
;; do not apply to Functional langs

(defn first-name-valid?
  [person]
  (not (nil? (:first-name person))))

(defn full-name-valid?
  [person]
  (and
  (not (nil? (:first-name person)))
  (not (nil? (:middle-name person)))
  (not (nil? (:last-name person)))))

(defn person-collector
  [valid?]
  (let [valid-people (atom [])]
    (fn [person]
      (if (valid? person)
        (swap! valid-people conj person))
      @valid-people)))

(def first-name-valid-collector (person-collector first-name-valid?))
(def full-name-valid-collector (person-collector full-name-valid?))

;; test data
(def p1 {:first-name "John" :middle-name "Quincy" :last-name "Adams"})
(def p2 {:first-name "Mike" :middle-name nil :last-name "Adams"})

;; usage
;(full-name-valid-collector p1)
