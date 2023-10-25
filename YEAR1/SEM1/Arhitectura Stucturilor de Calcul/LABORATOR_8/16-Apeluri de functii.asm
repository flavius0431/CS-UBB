;16.Sa se citeasca de la tastatura doua numere a si b (in baza 10).
; Sa se calculeze si sa se afiseze media lor aritmetica in baza 16.
bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit,printf,scanf   
            ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions
import printf msvcrt.dll
import scanf msvcrt.dll

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...
    
    a dd  0 ; in aceasta variabila se va stoca valoarea citita de la tastatura a lui a
    b dd  0 ; in aceasta variabila se va stoca valoarea citita de la tastatura a lui b
    messagea db "a = ",0
    messageb db "b = ",0
    messageab db "(a+b)/2 = %X,%X",0 ; format pentru afisare
    format db "%d",0 ; format pentru citire
  

; our code starts here
segment code use32 class=code
    start:
        ; ...
        
        ; vom apela printf(messagea) => se va afisa "a = "
        ; punem parametrii pe stiva
        push dword messagea ; ! pe stiva se pune adresa string-ului, nu valoarea
        call[printf]   ; apelam functia printf pentru afisare
        add esp, 4*1   ; eliberam parametrii de pe stiva ; 4 = dimensiunea unui dword; 1 = nr de parametri
        
        
        ; vom apela scanf(format, a) => se va citi un numar in variabila a
        ; punem parametrii pe stiva de la dreapta la stanga
        push dword a ; ! adresa lui a, nu valoarea
        push dword format
        call [scanf] ; apelam functia scanf pentru citire
        add esp,4*2  ; eliberam parametrii de pe stiva
                     ; 4 = dimensiunea unui dword; 2 = nr de parametri
        
        
        ; vom apela printf(messageb) => se va afisa "b = "
        ; punem parametrii pe stiva
        push dword messageb ; ! pe stiva se pune adresa string-ului, nu valoarea
        call[printf] ; apelam functia printf pentru afisare
        add esp, 4*1 ; eliberam parametrii de pe stiva 
                     ; 4 = dimensiunea unui dword; 1 = nr de parametri
        
        ; vom apela scanf(format, b) => se va citi un numar in variabila a
        ; punem parametrii pe stiva de la dreapta la stanga
        push dword b ; ! adresa lui b, nu valoarea
        push dword format 
        call [scanf] ; apelam functia scanf pentru citire
        add esp, 4*2 ; eliberam parametrii de pe stiva
                     ; 4 = dimensiunea unui dword; 2 = nr de parametri
        
        
        
        ;facem media aritmedica 
        mov eax,[a] ; eax = a
        mov ebx,[b] ; ebx = b
        mov edx,0   ; edx = 0
        add eax, ebx; eax = eax + ebx
        mov ecx, 2  ; ecx = 2
        idiv ecx    ; eax = edx:eax/ecx
        
        
        
        push edx
        push eax
        push dword messageab
        call[printf]; apelam functia printf pentru afisare
        add esp,4*2 ; eliberam parametrii de pe stiva
                    ; 4 = dimensiunea unui dword; 2 = nr de parametri
        
      
        
    
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
