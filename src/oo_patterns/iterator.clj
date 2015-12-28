(ns oo-patterns.iterator)

(def vowel? #{\a \e \i \o \u})
(defn vowels-in-word
  [word]
  (set (filter vowel? word)))

;; usage
;(vowels-in-word "onomotopeia")
;(vowels-in-word "yak")

(defn prepend-hello
  [names]
  (map (fn [name] (str "Hello, " name)) names))

;; usage
;(prepend-hello ["Mike" "Tom" "Harry"])


(defn sum-sequence
  [s]
  (reduce + s))

;; usage
;(sum-sequence [1 2 3]

(def close-zip? #{19123 19103})

(defn print-greetings
  [people]
  (doseq [{:keys [name address]} people :when (close-zip? (address :zip-code))]
    (println (str "Hello, " name ", and welcome to the Lambda Bar and Grille!"))))

;; sample people object - Tom should print but not Chris
(def ppl [{:name "Tom" :address {:zip-code 19123}} {:name "Chris" :address {:zip-code 11111}}])

;; usage
; (print-greetings ppl)
