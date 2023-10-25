;;15.a)Sa se scrie o functie care intoarce reuniunea a doua multimi.
                     
;; Modelul matematic
;; reuniunea(a1a2...an, b1b2...bm) =
;;           - [], daca n = 0 si m = 0
;;           - [a1a2...an] , daca m = 0 si n != 0
;;           - [b1b2...bm] , daca n = 0 si m != 0
;;           - a1 U reuniunea(a2...an,b1...bm),
;;             daca n > 0 si m > 0 si a1 < b1
;;           - b1 U reuniunea(a1...an,b2...bm),
;;             daca n > 0 si m > 0 si b1 <= a1
;; a- prima multime
;; b- a doua multime
;;reunim cele doua liste
(defun reuniunea(a b)
(cond
 ((AND (null a) (null b)) nil)
 ((AND (null b) (not (null a))) a)
 ((AND (null a) (not (null b))) b)
 ((< (car a) (car b)) (cons (car a) (reuniunea (cdr a) b)))
 ((<= (car b) (car a)) (cons (car b) (reuniunea a (cdr b))))
)
)

;;(print (reuniunea'(7 6 1) '(1 2 3 8 5 6 )))
;; => (1 1 2 3 5 6 6 7 8)

;;Modelul matematic
;; eliminDuplicate(l1...ln) =
;;            - [] , daca n = 0
;;            - [l1] , daca n = 1
;;            - l1 U eliminDuplicate(l2...ln), daca n > 1 si l1 != l2
;;            - eliminDuplicate(l2...ln), daca n > 1 si l1 = l2
;; lista- lista din care eliminam dublicatele
(defun eliminDuplicate(lista)
(cond
 ((null lista) nil)
 ((equal (cdr lista) nil) lista)
 ((AND (not (equal (cdr lista) nil)) (not (= (car lista) (car (cdr lista))))) (cons (car lista) (eliminDuplicate (cdr lista))))
 ((AND (not (equal (cdr lista) nil)) (= (car lista) (car (cdr lista)))) (eliminDuplicate (cdr lista)))
)
)

;;(print (eliminDuplicate '(1 2 2 3 3 3 4 4 55))) => (1 2 3 4 55) 

;;Modelul matematic
;; reuniuneaPrincipala(a1...an,b1...bm) = - eliminDuplicate(reuniunea(a1...an,b1...bm))

(defun reuniuneaPrincipala(a b)
 (eliminDuplicate (reuniunea a b))
)

(print (reuniuneaPrincipala '( 11 6 7 ) '(1 2 3  9 5 6 8 )))
;;=> (1 2 3 5 6 7 8 9 11) 

;;b)Sa  se  construiasca  o  functie  care  intoarce  produsul  atomilor  numerici dintr-o lista, de la orice nivel.

;;Modelul matematic
;;    produsNivel(l1l2l3..ln) = 1, daca n=0
;;                              l1*produsNivel(l2l3..ln), daca l1 =atom numeric, n>=1
;;                              produsNivel(l1)*produsNivel(l2l3..ln), daca l1 = lista,n>=1
;;                              produsNivel(l2l3..ln), daca l1 =atom , n>=1
;; lista= lista la care aflam produsul atomulor numerici din orice nivel
(defun produsNivel (lista)
  (cond
       ((null lista) 1 )
       ((numberp (car lista)) (* (car lista) (produsNivel (cdr lista))))
       ((listp (car lista)) (* (produsNivel (car lista)) (produsNivel (cdr lista))))
       (t(produsNivel (cdr lista)))
  )
)

;;(print (produsNivel '(((1 3) 6) 3 ((((2))))a)))
;;=>108

;;c)Definiti o functie care sorteaza cu pastrarea dublurilor o lista liniara.


;;Modelul matematic
;;  insereazaSortat(l1l2..ln, el)= [el], daca n=0
;;                                 ell1..ln, daca el<=l1, n>=1
;;                                 l1 U insereazaSortat(l2l3..ln,el), daca el > l1, n>=1
;; lista - lista 
;; el- element, pe care il punem in lista in pozitia dorita
(defun insereazaSortat (lista el)
  (cond
     ((null lista ) (list el))
     ((<= el (car lista)) (cons el lista))
     (t (cons (car lista) (insereazaSortat (cdr lista) el)))
  )
)

;;(print (insereazaSortat '(2 5 6) '3))
;;=>(2 3 5 6)
;;Modelul matematic
;;  sorteaza(l1l2l3..ln) = [], daca n=0
;;                         insereazaSortat( sorteaza(l2l3..ln), l1))
;;
(defun sorteaza (lista)
  (cond
     ((null lista) nil)
     (t (insereazaSortat (sorteaza (cdr lista)) (car lista)))
  )
)

;;(print (sorteaza '(1 6 8 2 3 0)))
;;=>(0 1 2 3 6 8) 

;;d)Definiti  o  functie  care construiește  o  listă  cu  pozițiile  elementului minim dintr-o listă liniară numerică.

;;Modelul matematic 
;;  minim(l1l2..ln, min) = min, daca n=0
;;                        minim(l2l3..ln,l1), daca l1< min
;;                        minim(l2l3..ln, min), daca l1>= min 
;; lista - lista din care aflam minimul
;; min- minimul listei
(defun minim (lista min)
  (cond
     ((null lista) min)
     ((< (car lista) min) (minim (cdr lista) (car lista)))
     (t (minim (cdr lista) min)) 
  )
)

;;(print (minim '(2 4 5 8 9 1) 10))
;;=>1

;;Modelul matematic 
;;  pozitii(l1l2..ln,min, poz)= [],n=0
;;                             poz U pozitii(l2l3..ln,min, poz+1), daca l1 = min
;;                             pozitii(l2l3..ln, min, poz+1), daca l1!=min
;; lista -lista din care aflam pozitiile minime
;; min- minimul din lista
;; poz- pozitiile fiecarui element din lista
(defun pozitii (lista min poz)
  (cond
     ((null lista) nil)
     ((= (car lista) min) (cons poz (pozitii (cdr lista) min (+ poz 1))))
     (t (pozitii (cdr lista) min (+ poz 1)))
  )
)                

;;Modelul matematic 
;;  pozitiiPrincipala(l1l2..ln)= pozitii(l1l2..ln, minim(l1..ln),1)

(defun pozitiiPrincipala (lista)
  (pozitii lista (minim lista (car lista)) 1)
)

;;(print (pozitiiPrincipala '(7 1 9 1 6 1)))
;;=>(2 4 6)