import csv
from tqdm import tqdm
import pandas as pd
import os

df = pd.read_csv('./data/newMatrixR.csv', sep='|')
data =[]


for i in range(0, 15242):
  temp = df['data'][i][1:-1].split(', ')
  tempstr = ''
  for i in temp:
    tempstr += str(i) + ','
  tempstr = tempstr[:-1]
  data.append(tempstr)

  
info = pd.DataFrame(
  {"data": data })

info.to_csv('newMatrixR2.csv',  mode='w', header=True, index=False, encoding='utf-8-sig', sep='|')  
