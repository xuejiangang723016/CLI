package com.demo;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xue
 * @version 1.0.0
 * @ClassName getInfo.java
 * @Description TODO
 * @createTime 2023 03 8 14:45:00
 */
public class getInfo {
    @Test
    public  void test() throws IOException, InterruptedException {

        Runtime runtime = Runtime.getRuntime();
        String[] exec = {"ping",  "www.baidu.com"};
        Process exec1 = runtime.exec(exec);
        exec1.waitFor();
        BufferedReader in = new BufferedReader(new InputStreamReader(exec1.getInputStream(),"GBK"));
        String line;
        StringBuilder output = new StringBuilder();
        while((line = in.readLine())!=null){
            output.append(line).append("\n");
        }
        String helpText = output.toString();
        System.out.println(helpText);
//        Map<String, String> map = new HashMap<>();
//        String regex = "\\[[a-zA-Z\\s-]*\\]";
//        Pattern compile = Pattern.compile(regex);
//        Matcher matcher = compile.matcher(helpText);
//        while (matcher.find()) {
//            String text = matcher.group();
//            String regex1 = "\\[(.*?)\\]";
//            Pattern memory = Pattern.compile(regex1);
//            Matcher matcher1 = memory.matcher(text);
//            while (matcher1.find()) {
//                String group = matcher1.group(1);
//                String[] s = group.split(" ");
//                if (s.length >= 2) {
//                    map.put(s[0], s[1]);
//                } else {
//
//                    String s1 = group.replaceAll("-", "");
//                    char[] chars = s1.toCharArray();
//                    for (char c : chars) {
//                        map.put("-" + c, null);
//                    }
//                }
//            }
//        }
//
//        map.entrySet().stream().forEach(Entry -> {
//            System.out.println(Entry.getKey() + "=======" + Entry.getValue());
//        });

    }

    @Test
    public void formatStr(){
        String data = "[-c 123]";
        Pattern compile = Pattern.compile("\\[|\\]");
        Matcher matcher = compile.matcher(data);
        String dat =matcher.replaceAll("");
        System.out.println(dat.split(" ")[0]);

    }
}
