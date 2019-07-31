$(document)
		.ready(
				function() {
					document.documentElement.style.overflow = 'hidden';
					$("#questionSubmit").on(
							"click",
							function() {
								$.ajax({
									type : "Post",
									url : "adoUpdateQuestion",
									data : $("#questionForm").serialize(),
									beforeSend : function() {
										$(".ERR_questionTitle").text("");
										$(".ERR_difficulty").text("");
										$(".ERR_questionContent").text("");
										$(".ERR_questionType").text("");
									},
									success : function(data) {
										var result = eval("(" + data + ")");
										$(".ERR_questionTitle").text(
												result.ERR_questionTitle);
										$(".ERR_questionContent").text(
												result.ERR_questionContent);
										$(".ERR_difficulty").text(
												result.ERR_difficulty);
										$(".ERR_questionType").text(
												result.ERR_questionType);
										if (null != result.resultStatus) {
											$("#modalContent").text(
													result.resultStatus);
											$("#addmodal").modal("show");
										}
									}
								});
								return false;
							});
					  (function($){
				            $.fn.serializeJson = function(){
				                var jsonData1 = {};
				                var serializeArray = this.serializeArray();
				                // 先转换成{"id": ["12","14"], "name": ["aaa","bbb"], "pwd":["pwd1","pwd2"]}这种形式
				                $(serializeArray).each(function () {
				                    if (jsonData1[this.name]) {
				                        if ($.isArray(jsonData1[this.name])) {
				                            jsonData1[this.name].push(this.value);
				                        } else {
				                            jsonData1[this.name] = [jsonData1[this.name], this.value];
				                        }
				                    } else {
				                        jsonData1[this.name] = this.value;
				                    }
				                });
				                // 再转成[{"id": "12", "name": "aaa", "pwd":"pwd1"},{"id": "14", "name": "bb", "pwd":"pwd2"}]的形式
				                var vCount = 0;
				                // 计算json内部的数组最大长度
				                for(var item in jsonData1){
				                    var tmp = $.isArray(jsonData1[item]) ? jsonData1[item].length : 1;
				                    vCount = (tmp > vCount) ? tmp : vCount;
				                }

				                if(vCount > 1) {
				                    var jsonData2 = new Array();
				                    for(var i = 0; i < vCount; i++){
				                        var jsonObj = {};
				                        for(var item in jsonData1) {
				                            jsonObj[item] = jsonData1[item][i];
				                        }
				                        jsonData2.push(jsonObj);
				                    }
				                    return JSON.stringify(jsonData2);
				                }else{
				                    return "[" + JSON.stringify(jsonData1) + "]";
				                }
				            };
				        })(jQuery);

					 $("#testCaseSubmit").on(
								"click",function(){
									
									  var jsonStr = $("#testCaseForm").serializeJson();
							            $.ajax({
							                url: "adoUpdateTestCase",
							                type: "POST",
							                contentType : "application/json;charset=utf-8", //设置请求头信息
							                dataType:"json",
							                data: jsonStr,
							                success : function(data){
							                	if(null!=data.checkResult){
							                		$("#modalContent").text(
							                				data.checkResult);
													$("#addmodal").modal("show");
							                	}else{
							                		if(null!=data.addResult){
							                			$("#modalContent").text(
							                					data.addResult);
														$("#addmodal").modal("show");
							                		}
							                	}                                      
							                	
							                },
							              
								});
							            return false;
				});
			});