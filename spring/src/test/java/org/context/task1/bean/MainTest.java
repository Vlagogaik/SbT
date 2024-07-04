package org.context.task1.bean;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@ExtendWith(SpringExtension.class)
class MainTest {
	@Autowired
	private ApplicationContext applicationContext;

	@ParameterizedTest
	@MethodSource("components")
	public void givenInScopeComponentsWhenSearchingInApplicationContextThenFindThem() {
		assertNotNull(components());
	}

	private Stream<Object[]> components() {
		return Stream.of(
				new Object[]{applicationContext.getBean(Cat.class)},
				new Object[]{applicationContext.getBean(Dog.class)},
				new Object[]{applicationContext.getBean("parrot1",Parrot.class)},
				new Object[]{applicationContext.getBean("parrot2",Parrot.class)},
				new Object[]{applicationContext.getBean(Human.class)}
		);
	}

}
