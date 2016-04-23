(ns kana.core
  (:require [kana.utils :refer :all]))

(declare menu play report)

(def records (atom (cslurp "data")))
(def problems (cslurp "kana"))
(def ctr (count problems))

(defn -main [& args]
  (menu))

(defn menu []
  (loop []
    (println "Pick a service (1) play or (2) report (0) exit : ")
    (let [choice (read-line)]
      (if (= "0" choice)
        (do (cspit "data" @records)
            (println "Thank you for flying with us"))
        (do (condp = choice
              "1" (play records)
              "2" (report @records))
            (recur))))))

(defn report
  [rec]
  (let [parts (->> (partition 50 rec)
                   (map #(int (/ (apply + %) 50))))]
    (doseq [p parts]
      (println p))))

(defn display
  [{:keys [soal answer no] :as problem}]
  (println "Ketik exit untuk keluar")
  (println soal)
  (merge {:kunci answer :no no} (with-time)))

(defn problem
  [ctr problems]
  (let [no (rand-nth [5 6 7 8 9])
        pronos (vec (repeatedly no #(rand-int ctr)))
        pros (mapv #(% pronos) problems)
        kuncis (reduce merge pros)
        kanas (keys kuncis)]
    {:soal   (apply str (interpose " " kanas))
     :answer (apply str (interpose " " (map kuncis kanas)))
     :no no}))

(defn play [rec]
  (loop []
    (let [{:keys [answer timing]} (display (problem ctr problems))])))



