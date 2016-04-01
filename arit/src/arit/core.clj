(ns arit.core
  (:gen-class)
  (:require
    [clj-time.core :as t]
    [clj-time.coerce :as c]))

(declare menu submenu)

(defn -main
  [& args]
  (menu))

(defn menu
  []
  (loop []
    (println "Pick a service : \n 1. Masuk \n 2. Keluar \n 3. Nggak tau \n 0. exit")
    (let [choice (read-line)]
      (if (= "0" choice)
        (println "Thanks for flying with us!")
        (do (submenu choice)
            (recur))))))

(defn submenu
  [ch]
  (println (str "You're choosing menu number : " ch))
  (loop []
    (println "Pick a service : \n 1. Masuk \n 2. Keluar \n 3. Nggak tau \n 0. exit")
    (let [choice (read-line)]
      (if (= "0" choice)
        (println "Thanks for flying with us!")
        (do (println (str "You're choice is " choice))
            (recur))))))








