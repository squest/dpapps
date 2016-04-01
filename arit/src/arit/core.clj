(ns arit.core
  (:gen-class)
  (:require
    [arit.utils :refer :all]
    [arit.tracker :as tracker :refer [records]]
    [arit.problems :as pro]))

(declare menu play init)

(defn -main [& args]
  (menu))

(defn save-progress []
  (cspit @records))

(def state (atom {:level (:level @records)
                  :wrongs 0
                  :corrects 0}))

(def maxi (:max pro/game))

(defn menu []
  (loop []
    (println "Pick a service (ketik angkanya aja):
    1. Play
    2. Report
    0. exit")
    (let [choice (read-line)]
      (if (= "0" choice)
        (do (save-progress)
            (println "Thanks for flying with us!"))
        (do (condp = choice
              "1" (play state records)
              "2" (println (tracker/reporting (:records @records))))
            (recur))))))

(defn- init []
  (reset! records (cslurp)))

(def ops {- " - " + " + " / " / " * " x "})

(defn display
  [{:keys [a b op bulet d]}]
  (println "Ketik exit untuk keluar bdw")
  (when-not bulet
    (println "Kalo hasilnya koma-komaan, buletin sampe" d "di belakang koma, komanya pake titik"))
  (println (str "Berapa " a (ops op) b))
  (with-time))

(defn play [state records]
  (loop []
    (let [{:keys [level wrongs corrects]} @state
          {:keys [a b d op bulet] :as data} (pro/play level)
          {:keys [answer timing]} (display data)
          drecords (:records @records)]
      (if (= answer "exit")
        (println "がんばて!!")
        (if bulet
          (if (== (read-string answer) (op a b))
            (if (== 9 corrects)
              (do (reset! state {:level    (if (== level (inc maxi))
                                             level
                                             (inc level))
                                 :wrongs   0
                                 :corrects 0})
                  (reset! records {:level   (if (== level (inc maxi))
                                              level
                                              (inc level))
                                   :records (conj drecords [true timing])})
                  (println "BENER!!")
                  (recur))
              (do (reset! state {:level    level
                                 :wrongs   0
                                 :corrects (inc corrects)})
                  (reset! records {:level   level
                                   :records (conj drecords [true timing])})
                  (println "BENER!!")
                  (recur)))
            (if (== 2 wrongs)
              (do (reset! state {:level    (dec level)
                                 :wrongs   0
                                 :corrects 0})
                  (reset! records {:level   (dec level)
                                   :records (conj drecords [false timing])})
                  (println "SALAH!!!!!!")
                  (println "Yang bener " (op a b))
                  (recur))
              (do (reset! state {:level    level
                                 :wrongs   (inc wrongs)
                                 :corrects 0})
                  (reset! records {:level   level
                                   :records (conj drecords [false timing])})
                  (println "SALAH!!!!!!!")
                  (println "Yang bener " (op a b))
                  (recur))))
          (if (== (read-string answer) (trim (op a b) d))
            (if (== 9 corrects)
              (do (reset! state {:level (if (== level (inc maxi))
                                          level
                                          (inc level))
                                 :wrongs   0
                                 :corrects 0})
                  (reset! records {:level (if (== level (inc maxi))
                                            level
                                            (inc level))
                                   :records (conj drecords [true timing])})
                  (println "BENER!!")
                  (recur))
              (do (reset! state {:level    level
                                 :wrongs   0
                                 :corrects (inc corrects)})
                  (reset! records {:level   level
                                   :records (conj drecords [true timing])})
                  (println "BENER!!")
                  (recur)))
            (if (== 2 wrongs)
              (do (reset! state {:level    (dec level)
                                 :wrongs   0
                                 :corrects 0})
                  (reset! records {:level   (dec level)
                                   :records (conj drecords [false timing])})
                  (println "SALAH!!!!!!!!")
                  (println "Yang bener " (trim (op a b) d))
                  (recur))
              (do (reset! state {:level    level
                                 :wrongs   (inc wrongs)
                                 :corrects 0})
                  (reset! records {:level   level
                                   :records (conj drecords [false timing])})
                  (println "SALAH!!!!!!!!")
                  (println "Yang bener " (trim (op a b) d))
                  (recur)))))))))










