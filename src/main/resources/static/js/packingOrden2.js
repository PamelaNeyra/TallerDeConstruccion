$(document).ready( function() {
	
	var orden = {
			idOrdenVenta: $('#codigo').val()
	}
	
	function obtenerDatos(orden) {
		$.ajax({
	        type: "POST",
	        contentType: "application/json",
	        url: "/Packing/Asignacion/obtenerOrdenVenta",
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
	            $('#fechaE').val(data.fechaEmbarque);
	            $('#certificado').val(data.certificado);
	            $('#hora').val(data.horaEmbarque);
	            $('#planta').val(data.nombrePlanta);
	            $('#lugar').val(data.nombrePlanta);
	            $('#destino').val(data.paisDestino);
	            $('#labo').val(data.nombreLaboratorio);
	            $('#ot').val(data.ot);
	            $('#vence').val(data.aniosVencimiento);
	        },
	        error: function (xhr, ajaxOptions, thrownError) {
	        	var response = JSON.parse(xhr.responseText);	   
	        	$('#mensajeError').text(response.message);
	        	$('#modalError').modal('show');
	        }
	    });
	}
	
	obtenerDatos(orden);
	
	$('#asignacionesTabla').DataTable({
		ajax: {
			type: "POST",
			contentType: "application/json",
			url: "/Packing/Asignacion/listarAsignacion",
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
				title: "Packing de Asignaciones",
				className: "btn btn-success",
				exportOptions: {
					columns: [0,1,2,3,4,5,6,7,8]
				}
			},
			{
                extend: 'pdfHtml5',
                text: "<i class='fa fa-file-pdf'></i> PDF",
                title: 'Packing de Asignaciones',
                className: "btn btn-danger",
                exportOptions: {
					columns: [0,1,2,3,4,5,6,7,8]
				}
			}
	    ],
	    columns: [
			{data: "idPresentacion"},
			{data: "descripcion"},
			{data: "nroBultos"},
			{data: "cantidad"},
			{data: "fechaProduccion"},
			{data: "fechaVencimiento"},
			{data: "codigoTrazabilidad"},
			{data: "nombrePlanta"},
			{data: "ot"}
		],
		language: lenguaje
	});
	
});