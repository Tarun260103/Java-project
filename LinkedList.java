public class LinkedList{
    public static class Node {
        int data;
        Node next;

        public Node(int data) { //CREATING A NODE
            this.data = data;
            this.next = null;
        }
    }
        public static Node head;
        public static Node tail;
        public static int size;

        public void addfirst(int data) {

            //step 1 create new node
            Node newNode = new Node(data);
            size++;

            //if the linked list is empty
            if (head == null) {
                head = tail = newNode;
                return;
            }
            //step 2 newnode next  = head
            newNode.next = head;//link
            //step 3 head = new node
            head = newNode;

        }

        public void addlast(int data) {  //TC = 0(1)
            // step 1 create new node
            Node newNode = new Node(data);
            size++;
            if (head == null) { // base case
                head = tail = newNode;
                return;
            }
            // step 2 newNode next = tail
            tail.next = newNode;
            //step 3 tail = new node
            tail = newNode;

        }

        public void print() {
            if(head ==  null){
                System.out.println("null");
                return;
            }
            Node temp = head;
            while (temp != null) {
                System.out.print(temp.data + "->");
                temp = temp.next;
            }
            System.out.println("null");
        }
       //ADD IN THE MIDDLE
        public void addMiddle(int idx,int data ){ // tc = o(n)
            if(idx == 0){
                addfirst(data);
                return;
            }
            Node newNode = new Node(data);
            size++;
            Node temp = head;
            int i = 0;

            while(i < idx-1){
                temp = temp.next;
                i++;
            }
            //i=idx-1 -> temp ->next
            newNode.next = temp.next;
            temp.next = newNode;

        }
        public int removeFirst(){
            if (size == 0){
                System.out.println("ll is empty");
                return Integer.MIN_VALUE;
            }else if(size == 1){
                int val = head.data;
                head = tail = null;
                size = 0;
                return val;
            }
            int val = head.data;
            head = head.next;
            size--;
            return val;
        }
        public int removeLast(){
            if(size == 0){
                System.out.println("ll is empty");
                return Integer.MIN_VALUE;
            }else if(size == 1){
                int val = head.data;
                head = tail = null;
                size = 0;
                return val;
            }
            // previous  element = size - 2
            Node prev = head;
            for(int i = 0; i<size-2; i++){
                prev = prev.next;
            }

            int val = prev.next.data;
            prev.next = null;
            tail = prev;
            size--;
            return val;
        }
        public int iterativeSearch(int key){
            Node temp = head;
            int i = 0;
            while(temp != null){
                if(temp.data == key){
                    return i;
                }
                temp = temp.next;
                i++;
            }
            return -1;
        }

        //recursive search
        public int helper(Node head, int key){
            if(head == null){
                return -1;
            }
            if(head.data == key){
                return 0;
            }
            int idx = helper(head.next , key);
            if (idx == -1) {
                return -1;
            }
            return idx + 1;
        }
        public int recursiveSearch(int key) {
            return helper(head,key);

        }

        // REVERSE A LINKED LIST
        public void reverseLL(){
            Node prev = null;
            Node curr = tail = head;
            Node next;

            while(curr != null){
                next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }
            head = prev;
        }

        // FIND & DELETE NTH NODE FROM LAST
        public void deleteNTHfromEnd(int n){
            //calculate size
            int size = 0;
            Node temp = head;
            while (temp != null){
                temp =temp.next;
                size++;

            }
            if(n == size){
                head = head.next; //remove first
                return;
            }
            int idx = 1;
            int idxToFind = size - n;
            Node prev = head;
            while(idx <idxToFind){
                prev = prev.next;
                idx++;
            }
            prev.next = prev.next.next;
            return;
        }
        //  SLOW-FAST APPROACH
        public Node findMid(Node head ){
            Node slow = head;
            Node fast = head;

            while(fast != null && fast.next != null){
                slow = slow.next;//+1(turtle)
                fast = fast.next.next;//+2(hare/rabbit)
            }
            return slow; // THIS IS MY MID
        }

        public boolean checkPalindrome(){
            //base case
            if(head == null || head.next == null){
                return true;
            }

            //step 1 - to find mid
            Node midNode = findMid(head);

            //step 2 - to reverse 2nd half
            Node prev = null;
            Node curr = midNode;
            Node next;

            while(curr != null){
                next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }
            Node right = prev;// right half head
            Node left = head;

            //step 3 - check left and right half
            while (right != left){
                if(left.data != right.data){
                    return false;
                }

                left = left.next;
                right = right.next;
            }
            return true;
        }
        public static boolean isCycle(){
            Node slow = head ;
            Node fast = head;
            while(head != null && head.next != null){
                slow = slow.next; //+1
                fast = fast.next.next; //+2
                if(slow == fast){
                    return true; //cycle is exist
                }
            }
            return false; //cycle does not exist
        }

        // REMOVE CYCLE IN LL
        public static void removeCycle(){

            //step 1 - detect a cycle
            Node slow = head;
            Node fast = head;
            boolean cycle = false;
            while(fast != null && fast.next != null){
              slow = slow.next;
              fast = fast.next.next;

              if(fast == slow){
                  cycle = true ;
                  break;
              }
            }
            if(cycle == false){
                return;
            }

            //step 2 - find meeting point
            slow = head;
            Node prev = null; // lastnode
            while(slow != fast){
                prev = fast ;
                slow = slow.next;
                fast = fast.next;
            }

            //step 3 - remove cycle
            prev.next = null;
        }

        //MERGE SORT IN LL
        private Node getMid(Node head){    // step 1
            Node slow = head;
            Node fast = head.next;

            while(fast != null && fast.next != null ){
                slow = slow.next;
                fast = fast.next.next;
            }
          return slow;
        }

        private Node merge(Node head1 , Node head2){ // step 2
             Node mergedLL = new Node(-1);
             Node temp = mergedLL;
             while(head1 != null && head2 != null){
                 if(head1.data <= head2.data){
                     temp.next = head1;
                     head1 = head1.next;
                     temp = temp.next;
                 }else{
                     temp.next = head2;
                     head2 = head2.next;
                     temp = temp.next;

                 }
             }

             while(head1 != null){
                 temp.next = head1;
                 head1 = head1.next;
                 temp = temp.next;
             }

            while(head2 != null){
                temp.next = head2;
                head1 = head2.next;
                temp = temp.next;
            }
            return mergedLL.next;

        }

        public Node mergeSort(Node head){
            // base case
            if(head == null || head.next == null){
                return head;
            }
            //step 1 FIND MID
            Node mid = getMid(head);

            //step 2 LEFT AND RIGHT HALF BREAK
            Node rightHead = mid.next;
            mid.next = null;
            Node newLeft = mergeSort(head);
            Node newRight = mergeSort(rightHead);

            //MERGE
            return merge(newLeft , newRight);
        }

        //ZIG ZAG LINKED LIST
        public void ZigZag(){
            //STEP 1 : FIND MID
            Node slow = head;
            Node fast = head.next;

            while(fast != null && fast.next !=null){
                slow = slow.next;
                fast = fast.next.next;

            }
            Node Mid = slow;

            // STEP 2 REVERSE THE 2ND HALF
            Node curr = Mid.next;
            Mid.next = null;
            Node prev = null;
            Node next;

            while(curr != null){
                next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }
            Node left = head;
            Node right = prev;
            Node nextL , nextR;

            while(left != null && right != null){
                nextL = left.next; //lhead.next
                left.next = right;
                nextR = right.next;// rhead.next
                right.next = nextL;

                left = nextL ;
                right = nextR;
            }
        }



    public static void main(String args[]){
            LinkedList ll = new LinkedList();
            ll.addlast(1);
            ll.addlast(2);
            ll.addMiddle(6,8);
            ll.addlast(3);
            ll.addlast(4);
            ll.addlast(5);
            ll.removeFirst();
            ll.removeLast();
            ll.print();
            ll.ZigZag();
            ll.print();

        }
    }






