package com.itianyi.myo2o.service;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.itianyi.myo2o.dto.ShopExecution;
import com.itianyi.myo2o.entity.Shop;

public interface ShopService {
	ShopExecution addShop(Shop shop , CommonsMultipartFile file);
}
