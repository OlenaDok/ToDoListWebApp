/**
 * 
 */
$(".dropdown-menu li a").click(function(e){
    var selText = $(this).text();
    $(this).parents('.input-group').find('.dropdown-toggle').html(selText+' <span class="caret"></span>');       
});

$(function() {
	
	$("#datetimepicker1").datetimepicker();
});