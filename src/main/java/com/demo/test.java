package com.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author xue
 * @version 1.0.0
 * @ClassName test.java
 * @Description TODO
 * @createTime 2023年04月04日 18:00:00
 */
public class test {
    public static void main(String[] args) throws Exception {

        String token = "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";
        String prompt = "how to use chatgpt-4?";
        String apiKeyHeader = "Bearer " + token;
        String url = "https://api.openai.com/v1/engines/davinci-codex/completions";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", apiKeyHeader);

        String postData = "{\"prompt\": \"" + prompt + "\", \"max_tokens\": 64}";

        // Send post request
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        os.write(postData.getBytes());
        os.flush();
        os.close();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        System.out.println(response.toString());
    }
}
