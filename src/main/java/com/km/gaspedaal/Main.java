package com.km.gaspedaal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String args[]) {
        System.out.println("Calling...");
        URL url;
        try {
            url = new URL("https://www.gaspedaal.nl/volkswagen?srt=df-a");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            con.disconnect();
            filterAndPrint(content.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void filterAndPrint(String s) {
        String prefix = "<h4 class=\"font-bold text-xl ml-m flex-1 truncate pr-xs sm:ml-xs\">";
        Pattern pattern = Pattern.compile(prefix);
		Matcher matcher = pattern.matcher(s);
        int count = 0;
        int start, end;
        
        while(matcher.find()) {
            start = matcher.start();
            end = getPos(s, start);
            if(end > 0 && end > start) {
			    System.out.println(s.substring(start+prefix.length(), end));
                count++;
            }
        }
        System.out.println("count = "+count);
    }

    private static int getPos(String s, int pos) {
        Pattern pattern = Pattern.compile("</h4");
		Matcher matcher = pattern.matcher(s);
        while(matcher.find(pos)) {
            return matcher.start();
        }
        return 0;
    }
}
