import sys

count = -1
cities = []

for i, x in enumerate(sys.stdin):
    if (i != 0):
        line = str(x).strip()

        if (count == -1 or count == 0):
            count = int(line)
            cities = []
        else:
            if(line not in cities):
                cities.append(line)
            count -= 1
        if(count == 0):
            print(f"{len(cities)}")
        