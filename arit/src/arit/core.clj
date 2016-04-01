(ns arit.core
  (:gen-class)
  (:require
    [arit.utils :refer :all]
    [arit.tracker :as tracker :refer [records]]))

(declare menu play report init)

(defn -main [& args]
  (println (with-time)))

(defn save-progress []
  (cspit @records))

(defn menu []
  (loop []
    (println "Pick a service :
    \n 1. Play
    \n 2. Report
    \n 0. exit")
    (let [choice (read-line)]
      (if (= "0" choice)
        (do (save-progress)
            (println "Thanks for flying with us!"))
        (do (condp = choice
              "1" (play records)
              "2" (tracker/reporting (:records @records)))
            (recur))))))

(defn- init []
  (reset! records (cslurp)))










