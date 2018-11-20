var cuerpo;

$(document).ready( function () {

	var verContenido = function(tbody, table) {
		$(tbody).on("click", "span.btn", function(){
			var data = table.row($(this).parents("tr")).data();
			var idLote = data.idLote;
			cuerpo = {
					idLote: idLote
			}
			$('#modalContenido #tituloModal').text(idLote);
			$('#modalContenido').modal("show");
			actualizarTablaContenido();
		});
	}
	
	var actualizarTablaContenido = function() {
		var tabla = $('#contenidoTabla').DataTable({
			destroy: true,
			ajax: {
				url: "/Ver/Lote/listarContenido",
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
					title: "Lista de Contenidos",
					className: "btn btn-success",
					exportOptions: {
						columns: [0,1,2,3]
					}
				},
				{
	                extend: 'pdfHtml5',
	                text: "<i class='fa fa-file-pdf'></i> PDF",
	                title: 'Lista de Contenidos',
	                className: "btn btn-danger",
	                exportOptions: {
						columns: [0,1,2,3]
					}
				}
		    ],
			columns: [
				{data: "idPresentacion"},
				{data: "cantidad"},
				{data: "comprometido"},
				{data: "saldo"}
			],
			language: lenguaje
		});
	}
	
	var listar = function() {
		var tabla = $('#loteTabla').DataTable({
			ajax: {
				url: "/Ver/Lote/listarLote",
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
					title: "Lista de Lotes",
					className: "btn btn-success",
					exportOptions: {
						columns: [0,1]
					}
				},
				{
	                extend: 'pdfHtml5',
	                text: "<i class='fa fa-file-pdf'></i> PDF",
	                title: 'Lista de Lotes',
	                className: "btn btn-danger",
	                exportOptions: {
						columns: [0,1]
					}
				}
		    ],
			columns: [
				{data: "idLote"},
				{data: "fechaProduccion"},
				{defaultContent: "<span class='btn btn-success' data-toggle='modal'>" +
						"Detalle <span class='fa fa-list'></span></span>", "sClass": "text-center"}
			],
			language: lenguaje
		});
		
		verContenido('#loteTabla tbody', tabla);
	}
	
	listar();
});