$(function(){
    /* 메인------------------------------------ */
    /* 인기레시피 tab menu */
    var recipe_tab = $('.home .tab_menu>li');
    var recipe_conts = $('.home .recipe_conts>div');
    recipe_conts.hide().eq(0).show();
    recipe_tab.click(function(e){
        e.preventDefault();
        var tg = $(this);
        var i = tg.index();
        recipe_tab.removeClass('on');
        tg.addClass('on');
        recipe_conts.hide();
        recipe_conts.eq(i).show();
    });
    /* 인기레시피 Swiper */    
    $(window).on('load', function () {
        main_swipers();  
    });
    var swiper = new Swiper(".recipe_tab", {
        slidesPerView: 'auto',
        spaceBetween: 10,
        pagination: {
            clickable: true,
        },
    });
    var swiper = new Swiper(".complete_photo_list_wrap", {
        slidesPerView : 'auto', 
        //spaceBetween: 10,
        pagination: {
            clickable: true,
        },
    });
    /* home page 안에 swiper 묶음 */
    function main_swipers(){
        var Swipers = [];
        $('.home .swiper').each(function(i) {
            var this_class = $(this).attr('class').replace('swiper ', '');;
            //console.log(this_class);
    
            Swipers[i] = new Swiper('.'+this_class, {
                slidesPerView : 'auto', 
                //spaceBetween: 15,
                pagination: {
                    clickable: true,
                },
          });
        });
    }
    /* 로그인------------------------------------ */
    /* login alert */
    var user_btn = $('.ui_fixed li.user');
    var login_alert = $('#login_alert');
    user_btn.click(function(){
        login_alert.show();
        // 차후 로그인 여부에 따라 보여지는 페이지 다르게 수정
    });
    login_alert.each(function(){
        var btns = $('.btn_wrap a');
        btns.click(function(){
            login_alert.hide();
        });
    });

    /* login, join page */
    var user_id = $('#user_id');
    var user_pw = $('#user_pw');
    var join_user_name = $('#join_user_name');
    var join_user_id = $('#join_user_id');
    var join_user_pw = $('#join_user_pw');
    var join_user_ad = $('#join_user_ad');
    var pw_eye = $('.user_input_wrap .pw_eye');

    $('#user_id').on('input', login_input_ck);
    $('#user_pw').on('input', login_input_ck);
    $('#join_user_name').on('input', join_input_ck);
    $('#join_user_id').on('input', join_input_ck);
    $('#join_user_pw').on('input', join_input_ck);
    $('#join_user_ad').on('input', join_input_ck);
    function login_input_ck() {
        var id_ck = user_id.val(); 
        var pw_ck = user_pw.val(); 
        var login_btn = $('.login_btn');

        if (id_ck === '' || pw_ck === '') {
            login_btn.removeClass('on');
        } else {
            login_btn.addClass('on');
        }
    }
    function join_input_ck() {
        var name_ck = join_user_name.val(); 
        var id_ck = join_user_id.val(); 
        var pw_ck = join_user_pw.val(); 
        var ad_ck = join_user_ad.val(); 
        
        var join_btn = $('.join_btn');

        if (name_ck === '' || id_ck === '' || pw_ck === '' || ad_ck === '') {
            join_btn.removeClass('on');
        } else {
            join_btn.addClass('on');
        }
    }
    pw_eye.click(function(){
        if($(this).hasClass('off')){
            user_pw.attr('type','text');
            $(this).removeClass('off');
        }else{
            user_pw.attr('type','password');
            pw_eye.addClass('off');
        }
    });
    /* 마이------------------------------------ */
    /* my page menu toggle */
    var my_menu = $('.my .my_menu_wrap>ul>li');
    my_menu.each(function(){
        $(this).click(function(){
            if($(this).hasClass('on')){
                $(this).find('.sub_menu').slideUp(300);
                $(this).find('>a span').css({"background-position":"-20px 0"});
                $(this).removeClass('on');
            }else{
                $(this).find('.sub_menu').slideDown(300);
                $(this).find('>a span').css({"background-position":"0 0"});
                $(this).addClass('on');
            };
        });
    });
    /* my pick list tab menu */
    var my_pick_tab = $('.my_pick .tab_menu>li');
    var my_pick_conts = $('.my_pick .list_wrap>div');
    my_pick_conts.hide().eq(0).show();
    my_pick_tab.click(function(e){
        e.preventDefault();
        var tg = $(this);
        var i = tg.index();
        my_pick_tab.removeClass('on');
        tg.addClass('on');
        my_pick_conts.hide();
        my_pick_conts.eq(i).show();
    });
    /* list text overflow... */
    var list_text_over = $('.my_pick .list_wrap .cont_info .tit');
    list_text_over.each(function(){
        var length = 25;
        if( $(this).text().length >= length){
            $(this).text($(this).text().substr(0,length)+'...');
        }
    });
    /* 검색 메인------------------------------------ */
    /* search word remove button */
    var search_recent = $('.search .recent_wrap');
    search_recent.each(function(){
        var all_remove = $(this).find('.tit_wrap .all_remove');
        var word_remove = $(this).find('.search_word .remove');
        var search_word_nope = $(this).find('.nope');
        var search_word = $(this).find('.search_word');
        
        all_remove.click(function(){
            search_word.remove();
            search_word_nope.show();
        });
        word_remove.click(function(){
            $(this).parent('.search_word').remove();
            if($('.search_word').length===0){
                search_word_nope.show();
            }
        });
    });
    /* 레시피 작성------------------------------------ */
    /* recipe write */
    /* recipe write select box */
    var write_select = $('.recipe_write .select_box_btn');
    var write_select_box = $('.recipe_write .select_box');
    var select_default = $('.recipe_write .sub_tit .default');
    var write_plus_btn = $('.recipe_write .write_input .plus_btn');
    var recipe_write_submit = $('.recipe_write .header_wrap .recipe_save');    

    write_select.each(function(){
        $(this).click(function(){
            $(this).next(write_select_box).show();
        });
    });
    write_select_box.each(function(){
        var radio_btn = $(this).find('input[type=radio]');
        radio_btn.click(function(){
            if($(this).val()==$(this).val()){
                write_select_box.fadeOut(500);
            }
            var val = $(this).val();
            $(this).closest(write_select_box).prev(select_default).html('<p class="default">'+val+'</p><p class="icon"></p>');
        });
        
    });

    /* recipe plus button */
    write_plus_btn.each(function(){
        var li_count=2;
        $(this).click(function(){
            var stuff_line = `
            <div class="line stuff_line">
                <div class="input_wrap">
                    <input type="text" placeholder="예) 소고기" class="stuff">
                    <input type="text" placeholder="예) 300g" class="stuff_info">
                </div>
            </div>`;
            var spices_line = `
            <div class="line">
                <div class="input_wrap">
                    <input type="text" placeholder="예) 간장" class="spices">
                    <input type="text" placeholder="예) 1.5T" class="spices_info">
                </div>
            </div>`;

            li_count++;
            var recipe_line = `
            <div class="line">
                <div class="list_li">
                    <div class="list_text">
                        <p class="list_num">`+li_count+`</p>
                        <textarea name="recipe_list`+li_count+`" class="recipe_list ui-input-text ui-shadow-inset ui-body-inherit ui-corner-all ui-textinput-autogrow" placeholder="예) 소고기는 기름기를 떼어내고 적당한 크기로 잘라주세요."></textarea>
                    </div>
                    <div class="photo_plus_box">
                        <label for="recipe_img0`+li_count+`"><img src="images/insert_img.svg" alt="recipe_img0`+li_count+`" id="fileimg`+(li_count+6)+`"/></label>
						<input type="file" id="recipe_img0`+li_count+`" name="recipe_img0`+li_count+`" accept="image/*" onchange="loadFile(this,`+(li_count+6)+`)" class="input_file">
                    </div>
                </div>
            </div>`;
            

            if($(this).prev().hasClass('stuff_list_wrap')){
                $(this).prev().append(stuff_line); 
            }else if($(this).prev().hasClass('spices_list_wrap')){
                $(this).prev().append(spices_line); 
            }else{
                $(this).prev().append(recipe_line); 
            }
            recipe_write_textarea();
        });
    });

    $("textarea.recipe_list").textinput({autogrow: false});
    function recipe_write_textarea(){
        $('textarea.recipe_list').on('keyup', function (e) {
            $(this).css('height', 'auto');
            $(this).height(this.scrollHeight - 15);
        });
    };
    recipe_write_textarea();
    
    /* 사진 추가 부분 */
	// 메인 레시피 사진 부분
	$('.recipe_write .recipe_photo_wrap>label').one('click',function(event){
		$(this).css("padding","0");
        $(this).empty();
		$(`<img src="images/white.png" alt"" id="fileimg1"/>`).appendTo(this);
		$(this).off(event);
        
	});
    
    //요리 순서 및 완성 사진 등록
	var save_img;
	$(document).on('click','.recipe_write .photo_plus_box>label, .recipe_3 .review_plus>label',function(){
		var img = "images/insert_img.svg";
		var img1 = $(this).find('img').attr('src');
		
		if(img != img1){
			$(this).attr('for','');
			$('#delete_img').show();
			save_img = $(this);
		}
	});

    //등록 사진 삭제
	$('#delete_img .cancell').click(function(){
		$('#delete_img').hide();
	});
	$(document).on('click','#delete_img .check',function(){
		save_img.find('img').attr('src','images/insert_img.svg');
		var img_id = save_img.next().find('input').attr('id');
		if(img_id == null){
			img_id = save_img.find('img').attr('alt');
		}
		save_img.attr('for',img_id);
		$('#delete_img').hide();
	});

    recipe_write_submit.click(function(){
        //저장 후 원래 my pick 페이지로 이동 되게 수정 필요
        $("#recipe_write_form").submit();      
    });
    
    /* 인수------ */
    /* 레시피------------------------------------ */
    /* recipe list tab */
    var recipe_llist_tab = $('#recipe2_1 .recipe_tab ul li');
    var recipe_list_conts = $('#recipe2_1 .recipe_tabmenu>ul');
    recipe_list_conts.hide().eq(0).show();
    recipe_llist_tab.click(function(e){
        e.preventDefault();
        var tg = $(this);
        var i = tg.index();
        recipe_llist_tab.removeClass('on');
        tg.addClass('on');
        recipe_list_conts.hide();
        recipe_list_conts.eq(i).show();
    });
    var recipe_llist_tab2 = $('#recipe2_2 .recipe_tab ul li');
    var recipe_list_conts2 = $('#recipe2_2 .recipe_tabmenu>ul');
    recipe_list_conts2.hide().eq(0).show();
    recipe_llist_tab2.click(function(e){
        e.preventDefault();
        var tg = $(this);
        var i = tg.index();
        recipe_llist_tab2.removeClass('on');
        tg.addClass('on');
        recipe_list_conts2.hide();
        recipe_list_conts2.eq(i).show();
    });	
		
	//recipe swiper 전체 묶음
	var swiper = new Swiper(".recipe2 .recipe_tab, .recipe_review_img_list", {
        slidesPerView: 'auto',
        spaceBetween: 10
    });	
	var swiper = new Swiper(".cook_recipe_header", {
        slidesPerView: 1,
        //spaceBetween: 10
    });	
	
	$('.cook_recipe_header').mouseover(function(){
		$('.recipe_btn_left').show();
		$('.recipe_btn_right').show();
	});
	$('.cook_recipe_header').mouseout(function(){
		$('.recipe_btn_left').hide();
		$('.recipe_btn_right').hide();
	});

    /* review button */
	$('#review_button').click(function(){
		$('.cook_review_wrap').show();
	});
	
	$('.cook_review_close').click(function(){
		$('.cook_review_wrap').hide();
	});
	$('.cook_review_save').click(function(){
		$('.cook_review_wrap').hide();
	});
    
    var review_star_wrap = $('.cook_review_center .cook_review_star .star_wrap');
    var review_star_icon = $('.cook_review_center .cook_review_star .star_wrap .icon');
    review_star_wrap.each(function(){
        //review_star_icon.addClass('on');
		var index = $(this).index();
		var star = $(this).find('.star');
		star.tap(function(){
			review_star_icon.removeClass('on');
			star.addClass('on');
			if(star.hasClass('on')){
				var prev_input = $(this).parents('.ui-checkbox').prevAll();
				prev_input.find('.star').addClass('on');
			}
			//console.log(index+1);
		});
	});
    
    /* 희승------ */    
    /* 검색 서브페이지 기능 */
    /* list tab menu */
    var search_tab = $('#search_info_recipe_page .tab_menu>li');
    var search_conts = $('#search_info_recipe_page .food_cont');
    search_conts.hide().eq(0).show();
    search_tab.click(function(e){
        e.preventDefault();
        var tg = $(this);
        var i = tg.index();
        search_tab.removeClass('on');
        tg.addClass('on');
        search_conts.hide();
        search_conts.eq(i).show();
    });
	
	(()=>{
		var count = -1;
		var $push_product = $('#popup_push_product');
		var $push_product_btn = $('#search_map_page .push');
		var popup_text = $push_product.html();
		
		function delay(time) {
			return new Promise((resolve, reject) => {
				setTimeout(() => resolve(), time);
			})
		}		
		
		var arr_stoped = [];
		
		$push_product_btn.click(async function(){
			var p_p_text = '{{product}} 상품 '+(count+2)+'개를 <br/> 장바구니에 담았습니다.'
			$push_product.fadeIn()
			arr_stoped.push(false)
			count++;
			$push_product.html(p_p_text);
			var this_count = count;
			console.log(p_p_text);
			await delay(2000);
			if(arr_stoped[this_count+1] != false){
				$push_product.fadeOut()
				arr_stoped = [];
				count = -1;
			}
		})
		
	})(); 
});