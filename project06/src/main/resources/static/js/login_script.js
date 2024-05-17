$(function(){
	var guideClass = 'guide';
	$('.user_input').each(function(){
		var giudeText = this.defaultValue;
		var element = $(this);
        var user_id = $('.user_id');
        var user_pw = $('.user_pw');
		user_id.focus(function(){
			if(user_id.val()===giudeText){
				user_id.val('');
				user_id.removeClass(guideClass);
			}
		})

        user_pw.focus(function(){
            if(user_pw.val()===giudeText){
				user_pw.val('');
				user_pw.removeClass(guideClass);
			}
            user_pw.attr('type', 'password');
        });

        element.blur(function(){
			if(element.val()===''){
				element.val(giudeText);
				element.addClass(guideClass);
                element.attr('type', 'text');
			}
		});

		if(element.val()===giudeText){
			element.addClass(guideClass);

		}
    });
});