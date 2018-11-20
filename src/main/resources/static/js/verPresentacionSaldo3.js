
$(document).ready( function () {
	
	var presentacion = {
			idPresentacion: $('#codigo').val()
	}
	
	function listarAsignaciones(presentacion) {
		var tabla = $('#asignacionesTabla').DataTable({
			ajax: {
				type: "POST",
				contentType: "application/json",
				url: "/VerSaldoPresentacion/listarAsignacionSaldo",
				beforeSend: function() {
					$('#imagenCarga').show();
				},
				complete: function() {
					$('#imagenCarga').hide();
				},
				data: function(data) {
					return JSON.stringify(presentacion);
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
						columns: [0,1,2,3,4,5]
					}
				},
				{
	                extend: 'pdfHtml5',
	                text: "<i class='fa fa-file-pdf'></i> PDF",
	                title: 'Lista de Asignaciones',
	                className: "btn btn-danger",
	                exportOptions: {
						columns: [0,1,2,3,4,5]
					}
				}
		    ],
			columns: [
				{data: "idOrdenVenta"},
				{data: "nombreCliente"},
				{data: "idLote"},
				{data: "ot"},
				{data: "cantidad"},
				{data: "fechaAsignacion"}
				
			],
			language: lenguaje
		});
				
	}
	
	$('#botonConfirmar').on("click", function() {
		$('#modalConfirmar').modal('hide');
		 window.location.href = "/VerSaldoPresentacion";
	});
	
	$('#anterior').on("click", function() {
		$('#modalConfirmar').modal('show');
	});
	
	listarAsignaciones(presentacion);

});


	