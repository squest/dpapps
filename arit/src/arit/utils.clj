(ns arit.utils
  (:require
    [clj-time.core :as t]
    [clj-time.coerce :as c]
    [clj-time.local :as l]
    [clojure.pprint :as pp]))

(defn trim [n d]
  (let [ed (Math/pow 10 d)]
    (/ (int (* ed n)) ed 1.0)))

(defn cspit [data]
  (spit "data.edn" data))

(defn cslurp []
  (if-let [data (try (slurp "data.edn") (catch Exception e))]
    (do (println "ga nemu data")
        (read-string data))
    (do (cspit {:level 0 :records [[true 10]]})
        (cslurp))))

(defn now []
  (l/local-now))

(defn with-time []
  (let [t1 (c/to-long (now))
        ans (read-line)]
    {:answer ans
     :timing (quot (- (c/to-long (now)) t1) 1000)}))
