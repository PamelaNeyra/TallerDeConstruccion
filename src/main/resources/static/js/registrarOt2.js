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
				beforeSend: function() {
					$('#imagenCarga').show();
				},
				complete: function() {
					$('#imagenCarga').hide();
				},
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
			lengthMenu: tamañoMenu,
			dom: domTabla,
			buttons: [
				{
					extend: "excelHtml5",
					text: "<i class='fa fa-file-excel'></i> Excel",
					title: "Lista de Muestreos",
					className: "btn btn-success",
					exportOptions: {
						columns: [0,1,2]
					}
				},
				{
	                extend: 'pdfHtml5',
	                text: "<i class='fa fa-file-pdf'></i> PDF",
	                title: 'Lista de Muestreos',
	                className: "btn btn-danger",
					exportOptions: {
						columns: [0,1,2]
					}
				}
		    ],
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
			beforeSend: function() {
				$('#imagenCarga').show();
			},
			complete: function() {
				$('#imagenCarga').hide();
			},
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
			$('#modalConfirmar #tituloModal').text($('#ot').val());
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
			beforeSend: function() {
				$('#imagenCarga').show();
			},
			complete: function() {
				$('#imagenCarga').hide();
			},
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


	