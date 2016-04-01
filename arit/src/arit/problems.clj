(ns arit.problems
  (:require
    [arit.utils :refer :all]))

(def game
  {:max 0
   :funs [(fn []
            (let [b (range 1 10)]
              {:a 10 :b b :op - :bulet true}))]})

(defn play [level]
  (let [{:keys [max funs]} game
        f (if (> level max)
            (rand-nth funs)
            (funs level))]
    (f)))
