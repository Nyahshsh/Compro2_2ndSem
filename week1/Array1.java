import java.util.*;
public class Array1 {
    public static void main(String[] args){

        // Create Array
        int[] numbers = {3, 6, 9, 67, 69, 39, 79, 96, 99, 77};
        boolean found = false;
        int foundIndex = -1;

        // Scanner for User Input
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number baby ;> ");
        int input = sc.nextInt();
        
        // Create For Loop
        for (int i = 0; i < numbers.length; i++){

            //Find the user's number in the Array
            if (numbers[i] == input){
                found = true;
                foundIndex = i;
                break;
            }
        }

        // Display of Array
        for (int i = 0; i < numbers.length; i++){
            // Display Array
            System.out.print(numbers[i] + " ");
        }

        // Display of Index
        if (found){
            System.out.println(input + " was found at the index " + foundIndex + " baby :>");
        }
        else {
            System.out.println(input + " was not found in the array baby :<");
        }

    }
}