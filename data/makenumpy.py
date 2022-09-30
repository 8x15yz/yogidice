import csv
from tqdm import tqdm
import pandas as pd
import os

f = open('사람.csv','rt', encoding='UTF-8')
rdr = csv.reader(f)

index = 0

for line in rdr :
  print(line)
  index += 1
  if index == 4:
    break
