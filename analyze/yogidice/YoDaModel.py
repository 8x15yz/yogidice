import csv
import pandas as pd
from tqdm import tqdm
import numpy as np

def search(list):
  csv_data = np.loadtxt('../../data/ratingMatrix.csv', delimiter=',')
  csv_rating = np.loadtxt('../../data/ratingMatrixR.csv', delimiter=',')
  user = np.zeros(15242, int)
  for i in list:
    user[int(i) -1] = 1
  cal = user @ csv_data
  index = np.argsort(cal)[::-1][:10]
  result = []
  for i in index:
    
  return index



