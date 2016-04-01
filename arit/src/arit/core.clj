(ns arit.core
  (:gen-class)
  (:require
    [clj-time.core :as t]
    [clj-time.coerce :as c]))

(defn -main
  [& opts]
  (print "Pick your ema?")
  (let [which-one (read-line)]
    (condp = which-one
      "watson" (println "Cool!")
      "stone" (println "You're stone")
      "roberts" (println "Cute one"))))






