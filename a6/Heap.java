

import java.lang.reflect.Array;

import java.util.*;

/** Please keep up with the pinned Piazza note A6 FAQs.
 * Study the slides of the Lecture on Priority Queues and Heaps and the A6
 * handout before starting on this assignment. */

/** An instance is a min-heap of distinct values of type V with
 *  priorities of type double. Since it's a min-heap, the value
 *  with the smallest priority is at the root of the heap. */
public class Heap<V> {

    /** Class Invariant:
     *   1. c[0..size-1] represents a complete binary tree. c[0] is the root;
     *      For each k, c[2k+1] and c[2k+2] are the left and right children of c[k].
     *      If k != 0, c[(k-1)/2] (using integer division) is the parent of c[k].
     *   
     *   2. For k in 0..size-1, c[k] contains the value and its priority.
     *   
     *   3. The values in c[0..size-1] are all different.
     *   
     *   4. For k in 1..size-1, (c[k]'s priority) >= (c[k]'s parent's priority).
     *   
     *   valPos and the tree are in sync, meaning:
     *   
     *   5. The keys of valPos are the values in c[0..size-1].
     *      This implies that this.size = valPos.size.
     *   
     *   6. if value v is in c[k], then valPos.get(v) returns k.
     *      Thus, the HashMap is called valPos because, given a VALue, it
     *      returns its POSition in c.
     *      
     */
    protected Info[] c;
    protected int size;
    protected HashMap<V, Integer> valPos;

    /** Constructor: an empty heap with capacity 10. */
    public Heap() {
        c= createInfoArray(10);
        valPos= new HashMap<V, Integer>();
    }

    /** An Info object contains a value and a priority. */
    class Info {
        V value;           // The value
        double priority;   // The priority

        /** An instance with value v and priority p*/
        Info(V v, double p) {
            value= v;
            priority= p;
        }

        /** Return a representation of this instance. */
        @Override public String toString() {
            return "(" + value + ", " + priority + ")";
        }
    }

    /** Add v with priority p to the heap.
     *  Throw an illegalArgumentException if v is already in the heap.
     *  The expected time is logarithmic and the worst-case time is linear
     *  in the size of the heap. */
    public void add(V v, double p) throws IllegalArgumentException {
        // TODO #1: Write this whole method. Note that bubbleUp is not implemented,
        // so calling it will have no effect (yet). The first tests of add, using
        // test00Add, ensure that this method maintains fields c and valPos properly,
        // without worrying about bubbling up. Look at the spec of test00Add.
    	if(find(v))
    		throw new IllegalArgumentException("v existed");
    	fixSpace();
    	size++;
    	c[size-1] = new Info(v,p);
    	valPos.put(v, size-1);
    	bubbleUp(size-1);
    	

    }
    
    private boolean find(V v){
    	if(valPos.get(v) != null)
    		return true;
    	return false;
    }


    /** If size = length of c, double the length of array c.
     *  The worst-case time is proportional to the length of c. */
    protected void fixSpace() {
        //TODO #2. Any method that increases the size of the heap must call
        // this method first. 
        //
        // See the Piazza A6 FAQs note to see how to create an Info array.
        //
        // If you write this method correctly AND method
        // add calls this method appropriately, testing procedure
        // test10ensureSpace will not find errors. 
    	if (size==c.length){
    		Info[] a = createInfoArray(size*2);
    		for (int i=0;i<c.length;++i){
    			a[i] = c[i];
    		}
    		c=a;
    	}

    }

    /** Return the size of this heap.
     *  This operation takes constant time. */
    public int size() {
        return size;
    }

    /** Swap c[h] and c[k].
     *  Precondition: 0 <= h < heap-size, 0 <= k < heap-size. */
    protected void swap(int h, int k) {
        assert 0 <= h  && h < size  &&  0 <= k  &&  k < size;
        //TODO 3: When bubbling values up and (later on) down, two values,
        // say c[h] and c[k], will have to be swapped. At the same time,
        // field valPos has to be maintained.
        // In order to always get this right, use method swap for this.
        // Method swap is tested by testing procedure test13Add_Swap --it will
        // find no errors if you write this method properly.
        // 
        // Read the A6 FAQs note about valPos.put(...).
        Info val = c[h];
        c[h]=c[k];
        c[k]=val;
        
        valPos.put(c[k].value,k);
        valPos.put(c[h].value,h);

    }

