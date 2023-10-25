;Scrieți un program în limbaj de asamblare care să rezolve expresia aritmetică, considerând domeniile de definiție ale variabilelor.
;4.16  a*a-(e+f) :a,b,c,d-byte, e,f,g,h-word

bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
     a db 7
     f dw 20
     e dw 23
    ; ...

; our code starts here
segment code use32 class=code
    start:
     ; a*a-(e+f) :a,b,c,d-byte, e,f,g,h-word
       mov bl, [a] ; bl = a = 7
       mov al, [a] ; al = a = 7
       mul bl ; ax = al * bl
       
       mov bx, e; bx = e = 23
       add bx , f; bx = bx+ f = 23 + 20= 43
       
       sub ax,bx ; ax = ax - bx = 49 - 43 = 6
       
    
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
