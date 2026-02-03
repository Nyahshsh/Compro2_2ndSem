
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        StringBuilder sb = new StringBuilder();

        try(Scanner sc = new Scanner(System.in)) {
            System.out.print("Please Enter your First Name: ");
            sb.append("First Name: " + sc.nextLine() + " \n");

            System.out.print("Please Enter your Last Name: ");
            sb.append("Last Name: " + sc.nextLine() + " \n");

            System.out.print("Please Enter your Age: ");
            sb.append("Age: " + sc.nextInt() + " \n");

            System.out.print("Please Enter your Email: ");
            sb.append("Email: " + sc.next() + " \n");

            System.out.print("Please Enter your Phone Number: ");
            sb.append("Phone Number: " + sc.next() + " \n");

        } catch (InputMismatchException e) {
            System.out.println("!!!Invalid Input!!!");
        }


        try(FileWriter fw = new FileWriter("data.txt")){
            fw.write(sb.toString());
            System.out.println("Data is saved...");
        }catch(IOException e){
            System.out.println(e.getMessage());

        }
    }
}