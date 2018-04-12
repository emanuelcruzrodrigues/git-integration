$(document).ready(function(){
	$(".clickable-row").click(function() {
		window.open(
		 $(this).data("href"),
		  '_blank' // <- This is what makes it open in a new window.
		);
    });
});
