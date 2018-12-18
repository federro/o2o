
$(function(){
	getlist();
	function getlist(e){
		$.ajax({
			url:"/o2o/superadmin/listarea",
			type:"get",
			dataType:"json",
			success:function(data){
				if(data.success){
					handleList(data.rows);
				}
			}
		});
	}
	function handleList(data){
		var html = '';
		data.map(function(item,index){
			html += '<div class="row row-shop"><div class="col=40">'
				+ item.areaName + '</div><div class="col=60">'
				+ item.areaDesc + '</div>'
		});
	}
})