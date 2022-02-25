;Scrieți un program în limbaj de asamblare care să rezolve expresia aritmetică, considerând domeniile de definiție ale variabilelor.
;1.15 a-b-d+2+c+(10-b) ; a,b,c,d-Byte

bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    a db 52
    b db 6
    c db 23
    d db 17

; our code starts here
segment code use32 class=code
    start:
       ;a-b-d+2+c+(10-b) 
      mov ah,[a] ; ah = a = 52
      sub ah,[b] ; ah = ah - b = a - b = 52-6= 48
      sub ah,[d] ; ah = ah - d = a - b - d = 52-6-17= 31
      add ah, 2  ; ah = ah +2 = a - b - d + 2 = 52-6-17+2=33
      add ah,[c] ; ah = ah + c = a - b - d + 2 + c= 52-6-17+2+23=56
      mov al, 10 ; al = 10
      sub al,[b] ; al = al - b = 10 - b = 10 - 6 = 4
      add ah,al  ; ah = ah + al = a-b-d+2+c+(10-b)= 52-6-17+2+23+(10-6)= 60
      
    
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
