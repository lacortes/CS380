/**
*   Modified 
*/



import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public final class EchoServer {

    public static void main(String[] args) throws Exception {
        try (ServerSocket serverSocket = new ServerSocket(2222)) {
            while (true) {
                    Socket socket = serverSocket.accept();

                    Runnable r = () -> {
                        try {
                            String address = socket.getInetAddress().getHostAddress();
                            System.out.printf("Client connected: %s%n", address);

                            OutputStream os = socket.getOutputStream();
                            PrintStream out = new PrintStream(os, true, "UTF-8");
                            out.printf("Hi %s, thanks for connecting!%n", address);
                            
                            InputStream in = socket.getInputStream();
                            InputStreamReader inReader = new InputStreamReader(in, "UTF-8");
                            BufferedReader buffReader = new BufferedReader(inReader);

                            try {
                                while (true) { // Poll Client until 'exit'
                                    String info = buffReader.readLine();
                                    // System.out.println(info);

                                    out.println(info);

                                    if (info.equals("exit")) {
                                        // System.out.println("EXITING");
                                        System.out.printf("Address %s is disconnected.\n", address);
                                        break;
                                    }
                                }
                            } catch (NullPointerException e) {
                                System.out.printf("Address %s is disconnected.\n", address);
                            }
                        } catch (Exception e) {}
                    };
                    new Thread(r).start();
            }
        } 
    }
}
