(ns arit.core
  (:gen-class)
  (:require
    [arit.utils :refer :all]
    [arit.tracker :as tracker]))

(declare menu play report init)

(def records (atom nil))

(defn -main [& args]
  (println (with-time)))

(defn menu []
  (loop []
    (println "Pick a service :
    \n 1. Play
    \n 2. Report
    \n 0. exit")
    (let [choice (read-line)]
      (if (= "0" choice)
        (println "Thanks for flying with us!")
        (do (condp = choice
              "1" (play)
              "2" (report))
            (recur))))))

(defn- init []
  (reset! records (cslurp)))










