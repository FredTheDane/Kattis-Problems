import sys

for i, x in enumerate(sys.stdin):
    val = str(x).strip()
    if (val == "OCT 31" or val == "DEC 25"):
        print("yup")
    else:
        print("nope")
