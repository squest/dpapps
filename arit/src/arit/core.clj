(ns arit.core
  (:gen-class)
  (:require
    [arit.utils :refer :all]
    [arit.tracker :as tracker :refer [records]]
    [arit.problems :as pro]))

(declare menu play init)

(defn -main [& args]
  (println "You can start now!")
  (println (with-time)))

(defn save-progress []
  (cspit @records))

(def state (atom {:level (:level @records)
                  :warning 0
                  :demote 0
                  :promote 0}))

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
              "1" (play state records)
              "2" (tracker/reporting (:records @records)))
            (recur))))))

(defn- init []
  (reset! records (cslurp)))

(def ops {- " - " + " + " / " / " * " x "})

(defn display
  [{:keys [a b op]}]
  (println (str "Berapa " a (ops op) b))
  (with-time))

(defn play [state records]
  (let [{:keys [level warning demote promote]} @state
        {:keys [records]} @records]
    (loop []
      (let [{:keys [a b d op bulet] :as data} (pro/play level)
            {:keys [answer timing]} (display data)]
        (cond (= answer "exit")
              (println "がんばて!!")
              bulet
              (cond (== answer (op a b)))))))










