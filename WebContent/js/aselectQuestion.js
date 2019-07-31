$(document).ready(function(){
	document.documentElement.style.overflow='hidden';
	$("#Pagination").paginate(
			{
				count : $("#pageCount").val(),
				start : 1,
				display : 5,
				border : true,
				border_color : '#fff',
				text_color : '#fff',
				background_color : 'black',
				border_hover_color : '#ccc',
				text_hover_color : '#000',
				background_hover_color : '#fff',
				images : false,
				mouse : 'press',
				onChange : function(page) {
					  $.ajax({   
			                type: "POST",  
			                dataType: "text",  
			                url: "afindQuestion"+page,      //提交到一般处理程序请求数据  
			                success: function(data) {                                   
			                    $("#result tr:gt(0)").remove();        //移除Id为Result的表格里的行，从第二行开始（这里根据页面布局不同页变）  
			                    $("#result").append(data);             //将返回的数据追加到表格  
			                }  
			            });              
				}
			});
	$("#deleteConfirm").on("click",function(){
		alert("12");
	});
});
