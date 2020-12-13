package com.zuel.common.vo;

import java.io.InputStream;
import java.util.Properties;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

/*
 * 
 * @author:汪思超
 * @service:文件上传到fastdfs服务器
 * @date:2020.12.12
 * */

public class ZuelFileupLoad {

	private static StorageClient storageClient;
	static {
        try {
            Properties properties = new Properties();
            properties.load(
            		ZuelFileupLoad.class.getClassLoader().getResourceAsStream("fdfs.properties")
            );
            ClientGlobal.initByProperties(properties);
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getConnection();
            StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);
            storageClient = new StorageClient(trackerServer, storageServer);
        }catch(Exception e){
            e.printStackTrace();
            // 初始化错误。
            throw new ExceptionInInitializerError(e);
        }
    }
	
	public static String[] uploadFile(InputStream inputStream, String fileName){
        try {
            byte[] datas = new byte[inputStream.available()];
            inputStream.read(datas);
            String extName = fileName.substring(fileName.lastIndexOf(".") + 1);
            NameValuePair[] metaList = new NameValuePair[]{
                    new NameValuePair("fileName", fileName),
                    new NameValuePair("size", datas.length + "")
            };
            System.out.println("文件上传成功！文件为"+fileName);
            return storageClient.upload_file(datas, extName, metaList);
        }catch(Exception e){
            e.printStackTrace();
            return null; // 上传错误，返回null。
        }
    }
	
}
