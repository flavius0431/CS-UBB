
;Modelul matematic 
;  suma(l1l2..ln)= 0, daca n=0
;                  l1+suma(l2..ln), daca l1 este atom numeric
;                   suma(l2..ln),altfel
(defun suma(l)
;forma COND–forma condițională: permite ramificarea prelucrărilor
(cond 
 ; daca lista este vida
  ((null l) 0)
  ;NUMBERP–returneazăTdacă argumentule număr
  ; daca primul eslement este atom
  ((numberp(car l)) (+ (car l)(suma(cdr l))))
  ;altfel
  (t (suma(cdr l)))
)
)                

(defun eliminDuplicate (lista)
(cond
  ((null lista) nil)
  ((equal (cdr lista) nil) (car lista))
  ((AND (not (equal (cdr lista) nil)) (not (= (car lista) (car (cdr lista))))) (cons (car lista) (eliminDuplicate (cdr lista))))
  ((AND (not (equal (cdr lista) nil)) (= (car lista) (car (dr lista)))) (eliminDuplicate (cdr lista)))
))


(print (eliminDuplicate (1 2 2 3 3 3 4)))
