import csv
from tqdm import tqdm
import pandas as pd
import os

f = open('temp.csv','rt', encoding='UTF-8')
rdr = csv.reader(f)
list = []
for line in rdr:
    list.append(line)

f2 = open('보드게임목록.csv', 'rt', encoding='UTF-8')
rdrl = csv.reader(f2)
data = []
for line in rdrl:
    data.append(line)

index = 1
result = []
for i in tqdm(range(1,len(list))):
    word = str(list[i]).split(',')
    while (True) :
        wordname = word[0][2:]
        wordname = wordname[:-1]
        username = word[1][2:]
        username = username[:-1]
        userscore = word[2][2:]
        userscore = userscore[:-2]

        game = str(data[index])
        gamename = game.split('|')[1]

        if gamename == wordname:
            gamecode = game.split('|')[9]
            info = pd.DataFrame(
            {"game": [wordname], "code": [gamecode],  "user": [username], "score": [userscore]})
            if not os.path.exists('평점수정1.csv'):
                info.to_csv('평점수정1.csv',  mode='w', header=True, index=False, encoding='utf-8-sig', sep=',')  
            else:
                info.to_csv('평점수정1.csv',  mode='a', header=False, index=False, encoding='utf-8-sig', sep=',')
            break
        else : index += 1
   


