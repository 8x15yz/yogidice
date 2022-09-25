import csv
import pandas as pd
from tqdm import tqdm

## 
mec_score_board = open("mec_score_board.csv", "r", newline='', encoding='utf-8')
mec_score_board = list(csv.reader(mec_score_board))


obj_score = []

for i in mec_score_board:
    if i[1] == '167791':
        obj_score = i[2:]
        break
# print(obj_score)

calc_score_abs = pd.DataFrame({'no': [], 'economic' : [], 'figure' : [], 'stratgy' : [], 'rule' : [], 'puzzle' : [], 'party' : [], 'sum_score' : []})


for score in tqdm(range(len(mec_score_board))):
    # print(mec_score_board[score])
    arr = [mec_score_board[score][1]]
    scr = mec_score_board[score][2:]
    sum_score = 0
    for i in range(6):
        column_sc = 5-(abs(float(scr[i])-float(obj_score[i]))*0.1*5)
        arr.append(column_sc)
        sum_score += column_sc
    arr.append(sum_score)
    # print(arr)
    calc_score_abs.loc[score] = arr

print(calc_score_abs)



mec_main_all = open('mec_main_all.csv', "r", newline='', encoding='utf-8')
game_mec_info = open('game_mec_info.csv', "r", newline='', encoding='utf-8')
mec_main_all = list(csv.reader(mec_main_all))
game_mec_info = list(csv.reader(game_mec_info))

model_jackard = []

obj_score = []

for info in game_mec_info:
    if info[0] == '167791':
        obj_score = info[1:]
        if '' in obj_score:
            obj_score.remove('')
        obj_score = set(obj_score)
        break
# print(obj_score)
# print(game_mec_info[4])

for score in tqdm(range(len(game_mec_info))):
    if '' in game_mec_info[score]:
        game_mec_info[score].remove('')
    info = set(game_mec_info[score][1:])
    # print((len(obj_score.intersection(info))/len(obj_score.union(info)))*70)
    model_jackard.append((len(obj_score.intersection(info))/len(obj_score.union(info)))*70)

model_jackard = pd.DataFrame(model_jackard)
print(model_jackard)
