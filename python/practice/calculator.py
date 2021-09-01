class Calculator:
    def __init__(self, first, second):
        self.first = first
        self.second = second
    def __init__(self):
        pass

    def start(self):

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
                print(a + b)
            elif par1 == "2":
                print(a - b)
            elif par1 == "3":
                print(a * b)
            elif par1 == "4":
                print(a / b)

            last = input("종료하려면 q 입력")
            if last == "q":
                break
cal1 = Calculator()
# cal1.start()
cal1.first=10
print(cal1.first)
cal1.param1=30
print(cal1.param1)