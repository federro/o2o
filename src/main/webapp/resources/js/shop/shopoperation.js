/**
 * 
 */
$(function(){
	var initUrl = '/o2o/shopadmin/getshopinitinfo';
	var registerShopUrl = '/o2o/shopadmin/registershop'
	function getShopInitInfo(){
		$.getJSON(initUrl,function(data){
			if(data.success){
				var tempHtml = '';
				var tempArea = '';
				data.shopCategoryList.map(function(item,index){
					tempHtml += '<option data-id ="' + item.shopCategoryId + '">' + item.shopCategoryName + '<option>';
				});
				
				data.areaList.map(function(item,index){
					
				})
				

			}
		})
	}
})