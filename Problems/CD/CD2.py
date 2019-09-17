import sys
while True:
    n, m = map(int, sys.stdin.readline().split())
    if n == 0 and m == 0:
        break
    a = set(int(sys.stdin.readline()) for _ in range(n))
    ans = 0
    for _ in range(m):
        if int(sys.stdin.readline()) in a:
            ans += 1
    print(ans)