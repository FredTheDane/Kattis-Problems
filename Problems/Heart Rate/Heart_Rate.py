import sys
for i, s in enumerate(sys.stdin):
    if i != 0:
        vals = s.split()
        x = 60.0 / float(vals[1])
        bpm = x * int(vals[0])
        abpm_min = bpm - x
        abpm_max = bpm + x
        print(f"{abpm_min:.4f} {bpm:.4f} {abpm_max:.4f}")