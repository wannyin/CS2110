import static org.junit.Assert.*;
import static common.JUnitUtil.*;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.junit.BeforeClass;
import org.junit.Test;

public class BugTreeTest {

    private static Network n;
    private static Human[] humans;

    @BeforeClass
    public static void setup(){
        n= new Network();
        humans= new Human[]{new Human("A", n, 0), new Human("B", n, 0), new Human("C", n, 0),
                new Human("D", n, 0), new Human("E", n, 0), new Human("F", n, 0),
                new Human("G", n, 0), new Human("H", n, 0), new Human("I", n, 0),
                new Human("J", n, 0), new Human("K", n, 0), new Human("L", n, 0)
        };
    }

    @Test
    public void testBuiltInGetters(){
        BugTree dt= new BugTree(humans[1]);
        assertEquals("B", toStringBrief(dt));
        assertEquals(0, dt.depthOf(humans[1]));
    }

    @Test
    public void testAdd() {
        BugTree dt= new BugTree(humans[1]); 

        //Test add to root
        BugTree dt2= dt.add(humans[1], humans[2]);
        assertEquals("B[C]", toStringBrief(dt));
        //assertEquals(1, dt.depthOf(humans[2]));

        //Test add to non-root
        BugTree dt3= dt.add(humans[2], humans[3]);
        assertEquals("B[C[D]]", toStringBrief(dt));

        //Test add second child
        BugTree dt4= dt.add(humans[2], humans[0]);
        assertEquals("B[C[A D]]", toStringBrief(dt));

    }
    
    /** Return a representation of this tree. This representation is:
     * (1) the name of the Human at the root, followed by
     * (2) the representations of the children (in alphabetical
     *     order of the children's names).
     * There are two cases concerning the children.
     *
     * No children? Their representation is the empty string.
     * Children? Their representation is the representation of each child, with
     * a blank between adjacent ones and delimited by "[" and "]".
     * Examples:
     * One-node tree: "A"
     * root A with children B, C, D: "A[B C D]"
     * root A with children B, C, D and B has a child F: "A[B[F] C D]"
     */
    public static String toStringBrief(BugTree t) {
        String res= t.getRoot().getName();

        Object[] childs= t.copyOfChildren().toArray();
        if (childs.length == 0) return res;
        res= res + "[";
        selectionSort1(childs);

        for (int k= 0; k < childs.length; k= k+1) {
            if (k > 0) res= res + " ";
            res= res + toStringBrief(((BugTree)childs[k]));
        }
        return res + "]";
    }

    /** Sort b --put its elements in ascending order.
     * Sort on the name of the Human at the root of each BugTree
     * Throw a cast-class exception if b's elements are not BugTrees */
    public static void selectionSort1(Object[] b) {
        int j= 0;
        // {inv P: b[0..j-1] is sorted and b[0..j-1] <= b[j..]}
        // 0---------------j--------------- b.length
        // inv : b | sorted, <= | >= |
        // --------------------------------
        while (j != b.length) {
            // Put into p the index of smallest element in b[j..]
            int p= j;
            for (int i= j+1; i != b.length; i++) {
                String bi= ((BugTree)b[i]).getRoot().getName();
                String bp= ((BugTree)b[p]).getRoot().getName();
                if (bi.compareTo(bp) < 0) {
                    p= i;

                }
            }
            // Swap b[j] and b[p]
            Object t= b[j]; b[j]= b[p]; b[p]= t;
            j= j+1;
        }
    }

}
