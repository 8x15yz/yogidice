import csv
from tqdm import tqdm
import pandas as pd
import os

f = open('mechanism.csv','rt', encoding='UTF-8')
rdr = csv.reader(f)

for line in rdr:
  cate_kor = line[0].split('(')[0][:-1]
  cate_eng = line[0].split('(')[1][:-1]
  print(cate_kor + ':' + cate_eng)
  info = pd.DataFrame(
    {"이름": [cate_kor], "이름(eng)": [cate_eng]})
  if not os.path.exists('메커니즘.csv'):
      info.to_csv('메커니즘.csv',  mode='w', header=False, index=False, encoding='utf-8-sig', sep='|')  
  else:
      info.to_csv('메커니즘.csv',  mode='a', header=False, index=False, encoding='utf-8-sig', sep='|')