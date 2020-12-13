package com.zuel.manage.Controller.Impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.zuel.manage.Controller.PicUploadController;
import com.zuel.manage.service.PicUploadService;

/*
 * 
 * @author:汪思超
 * @service:文件上传控制器实现类
 * @date:2020.12.12
 * */

@RestController
public class PicUploadControllerImpl implements PicUploadController {

	@Autowired
	private PicUploadService service;
	
	@PostMapping("/pic/upload")
	@Override
	public Map<String, Object> uploadPicture(@RequestParam("uploadFile") MultipartFile uploadFile) {
		// TODO Auto-generated method stub
		return service.uploadPicture(uploadFile);
	}

}
