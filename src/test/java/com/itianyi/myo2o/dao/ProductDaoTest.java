package com.itianyi.myo2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.itianyi.myo2o.entity.Product;
import com.itianyi.myo2o.entity.ProductCategory;
import com.itianyi.myo2o.entity.Shop;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductDaoTest extends BaseTest{
	
	//引入productDao，来与db进行交互
	//dao层不用spring注入，而是直接引入
	@Autowired
	private ProductDao productDao;
	
	@Test
	public void testAQueryProductList(){
		Shop shop = new Shop();
		shop.setShopId(20L);
		Product product = new Product();
		product.setShop(shop);
		List<Product> productList = productDao.queryProductList(product, 3, 2);
		for(Product product1 : productList){
			System.out.println(product1.getProductName());
		}
	}
	
	@Test
	public void testBQueryProductCount(){
		Shop shop = new Shop();
		shop.setShopId(20L);
		ProductCategory category = new ProductCategory();
		category.setProductCategoryId(11L);
		Product product = new Product();
		product.setShop(shop);
		product.setProductCategory(category);
		int count = productDao.queryProductCount(product);
		
		System.out.println(count);
	}
	
	@Test
	public void testCInsertProduct(){
		Shop shop1 = new Shop();
		shop1.setShopId(15L);
		Shop shop2 = new Shop();
		shop2.setShopId(16L);
		ProductCategory pc1 = new ProductCategory();
		pc1.setProductCategoryId(9L);
		ProductCategory pc2 = new ProductCategory();
		pc2.setProductCategoryId(9L);
		ProductCategory pc3 = new ProductCategory();
		pc3.setProductCategoryId(10L);
		Product product1 = new Product();
		product1.setProductName("测试1");
		product1.setProductDesc("测试Desc1");
		product1.setImgAddr("test1");
		product1.setPriority(0);
		product1.setEnableStatus(1);
		product1.setCreateTime(new Date());
		product1.setLastEditTime(new Date());
		product1.setShop(shop1);
		product1.setProductCategory(pc1);
		Product product2 = new Product();
		product2.setProductName("测试2");
		product2.setProductDesc("测试Desc2");
		product2.setImgAddr("test2");
		product2.setPriority(0);
		product2.setEnableStatus(0);
		product2.setCreateTime(new Date());
		product2.setLastEditTime(new Date());
		product2.setShop(shop1);
		product2.setProductCategory(pc2);
		Product product3 = new Product();
		product3.setProductName("测试3");
		product3.setProductDesc("测试Desc3");
		product3.setImgAddr("test3");
		product3.setPriority(0);
		product3.setEnableStatus(1);
		product3.setCreateTime(new Date());
		product3.setLastEditTime(new Date());
		product3.setShop(shop2);
		product3.setProductCategory(pc3);
		int effectedNum = productDao.insertProduct(product1);
		assertEquals(1, effectedNum);
		effectedNum = productDao.insertProduct(product2);
		assertEquals(1, effectedNum);
		effectedNum = productDao.insertProduct(product3);
		assertEquals(1, effectedNum);
	}
	
	
	@Test
	public void testDUpdateProduct(){
		Product product = new Product();
		Shop shop = new Shop();
		shop.setShopId(15L);
		product.setShop(shop);
		product.setProductId(17L);
		product.setNormalPrice("5002");
		productDao.updateProduct(product);
	}
	
	
	
	
	@Test
	public void testQueryProductByProductId(){
		Product product =  productDao.queryProductByProductId(5L);
		System.out.println(product.toString());
		System.out.println(product.getProductName());
	}
	
}
