import numpy as np

#code
'''
x ≡ 1 mod 2
x ≡ 1 mod 3
x ≡ 6 mod 7
'''

#################################################################################

def lcm(a, b) :
    return (a * b) / gcd(a, b)

def gcd(a, b) :
    if b == 0 :
        return a
    else :
        return gcd(b, a % b)
	
def extendedEuclidean(a, b, s, t) :
    if a == 0 :
        return [b, 0, 1]
    else :
        res = extendedEuclidean(b % a, a, s, t)
        b1, s1, t1 = res
        s = t1 - ((b // a) * s1)
        t = s1
        res[1] = s
        res[2] = t
        print("\t\tres: " + str(res))
        return res

def check(a, b, res) :
    return ((a * res[1]) + (b * res[2])) == 1
    
def modInverse(a, m) : 
    a = a % m; 
    for x in range(1, m) : 
        if ((a * x) % m == 1) : 
            return x 
    return 1
    
def egcd(a, b, s=1, t=1) :
    egcd = extendedEuclidean(a, b, s, t)
    string = "\n====================V========================"
    string += "\n\tegcd(" + str(a) + ", " + str(b) + "):\n"
    egcd_val = egcd[0]
    if check(a, b, egcd) :
        string += "(" + str(a) + " * " + str(egcd[1]) + ") + (" +  str(b) + " * " + str(egcd[2]) + ") = 1"
    else :
        string += "(" + str(a) + " * " + str(egcd[1]) + ") + (" +  str(b) + " * " + str(egcd[2]) + ") != 1"
        string += "\n=> " + str(((a * egcd[1]) + (b * egcd[2])))
    string += "\nvals: " + str(egcd) + "\n"
    m = egcd_val #max(a, b)
    x = egcd[1] if m == b else egcd[2]
    if x < 0 :
        m += x
    else :
        m = x
    string += "m: " + str(m) + ", x: " + str(x) + "\n"
    string += "egcd Val: " + str(m) + "\n"
    string += "===================^=====================\n"
    print(string)
    return m

#################################################################################

def crt(rems, mods) :
    M = np.prod(mods)
    c = []
    egcds = []
    
    ms = [np.prod(np.array(mods[:i] + mods[i + 1:])) for i in range(len(mods))]
    cs = [modInverse(ms[i], mods[i]) for i in range(len(ms))]
    mcs = [ms[i] * cs[i] for i in range(len(ms))]
    mcsr = [mcs[i] * rems[i] for i in range(len(mcs))]
    sum_mcsr = sum(mcsr)
    crt_val = sum_mcsr % M
    print("\tM:\t" + str(M))
    print("\tms:\t" + "\t".join(map(str, ms)))
    print("\tcs:\t" + "\t".join(map(str, cs)))
    print("\tmcs:\t" + "\t".join(map(str, mcs)))
    print("\tmcsr:\t" + "\t".join(map(str, mcsr)))
    print("\tsum_mcsr:\t" + str(sum_mcsr))
    print("\tcrt_val:\t" + str(crt_val))
    return crt_val
    
rems_1 = [1,1,6]
mods_1 = [2,3,7]
rems_2 = [2,3,2]
mods_2 = [3,5,7]
rems_3 = [1,2,3]
mods_3 = [3,5,7]
rems_4 = [6, 13, 9, 19]
mods_4 = [11, 16, 21, 25]
print("\n\tHomework 2 Q1:\n\tCongruences:\n")
for i in range(len(rems_1)) :
    print("\t" + str(rems_1[i]) + " % " + str(mods_1[i]) + " = x")
print("crt: " + str(crt(rems_1, mods_1)))
    
print("\n\tExam prep question:\n\tCongruences:\n")
for i in range(len(rems_2)) :
    print("\t" + str(rems_2[i]) + " % " + str(mods_2[i]) + " = x")
print("crt: " + str(crt(rems_2, mods_2)))
    
print("\n\tClass example:\n\tCongruences:\n")
for i in range(len(rems_3)) :
    print("\t" + str(rems_3[i]) + " % " + str(mods_3[i]) + " = x")
print("crt: " + str(crt(rems_3, mods_3)))
    
print("\n\tOnline sample:\n\tCongruences:\n")
for i in range(len(rems_4)) :
    print("\t" + str(rems_4[i]) + " % " + str(mods_4[i]) + " = x")
print("crt: " + str(crt(rems_4, mods_4)))
    
# print(egcd(1234,4321, 1, 1))
# print(egcd(1769, 550, 1, 1))
# print(egcd(92400, 3696, 1, 1))
# print(modInverse(1234,4321))
# print(modInverse(1769, 550))
# print(modInverse(92400, 3696))
# print(modInverse(21, 105))