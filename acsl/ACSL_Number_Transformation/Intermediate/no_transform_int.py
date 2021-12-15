#filename = 'F:/IB_ComputerScience/ACSL_Number_Transformation/Intermediate/int-sample-input.txt'
filename = 'E:/intdata.txt'
with open(filename) as file_object:
    lines = file_object.readlines()

for line in lines:
    a = line.split()
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
            answer += str((int(n[position]) + int(n[selected_pos])) % 10)
        elif position == selected_pos:
            answer += str(n[selected_pos])
        elif position > selected_pos:
            answer += str(abs(int(n[position]) - int(n[selected_pos])))
    print(answer)   
