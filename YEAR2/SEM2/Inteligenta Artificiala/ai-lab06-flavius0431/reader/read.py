import csv
import matplotlib.pyplot as plt
import numpy as np


def loadData(fileName, inputVariabName1, inputVariabName2, outputVariabName):
    data = []
    dataNames = []
    with open(fileName) as csv_file:
        csv_reader = csv.reader(csv_file, delimiter=',')
        line_count = 0
        for row in csv_reader:
            if line_count == 0:
                dataNames = row
            else:
                data.append(row)
            line_count += 1

    pos=[]
    selectedVariable1 = dataNames.index(inputVariabName1)
    selectedVariable2 = dataNames.index(inputVariabName2)
    selectedOutput = dataNames.index(outputVariabName)
    input1=[]
    input2=[]
    outputs=[]

    for i in range(len(data)):
        if data[i][selectedVariable1] != '' and data[i][selectedVariable2] != '' and data[i][selectedOutput]:
            input1.append(float(data[i][selectedVariable1]))
            input2.append(float(data[i][selectedVariable2]))
            outputs.append(float(data[i][selectedOutput]))

    # selectedVariable = dataNames.index(inputVariabName1)
    # inputs1 = [float(data[i][selectedVariable])  for i in range(len(data)) ]
    #
    # selectedVariable = dataNames.index(inputVariabName2)
    # inputs2 = [float(data[i][selectedVariable]) for i in range(len(data))]
    #
    # selectedOutput = dataNames.index(outputVariabName)
    # outputs = [float(data[i][selectedOutput]) for i in range(len(data))]

    return input1, input2, outputs

def loadData1(fileName, inputVariabName1, inputVariabName2, outputVariabName):
    data = []
    dataNames = []
    with open(fileName) as csv_file:
        csv_reader = csv.reader(csv_file, delimiter = ',')
        line_count = 0
        for row in csv_reader:
            if line_count == 0:
                dataNames = row
            else:
                data.append(row)
            line_count += 1

    selectedVariable = dataNames.index(inputVariabName1)
    inputs1 = [float(data[i][selectedVariable]) for i in range(len(data))]

    selectedVariable = dataNames.index(inputVariabName2)
    inputs2 = [float(data[i][selectedVariable]) for i in range(len(data))]

    selectedOutput = dataNames.index(outputVariabName)
    outputs = [float(data[i][selectedOutput]) for i in range(len(data))]

    return inputs1, inputs2, outputs


def plotTrain(regressor, trainInput, trainOutput):
    fig = plt.figure()
    ax = fig.add_subplot(111, projection='3d')
    ax.scatter([trainInput[i][1] for i in range(len(trainInput))], [trainInput[i][2] for i in range(len(trainInput))],
               trainOutput, marker='.', color='red')
    plotArea(regressor, trainInput, trainOutput, ax)

def plotTest(regressor, trainInput, trainOutput, testInput, testOutput):
    fig = plt.figure()
    ax = fig.add_subplot(111, projection='3d')
    ax.scatter([testInput[i][0] for i in range(len(testInput))], [testInput[i][1] for i in range(len(testInput))],
               testOutput, marker='^', color='green')
    plotArea(regressor, trainInput, trainOutput, ax)

def plotArea(regressor, trainInput, trainOutput, ax):

    ax.set_xlabel("GDP")
    ax.set_ylabel("Freedom")
    ax.set_zlabel("Happiness")

    x = np.arange(min([trainInput[i][1] for i in range(len(trainInput))]),
                  max([trainInput[i][1] for i in range(len(trainInput))]), 0.01)

    y = np.arange(min([trainInput[i][2] for i in range(len(trainInput))]),
                  max([trainInput[i][2] for i in range(len(trainInput))]), 0.1)
    x, y = np.meshgrid(x, y)

    z = [regressor.predict([d1, d2]) for d1, d2 in zip(x,y)]
    z = np.array(z)

    ax.plot_surface(x, y, z.reshape(x.shape), alpha=0.7)
    plt.show()

