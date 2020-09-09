import time
front = rear = 0
capacity = 10

def traverse():
    global front,rear
    if front == rear:
        print("NOTHING TO SHOW.QUEUE IS EMPTY.\n")
        print("-------------------------------------------------------------------------------------------------------")
        return
    for i in range(front,rear-1):
        print(queue[i],"<--",end = " ")
    print(queue[rear-1])
    print()

def dequeue():
    global front,rear
    if front == rear:
        print("QUEUE IS EMPTY.CANNOT BE DEQUEUED.\n")
        print("-------------------------------------------------------------------------------------------------------")
        return
    queue.pop(0)

    """
    for i in range(rear):
        queue[i] = queue[i + 1]
    
    """
    rear -= 1
    print("ELEMENT DEQUEUED SUCCESSFULLY.\n")
    print("-----------------------------------------------------------------------------------------------------------")



def enqueue():
    global rear
    if capacity == rear:
        print("QUEUE IS FULL.\n")
        print("-------------------------------------------------------------------------------------------------------")
        return
    try:
        element = int(input("Enter Element to Enqueue:"))
        queue[rear] = element
        rear += 1
        print("ELEMENT ENQUEUED SUCCESSFULLY.\n")
        print("---------------------------------------------------------------------------------------------------------")

    except ValueError as v:
        print("ENTER CORRECT VALUE.\n")



def switch(input):
    switcher = {
        1: enqueue,
        2:dequeue,
        3: traverse
    }

    f = switcher.get(input,"INVALID CHOICE")
    if f == "INVALID CHOICE":
        print("INVALID CHOICE")
        return
    else:
        return f()


if __name__ == '__main__':
    print("===========================QUEUE AND IT'S OPERATIONS=====================================")
    queue = [0]*capacity
    while True:
        print("1:Enqueue\n2:Dequeue\n3:Show\n4:Exit")
        try:
            inp = int(input("Enter Your Choice here:"))
            if inp == 4:

                print("\nProgram "
                      "Terminating in 2 Sec...")
                time.sleep(2)
                break
            else:
                switch(inp)
        except ValueError as v:
            print("ENTER CORRECT VALUE.")