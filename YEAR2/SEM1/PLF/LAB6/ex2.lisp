;;2. Sa se tipareasca lista nodurilor de pe nivelul k dintr-un arbore de tipul (1).

;;(A 2 B 0 C 2 D 0 E 0)

;;De exemplu arborele
;;      A
;;     / \
;;    B   C
;;       / \
;;      D   E
;;Exemple de rulare
;;k=0=>(A)
;;k=1=>(B C)
;;k=2=>(D E)
;;k=3=>NIL



;;Modelul matematic
;; subarv_stang(l1l2l3..ln,nv,nm) = Ø , daca n=0
;;                                  Ø , daca nv > nm
;;                                  l1 U l2 U subarb_stang(l3..ln,nv+1,nm+l2),altfel
;; 
(defun subarb_stang(l nv nm)
    (cond
    ((null l) nil)
    ((> nv nm) nil)
    (t (cons (car l) (cons (cadr l) (subarb_stang (cddr l) (+ nv 1) (+ nm (cadr l))))))
    )
)

;;Modelul matematic 
;; stang(l1l2l3..ln) = subarb_stang(l3..ln,0,0)

(defun stang(l)
    (subarb_stang (cddr l) 0 0)
)

;;Modelul matematic
;; subatb_drept(l1l2l3..ln, nv, nm) = Ø , daca n=0
;;                                   l1l2..ln, daca nv > nm
;;                                   subarb_drept(l3..ln,nv+1,nm+l2),altfel

(defun subarb_drept(l nv nm)
    (cond
    ((null l) nil)
    ((> nv nm) l)
    (t (subarb_drept (cddr l) (+ nv 1) (+ nm (cadr l))))
    )
)

;;Modelul matematic 
;; drept(l1l2l3..ln) = subarb_drept(l3..ln,0,0)

(defun drept(l)
    (subarb_drept (cddr l) 0 0)
)

;; Modelul matematic
;;  nivel (l1l2l3..ln, k, niv) = Ø , daca n=0
;;                               [l1], daca k=niv
;;                              nivel((stang l),k,niv+1) U nivel((drept l), k, niv+1),altfel

(defun nivel(l k niv)
    (cond
    ((null l) nil)
    ((= k niv) (list (car l)))
    (t (append (nivel (stang l) k (+ niv 1)) (nivel (drept l) k (+ niv 1))))
    )
)

;;Modelul matematic
;; nivelP(l1l2l3..ln, k) = nivel(l1l2l3..ln,k,0)
(defun nivelP(l k)
    (nivel l k 0)
)

(print (nivelP '(A 2 B 0 C 2 D 0 E 0) 2))
;;k=1=>(B C)
;;k=2=>(D E)