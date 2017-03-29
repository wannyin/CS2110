import static org.junit.Assert.*;

import org.junit.Test;

public class PhDTester {

	@Test
	public void testConstructor1() {
		//  Needs 7 assert statements to test each field using getters.
		
		PhD testPhDFemale = new PhD("Jackie Loven", 'F', 2015, 3);
		// Jackie Loven got her PhD in March 2015.
		PhD testPhDMale = new PhD("Thomas Balk", 'M', 2011, 7);
		// Thomas Balk got his PhD in July 2011.
		
		assertEquals("Jackie Loven", testPhDFemale.getName());
		assertEquals(false, testPhDFemale.isMale());
		assertEquals(2015, testPhDFemale.getYear());
		assertEquals(3, testPhDFemale.getMonth());
		assertEquals(null, testPhDFemale.getFirstAdvisor());
		assertEquals(null, testPhDFemale.getSecondAdvisor());
		assertEquals(0, testPhDFemale.numAdvisees());
		assertEquals(true, testPhDMale.isMale());
	}
	
	@Test
	public void testSetters() {
		PhD testStudent = new PhD("Jackie Loven", 'F', 2015, 3);
		PhD testAdvisor1 = new PhD("Richard Robinson", 'M', 1980, 6);
		PhD testAdvisor2 = new PhD("David Gries", 'M', 2000, 1);
		
		//  Advisors are null at first.
		assertEquals(null, testStudent.getFirstAdvisor());
		testStudent.addFirstAdvisor(testAdvisor1);
		assertEquals(testAdvisor1, testStudent.getFirstAdvisor());
		//  Haven't added second advisor yet.
		assertEquals(null, testStudent.getSecondAdvisor());
		assertEquals(1, testAdvisor1.numAdvisees());
		//  Adding second advisor.
		testStudent.addSecondAdvisor(testAdvisor2);
		assertEquals(testAdvisor1, testStudent.getFirstAdvisor());
		assertEquals(testAdvisor2, testStudent.getSecondAdvisor());
		assertEquals(1, testAdvisor2.numAdvisees());
	}
	
	@Test
	public void testMoreDetailedConstructors() {
		//  Two PhD objects using original constructor.
		PhD testAdvisor1 = new PhD("Gandalf the Gray", 'M', 1980, 6);
		PhD testAdvisor2 = new PhD("Yolo Swaggins", 'M', 3000, 1);
		
		//  Testing the first new constructor on Eitan's information.
		PhD testPhD1 = new PhD("Eitan Jaffe", 'M', 1995, 12, testAdvisor1);
		assertEquals("Eitan Jaffe", testPhD1.getName());
		assertEquals(true, testPhD1.isMale());
		assertEquals(1995, testPhD1.getYear());
		assertEquals(12, testPhD1.getMonth());
		assertEquals(testAdvisor1, testPhD1.getFirstAdvisor());
		assertEquals(null, testPhD1.getSecondAdvisor());
		assertEquals(0, testPhD1.numAdvisees());
		//  Testing the first new constructor on the advisor's information.
		assertEquals(1, testAdvisor1.numAdvisees());
		assertEquals(0, testAdvisor2.numAdvisees());
		
		//  Testing the second new constructor on Erica's information.
		PhD testPhD2 = new PhD("Erica Sadler", 'F', 2010, 9, testAdvisor1, testAdvisor2);
		assertEquals("Erica Sadler", testPhD2.getName());
		assertEquals(false, testPhD2.isMale());
		assertEquals(2010, testPhD2.getYear());
		assertEquals(9, testPhD2.getMonth());
		assertEquals(testAdvisor1, testPhD2.getFirstAdvisor());
		assertEquals(testAdvisor2, testPhD2.getSecondAdvisor());
		assertEquals(0, testPhD2.numAdvisees());
		//  Testing the second new constructor on the advisors' information.
		assertEquals(2, testAdvisor1.numAdvisees()); //  Both Eitan and Erica now!
		assertEquals(1, testAdvisor2.numAdvisees()); //  Just Erica.
	}
	
	
	@Test
	public void testComparisons() {
		PhD test1 = new PhD("Tupac Shakur", 'M', 1995, 11);
		PhD test2 = new PhD("Katy Perry", 'F', 1980, 6);
		PhD testAdvisee1 = new PhD("TOM BRADY", 'M', 1995, 12, test1);
		PhD testAdvisee2 = new PhD("THE PATRIOTS", 'M', 1995, 11, test1, test2);
		
		//  See who got their PhD first.
		assertEquals(false, test2.isYoungerThan(test1));
		assertEquals(true, test1.isYoungerThan(test2));
		assertEquals(false, testAdvisee2.isYoungerThan(testAdvisee1));
		assertEquals(true, testAdvisee1.isYoungerThan(testAdvisee2));
		assertEquals(true, test1.isYoungerThan(testAdvisee2));
		assertEquals(true, testAdvisee2.isYoungerThan(test1));
		
		//  See if they're intellectual siblings.
		assertEquals(true, testAdvisee2.isPhDSibling(testAdvisee1));
		assertEquals(true, testAdvisee1.isPhDSibling(testAdvisee2));
		//  Remember that the advisors in common can't be null!
		assertEquals(false, test1.isPhDSibling(test2));
	}
}
