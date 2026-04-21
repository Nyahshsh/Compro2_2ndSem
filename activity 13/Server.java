import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private static final int PORT = 8000;
    private static List<PrintWriter> clients = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Server started on port " + PORT);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress());

                // create a thread for each client
                Thread clientThread = new Thread(() -> handleClient(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            System.err.println("Server Error: " + e.getMessage());
        }
    }

    private static void handleClient(Socket socket) {
        PrintWriter out = null;

        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // register client
            synchronized (clients) {
                clients.add(out);
            }

            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Client [" + socket.getInetAddress() + "]: " + message);

                if (message.equalsIgnoreCase("Goodbye")) {
                    broadcast("Client " + socket.getInetAddress() + " has left.");
                    break;
                }

                broadcast("Client [" + socket.getInetAddress() + "]: " + message);
            }

        } catch (IOException e) {
            System.err.println("Client connection error: " + e.getMessage());
        } finally {
            // unregister client on disconnect
            if (out != null) {
                synchronized (clients) {
                    clients.remove(out);
                }
            }
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Error closing socket: " + e.getMessage());
            }
            System.out.println("Client disconnected: " + socket.getInetAddress());
        }
    }

    private static void broadcast(String message) {
        synchronized (clients) {
            for (PrintWriter client : clients) {
                client.println(message);
            }
        }
    }
}