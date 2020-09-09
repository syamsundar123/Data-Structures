class Node:
    def __init__(self,data):
        self.data  = data
        self.next =  None


class new_node:
    def __init__(self):
        self.head = None 

    def append(self,data):
        newnode = Node(data)

        if self.head is None:
            self.head = newnode
            return

        ptr = self.head
        while ptr.next is not None:
            ptr = ptr.next
        ptr.next = newnode

    def print(self):
        ptr = self.head
        while ptr:
            print(ptr.data,end = " ")
            ptr = ptr.next


if __name__ == '__main__':
    n = int(input('No.of Elements:'))
    obj = new_node()
    for i in range(n):
        ele = (input())
        obj.append(ele)
        print('Processed')
    print("Elements are:",end = " ")
    obj.print()


