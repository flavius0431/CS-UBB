from random import randint
from random import shuffle
from random import random

class Chromosome:
    def __init__(self, problParam=None):
        self.__problParam = problParam
        self.__repres = []

        nodes = list(range(self.__problParam["network"]["noNodes"]))
        nodes.remove(problParam['startNode'])
        nodes.remove(problParam['endNode'])
        shuffle(nodes)

        self.__repres = [problParam['startNode']] + nodes + [problParam['endNode']]

        self.__fitness = 0.0


    @property
    def repres(self):
        return self.__repres

    @property
    def fitness(self):
        return self.__fitness

    @repres.setter
    def repres(self, l=[]):
        self.__repres = l

    @fitness.setter
    def fitness(self, fit=0.0):
        self.__fitness = fit

    def crossover(self, c):
        parentscut=randint(0,len(self.__repres)-1)
        mommy=[0]*len(self.__repres)
        daddy=[0]*len(self.__repres)
        for i in range(0,parentscut):
            mommy[i]=self.__repres[i]
            daddy[i]=c.__repres[i]
        for i in range (parentscut,len(self.__repres)):
            daddy[i]=self.__repres[i]
            mommy[i]=c.__repres[i]
        plod1=Chromosome(self.__problParam)
        plod1.repres=mommy
        plod2=Chromosome(self.__problParam)
        plod2.repres=daddy
        return[plod1,plod2]

    def mutation(self):
        for _ in range(3):
            pos1=randint(1,len(self.__repres)-2)
            pos2=randint(1,len(self.__repres)-2)
            aux=self.__repres[pos1]
            self.__repres[pos1]=self.__repres[pos2]
            self.__repres[pos2]=aux
    def __str__(self):
        return '\nChromo: ' + str([i+1 for i in self.__repres]) + ' has fit: ' + str(self.__fitness)

    def __repr__(self):
        return self.__str__()

    def __eq__(self, c):
        return self.__repres == c.__repres and self.__fitness == c.__fitness