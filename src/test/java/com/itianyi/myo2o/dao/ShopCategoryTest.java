package com.itianyi.myo2o.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.itianyi.myo2o.entity.ShopCategory;

public class ShopCategoryTest extends BaseTest{

	@Autowired
	private ShopCategoryDao categoryDao;

	@Test
	public void testQueryShopCategory(){

		List<ShopCategory> categories = categoryDao.queryShopCategory(new ShopCategory());
		for(ShopCategory category : categories){
			System.out.println(category.getShopCategoryName());
		}
	}
}
