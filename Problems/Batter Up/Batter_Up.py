import sys
for i, s in enumerate(sys.stdin):
    if i != 0:
        vals = list(map(int, s.split()))
        n = 0
        s = 0
        for val in vals:
            if val != -1:
                n += 1
                s += val
        print(s/n)