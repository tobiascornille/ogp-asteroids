def sumfac(param):
    a = param
    t = 1
    print("t:", t)
    while 1.5 < a:
        t += a * sumfac(a - 1)
        print("t:", t)
        a -= 1

    return t

print(sumfac(4))