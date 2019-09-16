import sys
import re

for i, x in enumerate(sys.stdin):
    val = str(x).strip().lower()
    if(re.match(r'(\w*([s])\2+)', val)):
        print("hiss")
    else:
        print("no hiss")