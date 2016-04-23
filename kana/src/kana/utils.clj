(ns kana.utils
  (:require
    [clj-time.core :as t]
    [clj-time.coerce :as c]
    [clj-time.local :as l]
    [clojure.pprint :as pp]))

(defn trim [n d]
  (let [ed (Math/pow 10 d)]
    (/ (int (* ed n)) ed 1.0)))

(def dir "resources/")

(defn cspit [fname data]
  (spit (str dir fname ".edn") data))

(defn cslurp [fname]
  (if (= fname "data")
    (if-let [data (try (slurp (str dir "data.edn")) (catch Exception e))]
      (do (println "nemu data")
          (read-string data))
      (do (cspit "data" {:correct 0 :wrong 0 :times []})
          (cslurp fname)))
    (-> (str dir fname ".edn") read-string)))

(defn now []
  (l/local-now))

(defn with-time []
  (let [t1 (c/to-long (now))
        ans (read-line)]
    {:answer ans
     :timing (quot (- (c/to-long (now)) t1) 1000)}))
