(defstruct name
  first
  (middle nil)
  last)

(setf b (make-name :first 'Barney :last 'Rubble))

(name-first b)    ; => Barney
(name-middle b)   ; => nil
(name-last b)     ; => Rubble

(name-p b)        ; => t (true)
(name-p 'Barnet)  ; => nil (name-p is testing the type of b)

(setf (name-middle b) 'Q)

(name-middle b)   ; => Q

b                 ; => [cl-struct-name Barney Q Rubble]


