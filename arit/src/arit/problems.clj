(ns arit.problems
  (:require
    [arit.utils :refer :all]))

(def game
  {:max  2
   :funs [(fn []
            (let [b (rand-nth (range 1 10))]
              {:a 10 :b b :op - :bulet true}))
          (fn []
            (let [a (rand-nth (range 1 10))
                  b (rand-nth (range 1 10))]
              {:a a :b b :op (rand-nth [+ -]) :bulet true}))
          (fn []
            (let [b (rand-nth (range 2 40 2))]
              {:a 5 :b b :op * :bulet true}))]})

(defn play [level]
  (let [{:keys [max funs]} game
        f (if-not (<= 0 level max)
            (rand-nth funs)
            (funs level))]
    (f)))
