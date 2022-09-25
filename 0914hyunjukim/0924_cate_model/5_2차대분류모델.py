import csv
import pandas as pd
from tqdm import tqdm


mec_score_board = open("mec_score_board.csv", "r", newline='', encoding='utf-8')
mec_score_board = csv.reader(mec_score_board)

obj_score = []

for i in mec_score_board:
    if i[1] == '167791':
        obj_score = i[2:]
        break
# print(obj_score)

calc_score_abs = pd.DataFrame({'no': [], 'economic' : [], 'figure' : [], 'stratgy' : [], 'rule' : [], 'puzzle' : [], 'party' : [], 'sum_score' : []})

cnt = 0
for score in tqdm(mec_score_board):
    cnt +=1
    arr = [score[1]]
    scr = score[2:]
    sum_score = 0
    for i in range(6):
        column_sc = 5-(abs(float(scr[i])-float(obj_score[i]))*0.1*5)
        arr.append(column_sc)
        sum_score += column_sc
    arr.append(sum_score)
    # print(arr)
    calc_score_abs.loc[cnt] = arr

calc_score_abs.to_csv("5_model_score_calc.csv", mode='w')

# print(obj_score)