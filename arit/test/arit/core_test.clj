(ns arit.core-test
  (:require
    [clojure.test :refer :all]
    [arit.core :refer :all]
    [arit.utils :refer :all]
    [arit.tracker :refer :all]
    [arit.problems :refer :all]))

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
                   (fn? op)
                   (#{true false} bulet)))]
      (is (= true
             (every? map? (:funs game))))
      (is (= true
             (every? f (:funs game)))))))


