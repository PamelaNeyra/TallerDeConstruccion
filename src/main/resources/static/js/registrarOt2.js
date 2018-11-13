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
			
	var muestra = {
			idMuestra: $('#codigo').val()
	}
	
	function listarLotes(muestra) {
		var tabla = $('#lotesTabla').DataTable({
			ajax: {
				type: "POST",
				contentType: "application/json",
				url: "/RegistrarOt/listarLotesOt",
				data: function(data) {
					return JSON.stringify(muestra);
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
				{data: "idPresentacion"},
				{data: "cantidadMuestreado"},
			],
			language: lenguaje
		});
				
	}
	
	function obtenerDatos(muestra) {
		$.ajax({
	        type: "POST",
	        contentType: "application/json",
	        url: "/RegistrarOt/obtenerMuestraOt",
	        data: JSON.stringify(muestra),
	        success: function (data) {	        	
	            $('#fechaR').val(data.fechaCreacion);
	            $('#laboratorio').val(data.idLaboratorio);
	            $('#estado').val(data.estado);
	        },
	        error: function (xhr, ajaxOptions, thrownError) {
	        	var response = JSON.parse(xhr.responseText);	   
	        	$('#mensajeError').text(response.message);
	        	$('#modalError').modal('show');
	        }
	    });
	}
	
	$('#botonConfirmar').on("click", function() {
		var muestra = {
		    idMuestra: $('#codigo').val(),
			fechaMuestreado: $('#fechaM').val(),
			ot: $('#ot').val(),
		}
		$('#modalConfirmar').modal('hide');
		registrarOt(muestra);
	});
	
	$('#registrarOt').on("click", function() {
		var valido = validarOt();
		if(valido) {
			$('#modalConfirmar').modal('show');
		}
	});
	
	function validarOt() {
		var mensaje = "";
		if($('#fechaM').val() === "")
			mensaje = "La Fecha de Muestra es requerida.";
		if($('#ot').val() <= 0)
			mensaje = "El numero de OT es requerido";
		if(mensaje != "")
		{
        	$('#mensajeError').text(mensaje);
        	$('#modalError').modal('show');
        	return false;	
		} else {
			return true;
		}
	}
	
	function registrarOt(muestra) {
		$.ajax({
	        type: "POST",
	        contentType: "application/json",
	        url: "/RegistrarOt/actualizarMuestraOt",
	        data: JSON.stringify(muestra),
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

	listarLotes(muestra);
	
	obtenerDatos(muestra);
	
});


	