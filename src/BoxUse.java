import java.rmi.StubNotFoundException;
import java.util.Scanner;

class Box{
    int height, length , width;

    public void volume(){
        System.out.println(height * length * width);
    }
}

class student{
    public int RollNo;
    public String name;

    student(int RollNo){
        this.RollNo = RollNo;
    }

    public void print(){
        System.out.println(name + " " + RollNo + " ");
    }
}

class Book{
    final int price = 10;
}

public class BoxUse {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Box b = new Box();
        b.height= 1;
        b.length = 2;
        b.volume();

        student child = new student(12);
        child.print();

        Book smallBook = new Book();
        smallBook.price = 20;
        System.out.println(smallBook.price);
    }
}
