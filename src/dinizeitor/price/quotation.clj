(ns dinizeitor.price.quotation
  (:require [clj-http.client :as client]
            [cheshire.core :refer :all]))


(defn get-quoutes []
  (-> (client/get "https://api.hgbrasil.com/finance" {:accept :json})
      :body
      (parse-string true)))