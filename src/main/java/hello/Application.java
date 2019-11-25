package hello;

public class Application {

    public static void main(String[] args) {
        new Thread(new UserInterface()).start();
    }

}
