$(function(){
	/*tool tip*/
    var balloon = $('<div class="balloon"></div>').appendTo('body');
	function updateBallonPosition(x,y){
		balloon.css({left: x + 15, top: y + 20});
	}
	
	function showBalloon(){
		balloon.stop().css('opacity', 0).show().animate({opacity:1},300);
	}
	function hideBalloon(){
		balloon.stop().animate({opacity:0},300,function(){balloon.hide();});
	}
	
	$('[title]').each(function(){
		var element = $(this);
		var text = element.attr('title');
		element.attr('title','');
		element.hover(
			function(event){
				balloon.text(text);
				updateBallonPosition(event.pageX, event.pageY);
				showBalloon();
			},
			function(){
				hideBalloon();
			}
		);
		element.mousemove(function(event){
			updateBallonPosition(event.pageX, event.pageY);
		});
	});

    /*main menu*/
    $('#menu>ul>li').hover(
        function(){
            $(this).find('.menu_inner_bg').stop(true, true).slideDown(500);
        },
        function(){
            $(this).find('.menu_inner_bg').stop(false, false).slideUp();
        }
    );

	$('#gnb .ham_btn').click(function(e){
		e.preventDefault();
		$('.menu_all .menu_all_bg').fadeIn(200);
	});
	$('.menu_all_bg .ham_close_btn').click(function(e){
		e.preventDefault();
		$('.menu_all .menu_all_bg').fadeOut(200);
	});

	/*quick menu*/
	var quick_top = parseInt($('.quick_menu_wrap').css('top'));
	$(window).on('scroll', function(){
		var w_scroll_top = $(window).scrollTop();
		if(w_scroll_top>=150){
			$('.quick_menu_wrap').stop().animate({top:w_scroll_top+quick_top+'px'},500);
		}else{
			$('.quick_menu_wrap').stop().animate({top:quick_top+'px'},500);
		}
	});
	$('.quick_menu_wrap').css({top:'150px'},500);
	var q_menu_wrap = $('.quick_menu_wrap');
	var quick_toggle = $('.q_toggle');
	t_b=true;
	quick_toggle.on('click', function(){
		if(t_b==true){
			$('.q_menu_list').stop().animate({height:'0', opacity:'0'},500, function(){$('.q_menu_list a').hide();});
			$('.quick_txt').fadeIn(150);
			$('.quick_txt a').show();
			quick_toggle.css('background-image','url(images/quick_down_bk02.png)');
			t_b=false;
		}else{
			$('.quick_txt').fadeOut(150);
			$('.quick_txt a').hide();
			$('.q_menu_list a').show();
			$('.q_menu_list').stop().animate({height:'519', opacity:'1'},500);
			quick_toggle.css('background-image','url(images/quick_up_bk02.png)');
			t_b=true;
		}
		return false;
	});

	/*top button*/
	var top_btn = $('.top_btn');
	top_btn.hide();
	$(window).on('scroll', function(){
		var w_scroll_top = $(window).scrollTop();
		if(w_scroll_top>465){
			top_btn.fadeIn();
		}else{top_btn.fadeOut();}
	});
	top_btn.click(function(){
		$('html').animate({scrollTop:0},800);
	})
});