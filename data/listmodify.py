import csv
from tqdm import tqdm
import pandas as pd
import os

f = open('보드게임목록(사용).csv','rt', encoding='UTF-8')
rdr = csv.reader(f, delimiter='|')

data = []


for line in rdr:
    
   
  
  info = pd.DataFrame(
    {"이름": [line[0]], "이름(eng)": [line[1]], "년도": [line[2]], "img": [line[3]], "점수(bl)": [line[4]], 
    "점수(user)": [line[5]], "bggcode" : [line[9]], "최소인원" : [line[10]], "최대인원" : [line[11]], "시간" : [line[12]], "최소시간" : [line[13]], "최대시간" : [line[14]], "연령" : [line[15]], "난이도" : [line[16]]})
  if not os.path.exists('보드게임목록db.csv'):
      info.to_csv('보드게임목록db.csv',  mode='w', header=True, index=False, encoding='UTF-8', sep='|')  
  else:
      info.to_csv('보드게임목록db.csv',  mode='a', header=False, index=False, encoding='UTF-8', sep='|')