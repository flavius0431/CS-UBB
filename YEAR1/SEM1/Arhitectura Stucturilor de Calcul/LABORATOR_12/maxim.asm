bits 32


global _determina_maxim


segment data public data use32
segment code public code use32

; int determina_maxim(int*, int);
_determina_maxim:
	
	push ebp
	mov ebp, esp
	 

	mov esi, [ebp + 8]   ; adresa primului elemen
	mov ecx, [ebp + 12]  ; lungimea vectorului
	dec ecx

	mov ebx, 0b  ; stocam rezultatul in ebx

	_loop:
		lodsd  ; eax = [esi], esi += 4

		cmp eax, ebx   
		jle skip_move  ; eax <= ebx
		mov ebx, eax  ; ebx = eax

		skip_move:
	loop _loop

	 
	mov eax, ebx
	mov esp, ebp
	pop ebp
	ret

