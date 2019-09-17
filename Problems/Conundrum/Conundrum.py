import sys

line = sys.stdin.readline().strip()
n = 0
s = "PER"
for i,x in enumerate(line):
    if x != s[i%3]:
        n += 1
print(n)