package edu.hw3;

import edu.hw3.task8.BackwardIterator;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Task8Test {

    @Test
    void backwardIteratorTest() {
        BackwardIterator<Integer> backwardIterator
            = new BackwardIterator<>(List.of(1, 2, 3));
        Assertions.assertEquals(3, backwardIterator.next());
        Assertions.assertEquals(2, backwardIterator.next());
        Assertions.assertEquals(1, backwardIterator.next());
        Assertions.assertFalse(backwardIterator.hasNext());

    }
}
