import pyarrow.parquet as pq 
import pandas as pd
import csv
import tqdm
file = 'ratingMatrix.csv'
f = open(file,'rt', encoding='UTF-8')
rdr = csv.reader(f, delimiter=',')
next(rdr)

data =[]
cnt = 1
for line in rdr:
  temp = []

  for i, v in enumerate(line):
    if v == '1':
      temp.append(i)
  data.append(temp) 
  cnt += 1
  print(cnt)
  
info = pd.DataFrame(
    {"data": data })

info.to_csv('matrix.csv',  mode='w', index=False, encoding='utf-8', sep=',')  
