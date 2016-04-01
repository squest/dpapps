(ns arit.core)

(defn -main
  [& opts]
  (print "Pick your ema?")
  (let [which-one (read-line)]
    (condp = which-one
      "watson" (println "Cool!")
      "stone" (println "You're stone")
      "roberts" (println "Cute one"))))
