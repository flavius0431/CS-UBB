;;Practic 

(defun setcf(nr)
  (cond 
   ((null nr) 0)
   ((< nr 10) 0)
   ((>= nr 10) (/ (- nr (mod nr 10)) 10))
  )
)


(defun produs( l  n cf cf1)
 (cond
    ((null l) 1)
    ((and (atom l) (equal cf1 0) )  (* l n) )
    ((and (atom l) (not (equal cf1 0)) ) (+ (* l n) cf1))
    (t(mapcar #'(lambda(x)(produs x n (setcf (* x n) ) cf)) l))
 )
)

(print (produs '(1 9 3 5 9 9) '2 '0  '0))
(print (produs '( 2 1 3) '2 '0  '0))
(print (produs '( 2 5 7) '3 '0  '0))
(print (produs '(4 2 8) '5 '0  '0))

