var idCliente = '';
var nombreCliente = '';
var lenguaje = {
	    "sProcessing":     "Procesando...",
	    "sLengthMenu":     "Mostrar _MENU_ registros",
	    "sZeroRecords":    "No se encontraron resultados",
	    "sEmptyTable":     "Ningún dato disponible en esta tabla",
	    "sInfo":           "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
	    "sInfoEmpty":      "Mostrando registros del 0 al 0 de un total de 0 registros",
	    "sInfoFiltered":   "(filtrado de un total de _MAX_ registros)",
	    "sInfoPostFix":    "",
	    "sSearch":         "Buscar:",
	    "sUrl":            "",
	    "sInfoThousands":  ",",
	    "sLoadingRecords": "Cargando...",
	    "oPaginate": {
	        "sFirst":    "Primero",
	        "sLast":     "Último",
	        "sNext":     "Siguiente",
	        "sPrevious": "Anterior"
	    },
	    "oAria": {
	        "sSortAscending":  ": Activar para ordenar la columna de manera ascendente",
	        "sSortDescending": ": Activar para ordenar la columna de manera descendente"
	    }
}

$(document).ready( function () {
	
	/*LISTAR*/
	/*var listar = function() {
		var tabla = $('#presentacionesTabla').DataTable({
			ajax: {
				url: "/RegistrarOrdenVenta/listarPresentacion",
				type: "GET",
			    error: function(xhr, ajaxOptions, thrownError) {
			        alert(xhr.status);
			        alert(thrownError);
			    }
			},
			sAjaxDataProp: "",
			order: [[ 0, "asc" ]],
			responsive: true,
			columns: [
				{data: "idPresentacion"},
				{data: "descripcion"},
				{defaultContent: "<span class='btn btn-success' data-toggle='modal' data-target='#modalAgregar'>" +
						"<span class='fa fa-plus-circle'></span></span>"}
			],
			language: lenguaje
		});
		
		agregarPresentacion('#presentacionesTabla tbody',tabla)
	}*/
	
	//listar();
	
	/*var actualizarTablaPresentacionesComprometidas = function(){
		$('#presentacionesTablaComprometidas').DataTable({
			destroy: true,
			data: contenidoList,
			order: [[ 0, "asc" ]],
			responsive: true,
			columns: [
				{title: "Código SAP"},
				{title: "Descripciónn"}
			],
			language: lenguaje
		});
	}*/
	var elegirCliente = function(tbody, table) {
		$(tbody).on("click", "span.btn", function(){
			var data = table.row($(this).parents("tr")).data();
			idCliente = data.idCliente;
			nombreCliente = data.nombreCliente;
			$('#exampleModalCenterTitle').text(data.nombreCliente);	
		});
	}
	
	var listarClientes = function() {
		var tabla = $('#tabla_clientes').DataTable({
			ajax: {
				url: "/RegistrarOrdenVenta/listarClientes",
				type: "GET",
			    error: function(xhr, ajaxOptions, thrownError) {
			    	var response = JSON.parse(xhr.responseText);	   
		        	$('#mensajeError').text(response.message);
		        	$('#modalError').modal('show');
			    }
			},
			sAjaxDataProp: "",
			order: [[ 0, "asc" ]],
			responsive: true,
			columns: [
				{data: "idCliente"},
				{data: "nombreCliente"},
				{defaultContent: "<span class='btn btn-success' data-toggle='modal' data-target='#modalElegir'>" +
				"<span class='fa fa-user'></span></span>"}
			],
			language: lenguaje
		});
		
		elegirCliente('#tabla_clientes tbody',tabla)
	}
	
	/*FIN LISTAR*/
	
	$('#botonAceptar').on("click", function() {
		window.location.href = "/RegistrarOrdenVenta/Orden/" + nombreCliente;
	});
	
	/*$('#btn_asignar_cliente').on("click", function() {
		var data = table.row($(this).parents("tr")).data();
		idCli = data.idCliente;
		nombreCli = data.nombreCliente;
		docuement.cliente.value = nombreCli;
	});*/
	
	listarClientes();
	
});