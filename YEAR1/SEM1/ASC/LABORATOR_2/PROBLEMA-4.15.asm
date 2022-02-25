;Scrieți un program în limbaj de asamblare care să rezolve expresia aritmetică, considerând domeniile de definiție ale variabilelor.
;4.15  f*(e-2)/[3*(d-5)]) :a,b,c,d-byte, e,f,g,h-word

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
     d db 6
     f dw 3
     e dw 8
    ; ...

; our code starts here
segment code use32 class=code
    start:
     ; f*(e-2)/[3*(d-5)]) :a,b,c,d-byte, e,f,g,h-word
       mov dx, [e]; dx = e = 8
       sub dx, 2 ; dx = dx -2 = e - 2= 6
       
       mov ax, [f]; ax = f = 3
       mul dx ; dx:ax = ax*dx = f *(e-2)=3*6=18
       
       
       mov bx, [d] ; bx = d
       sub bx, 5 ; bx=bx-5= d-5 =6-5=1
       
       
       
       
    
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program