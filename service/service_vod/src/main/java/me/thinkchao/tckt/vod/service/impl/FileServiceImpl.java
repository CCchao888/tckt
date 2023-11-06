package me.thinkchao.tckt.vod.service.impl;

import com.alibaba.fastjson.JSON;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import me.thinkchao.tckt.vod.service.FileService;
import me.thinkchao.tckt.vod.utils.ConstantPropertiesUtil;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

import static me.thinkchao.tckt.vod.utils.ConstantPropertiesUtil.BUCKET_NAME;
import static me.thinkchao.tckt.vod.utils.ConstantPropertiesUtil.END_POINT;

/**
 * Author:chao
 * Date:2023-11-05
 * Description:
 */
@Service
public class FileServiceImpl implements FileService {
    @Override
    public String upload(MultipartFile file) {
        // 1 初始化用户身份信息（secretId, secretKey）。
        String secretId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String secretKey = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置 bucket 的地域
        // clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法
        Region region = new Region(END_POINT);
        ClientConfig clientConfig = new ClientConfig(region);
        // 这里建议设置使用 https 协议
        // 从 5.6.54 版本开始，默认使用了 https
        clientConfig.setHttpProtocol(HttpProtocol.https);
        // 3 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);


        try{
            // 指定要上传的文件
            InputStream inputStream = file.getInputStream();
            // 指定文件将要存放的存储桶
            // 指定文件上传到 COS 上的路径，即对象键。例如对象键为folder/picture.jpg，则表示将文件 picture.jpg 上传到 folder 路径下
            String key = UUID.randomUUID().toString().replaceAll("-","")+
                    file.getOriginalFilename();
            String dateUrl = new DateTime().toString("yyyy/MM/dd");
            key = dateUrl+"/"+key;

            ObjectMetadata objectMetadata = new ObjectMetadata();
            PutObjectRequest putObjectRequest =
                    new PutObjectRequest(BUCKET_NAME, key, inputStream,objectMetadata);
            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
            System.out.println(JSON.toJSONString(putObjectResult));
            //https://tckt-thinkchao-1322177530.cos.ap-guangzhou.myqcloud.com/%E7%81%8C%E7%AF%AE%E9%AB%98%E6%89%8B4.jpg
            String url = "https://"+BUCKET_NAME+"."+"cos"+"."+END_POINT+".myqcloud.com"+"/"+key;
            return url;
        } catch (Exception clientException) {
            clientException.printStackTrace();
            return null;
        }

    }
}
