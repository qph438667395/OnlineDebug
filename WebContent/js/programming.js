$(document).ready(function(){
	$("#btn-run").on("click",function(){
		$.ajax({
			url:"udoRun",
			type:"post",
			data:{javaCode:editor.getValue(), input:$("#input").val()},
			timeout:500000,
			success:function(data){
				var result=eval("("+data+")");
				if(result.flag==true){
					$("#output").css("color","black");
				}else{
					$("#output").css("color","red");
				}
				$("#output").text(result.result);
			},
			error:function(){
				$("#output").css("color","red");
				$("#output").text("abnormal communication");}
		});

	});
});