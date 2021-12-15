def num_of_primes(n, factors):
    prime_check = 2
    while prime_check <= int(n):
        if int(n) % prime_check == 0:
            n = int(n) / prime_check
            factors.append(prime_check)
        else:
            prime_check += 1    
    return factors

#TESTING CODE#
factors = []
test_digit = 60098065452
print(num_of_primes(test_digit, factors))
print(list(set(factors)))
print(len(list(set(factors))))

