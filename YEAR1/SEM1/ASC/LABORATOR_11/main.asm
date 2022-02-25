;17.Se citeste de la tastatura un sir de numere in baza 10 fara semn. 
;Sa se determine valoarea maxima din sir si sa se afiseze in fisierul
; max.txt (fisierul va fi creat) valoarea maxima, in baza 16
bits 32 

global start        

import exit msvcrt.dll  
import scanf msvcrt.dll
import printf msvcrt.dll
import fopen msvcrt.dll
import fprintf msvcrt.dll
import fclose msvcrt.dll

extern exit, scanf, printf, fopen, fprintf, fclose
extern citeste_string, validare_input, urmatorul_numar


segment data use32 class=data
    fila_nume           db  "max.txt", 0
    descriptior    dd  -1 
    mod_acces         db  "w", 0
    
    b16_format          db  "%x ", 0
    
    invalid_input_text  db  "Nu este valida intrarea!", 0
    
    maxim        dd  0b
    
    text        times 128 db 0
  
    temp        dd  0
    
    
segment code use32 class=code
    start:
        
        push dword text
        call citeste_string  ; citeste stringul din consola
        
        push dword text
        call validare_input  ; valideaza string

        cmp edx, 0  ; nevalid
        je Invalid_Input
        
        
        mov esi, text
        
        rezolva:
            
            push dword esi
            call urmatorul_numar
            
            mov [temp], ecx
            
            cmp eax, [maxim]
            jbe skip
            mov [maxim], eax
            
            mov ecx, [temp]
            
            skip:
        cmp ecx, -1  ; daca ecx == -1 am verificat toate numerele
        jne rezolva
        
        ; deschidem fisierul
        
        push dword mod_acces
        push dword fila_nume
        call [fopen]    
        add esp, 4 * 2
        
        mov [descriptior], eax 
        
        ; scrie numarul
        push dword [maxim]
        push dword b16_format
        push dword [descriptior]
        call [fprintf]
        add esp, 4 * 2
        
        ; inchidem fisierul
        push dword [descriptior]
        call [fclose]
        add esp, 4 * 1
        
        
        jmp _Sfarsit
        
        Invalid_Input:
            push dword invalid_input_text
            call [printf]
            
            
        _Sfarsit:
        
        push    dword 0      
        call    [exit]
