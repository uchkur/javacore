// Передается набор аргументов n в виде массива строк String[] args, необходимо:
// 1. Распечать сумму символов в аргументе
// 2. Если каждый символ делиться на 10 без остатка, после суммы вывести + для каждого такого символа
// 3. Если символ умещается в byte, то вывести b для каждого такого символа
// 4. В конце необходимо сложить все значения всех символов всех аргументов и вывести в финальной строке результат

// Условия:
// Кол-во аргументов n, где n может принимать значения 0 <= n <= 10^32
// Размер каждой строки в массиве = s, где s от 1 до 20 символов

// Пример ввода "ABC", "BBB", "TTT" - не реальный пример, чисто для понимания какие параметры передаются.
// Для данного ввода не получите вывод ниже.
// Пример вывода работы программы:
// 300 +b+b+
// 1200 +++
// 700 +b+b+b++

public class Task1 {
    public static void main(String[] args) {
        long sum_char_codes = 0;
        long total = 0;
        String addon = " ";
        for (String arg:args)
        {
            System.out.println("String.length = " + arg.length());
            if (arg.length() > 20) continue; //по условию - аргументы должны быть не более 20 символов
                for (int i = 0; i < arg.length(); i++) {
                    sum_char_codes = sum_char_codes + arg.charAt(i);
                    if (arg.charAt(i)%10 == 0) {addon = addon + "+";}
                    if (arg.charAt(i) < 128) {addon = addon + "b";}
                    }
                System.out.println(sum_char_codes + addon);
                total=total+sum_char_codes;
                sum_char_codes = 0;
                addon=" ";
            }
            System.out.println("total: " + total);
        }

    public static void main_unicode(String[] args) {
        long sum_code_points_codes= 0;
        long total = 0;
        String addon = " ";
        for (String arg:args)
        {
            System.out.println(arg);
            int cpCount = arg.codePointCount(0, arg.length()); //истинная длина, число кодовых точек
            System.out.println("String.codePointCount = " + cpCount);
            int i = 0;
            int index = 0;
            int cp = 0;
            char c;
            while (i < cpCount)
            {
                index = arg.offsetByCodePoints(0, i);
                cp = arg.codePointAt(index);
                sum_code_points_codes = sum_code_points_codes + cp;
                if (cp%10 == 0) {addon = addon + "+";}
                if (cp < 128) {addon = addon + "b";}
                if (Character.isSupplementaryCodePoint(cp)) i += 2;
                else i++;
            }
            System.out.println(sum_code_points_codes + addon);
            total = total + sum_code_points_codes;
            sum_code_points_codes = 0;
            addon = " ";
        }
        System.out.println("total: " + total);
    }

    public static void horstman77(String arg) {
        String s = arg.substring(0, 3);
        System.out.println(s);
    }
    }
