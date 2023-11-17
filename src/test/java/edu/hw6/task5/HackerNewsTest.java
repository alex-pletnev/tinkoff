package edu.hw6.task5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class HackerNewsTest {

    @Test
    void hackerNewsTopStoriesTest() {
        Assertions.assertDoesNotThrow(HackerNews::hackerNewsTopStories);
    }

    @Test
    void newsWithExistingIdExceptTitleTest() throws InterruptedException {
        var id = 38282950L;
        var excepted = "Push ifs up and fors down";
        var actual = HackerNews.news(id);
        Assertions.assertEquals(excepted, actual);
    }

    @ParameterizedTest
    @CsvSource({"-1", "32952935923592355"})
    void newsWithInvalidIdExceptNullTest(long id) throws InterruptedException {
        var actual = HackerNews.news(id);

        Assertions.assertNull(actual);
    }

}
