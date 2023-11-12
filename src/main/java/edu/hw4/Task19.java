package edu.hw4;

import edu.hw4.validate.ValidationError;
import edu.hw4.validate.Validator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Task19 {

    public Map<String, Set<ValidationError>> run(List<Animal> list) {
        Validator validator = new Validator();
        return list.stream()
            .filter(animal -> !validator.validate(animal).isEmpty())
            .collect(Collectors.toMap(
                Animal::name,
                animal -> validator.validate(animal)
            ));

    }
}
