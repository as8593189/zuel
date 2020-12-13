package com.zuel.manage.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

/*
 * 
 * @author:汪思超
 * @service:文件上传服务api
 * @date:2020.12.12
 * */


public interface PicUploadService {

	
	Map<String, Object> uploadPicture(MultipartFile uploadFile);
	
}
