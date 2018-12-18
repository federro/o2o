package com.itianyi.myo2o.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Date;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.itianyi.myo2o.dao.BaseTest;
import com.itianyi.myo2o.dto.ShopExecution;
import com.itianyi.myo2o.entity.Area;
import com.itianyi.myo2o.entity.PersonInfo;
import com.itianyi.myo2o.entity.Shop;
import com.itianyi.myo2o.entity.ShopCategory;
import com.itianyi.myo2o.enums.ShopStateEnum;
import com.itianyi.myo2o.util.FileUtil;

public class ShopServiceTest extends BaseTest{
	@Autowired
	private ShopService shopService;
	
	@Test
	public void testAddShop() throws IOException{
		Shop shop = new Shop();
		PersonInfo personInfo = new PersonInfo();
		Area area = new Area();
		ShopCategory shopCategory = new ShopCategory();
		personInfo.setUserId(9L);
		area.setAreaId(7L);
		shopCategory.setShopCategoryId(25L);
		shop.setOwnerId(9L);
		shop.setArea(area);
		shop.setShopCategory(shopCategory);
		shop.setShopName("菲菲的小天地lalala");
		shop.setShopDesc("里面无所不有，有求必应");
		shop.setShopAddr("魔法学院2层东墙中央");
		shop.setPhone("9922334");
		//shop.setShopImg("test");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(ShopStateEnum.CHECK.getState());
		shop.setAdvice(ShopStateEnum.CHECK.getStateInfo());
		
		
		File file = new File("C:\\Users\\federro\\Pictures\\search\\125.jpg");
		FileItem fileItem = new DiskFileItem("mainFile", Files.probeContentType(file.toPath()), false, file.getName(), (int) file.length(), file.getParentFile());
		 
		try {
		    InputStream input = new FileInputStream(file);
		    OutputStream os = fileItem.getOutputStream();
		    IOUtils.copy(input, os);
		    // Or faster..
		    // IOUtils.copy(new FileInputStream(file), fileItem.getOutputStream());
		} catch (IOException ex) {
		    // do something.
			
		}
		 
		CommonsMultipartFile multipartFile = new CommonsMultipartFile(fileItem);

		ShopExecution ex = shopService.addShop(shop, multipartFile);
		assertEquals(ShopStateEnum.CHECK.getState(), ex.getState());
		
	}
	
	@Test
	public void testSeperator(){
		System.out.println(FileUtil.getImgBasePath());
	}
}
