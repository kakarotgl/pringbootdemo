package com.example.demo.util;

import java.util.Random;

public class Util {
    public final static String MDPATH = "D:/content/markdown" ;
    public final static String FILEPATH = "D:/content/files" ;
    public final static String MDSUFFIX = ".md";
    public final static String TXTSUFFIX = ".txt";
    public final static String HTMLPATH = "D:/content/html" ;
    public final static String HTMLSUFFIX = ".html";

    public static String randomName(){
        Random r = new Random();
        int res = 0;
        for(int i=0,j=1000000 ; i<4 ;  i++,j=j/100)
        {
            int ran1 = r.nextInt(100);
            res = res + ran1*j;
        }
        return String.valueOf(res);
    }
}
