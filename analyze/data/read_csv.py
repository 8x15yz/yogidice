import csv
from tqdm import tqdm
import pandas as pd
import os
import numpy as np
import pyarrow as pa

import pyarrow.parquet as pq 

arr = np.zeros((15243,420148), int)
for i in range(0,1):
  # file = 'ratingMatrix00' + str(i) + '.csv'
  file = 'ratingMatrix.csv'
  f = open(file,'rt', encoding='UTF-8')
  rdr = csv.reader(f, delimiter=',')
  next(rdr)
  cnt = 0
  for i in range(0,420148):
    arr[0][i] = cnt
    cnt += 1
  index = 1
  for line in tqdm(rdr):
    for i in range(0,len(line)):
      arr[index][i] = line[i]
    index += 1
df = pd.DataFrame(arr)
# df.to_parquet('data3.parquet', engine='pyarrow', index=False) 
table_from_pandas = pa.Table.from_pandas(df, preserve_index=False) 
pq.write_table(table_from_pandas, 'data.parquet')


