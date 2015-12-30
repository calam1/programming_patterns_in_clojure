(ns oo-patterns.visitor)

;; visitor - how to add functionality to existing record/class

(defprotocol NameExtractor
  (extract-name [this] "Extracts a name from a person."))

(defrecord SimplePerson
    [first-name last-name house-num street])

(def simple-person (->SimplePerson "Mike" "Linn" 123 "Fake St."))

;; usage
; (:first-name simple-person)

;; added a method to an existing record/class
(extend-type SimplePerson
  NameExtractor
  (extract-name
    [this]
    (str (:first-name this) " " (:last-name this))))

;; usage
; (extract-name simple-person)

(defrecord ComplexPerson
    [name address]
  NameExtractor
  (extract-name
    [this]
    (str (-> this :name :first) " "  (-> this :name :last))))

(def complex-person (->ComplexPerson {:first "Mike" :last "Linn"}
                                     {:house-num 123 :street "Fake St."}))

;; usage
; (extract-name complex-person)

(defprotocol AddressExtractor
  (extract-address
    [this]
    "Extracts an address from a person"))

(extend-type SimplePerson
  AddressExtractor
  (extract-address
    [this]
    (str (:house-num this) " " (:street this))))

(extend-type ComplexPerson
  AddressExtractor
  (extract-address
    [this]
    (str (-> this :address :house-num)
         " "
         (-> this :address :street))))

;; usage
;; as you can see the extract-name is the built in method
;; we added extract-address by extend-type as shown above
; (extract-name complex-person)
; (extract-address complex-person)

;; basic multimethod
(defmulti test-multimethod (fn [keyword] keyword))
(defmethod test-multimethod :foo
  [a-map]
  "foo-method was called")

(defmethod test-multimethod :bar
  [b-map]
  "bar-method was called")

;; usage
; (test-multimethod :foo)
; (test-multimethod :bar)

;; multimethod for shapes
(defmulti perimeter (fn [shape] (:shape-name shape)))

(defmethod perimeter :circle [circle]
  (* 2 Math/PI (:radius circle)))

(defmethod perimeter :rectangle [rectangle]
  (+ (* 2 (:width rectangle)) (* 2 (:height rectangle))))


;; test shapes
(def some-shapes [{:shape-name :circle :radius 4}
                  {:shape-name :rectangle :width 2 :height 2}])

;; usage
; (for [shape some-shapes] (perimeter shape))

;; add new operations
(defmulti area (fn [shape] (:shape-name shape)))

(defmethod area :circle [circle]
  (* Math/PI (:radius circle) (:radius circle)))

(defmethod area :rectangle [rectangle]
  (* (:width rectangle) (:height rectangle)))

;; usage
; (for [shape some-shapes] (area shape))

;; add new shapes/implementations
(defmethod perimeter :square [square]
  (* 4 (:side square)))
(defmethod area :square [square]
  (* (:side square) (:side square)))

;; usage
; add a square to the vector of shapes
; (def more-shapes (conj some-shapes
;   {:shape-name :square :side 4}))
; (for [shape some-shapes] (area shape))
; (for [shape some-shapes] (perimeter shape))
