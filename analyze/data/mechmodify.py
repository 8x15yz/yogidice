import csv
from tqdm import tqdm
import pandas as pd
import os

f = open('메커니즘.csv','rt', encoding='UTF-8')
rdr = csv.reader(f, delimiter='|')

data = []
for line in rdr :
    d1 = line[0]
    d2 = line[2]
    info = pd.DataFrame(
        {"code": [d1] , "type" : [d2] })
    if not os.path.exists('게임메카db.csv'):
        info.to_csv('게임메카db.csv',  mode='w', header=True, index=False, encoding='utf-8', sep='|')  
    else:
        info.to_csv('게임메카db.csv',  mode='a', header=False, index=False, encoding='utf-8', sep='|')

