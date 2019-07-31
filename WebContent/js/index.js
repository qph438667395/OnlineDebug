$(document).ready(function() {
	document.documentElement.style.overflow='hidden';
	var size=$(".img li").size();
	for(var i=1;i<=size;i++){
		var li="<li>"+i+"</li>";
		$(".num").append(li);
	}
	var i=0;
	$(".img li").eq(0).show();
	$(".num li").eq(0).addClass("active");
	$(".num li").mouseover(function() {
		$(this).addClass("active").siblings().removeClass("active");
		var index = $(".num li").index(this);
		i=index;
		$(".img li").eq(index).stop().fadeIn().siblings().stop().fadeOut();
	});
	
	var t=setInterval(move,1500);
	function move(){
		i++;
		if(i==size){
			i=0;
		}
		$(".num li").eq(i).addClass("active").siblings().removeClass("active");
		$(".img li").eq(i).fadeIn().siblings().fadeOut();
	}
	function moveL(){
		i--;
		if(i==-1){
			i=size-1;
		}
		$(".num li").eq(i).addClass("active").siblings().removeClass("active");
		$(".img li").eq(i).fadeIn().siblings().fadeOut();
	}
	$("#content").hover(function(){
		clearInterval(t);
	},function(){
		t=setInterval(move,1500);
	});
	
	$(".left").click(function(){
		moveL();
	});
	
	$(".right").click(function(){
		move();
	})
});