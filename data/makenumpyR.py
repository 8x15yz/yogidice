import csv
from tqdm import tqdm
import pandas as pd
import os
import numpy as np

f = open('./data/사람.csv','rt', encoding='UTF-8')
rdr = csv.reader(f)
user = []
for line in rdr:
  user.append(line[0])
fl = open('./data/allRating.csv', 'rt', encoding='UTF-8')
rdl = csv.reader(fl, delimiter= '|')
ldata = []
for line in rdl:
  ldata.append(line)
fb = open('./data/code&id.csv', 'rt', encoding='UTF-8')
rdb = csv.reader(fb, delimiter= ',')
data = []
for line in rdb:
  data.append(line[0])

index = 0
# arr = np.zeros((15242), dtype=np.float16)
arr = [ [] for _ in range(15242)]

for line in tqdm(ldata):
  gameindex = data.index(line[0])
  try :
    arr[gameindex].append(float(line[2]))
  except :
    arr[gameindex].append(0)
  
print(arr[1])  
info = pd.DataFrame({"data " :arr})

info.to_csv('ratingMatrixR.csv',  mode='w', header=True, index=False, encoding='utf-8', sep='|')  

exit()


  
