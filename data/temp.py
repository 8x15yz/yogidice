import csv
from tqdm import tqdm
import pandas as pd
import os

f = open('보드게임목록.csv','rt', encoding='UTF-8')
rdr = csv.reader(f, delimiter='|')
data = []
cnt = 0
for line in rdr:
  word = line

  title = word[1]
  if ',' in title:
    cnt = cnt + 1
  title = title.replace(',', '')
  word[1] = title
  
  info = pd.DataFrame(
    {"이름": [word[0]], "이름(eng)": [word[1]], "년도": [word[2]], "img": [word[3]], "점수(bl)": [word[4]], 
          "점수(user)": [word[5]], "분류" : [word[6]], "테마" : [word[7]], "진행방식" : [word[8]],
          "bggcode" : [word[9]], "최소인원" : [word[10]], "최대인원" : [word[11]], "시간" : [word[12]], "최소시간" : [word[13]], "최대시간" : [word[14]], "연령" : [word[15]], "난이도" : [word[16]]})
  if not os.path.exists('수정.csv'):
      info.to_csv('수정.csv',  mode='w', header=True, index=False, encoding='utf-8-sig', sep='|')  
  else:
      info.to_csv('수정.csv',  mode='a', header=False, index=False, encoding='utf-8-sig', sep='|')
print(cnt)