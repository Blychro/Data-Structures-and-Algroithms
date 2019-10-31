package edu.cofc.csci230;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.security.Permission;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Queue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import edu.cofc.csci230.ConstantTimeQueue;

class Hw4ConstantTimeQueue {

	ConstantTimeQueue<String> testQueue;
	Queue<String> sampleQueue;
	static final String UNEXPECTED_EXCEPTION = "Unexpected exception. (-10 pts)";
	static final double ptsPerTestCase = 10.0;
	
	@BeforeEach
	void setUp() throws Exception {
		System.setSecurityManager(new NoExitSecurityManager());

		// We use it as a baseline to test the edu.cofc.csci230.ConstantTimeStack and Queue classes.
		sampleQueue = new PriorityQueue<String>();
		sampleQueue.add("To Kill a Mockingbird");
		sampleQueue.add("Chicago");
		sampleQueue.add("Rent");
		sampleQueue.add("Cats");
		sampleQueue.add("Kinky Boots");
		sampleQueue.add("Wicked");
		sampleQueue.add("The Phantom of the Opera");
		
		// testStack and testQueue are an instance of the assignment's ConstantTimeStack and ConstantTimeQueue
		// We use it in the test cases below.
		try {
			testQueue = getQueue(sampleQueue);
		} catch(NullPointerException e) {}
	}
	
	@AfterEach
	void tearDown() throws Exception {
		System.setSecurityManager(null);
	}
	
	@Test
	@DisplayName("Queue - Peek")
	public void peek() {
		// Should retrieve the right node for every index
		try {
			String expected = sampleQueue.peek();
			String actual = testQueue.peek();
			assertEquals(expected, actual, "Does not return the right value (-" + String.valueOf(ptsPerTestCase) + " pts)");
		} catch(Exception e) {			
			fail("(" + e.getClass().getName() + ") " + UNEXPECTED_EXCEPTION);
		}
	}
	
	@Test
	@DisplayName("Queue - Peek empty")
	public void peekEmpty() {
		try {
			ConstantTimeQueue<String> queue = new ConstantTimeQueue<>();
			assertTrue("Does not return null when the queue is empty (-" + String.valueOf(ptsPerTestCase) + " pts)", queue.peek() == null);
		} catch(Exception e) {			
			fail("(" + e.getClass().getName() + ") " + UNEXPECTED_EXCEPTION);
		}
	}
	
	@Test
	@DisplayName("Queue - Add")
	public void push() {
		String play1 = "The Book of Mormon";
		String play2 = "Lion King";
		try {
			ConstantTimeQueue<String> queue = new ConstantTimeQueue<>();
			queue.add(play1);
			queue.add(play2);
			String expectedFront = play1;
			String actualFront = queue.remove();
			String expectedBack = play2;
			String actualBack = queue.remove();
			
			boolean match = expectedFront.equals(actualFront) && expectedBack.equals(actualBack);
			assertTrue("Found unexpected value at the front of the queue. (-" + String.valueOf(ptsPerTestCase) + " pts)", match);
		} catch(Exception e) {
			fail("(" + e.getClass().getName() + ") " + UNEXPECTED_EXCEPTION);
		}
	}
	
	@Test
	@DisplayName("Queue - Constant time add")
	public void pushConstantTime() {
		String play = "The Book of Mormon";
		try {
			sampleQueue.add(play);
			String expected = sampleQueue.peek();
			testQueue.add(play);
			String actual = testQueue.peek();
			assertEquals(expected, actual, "Wrong value found at the front of the queue (-" + String.valueOf(ptsPerTestCase) + " pts)");
			
			final int firstLength = 100;
			final int finalLength = 100000;
			
			long startTime1 = System.currentTimeMillis();
			for(int i = 0; i < firstLength; i++)
				testQueue.add(play);
			long duration1 = System.currentTimeMillis() - startTime1;
			
			long startTime2 = System.currentTimeMillis();
			for(int i = 0; i < finalLength; i++)
				testQueue.add(play);
			long duration2 = System.currentTimeMillis() - startTime2;
						
			assertTrue(duration1 - duration2 + 50 > 0, "Does not push with a constant time. (-" + String.valueOf(ptsPerTestCase) + " pts)");
		} catch(Exception e) {
			fail("(" + e.getClass().getName() + ") " + UNEXPECTED_EXCEPTION);
		}
	}
	
