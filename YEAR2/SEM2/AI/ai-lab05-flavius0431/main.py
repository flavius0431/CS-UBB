from utils.reader import *
import os
import math

def error_prediction(X,Y,Z,PX,PY,PZ):
    avgabs = 0
    avgpow = 0
    avgsqrt = 0

    for i in range(len(X)):
        avgabs += math.sqrt(math.pow(X[i] - PX[i],2) + math.pow(Y[i] - PY[i],2) + math.pow(Z[i] - PZ[i],2))
        avgpow += math.pow(math.sqrt(math.pow(X[i] - PX[i], 2) + math.pow(Y[i] - PY[i], 2) + math.pow(Z[i] - PZ[i], 2)),2)
        avgsqrt += math.pow(math.sqrt(math.pow(X[i] - PX[i], 2) + math.pow(Y[i] - PY[i], 2) + math.pow(Z[i] - PZ[i], 2)), 2)

    avgabs= avgabs/len(X)
    avgpow = avgpow/len(X)
    avgsqrt = math.sqrt(avgsqrt / len(X))

    print("Media cu modul: ",avgabs)
    print("Media patratelor: ",avgpow)
    print("Media cu radical: ",avgsqrt)


def cross_entropy_loss(y_pred,y_true):
    epsilon = 1e-9
    y_pred = np.clip(y_pred, epsilon, 1.0 - epsilon)
    ce_loss = -np.sum(y_true * np.log(y_pred)) / y_pred.shape[0]
    return ce_loss

import numpy as np

def log_loss_function_classification(y_pred, y_true):
    loss = 0
    for i in range(len(y_true)):
        loss = loss - y_true[i] * np.log(y_pred[i])

    return loss

def loss_function_regression(x,y,bi):
    loss=0
    for i in range(len(y)):
        t=y[i]
        for j in range(len(bi)):
            t=t-x[i][j]*bi[j]
        loss= loss+ t*t
    return loss



def prediction(type,predictiontype):
    acc= sum([1 if type[i] == predictiontype[i] else 0 for i in range(0,len(type))])/len(type)

    rez = []
    for i in range(0,len(type)):
        if type[i] not in rez:
            rez.append((type[i]))

    TP = 0
    FP = 0
    TN = 0
    FN = 0

    for i in range(0, len(rez)):
        for j in range(len(type)):
            if type[j] == rez[i] and predictiontype[i] == rez[i]:
                TP += 1
            if type[j] != rez[i] and predictiontype[i] == rez[i]:
                FP += 1
            if type[j] != rez[i] and predictiontype[i] != rez[i]:
                TN += 1
            if type[j] == rez[i] and predictiontype[i] != rez[i]:
                FN += 1

    precisionPos = TP / (TP + FP)
    recallPos = TP / (TP + FN)

    precisionNeg = TN / (TN + FN)
    recallNeg =TN / (TN + FP)

    return acc, precisionPos, precisionNeg, recallPos, recallNeg



if __name__=='__main__':
    crtDir = os.getcwd()
    Path = os.path.join(crtDir,'data','sport.csv')
    weight, waist, pulse, predictedWeight, predictedWaist, predictedPulse = readDate(Path)
    error_prediction( weight, waist, pulse, predictedWeight, predictedWaist, predictedPulse)
    crtDir = os.getcwd()
    Path = os.path.join(crtDir, 'data', 'flowers.csv')
    type,predictiontype = readflowers(Path)
    acc, precPos, precNeg, recallPos, recallNeg = prediction(type,predictiontype)
    print('acc: ', acc, ' precision positive: ', precPos, ' precision negative: ', precNeg, ' recall positive: ',
          recallPos, ' recall negative ', recallNeg)

    y_pred = np.array([0.8, 0.3, 0.6, 0.2])
    y_true = np.array([1, 0, 1, 0])

    log_loss = log_loss_function_classification(y_pred, y_true)

    print("Log loss classification:", log_loss)

    x = [[1, 2], [2, -1], [2, 2], [0, 2]]
    y = [1, -1, 0, 0]
    bi = [-0.3, 0.7]

    loss = loss_function_regression(x, y, bi)

    print("Loss regression:", loss)






