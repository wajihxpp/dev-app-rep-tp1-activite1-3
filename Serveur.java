package serveur;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Le serveur est pr�t � �couter.");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connexion �tablie avec " + clientSocket.getInetAddress());

                BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);

                String operation = input.readLine();
                double value = Double.parseDouble(input.readLine());

                double result = calculate(operation, value);
                output.println(result);

                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static double calculate(String operation, double value) {
        switch (operation) {
            case "add":
                return value + 5;
            case "subtract":
                return value - 5;
            case "multiply":
                return value * 5;
            case "divide":
                return value / 5;
            default:
                return Double.NaN; // Op�ration non valide
        }
    }
}