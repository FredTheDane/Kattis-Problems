import sys
for i, s in enumerate(sys.stdin):
    vals = list(map(int, s.strip().split()))
    print(abs(vals[0] - vals[1]))