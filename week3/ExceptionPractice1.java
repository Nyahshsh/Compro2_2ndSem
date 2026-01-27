package week3;

import java.util.*;

public class ExceptionPractice1 {
    public static void main(String[] args) {

        //
        try {
            // User Input
            Scanner sc = new Scanner(System.in);
            System.out.print("Please Enter a Number: ");
            int num = sc.nextInt();

            int x = num;
        } catch (Exception y) {
            System.out.println("Error: " + y.getMessage());
        }

        System.out.println("!!!End!!!");

    }

    public static void inputNumber(){
        int num;
        Scanner sc = new Scanner(System.in);

        while(true){
            try{
                num = sc.nextInt();
                return num;
            }catch(Exception e){
                sc.nextLine();
                System.out.println("Invalid Number");
                System.out.println("");
            }
        }
    }
}
