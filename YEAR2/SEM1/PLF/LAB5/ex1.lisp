;;Prblema1
;;a)Sa se insereze intr-o lista liniara un atom a dat dupa al 2-lea, al 4-lea, al 6-lea,....element.
;;Modelul matematic
;;   insereaza(l1l2..ln,pozC,pozI,el)=-Ø, daca n=0
;;                                     -l1 U el U insereaza(l2l3..ln,pozC+1,pozI*2,el), daca pozC = pozI, n>=1
;;                                     -l1 U insereaza(l2l3..ln,pozC+1,pozI,el), daca pozC!=pozI, n>=1

(defun insereaza( l pozC pozI el)
 (cond
   ((null l) nil)
   ((not(= pozC pozI)) (cons (car l) (insereaza (cdr l) (+ pozC 1) pozI el)))
   (t (cons (car l) (cons el (insereaza (cdr l) (+ pozC 1) (+ pozI 2) el))))
 )
)


(defun insereazaP (l el)
   (insereaza l 1 2 el)
)

;;(print (insereazaP '(1 2 3 4 5) 7))

;;b)Definiti o functie care obtine dintr-o lista data lista tuturor atomilor care apar, pe orice nivel, dar in ordine inversa. De exemplu:(((A B) C) (D E)) --> (E D C B A)
;;Modelul matematic 
;;  inverseaza (l1l2..ln)=-Ø, daca n=0
;;                        -inverseaza(l2l3..ln)Ul1,altfel
(defun inverseaza (l)
 (cond
   ((null l) nil)
   (t(append (inverseaza (cdr l)) (list(car l))))
 )
)
;;(print (inverseaza '(A B C D)))

;;Modelul matematic 
;;  listaliniarainv(l1l2l3..ln)=-Ø, daca n=0
;;                           - [l1] , daca l1 = atom
;;                           - listaliniarainv(l2l3..ln) U listaliniarainv(l1) , daca l1!=atom

(defun listaliniarainv(l)
 (cond
    ((null l) nil)
    ((atom l) (list l))
    (t(append (listaliniarainv(cdr l)) (listaliniarainv(car l)) ))
 )
)
;;(print (listaliniarainv '(((A (X R) B) C) (D E))))
;;c)Definiti o functie care intoarce cel mai mare divizor comun al numerelor dintr-o lista neliniara.
;;Modelul Matematic
;;  cmmdc (a b r) = - r, daca a=b
;;                  - r=a-b, a >b
;;                  - r=b-a,a < b



(defun cmmdc( a b)
 (cond
   ((= a b) a)
   ((> a b) (cmmdc (- a b) b ))
   ((< a b) (cmmdc a (- b a) ))
 )
)
;;(print (cmmdc '12 '16))

(defun cmmdcl(l d)
 (cond
   ((null l) d)
   (t(cmmdcl (cdr l) (cmmdc d (car l))))
 )
)

(defun cmmdclP( l)
  (cmmdcl (cdr l) (car l)) 
)

(print (cmmdclP '( 60  15 30 90)))

;;d)Sa se scrie o functie care determina numarul de aparitii ale unui atom dat intr-o lista neliniara.
;;Modelul matematic
;;     nraparitii(l1l2..ln,el)= -0, daca n=0
;;                              -1+nraparitii(l2..ln,el),l1=atom daca l1=el 
;;                              -nraparitii(l2..ln,el),l1=atom daca l1!=el 
;;                              -nraparitii(l1,el)+nraparitii(l2l3..ln,el), daca l1 nu este atom   

(defun nraparitii (l el)
 (cond 
   ((null l) 0)
   ((AND (atom (car l)) (= (car l) el)) (+ 1 (nraparitii (cdr l ) el)))
   ((AND (atom (car l)) (not(= (car l) el))) (nraparitii (cdr l ) el))
   ((listp (car l)) (+ (nraparitii (car l) el) (nraparitii (cdr l) el)))
 )
)

;;(print (nraparitii '(1 2 (1 2) 4 5 (1 (4(8(1))))) 2))
