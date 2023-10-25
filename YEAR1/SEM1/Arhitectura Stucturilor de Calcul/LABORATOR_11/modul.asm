bits 32

import scanf msvcrt.dll
extern scanf

global citeste_string, validare_input, urmatorul_numar

segment code use32 public code


caracter       db  0
format_citit     db  "%c", 0
ten             dw  10

citeste_string:
    ; citim din consola pana la space
   
    mov edi, [esp + 4]
    
    citeste_caracter:
       
       ; scanf(format_citit, &caracter)
        push dword caracter
        push dword format_citit
        call [scanf]
        add esp, 4 * 2  ; eliberam stiva
        
        cmp byte [caracter], 10  ; comparam cu caracterul nul
        je citire_sfarsit  ; cand terminam de citit, ultimul caracter este cel nul
        
        ; [edi] = [caracter]
        mov al, [caracter]
        mov [edi], al
        inc edi
        
    jmp citeste_caracter ; citim alt caracter
    
    citire_sfarsit:
    ret 1 * 4
    
    
    
    
validare_input:
    ; verififam daca fiecare caracter dat este cifra sau spatiu
    ; return 1 daca avem doar cifre si spatii, altfel 0
    ;rezultatul e in edx
    
    mov esi, [esp + 4]
    mov edx, 0 
    mov ebx, 0 
    
    lodsb  ; al = [esi]
    
    cmp al, 0  ; verificam daca primul caracter este 0
    je validare_sfarsit
    
    start_loop:
       
        cmp al, 20h  ; camparam cu ' '
        je spatiu
        
        
        cmp al, 30h  ; camparam cu '0'
        jb validare_sfarsit
        
        cmp al, 39h  ; camparam cu '9'
        jg validare_sfarsit
        
        jmp valid_caracter
            
        spatiu:
            mov ebx, 0
            jmp valid_caracter
            
        valid_caracter:
        lodsb  ; al = [esi]
        
    cmp al, 0
    jne start_loop;  citim alt caracter
    
    mov edx, 1
    validare_sfarsit:
    ret 1 * 4
    
    
urmatorul_numar:
    ; returnm uratorul numar
    ; rezultatul este stocat in eax
    ; ecx va fi -1 daca gasim 0 (sfarsitul sirului)
    
    mov esi, [esp + 4]
    mov eax, 0 
    mov ecx, 0
    mov ebx, 0
    
    
    lodsb  ; al = [esi]
    xchg cl, al  ; cl = [esi]
    
    cmp cl, 20h  ;  verificam daca primul caracter e spatiu
    je urmatorul_numar_sfarsit
    
    cmp cl, 0  ;  verificam daca primul caracter e 0
    je sfarsit_input
    
    
    start_loop_gen:
        
        imul word [ten] ;  result = result * 10
        sub cl, 30h  ; valoarea cifrelor(nu ascii)
        add eax, ecx  ; formam numarul
       
        
        xchg ecx, eax  ; schimbam pentru a nu strica eax
        lodsb  ; al = [esi]
        xchg ecx, eax  ; cl <-> al
        
        cmp cl, 0
        je sfarsit_input
        
    cmp cl, 20h
    jne start_loop_gen ; citim alt caracter
    
    jmp skip_sfarsit_input
    
    sfarsit_input:
    mov ecx, -1  ;  ecx =-1 daca nu mai aem caractere
    
    skip_sfarsit_input:
    
    cmp ebx, 1  
    jne urmatorul_numar_sfarsit
    neg eax
    
    urmatorul_numar_sfarsit:
    ret 1 * 4
    
    
    
    