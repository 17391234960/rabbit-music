package com.skyblue.rabbitmusic.service.impl;

import com.alibaba.fastjson.JSON;
import com.skyblue.rabbitmusic.dto.ImagePicDto;
import com.skyblue.rabbitmusic.entity.ImagePic;
import com.skyblue.rabbitmusic.mapper.ImagePicMapper;
import com.skyblue.rabbitmusic.repository.ImagePicRepository;
import com.skyblue.rabbitmusic.service.ImagePicService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ImagePicServiceImpl extends BaseService implements ImagePicService {

    private final Logger log = LoggerFactory.getLogger(ImagePicServiceImpl.class);


    private ImagePicRepository imagePicRepository;

    private ImagePicMapper imagePicMapper;

    private static final String url ="https://iw233.cn/API/pc.php?type=json";

    /**
     * 查询所有图片接口
     * @return List<ImagePicDto>
     */
    @Override
    public List<ImagePicDto> listAll() {
        return imagePicRepository.findAll()
                .stream().map(imagePicMapper::toDTO).collect(Collectors.toList());
    }

    /**
     * 创建接口
     * @param imagePicDto 创建request
     * @return ImagePicDto
     */
    @Override
    public ImagePicDto create(ImagePicDto imagePicDto) {
        ImagePic user = imagePicMapper.toEntity(imagePicDto);
        return imagePicMapper.toDTO(imagePicRepository.save(user));
    }


    /**
     * 获取图片url
     * @return url
     */
    public String getImageUrl() {
        return getRequireImageUrl();
    }

    /**
     * 校验图片是否可用
     * @return 图片数据
     */
    public String getRequireImageUrl() {
        String img = sendRequest(url, HttpMethod.GET.name());
        Map<String,Object> parse =  JSON.parseObject(img);
        Boolean pic = isImagesTrue((String) parse.get("pic"));
        int i = 0;
        while (!pic && i <= 10) {
            i++;
            img = sendRequest(url, HttpMethod.GET.name());
            parse =  JSON.parseObject(img);
            pic = isImagesTrue((String) parse.get("pic"));
        }
        return img;
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

    /**
     * 判断网络图片是否存在
     * imgUrl 图片地址链接
     */
    public Boolean isImagesTrue(String imgUrl) {

        int RESPONSE_CODE = 0;
        try {
            URL url = new URL(imgUrl);
            HttpURLConnection urlcon = (HttpURLConnection) url.openConnection();
            RESPONSE_CODE = urlcon.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (RESPONSE_CODE == HttpURLConnection.HTTP_OK) {
            log.info("图片可以访问 posted ok!");
            // 保存数据
            ImagePicDto imagePicDto = new ImagePicDto();
            imagePicDto.setPicUrl(imgUrl);
            imagePicDto.setPicName("");
//            User currentUserEntity = getCurrentUserEntity();
//            log.info("当前登陆用户信息: {}",currentUserEntity);
//            UserDto loginUser = SecurityUtils.getLoginUser();
//            imagePicDto.setRemark(loginUser.getUsername());
//            String principal = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//            log.error("logUser{}",principal);
            create(imagePicDto);
            return true;
        } else {
            log.error("图片不可以访问 Bad post...");
            return false;
        }
    }


}
