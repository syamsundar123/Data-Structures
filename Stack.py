top = -1
import time
def isempty():
    if top == -1:
        return True
    else:
        return False


def isfull():

    if capacity == top - 1:
        return True
    else:
        return False
def display():
    if isempty():
        print("STACK IS UNDERFLOW.")
        return
    for i in range(top,-1,-1):
        print(stack[i])

    print()



def push():
    global top
    element = int(input("Enter element to push into stack.\n"))
    if isfull():
        print("Stack is Overflow.")
        return
    else:
        top += 1
        stack[top] = element
        print("ELEMENT PUSHED SUCCESFULLY\n.")
        print("------------------------------------------------")


def pop():
     global top
     if isempty():
         print("STACK IS UNDERFLOW")
         return
     else:
         print("ELEMENT POPPED SUCCESSFULLY.  ",stack[top])
         print("\n-----------------------------------------------------------")
         top -= 1



def switch(input):
    switcher = {
        1: push,
        2:pop,
        3: display
    }

    f = switcher.get(input,"INVALID CHOICE")
    if f == "INVALID CHOICE":
        print("INVALID CHOICE")
        return
    else:
        return f()


if __name__ == '__main__':
    global capacity
    capacity = 9
    stack = [0 for i in range(10)]
    while True:
        print("1:Push\n2:Pop\n3:Show\n4:Exit")
        try:
            inp = int(input("Enter Your Choice here:"))
            if inp == 4:
                print("Program Terminating in 3 sec...")
                time.sleep(3)
                break
            else:
                switch(inp)
        except ValueError as v:
            print("ENTER CORRECT VALUE.")











