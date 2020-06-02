import java.util.Scanner;

class student{
    private int RollNo;
    private String name;

    public  void AddData(){
        Scanner s = new Scanner(System.in);
        System.out.print("Enter name : ");
        String name = s.next();
        System.out.print("Enter Roll No. : ");
        RollNo = s.nextInt();
    }

    public void getData(){
        System.out.println(name);
        System.out.println(RollNo);
    }


    student(int RollNo, String name){
        this.RollNo = RollNo;
        this.name = name;
    }
    student(int RollNo){
        this.RollNo = RollNo;
    }
    student(String name){
        this.name = name;
    }
    student(){
        this.RollNo = 0;
        this.name = "";
    }

};

public class School {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int i, j;

        student obj[] = new student[10];
        for(i=0 ; i<obj.length ; i++){
            obj[i] = new student();
            obj[i].getData();
        }
    }
}
