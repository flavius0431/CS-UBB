import os
import warnings
import matplotlib.pyplot as plt
from read import *
from GA import GA
from random import uniform
from read import readTSP


def plot_network(network,comunities):
    A = np.matrix(network["mat"])
    G = nx.from_numpy_array(A)
    pos = nx.spring_layout(G)  # compute graph layout
    plt.figure(figsize=(20, 20))  # image is 8 x 8 inches
    nx.draw_networkx_nodes(G, pos, node_size=800, cmap=plt.cm.RdYlBu, node_color=comunities)
    nx.draw_networkx_edges(G, pos, alpha=0.3)
    plt.show()

def generateNewValue(lim1, lim2):
    return (int)(uniform(lim1, lim2))

def computeFitness(chromo, param):
    repr = chromo.repres
    mat = param['network']['mat']
    if len(repr) < 2: return 100000
    f = 0
    for i in range(0, len(repr)-1):
        f += mat[repr[i]][repr[i+1]]
    f += mat[repr[-1]][repr[0]] #daca vrem ciclu

    return f

if __name__ == '__main__':
    # load a tetwork
    crtDir = os.getcwd()

    filePath = os.path.join(crtDir, 'data', 'berlin52.txt')

    networks = read_berlin(filePath)
    network=networks['network']

    # plot the network

    warnings.simplefilter('ignore')

    # initialise de GA parameters
    gaParam = {'popSize': 50, 'noGen': 500,'function':computeFitness}
    # problem parameters
    problParam = {'function':computeFitness, 'network': network, 'noNodes': network['noNodes'],'startNode':networks['startNode'],'endNode':networks['endNode']}

    ga = GA(gaParam, problParam)
    ga.initialisation()
    ga.evaluation()

    chromoMax = ga.bestChromosome()
    allBestFitnesses = []
    allWorstFitnesses = []
    allAvgFitnesses = []
    generations = []

    for g in range(gaParam['noGen']):
        generations.append(g)
        ga.oneGenerationElitism()
        bestChromo = ga.bestChromosome()
        worstChromo = ga.worstChromosome()
        allBestFitnesses.append(bestChromo.fitness)
        allWorstFitnesses.append(worstChromo.fitness)
        allPotentialSolutions = [c.fitness for c in ga.population]
        allAvgFitnesses.append(sum(allPotentialSolutions) / len(allPotentialSolutions))
        if bestChromo.fitness < chromoMax.fitness:
            chromoMax = bestChromo
        print(bestChromo)

    print()
    print("Best configuration(max chromosome):")
    print(chromoMax)
    plot_network(network, chromoMax.repres)

    plt.ioff()
    best, = plt.plot(generations, allBestFitnesses, 'g', label='best')
    avg, = plt.plot(generations, allAvgFitnesses, 'b', label='average')
    worst, = plt.plot(generations, allWorstFitnesses, 'r', label='worst')

    plt.legend([best, avg, worst], ['Best', 'Average', 'Worst'])
    plt.show()
