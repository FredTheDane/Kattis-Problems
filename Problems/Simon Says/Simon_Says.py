import sys
for i, x in enumerate(sys.stdin):
    if x[:10] == "Simon says":
        print(x[10:])