;;EXERCITIUL 4
;;Modelul Matematic
;;   suma(x) - x , daca x este atom numeric
;;           -  0, daca x este atom nenumeric      
;;           - (i=1..n)  ∑ suma(xi), daca x este lista
;;
  

(defun suma(x)
  (cond
    ((numberp x) x)
    ((listp x) (apply '+(mapcar #'suma x)))
    (t 0)
  )
)

(print (suma '(1 (2) 2 (((2))) 3)))