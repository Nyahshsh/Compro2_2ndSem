import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final String HOST = "localhost";
    private static final int PORT = 8000;

    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PORT);
                PrintWriter out =

                        new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                Scanner input = new Scanner(System.in);) {

            // create a thread for listening to a server's broadcast
            Thread listener = new Thread(() -> {
                String serverMessage;
                try {
                    while ((serverMessage = in.readLine()) != null) {
                        System.out.println("\n " + serverMessage);
                        System.out.print("> "); // prompt the user
                    }
                } catch (IOException e) {
                    System.err.println("!!!CONNECTTION TO SERVER LOST!!!");
                }
            });
            listener.start();

            // main thread, handle user input
            System.out.print("> ");
            while (true) {
                String userInput = input.nextLine();
                out.println(userInput);

                if (userInput.equalsIgnoreCase("Goodbye")) {
                    break;
                }
            }

            System.out.println("Connection closing...");

        } catch (IOException e) {
            System.err.println("Client Error:" + e.getMessage());
        }

    }
}