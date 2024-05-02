/* time_event */
function unit(x){
	var m = x%10
	return m
}
function tenth(y){
	var m = y/10%10
	m = Math.floor(m)
	return m
}
function hund(z){
	var m = z/100
	m = Math.floor(m)
	return m
}
function dday_count(){
	var date = $('.d_day');
	var hour = $('.hour');
	var minute = $('.minute');
	var seconds = $('.seconds');

	doomsday = new Date('Octo 1, 2024 00:00:00')
	// Janu Febr Marc Apri May June July Augu Sept Octo Nove Dece
	today = new Date()
	howfar = doomsday - today
	
	if(howfar>0){
		setTimeout('dday_count()',1000)
	} else{
		clearTimeout('dday_count()')
		document.getElementById('time_out').innerHTML='이벤트 종료. 다음 이벤트를 기다려 주세요.'
		$('.time').addClass('off');
		return false
	}

	days = Math.floor(howfar/(1000*60*60*24))
	hours = Math.floor(howfar/(1000*60*60))
	mins = Math.floor(howfar/(1000*60))
	secs = Math.floor(howfar/(1000))

	d = days
	h = hours-days*24
	m = mins-hours*60
	s = secs-mins*60

	if(d<10){d='0'+d}else if(d<100){d='0'+d}
	if(h<10){h='0'+h}
	if(m<10){m='0'+m}
	if(s<10){s='0'+s}

	date.text(d);
	hour.text(h);
	minute.text(m);
	seconds.text(s);
}

