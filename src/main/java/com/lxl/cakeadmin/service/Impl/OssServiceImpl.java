package com.lxl.cakeadmin.service.Impl;

import com.alibaba.fastjson2.JSONObject;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.DeleteObjectsResult;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.lxl.cakeadmin.config.OssConfig;
import com.lxl.cakeadmin.service.OssService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ProjectName: cake-admin
 * @Package: com.lxl.cakeadmin.service.Impl
 * @ClassName: OssServiceImpl
 * @Author: 63283
 * @Description:
 * @Date: 2023/12/9 20:57
 */
@Slf4j
@Service
public class OssServiceImpl implements OssService {

    @Autowired
    private OssConfig ossConfig;

    @Autowired
    private OSS ossClient;

    @Override
    public String deleteOneFile(String deletePhotoUrl) {
        try {
            URI uri = new URI(deletePhotoUrl.substring(1, deletePhotoUrl.length() - 1));
            String path = uri.getPath().substring(1);
            log.info("deletePhotoUrl ===> {}", path);
            if (ossClient == null) {
                ossClient = new OSSClientBuilder().build(ossConfig.getEndpoint(), ossConfig.getAccessId(), ossConfig.getSecretKey());
            }
            ossClient.deleteObject(ossConfig.getBucket(), path);
            return "删除成功";
        } catch (Exception e) {
            log.info("出现错误 ===> {}", e.getMessage());
            return "出现错误";
        }
    }


    @Override
    public List<String> deleteNotOneFile(List<String> deletePhotoUrls) {
        try {
            List<String> fileNameList = new ArrayList<>();
            for (String deletePhotoUrl : deletePhotoUrls) {
                URI uri = new URI(deletePhotoUrl.substring(1, deletePhotoUrl.length() - 1));
                String path = uri.getPath().substring(1);
                log.info("deletePhotoUrl ===> {}", path);
                fileNameList.add(path);
            }
            if (ossClient == null) {
                ossClient = new OSSClientBuilder().build(ossConfig.getEndpoint(), ossConfig.getAccessId(), ossConfig.getSecretKey());
            }
            DeleteObjectsResult deleteObjectsResult = ossClient.deleteObjects(new DeleteObjectsRequest(ossConfig.getBucket()).withKeys(fileNameList).withEncodingType("url"));
            return deleteObjectsResult.getDeletedObjects();
        } catch (Exception e) {
            log.info("出现错误 ===> {}", e.getMessage());
            return null;
        }
    }

    @Override
    public Map<String, String> returnPolicy() {
        // 填写Host地址，格式为https://bucketname.endpoint。
        String host = "https://" + ossConfig.getBucket() + "." + ossConfig.getEndpoint();
        // 设置上传到OSS文件的前缀，可置空此项。置空后，文件将上传至Bucket的根目录下
        String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String dir = format + "/";
        Map<String, String> respMap = null;
        try {
            long expireTime = 30;
            long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
            Date expiration = new Date(expireEndTime);
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

            String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes(StandardCharsets.UTF_8);
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = ossClient.calculatePostSignature(postPolicy);

            respMap = new LinkedHashMap<>();
            respMap.put("accessid", ossConfig.getAccessId());
            respMap.put("policy", encodedPolicy);
            respMap.put("signature", postSignature);
            respMap.put("dir", dir);
            respMap.put("host", host);
            respMap.put("expire", String.valueOf(expireEndTime / 1000));

        } catch (Exception e) {
            log.info("getPolicy error ===> {}", e.getMessage());
        }
        return respMap;
    }
}
