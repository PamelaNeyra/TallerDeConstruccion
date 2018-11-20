var cuerpo;

$(document).ready( function () {

	var verRetiro = function(tbody, table) {
		$(tbody).on("click", "span.btn", function(){
			var data = table.row($(this).parents("tr")).data();
			var idOrdenRetiro = data.idOrdenRetiro;
			cuerpo = {
					idOrdenRetiro: idOrdenRetiro
			}
			$('#modalRetiro #tituloModal').text(idOrdenRetiro);
			$('#modalRetiro').modal("show");
			actualizarTablaRetiro();
		});
	}
	
	var actualizarTablaRetiro = function() {
		var tabla = $('#retiroTabla').DataTable({
			destroy: true,
			ajax: {
				url: "/Ver/OrdenRetiro/listarRetiro",
				type: "POST",
				contentType: "application/json",
				data: function(data) {
					return JSON.stringify(cuerpo);
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
					title: "Lista de Retiros",
					className: "btn btn-success",
					exportOptions: {
						columns: [0,1,2]
					}
				},
				{
	                extend: 'pdfHtml5',
	                text: "<i class='fa fa-file-pdf'></i> PDF",
	                title: 'Lista de Retiros',
	                className: "btn btn-danger",
	                exportOptions: {
						columns: [0,1,2]
					}
				}
		    ],
			columns: [
				{data: "idPresentacion"},
				{data: "idLote"},
				{data: "cantidad"}
			],
			language: lenguaje
		});
	}
	
	var listar = function() {
		var tabla = $('#ordenTabla').DataTable({
			ajax: {
				url: "/Ver/OrdenRetiro/listarOrdenRetiro",
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
			lengthMenu: tamañoMenu,
			dom: domTabla,
			buttons: [
				{
					extend: "excelHtml5",
					text: "<i class='fa fa-file-excel'></i> Excel",
					title: "Lista de Ordenes de Retiro",
					className: "btn btn-success",
					exportOptions: {
						columns: [0,1,2]
					}
				},
				{
	                extend: 'pdfHtml5',
	                text: "<i class='fa fa-file-pdf'></i> PDF",
					title: "Lista de Ordenes de Retiro",
	                className: "btn btn-danger",
	                exportOptions: {
						columns: [0,1,2]
					}
				}
		    ],
			columns: [
				{data: "idOrdenRetiro"},
				{data: "fechaRetiro"},
				{data: "descripcion"},
				{defaultContent: "<span class='btn btn-success' data-toggle='modal'>" +
						"Detalle <span class='fa fa-list'></span></span>", "sClass": "text-center"}
			],
			language: lenguaje
		});
		
		verRetiro('#ordenTabla tbody', tabla);
	}
	
	listar();
});