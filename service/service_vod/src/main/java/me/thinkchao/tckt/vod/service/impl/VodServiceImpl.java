package me.thinkchao.tckt.vod.service.impl;

import com.qcloud.vod.VodUploadClient;
import com.qcloud.vod.model.VodUploadRequest;
import com.qcloud.vod.model.VodUploadResponse;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.vod.v20180717.VodClient;
import com.tencentcloudapi.vod.v20180717.models.DeleteMediaRequest;
import com.tencentcloudapi.vod.v20180717.models.DeleteMediaResponse;
import me.thinkchao.tckt.exception.TcktException;
import me.thinkchao.tckt.vod.service.VodService;
import me.thinkchao.tckt.vod.utils.ConstantPropertiesUtil;
import org.springframework.stereotype.Service;

/**
 * Author:chao
 * Date:2023-11-09
 * Description:
 */
@Service
public class VodServiceImpl implements VodService {
    // 上传视频
    @Override
    public String uploadVideo() {

        VodUploadClient client = new VodUploadClient(ConstantPropertiesUtil.ACCESS_KEY_ID, ConstantPropertiesUtil.ACCESS_KEY_SECRET);

        //上传请求对象
        VodUploadRequest request = new VodUploadRequest();
        //设置视频文件的本地路径
        request.setMediaFilePath("D:\\11video\\tckt\\001.mp4");
        //任务流
        request.setProcedure("LongVideoPreset");
        try {
            //调用方法上传视频，指定地域
            VodUploadResponse response = client.upload("ap-guangzhou", request);
            //获取上传后的视频ID
            String fileId = response.getFileId();
            return fileId;
        } catch (Exception e) {
            // 业务方进行异常处理
            throw new TcktException(20001, "上传视频失败");
        }
    }

    // 删除视频
    @Override
    public void removeVideo(String fileId) {
        try{
            // 实例化一个认证对象，入参需要传入腾讯云账户secretId，secretKey,此处还需注意密钥对的保密
            Credential cred =
                    new Credential(ConstantPropertiesUtil.ACCESS_KEY_ID,
                            ConstantPropertiesUtil.ACCESS_KEY_SECRET);
            // 实例化要请求产品的client对象,clientProfile是可选的
            VodClient client = new VodClient(cred, "");
            // 实例化一个请求对象,每个接口都会对应一个request对象
            DeleteMediaRequest req = new DeleteMediaRequest();
            req.setFileId(fileId);
            // 返回的resp是一个DeleteMediaResponse的实例，与请求对象对应
            DeleteMediaResponse resp = client.DeleteMedia(req);
            // 输出json格式的字符串回包
            System.out.println(DeleteMediaResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
            throw new TcktException(20001, "删除视频失败");
        }
    }
}

















