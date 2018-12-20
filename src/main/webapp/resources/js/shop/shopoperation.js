/**
 * 
 */
$(function(){
	var initUrl = '../shopadmin/getshopinitinfo';
	var registerShopUrl = '../shopadmin/registershop';
		getShopInitInfo();
	function getShopInitInfo(){
		$.getJSON(initUrl,function(data){
			if(data.success){
				var tempHtml = '';
				var tempAreaHtml = '';
				data.shopCategoryList.map(function(item,index){
					tempHtml += '<option data-id ="' + item.shopCategoryId + '">' + item.shopCategoryName + '<option>';
				});
				
				data.areaList.map(function(item,index){
					tempAreaHtml += '<option data-id= "' + item.areaId +'">' + item.areaName + '</option>';
				});
				
				$('#shop-category').html(tempHtml);
				$('#shop-area').html(tempAreaHtml);
			}
		});
		
		$('#submit').click(function(){
			var shop = {};
			shop.shopName = $('#shop-name').val();
			shop.shopAddr = $('#shop-addr').val();
			shop.phone = $('#shop-phone').val();
			shop.shopDesc = $('#shop-desc').val();
			//瞅瞅,内部的就用对象来表示
			shop.shopCategory = {
					shopCategoryId : $('#shop-category').find('option').not(function(){
						return !this.selected;
					}).data('id')
			};
			shop.area = {
				areaId : $('#shop-area').find('option').not(function(){
					return !this.selected;
				}).data('id')	
			};
			
			var shopImg = $('#shop-img')[0].files[0];
			var formData = new FormData();
			formData.append('shopImg',shopImg);
			formData.append('shopStr',JSON.stringify(shop));
			
			var verifyCodeActual = $('#j_captcha').val();
			if(verifyCodeActual == null){
				alert("请输入验证码！");
				return;
			}
			formData.append('verifyCodeActual',verifyCodeActual);
			
			$.ajax({
				url:registerShopUrl,
				type:'POST',
				data:formData,
				contentType:false,
				processData:false,
				cache:false,
				success:function(data){
					//后台一定要有一个success的属性，其值为true或者false
					if(data.success){
						alert('提交成功！');
					}else{
						alert('提交失败' + data.errMsg);
					}
					$('#captcha_img').click();
				}
			})
		});
	}
})