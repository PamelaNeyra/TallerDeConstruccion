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
		
	var orden = {
			idOrdenVenta: $('#codigo').val()
	};
	
	function listarAsignaciones(orden) {
		var tabla = $('#asignacionesTabla').DataTable({
			ajax: {
				type: "POST",
				contentType: "application/json",
				url: "/EmbarcarOrden/listarAsignacion",
				//data: ordenJSON,
				beforeSend() {
					console.log(orden);
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
				{data: "idLote"},
				{data: "ot"},
				{data: "idPresentacion"},
				{data: "descripcion"},
				{data: "cantidad"},
				{data: "saldo"}
			],
			language: lenguaje
		});
		
	}
	
	function obtenerDatos(orden) {
		$.ajax({
	        type: "POST",
	        contentType: "application/json",
	        url: "/EmbarcarOrden/obtenerOrdenVenta",
	        data: JSON.stringify(orden),
	        success: function (data) {	        	
	            $('#cliente').val(data.nombreCliente);
	            $('#fechaA').val(data.fechaAsignacion);
	            $('#certificado').val(data.certificado);
	            $('#planta').val(data.nombrePlanta);
	        },
	        error: function (xhr, ajaxOptions, thrownError) {
	        	var response = JSON.parse(xhr.responseText);	   
	        	$('#mensajeError').text(response.message);
	        	$('#modalError').modal('show');
	        }
	    });
	}
	
	$('#botonEmbarcarAsignacion').on("click", function() {
		$.ajax({
	        type: "POST",
	        contentType: "application/json",
	        url: "/EmbarcarOrden/actualizarOrden",
	        data: JSON.stringify(orden),
	        success: function (data) {	        	
	            $('#modalExito').modal('show');
	        },
	        error: function (xhr, ajaxOptions, thrownError) {
	        	var response = JSON.parse(xhr.responseText);	   
	        	$('#mensajeError').text(response.message);
	        	$('#modalError').modal('show');
	        }
	    });
	});

	listarAsignaciones(orden);
	
	obtenerDatos(orden);
	
});


	