(ns com.jimbarritt.spikes.clojure.RunAllTests
  (:require [clojure.test :as t])
  (:use com.jimbarritt.spikes.clojure.MathsTest)
  (:gen-class))

(defn run
  "Runs all defined tests"
  []
  (println "Loading tests...")
  (apply t/run-tests test-namespaces))

(run)