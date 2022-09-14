from time import sleep
import pandas as pd
from selenium import webdriver as wb
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.keys import Keys
from bs4 import BeautifulSoup as bs
import lxml
import re

def main():
    driver = wb.Chrome("./chromedriver")
    driver.get("https://boardlife.co.kr/rank.php")

    title_kor = []
    title_eng = []
    year = []
    score_bl = []
    score_user = []
    gametype = []
    gametheme = []
    mechanisms = []
    image = []

    index = True
    cnt = 0
    while index:
        soup = bs(driver.page_source, 'html.parser')
        page_bar = soup.find('div', class_="paging-btn")
        pages = page_bar.find_all('a')
        page_now = soup.find('a', class_="now").text
        page = []
        for i in pages:
            page.append(i.text)
        index_now = page.index(page_now)
        if '다음' in page:
            index_next = page.index('다음')
        else:
            index_next = len(page)
            if index_now == len(page) -1 :
                index = False

        table_title = soup.find_all('div', class_="storage-title")
        table_year = soup.find_all('div', class_="storage-year")
        table_score_bl = soup.find_all('div', class_="storage-avg")
        table_score_user = soup.find_all('div', class_="storage-point")
        table_image = soup.select(".storage-thumb > img")
        for i in table_image:
            image.append(i['src'])
        for i in range(0, len(table_title)):
            cnt = cnt + 1
            print('no : '+ str(cnt))
            title_kor.append(table_title[i].text)
            year.append(re.sub(r'[^0-9]', '',table_year[i].text))
            score_bl.append(table_score_bl[i].text)
            score_user.append(table_score_user[i].text)
            driver.find_element("xpath", '/html/body/div[5]/div[1]/div[2]/div[2]/a[' + str(i+1) + ']').click()
            driver.find_element("xpath", '//*[@id="game-overview-box"]/div[2]/div[1]/div[6]/a').click()
            driver.switch_to.window(driver.window_handles[-1])
            soup = bs(driver.page_source, 'html.parser')
            eng_title = soup.select_one('h1 > a').text
            eng_title = eng_title.strip()
            title_eng.append(eng_title)
            print('title : '+eng_title)
            # temptype = soup.select_one('ul > li > div.feature-description > span.ng-scope > a').text
            # gametype.append(temptype)
           
            # print('type : '+temptype)
            driver.find_element("xpath", '//*[@id="mainbody"]/div[2]/div/div[1]/div[2]/ng-include/div/div/ui-view/ui-view/div/overview-module/description-module/div/div[2]/div/div[1]/classifications-module/div/div[2]/ul/li[1]/div[2]/button').click()
            sleep(0.5)
            driver.switch_to.window(driver.window_handles[-1])
            soup = bs(driver.page_source, 'html.parser')
            typetable = soup.select('tbody > tr')
            temptype = ''
            for l in typetable:
                soup = bs(str(l), 'lxml')
                per = soup.select_one('td > span')
                if per['class'] == 'ng-binding is-winner':
                    temptype = soup.select_one('th > a').text
                    gametype.append(temptype)
            print('type : ' + temptype)
            driver.switch_to.window(driver.window_handles[1])
            driver.find_element("xpath", '/html/body/div[1]/div/div/div[2]/button[2]').click()
            sleep(0.5)
            driver.find_element("xpath", '//*[@id="primary_tabs"]/ul/li[12]/button').click()
            sleep(0.5)
            driver.find_element("xpath", '/html/body/div[4]/div/div[1]/ul/li[9]/a').click()
            sleep(0.5)
            soup = bs(driver.page_source, 'html.parser')
            tempcate = soup.find_all('li', class_='outline-item ng-scope')
            tempstr = ''
            soup = bs(str(tempcate[13]), 'lxml')
            temp = soup.select('a')
            for l in temp:
                tempstr = tempstr + l.text + ','
            tempstr = tempstr[1:]
            tempstr = tempstr[:-1]
            gametheme.append(tempstr)
            print('theme : '+ tempstr)
            
            tempstr = ''
            soup = bs(str(tempcate[14]), 'lxml')
            temp = soup.select('a')
            for l in temp:
                tempstr = tempstr + l.text + ','
            tempstr = tempstr[1:]
            tempstr = tempstr[:-1]
            mechanisms.append(tempstr)
            print('mechanisms : ' + tempstr)
            print('================================================================================')

            driver.close()
            driver.switch_to.window(driver.window_handles[0])
            driver.back()
        if index :
            if int(index_now) + 1 < int(index_next):
                next_index = int(index_now) + 2
                driver.find_element("xpath", '/html/body/div[5]/div[1]/div[3]/div/a[' + str(next_index) + ']').click()
            elif int(index_now) + 1 == int(index_next):
                next_next = int(index_next) + 1
                driver.find_element("xpath", '/html/body/div[5]/div[1]/div[3]/div/a[' + str(next_next) + ']').click()

    # '''csv 저장'''
    info = pd.DataFrame(
        {"이름": title_kor, "이름(eng)": title_eng, "년도": year, "img": image, "점수(bl)": score_bl, "점수(user)": score_user, "분류" : gametype, "테마" : gametheme, "진행방식" : mechanisms})
    info.to_csv('보드게임목록.csv', header=False, index=False, encoding='utf-8-sig')
    return info


main()