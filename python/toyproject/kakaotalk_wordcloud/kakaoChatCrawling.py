import matplotlib.font_manager as fm
from wordcloud import WordCloud

def get_except_keyword(filename):
    keyword_list = list()
    with open(filename, encoding='utf-8') as f:
        for keyword in f.readlines():
            keyword_list.append(keyword.strip())
        return keyword_list

def do_except(text, exceptKeyword):
    summary = ''
    for word in exceptKeyword:
        text=text.replace(word,'')
        
    return text
    
text = ""
except_keyword = get_except_keyword("./except_word.txt")
font_path = 'C:/Windows/Fonts/malgun.ttf'

with open("C:/Users/tjwhd/OneDrive/바탕 화면/sjh_C/work/data/kakao.txt", "r", encoding="utf-8") as f:
    lines = f.readlines()
    for line in lines[5:]:
        if '현 :' in line or '범 :' in line:
            text+=(do_except(line.split(' : ')[1], except_keyword))

wc = WordCloud(font_path=font_path, background_color="white", width=600, height=400)
wc.generate(text)
wc.to_file("result_review.png")