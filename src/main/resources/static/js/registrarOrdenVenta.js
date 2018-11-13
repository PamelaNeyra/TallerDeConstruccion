var idCliente = '';
var nombreCliente = '';
var idCliente = '';
var lenguaje = {
	    "sProcessing":     "Procesando...",
	    "sLengthMenu":     "Mostrar _MENU_ registros",
	    "sZeroRecords":    "No se encontraron resultados",
	    "sEmptyTable":     "Ningún dato disponible en esta tabla",
	    "sInfo":           "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
	    "sInfoEmpty":      "Mostrando registros del 0 al 0 de un total de 0 registros",
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

$(document).ready( function () {

	var elegirCliente = function(tbody, table) {
		$(tbody).on("click", "span.btn", function(){
			var data = table.row($(this).parents("tr")).data();
			idCliente = data.idCliente;
			nombreCliente = data.nombreCliente;
			$('#tituloModal').text(data.nombreCliente);	
		});
	}
	
	var listarClientes = function() {
		var tabla = $('#tabla_clientes').DataTable({
			ajax: {
				url: "/RegistrarOrdenVenta/listarClientes",
				type: "GET",
				beforeSend: function() {
					$('#imagenCarga').show();
				},
				complete: function() {
					$('#imagenCarga').hide();
				},
			    error: function(xhr, ajaxOptions, thrownError) {
			    	var response = JSON.parse(xhr.responseText);	   
		        	$('#mensajeError').text(response.message);
		        	$('#modalError').modal('show');
			    }
			},
			sAjaxDataProp: "",
			order: [[ 0, "asc" ]],
			responsive: true,
			columns: [
				{data: "idCliente"},
				{data: "nombreCliente"},
				{defaultContent: "<span class='btn btn-success' data-toggle='modal' data-target='#modalElegir'>" +
				"Elegir <span class='fa fa-user'></span></span>", "sClass": "text-center"}
			],
			language: lenguaje
		});
		
		elegirCliente('#tabla_clientes tbody',tabla)
	}
	
	$('#botonAceptar').on("click", function() {
		window.location.href = "/RegistrarOrdenVenta/Orden/" + nombreCliente + "/" + idCliente;
	});
	
	listarClientes();
	
});