	@Test
	@DisplayName("Queue - Remove")
	public void remove() {
		try {
			String expected = sampleQueue.remove();
			String actual = testQueue.remove();
			
			assertEquals(expected, actual, "Popped the wrong item (-" + String.valueOf(ptsPerTestCase) + " pts)");
			
			String expectedNextPop = sampleQueue.remove();
			String actualNextPop = testQueue.remove();
			assertEquals(expectedNextPop, actualNextPop, "Popped the wrong item (-" + String.valueOf(ptsPerTestCase) + " pts)");
		} catch(Exception e) {
			fail("(" + e.getClass().getName() + ") " + UNEXPECTED_EXCEPTION);
		}
	}
	
	@Test
	@DisplayName("Queue - Remove empty")
	public void removeEmpty() {
		try {
			Executable removeEmpty = new Executable() {
				@Override
				public void execute() throws Throwable {
					ConstantTimeQueue<String> queue = new ConstantTimeQueue<>();
					queue.remove();
				}
			};
			assertThrows(NoSuchElementException.class, removeEmpty, "Does not raise a NoSuchElementException (-" + String.valueOf(ptsPerTestCase) + " pts)");
		} catch(Exception e) {			
			fail("(" + e.getClass().getName() + ") " + UNEXPECTED_EXCEPTION);
		}
	}
	
	@Test
	@DisplayName("Queue - Constant time remove")
	public void popConstantTime() {
		String play = "The Book of Mormon";
		try {String expected = sampleQueue.remove();
		String actual = testQueue.remove();
		
		assertEquals(expected, actual, "Returned the wrong item when removing (-" + String.valueOf(ptsPerTestCase) + " pts)");
		
		final int finalLength = 100000;
		
		for(int i = 0; i < finalLength; i++)
			sampleQueue.add(play);
		long startTime1 = System.currentTimeMillis();
		for(int i = 0; i < finalLength; i++)
			sampleQueue.remove();
		long duration1 = System.currentTimeMillis() - startTime1;
		
		for(int i = 0; i < finalLength; i++)
			testQueue.add(play);
		long startTime2 = System.currentTimeMillis();
		for(int i = 0; i < finalLength; i++)
			testQueue.remove();
		long duration2 = System.currentTimeMillis() - startTime2;
		
		System.out.println(duration1);
		System.out.println(duration2);
		
		assertTrue(duration2 < duration1 + 30 , "Does not remove with a constant time. (-" + String.valueOf(ptsPerTestCase) + " pts)");
		
		} catch(Exception e) {
			fail("(" + e.getClass().getName() + ") " + UNEXPECTED_EXCEPTION);
		}
	}

	/*
	 * 
	 * 
	 * No more tests below this point
	 * 
	 * 
	 */
	
	// Returns a new instance of edu.cofc.csci230.ArrayList with the contents of the ArrayList parameter
		private <AnyType extends Comparable<AnyType>> ConstantTimeQueue<AnyType> getQueue(Queue<AnyType> queue) {
			ConstantTimeQueue<AnyType> clone = new ConstantTimeQueue<AnyType>();
			for(AnyType item : queue) {
				clone.add(item);
			}
			
			return clone;
		}
	 private static class NoExitSecurityManager extends SecurityManager {
		
        @Override
        public void checkPermission(Permission perm) {
            // allow anything.
        }
        @Override
        public void checkPermission(Permission perm, Object context) {
            // allow anything.
        }
        @Override
        public void checkExit(int status) 
        {
            super.checkExit(status);
            throw new ExitException(status);
        }
	 }
	 
	 protected static class ExitException extends SecurityException {
		private static final long serialVersionUID = -8576748019682914998L;
		public final int status;
        public ExitException(int status) {
        	System.out.println("System.exit(" + String.valueOf(status) + ")");
	        this.status = status;
        }
    }
	
	 protected abstract class Assertion {
		 abstract void checkAssertion();
	 }
}
