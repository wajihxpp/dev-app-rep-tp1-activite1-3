package client;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            System.out.println("Op�rations disponibles :");
            System.out.println("1. Addition");
            System.out.println("2. Soustraction");
            System.out.println("3. Multiplication");
            System.out.println("4. Division");

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("S�lectionnez une op�ration (1/2/3/4) : ");
            String choice = reader.readLine();

            double value = 0;

            if (choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4")) {
                System.out.print("Entrez la valeur : ");
                value = Double.parseDouble(reader.readLine());
            } else {
                System.out.println("Op�ration non valide.");
                socket.close();
                return;
            }

            String operation = "";

            switch (choice) {
                case "1":
                    operation = "add";
                    break;
                case "2":
                    operation = "subtract";
                    break;
                case "3":
                    operation = "multiply";
                    break;
                case "4":
                    operation = "divide";
                    break;
            }

            output.println(operation);
            output.println(value);

            double result = Double.parseDouble(input.readLine());
            System.out.println("R�sultat : " + result);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
