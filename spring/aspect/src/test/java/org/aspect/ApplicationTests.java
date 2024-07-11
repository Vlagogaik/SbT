package org.aspect;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class ApplicationTests {

	@Autowired
	private ExampleService exampleService;

	@Test
	public void testMethodWithString_Null() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> exampleService.methodWithString(null));
		assertEquals("Method argument is null", exception.getMessage());
	}

	@Test
	public void testMethodWithString_Empty() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> exampleService.methodWithString(""));
		assertEquals("Method argument is an empty string", exception.getMessage());
	}

	@Test
	public void testMethodWithCollection_Null() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> exampleService.methodWithCollection(null));
		assertEquals("Method argument is null", exception.getMessage());
	}

	@Test
	public void testMethodWithCollection_Empty() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> exampleService.methodWithCollection(new ArrayList<>()));
		assertEquals("Method argument is an empty collection", exception.getMessage());
	}

	@Test
	public void testMethodWithMultipleArgs_Null() {
		IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class, () -> exampleService.methodWithMultipleArgs(null, new ArrayList<>()));
		assertEquals("Method argument is null", exception1.getMessage());

		IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class, () -> exampleService.methodWithMultipleArgs("Oleg", null));
		assertEquals("Method argument is null", exception2.getMessage());
	}

	@Test
	public void testMethodWithMultipleArgs_Empty() {
		IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class, () -> exampleService.methodWithMultipleArgs("", new ArrayList<>()));
		assertEquals("Method argument is an empty string", exception1.getMessage());

		IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class, () -> exampleService.methodWithMultipleArgs("Oleg", new ArrayList<>()));
		assertEquals("Method argument is an empty collection", exception2.getMessage());
	}

}
