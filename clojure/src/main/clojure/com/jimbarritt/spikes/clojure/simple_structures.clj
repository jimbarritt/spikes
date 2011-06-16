(ns simplestructures)

(defn increment [x]
  (+ x 1))

(defn print-number [i]
  (print i " "))

(defn count-to [max]
  (println)
  (loop [i 1]
    (if (> i max)
      i
      (recur (do
               (print-number i)
               (increment i)))))
  (println))

(count-to 20)


