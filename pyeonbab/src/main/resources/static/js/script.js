$(function(){
	$("#onboarding").touchSlider({
		initComplete : function (e) {
			var _this = this;
			var paging = $(".center>.paging");
			
			paging.html("");
			$(this).find(" > ul > li").each(function (i, el) {
				var num = (i+1) / e._view;
				if((i+1) % e._view == 0) {
					paging.append('<button type="button" class="btn_page">page' + num + '</button>');
				}
			});
			paging.find(".btn_page").bind("click", function (e) {
				_this.go_page($(this).index());
			});
		},
		counter : function (e) {
			$(".paging").find(".btn_page").removeClass("on").eq(e.current-1).addClass("on");
		},
		roll : false
	});
});