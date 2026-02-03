package Activity4;

import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

public class Grades {
    static String[] subjects;
    static Double[][] grades;

    public static void main(String[] args) {
        subjects = new String[50];
        grades = new Double[50][3];

        System.out.println("={MAIN MENU}=");
        Scanner sx = new Scanner(System.in);
        System.out.println("[1] Add Grade for Subject\n [2] Exit");
        int input = sx.nextInt();

        if

        try (Scanner sc = new Scanner(System.in)) {
            for (int r = 0; r < 3; r++) {
                System.out.print("Enter Subject: ");
                subjects[r] = sc.nextLine();

                System.out.print("Enter Prelim Grade: ");
                try {
                    grades[r][0] = sc.nextDouble();
                } catch (InputMismatchException e) {
                    System.out.println("!!!Invalid Number!!!");
                }

                System.out.print("Enter Midterm Grade: ");
                try {
                    grades[r][1] = sc.nextDouble();
                } catch (InputMismatchException e) {
                    System.out.println("!!!Invalid Number!!!");
                }

                System.out.print("Enter Finals Grade: ");
                try {
                    grades[r][2] = sc.nextDouble();
                } catch (InputMismatchException e) {
                    System.out.println("!!!Invalid Number!!!");
                }

                sc.nextLine();
                System.out.println();
            }
            writeGrade();
        }
    }

    public static void writeGrade() {
        StringBuilder sb = new StringBuilder();

        sb.append("Subject, Prelim, Midterm, Finals\n");
        for (int r = 0; r < subjects.length; r++) {
            if (subjects[r] == null)
                break;

            sb.append(subjects[r]);
            for (int c = 0; c < grades[r].length; c++) {
                sb.append(",  ").append(grades[r][c]);
            }
            sb.append("\n");
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("data.csv"))) {
            bw.write(sb.toString());
            bw.flush();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println(sb.toString());
    }
}
