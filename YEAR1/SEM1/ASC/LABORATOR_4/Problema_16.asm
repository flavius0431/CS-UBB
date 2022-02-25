;Problema 16.Se dau octetul A si cuvantul B. Sa se obtina dublucuvantul C:
;bitii 0-7 ai lui C au valoarea 1
;bitii 8-11 ai lui C coincid cu bitii 4-7 ai lui A
;bitii 12-19 coincid cu bitii 2-9 ai lui B
;bitii 20-23 coincid cu bitii 0-3 ai lui A
;bitii 24-31 coincid cu octetului high din cuvantul B

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
    A db          10010011b ; A = 1001 0011b = 93h
    B dw  0101001000100110b  ; B = 0101 0010 0010 0110 b = 5226h
    C dd 0                  ; C = 0000b = 0h

; our code starts here
segment code use32 class=code
    start:
        ; ...
        ;Se dau octetul A si cuvantul B. Sa se obtina dublucuvantul C:
        ;bitii 0-7 ai lui C au valoarea 1
        ;bitii 8-11 ai lui C coincid cu bitii 4-7 ai lui A
        ;bitii 12-19 coincid cu bitii 2-9 ai lui B
        ;bitii 20-23 coincid cu bitii 0-3 ai lui A
        ;bitii 24-31 coincid cu octetului high din cuvantul B
        
        mov edx, 0 ; in registrul edx vom calcula rezultatul
        mov dl, 11111111b ;punem pe bitii 0-7 valoarea 1
        
        mov eax,0 ; in registu eax vom pune bitii 8-11
        mov al, [A]
        and al, 11110000b ; izolam bitii 4-7
        mov cl, 4
        ror al,cl
        
        mov dh, al
        ;edx=0000 0000 0000 0000 0000 1001 1111 1111
        mov ebx, 0 ; in registu ebx vom pune bitii 12-19
        mov bx,[B]
        and bx, 0000001111111100b ; izolam bitii 2-9 din B
        ;       0101 0010 0010 0110 =
        ;bx=1000 1001 = 89h
        
        mov cl, 10
        rol ebx, cl ; deplasam cu 10 pozitii spre stanga
        
        or  edx, ebx ; punem bitii in edx
        
        mov eax, 0 ; in registu eax vom pune bitii 20-23
        mov al, [A]
        and al, 00001111b ; izolam bitii 0-3
        mov cl, 20
        rol eax, cl; deplasam  cu 20 de pozitii spre stanga
        
        or edx, eax ; punem bitii in edx
        
        mov ebx, 0 ; in registu ebx vom pune bitii 24-31
        mov bx,[B]
        and bx, 1111111100000000b ; izolam bitii 8-15
        mov cl, 16
        rol ebx, cl ; deplasam cu 16 pozitii spre stanga
        
        or edx, ebx; punem bitii in edx
         
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
