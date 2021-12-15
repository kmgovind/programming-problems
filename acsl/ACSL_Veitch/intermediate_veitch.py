"""
@author:
    Kavin M. Govindarajan
    1A - IBCS
@logic:
    Create pre-def map of Vietch based on characters
    Create 2d array preset to False
    Run through bool expression, parsing at '+'
    If subset is contained in pre-def set the mapping 2d array to True
    Run through whole string, remainder will have all Trues
    Convert Bool to Hex
    Output
"""

pre_def = [
    ["ABcd", "ABCd", "aBCd", "aBcd"],
    ["ABcD", "ABCD", "aBCD", "aBcD"],
    ["AbcD", "AbCD", "abCD", "abcD"],
    ["Abcd", "AbCd", "abCd", "abcd"]
]

hex_map = {
    
    "0000":'0',
    "0001":'1',
    "0010":'2',
    "0011":'3',
    "0100":'4',
    "0101":'5',
    "0110":'6',
    "0111":'7',
    "1000":'8',
    "1001":'9',
    "1010":'A',
    "1011":'B',
    "1100":'C',
    "1101":'D',
    "1110":'E',
    "1111":'F'
}


#Resetting mappable_vietch to default False state
def reset():
    mappable_vietch = [
        [False, False, False, False],
        [False, False, False, False],
        [False, False, False, False],
        [False, False, False, False]
    ]
    return mappable_vietch

#Removing '~' tag from input statement
def casify(inputted):
    inputted = inputted.replace("~A", "a")
    inputted = inputted.replace("~B", "b")
    inputted = inputted.replace("~C", "c")
    inputted = inputted.replace("~D", "d")
    return inputted

#running through mapping by substring
def mapper(input_string, map_vietch):
    for sub_string in input_string:
        map_vietch = checker(sub_string, map_vietch)
    return map_vietch

#returns whether the substring is contained
def containsAll(sub, total):
    return 0 not in [c in sub for c in total]

#checking if mappable
def checker(element, map_vietch):
    for i in range(0,4):
        for j in range(0,4):
            if(containsAll(pre_def[i][j], element)):
                map_vietch[i][j] = True
    return map_vietch

#Taking Mapped vietch to Hex form
#TODO: Run through mapped_vietch line-by-line
#TODO: Convert each line to hex char, store in string
def bool_to_hex(final):
    converted_output = ""
    for row in final:
        sub_conversion = ""
        for i in row:
            if i:
                sub_conversion += "1"
            else:
                sub_conversion += "0"
        converted_output += hex_map[sub_conversion]
    return converted_output


#Main
with open('F:/3int.txt') as f:
    for line in f:
        mapped = reset()
        line = casify(line)
        line = line.strip()
        splitted = line.split("+")
        mapped = mapper(splitted, mapped)
        print(bool_to_hex(mapped))

