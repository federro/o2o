/**
 * 
 */
$(function(){
	function getlist(e){
		$.ajax({
			url:"/o2o/shop/list",
			type:"get",
			dataType:"json",
			success:function(data){
				if(data.sucess){
					handleList(data.)
				}
			}
		})
	}
})