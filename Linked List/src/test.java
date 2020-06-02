import java.util.Scanner;
import java.io.*;

public class test {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("helloworld");
        clearConsole();
    }

    public static void clearConsole() {
        final String os = System.getProperty("os.name");
        try {
            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            }
        }
        catch (Exception e){
            System.out.println("Exception handled");
        }
    }

}
