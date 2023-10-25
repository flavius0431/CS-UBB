;16.Se dau doua siruri de caractere ordonate alfabetic s1 si s2. 
;Sa se construiasca prin interclasare sirul ordonat s3 care 
;sa contina toate elementele din s1 si s2.
;medota indirecta

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
    s1 db 'a', 'c', 'd'
    l1  equ $-s1
    s2 db 'b', 'e', 'f'
    l2 equ $-s2
    l  equ l1+l2
    
    s3 TIMES l db 0 
        
; our code starts here
segment code use32 class=code
    start:
        ; ...
        mov esi, l1 ; indexul pentru s1
        mov ebx, l2 ; indexul pentru s2
        mov ecx, l ; lungimea lui s3
        mov edi, s3+l ; indexul pentru s3
        
                
        jecxz final
        std
        repeta:
             cmp esi,0 ; comparam esi cu 0
             jz final1 ;
             cmp ebx, 0 ; comparam ebx cu 0
             jz final2 ;
             mov al, [s1+ esi-1] ; mutam in al  elementul din s1 de pe pozitia esi
             mov ah, [s2 + ebx-1] ; mutam in ah elementul din s2 de pe pozitia ebx
             cmp al, ah ; comparam al cu ah 
             
             ja et  ; daca al mai mare decat ah sarim la eticheta et
                 mov al,ah; mutam din ah in al
                 stosb; mutam elementul din al in s3
                  ; se decrementeaza edi
                  dec ebx; decrementeaza ebx
             
             jmp continua
             et:
                 stosb ; mutam elementul din al in s3
                  ; se decrementeaza edi
                  dec esi ; se decrementeaza esi
 
             continua:
  
              loop repeta
         
         jmp final 
         final1:
         ; merg cu s2 unde mutam toate caracterele ramase in s2 in s3
         repetas2:
             mov al, [s2+ebx-1]
             stosb ; mutam fin al in edi
             dec ebx ; decrementam ebx
         loop repetas2
         jmp final
         final2:
         ; merg cu s1 unde mutam toate caracterele ramase in s1 in s3
         repetas1:
             mov al, [s1+esi-1]
             stosb ; mutam din al in edi
             dec esi; decrementam esi
          loop repetas1
   
         final:

    
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
