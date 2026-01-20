package week2;
import java.util.Scanner;

public class Activity1 {
    public static void main(String[] args){

        welcomeUsers();
        showSeats();

    }

    public static void welcomeUsers(){
        //Theater Greetings
        System.out.println("*******************************************");
        System.out.println("!!!Welcome to Nyah's Theater!!!");
        System.out.println("*******************************************\n");
        System.out.println("These are the seats available:");
    }

    public static void showSeats(){
        //Create Array
        int[][] theaterRow = new int[1][8];
        theaterRow[0][0] = 1;
        theaterRow[0][1] = 2;
        theaterRow[0][2] = 3;
        theaterRow[0][3] = 4;
        theaterRow[0][4] = 5;
        theaterRow[0][5] = 6;
        theaterRow[0][6] = 7;
        theaterRow[0][7] = 8;

        //Show Available Seats
         for(int i = 0; i < theaterRow.length; i++){
            for(int j = 0; j < theaterRow[i].length; j++){
            System.out.printf("%-3s|  ", theaterRow[i][j]);
            }
        }
    }

    public static void 

    

    
    
}
