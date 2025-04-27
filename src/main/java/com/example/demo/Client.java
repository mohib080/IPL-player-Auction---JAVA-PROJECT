package com.example.demo;

import java.io.*;
import java.net.*;

public class Client {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int PORT = 12345;

    public static String sendRequest(String request) {
        try (Socket socket = new Socket("localhost", 12345); // Ensure the port matches the server's port
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.println("Sending request to server: " + request); // Debug log
            out.println(request); // Send request

            String response = in.readLine(); // Read response
            System.out.println("Received response from server: " + response); // Debug log

            return response;
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Return null if there is an error
        }
    }
}