$(function(){
	//$('.pop_wrap').hide();
	/* pop */
	var pop_all_close = $('#pop .pop_all_close');
	var pop_close = $('.pop_close_wrap .pop_close');
	var pop_checkbox = $('.pop_box input[type="checkbox"]');

	pop_all_close.click(function(){
		$('.pop_wrap').hide();
	});
	pop_close.on('click', function(){
		$(this).parents('.pop_box').remove();
		pop_length();
		pop_noep();
	});

	/*popup cookie*/
	if($.cookie('event_pop1') == 'nope1'){
		$('#pop_ck1').parents('.pop_box').remove();
		pop_length();
	}
	if($.cookie('event_pop2') == 'nope2'){
		$('#pop_ck2').parents('.pop_box').remove();
		pop_length();
	}
	if ($.cookie('event_pop1') == 'nope1' && $.cookie('event_pop2') == 'nope2' && $('.pop_box').length == 0) {
		pop_all_close.trigger('click'); 
	}
	function pop_noep(){
		if(pop_checkbox.eq(0).is(":checked")){
			$.cookie('event_pop1', 'nope1', {expires:1});
		};
		if(pop_checkbox.eq(1).is(":checked")){
			$.cookie('event_pop2', 'nope2', {expires:1});
		};
	}
	function pop_length(){
		if($('.pop_box').length == 0) {
			pop_all_close.trigger('click'); 
		}
		if($('.pop_box').length == 1){
			$('#pop').css('width','420').css('margin-left','-210px');
			$('.box_margin').css('margin-left','0');
		}
	}

	window.open('popup.html', "", "width=440, height=596");

	/*banner*/
	var banner_bg = $('.banner_bg_list li');
	var banner = $('.banner_list>li');
	var bn_current = 0;
	var bn_setInterval;
	var bn_tg_num = $('.num_list .num');
	var bn_total_num = $('.num_list .total_num');
	var bn_pause = $('.banner_buts .pause_btn');
	var bn_play = $('.banner_buts .play_btn');
	var bn_prev = $('.banner_buts .prev_btn');
	var bn_next = $('.banner_buts .next_btn');
	bn_total_num.append(' / '+banner.size());
  
	function move(tg, start, end) { 
		tg.css('left', start).stop().animate({left: end},{duration:500, ease:'easeOutCubic'});
	};
	function left_but (){
	  var prev = banner.eq(bn_current);
	  var bg_prev = banner_bg.eq(bn_current);
	  move(prev, '0%', '-100%');
	  bg_prev.fadeOut();
	  bn_current--;
	  if(bn_current == -1) bn_current=banner.size()-1;
	  bn_tg_num.text(bn_current+1);
	  var next = banner.eq(bn_current);
	  var bg_next = banner_bg.eq(bn_current);
	  move(next, '100%','0%');
	  bg_next.fadeIn();
	};
	function right_but (){
	  var prev=banner.eq(bn_current);
	  var bg_prev = banner_bg.eq(bn_current);
	  move(prev, '0%', '-100%');
	  bg_prev.fadeOut();
	  bn_current++;
	  if(bn_current == banner.size()) bn_current=0;
	  bn_tg_num.text(bn_current+1);
	  var next=banner.eq(bn_current);
	  var bg_next = banner_bg.eq(bn_current);
	  move(next, '100%','0%');
	  bg_next.fadeIn();
	};
	bn_prev.click(function(){
	  left_but();
	  interval_reset();
	});
	bn_next.click(function(){
	  right_but();
	  interval_reset();
	});
	function bn_timer(){
	  bn_setInterval = setInterval(function(){
		right_but();
		},3000);
	};
	bn_timer();
	function interval_reset(){
		clearInterval(bn_setInterval);
		bn_timer();
	};
	bn_play.hide();
	bn_pause.click(function () {
		clearInterval(bn_setInterval);
		bn_pause.hide();
		bn_play.show();
	});
	bn_play.click(function () {
		bn_timer();
		bn_play.hide();
		bn_pause.show();
	}); 

	/*main_event_service*/
	var event_service = $('#main_event_service');
	var evnt_wrap = $('.event_wrap');
	var service_wrap = $('.point_service_wrap');
	
	evnt_wrap.hover(
		function(){
			service_wrap.stop(true,true).animate({width:'10%', right:0},{duration:500, queue:false, easeing:'easeOutCubic'});
			$(this).find('.event_tit').stop(true,true).animate({width:'26%', left:0},{duration:500, queue:false, easeing:'easeOutCubic'});
			$(this).find('.event_box_bg').stop(true,true).hide(500);
			$(this).stop(true,true).animate({width:'90%', left:0},{duration:500, queue:false, easeing:'easeOutCubic'});
			$(this).find('.event_box_wrap').stop(true,true).animate({width:'100%', left:0},{duration:500, queue:false, easeing:'easeOutCubic'});
			$('.service_tit').stop(true,true).hide(500);
			$('.service_tit_off').stop(true,true).show(500);
		},
		function(){
			service_wrap.stop(true,true).animate({width:'705px', right:0},{duration:500, queue:false, easeing:'easeOutCubic'});
			$(this).find('.event_tit').stop(true,true).animate({width:'655px', left:0},{duration:500, queue:false, easeing:'easeOutCubic'});
			$(this).find('.event_box_bg').stop(true,true).show(500);
			$(this).stop(true,true).animate({width:'705px', left:0},{duration:500, queue:false, easeing:'easeOutCubic'});
			$(this).find('.event_box_wrap').stop(true,true).animate({width:'100%', left:0},{duration:500, queue:false, easeing:'easeOutCubic'});
			$('.service_tit').stop(true,true).show(500);
			$('.service_tit_off').stop(true,true).hide(500);
		}
	);
	service_wrap.hover(
		function(){
			evnt_wrap.stop(true,true).animate({width:'10%', right:0},{duration:500, queue:false, easeing:'easeOutCubic'});
			$(this).find('.service_tit').stop(true,true).animate({width:'26%', right:0},{duration:500, queue:false, easeing:'easeOutCubic'});
			$(this).find('.service_box_bg').stop(true,true).hide(500);
			$(this).stop(true,true).animate({width:'90%', left:0},{duration:500, queue:false, easeing:'easeOutCubic'});
			$(this).find('.service_box_wrap').stop(true,true).animate({width:'100%', left:0},{duration:500, queue:false, easeing:'easeOutCubic'});
			$('.event_tit').stop(true,true).hide(500);
			$('.event_tit_off').stop(true,true).show(500);
		},
		function(){
			evnt_wrap.stop(true,true).animate({width:'705px', right:0},{duration:500, queue:false, easeing:'easeOutCubic'});
			$(this).find('.service_tit').stop(true,true).animate({width:'655px', right:0},{duration:500, queue:false, easeing:'easeOutCubic'});
			$(this).find('.service_box_bg').stop(true,true).show(500);
			$(this).stop(true,true).animate({width:'705px', left:0},{duration:500, queue:false, easeing:'easeOutCubic'});
			$(this).find('.service_box_wrap').stop(true,true).animate({width:'100%', left:0},{duration:500, queue:false, easeing:'easeOutCubic'});
			$('.event_tit').stop(true,true).show(500);
			$('.event_tit_off').stop(true,true).hide(500);
		}
	);

	/* best_fee tab */
	var best_tab = $('.fee_tab_menu li');
	var bast_conts = $('.fee_list_wrap>div');

	bast_conts.hide().eq(0).show();
	best_tab.on('click',function(e){
		e.preventDefault();
		var tg = $(this);
		i = tg.index();

		best_tab.removeClass('active');
		tg.addClass('active');

		bast_conts.hide();
		bast_conts.eq(i).show();
	});

	var swiper = new Swiper(".fee_list01", { 
		slidesPerView: 2,
		spaceBetween: 20,
		navigation: {
			nextEl: ".fee01 .swiper-button-next",
			prevEl: ".fee01 .swiper-button-prev",
		},
	});
	var swiper = new Swiper(".fee_list02", { 
		slidesPerView: 2,
		spaceBetween: 20,
		navigation: {
			nextEl: ".fee02 .swiper-button-next",
			prevEl: ".fee02 .swiper-button-prev",
		},
	});
	var swiper = new Swiper(".fee_list03", { 
		slidesPerView: 2,
		spaceBetween: 20,
		navigation: {
			nextEl: ".fee03 .swiper-button-next",
			prevEl: ".fee03 .swiper-button-prev",
		},
	});
	var swiper = new Swiper(".fee_list04", { 
		slidesPerView: 2,
		spaceBetween: 20,
		navigation: {
			nextEl: ".fee04 .swiper-button-next",
			prevEl: ".fee04 .swiper-button-prev",
		},
	});
	var swiper = new Swiper(".fee_list05", { 
		slidesPerView: 2,
		spaceBetween: 20,
		navigation: {
			nextEl: ".fee05 .swiper-button-next",
			prevEl: ".fee05 .swiper-button-prev",
		},
	});
	var swiper = new Swiper(".fee_list06", { 
		slidesPerView: 2,
		spaceBetween: 20,
		navigation: {
			nextEl: ".fee06 .swiper-button-next",
			prevEl: ".fee06 .swiper-button-prev",
		},
	});
   
    /*phone_join tab*/
    $('.join_conts').each(function(){
		var top_div = $(this);
		var anchors = top_div.find('.join_tab_menu a'); 
		var panel_divs = top_div.find('.join_box');
		var lastAnchor;
		var lastPanel;
		
		anchors.show();
		lastAnchor = anchors.filter('.active');
		lastPanel = $(lastAnchor.attr('href'));
		panel_divs.hide();
		lastPanel.show();
		
		anchors.click(function(event){
			event.preventDefault();
			var currentAnchor = $(this);
			var currentPanel = $(currentAnchor.attr('href'));
			
			if(currentAnchor.get(0) === lastAnchor.get(0)){
				return;
			}
			lastPanel.stop().slideUp(200,function(){
				lastAnchor.removeClass('active');
				currentAnchor.addClass('active');
				currentPanel.stop().slideDown(200);
				lastAnchor = currentAnchor;
				lastPanel = currentPanel;
			});
		});
	});

    /*inquiry bn*/
    var interval = 3500;
    $('.inquiry_bn_list').each(function(){
        var timer;
        var container = $(this);
        function switchImg(){
            var anchors = container.find('.inquiry_bn');
            var first = anchors.eq(0);
            var second = anchors.eq(1);
            first.fadeOut().appendTo(container);
            second.fadeIn();
        }
        function startTimer(){
            timer = setInterval(switchImg, interval);
        }
        function stopTimer(){
            clearInterval(timer);
        }
        container.find('.inquiry_bn').hover(stopTimer,startTimer);
        startTimer();
    });

	/* inquiry_bn weather */
	$.getJSON('http://api.openweathermap.org/data/2.5/forecast?id=1846898&appid=f9ff5111d90e0b7ca9a953b6af75792a&units=metric', function(data){
		var $min_temp = data.list[0].main.temp_min;
		var $max_temp = data.list[0].main.temp_max;
		var $w_temp = data.list[0].main.temp;
		var $now = new Date(Date.now());
		var month = $now.getMonth()+1;
		var $w_date = $now.getFullYear() + '/' + month + '/' + $now.getDate();
		var $now_time = $now.getHours() + '시' + $now.getMinutes() + '분'
		var hour = $now.getHours();
		var $w_icon = data.list[0].weather[0].icon;
		//var $w_city = data.city.name;
		
		$('.w_temp').append($w_temp+' ℃');
		$('.min_max_temp').append('최저 기온'+$min_temp+' ℃'+' | '+'최고 기온'+$max_temp+' ℃');
		$('.w_max').append();
		$('.weather_bn .now_date').append($w_date);
		$('.now_time').append($now_time);
		$('.w_icon').append('<img src="http://openweathermap.org/img/wn/'+$w_icon+'.png"/>');
		$('.w_city').append('안양시');
		
		if(hour>=4&&hour<=12){//오전
			$('.weather_bn').css("background-image","url(images/weacher_am.png)");
		} else if(hour>=18){//오후
			$('.weather_bn').css("background-image","url(images/weacher_pm.jpg)").css('background-position-y', '-15px');
		} else if(hour<4){//오후
			$('.weather_bn').css("background-image","url(images/weacher_pm.jpg)").css('background-position-y', '-15px');
		} else{
			$('.weather_bn').css("background-image","url(images/weacher_am_pm.png)").css('background-position-y', '-80px');
		}
		
	});
});