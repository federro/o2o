package com.itianyi.myo2o.web.shopadmin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itianyi.myo2o.dto.ShopExecution;
import com.itianyi.myo2o.entity.Area;
import com.itianyi.myo2o.entity.PersonInfo;
import com.itianyi.myo2o.entity.Shop;
import com.itianyi.myo2o.entity.ShopCategory;
import com.itianyi.myo2o.enums.ShopStateEnum;
import com.itianyi.myo2o.service.AreaService;
import com.itianyi.myo2o.service.ShopCategoryService;
import com.itianyi.myo2o.service.ShopService;
import com.itianyi.myo2o.util.CodeUtil;
import com.itianyi.myo2o.util.HttpServletRequestUtil;
import com.itianyi.myo2o.util.ImageUtil;

@Controller
@RequestMapping(value = "/shopadmin")
public class ShopManagenmentController {
	Logger logger = LoggerFactory.getLogger(ShopManagenmentController.class);
	
	@Autowired
	private ShopService shopService;
	@Autowired
	private ShopCategoryService shopCategoryService;
	@Autowired
	private AreaService areaService;
	
	@RequestMapping(value="/getshopinitinfo",method=RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> getShopInitInfo(){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		List<ShopCategory> shopCategoryList = new ArrayList<ShopCategory>();
		List<Area> areaList = new ArrayList<Area>();
		try{
			shopCategoryList = shopCategoryService.getShopCategoryList(new ShopCategory());
			areaList = areaService.getAreaList();
			modelMap.put("shopCategoryList", shopCategoryList);
			modelMap.put("areaList", areaList);
			modelMap.put("success", true);
		}catch(Exception e){
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		
		return modelMap;
	}
	
	@RequestMapping(value="/registershop",method=RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> registerShop(HttpServletRequest request){
		//1接收并转化相应的信息；
		Map<String, Object> modelMap = new HashMap<String, Object>();
		//接受请求的验证码，如果不对，则返回错误信息
		
		if(!CodeUtil.checkVerifyCode(request)){
			modelMap.put("success",false);
			modelMap.put("errMsg", "验证码输入错误");
			return modelMap;
		}
		
		
		//与前端商讨好字符名称
		String shopStr = HttpServletRequestUtil.getString(request,"shopStr");
		//前端传过来字段名称，要与bean的属性名称一致。
		ObjectMapper mapper = new ObjectMapper();
		Shop shop = null;
		try{
			//JSON to SHOP 的转换
			shop = mapper.readValue(shopStr, Shop.class);
		}catch(Exception e){
			logger.debug("转换shopBean失败");
			modelMap.put("success",false);
			modelMap.put("errMsg",e.getMessage());
			return modelMap;
		}
		
		CommonsMultipartFile shopImg = null;
		//使用multipartresolver从request上下文判断是否有上传文件
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		//如果有上传文件，强制转换
		if(commonsMultipartResolver.isMultipart(request)){
			MultipartHttpServletRequest httpServletRequest = (MultipartHttpServletRequest)request;
			shopImg = (CommonsMultipartFile)httpServletRequest.getFile("shopImg");
		}else{
			logger.debug("转换shopBean失败");
			modelMap.put("success",false);
			modelMap.put("errMsg","上传图片不能为空");
		}
		//2注册店铺
		if(shop != null && shopImg != null){
			//越少依赖前端信息越好
			//等于是在这里模拟一下传入的商店信息和图片信息
			//等于是第一步的数据没有用到，这里纯属模拟
			shop.setOwnerId(9L);
			
			//模拟图片信息
//			CommonsMultipartFile multipartFile = ImageUtil.getMutiPartFile("126.jpg");
			
			
//			ShopExecution se = 	shopService.addShop(shop, multipartFile);
			ShopExecution se = 	shopService.addShop(shop, shopImg);
			if(se.getState() == ShopStateEnum.CHECK.getState()){
				modelMap.put("success",true);
			}else{
				modelMap.put("success", false);
				modelMap.put("errMsg", se.getStateInfo());
			}
			return modelMap;
		}else{
			logger.debug("转换shopBean失败");
			modelMap.put("success",false);
			modelMap.put("errMsg","请输入店铺信息");
		}
		//3返回结果
		return null;
	}
	
	private static void inputStreamToFile(InputStream inputStream,File file){
		OutputStream outputStream = null;
		try{
			outputStream = new FileOutputStream(file);
			int bytesRead = 0;
			byte[] buffer = new byte[1024];
			while((bytesRead = inputStream.read(buffer)) != -1){
				outputStream.write(buffer, 0, bytesRead);
			}
		}catch(Exception e){
			throw new RuntimeException("调用inputStream异常"+e.getMessage());
		}finally {
			try{
			if(outputStream != null){
				outputStream.close();
			}
			if(inputStream != null){
				inputStream.close();
			}
			}catch(Exception e){
				throw new RuntimeException("关闭输入输出流异常"+e.getMessage());
			}
		}
	}
}
