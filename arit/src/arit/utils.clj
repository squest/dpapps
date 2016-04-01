(ns arit.utils
  (:require
    [clj-time.core :as t]
    [clj-time.coerce :as c]
    [clj-time.local :as l]
    [clojure.pprint :as pp]))

(defn trim [n d]
  (let [ed (Math/pow 10 d)]
    (/ (int (* ed n)) ed 1.0)))

(defn cslurp []
  (read-string (slurp "resources/data.edn")))

(defn cspit [data]
  (->> (pp/pprint data)
       with-out-str
       (spit "resources/data.edn")))

(defn now []
  (l/local-now))

(defn with-time []
  (let [t1 (c/to-long (now))
        ans (read-line)]
    {:answer ans
     :timing (quot (- (c/to-long (now)) t1) 1000)}))
