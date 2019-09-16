import sys
from math import sin,ceil,radians

for i, x in enumerate(sys.stdin):
    vals = list(map(int, x.split()))
    C = radians(90)
    A = radians(vals[1])
    a = vals[0]
    c = (sin(C)*a)/(sin(A))
    print(f"{ceil(c)}")