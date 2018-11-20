var cuerpo;

$(document).ready( function () {

	var verMuestreo = function(tbody, table) {
		$(tbody).on("click", "span.btn", function(){
			var data = table.row($(this).parents("tr")).data();
			var idMuestra = data.idMuestra;
			cuerpo = {
					idMuestra: idMuestra
			}
			$('#modalMuestreo #tituloModal').text(idMuestra);
			$('#modalMuestreo').modal("show");
			actualizarTablaMuestreo();
		});
	}
	
	var actualizarTablaMuestreo = function() {
		var tabla = $('#muestreoTabla').DataTable({
			destroy: true,
			ajax: {
				url: "/Ver/Muestra/listarMuestreo",
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
					title: "Lista de Muestreos",
					className: "btn btn-success",
					exportOptions: {
						columns: [0,1,2,3]
					}
				},
				{
	                extend: 'pdfHtml5',
	                text: "<i class='fa fa-file-pdf'></i> PDF",
	                title: 'Lista de Muestreo',
	                className: "btn btn-danger",
	                exportOptions: {
						columns: [0,1,2,3]
					}
				}
		    ],
			columns: [
				{data: "idPresentacion"},
				{data: "idLote"},
				{data: "cantidadMuestreado"}
			],
			language: lenguaje
		});
	}
	
	var listar = function() {
		var tabla = $('#muestraTabla').DataTable({
			ajax: {
				url: "/Ver/Muestra/listarMuestra",
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
					title: "Lista de Muestras",
					className: "btn btn-success",
					exportOptions: {
						columns: [0,1,2,3,4]
					}
				},
				{
	                extend: 'pdfHtml5',
	                text: "<i class='fa fa-file-pdf'></i> PDF",
	                title: 'Lista de Muestras',
	                className: "btn btn-danger",
	                exportOptions: {
						columns: [0,1,2,3,4]
					}
				}
		    ],
			columns: [
				{data: "idMuestra"},
				{data: "fechaCreacion"},
				{data: "fechaMuestreado"},
				{data: "ot"},
				{data: "idLaboratorio"},
				{defaultContent: "<span class='btn btn-success' data-toggle='modal'>" +
						"Detalle <span class='fa fa-list'></span></span>", "sClass": "text-center"}
			],
			language: lenguaje
		});
		
		verMuestreo('#muestraTabla tbody', tabla);
	}
	
	listar();
});