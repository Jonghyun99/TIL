def add(a, b):
    return a+b

def subtract(a,b):
    return a-b

def multiple(a,b):
    return a*b

def div(a,b):
    return a/b
while True:
    prompt = """
    숫자 입력

    1. add
    2. subtract
    3. multiple
    4. div

    input: 
    """
    print(prompt)
    par1 = input()

    a = input("첫 번째 숫자: ")
    b = input("두 번째 숫자: ")
    a=int(a)
    b=int(b)

    if par1 == "1":
        print(add(a, b))
    elif par1 == "2":
        print(subtract(a,b))
    elif par1 == "3":
        print(multiple(a,b))
    elif par1 == "4":
        print(div(a,b))

    last = input("종료하려면 q 입력")
    if last == "q":
        break