(ns oo-patterns.decorator)

;; decorator like most if not all the OO patterns are replaced by some
;; sort of function composition or higher order functions

(defn add
   [a b]
   (+ a b))

(defn subtract
  [a b]
  (- a b))

(defn multiply
  [a b]
  (* a b))

(defn divide
  [a b]
  (/ a b))

(defn make-logger
  [calc-fn]
  (fn [a b]
    (let [result (calc-fn a b)]
      (println (str "Result is: " result))
      result)))

(def logging-add (make-logger add))
(def logging-subtract (make-logger subtract))
(def logging-multiply (make-logger multiply))
(def logging-divide (make-logger divide))

;; usage
;(logging-add 2 3)

