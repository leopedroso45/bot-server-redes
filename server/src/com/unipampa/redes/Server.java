package com.unipampa.redes;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Scanner;

class User {
    private String token;
    private String status;

    public User(String token) {
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public String getToken() {
        return token;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}



public class Server {

    HashMap<String, User> userList;


    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket();

        server.bind(new InetSocketAddress("localhost", 40289));

        InetAddress inet = server.getInetAddress();
        Console.log("HostAddress: " + inet.getHostAddress());
        Console.log("HostName: " + inet.getHostName());
        Console.log("Porta: " + server.getLocalPort());

        while (true) {
            Console.log("Esperando conexão");
            Socket cliente = server.accept();
            Console.log("Cliente conectou");

            Thread t = new Thread(() -> {
                try {
                    InputStreamReader inputStreamReader  = new InputStreamReader(cliente.getInputStream());
                    BufferedReader entrada = new BufferedReader(inputStreamReader);

                    PrintStream saida = new PrintStream(cliente.getOutputStream());
                    String linha = entrada.readLine();
                    while (linha!=null && !linha.equals("exit")) {
                        Console.log(linha);
                        saida.println("Server Response: "+linha);
                        linha = entrada.readLine();
                    }
                    saida.println("end");
                    Console.log("saiu");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            t.start();
        }


        /*Request getReq = new Request();
        //Runs sendReq passing in the url and port from the command line
        try {
//            getReq.sendReq("127.0.0.1", "/", 80);
            getReq.sendReq("localhost", "/api/about", 80);
        } catch (IOException e) {

            e.printStackTrace();
        }
*/

    }

    public static String sha256(String base) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}


class Request {


    public void sendReq(String url, String subpage, int port) throws IOException {


        Socket s = new Socket(InetAddress.getByName(url), 80);
        s.setKeepAlive(true);
        PrintWriter pw = new PrintWriter(s.getOutputStream());
        pw.println("DELETE " + subpage + " HTTP/1.1");
        pw.println("Host: " + url);
        pw.println("");
        pw.flush();
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String t;
        Console.log("indo");
        while ((t = br.readLine()) != null) Console.log(t);
        Console.log("foi");


    }
}