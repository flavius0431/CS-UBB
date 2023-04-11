;;Exercitiul 14
;;Definiti o functie care da adancimea unui arbore n-ar reprezentat sub forma :
;;(radacina lista_noduri_subarb1 lista_noduri_subarb2...lista_noduri_subarbn)
;;Ex: adancimea arborelui (a (b (c)) (d) (e (f))) este 3
;;         a
;;       / | \
;;      b  d  e
;;     /     /
;;    c     f 

;;Modelul matematic
;; adancime (l)= -  0 , daca l este lista vida
;;               -  1, daca l este lista liniara
;;               - 1+ ∑ adancime(li) ,l1l2..ln = l,altfel


(defun adancime_arbore(l) 
  (cond 
     ((null l) 0)
     ((atom l) 0)  
     (t(+ 1(apply #'max(mapcar #'adancime_arbore l))))
  )
)

(print (adancime_arbore '(a (b (c)) (d) (e (f))) ))
;;         a
;;       / | \
;;      b  d  e
;;     /     /
;;    c     f =>3
(print (adancime_arbore '(a (b) (d) (e)) ))
;;         a
;;       / | \
;;      b  d  e=>2
(print (adancime_arbore '(a (b (c)(g)) (d) (e (f(h)))) ))
;;          a
;;       /  |  \
;;      b   d   e
;;     /\      /
;;    c  g    f 
;;            /
;;            h =>4
(print (adancime_arbore '(a (b (c(f(h)))) (d) (z) (e (f))) ))
;;         a
;;       / | \
;;      b  d  e
;;     /     /
;;    c     f  ....=>5
(print (adancime_arbore '(a )))
;;         a   =>1

