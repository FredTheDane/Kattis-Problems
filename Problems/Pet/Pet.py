import sys

best = 0
bestcontestant = -1
for i, x in enumerate(sys.stdin):
    vals = map(int, x.split())
    score = sum(vals)
    if (bestcontestant == -1):
        bestcontestant = i+1
        best = score
    if (score > best):
        bestcontestant = i+1
        best = score
print(f"{bestcontestant} {best}")