#15. a. Să se testeze egalitatea a două liste.
# b. Să sedetermine intersecția a două mulțimi reprezentate sub formăde listă.
from ListaRecursiva import creareLista, tipar, egalitate


def main():
    list1 = creareLista()
    #tipar(list1)
    list2 = creareLista()
    #tipar(list2)
    egalitate(list1,list2)

main()
