"""
9. Palindromul unui număr este numărul obținut prin scrierea cifrelor in ordine
 inversa (Ex. palindrom(237) = 732). Pentru un n dat calculați palindromul sau.
 """
def palindrom(my_number):
    if my_number[0]=='-':
        return None
    x=' '
    for i in reversed(range(len(my_number))):
        x=x+my_number[i]
    return x

nr = input("Choose the number: ")
x = palindrom(nr)

if x:
    print(x)
    
