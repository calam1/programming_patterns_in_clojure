(ns oo-patterns.command)

;; command pattern cash register as example - very basic

(defn make-cash-register
  []
  (let [register (atom 0)]
    (set-validator! register (fn [new-total] (>= new-total 0)))
    register))

(defn add-cash
  [register to-add]
  (swap! register + to-add))

(defn reset
  [register]
  (swap! register (fn [oldval] 0)))

(defn make-purchase
  [register amount]
  (fn []
  (println (str "Purchase in amount: " amount))
  (add-cash register amount)))

(def purchases (atom []))
(defn execute-purchase
  [purchase]
  (swap! purchases conj purchase)
  (purchase))

;; usage
(def register (make-cash-register))
(add-cash register 100)
(def purchase-1 (make-purchase register 100))
(def purchase-2 (make-purchase register 50))

; we execute this way vs (purchase-1) so that we save the state in
; purchases atom
(execute-purchase purchase-1)
(execute-purchase purchase-2)

; running through the purchases atom we rerun the purchase
(doseq [purchase @purchases] (purchase))
