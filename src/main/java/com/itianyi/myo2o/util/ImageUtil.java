package com.itianyi.myo2o.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import net.coobird.thumbnailator.Thumbnails;

public class ImageUtil {
	
	
	public static String generateThumbnail(CommonsMultipartFile thumbnail,String targetAddr){
		String realFileName = FileUtil.getRandomFileName();
		String extension = getFileExtension(thumbnail);
		makeDirPath(targetAddr);
		
		//相对路径=目标路径+随机文件名+后缀
		String relativeAddr = targetAddr + realFileName + extension;
		
		//绝对路径 = 基本路径+相对路径
		File dest = new File(FileUtil.getImgBasePath() + relativeAddr);
		
		//将图片保存到绝对路径
		try{
			Thumbnails.of(thumbnail.getInputStream()).size(200, 200).outputQuality(0.25f).toFile(dest);
		
		}catch(IOException e){
			throw new RuntimeException("创建缩略图失败" + e.toString());
		}
		
		return relativeAddr;
	}
	
	public static List<String> generateNormalImgs(List<CommonsMultipartFile> imgs , String targetAddr){
		int count = 0;
		List<String> realtiveAddrList = new ArrayList<String>();
		if(imgs != null && imgs.size()>0){
			makeDirPath(targetAddr);
			for(CommonsMultipartFile img : imgs){
				String realFileName = FileUtil.getRandomFileName();
				String extension = getFileExtension(img);
				String relativeAddr = targetAddr + realFileName + count + extension;
				File dest = new File(FileUtil.getImgBasePath() + relativeAddr);
				count++;
				try{
					Thumbnails.of(img.getInputStream()).size(600, 300).outputQuality(0.5f).toFile(dest);
				}catch(Exception e){
					throw new RuntimeException("创建图片失败：" + e.toString());
				}
				realtiveAddrList.add(relativeAddr);
			}
		}
		return null;
	}
	
	
	public static CommonsMultipartFile getMutiPartFile(String imgName){
		
		File file = new File("C:\\Users\\federro\\Pictures\\search\\"+imgName);
		try{
		FileItem fileItem = new DiskFileItem("mainFile", 
				Files.probeContentType(file.toPath()), 
				false, 
				file.getName(), 
				(int) file.length(), 
				file.getParentFile());
		 
		try {
		    InputStream input = new FileInputStream(file);
		    OutputStream os = fileItem.getOutputStream();
		    IOUtils.copy(input, os);
		    // Or faster..
		    // IOUtils.copy(new FileInputStream(file), fileItem.getOutputStream());
		} catch (IOException ex) {
		    // do something.
			throw new RuntimeException("转化图片multipartFile失败" + ex.getMessage());
		}
	 
		CommonsMultipartFile multipartFile = new CommonsMultipartFile(fileItem);
		return multipartFile;
		}catch(Exception e){
		throw new RuntimeException("转化图片multipartFile失败" + e.getMessage());
	}
		
	}
	
	public static String generateNormalImg(CommonsMultipartFile thumbnail,String targetAddr){
		
		String realFileName = FileUtil.getRandomFileName();
		String extension = getFileExtension(thumbnail);
		makeDirPath(targetAddr);
		String relativeAddr = targetAddr + realFileName + extension;
		File dest = new File(FileUtil.getImgBasePath() + relativeAddr);
		try {
			Thumbnails.of(thumbnail.getInputStream()).size(337, 640).outputQuality(0.5f).toFile(dest);
		} catch (IOException e) {
			throw new RuntimeException("创建缩略图失败：" + e.toString());
		}
		return relativeAddr;
	}
	
	

	private static void makeDirPath(String targetAddr) {
		// TODO Auto-generated method stub
		String realFileRarentPath = FileUtil.getImgBasePath() + targetAddr;
		File dirPath = new File(realFileRarentPath);
		if(!dirPath.exists()){
			dirPath.mkdirs();
		}
		
	}

	private static String getFileExtension(CommonsMultipartFile cFile) {
		// TODO Auto-generated method stub
		String originalFileName = cFile.getOriginalFilename();
		return originalFileName.substring(originalFileName.lastIndexOf("."));
		
	}
}