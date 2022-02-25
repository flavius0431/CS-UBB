;Scrieți un program în limbaj de asamblare care să rezolve expresia aritmetică, considerând domeniile de definiție ale variabilelor.
;3.16  (a+b)/2 + (10-a/c)+b/4 ; a,b,c- byte d-word

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
     a db 6
     b db 8
     c db 3
    ; ...

; our code starts here
segment code use32 class=code
    start:
     ; (a+b)/2 + (10-a/c)+b/4
      mov al,[a] ; al = a= 6
      add al, [b]; al = al + b = a + b = 6 + 8 = 14
      mov bh, 2; bh = 2
      div bh ; ax = ah/bh= 14 / 2
      mov cl, al ; cl = al = 7
      
      mov ax, [a] ; ax=a=6
      mov bl, [c] ; bl = c = 3
      div bl ; al = ax/ bl = 6 / 3= 2
      
      add cl, bl; cl = cl+ bl = 7 + 2 = 9
      
      mov dl, 10 ; dl = 10
      sub dl, bl ; dl = dl - bl = 10 - 6 = 4
      
      mov ax, b; ax = b =8
      mov bl, 4; bl = 4
      div bl; al = ax / bl= 8 / 4 = 2
       
      add al, cl; al = al + cl
      
    
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
