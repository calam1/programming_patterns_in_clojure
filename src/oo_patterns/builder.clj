(ns oo-patterns.builder)

;; instead of using the builder pattern

(def person
  {:first-name "John"
   :middle-name "Quincy"
   :last-name "Adams"})

;; usage
;(person :first-name)
;(get person :first-name)
;(into {} (for [[k v] person] [k (.toUpperCase v)]))

;; using multimethods
(def cat {:type :cat
          :color "black"
          :name "Fuzzy Cat"})

(def dog {:type :dog
          :color "brown"
          :name "Rover"})

(defmulti make-noise (fn [animal] (:type animal)))
(defmethod make-noise :cat [cat] (println (str (:name cat)) "meows"))
(defmethod make-noise :dog [dog] (println (str (:name dog)) "bark"))

;;usage
;(make-noise cat)
;(make-noise dog)


;; defrecord and protocol example - if you need something more than a simple map to
;; represent your object

(defrecord Kitty [color name])
(defrecord Doggy [color name])

(def kit (Kitty. "Calicl" "Fuzzy Cat"))
(def doogy (Doggy. "Brown" "Brown Dog"))

;; map usage
;(:name kit)
;(:mame doogy)


(defprotocol NoiseMaker
  (protocol-noise-maker [this]))

(defrecord NoisyCat [color name]
  NoiseMaker
    (protocol-noise-maker [this] (str (:name this) "meows")))

(defrecord NoisyDog [color name]
    NoiseMaker
  (protocol-noise-maker [this] (str (:name this) "barks")))

;;usage
;(def noisy-cat (NoisyCat. "Calico" "Fuzzy Cat"))
;(def noisy-dog (NoisyDog. "Brown" "Brown Dog"))
;(protocol-noise-maker noisy-cat)
;(protocol-noise-maker noisy-dog)


;; Notes
; Here we define a protocol that has a single function, protocol-noise-maker
; and we create a NoisyCat and NoisyDog to take advantage ofit

