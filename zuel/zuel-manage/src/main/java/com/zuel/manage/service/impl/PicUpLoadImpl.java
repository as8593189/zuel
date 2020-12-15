package com.zuel.manage.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.zuel.common.vo.ZuelFileupLoad;
import com.zuel.manage.service.PicUploadService;

/*
 * 
 * @author:汪思超
 * @service:文件上传服务实现类
 * @date:2020.12.12
 * */
@Service
public class PicUpLoadImpl  implements PicUploadService{

	@Value("${zuel.picture.nginxServer}")
    private String nginxServer;
	
	@Override
	public Map<String, Object> uploadPicture(MultipartFile uploadFile) {
		Map<String, Object> result = new HashMap<>();
        try{
            // 上传图片
            String[] uploadResult =
                    ZuelFileupLoad.uploadFile(uploadFile.getInputStream(), uploadFile.getOriginalFilename());

            // 处理返回结果
            result.put("error", 0);
            result.put("url", nginxServer + uploadResult[0] + "/" + uploadResult[1]);

            return result;
        }catch(Exception e){
            e.printStackTrace();
        }

        result.put("error", 1);
        result.put("message", "上传图片失败，请稍后重试");
        return result;
	}

}
