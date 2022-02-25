;15Se dau un nume de fisier si un text (definite in segmentul de date). 
;Textul contine litere mici, litere mari, cifre si caractere speciale.
; Sa se inlocuiasca toate caracterele speciale din textul dat cu caracterul 'X'.
; Sa se creeze un fisier cu numele dat si sa se scrie textul obtinut in fisier.
bits 32
global start        

extern exit,fopen,fprintf,fclose ; need to declare functions as extern
import exit msvcrt.dll
import fopen msvcrt.dll ; need to import the functions
import fprintf msvcrt.dll
import fclose msvcrt.dll

segment data use32 class=data
    

    fname db "fisier.txt",0 ; numele fisierului care va fi creat
    mod_acces db "w",0       ; modul de deschidere a fisierului - 
                              ; w - pentru scriere. daca fiserul nu exista, se va crea
    descr_fis dd -1           ; variabila in care vom salva descriptorul fisierului - necesar pentru a putea face referire la fisier       
    format db "%s",0          ;format
    
    text db "@ANA are 12 Mere $! multe & pere!!",0   ; textul care va fi scris in fisier 
    len equ ($-text)
    
segment code use32 class=code
    start:
        mov ecx,len 
        cld     
        mov esi,text   
        start_loop:
            
            mov edi,esi 
            lodsb                  
            cmp al, 'a'                 ; verificam daca al >= 'a'
            jae verificam_litera_mica
            jmp verificam_litera_mare
            jmp verificam_cifre
            
        verificam_litera_mica:          ; stim ca al >= 'a'
            cmp al, 'z'             ; verificam daca al <= 'z'
            dec ecx
            jna start_loop                  ; daca al <= 'z' trecem la urmatoarea citire
            
        verificam_litera_mare:
            cmp al, 'A'                 ; verificam daca al >= 'A'
            jae verificam_litera_mare2
            jmp verificam_cifre
            
           
        verificam_litera_mare2:         ; stim ca al >= 'A'
            cmp al, 'Z'                 ; verificam daca al <= 'Z'
            dec ecx
            jna start_loop              ; daca al <= 'Z' trecem la urmatoarea citire
            
        verificam_cifre:
             cmp al,'1'                  ; verificam daca al >= '1'
             jae verificam_cifra_2 
             jmp store
             
         verificam_cifra_2:              ;stim ca al >= 'a'
             cmp al,'9'                  ; verificam daca al <= 'z'
             dec ecx
             jna start_loop
             
             
            store:            ;stim ca al nu este litera si nici cifra
                cmp al,' '    ; verificam daca este spatiu
                je skip
                mov al,'X'      ;daca al este caracter special il mutam pe 'X' in al
                stosb          ; inlocuim caracterul secial cu 'X'
                
            skip:
        
        loop start_loop
        
        ; apelam functia fopen() pentru a deschide fisierul
        ; functia va returna in EAX descriptorul fisierului sau 0 in caz de eroare
        ; eax = fopen(nume_fisier, mod_acces)
        push dword mod_acces
        push dword fname
        call [fopen]
        add esp,4*2
        
        ; verificam daca functia fopen a deschis cu succes fisierul (EAX != 0)
        cmp eax,0
        je final    ; daca a aparut o eroare (EAX = 0), terminam executia programului
        
        mov [descr_fis],eax
        
        ;eax = fprintf(FILE *stream,const char *format, [arguments..]);
        
        push dword text
        push dword format
        push dword [descr_fis]
        call [fprintf]
        add esp,4*3
        
        push dword [descr_fis]
        call [fclose]
        add esp,4
        
        final:
        
        push    dword 0
        call    [exit]