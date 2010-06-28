(ns com.jimbarritt.spikes.clojure.MathsTest
  (:use clojure.test)
  (:use com.jimbarritt.spikes.clojure.Maths))

(deftest test-addition
 (is (= 3 (+ 3 2))))

(deftest addTwoNumbersTest
  (is (= 4 (addTwoNumbers 2 2)))
  (is (= 7 (addTwoNumbers 3 4))))


