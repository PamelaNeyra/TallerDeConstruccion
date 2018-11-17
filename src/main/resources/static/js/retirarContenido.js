$(document).ready( function () {
	
	$('[data-toggle="tooltip"]').tooltip(); 
	
	$("#menu-toggle").click(function (e) {
		e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
	
});