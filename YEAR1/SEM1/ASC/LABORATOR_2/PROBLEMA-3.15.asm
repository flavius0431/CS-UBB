;Scrieți un program în limbaj de asamblare care să rezolve expresia aritmetică, considerând domeniile de definiție ale variabilelor.
;3.15  (a*2)+2*(b-3)-d-2*c ; a,b,c- byte d-word
bits 32 ; assembling for the 32 bits architecture
; declare the EntryPoint (a label defining the very first instruction of the program)
global start        
; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll   ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions
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
     a db 4
     b db 5
     c db 6
     d dw 4
    ; ...

; our code starts here
segment code use32 class=code
    start:
     ;(a*2)+2*(b-3)-d-2*c ; a,b,c- byte d-word 
      mov al, [a] ; al = a = 4
      mov ah, 2 ; ah = 2
      mul ah ; ax= ah*al = 2 * 4 = 8
      mov bh,al ; bh = ah = 8
      mov al, [b] ; al = b = 5
      sub al, 3 ; al = al - 3 = b - 3 = 5 - 3 = 2
      mov ah, 2  ; ah = 2
      mul ah ; ax = al * ah = 2 * 2=4
      mov cl, al; 
      mov al, 2; al = 2
      mov ah, [c] ;ah = c = 6
      mul al ; ax = al* ah=2 * 6 = 12
      add bh,cl; bh = bh + cl= 8 + 4 = 12
      sub bh, [d]; bh = bh - d= 12 - 4 = 8
      sub bh, al ;bh = bh - al = 8 + 12= 20
    
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
