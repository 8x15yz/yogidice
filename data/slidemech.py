import csv
from tqdm import tqdm
import pandas as pd
import os

f = open('보드게임목록(사용).csv','rt', encoding='UTF-8')
rdr = csv.reader(f, delimiter='|')
fl = open('메커니즘.csv', 'rt', encoding='UTF-8')
rdl = csv.reader(fl, delimiter='|')
data = []
for line in rdl :
  data.append(line)
typelist = []
codelist = []
index = 0
for line in rdr:

    word  = line[8].split(',')
    for w in word:

      for i in range(1,len(data)):
        if data[i][0] == w:
          typelist.append(i)
          codelist.append(index)

    index = index + 1
info = pd.DataFrame(
    {"code": codelist , "type" : typelist })
if not os.path.exists('게임메카.csv'):
  info.to_csv('게임메카.csv',  mode='w', header=True, index=False, encoding='utf-8', sep=',')  
else:
  info.to_csv('게임메카.csv',  mode='a', header=False, index=False, encoding='utf-8', sep=',')

