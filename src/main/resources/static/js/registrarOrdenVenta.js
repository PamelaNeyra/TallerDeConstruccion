var idCliente = '';
var nombreCliente = '';
var idCliente = '';

$(document).ready( function () {

	var elegirCliente = function(tbody, table) {
		$(tbody).on("click", "span.btn", function(){
			var data = table.row($(this).parents("tr")).data();
			idCliente = data.idCliente;
			nombreCliente = data.nombreCliente;
			$('#tituloModal').text(data.nombreCliente);	
		});
	}
	
	var listarClientes = function() {
		var tabla = $('#tabla_clientes').DataTable({
			ajax: {
				url: "/RegistrarOrdenVenta/listarClientes",
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
						columns: [0,1]
					}
				},
				{
	                extend: 'pdfHtml5',
	                text: "<i class='fa fa-file-pdf'></i> PDF",
	                title: 'Lista de Clientes',
	                className: "btn btn-danger",
					exportOptions: {
						columns: [0,1]
					}
				}
		    ],
			columns: [
				{data: "idCliente"},
				{data: "nombreCliente"},
				{defaultContent: "<span class='btn btn-success' data-toggle='modal' data-target='#modalElegir'>" +
				"Elegir <span class='fa fa-user'></span></span>", "sClass": "text-center"}
			],
			language: lenguaje
		});
		
		elegirCliente('#tabla_clientes tbody',tabla)
	}
	
	$('#botonAceptar').on("click", function() {
		window.location.href = "/RegistrarOrdenVenta/Orden/" + nombreCliente + "/" + idCliente;
	});
	
	listarClientes();
	
});