(ns arit.tracker
  (:require
    [arit.utils :refer :all]))

(def records (atom (cslurp)))

(defn score [data]
  (let [raw (->> data reverse (take 100) (map first))
        correct (->> raw (filter true?) count)
        wrong (->> raw (filter false?) count)]
    (long (* 100 (/ correct (+ correct wrong))))))

(defn timing [data]
  (let [raw (->> (map second data)
                 (partition-all 50))
        avg (fn [col]
              (let [c (count col)]
                (trim (/ (apply + col) c) 2)))]
    (mapv avg raw)))

(defn reporting [data]
  {:score (score data)
   :time  (timing data)})
