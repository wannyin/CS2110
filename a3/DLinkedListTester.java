import static org.junit.Assert.*;

import org.junit.Test;

public class DLinkedListTester {

	@Test
	public void testAppend() {
		DLinkedList<String> l = new DLinkedList<String>();
		assertEquals(l.toString(), "");
		assertEquals(l.toStringRev(), "");
		assertEquals(l.size(), 0);
		l.append("hi");
		assertEquals(l.toString(), "[hi]");
		l.append("bye");
		assertEquals(l.toString(), "[hi, bye]");
		l.append("What");
		assertEquals(l.toStringRev(), "[What, bye, hi]");
		l.append("1212?#?@#!$( what space ");
		assertEquals(l.toStringRev(), "[1212?#?@#!$( what space , What, bye, hi]");
		assertEquals(l.size(), 4);
	}
	
	@Test
	public void testPrepend() {
		DLinkedList<String> l = new DLinkedList<String>();
		l.append("Last");
		assertEquals(l.getHead(), l.getTail());
		assertEquals(l.getData(l.getHead()), "Last");
		l.prepend("First");
		l.append("Even more last");
		assertEquals(l.toString(), "[First, Last, Even more last]");
		assertEquals(l.toStringRev(), "[Even more last, Last, First]");
		l.prepend("Even more first");
		assertEquals(l.toString(), "[Even more first, First, Last, Even more last]");
	}
	
	@Test
	public void testInsertAfter() {
		DLinkedList<String> l = new DLinkedList<String>();
		l.append("last");
		l.prepend("first");
		l.insertAfter("better last", l.getTail());
		assertEquals(l.getTail().getData(), "better last");
		assertEquals(l.getTail().succ(), null);
		assertEquals(l.getTail().pred().getData(), "last");
		l.insertAfter("middle", l.getHead());
		assertEquals(l.size(), 4);
		assertEquals(l.getHead().succ().getData(), "middle");
		assertEquals(l.getHead().succ().pred().getData(), "first");
		assertEquals(l.getHead().succ().succ().getData(), "last");
		assertEquals(l.toString(), "[first, middle, last, better last]");
		assertEquals(l.toStringRev(), "[better last, last, middle, first]");
	}
	
	@Test
	public void testInsertBefore() {
		DLinkedList<String> l = new DLinkedList<String>();
		l.append("last");
		l.prepend("first");
		l.insertBefore("Better first", l.getHead());
		l.insertBefore("Very second to last", l.getTail());
		assertEquals(l.toStringRev(), "[last, Very second to last, first, Better first]");
		assertEquals(l.toString(), "[Better first, first, Very second to last, last]");
		assertEquals(l.getHead().getData(), "Better first");
		assertEquals(l.getTail().getData(), "last");
		assertEquals(l.getHead().pred(), null);
		assertEquals(l.getHead().succ().getData(), "first");
		assertEquals(l.getTail().pred().getData(), "Very second to last");
		assertEquals(l.getTail().pred().pred().getData(), "first");
	}
	
	@Test
	public void testRemove() {
		DLinkedList<String> l = new DLinkedList<String>();
		l.append("first");
		l.append("middle");
		l.append("last");
		l.remove(l.getHead());
		assertEquals(l.toString(), "[middle, last]");
		assertEquals(l.getHead().getData(), "middle");
		assertEquals(l.getTail().getData(), "last");
		assertNull(l.getHead().succ().succ());
		l.prepend("first");
		l.remove(l.getTail());
		assertEquals(l.getTail().getData(), "middle");
		assertEquals(l.size(), 2);
		assertEquals(l.getTail().pred().pred(), null);
		assertEquals(l.toStringRev(), "[middle, first]");
		l.append("last");
		l.remove(l.getHead().succ());
		assertEquals(l.toString(), "[first, last]");
		l.remove(l.getHead());
		assertEquals(l.size(), 1);
		l.remove(l.getHead());
		assertEquals(l.size(), 0);
		assertEquals(l.toString(), "");
		assertEquals(l.toString(), l.toStringRev());
		assertNull(l.getHead());
		assertNull(l.getTail());
	}

}
