package com.itianyi.myo2o.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.itianyi.myo2o.dao.ShopDao;
import com.itianyi.myo2o.dto.ShopExecution;
import com.itianyi.myo2o.entity.Shop;
import com.itianyi.myo2o.enums.ShopStateEnum;
import com.itianyi.myo2o.service.ShopService;
import com.itianyi.myo2o.util.FileUtil;
import com.itianyi.myo2o.util.ImageUtil;

import o2o.ShopOperationException;

/**
 * 
* 1、后台添加店铺信息
*
* 创建人：FF     
* @version V1.0   
*
 */
@Service
public class ShopServiceImpl implements ShopService{

	@Autowired
	private ShopDao shopDao;
	
	@Override
	@Transactional
	public ShopExecution addShop(Shop shop, CommonsMultipartFile shopImg) {
		// TODO Auto-generated method stub
		
		if(shop == null ){
			return new ShopExecution(ShopStateEnum.NULL_SHOPID);
		}
		//TODO 判断shop当中的catalogry是否为空
		if(shop.getShopCategory() == null){
			return new ShopExecution(ShopStateEnum.NULL_SHOP_CATAGORY);
		}
		try{
			//赋值初始值
			shop.setEnableStatus(0);
			shop.setCreateTime(new Date());
			shop.setLastEditTime(new Date());
			int effectedNum = shopDao.insertShop(shop);
			if(effectedNum <= 0 ){
				//只有抛出runtime exception 才能切断事务，进行回滚
				throw new ShopOperationException("店铺创建失败");
			}else{
				if(shopImg != null){
					//图片不为空，存储图片到相应目录中去
					try{
						addShopImg(shop, shopImg);
					}catch(Exception e){
						throw new ShopOperationException("addImg error" + e.getMessage());
					}
					
					effectedNum = shopDao.updateShop(shop);
					if(effectedNum <=0 ){
						throw new ShopOperationException("updatIMg error");
					}
						}
			}
		}catch(Exception e)
		{
			throw new ShopOperationException("addShop error" + e.getMessage());
		}
		return new ShopExecution(ShopStateEnum.CHECK,shop);
	}

	private void addShopImg(Shop shop, CommonsMultipartFile shopImg) {
		// TODO Auto-generated method stub
		//获取shop图片路径的相对值路径
		String dest = FileUtil.getShopImagePath(shop.getShopId());
		//执行水印插入，并返回相对地址
		String shopImgAddr = ImageUtil.generateThumbnail(shopImg, dest);
		//shop将相对地址存入
		shop.setShopImg(shopImgAddr);
	}
	
}
