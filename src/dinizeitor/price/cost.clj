(ns dinizeitor.price.cost
  (:require [clojure.data.csv :as csv]
            [clojure.java.io :as io]))

(def financial-data "C:\\Users\\th129\\Desktop\\clojure\\dinizeitor\\src\\dinizeitor\\price\\FL_insurance_sample.csv")

(defn read-csv[data]
  (with-open [reader (io/reader data)]
  (doall
    (csv/read-csv reader))))

(defn csv-data->maps [csv-data]
  (map zipmap
       (->> (first csv-data) ;; First row is the header
            (map keyword) ;; Drop if you want string keys instead
            repeat)
       (rest csv-data)))

(csv-data->maps (read-csv financial-data))