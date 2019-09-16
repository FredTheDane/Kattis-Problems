import sys

for i, x in enumerate(sys.stdin):
    if (i != 0):
        vals = list(map(int, x.split()))
        r = vals[0]
        e = vals[1]
        c = vals[2]
        if ((e - c) > r):
            print("advertise")
        elif (e - c == r):
            print("does not matter")
        else:
            print("do not advertise")