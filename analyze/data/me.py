import csv
from tqdm import tqdm
import pandas as pd
import os

f = open('메커.csv','rt', encoding='UTF-8')
rdr = csv.reader(f,delimiter="|")

for line in rdr:
  print(line)
  word = line[0].split('\t')


  info = pd.DataFrame(
    {"name": [word[1]] , "ename" : [word[0]], "sort" : [word[2]]})
  if not os.path.exists('수정메커.csv'):
    info.to_csv('수정메커.csv',  mode='w', header=True, index=False, encoding='utf-8-sig', sep='|')  
  else:
    info.to_csv('수정메커.csv',  mode='a', header=False, index=False, encoding='utf-8-sig', sep='|')