import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ArraysStudyTest {
    @org.junit.jupiter.api.Test
    void transform() {
        int[] arr = {-1, 10, 10, 20, -21, 21, 1003, 100, 300, 345, 432, 1223, 1233};
        int groupSize = 2;
        System.out.println(Arrays.deepToString(ArraysStudy.transform(arr, groupSize)));
    }



}