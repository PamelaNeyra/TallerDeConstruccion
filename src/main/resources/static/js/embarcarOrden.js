var idOrden = '';

$(document).ready( function () {
		
	var elegirOrden = function(tbody, table) {
		$(tbody).on("click", "span.btn", function(){
			var data = table.row($(this).parents("tr")).data();
			idOrden = data.idOrdenVenta;
			if(data.estado === 'Embarcado') {
	        	$('#mensajeError').text('Esta orden ya está embarcada.');
	        	$('#modalError').modal('show');
			} else {
	        	$('#modalConfirmar').modal('show');
				$('#tituloModal').text(data.idOrdenVenta);	
			}
		});
	}		
	
	var listar = function() {
		var tabla = $('#ordenesTabla').DataTable({
			ajax: {
				url: "/EmbarcarOrden/listarOrdenVenta",
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
					title: "Lista de Clientes",
					className: "btn btn-success",
					exportOptions: {
						columns: [0,1,2,3,4]
					}
				},
				{
	                extend: 'pdfHtml5',
	                text: "<i class='fa fa-file-pdf'></i> PDF",
	                title: 'Lista de Clientes',
	                className: "btn btn-danger",
	                exportOptions: {
						columns: [0,1,2,3,4]
					}
				}
		    ],
			columns: [
				{data: "idOrdenVenta"},  
				{data: "nombreCliente"},
				{data: "fechaAsignacion"},
				{data: "cantidadTotal"},
				{data: "estado"},
				{defaultContent: "<span class='btn btn-success' data-toggle='modal'>" +
										"Embarcar <span class='fa fa-ship'></span></span>", "sClass": "text-center"}
			],
			language: lenguaje
		});
		
		elegirOrden('#ordenesTabla tbody',tabla);
	}
	
	listar();

	$('#botonAceptar').on("click", function() {
		window.location.href = "/EmbarcarOrden/" + idOrden;
	});

});