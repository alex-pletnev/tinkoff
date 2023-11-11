package edu.hw5;

import edu.hw5.task6.InvalidStringException;
import edu.hw5.task6.Task6;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Task6Test {

    Task6 task6;

    @BeforeEach
    void setUp() {
        task6 = new Task6();
    }

    @ParameterizedTest
    @CsvSource({"'abc','achfdbaabgabcaabg', true",
        "'xfdyn','zxcvbnmkjhgfdertyuiknbv', true",
        "'rie1','rivnjrjfivrfjvrjv vfjvfiv fvjnfie13456 }', true",
        "'efef','evdvdfbghbkbekbgkgfbkgbk', true",
        "'abc','yhinykkbkmyibmyhmi454888bkiki4m5i5i5hm6i', false",
        "'asdfghjk', , false",
        ", , false",
        ", 'ddfs', false"
    })
    void isSubsequenceTest(String subsequence, String sequence, boolean excepted) {
        var actual = task6.isSubsequence(subsequence, sequence);
        Assertions.assertEquals(excepted, actual);
    }

    @ParameterizedTest
    @CsvSource({"'a.*bc','achfdbaabgabcaabg'",
        "'xf{d}yn','zxcvbnmkjhgfdertyuiknbv'",
        "'ri$e1','rivnjrjfivrfjvrjv vfjvfiv fvjnfie13456 }'",
        "'efe\\f','evdvdfbghbkbekbgkgfbkgbk'",
        "'a.b.c','yhinykkbkmyibmyhmi454888bkiki4m5i5i5hm6i'"
    })
    void isSubsequenceInvalidStringTest(String subsequence, String sequence) {
        Assertions.assertThrows(InvalidStringException.class, () -> task6.isSubsequence(subsequence, sequence));
    }

}
