package com.hmtmcse.test;

import java.net.MalformedURLException;
import java.net.URL;

public class PlayWithUrl {


    public static void main(String[] args) {
        String base = "http://hmtmcse.com/";
        String postfix = "/bash/tutorial";

        try {
            URL url = new URL(base);
            URL full = new URL(url, postfix);
            System.out.println(full.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}
