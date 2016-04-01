(ns arit.core-test
  (:require
    [clojure.test :refer :all]
    [arit.core :refer :all]
    [arit.utils :refer :all]
    [arit.tracker :refer :all]
    [arit.problems :as pro]))

(deftest utils
  (testing "Utilities"
    (is (= (cslurp)
           (do (cspit (cslurp))
               (cslurp))))))

(deftest problems
  (testing "Generator"
    (let [f (fn [{:keys [a b op bulet]}]
              (and (number? a)
                   (number? b)
                   (fn? op)))
          maxi (inc (:max pro/game))]
      (is (= (zipmap (range maxi) (repeat true))
             (zipmap (range maxi) (map map? (:funs pro/game)))))
      (is (= (zipmap (range maxi) (repeat true))
             (zipmap (range maxi) (map f (:funs pro/game))))))))


