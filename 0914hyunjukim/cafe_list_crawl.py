
import time
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver import ActionChains
import csv
from tqdm import tqdm


options = webdriver.ChromeOptions()
options.add_experimental_option("excludeSwitches", ["enable-logging"])
driver = webdriver.Chrome(options=options)
url = 'https://map.kakao.com/'
driver.get(url)

elem = driver.find_element(By.ID, 'search.keyword.query')
elem.send_keys("보드카페")
elem.send_keys("\n")

time.sleep(2)

# lists = driver.find_element(By.ID, 'info.search.place.list')
lists = driver.find_element(By.CSS_SELECTOR, 'ul')
print(lists.text)

li = lists.find_elements(By.CSS_SELECTOR, 'li')
for i in li:
    head_item = i.find_element(By.CLASS_NAME, 'head_item')
    # print(head_item)

# boardCafe = li.find_element(By.CLASS_NAME, 'head_item').find_element(By.CSS_SELECTOR, 'span')

# ActionChains(driver).move_to_element(boardCafe).click()







