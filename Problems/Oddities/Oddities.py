import sys

for i, x in enumerate(sys.stdin):
    number = int(x)
    if (i != 0):
        if (number % 2 == 0):
            print(f"{number} is even")
        else:
            print(f"{number} is odd")