import requests
import csv
import bs4
import pandas as pd
from tqdm import tqdm
import os
import time
from urllib.request import Request, urlopen
from urllib.parse import urlencode, quote_plus, unquote

# search_url = 'https://boardgamegeek.com/xmlapi/search?search='
rating_url = 'https://boardgamegeek.com/xmlapi2/thing?id='

search_url = 'https://boardgamegeek.com/xmlapi/search?search='

f = open('보드게임목록.csv','rt', encoding='UTF-8')
rdr = csv.reader(f)

# for line in rdr:
#     gamename = requests.get(search_url + line[1])
#     print(response.text)

gamename = 'Gloomhaven'
year = '2017'
response = requests.get(search_url + gamename)
content = response.text
xml_obj = bs4.BeautifulSoup(content,'lxml-xml')
rows = xml_obj.findAll('boardgame')
objectid = ''
usergame = []
username = []
userreview = []
for i in range(0,len(rows)):
    if rows[i].find('name').text == gamename and rows[i].find('yearpublished').text == year:
        boardgame = xml_obj.findAll('boardgame')
        game = boardgame[i]
        objectid = game['objectid']
        print(objectid)
        break
response = requests.get(rating_url + objectid + '&ratingcomments=1&page=1')
content = response.text
xml_obj = bs4.BeautifulSoup(content,'lxml-xml')
rows = xml_obj.find('comments')
count = int(int(rows['totalitems']) / 100) + 2
print(rows['totalitems'])

def getApi(rating_url, objectid, page) :
    return requests.get(rating_url + objectid + '&ratingcomments=1&page=' + page)

for i in tqdm(range(1,count)):
    while True:
        response = getApi(rating_url, objectid, str(i))
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
    info.to_csv('게임평점.csv', mode='w', header=False, index=False, encoding='utf-8-sig')
else:
    info.to_csv('게임평점.csv', mode='a', header=False, index=False, encoding='utf-8-sig')








