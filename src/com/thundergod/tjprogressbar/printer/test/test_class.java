package com.thundergod.tjprogressbar.printer.test;



import com.thundergod.tjprogressbar.printer.TJProgressBar;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class test_class {
    static TJProgressBar printer = new TJProgressBar();
    public static void main(String[] args) {
        printer.getProgressBar(0,100,null,null,null,15);
    }
    public void runtest2(){
        printer.getProgressBar(0,2000, test_class.class,null,null,100);
    }
    public void runtest1(String URL,String FILE){


        try (BufferedInputStream in = new BufferedInputStream(new URL(URL).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(FILE)) {
            byte dataBuffer[] = new byte[1024*10];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024*10)) != -1) {
                printer.getProgressBar(0,bytesRead,test_class.class,URL,FILE,1024);
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            // handle exception
        }
    }
}
