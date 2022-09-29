import csv
import pandas as pd
from tqdm import tqdm
import json

def knn(list):
  result = []
  for i in list:
    result.append(i['game'])
  result = dict(zip(range(1, len(result) + 1), result))
  return result