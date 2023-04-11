import os
import warnings
import matplotlib.pyplot as plt
from read import *
from GA import GA
from random import uniform

def main():
    # load a tetwork
    allBestFitnesses = []
    allAvgFitnesses = []
    allWorstFitnesses=[]
    generation=[]
    crtDir = os.getcwd()
    #filePath = os.path.join(crtDir, 'data/football', 'football.gml')
    filePath = os.path.join(crtDir, 'data/dolphins', 'dolphins.gml')
    #filePath = os.path.join(crtDir, 'data/krebs', 'krebs.gml')
    network = readNet(filePath)

    # plot the network

    warnings.simplefilter('ignore')

    # initialise de GA parameters
    gaParam = {'popSize': 50, 'noGen': 100}
    # problem parameters
    problParam = {'min' : 10, 'max' : 35,'function':modularity_density, 'network': network, 'noNodes': network['noNodes']}

    ga = GA(gaParam, problParam)
    ga.initialisation()
    ga.evaluation()

    minChromo=ga.worstChromosome()
    maxChromo=ga.bestChromosome()
    for g in range(gaParam['noGen']):
        generation.append(g)
        ga.oneGenerationElitism()
        bestChromo = ga.bestChromosome()
        worstChromo = ga.worstChromosome()
        allBestFitnesses.append(bestChromo.fitness)
        allWorstFitnesses.append(worstChromo.fitness)
        allPotentialSolutions = [c.fitness for c in ga.population]
        allAvgFitnesses.append(sum(allPotentialSolutions) / len(allPotentialSolutions))
        if bestChromo.fitness < maxChromo.fitness:
            maxChromo = bestChromo
        print(bestChromo)

        print('Strong chromosome ' + str(g) + ' is: Chromosome = ' + str(bestChromo.repres) + ' fitness = ' + str(bestChromo.fitness))
        allWorstFitnesses.append(worstChromo.fitness)
        allBestFitnesses.append(bestChromo.fitness)


    chromosome = ga.bestChromosome()
    plt.ioff()
    best, = plt.plot(generation,allBestFitnesses,label='best')
    mean, = plt.plot(generation,allAvgFitnesses,label='mean')
    worst, =plt.plot(generation,allWorstFitnesse,label='worst')
    plt.legend([best,mean,worst],['Best','Mean','Worst'])
    plt.show()
    #plot_network(network,maxChromo.repres)

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


main()
