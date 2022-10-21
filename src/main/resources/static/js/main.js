/**
 * 
 */
 
 $(document).ready(function(){
	$('.table .editBtn').on('click', function(event){
		event.preventDefault();
		var href = $(this).attr('href');
		
		$.get(href,function(career, status){
			$('.myForm #name').val(career.name);
		})
		
		$('.myForm #staticBackdrop').modal();
	});
});