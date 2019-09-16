import sys
data = {}
for i, x in enumerate(sys.stdin):
    data[i] = sum(list(map(int, x.split())))
best = max(zip(data.values(), data.keys()))
print(f"{best[1]+1} {best[0]}")