;; A demonstration of building a simple language generator in lisp

(setf language ((Nouns -> Elephant Crocodile Tiger))
		(Verbs -> Kicks Throws Hugs))


(defun expr-lhs (phrase)
  (first phrase)
)

(defun expr-rhs (phrase)
  (rest (rest phrase))
)




(expr-lhs '(Nouns -> Elephant Crocodile Tiger))
(expr-rhs '(Nouns -> Elephant Crocodile Tiger))

