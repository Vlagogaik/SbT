package org.aspect;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class ApplicationTests {

	@Autowired
	private ExampleService exampleService;

	@Test
	public void testMethodWithString_Null() {
		assertThrows(IllegalArgumentException.class, () -> exampleService.methodWithString(null));
	}

	@Test
	public void testMethodWithString_Empty() {
		assertThrows(IllegalArgumentException.class, () -> exampleService.methodWithString(""));
	}

	@Test
	public void testMethodWithCollection_Null() {
		assertThrows(IllegalArgumentException.class, () -> exampleService.methodWithCollection(null));
	}

	@Test
	public void testMethodWithCollection_Empty() {
		assertThrows(IllegalArgumentException.class, () -> exampleService.methodWithCollection(new ArrayList<String>()));
	}

	@Test
	public void testMethodWithMultipleArgs_Null() {
		assertThrows(IllegalArgumentException.class, () -> exampleService.methodWithMultipleArgs(null, new ArrayList<>()));
		assertThrows(IllegalArgumentException.class, () -> exampleService.methodWithMultipleArgs("Oleg", null));
	}

	@Test
	public void testMethodWithMultipleArgs_Empty() {
		assertThrows(IllegalArgumentException.class, () -> exampleService.methodWithMultipleArgs("", new ArrayList<>()));
		assertThrows(IllegalArgumentException.class, () -> exampleService.methodWithMultipleArgs("Oleg", new ArrayList<>()));
	}

}
