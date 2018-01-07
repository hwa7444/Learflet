# -*- coding: ms949 -*-

import math, sys
from konlpy.tag import Twitter
import cx_Oracle

#자바에서 id 받아오기
#user_id = sys.argv[1]

# Bayesian filter 함수 정의



class BayesianFilter:
    
    def __init__(self):
        self.words = set() # 출현한 단어 기록
        self.word_dict = {} # 카테고리마다의 출현 횟수 기록
        self.category_dict = {} # 카테고리 출현 횟수 기록

    # 형태소 분석하기
    def split(self, text):
        results = []
        twitter = Twitter()
        # 단어의 기본형 사용
        malist = twitter.pos(text, norm=True, stem=True)
        for word in malist:
            # 어미/조사/구두점 등은 대상에서 제외
            if not word[1] in ["Josa", "Eomi", "Punctuation"]:
                results.append(word[0])
        return results

    # 단어와 카테고리의 출현 횟수 세기
    def inc_word(self, word, category):
        # 단어를 카테고리에 추가하기
        if not category in self.word_dict:
            self.word_dict[category] = {}
        if not word in self.word_dict[category]:
            self.word_dict[category][word] = 0
        self.word_dict[category][word] += 1
        self.words.add(word)

    def inc_category(self, category):
        # 카테고리 계산하기
        if not category in self.category_dict:
            self.category_dict[category] = 0
        self.category_dict[category] += 1

    # 텍스트 학습하기
    def fit(self, text, category):
        word_list = self.split(text)
        for word in word_list:
            self.inc_word(word, category)
        self.inc_category(category)

    # 단어 리스트에 점수 매기기
    def score(self, words, category):
        score = math.log(self.category_prob(category))
        for word in words:
            score += math.log(self.word_prob(word, category))
        return score

    # 예측하기
    def predict(self, text):
        best_category = None
        max_score = -sys.maxsize
        words = self.split(text)
        score_list = []
        for category in self.category_dict.keys():
            score = self.score(words, category)
            score_list.append((category, score))
            if score > max_score:
                max_score = score
                best_category = category
        return best_category, score_list

    # 카테고리 내부의 단어 출현 횟수 구하기
    def get_word_count(self, word, category):
        if word in self.word_dict[category]:
            return self.word_dict[category][word]
        else:
            return 0

    # 카테고리 계산
    def category_prob(self, category):
        sum_categories = sum(self.category_dict.values())
        category_v = self.category_dict[category]
        return category_v/sum_categories

    # 카테고리 내부의 단어 출현 비율 계산
    def word_prob(self, word, category):
        n = self.get_word_count(word, category) + 1
        d = sum(self.word_dict[category].values()) + len(self.words)
        return n/d
#-------------------------------------------------------------------------------
# 직접 정의한 베이지안필터 사용하기

#from bayes import BayesianFilter
bf = BayesianFilter()

# 텍스트 학습
#for n in range(len(dummy["gender"])):
#    bf.fit("'%s','%s','%d','%d','%d'" %(dummy["gender"][n],dummy["age"][n],dummy["i1"][n],dummy["i2"][n],dummy["i3"][n]),dummy["c1"][n])


# 예측

#DB에서 값 가져와서 학습시키기



#DB select / 값 가져오기

conn = cx_Oracle.connect('beng/1234@localhost:1521/xe')
cur = conn.cursor()
cur.execute('SELECT * FROM analdata')

mem_test=cur.fetchall()
print(mem_test)
for result in mem_test:
    #학습시키는 부분
    bf.fit("'%s','%s','%s','%s','%s','%s','%s'" %(result[1],result[2],result[3],result[4],result[5],result[6],result[7]),result[8])

cur.close()
conn.close()

#해당 사용자 데이터 가져와서 분석 / 

#사용자 data id로 가져오기
conn = cx_Oracle.connect('beng/1234@localhost:1521/xe')
cur = conn.cursor()

#잠시 값 넣어두기
user_id = 'a'
cur.execute('SELECT * FROM analdata where id=(:k)',k=user_id)

mem_test=cur.fetchall()
print(mem_test)
for result in mem_test:
    #학습시키는 부분
    bf.fit("'%s','%s','%s','%s','%s','%s','%s'" %(result[1],result[2],result[3],result[4],result[5],result[6],result[7]),result[8])

cur.close()
conn.close()


pre, scorelist = bf.predict("W earlyTwnty 5 5 4")
print("결과 =", pre)
print(scorelist)

#튜플 내림차순 정렬

changer = ""

for i in range(len(scorelist)):
    for j in range(len(scorelist)):
        if scorelist[i][1] > scorelist[j][1]:
            changer = scorelist[i]
            scorelist[i] = scorelist[j]
            scorelist[j] = changer
            

#DB에 coupon ranking 넣기

conn = cx_Oracle.connect('beng/1234@localhost:1521/xe')
cur = conn.cursor()


cur.execute("insert into couponrank values('%s','%s','%s','%s','%s','%s')" %(scorelist[0][0],scorelist[1][0],scorelist[2][0],scorelist[3][0],scorelist[4][0],scorelist[5][0]))

conn.commit()

cur.close()
conn.close()
            
