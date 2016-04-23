(ns kana.core
  (:require [kana.utils :refer :all]
            [clojure.string :as cs]))

(declare menu play report)

(def records (atom (cslurp "data")))
(def problems (mapv #(do {(key %) (val %)}) (cslurp "kana")))
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
              "1" (play)
              "2" (report @records))
            (recur))))))

(defn report
  [rec]
  (let [parts (->> (partition 50 (:times rec))
                   (map #(int (/ (apply + %) 50))))
        {:keys [correct wrong]} rec]
    (doseq [p parts]
      (println p))
    (println "Total bener : " correct "Salah : " wrong "Percent : " (int (* 100 (/ correct (+ correct wrong)))))))

(defn display
  [{:keys [soal answer no] :as problem}]
  (println "Ketik exit untuk keluar")
  (println soal)
  (merge {:kunci answer :no no} (with-time)))

(defn problem
  [ctr problems]
  (let [no (rand-nth [5 6 7 8 9])
        pronos (vec (repeatedly no #(rand-int ctr)))
        pros (mapv #(problems %) pronos)
        kuncis (reduce merge pros)
        kanas (keys kuncis)]
    {:soal   (apply str (interpose " " kanas))
     :answer (apply str (interpose " " (map kuncis kanas)))
     :no no}))

(defn play []
  (loop []
    (let [{:keys [answer timing kunci no]} (display (problem ctr problems))]
      (if (= answer "exit")
        (println "Thanks for playing")
        (let [a1 (cs/split answer #" ")
              a2 (cs/split kunci #" ")
              hasil (mapv = a1 a2)
              bener (count (filter true? hasil))
              salah (- no bener)]
          (swap! records
                 #(merge % (merge-with +
                                       {:correct (:correct %) :wrong (:wrong %)}
                                       {:correct bener :wrong salah})))
          (Thread/sleep 100)
          (swap! records
                 #(merge % {:times (apply conj (:times %) (repeat no (quot timing no)))}))
          (println kunci)
          (println "")
          (recur))))))



