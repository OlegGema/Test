//Oleg Gema
package com.test;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;

@SuppressWarnings("unchecked")

public class Query {

    private static File textFile(){
        return new File(Query.class.getResource("/text.txt").getFile());
    }

    public static JSONArray query(String q,int limit,int length){
        JSONArray result=new JSONArray();

        try {
            BufferedReader reader=new BufferedReader(new FileReader(textFile()));
            int chars=0;
            if (!q.isEmpty()){
                while (reader.ready()) {
                    String str = reader.readLine();

                    if (length == 0) {
                        if (str.toLowerCase().contains(q)) {

                            if (chars <= limit) {
                                chars+=str.length();
                                System.out.println(chars);
                                result.add(str);
                            }
                            else {
                                break;
                            }
                        }
                    }

                    if (length >0 ) {
                        if (str.toLowerCase().contains(q)) {

                            if(chars<=limit) {
                                if (str.length() <= limit) {
                                    if (length != 0 && length <= str.length()) {
                                        chars+=str.length();
                                        str = str.substring(0, length);
                                        result.add(str);
                                    } else if (str.length() <= length) {
                                        chars+=str.length();
                                        result.add(str);
                                    }
                                }
                            }else {
                                break;
                            }
                        }
                    }

                }
            }else {
                while (reader.ready()){
                    String str=reader.readLine();

                    if (length > 0 && str.length() > length || str.isEmpty())
                        continue;



                    result.add(str);
                }
            }
            reader.close();


        }catch (IOException e){
            e.printStackTrace();
        }
        return result;
    }
    public static JSONObject getMetaData() {

        JSONObject textFileAPI = new JSONObject();

        Path file = textFile().toPath();
        String fileName = file.getFileName().toString();
        BasicFileAttributes attributes;
        SimpleDateFormat date = new SimpleDateFormat("MMMM d, yyyy 'at' HH:mm aaa");
        int kilobytes;

        try {
            attributes = Files.readAttributes(file, BasicFileAttributes.class);
            if (attributes.size() < 1000)
                kilobytes = 1;
            else
                kilobytes = (int)(attributes.size() / 1024);

            textFileAPI.put("fileName", fileName);
            textFileAPI.put("fileSize", kilobytes + "KB");
            textFileAPI.put("metaData", date.format(attributes.creationTime().toMillis()));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return textFileAPI;
    }

}