    /** Bubble c[k] up in heap to its right place.
     *  Precondition: Priority of every c[i] >= its parent's priority
     *                except perhaps for c[k]  AND
     *                0 <= k < size. */
    private void bubbleUp(int k) {
        // TODO #4 As you know, this method should be called within add in order
        // to bubble a value up to its proper place, based on its priority.
        // Do not use recursion. Use iteration.
        // If this method is written properly, testing procedure
        // test15Add_BubbleUp() will not find any errors.
        assert 0 <= k  && k < size;

        while (k>0){
        	if(c[k].priority < c[(k-1)/2].priority){
        		//System.out.println(c[k].priority +":"+ c[(k-1)/2].priority);
        		swap(k,(k-1)/2);
        		k = (k-1)/2;
        	}
        	else
        		break;
        }
    }

    /** Return the value of this heap with lowest priority. Do not
     *  change the heap. This operation takes constant time.
     *  Throw a NoSuchElementException if the heap is empty. */
    public V peek() {
        // TODO 5: Do peek. This is an easy one. 
        //         test20Peek() will not find errors if this is correct.
    	if(size==0)
    		throw new NoSuchElementException("empty");
        return c[0].value;
    }

    /** Remove and return the element of this heap with lowest priority.
     *  The expected time is logarithmic and the worst-case time is linear
     *  in the size of the heap.
     *  Throw a NoSuchElementException if the heap is empty. */
    public V poll() {
        // TODO 6: Do poll and bubbleDown (#7) together. When they are
        //         written correctly, testing procedure
        //         test30Poll_BubbleDown_NoDups will not find errors.
        // 
        //         Note also testing procedure test40testDuplicatePriorities
        //         This method tests to make sure that when bubbling up or down,
        //         two values with the same priority are not swapped.
    	if(size==0)
    		throw new NoSuchElementException("empty");
    	V v= peek();
    	swap(0, size-1);
		c[size-1]= null;
		size--;
    	valPos.remove(v);
    	if (size > 0) bubbleDown(0);
		return v;
    }

    /** Bubble c[k] down in heap until it finds the right place.
     *  If there is a choice to bubble down to both the left and
     *  right children (because their priorities are equal), choose
     *  the left child.
     *  Precondition: 0 <= k < size   and
     *                Each c[i]'s priority <= its childrens' priorities 
     *                except perhaps for c[k] */
    private void bubbleDown(int k) {
        // TODO 7: Do poll (#6) and bubbleDown together. We also suggest
        //         implementing and using smallerChildOf, though you don't
        //         have to. Do not use recursion. Use iteration.
    	assert 0 <= k  &&  k < size;
        
        while (2*k+1<size){
        	int pos = smallerChildOf(k);
        	if(c[k].priority > c[pos].priority){
        		swap(k,pos);
        		k = pos;
        	}
        	else
        		break;
        }


    }

    /** Return the index of the smaller child of c[n].
     *  If the two children have the same priority, choose the left one.
     *  Precondition: left child exists: 2n+1 < size of heap */
    protected int smallerChildOf(int n) {
        assert 2*n + 1 < size;
        if(2*n+2<size){
        	if(c[2*n+1].priority < c[2*n+2].priority)
        		return 2*n+1;
        	else
        		return 2*n+2;
        }
        return 2*n+1;
    }

    /** Change the priority of value v to p.
     *  The expected time is logarithmic and the worst-case time is linear
     *  in the size of the heap.
     *  Throw an IllegalArgumentException if v is not in the heap. */
    public void updatePriority(V v, double p) {
        // TODO  8: When this method is correctly implemented, testing procedure
        //          test50ChangePriority() won't find errors.
    	if(!find(v))
    		throw new IllegalArgumentException("v existed");
    	int pos = valPos.get(v);
    	double prev = c[pos].priority;
    	c[pos].priority=p;
    	if(prev<p) bubbleDown(pos);
    	else if(prev>p) bubbleUp(pos);
    	
    }

    /** Create and return an Info array of size n.
     *  This is necessary because Generics and arrays don't interoperate nicely.
     *  A student in CS2110 would not be expected to know about the need
     *  for this method and how to write it. */
    Info[] createInfoArray(int n) {
        return (Info[]) Array.newInstance(Info.class, n);
    }
}