;Instructiuni de comparare, salt conditionat si de ciclare. Operatii pe siruri.
;17.Se dau 2 siruri de octeti S1 si S2 de aceeasi lungime.
; Sa se construiasca sirul D astfel incat fiecare element 
;din D sa reprezinte maximul dintre elementele de pe pozitiile 
;corespunzatoare din S1 si S2.
;exemplu
;S1: 1, 3, 6, 2, 3, 7
;S2: 6, 3, 8, 1, 2, 5
;D: 6, 3, 8, 2, 3, 7

bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit , printf              ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
import printf msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...
    S1 db 1, 3, 6, 2, 3, 7    
    L1 equ $-S1 ; L1 = 6 lungimea sirului
    S2 db 6, 3, 8, 1, 2, 5
    D TIMES  L1 db 0 

; our code starts here
segment code use32 class=code
    start:
        ; ...
        mov ecx, L1 ; punem lungimea sirului in 
        mov esi, 0 ; indexul de la care incep sirurile S1,S2
        mov edi, 0 ; indexul de la care adaugam elemente in D
        jecxz Sfarsit ; Ne duce la Sfarit cand ecx==0
        RepetaSir:
        mov al, [S1 + esi] ; mutam in al elementul din S1 de pe pozitia esi
        mov ah, [S2 + esi] ; mutam in ah elementul din S2 de pe pozitia esi
        cmp al, ah ; comparam al si ah
        jle et1 ;  daca e mai mic sau egal sare la et1
             mov [D + edi], al ; altefel mutam al in D, deoarece al este mai mare decat ah
             jmp dupa ; sare la eticheta dupa
         et1:
             mov [D + esi], ah ; mutam ah in D, deoarece al este mai mare decat al
         dupa:

        inc edi ; incrementam edi
        inc esi ; incrementam esi

        loop RepetaSir
        Sfarsit:
        

        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
