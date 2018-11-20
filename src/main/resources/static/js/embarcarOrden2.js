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
				beforeSend: function() {
					$('#imagenCarga').show();
				},
				complete: function() {
					$('#imagenCarga').hide();
				},
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
			lengthMenu: tamañoMenu,
			dom: domTabla,
			buttons: [
				{
					extend: "excelHtml5",
					text: "<i class='fa fa-file-excel'></i> Excel",
					title: "Lista de Asignaciones",
					className: "btn btn-success",
					exportOptions: {
						columns: [0,1,2,3,4]
					}
				},
				{
	                extend: 'pdfHtml5',
	                text: "<i class='fa fa-file-pdf'></i> PDF",
	                title: 'Lista de Asignaciones',
	                className: "btn btn-danger",
	                exportOptions: {
						columns: [0,1,2,3,4]
					}
				}
		    ],
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
			beforeSend: function() {
				$('#imagenCarga').show();
			},
			complete: function() {
				$('#imagenCarga').hide();
			},
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

	listarAsignaciones(orden);
	
	obtenerDatos(orden);
	
});


	