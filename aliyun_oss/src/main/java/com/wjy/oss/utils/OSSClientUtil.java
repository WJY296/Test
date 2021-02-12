package com.wjy.oss.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;


import javax.xml.bind.SchemaOutputResolver;
import java.io.*;

public class OSSClientUtil {


	private static String endpoint = "oss-cn-hangzhou.aliyuncs.com";

	private static String accessKeyId = "LTAI4FyVni632yZbouNQiito";
	private static String accessKeySecret = "H1TzyWMM1UNslCuMpr5aJJFJvqdUBr";

	private static String bucketName = "wjy";

	private static OSS ossClient;

	public OSSClientUtil() {

		ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
	}

	/**
	 * 初始化
	 */
	public void init() {
		ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
	}

	/**
	 * 销毁
	 */
	public void destory() {
		ossClient.shutdown();
	}

	/**
	 * 上传文件
	 * @param file
	 * @return
	 */
	public static String upload(MultipartFile file) throws IOException {
		//文件名
		String filename = file.getOriginalFilename();
		//把文件转换为输入流
		InputStream inputStream = file.getInputStream();

		PutObjectResult result = ossClient.putObject(bucketName, filename, inputStream);
		System.out.println(result);

		// 关闭OSSClient。
		ossClient.shutdown();

		return "";
	}



}