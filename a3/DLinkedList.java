/* NetId(s): nnnn, nnnn. Time spent: hh hours, mm minutes.

 * Name(s):
 * What I thought about this assignment:
 *
 *
 */

/** An instance is a doubly linked list. */
public class DLinkedList<E> {
    private int size;  // Number of nodes in the linked list.
    private Node head; // first node of linked list (null if none)
    private Node tail; // last node of linked list (null if none)

    /** Constructor: an empty linked list. */
    public DLinkedList() {
    	size = 0;
    	head = null;
    	tail = null;
    }

    /** Return the number of values in this list. */
    public int size() {
        return size;
    }

    /** Return the first node of the list (null if the list is empty). */
    public Node getHead() {
        return head;
    }

    /** Return the last node of the list (null if the list is empty). */
    public Node getTail() {
        return tail;
    }

    /** Return the data in node n of this list.
     * Precondition: n is a node of this list; it may not be null. */
    public E getData(Node n) {
        assert n != null;
        return n.data;
    }

    /** Return a representation of this list: its data, with adjacent
     * ones separated by ", ", "[" at the beginning, and "]" at the end. <br>
     * 
     * E.g. for the list containing 6 3 8 in that order, the result it "[6, 3, 8]". */
    public String toString() {
        //TODO: Write this method first. Do NOT use field size
    	
    	String a = "[";
        Node p = head;
        if (p == null){
        	return "";
        }
        
        while (p!=null){
        	a = a.concat((String) p.data);
        	a = a.concat(", ");
        	p = p.succ;
        }
        a = a.substring(0, a.length()-2);
        a = a.concat("]");
        
        return a;

    }

    /** Return a representation of this list: its values in reverse, with adjacent
     * ones separated by ", ", "[" at the beginning, and "]" at the end. <br>
     * 
     * E.g. for the list containing 6 3 8 in that order, the result it "[8, 3, 6]".*/
    public String toStringRev() {
        //TODO: Write this method second. Do NOT use field size
        String a = "[";
        Node p = tail;
        
        if (p == null){
        	return "";
        }
        
        while (p!=null){
        	a = a.concat((String) p.data);
        	a = a.concat(", ");
        	p = p.pred;
        }
        a = a.substring(0, a.length()-2);
        a = a.concat("]");
        
        return a;
    }

    /** Place data v in a new node at the beginning of the list and
     * return the new node */
    public Node prepend(E v) {
        //TODO: This is the third method to write. Then you can begin testing
    	Node a = new Node(null, v,null);
    	if (head == null){
        	head = a;
        	tail = a;
        }
        else{
        	Node p = head;
        	p.pred = a;
        	head = a;
        	a.succ = p;
        }
        
    	size +=1;
        
         return a;
    }

    /** Place data v in a new node at the end of the list and
     * return the new node. */
    public Node append(E v) {
        //TODO:This is the fourth method to write.
    	Node a = new Node(null, v,null);
    	if (head == null){
        	head = a;
        	tail = a;
        }
        else{
        	Node p = tail;
        	p.succ = a;
        	tail = a;
        	a.pred = p;
        }
        
    	 size +=1;
         return a;
    }

    /** Place data v in a new node after node n and
     * return the new node.
     * Precondition: n must be a node of this list; it may not be null. */
    public Node insertAfter(E v, Node n) {
        //TODO: This is the fifth method to write and test
    	Node p = new Node(null,v,null);
    	Node after = n.succ;
    	p.succ = after;
    	p.pred = n;
    	n.succ = p;
    	if (after == null) tail = p;
    	else after.pred = p;
    	
    	size+=1;

        return p;
    }

    /** Place data v in a new node before node n and
     * return the new node.
     * Precondition: n must be a node of this list; it may not be null. */
    public Node insertBefore(E v, Node n) {
        //TODO: This is the sixth method to write and test
    	Node p = new Node(null,v,null);
    	Node before = n.pred;
    	p.succ = n;
    	p.pred = before;
    	n.pred = p;
    	if (before ==null) head=p;
    	else before.succ = p;
    	
    	
    	
    	size+=1;
        
        return p;
    }

    /** Remove node n from this list.
     * Precondition: n must be a node of this list; it may not be null. */
    public void remove(Node n) {
        //TODO: This is the seventh method to write and test
    	Node before = n.pred;
    	Node after = n.succ;
    	
    	if (size ==1){
    		head = null;
    		tail = null;
    	}
    	else if (before == null){
    		head = after;
    		after.pred = null;
    	}
    	else if (after == null){
    		tail = before;
    		before.succ= null;
    	}
    	else{
        	before.succ = after;
        	after.pred = before;
    	}


    	size -=1;
    } 


    /*********************/

    /** An instance is a node of this list. */
    public class Node {
        /** Predecessor of this node on list (null if this is the first node). */
        private Node pred;

        /** The data in this element. */
        private E data; 

        /** Successor of this node on list. (null if is the last node). */
        private Node succ; 

        /** Constructor: an instance with predecessor node p (can be null),
         * successor node s (can be null), and data v. */
        private Node(Node p, E v, Node s) {
            pred= p;
            succ= s;
            data= v;
        }

        /** Return the data in this node. */
        public E getData() {
            return data;
        }

        /** Return the predecessor of this node (null if this is the
         * first node of the list). */
        public Node pred() {
            return pred;
        }

        /** Return the successor of this node (null if this is the
         * last node of this list). */
        public Node succ() {
            return succ;
        }
    }

}
