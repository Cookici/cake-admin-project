package com.lxl.cakeadmin.service;

import com.aliyun.oss.OSS;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: cake-admin
 * @Package: com.lxl.cakeadmin.service
 * @ClassName: OssService
 * @Author: 63283
 * @Description:
 * @Date: 2023/12/9 20:57
 */

public interface OssService {

    String deleteOneFile(String deletePhotoUrl);

    List<String> deleteNotOneFile(List<String> deletePhotoUrls);

    Map<String, String> returnPolicy();
}
