import requests
import csv
import bs4
import pandas as pd
from tqdm import tqdm
import os
import time
from urllib.request import Request, urlopen
from urllib.parse import urlencode, quote_plus, unquote
from time import sleep

rating_url = 'https://boardgamegeek.com/xmlapi2/thing?id='

search_url = 'https://boardgamegeek.com/xmlapi/search?search='

f = open('보드게임목록.csv','rt', encoding='UTF-8')
rdr = csv.reader(f)
data = []
for line in rdr:
    data.append(line)
def main(startnum, endnum):
    startnum = int(startnum)
    endnum = int(endnum)
    gamename = ''
    gamecode = ''
    
    for i in range(startnum, endnum + 1):
        usergame = []
        username = []
        userreview = []
        tempstr = str(data[i])
        gamename = tempstr.split('|')[1]
        gamecode = tempstr.split('|')[9]
        
        print(gamename + '|' + gamecode)

        response = requests.get(rating_url + gamecode + '&ratingcomments=1&page=1')
        content = response.text
        xml_obj = bs4.BeautifulSoup(content,'lxml-xml')
        rows = xml_obj.find('comments')
        count = int(int(rows['totalitems']) / 100) + 2
        print(rows['totalitems'])

        for i in tqdm(range(1,count)):
            while True:
                response = getApi(rating_url, gamecode, str(i))
                content = response.text
                if response.status_code == 429:
                    time.sleep(5)
                else: break

            xml_obj = bs4.BeautifulSoup(content,'lxml-xml')
            rows = xml_obj.findAll('comment')
            if not rows: print(response)

            for j in rows:
                usergame.append(gamename)
                username.append(j['username'])
                userreview.append(j['rating'])
        info = pd.DataFrame({"이름": usergame, "user": username, "평점": userreview})
        if not os.path.exists('게임평점.csv'):
            info.to_csv('게임평점.csv', mode='w', header=True, index=False, encoding='utf-8-sig')
        else:
            info.to_csv('게임평점.csv', mode='a', header=False, index=False, encoding='utf-8-sig')
        if i == endnum :
            break
        sleep(5)        
def getApi(rating_url, gamecode, page) :
    return requests.get(rating_url + gamecode + '&ratingcomments=1&page=' + page)


    

startnum = input()
endnum = input()
main(startnum, endnum)







