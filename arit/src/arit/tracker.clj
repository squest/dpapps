(ns arit.tracker
  (:require
    [arit.utils :refer :all]))

(defn score [data]
  (let [raw (map first data)
        correct (->> raw (filter true?) count)
        wrong (->> raw (filter false?) count)]
    ))

(defn timing [data]
  (let [raw (map second data)]
    raw))

(defn reporting [data]
  {:score (score data)
   :time  (timing data)})
