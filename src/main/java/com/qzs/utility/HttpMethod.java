package com.qzs.utility;

import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Administrator on 2016/5/14.
 */
@RestController
public class HttpMethod {
    /**
     * http请求的get方法
     *
     * @param url
     * @param param
     * @return
     */
    public static String doGet(String url, String param) {
        String jsonString = "";
        //String param = "rid=" +rid + "&pid=" + pid + "&page=10&size=10";
        try {
            String urlString = url + "?" + param;
            URL relUrl = new URL(urlString);
            URLConnection conn = relUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.connect();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";

            StringBuffer sb = new StringBuffer();

            while ((line = bufferedReader.readLine()) != null) {
                line = new String(line.getBytes(), "UTF-8");
                jsonString = jsonString + line;
                sb.append(line);

            }
//            System.out.println("数据流：" + jsonString);

        } catch (IOException e) {
            e.printStackTrace();
        }


        return jsonString;
    }


    public static String  doPost(String urlAddress, String param) {
        String result = "";
        System.out.println("post url :" + urlAddress + "?" + param);
        try {
            URL url = new URL(urlAddress);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setUseCaches(false);
            conn.setInstanceFollowRedirects(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            //conn.setRequestProperty("Cookie", "JSESSIONID=" + sessionid);
//			 conn.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
//	         conn.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格
            conn.connect();

            //post请求

            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.writeBytes(param);
            out.flush();
            out.close();
            //读取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String lines;
            StringBuffer sb = new StringBuffer("");
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), "UTF-8");
                result = result + lines;
                sb.append(lines);
            }
//            System.out.println("返回 ：：" + sb);
            reader.close();
            //断开连接
            conn.disconnect();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
        return result;
    }

}
