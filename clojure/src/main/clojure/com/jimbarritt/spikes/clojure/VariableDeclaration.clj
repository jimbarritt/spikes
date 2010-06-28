(ns com.jimbarritt.spikes.clojure.StringManipulation)

(def someString "\nhello")
(println someString)

(def someNumber 34)
(println someNumber)

(def someArray [267, 3879, 490, 689])
(println someArray)
(println (format "Second item in the array: %s" (.get someArray 1)))
(println "nth item for (1) in the array: " (.nth someArray 1))
(println "Number of items in the array: " (.count someArray))