package org.aspect;

import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class ExampleService {

    @NotEmpty
    public String methodWithString(String input) {
        return input + " created by Oleg";
    }

    @NotEmpty
    public void methodWithCollection(Collection<String> input) {
        // Выглядит как реализация метода
    }

    @NotEmpty
    public void methodWithMultipleArgs(String input1, Collection<String> input2) {
        // Выглядит как реализация метода
    }
}
