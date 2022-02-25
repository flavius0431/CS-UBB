;Problema 10.Sa se inlocuiasca bitii 0-3 ai octetului B cu bitii 8-11 ai cuvantului A.
bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...
   
    A dw 0110100010111010b ;0110 1000 1011 1010 = 26810 = 68BAh
    B db         01001101b ;0100 1101 = 77 = 4Dh
   
; our code starts here
segment code use32 class=code
    start:
        ; ...
        ;Sa se inlocuiasca bitii 0-3 ai octetului B cu bitii 8-11 ai cuvantului A.
        
        mov ax,[A] ; ax = A =0110 1000 1011 1010b= 68BAh
        shr ax, 8  ; shiftam spre dreapta cu 8  biti ax=1011 1010b= 68h
        
        and al, 00001111b ; zerorizam bitii 4-7 al = 0000 1010b = 08h
        mov bl, [B] ; bl = 0100 1101b = 4Dh
        
        and bl, 11110000b ; zerorizam bitii 0-3 ai octetului B bl=0100 0000b = 40h
        
        or bl, al ; punem bitii 0-3 ai octetului B cu bitii 8-11 ai cuvantului A
        
   
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
