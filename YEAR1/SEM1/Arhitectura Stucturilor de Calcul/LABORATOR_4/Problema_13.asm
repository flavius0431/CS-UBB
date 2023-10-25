;Problema 13.Dandu-se 4 octeti, sa se obtina in AX suma numerelor 
;intregi reprezentate de bitii 4-6 ai celor 4 octeti.
bits 32 ; assembling for the 32 bits architectur;

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...01110000
    a db 10100101b ; 1010 0101b = A5h
    b db 01001101b ; 0100 1101b = 4Dh
    c db 01110100b ; 0111 0100b = 74h
    d db 10011101b ; 1001 1101b = 9Dh

; our code starts here
segment code use32 class=code
    start:
        ; ...
        mov bx, 0 ;in registru bx vom pune bitii 4-6
        mov bl, [a]; bl = a =10100101b = A5h
        and bx, 0000000001110000b; izolam bitii 4-6
        mov cl, 4
        ror bx, cl ; deplasam bitii cu 4 pozitii spre dreapta
        mov ax, bx ; mutam primul octet in ax  ; ax = bx
        
        mov bx, 0 ;in registru bx vom pune bitii 4-6
        mov bl, [b]
        and bx, 0000000001110000b; izolam bitii 4-6
        mov cl, 4
        ror bx, cl ; deplasam bitii cu 4 pozitii spre dreapta
        add ax, bx ; adunam al doilea octet ; ax=ax+bx
        
        mov bx, 0 ;in registru bx vom pune bitii 4-6
        mov bl, [c]
        and bx, 0000000001110000b; izolam bitii 4-6
        mov cl, 4
        ror bx, cl ; deplasam bitii cu 4 pozitii spre dreapta
        add ax, bx ; adunam al treile octet; ax=ax+bx
        
        mov bx, 0 ;in registru bx vom pune bitii 4-6
        mov bl, [d]
        and bx, 0000000001110000b; izolam bitii 4-6
        mov cl, 4
        ror bx, cl ; deplasam bitii cu 4 pozitii spre dreapta
        add ax, bx ; adunam al patrulea octet; ax=ax+bx
        
    
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
