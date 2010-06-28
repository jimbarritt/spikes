(ns com.jimbarritt.spikes.MathsTest
  (:use clojure.test)
  (:use com.jimbarritt.spikes.Maths))

(println (format "The result is %d" (addTwoNumbers 2 3)))


(deftest test-addition
(is (= 3 (+ 3 2))))

(deftest addTwoNumbersTest
  (is (= 4 (addTwoNumbers 2 2)))
  (is (= 7 (addTwoNumbers 3 4))))


