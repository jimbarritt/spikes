(if t "true" "false") ; => "true"

(when t "hello") ; => "hello"

(unless nil "something was false") ; => "something was false"

(cond (t "hello")) ; => "hello"

(cond (nil "not going to see this")
      (nil "or this")
      (t   "but will see this"))     ; => "but will see this"

(cond ((not nil) "this will print")) ; => "this will pring"

