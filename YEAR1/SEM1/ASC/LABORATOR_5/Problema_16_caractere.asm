;Instructiuni de comparare, salt conditionat si de ciclare. Operatii pe siruri.
;Se dau doua siruri de caractere S1 si S2. Sa se construiasca sirul D prin 
;concatenarea elementelor de pe pozitiile impare din S2 cu elementele de pe pozitiile pare din S1.
;exemplu:
;S1: 'a', 'b', 'c', 'b', 'e', 'f'
;S2: '1', '2', '3', '4', '5'
;D: '1', '3', '5', 'b', 'b', 'f'

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
    S1 db 'a', 'b', 'c', 'b', 'e', 'f'
    ;      61   62   63   62   65   66 
    L1 equ $-S1 ; L1 = 6
    S2 db '1', '2', '3', '4', '5'
    ;      31  32   33    34   35
    L2 equ $-S2 ; L2 = 5
    
    D TIMES  L2+L1 db 0

; our code starts here
segment code use32 class=code
    start:
        ; ...
        mov ebx, L2 ; ecx = 5
        mov ecx, L2 ; ecx = 5
        mov esi, 0 ; indicele pentru S2
        mov edi, 0 ; indicele pentru D
        
        
        RepetaSirS2:
             mov al, [S2+ esi] ; mutam in al din S2, pe pozitia esi
             mov [D+ edi], al ; mutam elementul din al in D, pe pozitia edi
             inc edi ; incrementam edi
             add esi, 2 ;mutam esi cu 2 pozitii pentru a lua doar caracterele de pe pozitii impare
             cmp esi, ebx
             jnge final
                 mov ecx, 1
             final:
        loop RepetaSirS2
        
        mov ebx, L1 ; ecx = 6
        mov ecx, L1 ; ecx = 6
        mov esi, 1 ; indicele pentru S1
        
        RepetaSir1:
             mov al, [S1+ esi] ; mutam in al din S1, pe pozitia esi
             mov [D+ edi] , al ; mutam elementul din al in D, pe pozitia edi
             inc edi
             add esi, 2 ;mutam esi cu 2 pozitii pentru a lua doar caracterele de pe pozitii pare
             cmp esi, ebx 
             jnge final2
             mov ecx, 1
             final2:
         loop RepetaSir1

        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
