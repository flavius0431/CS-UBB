;Scrieți un program în limbaj de asamblare care să rezolve expresia aritmetică, considerând domeniile de definiție ale variabilelor.
;1.16 a+13-c+d-7+b ; a,b,c,d-Byte

bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    a db 12
    b db 33
    c db 15
    d db 5

; our code starts here
segment code use32 class=code
    start:
       ;a+13-c+d-7+b 
       mov al,[a] ;al = a = 12
       add al,13 ; al = al + 13 = a + 13 = 12 + 13 = 25
       sub al, [c] ; al = al - c = a + 13 - c= 12 + 13 - 15 =10
       add al, [d] ; al = al + d =  a + 13 - c  + d =12 + 13 - 15 +5 =15
       sub al, 7 ; al = al - 7 =  (a + 13 - c  + d) - 7= 12 + 13 - 15 +5 - 7= 8
       add al, [b]; al = al + b =(a + 13 - c  + d - 7)+b = 12 + 13 - 15 +5 - 7 +33= 41
       
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
