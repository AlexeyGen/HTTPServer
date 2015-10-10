package com.suchorukov.java.httpserver;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by User4 on 05.10.2015.
 */
public class HTMLGen {
    private static String src = "resources" + File.separator;
    public static void main(String[] args) throws FileNotFoundException {
        String dirName = new File("resources").getAbsolutePath();
//        System.out.println(dirName);

        List<File> dirNames = new ArrayList();
        List<File> fileNames = new ArrayList();

        for(File file: new File(dirName).listFiles())
        {
            if(file.isDirectory())
                dirNames.add(file);
            else if(file.isFile())
                fileNames.add(file);
        }

        // сортируем
        // ??? лямбда быстрее ли, чем обычная функция, или нет?
        // ??? как протестить, что сортируется?
        Collections.sort(dirNames, (o1, o2) -> o1.toString().compareTo(o2.toString()));
        Collections.sort(fileNames, (o1, o2) -> o1.toString().compareTo(o2.toString()));

        File htmlFile = new File(src + "index.html");
        if(!htmlFile.exists())
            try {
                htmlFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        String content = new String();
        content += "<HTML>\n<HEAD>\n<style>td{min-width: 120px; }</style></HEAD>\n<BODY>\n<table>";
        content += "<tr><td colspan='3'><a href='javascript:void(0);'>..</a></td></tr>";

//        System.out.println("Выводим список всех директорий:");
        for( File file: dirNames)
        {
            content += buildRow(file);
        }

//        System.out.println("Выводим список всех файлов:");
        for( File file: fileNames)
        {
            content += buildRow(file);
        }

        content += "</table></BODY></HTML>\n";

        PrintWriter outFile = new PrintWriter(htmlFile.getAbsoluteFile());
        outFile.write(content);
        outFile.flush();
        outFile.close();

    }

////////////////////////////////////////////////////

    private static String buildRow(File file){
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        long fn = file.length();
        String sfn = Long.toString(fn);
        String fileLen = (fn > 0 ? sfn + " байт " : "") ;
        String fileName = getFileName(file);
        return "<tr><td><a href='" + file.getName() + "'>" + fileName + "</a></td><td>" + fileLen + "</td><td>" + sdf.format(file.lastModified()) + "</td></tr>";
    }

    private static String getFileName(File file){
        String rez="";
        if(file.isDirectory())
            // ??? почему нельзя делать двойной return?
            rez = "<b>" + file.getName() + "</b>";
        else if(file.isFile())
            rez = file.getName();
        return rez;
    }
}
