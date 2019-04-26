package com.unipampa.redes;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {

//        HostAddress: 127.0.0.1
//        HostName: localhost
//        Porta: 40289

        Request getReq = new Request();

        try {
//            getReq.sendReq("127.0.0.1", "/", 80);
            getReq.sendReq("127.0.0.1", "/", 40289);
        } catch (IOException e) {

            e.printStackTrace();
        }


    }

}


class Request {


    public void sendReq(String url, String subpage, int port) throws IOException {


        Socket s = new Socket(InetAddress.getByName(url), port);
        s.setKeepAlive(true);
        PrintWriter pw = new PrintWriter(s.getOutputStream());
        pw.println("POST " + subpage + " HTTP/1.1");
        pw.println("Host: " + url);
        pw.println("");
        pw.println("Agora vou mandar o comando de sair dessa porra");
        pw.println("exit");
        pw.flush();
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String t;
        System.out.println("Resposta: ");
        while ((t = br.readLine()) != null && !t.equals("end")){
            System.out.println(t);
        }
        System.out.println("Saiiiii");
    }
}