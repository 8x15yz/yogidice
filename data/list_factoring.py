import csv
from tqdm import tqdm
import pandas as pd
import os

f = open('보드게임목록.csv','rt', encoding='UTF-8')
rdr = csv.reader(f, delimiter='|')
ft = open('type.csv','rt', encoding='UTF-8')
rdt = csv.reader(ft, delimiter=',')
tdata = []
for i in rdt:
  tdata.append(i)
fc = open('카테고리.csv','rt', encoding='UTF-8')
rdc = csv.reader(fc, delimiter='|')
cdata = []
for i in rdc:
  cdata.append(i)
fm = open('메커니즘.csv','rt', encoding='UTF-8')
rdm = csv.reader(fm, delimiter='|')
mdata = []
for i in rdm:
  mdata.append(i)

url = 'https://boardlife.co.kr/'
index = 0
for line in rdr:
  if index == 0:
    index = index + 1
    continue
  index += 1
  result = ''
  if 'ImgNoImage' in line[3] :
    result = url + line[3]
  else :
    img = line[3].split('/')
    if not len(img[5].split('_')) == 1: 
      text = img[5].split('_')
      src = text[0] + text[len(text)-1]
      img[0] = url + img[0]
      img[5] = src
    else :
      result += url
    for i in img:
      result += i + '/'
    result = result[:-1]
   
  line[3] = result
  type = line[6]
  
  for t in tdata:
    if t[0] == type:
      line[6] = t[1]
     
  cate = line[7].split(',')
  result_c = ''
  for c in cate :
    for cd in cdata:
      if cd[1] == c:
        result_c += cd[0] + ','
        break
  result_c = result_c[:-1]
  if not result_c == '':
    line[7] = result_c
  
  mech = line[8].split(',')
  result_m = ''
  for m in mech :
    for md in mdata:
      if md[1] == m:
        result_m += md[0] + ','
        break
  result_m = result_m[:-1]
  if not result_m == '':
    line[8] = result_m

  
  info = pd.DataFrame(
    {"이름": [line[0]], "이름(eng)": [line[1]], "년도": [line[2]], "img": [line[3]], "점수(bl)": [line[4]], 
    "점수(user)": [line[5]], "분류" : [line[6]], "테마" : [line[7]], "진행방식" : [line[8]],
    "bggcode" : [line[9]], "최소인원" : [line[10]], "최대인원" : [line[11]], "시간" : [line[12]], "최소시간" : [line[13]], "최대시간" : [line[14]], "연령" : [line[15]], "난이도" : [line[16]]})
  if not os.path.exists('보드게임목록수정.csv'):
      info.to_csv('보드게임목록수정.csv',  mode='w', header=True, index=False, encoding='UTF-8', sep='|')  
  else:
      info.to_csv('보드게임목록수정.csv',  mode='a', header=False, index=False, encoding='UTF-8', sep='|')