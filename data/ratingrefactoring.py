import csv
from tqdm import tqdm
import pandas as pd
import os
import re
f = open('all_rating.csv','rt', encoding='UTF-8')
rdr = csv.reader(f, delimiter=',')
data1 = []
data2 = []
data3 = []
for line in tqdm(rdr):
    data1.append(line[1])
    data2.append(line[2])
    data3.append(line[3])


info = pd.DataFrame(
    {"code": data1 , "name" : data2, "score" : data3 })
if not os.path.exists('allRating.csv'):
    info.to_csv('allRating.csv',  mode='w', header=True, index=False, encoding='utf-8', sep='|')  
else:
    info.to_csv('allRating.csv',  mode='a', header=False, index=False, encoding='utf-8', sep='|')

