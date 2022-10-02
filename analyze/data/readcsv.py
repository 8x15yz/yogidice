import csv
import pandas as pd
df = pd.read_csv('./matrix.csv')
index = 0
data = []

for i in range(0,len(df)):
    temp = df['data'][i][1:-1].split(', ')
    tempstr = ''
    for j in temp :
        tempstr += j + ','
    data.append(tempstr[:-1])
info = pd.DataFrame(
    {"data": data})

info.to_csv('newMatrix.csv',  mode='w', header=True, index=False, encoding='utf-8-sig', sep='|')  

