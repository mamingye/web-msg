package com.weibo.message.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by REDUDPY on 2018/4/26.
 */
public class ReadUtil {
    public static List<String> readFile(MultipartFile file){
        List<String> list = new ArrayList<>();
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;

         try {
             //BufferedReader是可以按行读取文件

           inputStream =file.getInputStream();
           bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

             String str = null;
             while ((str = bufferedReader.readLine()) != null) {
                 list.add(str);
             }
         }catch (Exception e){
             throw  new RuntimeException(e);
         }finally {
             if(inputStream!=null){
                 try {
                     inputStream.close();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }

                 if (bufferedReader!=null){
                     try {
                         bufferedReader.close();
                     } catch (IOException e) {
                         e.printStackTrace();
                     }
                 }
             }
         }
         return list;

    }
    public static String readFileTStream(MultipartFile file){
        List<String> list = new ArrayList<>();
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;

        try {
            //BufferedReader是可以按行读取文件

            inputStream =file.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                list.add(str);
            }
        }catch (Exception e){
        	 e.printStackTrace();
        }finally {
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (bufferedReader!=null){
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;

    }
}
