package com.mysite.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {

	public String restore(MultipartFile file) {
		
		//파일저장할 위치
		String saveDir="D:\\javaStudy\\file\\";
		
		//원 파일이름 찾기
		String orgName=file.getOriginalFilename();
		System.out.println("orgName: "+orgName);
		
		//확장자찾기
		String exName=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		System.out.println("exName: "+exName);
		
		//파일사이즈 찾기
		long fileSize=file.getSize();
		System.out.println("fileSize: "+fileSize);
		
		
		//유니크한 저장파일이름 만들기
		String saveName= System.currentTimeMillis()+UUID.randomUUID().toString()+exName;
		System.out.println("saveName: "+saveName);		
		
		//파일패스=저장되있는 위치
		String filePath=saveDir+saveName;
		System.out.println("filePath: "+filePath);
		
		//파일복사 파일패스에 실체가 저장되어 있어야지
		try {
			byte[] fileDate=file.getBytes();
			OutputStream out=new FileOutputStream(filePath);
			BufferedOutputStream bout=new BufferedOutputStream(out);
			
			bout.write(fileDate);
			bout.flush();
			if(bout != null) {
				bout.close();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return saveName;
	}
}
