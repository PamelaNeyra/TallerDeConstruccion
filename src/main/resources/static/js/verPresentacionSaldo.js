var idPresentacion = '';

$(document).ready( function () {
	
	var verDetalle = function(tbody, table) {
		$(tbody).on("click", "#detalle", function(){
			var data = table.row($(this).parents("tr")).data();
			idPresentacion=data.idPresentacion;
	        $('#modalConfirmar').modal('show');
		    $('#tituloModal').text(data.idPresentacion);	
			
		});
	}	
	
	var verAsignacion = function(tbody, table) {
		$(tbody).on("click", "#asignacion", function(){
			var data = table.row($(this).parents("tr")).data();
			idPresentacion=data.idPresentacion;
	        $('#modalConfirmar1').modal('show');
		    $('#tituloModal1').text(data.idPresentacion);	
			
		});
	}	
	
	
	var listar = function() {
		var tabla = $('#presentacionesTabla').DataTable({
			ajax: {
				url: "/VerSaldoPresentacion/listarPresentacion",
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
					title: "Lista de Presentaciones",
					className: "btn btn-success",
					exportOptions: {
						columns: [0,1,2,3,4]
					}
				},
				{
	                extend: 'pdfHtml5',
	                text: "<i class='fa fa-file-pdf'></i> PDF",
	                title: 'Lista de Presentaciones',
	                className: "btn btn-danger",
	                exportOptions: {
						columns: [0,1,2,3,4]
					}
				}
		    ],
			columns: [
				{data: "idPresentacion"},
				{data: "descripcion"},
				{data: "cantidadTotal"},
				{data: "comprometidoTotal"},
				{data: "saldo"},
				{defaultContent: "<span id='detalle' class='btn btn-success' data-toggle='modal'>" +
										"Lotes <span class='fa fa-box'></span></span>", "sClass": "text-center"},
				{defaultContent: "<span id='asignacion' class='btn btn-primary' data-toggle='modal'>" +
					               "Asignaciones <span class='fa fa-dolly'></span></span>", "sClass": "text-center"}
			],
			language: lenguaje
		});
		
		verDetalle('#presentacionesTabla tbody',tabla);
		verAsignacion('#presentacionesTabla tbody',tabla);
	}
	
	listar();

	$('#botonAceptar').on("click", function() {
		window.location.href = "/VerSaldoPresentacion/Contenido/" + idPresentacion;
	});
	
	$('#botonAceptar1').on("click", function() {
		window.location.href = "/VerSaldoPresentacion/Asignacion/" + idPresentacion;
	});


});