#filename = 'F:/IB_ComputerScience/ACSL_Number_Transformation/Senior/sr-sample-input.txt'
filename = 'E:/srdata1.txt'
def num_of_primes(n , factors):
    prime_check = 2
    while int(n) > 1:
        while int(n) % prime_check == 0:
            factors.append(prime_check)
            n = int(n) / prime_check
        prime_check += 1
        if prime_check * prime_check > int(n):
            if int(n) > 1: factors.append(n)
            break
    return factors

with open(filename) as file_object:
    lines = file_object.readlines()

for line in lines:
    a = line.split()
    #print(a)
    n = a[0]
    p = a[1]
    n_len = len(str(n))
    #print("length: " + str(n_len))
    #print("n: " + n)
    #print("p: " + p)
    selected_pos = n_len - int(p)
    answer = ""
    for position in range(0,n_len):
        if position < selected_pos:
            answer += str((int(n[position]) + int(n[selected_pos])))
        elif position == selected_pos:
            factors = []
            answer += str(len(list(set(num_of_primes(n, factors)))))
        elif position > selected_pos:
            answer += str(abs(int(n[position]) - int(n[selected_pos])))
    print(answer)   
