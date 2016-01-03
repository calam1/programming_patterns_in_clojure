(ns functional-programming-patterns.function-builder)

(defn discount
  [percentage]
  {:pre [(and (>= percentage 0) (<= percentage 100))]}
   (fn [price] (- price (* price percentage 0.01))))

;; usage
; ((discount 50) 200)

(def twenty-five-percent-off (discount 25))

;; usage
; (apply + (map twenty-five-percent-off [75.0, 25.0]))

(def personAddress {:address {:street {:name "Fake St."}}})

;; usage
; (get-in person [:address :street :name])

;; building a selector on top of get-in
(defn selector
  [& path]
  {:pre [(not (empty? path))]}
  (fn [ds] (get-in ds path)))

(def person {:name "Michael Johnson"})

(def personName (selector :name))
;; usage
; (personName person)

(def personStreet (selector :address :street :name))
;; usage
; (personStreet personAddress)

;; function compositon
(defn append-a
  [s]
  (str s "a"))

(defn append-b
  [s]
  (str s "b"))

(defn append-c
  [s]
  (str s "c"))

(def append-cba (comp append-a append-b append-c))

(def request
  {:headers
   {"Authorization" "auth"
    "X-RequestFingerPrint" "fingerprint"}
   :body "body"})

(defn check-authorization
  [request]
  (let [auth-header (get-in request [:headers "Authorization"])]
    (assoc
        request
      :principal
      (if-not (nil? auth-header)
        "AUser"))))

(defn log-fingerprint
  [request]
  (let [fingerprint (get-in request [:headers "X-RequestFingerPrint"])]
    (println (str "FINGERPRINT=" fingerprint))
    request))

(defn compose-filters
  [filters]
  (reduce
   (fn [all-filters, current-filter] (comp all-filters current-filter))
  filters))

;; composing the http header functions
(def filter-chain (compose-filters [check-authorization log-fingerprint]))
;; usage
; (filter-chain request)

;; partially applied functions
(defn add-two-ints
  [int-one int-two]
  (+ int-one int-two))

(def add-forty-two (partial add-two-ints 42))
;; usage
; (add-forty-two 100)

(defn tax-for-state
  [state amount]
  (cond
   (= :ny state) (* amount 0.0645)
   (= :pa state) (* amount 0.045)))

(def ny-tax (partial tax-for-state :ny))
(def pa-tax (partial tax-for-state :pa))
;; usage
; (ny-tax 100)
; (pa-tax 100)

