import static org.junit.jupiter.api.Assertions.*;

class Task1Test {
    @org.junit.jupiter.api.Test
    void main() {
        System.out.println("Test main begin:");
        Task1.main( new String[] {"Java\u2122"});
        System.out.println("Test main end;");
        System.out.println("==============================");
    }

    @org.junit.jupiter.api.Test
    void main_unicode() {
        System.out.println("Test unicode begin:");
        Task1.main_unicode( new String[] {"Java\u2122"});
        System.out.println("Test unicode end;");
        System.out.println("==============================");
    }
    @org.junit.jupiter.api.Test
    void horstrman77()
    {
        System.out.println("horstman77 begin:");
        Task1.horstman77("Hello");
        System.out.println("horstman77 end;");
        System.out.println("==============================");
    }

}
