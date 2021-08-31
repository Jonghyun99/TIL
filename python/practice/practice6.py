f = open("새파일.txt", "w")
for i in range(1,11):
    data = "{0}번 째 줄입니다.\n".format(i)
    f.write(data)
f.close()