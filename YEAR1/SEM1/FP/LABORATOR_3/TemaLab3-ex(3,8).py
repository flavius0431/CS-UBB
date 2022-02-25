"""
Scrieti o aplicatie care are interfata utilizator tip consolă cu un meniu:
1 Citirea unei liste de numere intregi
2 Gasirea secventelor de lungime maxima care respectă o proprietatea dată. (8) Au toate elementele in intervalul [0, 10] dat.
3 Gasirea secventelor de lungime maxima care respectă o proprietatea dată.(12) Are oricare doua elemente consecutive sunt de semne contrare.
4 Gasirea secventelor de lungime maxima care respectă o proprietatea dată.(9) p=1 sau in oricare trei elemente consecutive exista o valoarea care se repeta.
5 Iesire din aplicatie.

Documentatia să contină:
 Scenarii de rulare pentru cele două cerinte primite (vezi curs 1 – scenarii de rulare)
 Cazuri de testare pentru cele doua cerinte în format tabelar (vezi curs 1 – cazuri de testare)
"""

def print_menu():
    print("1.Citirea unei liste de numere intregi.")
    print("2.Gasirea secventelor de lungime maxima care are toate elementele in intervalul [0, 10].")
    print("3.Gasirea secventelor de lungime maxima care respectă o proprietatea dată.(12) Are oricare doua elemente consecutive sunt de semne contrare.")
    print("4.Gasirea secventelor de lungime maxima care respectă o proprietatea dată.(9) p=1 sau in oricare trei elemente consecutive exista o valoarea care se repeta.")
    print("5.Iesire din aplicatie.")
    
def print_list(message, lst):
    print(message,lst)

def interva_0_10(the_list):
    """
    Gaseste secventa de lungime  maxima care are toate elementele in intervalul[0,10]
    :parm the_list : lista de numere in care cautam secventa de lungime maxima
    :type the_list : list
    :return: secventa de lungime maxima care are elementele in intervalul [0,10]
    :rtype: list
    
    """
    
    lista_secventa_maxima = []
    lista_secventa = []

    for el in the_list:
         if el >=0 and el <=10:
            lista_secventa.append(el)
         else:
             if(len(lista_secventa) > len(lista_secventa_maxima)):
                 lista_secventa_maxima = lista_secventa[:]
             lista_secventa.clear()
    if(len(lista_secventa) > len(lista_secventa_maxima)):
        lista_secventa_maxima = lista_secventa[:]
    return lista_secventa_maxima
   
   
    


def semne_contrare(number_list):
    """
    Gaseste secventa de lungime  maxima care are oricare doua elemente consecutive sunt de semne contrare
    :parm the_list : lista de numere in care cautam secventa de lungime maxima
    :type the_list : list
    :return: secventa de lungime maxima care are oricare doua elemente consecutive sunt de semne contrare
    :rtype: list
    
    """
    
    m=0 if number_list[0]<0 else 1 # in m pastram semnul primului numar, 0 daca numarul este mai mic decat 0, respectiv 1 daca este mai mare sau egal cu 0
    lista_semne=[]
    lista_maxima=[]
    lista_semne.append(number_list[0])
    for i in range(1,len(number_list)):
       n = 0 if number_list[i]<0 else 1
       if n != m:
           lista_semne.append(number_list[i])
           m=n
       else:
           lista_semne.clear()
           m=n
           lista_semne.append(number_list[i])
               
       if (len(lista_semne)>len(lista_maxima)):
           lista_maxima=lista_semne[:]
       
    if len(lista_semne)>len(lista_maxima):
            lista_maxima=lista_semne[:]
    return lista_maxima


def trei_elem_consecutive(number_list):
    lista_maxima=[]
    lista_sec=[]
    nr1 = number_list[0]
    nr2 = number_list[1]
    lista_sec.append(nr1)
    lista_sec.append(nr2)
   
    
    for i in range(2, len(number_list)):
        if nr1 == nr2 or nr1 == number_list[i] or nr2 == number_list[i]:
            lista_sec.append(number_list[i])
            
        else:
            if len(lista_sec)>len(lista_maxima):
                lista_maxima=lista_sec[:]
            lista_sec.clear()
        
        nr1 = nr2
        nr2 = number_list[i]

            
    if len(lista_sec)>len(lista_maxima):
        lista_maxima=lista_sec[:]
    return lista_maxima


def citeste_lista():
    #format: 6, 10, 1001, 503
    the_list_as_string = input("Dati lista de numere intregi in formatul cerut: ")
    list_of_strings = the_list_as_string.split(",")
    
    number_list=[int(elem.strip()) for elem in list_of_strings]

    return number_list

def start():
    current_list=[]
    while True:
        print_menu()
        print_list('Lista curenta este: ', current_list)
        option = int( input("Optiunea dumneavoastra este: "))
        if option == 1:
            current_list =  citeste_lista()
        elif option == 2:
            print("Secventa maxima care are toate elementele din intervalul [0,10]: ",interva_0_10(current_list))
        elif option == 3:
            print("Secventa maxima care are oricare 2 elemente consecutive de semne contrare: ", semne_contrare(current_list))
        elif option == 4:
            print("secventa maxima care are in oricare trei elemente consecutive exista o valoarea care se repeta.",trei_elem_consecutive(current_list))
        elif option == 5:
            return

start()

        
            
