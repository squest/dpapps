(ns arit.problems
  (:require
    [arit.utils :refer :all]))

(def game
  (let [funs [(fn []
                (let [b (rand-nth (range 1 10))]
                  {:a 10 :b b :op - :bulet true}))
              (fn []
                (let [a (rand-nth (range 1 10))
                      b (rand-nth (range 1 10))]
                  {:a a :b b :op (rand-nth [+ -]) :bulet true}))
              (fn []
                (let [b (rand-nth (range 2 40 2))]
                  {:a 5 :b b :op * :bulet true}))
              (fn []
                (let [a (rand-nth (range 4 100 4))]
                  {:a a :b 4 :op / :bulet true}))
              (fn []
                (let [b (rand-nth (range 4 100 4))]
                  {:a 25 :b b :op * :bulet true}))
              (fn []
                (let [b (rand-nth (range 2 10))]
                  {:a 5 :b b :op * :bulet true}))
              (fn []
                (let [a (rand-nth (range 1 10))
                      b (rand-nth (range 1 10))]
                  {:a a :b b :op * :bulet true}))
              (fn []
                (let [a1 (rand-nth (range 1 5))
                      a2 (rand-nth (range 1 5))
                      a (read-string (str a1 a2))]
                  {:a a :b 2 :op * :bulet true}))
              (fn []
                (let [a (rand-nth (range 2 10))
                      b (rand-nth (range 2 10))]
                  {:a a :b b :op * :bulet true}))
              (fn []
                (let [a1 (rand-nth (range 1 15))
                      a2 (rand-nth (range 1 5))]
                  {:a (read-string (str a1 a2)) :b 2 :op * :bulet true}))
              (fn []
                (let [a (rand-nth (range 20 100))
                      b (rand-nth (1 10))
                      op (rand-nth [+ -])]
                  {:a a :b b :op op :bulet true}))
              (fn []
                (let [a (rand-nth (range 2 80 2))]
                  {:a a :b 25 :op * :bulet true}))]]
    {:max (dec (count funs))
    :funs funs}))

(defn play [level]
  (let [{:keys [max funs]} game
        f (if-not (<= 0 level max)
            (rand-nth funs)
            (funs level))]
    (f)))
