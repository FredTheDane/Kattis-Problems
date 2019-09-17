import sys
from datetime import date
line = list(map(int,sys.stdin.readline().split()))
d = date(2009, line[1], line[0]).weekday()
if (d == 0):
    print("Monday")
elif (d == 1):
    print("Tuesday")
elif (d == 2):
    print("Wednesday")
elif(d == 3):
    print("Thursday")
elif(d == 4):
    print("Friday")
elif(d == 5):
    print("Saturday")
elif(d == 6):
    print("Sunday")