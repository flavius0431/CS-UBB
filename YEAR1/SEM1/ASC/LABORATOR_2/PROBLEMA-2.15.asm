;Scrieți un program în limbaj de asamblare care să rezolve expresia aritmetică, considerând domeniile de definiție ale variabilelor.
;2.15 (a-b+c)-(d+d); a,b,c,d-Word

bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    a dw 323
    b dw 32
    c dw 12
    d dw 120

; our code starts here
segment code use32 class=code
    start:
       ;(a-b+c)-(d+d)
       mov ax,[a] ; ax= a = 323
       sub ax ,[b] ; ax = ax - b = a - b =323 - 32 = 291
       add ax, [c]; ax = ax + c = a - b + c = 323 -32 + 12=303
       mov bx, [d]; bx = d = 120
       add bx, [d]; bx = bx + d= d + d= 120 =240
       sub ax, bx ; ax = ax - bx =(a - b + c)-(d+d)= 303 - 240 = 63

       
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
