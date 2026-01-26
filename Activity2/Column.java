package Activity2;

import java.util.*;
public class Column {
    public static void main(String[] args) {

        //Problem 1
        //Declare 2D Array
        double[][] m = new double[3][4];

        //User Input
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a 3-by-4 matrix row by row:");

        //Format
        for(int i = 0; i < m.length; i++){
            for(int j = 0; j < m[i].length; j++){
                m[i][j] = sc.nextDouble();
            }
        }


        sumColumn(m, 0);
    

        // //Problem 2
        // //Declare 2D Array
        // double[][] n = new double[4][4];
        // Scanner sx = new Scanner(System.in);
        // System.out.println("Please enter a 4-by-4 matrix row by row:");

        // //Format
        // for(int x = 0; x < n.length; x++){
        //     for(int y = 0; y < n[x].length; y++){
        //         n[x][y] = sx.nextDouble();
        //     }
        // }

    }

    public static double sumColumn(double[][] m, int columnIndex) {

        //Display
        double sum = 0; 

        //For Loop 
        for(int row = 0; row < m.length; row++){
            sum = (double) (sum + m[row][columnIndex]);
            System.out.println("The Sum of the Elements at Column " + columnIndex + " is " + sum);
        }
        
        return sum;
    }

    // public static double sumMajorDiagonal(double[][] n) {
        
    // }
}
