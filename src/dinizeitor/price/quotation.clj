(ns dinizeitor.price.quotation
  (:require [clj-http.client :as client]
            [cheshire.core :refer :all]
            [clojurewerkz.money.currencies :as cu]
            [clojurewerkz.money.amounts :refer [amount-of]]
            [clojurewerkz.money.format :as money]))

(import java.util.Locale)


(money/format (amount-of cu/BRL 1.02 ) (Locale. "pt" "BR"))

(defn get-quoutes []
  (-> (client/get "https://api.hgbrasil.com/finance" {:accept :json})
      :body
      (parse-string true)))


(def dollar-status (get-in (get-quoutes) [:results :currencies :USD]))

(defn round2
  [precision d]
  (let [factor (Math/pow 10 precision)]
    (/ (Math/round (* d factor)) factor)))

;; ((partial round2 2) 4.885555555555) => 4.89

(defn converter-coin [{:keys [buy] :as dollar-status} value coin]
  (cond
    (= (clojure.string/upper-case coin) "BRL") (/ buy value)
    (= (clojure.string/upper-case coin) "USD") (* value buy)))

