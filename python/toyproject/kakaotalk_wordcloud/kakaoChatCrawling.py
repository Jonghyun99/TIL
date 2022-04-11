from wordcloud import WordCloud
import matplotlib.pyplot as plt
from collections import Counter
from PIL import Image
import numpy as np
from konlpy.tag import Okt

#단어 예외 처리 로직
def get_except_keyword(filename):
    keyword_list = list()
    with open(filename, encoding='utf-8') as f:
        for keyword in f.readlines():
            keyword_list.append(keyword.strip())
        return keyword_list

#단어 예외처리 실행부
def do_except(text, exceptKeyword):
    summary = ''
    for word in exceptKeyword:
        text=text.replace(word,'')
        
    return text

text = ""
person = 'jongmin'
except_keyword = get_except_keyword("./except_word.txt")
font_path = 'C:/Windows/Fonts/malgun.ttf'

#파일경로 입력
with open("C:/Users/tjwhd/OneDrive/바탕 화면/sjh_C/work/data/kakao_{}.txt".format(person), "r", encoding="utf-8") as f:
    lines = f.readlines()
    for line in lines[5:]:
        #모바일카톡으로 받았을 땐 아래 줄 주석 제거, 조건문에는 채팅친사람 이름 끝 자 넣어주면 됨
        # if '현 :' in line or '범 :' in line:
        #     text+=(do_except(line.split(':')[2], except_keyword))
            
        #PC카톡은 아래 줄 주석 제거
        if '] [' in line:
            text+=(do_except(line.split(']')[2], except_keyword))

# okt 객체 생성
okt = Okt()

#명사만 분리
noun = okt.nouns(text)

#한글자 명사 제거
for i,v in enumerate(noun):
    if len(v)<2:
        noun.pop(i)

#명사 개수 세기
count = Counter(noun)
f.close()

#명사 빈도 수 계산한 리스트
noun_list = count.most_common(100)


#워드클라우드 객체 생성
wc = WordCloud(font_path=font_path, background_color="white", width=600, height=400)


wc.generate_from_frequencies(dict(noun_list))
wc.to_file("./result_folder/result_review{}.png".format(person))
print("생성완료!")
