(ns com.jimbarritt.spikes.MathsTest
  (:require clojure.test)
  (:use com.jimbarritt.spikes.Maths))

(print (format "The result is %d" (addTwoNumbers 2 3)))

(deftest addTwoNumbers
  (is (= 4 (addTwoNumbers 2 2)))
  (is (= 7 (addTwoNumbers 3 4))))


