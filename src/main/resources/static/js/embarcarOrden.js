var idOrden = '';
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
	
	var elegirOrden = function(tbody, table) {
		$(tbody).on("click", "span.btn", function(){
			var data = table.row($(this).parents("tr")).data();
			idOrden = data.idOrdenVenta;
			if(data.estado === 'Embarcado') {
	        	$('#mensajeError').text('Esta orden ya está embarcada.');
	        	$('#modalError').modal('show');
			} else {
	        	$('#modalConfirmar').modal('show');
				$('#tituloModal').text(data.idOrdenVenta);	
			}
		});
	}		
	
	var listar = function() {
		var tabla = $('#ordenesTabla').DataTable({
			ajax: {
				url: "/EmbarcarOrden/listarOrdenVenta",
				type: "GET",
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
				{data: "idOrdenVenta"},  
				{data: "nombreCliente"},
				{data: "fechaAsignacion"},
				{data: "cantidadTotal"},
				{data: "estado"},
				{defaultContent: "<span class='btn btn-success' data-toggle='modal'>" +
										"Embarcar <span class='fa fa-ship'></span></span>", "sClass": "text-center"}
			],
			language: lenguaje
		});
		
		elegirOrden('#ordenesTabla tbody',tabla);
	}
	
	listar();

	$('#botonAceptar').on("click", function() {
		window.location.href = "/EmbarcarOrden/" + idOrden;
	});

});