$(function(){
    var qa_board = $('#board_list');
    $.ajax({
        url: '../data/qa_board_data.xml',
        dataType: 'xml',
        async: false,
        success:function(xml){
            //console.log(xml);
            var board  = $(xml);
            board.find('board_tr').each(function(){
                var board_tr = $(this);
                var board_num = board_tr.find('num').text();
                var board_subj = board_tr.find('subject').text();
                var board_writer = board_tr.find('writer').text();
                var board_date = board_tr.find('date').text();
                var board_cont_tit = board_tr.find('cont_tit').text();
                var board_cont_txt = board_tr.find('cont_txt').html();

                var b_tr = $('<div class="board_tr cf"></div>');
                var num = $('<div class="num fl"></div>').text(board_num);
                var subj = $('<div class="subject fl"></div>').text(board_subj);
                var writer = $('<div class="writer fl"></div>').text(board_writer);
                var date = $('<div class="date fl"></div>').text(board_date);
                var cont_wrap = $('<div class="cont_wrap fl"></div>');
                var cont_tit = $('<div class="cont_tit fl"></div>').text(board_cont_tit);
                var cont_txt = $('<div class="cont_txt fl"></div>').html(board_cont_txt);
                b_tr.append(num);
                b_tr.append(subj);
                b_tr.append(writer);
                b_tr.append(date);
                b_tr.append(cont_wrap);
                cont_wrap.append(cont_tit);
                cont_wrap.append(cont_txt);
                qa_board.append(b_tr);
                
            });
        }
    });

    var class_closed = 'closed';
	qa_board.each(function(){
		var board = $(this);
        var all_tr = board.find('.board_tr>div');
		var all_tit = board.find('.board_tr .subject');
		var all_cont = board.find('.cont_wrap');
		
		function closeAll(){
			all_tr.addClass(class_closed);
			all_tit.addClass(class_closed);
			all_cont.addClass(class_closed);
		}
		
		function open(tr,tit,cont){
			tr.removeClass(class_closed);
			tit.removeClass(class_closed);
			cont.removeClass(class_closed);
		}
		
		closeAll();
		
		all_tit.click(function(){
            var tit = $(this);
			var cont = tit.nextAll('.cont_wrap');
            var tr = tit.siblings('div');
			closeAll();
			open(tr,tit,cont);
		});
	});
});