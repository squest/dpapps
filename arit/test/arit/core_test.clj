(ns arit.core-test
  (:require
    [clojure.test :refer :all]
    [arit.core :refer :all]
    [arit.utils :refer :all]
    [arit.tracker :refer :all]))

(deftest utils
  (testing "Utilities"
    (is (= (cslurp)
           (do (cspit (cslurp))
               (cslurp))))))


