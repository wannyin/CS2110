import static org.junit.Assert.*;

import org.junit.Test;

public class A2Tester {

	@Test
	public void testingPalindrome() {
		assertEquals(true, A2.sameBackAndForth("abba"));
		assertEquals(true, A2.sameBackAndForth(""));
		assertEquals(false, A2.sameBackAndForth("ab"));
		assertEquals(true, A2.sameBackAndForth("aba"));
		assertEquals(false, A2.sameBackAndForth("Madam, I'm Adam"));
		assertEquals(false, A2.sameBackAndForth("MadamImAdam"));
		assertEquals(true, A2.sameBackAndForth("madamimadam"));
	}
	
	@Test
	public void testingNumOccurances() {
		assertEquals(1, A2.numOccurrences("ab", "b"));
		assertEquals(2, A2.numOccurrences("Luke Skywalker", "ke"));
		assertEquals(3, A2.numOccurrences("abababab", "aba"));
		assertEquals(0, A2.numOccurrences("Luke Skywalker", "xy"));
	}
	
	@Test
	public void testDecompress() {
		assertEquals("bbbcbbbbbxx", A2.decompress("b3c1b5x2a0"));
	}
	
	@Test
	public void testNaming() {
		assertEquals("David Gries", A2.fixName("   gRies,  DAVID     "));
		assertEquals("Nate Foster", A2.fixName("foster,    nate"));
		assertEquals("James Arthur Gosling", A2.fixName("GOSLING, JAMES   ARTHUR"));
	}
	
	@Test
	public void testAnagram() {
		assertEquals(true, A2.areAnagrams("mary", "army"));
		assertEquals(true, A2.areAnagrams("tom marvolo riddle", "i am lordvoldemort"));
		assertEquals(false, A2.areAnagrams("tommarvoloriddle", "i am lordvoldemort"));
		assertEquals(false, A2.areAnagrams("hello", "world"));
	}
	
	@Test
	public void testReplaceConsonants() {
		assertEquals("_iNeCRaFT", A2.replaceConsonants("Minecraft"));
		assertEquals("_LaN _uRiNG" ,A2.replaceConsonants("Alan Turing"));
	}
}
