(ns oo-patterns.template)

;; function composition
(defn make-grade-reporter
  [num-to-letter print-grade-report]
  (fn [grades]
    (print-grade-report (map num-to-letter grades))))

(defn full-grade-reporter
  [grade]
  (cond
   (and (<= grade 5.0) (> grade 4.0)) "A"
   (and (<= grade 4.0) (> grade 3.0)) "B"
   (and (<= grade 3.0) (> grade 2.0)) "C"
   (and (<= grade 2.0) (> grade 0)) "D"
   (= grade 0) "F"
   :else "N/A"))

(defn print-histogram
  [grades]
  (let [grouped (group-by identity grades) ; i.e. {"A" ["A" "A"], "B" ["B"]}
        counts (sort (map (fn [[grade grades]] [grade (count grades)]) grouped))] ; inline fn destructures
    (doseq [[grade num] counts]
      (println (str grade ":" (apply str (repeat num "*")))))))

(def full-grade-reporter (make-grade-reporter full-grade-reporter print-histogram))

(def sample-grades [5.0 4.0 4.4 2.2 3.3 3.5])

;; usage
; (full-grade-reporter sample-grades)

