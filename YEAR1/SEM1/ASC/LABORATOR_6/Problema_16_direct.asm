;Se dau doua siruri de caractere ordonate alfabetic s1 si s2. 
;Sa se construiasca prin interclasare sirul ordonat s3 care 
;sa contina toate elementele din s1 si s2.

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
    s1 db 'a', 'b', 'c','x','y','z' 
    l1  equ $-s1
    s2 db 'd', 'e','f', 'h'
    l2 equ $-s2
    l  equ l1+l2

    s3 TIMES l db 0 
        
; our code starts here
segment code use32 class=code
    start:
        ; ...
        mov esi, 0 ; indexul pentru s1
        mov ebx, 0 ; indexul pentru s2
        mov ecx, l ; lungimea lui s3
        mov edi, s3 ; indexul pentru s3
        
                
        jecxz final
        cld
        repeta:
             cmp esi,l1-1 ; comparam esi cu l1-1
             ja final1 ; daca esi > l1-1 atunci mergem la final1
             cmp ebx, l2-1 ; comparam ebx cu l2-1
             ja final2 ; daca ebx > l2-1 atunci mergem la final2
             mov al, [s1+esi] ; mutam in al  elementul din s1 de pe pozitia esi
             mov ah, [s2+ebx] ; mutam in ah elementul din s2 de pe pozitia ebx
             cmp al, ah ; comparam al cu ah 
             
             jb et  ; daca al mai mic decat ah sarim la eticheta et
                 mov al,ah; mutam din ah in al
                 stosb; mutam elementul din al in s3
                  ; se incrementeaza edi
                  inc ebx; incrementa ebx
             
             jmp continua
             et:
                 stosb ; mutam elementul din al in s3
                  ; se incrementeaza edi
                  inc esi ; incrementa esi
 
             continua:
  
              loop repeta
         
         jmp final 
         final1:
         ; merg cu s2 unde mutam toate caracterele ramase in s2 in s3
         repetas2:
             mov al, [s2+ebx]
             stosb ; la adresa <es:edi> se incarca in octetul din al 
             inc ebx ; incrementam ebx
         loop repetas2
         jmp final
         final2:
         ; merg cu s1 unde mutam toate caracterele ramase in s1 in s3
         repetas1:
             mov al, [s1+esi]
             stosb ; la adresa <es:edi> se incarca in octetul din al 
             inc esi; incremetam esi
          loop repetas1
   
         final:

    
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
