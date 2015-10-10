package com.suchorukov.java.httpserver;

// import java.io.IOException;
// import java.io.OutputStream;

import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;

/**
 * Created by IT-Academy on 08.10.2015.
 */
public class ClientClass implements Runnable {
    Socket s;
    private static String src = "resources" + File.separator;

    public ClientClass(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
//        System.out.println("run");


        try (Socket tmp = s) {

            OutputStream out = s.getOutputStream();
            InputStream in = s.getInputStream();

            StringBuilder sb = new StringBuilder();
            int c;
            while ((c = in.read()) != -1 && c != 10 && c != 13) {
                sb.append((char) c);
            }

            //получаем команду и ее аргументы
            String data = sb.toString();
            String args[] = data.split(" ");
            String cmd = args[0].trim().toUpperCase();
            String fileName = args[1].trim();
            // формируем заголовки ответа



            //пишем статус ответа
            out.write("HTTP/1.0 200 OK\r\n".getBytes());
            //минимально необходимые заголовки, тип и длина
            out.write("Content-Type: text/html\r\n".getBytes());
            out.write(("Content-Length: "+s.length()+"\r\n").getBytes());
            //пустая строка отделяет заголовки от тела
            out.write("\r\n".getBytes());
            //тело
            out.write(s.getBytes());
            out.flush();
            if(cmd == "GET"){

//                String s = "<html><title>test</title><body>Hello <b>world</b></body></html>";
                HTMLGen html = new HTMLGen();
            }
            else if(cmd == "HEAD"){

            }

            System.out.println(cmd);
//            System.out.println(sb);

            // пишем ответ  Hello world



//            boolean isFolder = dirNames.stream().filter(name -> fn.equals(name)).findAny().isPresent();
//                System.out.println(data);


            String content = new String();
            String str = new String();
            //тело


            // отправляем контетнт
            out.write(content.getBytes());
//            System.out.println(content);
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
//            if(s.isBound())
        }
    }

}


