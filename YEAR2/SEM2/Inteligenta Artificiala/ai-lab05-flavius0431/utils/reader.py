import csv
import numpy as np

def readDate(filename):
    f = open(filename, "r")
    csv_reader = csv.reader(f)
    weight = []
    waist = []
    pulse = []
    predictedWeight = []
    predictedWaist = []
    predictedPulse = []
    for row in csv_reader:
        weight.append(int(row[0]))
        waist.append(int(row[1]))
        pulse.append(int(row[2]))
        predictedWeight.append(int(row[3]))
        predictedWaist.append(int(row[4]))
        predictedPulse.append(int(row[5]))

    def readDate(filename):
        f = open(filename, "r")
        csv_reader = csv.reader(f)
        weight = []
        waist = []
        pulse = []
        predictedWeight = []
        predictedWaist = []
        predictedPulse = []
        for row in csv_reader:
            weight.append(int(row[0]))
            waist.append(int(row[1]))
            pulse.append(int(row[2]))
            predictedWeight.append(int(row[3]))
            predictedWaist.append(int(row[4]))
            predictedPulse.append(int(row[5]))

    return weight, waist, pulse, predictedWeight, predictedWaist, predictedPulse

def readflowers(filename):
    f = open(filename, "r")
    csv_reader = csv.reader(f)
    type=[]
    predictiontype=[]
    for row in csv_reader:
        type.append(row[0])
        predictiontype.append(row[1])
    return type,predictiontype



