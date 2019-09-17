import sys
N = 0
M = 0
N_own = []
c = 0
t = 0

for i, s in enumerate(sys.stdin):
    if M == 0:
        vals = s.split()
        N = int(vals[0])
        M = int(vals[1])
        N_own.clear()
        c = 0
        t = 0
    else:
        val = s
        if N != 0:
            N_own.append(val)
            t = val
            N -= 1
        elif M != 0:
            if val <= t and val in N_own:
                c += 1
            M -= 1
        if M == 0:
            print(c)