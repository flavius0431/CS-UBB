;;Se da un arbore n-ar reprezentat sub forma(radacina lista_subarb1 lista_subarb2 ... lista_subarbn).
;;Se cere sa se determine numarul total al nodurilor dispuse pe nivelurile pare

;;Model Matematic
;;   sum( x, niv) = 1, x-atom, niv=par
;;                  0,daca x=[]
;;                  ∑ suma(xi, niv +1), x lista x=x1x2...xn,x!=[]
;;                  0,altfel

(defun sum2(x niv)
 (cond 
   ((null x) 0)
   ((and (atom x) (= (mod niv 2) 0)) 1)
   ((listp x) (apply #'+ (mapcar #'(lambda(i) (sum2 i (+ niv 1))) x)))
   (t 0)
)
)


(defun sum_main (x)
  (sum2 x 0)
)

;;(print (sum_main '(A (B(C(D)(E)))(F))))

;;Sa se numere toate sublistele unei liste (inclusiv lista insasi , daca este cazul)
;; pentru care primul atom numeric (indiferent de nivel este 5)

;;Modelul Matematic 
;; numara (l)= { 0, l-atom 
;;               1+∑ numara(li), l=l1l2..ln, verifica(l)=A
;;               ∑ numara(li), l=l1l2..ln,verifica(l)=F
;;
;;  liniarizeaza(l)= {[], l este atom nenumeric
;;                    [l], daca l este atom numeric
;;                     U liniarizeaza (li), l =l1l2..ln
;;
;;   verifica(l) = A, daca n>0, l1=5, unde l1'l2'..ln'=liniarizeaza (l)/nil
;;                 F, altfe

(defun liniarizeaza (l)
  (cond
    ((numberp l) (list l))
    ((atom l) nil)
    (t(mapcan #'liniarizeaza l))
  )
)

(defun verifica(l)
  (equal (car (liniarizeaza l)) 5)
)

(defun numara(l)
 (cond
   ((atom l) 0)
   ((and (listp l) (verifica l)) (+ 1 (apply #'+ (mapcar #'numara l))))
   (t(apply #'+ (mapcar #'numara l ))) 
 )
)

;;(print (numara '( A ( B (C 5) 1 ) (A 1 5) 5) ) )


;;Modifica
;;Modelul matematic
;;   model(l)={ 2l, daca e atom numeric
;;               l, daca e atom nenumeric
;;               U model(li) l =l1l2..ln

(defun model(l)
 (cond
   ((numberp l) (* 2 l))
   ((atom l) l)
   (t (mapcar #'model l)) 
 )
)

;;(print (model '(1 (b (4) c) (d (3 (5 f))))))

; Un arbore binar se memoreaza (radacina (lista-subarbore-1)(lista-subarbore-2)).
; Sa se determine lista nodurilor de pe nivelul k din arbore. Nivelul radacinii se considera 0. 
; ex: (A (B) (C)) 0 -> (A)

; Modelul matematic
; nivel(a1...an, k ,niv) = - [] ,daca n = 0
;                          - [a1] ,daca k = niv si a = lista 
;                          - nivel(a2, k, niv+1), daca n = 2
;                          - nivel(a2, k, niv+1) U nivel(a3, k ,niv+1), daca k!=niv si n = 3
;                          //- i=2,n U nivel(ai, k, niv+1), unde a = lista si k!= niv

; a = arborele
; k = nivelul de pe care se cer nodurile
; niv = nivelul curent, initial 0

(defun nivel(arbore k niv)
(cond
 ((null arbore) nil)
 ( (= k niv) (list (cdr arbore)) )
 (t (mapcan #'(lambda (i) (nivel i k (+ 1 niv))) (cdr arbore)))
)
)

; noduriNivel(arbore k) = nivel(arbore k 0)
(defun noduriNivel(arbore k)
  (nivel arbore k 0)
)

;;(print (noduriNivel '(A (B (F (G) (H))) (C (L)) (D (M)) (E (N))) '2))


(defun inlocuieste (l e e1)
 (cond 
   ((NULL l) nil)
   ((AND (atom l) (not(equal l e)))  l)
   ((AND (atom l) (equal l e)) e1)
   (t(apply #'list(mapcar #'(lambda(i) (inlocuieste i e e1)) l)))
 )
)

;;(print (inlocuieste '(1 (2 A (3 A)) (A)) 'A 'B ))

;;EX B

(defun liniarizeaza( l)
  (cond
    ((null l) nil)
    ((numberp (car l)) (liniarizeaza (cdr l)))
    ((atom (car l)) (cons (car l) (liniarizeaza (cdr l))))
    (t (append (liniarizeaza (car l)) (liniarizeaza (cdr l))))
  )
)
;;(print (liniarizeaza'(F A 2 3 (B 1 (A D 5) C C (F)) 8 11 D (A F) F)  ))

(defun apare (l e)
  (cond
    ((null l) 0)
    ((AND (atom (car l)) (equal (car l) e)) (+ 1 (apare (cdr l) e)))
    (t (apare (cdr l ) e))
  ) 
)

;;(print (apare (liniarizeaza '(F A 2 3 (B 1 (A D 5) C C (F)) 8 11 D (A F) F)) 'F ))

(defun sterge (l e)
  (cond
    ((null l) nil)
    ((AND (atom (car l)) (equal (car l) e)) (sterge(cdr l) e))
    (T (cons (car l) (sterge (cdr l ) e)))
  ) 
)
;;(print (sterge (liniarizeaza '(F A 2 3 (B 1 (A D 5) C C (F)) 8 11 D (A F) F)) 'F ))

(defun inverseaza (l)
  (cond 
    ((null l) nil)
    (t(append (inverseaza(cdr l)) (list(car l))))
  )
)

;(print (inverseaza '(1 2 3)))

(defun main(l)
  (cond 
    ((null l) nil)
    ((= (mod (apare l (car l)) 2) 0) (cons (car l) (main( sterge l (car l)))))
    (t (main ( sterge l (car l))))
  )
)

;(print (main (liniarizeaza '(F A 2 3 (B 1 (A D 5) C C (F)) 8 11 D (A F) F)) ))


(defun mainP(l)
  (inverseaza (main ( liniarizeaza l)))
)

;;(print (mainP '(F A 2 3 (B 1 (A D 5) C C (F)) 8 11 D (A F) F) ))

(defun substituie(l e e1 niv)
  (cond
     ((and (atom l)(not (equal l e))) l)
     ((and (atom l)(equal l e)(equal (mod niv 2) 0)) e) 
     ((and (atom l)(equal l e)(equal (mod niv 2) 1)) e1) 
     (t(mapcar #'(lambda(x) (substituie x e e1 (+ niv 1))) l ))
  )
)

;(print (substituie '() 'd 'f 1))


(defun verifica( arbore x niv)
  (cond
    ((null arbore) nil)
    ((AND (equal arbore x) (equal (mod niv 2) 0 )) (list t))
    ((listp arbore)  (mapcan #'(lambda(i) (verifica i x (+ niv 1))) arbore))
    (t nil)
  )
)

;(print (car(verifica '(a(b(g))(c(d(e))(f))) 'g '-1 )))


(defun calea( a nod c)
  (cond 
    ((null a) nil)
    ((equal a nod) c)
    ((listp a) (mapcan #'(lambda(i) (calea i nod (append c (list(car a)) ))) a))
    (t nil)
  )
)

;(print (calea '(a (b (g)) (c(d(e))(f))) 'e '()) )

(defun inlocuire(a k e niv)
  (cond
    ((null a) nil)
    ((and (atom a) (equal k niv)) e)
    ((and (atom a) (not (equal k niv))) a)
    (t(mapcar #'(lambda(i) (inlocuire i k e (+ niv 1)))a))
  )
)

;(print (inlocuire '(a (b (g)) (c(d(e))(f)) ) '2 'h '-1))

(defun elimina(l k niv)
 (cond
   ((null l) nil)
   ((and (atom l) (not (equal niv k))) (list l))
   ((listp l) (list(mapcan #'(lambda(i) (elimina i k (+ niv 1))) l)))
   (t nil) 
 )
)

(print (car (elimina '(a (1 (2 b)) (c(d))) '1 '0)))
