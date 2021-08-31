f = open("./새파일.txt", "r")
lines = f.readlines()
for line in lines:
    line=line.strip()
    print(line)
f.close()