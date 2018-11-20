var cuerpo;

$(document).ready( function () {

	var verAsignacion = function(tbody, table) {
		$(tbody).on("click", "span.btn", function(){
			var data = table.row($(this).parents("tr")).data();
			var idOrdenVenta = data.idOrdenVenta;
			cuerpo = {
					idOrdenVenta: idOrdenVenta
			}
			$('#modalAsignacion #tituloModal').text(idOrdenVenta);
			$('#modalAsignacion').modal("show");
			actualizarTablaAsignacion();
		});
	}
	
	var actualizarTablaAsignacion = function() {
		var tabla = $('#asignacionTabla').DataTable({
			destroy: true,
			ajax: {
				url: "/Ver/OrdenVenta/listarAsignacion",
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
					title: "Lista de Asignaciones",
					className: "btn btn-success",
					exportOptions: {
						columns: [0,1,2]
					}
				},
				{
	                extend: 'pdfHtml5',
	                text: "<i class='fa fa-file-pdf'></i> PDF",
	                title: 'Lista de Asignaciones',
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
				url: "/Ver/OrdenVenta/listarOrdenVenta",
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
					title: "Lista de Ordenes de Venta",
					className: "btn btn-success",
					exportOptions: {
						columns: [0,1,2]
					}
				},
				{
	                extend: 'pdfHtml5',
	                text: "<i class='fa fa-file-pdf'></i> PDF",
					title: "Lista de Ordenes de Venta",
	                className: "btn btn-danger",
	                exportOptions: {
						columns: [0,1,2]
					}
				}
		    ],
			columns: [
				{data: "idOrdenVenta"},
				{data: "nombreCliente"},
				{data: "fechaAsignacion"},
				{defaultContent: "<span class='btn btn-success' data-toggle='modal'>" +
						"Detalle <span class='fa fa-list'></span></span>", "sClass": "text-center"}
			],
			language: lenguaje
		});
		
		verAsignacion('#ordenTabla tbody', tabla);
	}
	
	listar();
});