import csv
import os
import matplotlib.pyplot as plt
import numpy as np
from sklearn import linear_model
from sklearn.metrics import mean_squared_error
import pandas as pd
from reader.read import loadData
from reader.read import loadData1

def plotDataHistogram(x, variableName):
    n, bins, patches = plt.hist(x, 10)
    plt.title('Histogram of ' + variableName)
    plt.show()



def divideData(gdp, freedom, happiness):
    np.random.seed(5)
    indexes = [i for i in range(len(gdp))]

    trainSample = np.random.choice(indexes, int(0.8 * len(gdp)), replace=False)
    validationSample = [i for i in indexes if not i in trainSample]

    trainInputs = [[gdp[i], freedom[i]] for i in trainSample]
    trainOutputs = [happiness[i] for i in trainSample]

    validationInputs = [[gdp[i], freedom[i]] for i in validationSample]
    validationOutputs = [happiness[i] for i in validationSample]

    return trainInputs, trainOutputs, validationInputs, validationOutputs

def divideData_null(gdp, freedom, happiness):
    np.random.seed(5)
    indexes = [i for i in range(len(gdp))]

    trainSample = np.random.choice(indexes, int(0.8 * len(gdp)), replace=False)
    validationSample = [i for i in indexes if not i in trainSample]

    trainInputs = [[happiness[i], freedom[i]] for i in trainSample]
    trainOutputs = [gdp[i] for i in trainSample]

    validationInputs = [[gdp[i], happiness[i]] for i in validationSample]
    validationOutputs = [freedom[i] for i in validationSample]

    return trainInputs, trainOutputs, validationInputs, validationOutputs

def sk_learnRegression_o(trainInputs, trainOutputs):
    x = [[el1] for el1 in trainInputs]
    regressor = linear_model.LinearRegression()
    regressor.fit(x, trainOutputs)
    w0, w1= regressor.intercept_, regressor.coef_[0], regressor.coef_[1]

    return w0, w1, regressor
def sk_learnRegression(trainInputs, trainOutputs):
    x = [[el1, el2] for el1, el2 in trainInputs]
    regressor = linear_model.LinearRegression()
    regressor.fit(x, trainOutputs)
    w0, w1, w2 = regressor.intercept_, regressor.coef_[0], regressor.coef_[1]

    return w0, w1, w2, regressor

if __name__=='__main__':

    crtDir = os.getcwd()
    filePath = os.path.join(crtDir, 'data', 'v3_world-happiness-report-2017.csv')
    df = pd.read_csv(filePath)

    inputsGDP, inputsFreedoom, outputs = loadData(filePath, 'Economy..GDP.per.Capita.', 'Freedom', 'Happiness.Score')


    plotDataHistogram(inputsGDP, 'capita GDP')
    plotDataHistogram(inputsFreedoom, 'Freedom')
    plotDataHistogram(outputs, 'Happiness score')



    trainInputs, trainOutputs, validationInputs, validationOutputs = divideData_null(inputsGDP, inputsFreedoom, outputs)


    w0, w1, w2, regressor = sk_learnRegression(trainInputs, trainOutputs)
    print('the learnt model: f(x) = ', w0, ' + ', w1, ' * x1', ' + ', w2, '* x2')


    noOfPoints = 1000
    xref = []
    yref = []
    trainInputsX = []
    trainInputsY = []
    for a in trainInputs:
        trainInputsX.append(a[0])
        trainInputsY.append(a[1])

    valx = min(trainInputsX)
    valy = min(trainInputsY)

    stepx = (max(trainInputsX) - min(trainInputsX)) / noOfPoints
    stepy = (max(trainInputsY) - min(trainInputsY)) / noOfPoints

    for i in range(1, noOfPoints):
        xref.append(valx)
        yref.append(valy)
        valx += stepx
        valy += stepy

    zref = []
    for i in range(len(xref)):
        zref.append(w0 + w1*xref[i] + w2*yref[i])

    ax = plt.axes(projection='3d')
    ax.plot(trainInputsX, trainInputsY, trainOutputs, 'ro', label = 'training data')
    ax.plot3D(xref, yref, zref, 'b-', label = 'learnt model')
    ax.set_title('train data and the learnt model')
    ax.set_xlabel('GDP')
    ax.set_ylabel('Fredoom')
    ax.set_zlabel('Happiness')
    ax.legend()
    plt.show()


    computedValidationOutputs = regressor.predict([x for x in validationInputs])

    validationInputsX = []
    validationInputsY = []

    for a in validationInputs:
        validationInputsX.append(a[0])
        validationInputsY.append(a[1])

   
    ax = plt.axes(projection='3d')
    ax.plot(validationInputsX, validationInputsY, computedValidationOutputs, 'yo', label = 'computed test data')  #computed test data are plotted yellow red and circle sign
    ax.plot(validationInputsX, validationInputsY, validationOutputs, 'g^', label = 'real test data')              #real test data are plotted by green triangles
    ax.set_title("computed validation and real validation data")
    ax.set_xlabel('GDP capita')
    ax.set_ylabel('Freedom')
    ax.set_zlabel('Happiness')
    ax.legend()
    plt.show()

    # compute the differences between the predictions and real outputs
    # manual computation
    error = 0.0
    for t1, t2 in zip(computedValidationOutputs, validationOutputs):
        error += (t1 - t2) ** 2
    error = error / len(validationOutputs)
    print('\nprediction error (manual): ', error)

    # by using sklearn
    error = mean_squared_error(validationOutputs, computedValidationOutputs)
    print("prediction error (tool): ", error)
