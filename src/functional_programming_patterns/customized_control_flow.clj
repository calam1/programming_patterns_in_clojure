(ns functional-programming-patterns.customized-control-flow)

(defn choose
  [num first second third]
  (cond
   (= 1 num) (first)
   (= 2 num) (second)
   (= 3 num) (third)))

;; usage
(choose 2
        (fn [] (println "hello, world"))
        (fn [] (println "goodbye, cruel world"))
        (fn [] (println "meh, indifferent world"))) 

;; how do we get to this
; (choose 2
;        (println "hello, world")
;        (println "goodbye, cruel world")
;        (println "meh, indifferent world"))

(defmacro simpler-choose
  [num first second third]
  `(cond
    (= 1 ~num) ~first
    (= 2 ~num) ~second
    (= 3 ~num) ~third))

;; usage
; (macroexpand-1
;  '(simpler-choose 1 (println "foo") (println "bar") (println "baz")))

; (simpler-choose 2
;    (println "hello, world")
;    (println "goodbye, cruel world")
;    (println "meh, indifferent world"))

;; average time
(defn time-run
  [to-time]
  (let [start (System/currentTimeMillis)]
    (to-time)
    (- (System/currentTimeMillis) start)))

(defmacro avg-time
  [times to-time]
  `(let [total-time#
         (apply + (for [_# (range ~times)] (time-run (fn [] ~to-time))))]
     (float (/ total-time# ~times))))

;; usage
; (avg-time 5 (Thread/sleep 1000)
