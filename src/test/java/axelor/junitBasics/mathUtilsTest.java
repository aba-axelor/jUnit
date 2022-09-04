package axelor.junitBasics;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestReporter;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)                           // for only one instance per class
class mathUtilsTest {
	
	mathUtils mu;                                                        // instance declaration
	TestInfo testInfo;
	TestReporter testReporter;
	
//	@BeforeAll
//	static void beforeAllInit() {                                        // incase of Lifecycle.perclass we dont need to declare method static 
//		System.out.println("This needs to be run before All ");          // needs to be static bcz class instance is not created yet.
//	}
	
	@BeforeEach                                                         // instance initialized before each test
	void init(	TestInfo testInfo, TestReporter testReporter) {
		this.testInfo = testInfo;
		this.testReporter = testReporter;
		 mu = new mathUtils();
		 testReporter.publishEntry("Running "+testInfo.getDisplayName()+" with tags "+testInfo.getTags());
	}
	
//	@AfterEach                                                         // runs after each method executed
//	void cleanUp() {
//		System.out.println("Cleaning up");
//	}
	
	
	@Nested
	@DisplayName("add method")
	@Tag("Math")
	class addTest{
		
		@Test
		@DisplayName("when Adding two positive numbers")                                // for displaying name
		void testAddPositive() {
			assertEquals(2, mu.add(1, 1), "should return the right sum");
		}
		
		@Test
		@DisplayName("when Adding two negative numbers")                                
		void testAddNegative() {
			assertEquals(-2, mu.add(-1, -1), "should return the right sum");
		}
		
	}
	
	@Test
	@Tag("Math")
	@DisplayName("multiply method")
	void testMultiply() {
		assertAll(
				() -> assertEquals(4, mu.multiply(2, 2)),
				() -> assertEquals(0, mu.multiply(2, 0)),
				() -> assertEquals(-2, mu.multiply(2, -1))
				);
	}
	
	@Test
	@Tag("Math")
	void testDivide() {
		assertThrows(ArithmeticException.class,() -> mu.divide(4, 0), "Divide by zero should throw exception.");
	}

	
	
	@Test
	@Tag("Circle")
	@RepeatedTest(3)
	void testcalculateCircleArea() {
		assertEquals(314.1592653589793, mu.calculateCircleArea(10), "Should calculate the area of circle");
	}
	
	@Test
	@DisplayName("TDD method should not run")                                // for displaying name
	@Disabled
	void testDisabled() {
		fail("This test should be disabled");
	}

}
