package org.context.task1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springs.contexts.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
@ExtendWith(SpringExtension.class)
class MainTest {
	private ApplicationContext applicationContext;
	@Test
	public void givenInScopeComponents_whenSearchingInApplicationContext_thenFindThem() {
		assertNotNull(applicationContext.getBean(Cat.class));
	}

}
