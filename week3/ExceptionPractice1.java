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

    }
}
