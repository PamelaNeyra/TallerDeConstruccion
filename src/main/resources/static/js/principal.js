var lenguaje = {
	    "sProcessing":     "Procesando...",
	    "sLengthMenu":     "Mostrar _MENU_ registros",
	    "sZeroRecords":    "No se encontraron resultados",
	    "sEmptyTable":     "Ningún dato disponible en esta tabla",
	    "sInfo":           "Mostrando _START_ al _END_ de un total de _TOTAL_ registros",
	    "sInfoEmpty":      "Mostrando 0 al 0 de un total de 0 registros",
	    "sInfoFiltered":   "(filtrado de un total de _MAX_ registros)",
	    "sInfoPostFix":    "",
	    "sSearch":         "Buscar:",
	    "sUrl":            "",
	    "sInfoThousands":  ",",
	    "sLoadingRecords": "Cargando...",
	    "oPaginate": {
	        "sFirst":    "Primero",
	        "sLast":     "Último",
	        "sNext":     "Siguiente",
	        "sPrevious": "Anterior"
	    },
	    "oAria": {
	        "sSortAscending":  ": Activar para ordenar la columna de manera ascendente",
	        "sSortDescending": ": Activar para ordenar la columna de manera descendente"
	    }
}
var domTabla = '<"row"<"col-sm-4"B><"d-flex col-sm-5 justify-content-end"f><"d-flex col-sm-3 justify-content-end"l>><t><"row"<"col-sm-4"i><"d-flex felx-wrap col-sm-8 justify-content-end"p>>';
var tamañoMenu = [5, 10, 15, 20];
var alertaValidacion = $('<div class="alert alert-danger" id="validacion"></div>');

$(document).ready( function () {

	$('[data-toggle="tooltip"]').tooltip();
	
	$("#menu-toggle").click(function (e) {
		e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });

});