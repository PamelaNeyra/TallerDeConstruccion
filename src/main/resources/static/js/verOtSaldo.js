var ot = '';
var fechaMuestreado = '';
var nombreLaboratorio = '';
var cantidad = '';

$(document).ready( function () {
	
	var verDetalle = function(tbody, table) {
		$(tbody).on("click", "#detalle", function(){
			var data = table.row($(this).parents("tr")).data();
			ot =data.ot;
			fechaMuestreado = data.fechaMuestreado;
			nombreLaboratorio = data.nombreLaboratorio;
			cantidad = data.cantidad;
	        $('#modalConfirmar').modal('show');
		    $('#tituloModal').text(data.ot);	
			
		});
	}	
	

	var listar = function() {
		var tabla = $('#muestreosTabla').DataTable({
			ajax: {
				url: "/VerSaldoOt/listarMuestreoOt",
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
						columns: [0,1,2,3]
					}
				},
				{
	                extend: 'pdfHtml5',
	                text: "<i class='fa fa-file-pdf'></i> PDF",
	                title: 'Lista de Muestras',
	                className: "btn btn-danger",
	                exportOptions: {
						columns: [0,1,2,3]
					}
				}
		    ],
			columns: [
				{data: "ot"},
				{data: "fechaMuestreado"},
				{data: "nombreLaboratorio"},
				{data: "cantidad"},
				{defaultContent: "<span id='detalle' class='btn btn-success' data-toggle='modal'>" +
										"Detalle <span class='fa fa-list'></span></span>", "sClass": "text-center"},
			],		
			language: lenguaje
								
		});
		
		verDetalle('#muestreosTabla tbody',tabla);

	}
	
	listar();

	$('#botonAceptar').on("click", function() {
		$('#ot').val(ot);
		$('#fechaMuestreado').val(fechaMuestreado);
		$('#nombreLaboratorio').val(nombreLaboratorio);
		$('#cantidad').val(cantidad);
		$('#submit').click();
	});

});