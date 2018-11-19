var idMuestra = '';

$(document).ready( function () {
	
	var elegirMuestra = function(tbody, table) {
		$(tbody).on("click", "span.btn", function(){
			var data = table.row($(this).parents("tr")).data();
			idMuestra=data.idMuestra;
			if(data.estado === 'Muestreado') {
	        	$('#mensajeError').text('Muestra ya tiene asignado OT');   //Muestreada --> ot
	        	$('#modalError').modal('show');
			} else {
	        	$('#modalConfirmar').modal('show');
				$('#tituloModal').text('Muestra: ' + data.idMuestra);	
			}
		});
	}	
	
	var listar = function() {
		var tabla = $('#muestrasTabla').DataTable({
			ajax: {
				url: "/RegistrarOt/listarMuestraOt",
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
						columns: [0,1,2,3,4,5,6]
					}
				},
				{
	                extend: 'pdfHtml5',
	                text: "<i class='fa fa-file-pdf'></i> PDF",
	                title: 'Lista de Muestras',
	                className: "btn btn-danger",
					exportOptions: {
						columns: [0,1,2,3,4,5,6]
					}
				}
		    ],
			columns: [
				{data: "idMuestra"},  
				{data: "ot"},
				{data: "fechaCreacion"},
				{data: "fechaMuestreado"},
				{data: "idLaboratorio"},
				{data: "cantidadTotal"},
				{data: "estado"},
				{defaultContent: "<span class='btn btn-success' data-toggle='modal'>" +
										"Elegir <span class='fa fa-check'></span></span>", "sClass": "text-center"}
			],
			language: lenguaje
		});
		
		elegirMuestra('#muestrasTabla tbody',tabla);
	}
	
	listar();

	$('#botonAceptar').on("click", function() {
		window.location.href = "/RegistrarOt/" + idMuestra;
	});

});