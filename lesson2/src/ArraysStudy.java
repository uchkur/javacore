/*
 * Одномерный сортированный массив преобразовать в двухмерный с учетом размера группы и переупорядочить.
 * Дано: Массив типа long[], размер массива - n, где 1 =< n =< 10^100000
 * Массив содержит в себе элементы а, 1 =< a =< 10^4
 * Массив изначально отсортирован, т.е. а1>= a0, a2 >= a1, и т.д.
 * Пример входных данных:
 * inputArray = [-1, 10, 10, 20, -21, 21, 1003]
 * groupSize = 2
 * Задача - трансормировать массив таким образом, чтобы:
 * a) массив превратился в двухмерный, размером n / groupSize
 * б) Сумма элементов в каждом подмассиве быть больше предыдущей
 * в) Если сумма элементов меньше 0, то данная группа исключается и принимает размер 0
 *
 * Пример вывода для данных выше:
 *
 * [1003, 21], т.к. 21 + 1003 = 1024, 1024 > 0
 * [10,10] 10+10 = 20, 20 > 0\
 * [] -21 + 20 = -1, -1 < 0
 * [] - 1 < 0
 *
 *
 *
 */


import java.util.Arrays;

public class ArraysStudy {
    public static void main(String[] args) {
        int[] arr = {-1, 10, 10, 20, -21, 21, 1003, 100, 300, 345, 432, 1223, 1233};
        int groupSize = 2;
        System.out.println(Arrays.deepToString(transform(arr, groupSize)));
    }
    public static int[][] transform(int[] arr, int groupSize) {
        int[][] twoDimArr = vector2da(arr, groupSize);
        int[][] sortedArr;
        sortedArr = sortByRowSum(removeLessThan(twoDimArr, 0));
        return (sortedArr);
    }

    private static int[][] vector2da (int[] a, int groupSize)
    {
        int N = (a.length + 1) / groupSize; //java array 0..~
        int[][] res = new int[N][];
        for (int i = 0; i < N; i++) {
            res[i] = Arrays.copyOfRange(a, i * a.length / N, (i + 1) * a.length / N);
        }
        return res;

    }

    private static int[][] removeLessThan (int[][] a, int lessThan)
    {
        int summa = 0;
        int k = 0;
        int l = 0;
        int m = 0;

        int[][] tmp = new int [a.length][];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                summa += a[i][j];
                l++;
            }
            if (summa >= 0) {
                tmp[k++] = Arrays.copyOfRange(a[i], 0, l);
                m++;
            }
            l = 0;
            summa = 0;
        }
        return Arrays.copyOfRange(tmp, 0, m);
    }

    private static int[][] sortByRowSum(int[][] arr) {
        int[][] ret = new int[arr.length][];
        for(int i = 0; i < arr.length - 1; i++) {
            for(int j = 0; j < arr.length - i - 1; j++) {
                if (rowSum(arr[j]) > rowSum(arr[j + 1])) {
                    int[] tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
        ret = Arrays.copyOfRange(arr, 0, arr.length);
        return ret;
    }

    private static int rowSum(int[] line) {
        int result = 0;
        for(int val : line) {
            result += val;
        }
        return result;
    }
}
