(ns functional-programming-patterns.lazy-sequence)

(def integers (range Integer/MAX_VALUE))

;; usage
; (take 5 integers)

(def randoms (repeatedly (fn [] (rand-int Integer/MAX_VALUE))))

;; usage - this is memoized so if you do (take 3 randoms) then (take 4
; randoms) the first 3 values are  the same as the original function call
; (take 5 randoms)

(def print-hellos (repeatedly (fn [] (println "hello"))))

;; usage
; (take 5 print-hellos)

(defn get-page
  [page-num]
  (cond
   (= page-num 1) "Page1"
   (= page-num 2) "Page2"
   (= page-num 3) "Page3"
   :default nil))

(defn paged-sequence
  [page-num]
  (let [page (get-page page-num)]
    (when page
      (cons page (lazy-seq (paged-sequence (inc page-num)))))))

;; usage
; (paged-sequence 1)

