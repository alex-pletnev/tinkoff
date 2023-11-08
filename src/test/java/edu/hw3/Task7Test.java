package edu.hw3;

import edu.hw3.task7.NullStringComparator;
import java.util.TreeMap;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class Task7Test {

    @Test
    void nullStringComparatorTest() {
        TreeMap<String, String> tree = new TreeMap<>(new NullStringComparator());
        tree.put(null, "test");

        assertThat(tree.containsKey(null)).isTrue();
    }
}
