import static org.junit.jupiter.api.Assertions.*;

class Task1Test {
    @org.junit.jupiter.api.Test
    void main() {
        System.out.println("Test main begin:");
        Task1.main( new String[] {"\uFF00\uFFF9\uFFFA", "\uFFCC\uFFF9\uFFFA"});
        System.out.println("Test main end;");
        System.out.println("==============================");
    }

    @org.junit.jupiter.api.Test
    void main_unicode() {
        System.out.println("Test unicode begin:");
        Task1.main_unicode( new String[] {"\uFF00\uFFF9\uFFFA", "\uFFCC\uFFF9\uFFFA"});
        System.out.println("Test unicode end;");
        System.out.println("==============================");
    }

}
