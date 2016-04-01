(ns arit.core
  (:gen-class)
  (:require
    [clj-time.core :as t]
    [clj-time.coerce :as c]))

(defn -main
  [& args]
  (print "Choose your Ema .. ")
  (let [with-ema (read-line)]
    (condp = with-ema
      "watson" (println "door")
      "roberts" (println "yes")
      "stone" (println "stoned"))))








