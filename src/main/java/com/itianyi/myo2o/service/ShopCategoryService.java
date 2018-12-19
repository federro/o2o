package com.itianyi.myo2o.service;

import java.util.List;

import com.itianyi.myo2o.entity.ShopCategory;

public interface ShopCategoryService {
	public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition);
}
