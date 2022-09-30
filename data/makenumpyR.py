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
arr = np.zeros((15242,420148), dtype=np.float16)


for line in tqdm(ldata):
  gameindex = data.index(line[0])
  userindex = user.index(line[1])

  if arr[gameindex][userindex] == 0:
    try :
      arr[gameindex][userindex] = line[2]
    except :
      arr[gameindex][userindex] = 0
  else : continue
  
info = pd.DataFrame(arr)

if not os.path.exists('ratingMatrixR.csv'):
  info.to_csv('ratingMatrixR.csv',  mode='w', header=True, index=False, encoding='utf-8', sep=',')  
else:
  info.to_csv('ratingMatrixR.csv',  mode='a', header=False, index=False, encoding='utf-8', sep=',')

exit()


  
