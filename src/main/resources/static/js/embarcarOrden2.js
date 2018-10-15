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
	}
	
	function listarAsignaciones(orden) {
		var tabla = $('#asignacionesTabla').DataTable({
			ajax: {
				type: "POST",
				contentType: "application/json",
				url: "/EmbarcarOrden/listarAsignacion",
				data: function(data) {
					return JSON.stringify(orden);
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
	            $('#total').val(data.cantidadTotal);
	        },
	        error: function (xhr, ajaxOptions, thrownError) {
	        	var response = JSON.parse(xhr.responseText);	   
	        	$('#mensajeError').text(response.message);
	        	$('#modalError').modal('show');
	        }
	    });
	}
	
	$('#botonConfirmar').on("click", function() {
		var orden = {
			idOrdenVenta: $('#codigo').val(),
			fechaEmbarque: $('#fechaE').val(),
			horaEmbarque: $('#hora').val(),
			paisDestino: $('#destino').val(),
		}
		$('#modalConfirmar').modal('hide');
		embarcarOrden(orden);
	});
	
	$('#embarcarOrden').on("click", function() {
		var valido = validarEmbarco();
		if(valido) {
			$('#tituloModal').text($('#codigo').val());
			$('#modalConfirmar').modal('show');
		}
	});
	
	function validarEmbarco() {
		var mensaje = "";
		if($('#fechaE').val() === "")
			mensaje = "La Fecha de Embarque es requerida.";
		if($('#hora').val() === "")
			mensaje = "La Hora de Embarque es requerida.";
		if($('#destino').val() <= 0)
			mensaje = "El País de Destino es requerido.";
		if(mensaje != "")
		{
        	$('#mensajeError').text(mensaje);
        	$('#modalError').modal('show');
        	return false;	
		} else {
			return true;
		}
	}
	
	function embarcarOrden(orden) {
		$.ajax({
	        type: "POST",
	        contentType: "application/json",
	        url: "/EmbarcarOrden/embarcarOrden",
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
	}

	listarAsignaciones(orden);
	
	obtenerDatos(orden);
	
});


	