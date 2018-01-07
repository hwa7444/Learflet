# -*- coding: ms949 -*-

import math, sys
from konlpy.tag import Twitter
import cx_Oracle

#�ڹٿ��� id �޾ƿ���
#user_id = sys.argv[1]

# Bayesian filter �Լ� ����



class BayesianFilter:
    
    def __init__(self):
        self.words = set() # ������ �ܾ� ���
        self.word_dict = {} # ī�װ������� ���� Ƚ�� ���
        self.category_dict = {} # ī�װ� ���� Ƚ�� ���

    # ���¼� �м��ϱ�
    def split(self, text):
        results = []
        twitter = Twitter()
        # �ܾ��� �⺻�� ���
        malist = twitter.pos(text, norm=True, stem=True)
        for word in malist:
            # ���/����/������ ���� ��󿡼� ����
            if not word[1] in ["Josa", "Eomi", "Punctuation"]:
                results.append(word[0])
        return results

    # �ܾ�� ī�װ��� ���� Ƚ�� ����
    def inc_word(self, word, category):
        # �ܾ ī�װ��� �߰��ϱ�
        if not category in self.word_dict:
            self.word_dict[category] = {}
        if not word in self.word_dict[category]:
            self.word_dict[category][word] = 0
        self.word_dict[category][word] += 1
        self.words.add(word)

    def inc_category(self, category):
        # ī�װ� ����ϱ�
        if not category in self.category_dict:
            self.category_dict[category] = 0
        self.category_dict[category] += 1

    # �ؽ�Ʈ �н��ϱ�
    def fit(self, text, category):
        word_list = self.split(text)
        for word in word_list:
            self.inc_word(word, category)
        self.inc_category(category)

    # �ܾ� ����Ʈ�� ���� �ű��
    def score(self, words, category):
        score = math.log(self.category_prob(category))
        for word in words:
            score += math.log(self.word_prob(word, category))
        return score

    # �����ϱ�
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

    # ī�װ� ������ �ܾ� ���� Ƚ�� ���ϱ�
    def get_word_count(self, word, category):
        if word in self.word_dict[category]:
            return self.word_dict[category][word]
        else:
            return 0

    # ī�װ� ���
    def category_prob(self, category):
        sum_categories = sum(self.category_dict.values())
        category_v = self.category_dict[category]
        return category_v/sum_categories

    # ī�װ� ������ �ܾ� ���� ���� ���
    def word_prob(self, word, category):
        n = self.get_word_count(word, category) + 1
        d = sum(self.word_dict[category].values()) + len(self.words)
        return n/d
#-------------------------------------------------------------------------------
# ���� ������ ������������ ����ϱ�

#from bayes import BayesianFilter
bf = BayesianFilter()

# �ؽ�Ʈ �н�
#for n in range(len(dummy["gender"])):
#    bf.fit("'%s','%s','%d','%d','%d'" %(dummy["gender"][n],dummy["age"][n],dummy["i1"][n],dummy["i2"][n],dummy["i3"][n]),dummy["c1"][n])


# ����

#DB���� �� �����ͼ� �н���Ű��



#DB select / �� ��������

conn = cx_Oracle.connect('beng/1234@localhost:1521/xe')
cur = conn.cursor()
cur.execute('SELECT * FROM analdata')

mem_test=cur.fetchall()
print(mem_test)
for result in mem_test:
    #�н���Ű�� �κ�
    bf.fit("'%s','%s','%s','%s','%s','%s','%s'" %(result[1],result[2],result[3],result[4],result[5],result[6],result[7]),result[8])

cur.close()
conn.close()

#�ش� ����� ������ �����ͼ� �м� / 

#����� data id�� ��������
conn = cx_Oracle.connect('beng/1234@localhost:1521/xe')
cur = conn.cursor()

#��� �� �־�α�
user_id = 'a'
cur.execute('SELECT * FROM analdata where id=(:k)',k=user_id)

mem_test=cur.fetchall()
print(mem_test)
for result in mem_test:
    #�н���Ű�� �κ�
    bf.fit("'%s','%s','%s','%s','%s','%s','%s'" %(result[1],result[2],result[3],result[4],result[5],result[6],result[7]),result[8])

cur.close()
conn.close()


pre, scorelist = bf.predict("W earlyTwnty 5 5 4")
print("��� =", pre)
print(scorelist)

#Ʃ�� �������� ����

changer = ""

for i in range(len(scorelist)):
    for j in range(len(scorelist)):
        if scorelist[i][1] > scorelist[j][1]:
            changer = scorelist[i]
            scorelist[i] = scorelist[j]
            scorelist[j] = changer
            

#DB�� coupon ranking �ֱ�

conn = cx_Oracle.connect('beng/1234@localhost:1521/xe')
cur = conn.cursor()


cur.execute("insert into couponrank values('%s','%s','%s','%s','%s','%s')" %(scorelist[0][0],scorelist[1][0],scorelist[2][0],scorelist[3][0],scorelist[4][0],scorelist[5][0]))

conn.commit()

cur.close()
conn.close()
            
