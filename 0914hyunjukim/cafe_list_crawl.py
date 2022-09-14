
import time
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
import csv
from tqdm import tqdm


options = webdriver.ChromeOptions()
options.add_experimental_option("excludeSwitches", ["enable-logging"])
driver = webdriver.Chrome(options=options)
url = 'https://map.kakao.com/'
driver.get(url)

elem = driver.find_element(By.ID, 'search.keyword.query')
elem.send_keys("보드")
elem.send_keys("\n")

time.sleep(2)

lists = driver.find_element(By.ID, 'info.search.place.list')

li = lists.find_element(By.CSS_SELECTOR, 'li')

li.find_element(By.CLASS_NAME, 'head_item').find_element(By.CSS_SELECTOR, 'span').send_keys(Keys.ENTER)






# file = open("cafe_list.csv", "w", newline='')
# cafe_list = csv.writer(file)
