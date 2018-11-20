$(document).ready( function() {

	var muestra = {
			idMuestra: $('#codigo').val()
	}	
	
	function obtenerDatos(muestra) {
		$.ajax({
	        type: "POST",
	        contentType: "application/json",
	        url: "/Packing/Muestra/obtenerMuestra",
	        data: JSON.stringify(muestra),
			beforeSend: function() {
				$('#imagenCarga').show();
			},
			complete: function() {
				$('#imagenCarga').hide();
			},
	        success: function (data) {	        	
	            $('#laboratorio').val(data.nombreLaboratorio);
	            $('#ot').val(data.ot);
	            $('#fechaM').val(data.fechaMuestreado);
	            $('#fechaR').val(data.fechaCreacion);
	            $('#cantidad').val(data.cantidadTotal);
	        },
	        error: function (xhr, ajaxOptions, thrownError) {
	        	var response = JSON.parse(xhr.responseText);	   
	        	$('#mensajeError').text(response.message);
	        	$('#modalError').modal('show');
	        }
	    });
	}
	
	obtenerDatos(muestra);
	
	$('#muestreoTabla').DataTable({
		ajax: {
			type: "POST",
			contentType: "application/json",
			url: "/Packing/Muestra/listarMuestreo",
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
				title: "Packing de Muestreos",
				className: "btn btn-success",
				exportOptions: {
					columns: [0,1,2,3,4,5,6,7,8]
				}
			},
			{
                extend: 'pdfHtml5',
                text: "<i class='fa fa-file-pdf'></i> PDF",
                title: 'Packing de Muestreos',
                className: "btn btn-danger",
                exportOptions: {
					columns: [0,1,2,3,4,5,6,7,8]
				}
			}
	    ],
	    columns: [
			{data: "descripcionProdTerm"},
			{data: "descripcion"},
			{data: "nroBultos"},
			{data: "nroBlocks"},
			{data: "cantidad"},
			{data: "fechaCaptura"},
			{data: "fechaProduccion"},
			{data: "fechaVencimiento"},
			{data: "codigoTrazabilidad"}
		],
		language: lenguaje
	});
	
});