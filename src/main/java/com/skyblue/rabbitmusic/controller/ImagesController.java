package com.skyblue.rabbitmusic.controller;

import com.alibaba.fastjson.JSON;
import com.skyblue.rabbitmusic.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/images")
public class ImagesController {
    private final Logger log = LoggerFactory.getLogger(ImagesController.class);

    private static final String url ="https://iw233.cn/API/pc.php?type=json";

    @GetMapping("/getImage")
    public Result<String> getImage() {
        String img = sendRequest(url, HttpMethod.GET.name());
        Map<String,Object> parse =  JSON.parseObject(img);
        String pic = (String) parse.get("pic");
        return Result.ok(pic);
    }


    public String sendRequest(String urlParam,String requestType) {

        HttpURLConnection con;

        BufferedReader buffer;
        StringBuilder resultBuffer;

        try {
            URL url = new URL(urlParam);
            //得到连接对象
            con = (HttpURLConnection) url.openConnection();
            //设置请求类型
            con.setRequestMethod(requestType);
            //设置请求需要返回的数据类型和字符集类型
            con.setRequestProperty("Content-Type", "application/json;charset=GBK");
            //允许写出
            con.setDoOutput(true);
            //允许读入
            con.setDoInput(true);
            //不使用缓存
            con.setUseCaches(false);
            //得到响应码
            int responseCode = con.getResponseCode();

            if(responseCode == HttpURLConnection.HTTP_OK){
                //得到响应流
                InputStream inputStream = con.getInputStream();
                //将响应流转换成字符串
                resultBuffer = new StringBuilder();
                String line;
                buffer = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                while ((line = buffer.readLine()) != null) {
                    resultBuffer.append(line);
                }
                return resultBuffer.toString();
            }

        }catch(Exception e) {
            e.printStackTrace();
        }
        Map<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("pic","");
        return JSON.toJSONString(stringObjectHashMap);

    }


}
