(ns functional-programming-patterns.focused-mutability)

(defn test-immutable
  [count]
  (loop [i 0 s []]
    (if (< i count)
      (recur (inc i) (conj s i))
      s)))

(defn test-mutable
  [count]
  (loop [i 0 s (transient [])]
    (if (< i count)
      (recur (inc i) (conj! s i))
      (persistent! s))))

;; time macro
(defmacro time-runs
  [fn count]
  `(dotimes [_# ~count]
     (time ~fn)))

;; usage
(def one-million 1000000)
; (time-runs (test-immutable one-million) 5)
; (time-runs (test-mutable one-million) 5)

(defn make-test-purchase
  []
  {:store-number (rand-int 100)
   :customer-number (rand-int 100)
   :item-number (rand-int 500)})

(defn infinite-test-purchases
  []
  (repeatedly make-test-purchase))

(defn immutable-sequence-event-processing
  [count]
  (let [test-purchases (take count (infinite-test-purchases))]
    (reduce
     (fn [map-of-purchases {:keys [store-number] :as current-purchase}]
       (let [purchases-for-store (get map-of-purchases store-number '())]
         (assoc map-of-purchases store-number
                (conj purchases-for-store current-purchase))))
     {}
     test-purchases)))

(defn mutable-sequence-event-processing
  [count]
  (let [test-purchases (take count (infinite-test-purchases))]
    (persistent! (reduce
     (fn [map-of-purchases {:keys [store-number] :as current-purchase}]
       (let [purchases-for-store (get map-of-purchases store-number '())]
         (assoc! map-of-purchases store-number
                (conj purchases-for-store current-purchase))))
     (transient {})
     test-purchases))))

;; usage
(def five-hundred-thousand 50000)
; (time-runs (immutable-sequence-event-processing five-hundred-thousand) 5)
; (time-runs (mutable-sequence-event-processing five-hundred-thousand) 5)

;; Notes
; Focused Mutability is an optimization pattern. It’s the sort of
; thing that the old advice to avoid premature optimization is all
; about. As we’ve seen from this chapter, Scala and Clojure’s
; immutable data structures perform very well—not much worse than
; their mutable counterparts! If you’re modifying several immutable
; data structures in one go and if you’re doing it for large amounts
; of data, you’re likely to see a significant improvement. However,
; immutable data structures should be the default—they’re usually
; plenty fast.


