
def get_except_keyword(filename):
    keyword_list = list()
    with open(filename, encoding='utf-8') as f:
        for keyword in f.readlines():
            keyword_list.append(keyword.strip())
        print(keyword_list)
        return keyword_list
    
except_keyword = get_except_keyword("except_word.txt")


def do_except(text, exceptKeyword):
    summary = ''
    for word in exceptKeyword:
        text=text.replace(word,'')
        
    return text

print(do_except("이모티콘 김치 사진 안녕 서종현", except_keyword))

