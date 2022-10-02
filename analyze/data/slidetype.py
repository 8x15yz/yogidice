import csv
from tqdm import tqdm
import pandas as pd
import os

f = open('보드게임목록(사용).csv','rt', encoding='UTF-8')
rdr = csv.reader(f, delimiter='|')
fl = open('타입.csv', 'rt', encoding='UTF-8')
rdl = csv.reader(fl)
data = []
for line in rdl :
  data.append(line)
typelist = []
codelist = []
index = 0
for line in rdr:

    word  = line[6]
    for i in range(1,len(data)):
      if data[i][1] == word:
        word = i
    typelist.append(word)
    codelist.append(index)

    index = index + 1
info = pd.DataFrame(
    {"code": codelist , "type" : typelist })
if not os.path.exists('게임타입.csv'):
  info.to_csv('게임타입.csv',  mode='w', header=True, index=False, encoding='utf-8', sep=',')  
else:
  info.to_csv('게임타입.csv',  mode='a', header=False, index=False, encoding='utf-8', sep=',')

