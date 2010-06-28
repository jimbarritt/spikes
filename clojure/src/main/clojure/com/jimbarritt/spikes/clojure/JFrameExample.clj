(ns com.jimbarritt.spikes.JFrameExample
  (:require [clojure.contrib.str-utils :as su])
  (:use [clojure.contrib.math :only (gcd, sqrt)])
  (:import (java.text NumberFormat) (javax.swing JFrame JLabel)))

(println (loaded-libs))
(println (su/str-join "$" [1 2 3])) ; -> 1$2$3
(println (gcd 27 72)) ; -> 9
(println (sqrt 5)) ; -> 2.236
(println (.format (NumberFormat/getInstance) Math/PI)) ; -> 3.142

; See the screenshot that follows this code.
(doto (JFrame. "Hello")
  (.add (JLabel. "Hello, World!"))
  (.pack)
  (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE)
  (.setVisible true))
