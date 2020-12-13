package com.zuel.manage.Controller;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

/*
 * 
 * @author:汪思超
 * @service:文件上传控制器api
 * @date:2020.12.12
 * */


public interface PicUploadController {

	
	Map<String, Object> uploadPicture(MultipartFile uploadFile);
	
}
