var idPre = '';
var desc = '';
var presentacionesList = [];
var tabla='';
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
	

	var eliminarFila = function(tbody,table){
		$(tbody).on('click', 'span.btn', function () {
			table
			.row( $(this).parents('tr') )
			.remove()
			.draw();
		});
	}
	
	var actualizarTablaPresentacionesComprometidas = function(){
		var tabla=$('#presentacionesTablaComprometidas').DataTable({
			destroy: true,
			data: presentacionesList,
			order: [[ 0, "asc" ]],
			responsive: true,
			columns: [
				{data: "idPresentacion"},
				{data: "descripcion"},
				{defaultContent: "<span class='btn btn-danger' data-toggle='modal' data-target=''>" +
				"<span class='fa fa-minus-circle'></span></span>"}
			],
			language: lenguaje
		});
		eliminarFila('#presentacionesTablaComprometidas tbody',tabla)
	}

	
	$('#botonAgregar').on("click", function() {
		var presentacion = {
			idPresentacion: idPre,
			descripcion: desc
		}
		//cantTotal = cantTotal + Number(cant);
		presentacionesList.push(presentacion);
		actualizarTablaPresentacionesComprometidas();
		//actualizarCantidadTotal();
		$('#modalAgregar').modal('hide');
	});
	
	var actualizarTablaPresentaciones = function(){
		$('#presentacionesTabla').DataTable({
			destroy: true,
			data: presentacionesList,
			order: [[ 0, "asc" ]],
			responsive: true,
			columns: [
				{data: "idPresentacion"},
				{data: "descripcion"},
				{data: "cantidadTotal"},
				{data: "comprometidoTotal"},
				{defaultContent: "<span class='btn btn-success' data-toggle='modal' data-target='#modalAgregar'>" +
				"<span class='fa fa-plus-circle'></span></span>"}
			],
			language: lenguaje
		});
		
	}
	
	$('#botonQuitar').on("click", function() {
		var presentacion = {
			idPresentacion: idPre,
			descripcion: desc
		}
		//cantTotal = cantTotal + Number(cant);
		presentacionesList.push(presentacion);
		actualizarTablaPresentaciones();
		//actualizarCantidadTotal();
		$('#modalQuitar').modal('hide');
	});
	
	
	
	
	/*LISTAR*/
	var agregarPresentacion = function(tbody, table) {
		$(tbody).on("click", "span.btn", function(){
			var data = table.row($(this).parents("tr")).data();
			idPre = data.idPresentacion;
			desc = data.descripcion;
			comproTotal = data.comprometido_total;
			saldo = data.saldo;
			$('#exampleModalCenterTitle').text(data.idPresentacion);			
		});
	}
	
	var listar = function() {
		var tabla = $('#presentacionesTabla').DataTable({
			ajax: {
				url: "/RegistrarOrdenVenta/listarPresentacion",
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
				{data: "idPresentacion"},
				{data: "descripcion"},
				{data: "cantidadTotal"},
				{data: "comprometidoTotal"},
				{defaultContent: "<span class='btn btn-success' data-toggle='modal' data-target='#modalAgregar'>" +
						"<span class='fa fa-plus-circle'></span></span>"}
			],
			language: lenguaje
		});
		
		agregarPresentacion('#presentacionesTabla tbody',tabla)
	}
	
	listar();	
	/*FIN LISTAR*/
	function limpiarControles() {
		$('#cod_orden').val("");
		$('#tipo_cert').val("");
		$('#fch_asig').val("");
		$('#destino').val("");
		presentacionesList = [];
	}
	
	function registrarOrdenVenta(ordenVenta) {
		$.ajax({
	        type: "POST",
	        contentType: "application/json",
	        url: "/RegistrarOrdenVenta/registrarOrdenVenta",
	        data: JSON.stringify(ordenVenta),
	        success: function (data) {	        	
	            $('#modalTerminarRegistro').modal('show');
	            limpiarControles();
	        },
	        error: function (xhr, ajaxOptions, thrownError) {
	        	var response = JSON.parse(xhr.responseText);	   
	        	$('#mensajeError').text(response.message);
	        	$('#modalError').modal('show');
	        }
	    });
	}
	
	function validarRegistrarOrdenventa() {
		var mensaje = "";
		if($('#cod_orden').val() === "")
			mensaje = "El código es requerido.";
		if($('#tipo_cert').val() <= 0)
			mensaje = "El certificado es requerido.";
		if(presentacionesList.length === 0)
			mensaje = "La Lista de Presentaciones no puede ser vacía.";
		if(mensaje != "")
		{
        	$('#mensajeError').text(mensaje);
        	$('#modalError').modal('show');
        	return false;	
		} else {
			return true;
		}
	}
	
	$('#terminarRegistro').on("click", function() {
		var esValido = validarRegistrarOrdenventa();
		if(esValido) {
			var ordenVenta = {
				idOrdenVenta: $('#cod_orden').val(),
				idCliente: 1,
				certificado: $('#tipo_cert').val(),
				fechaAsignacion: $('#fch_asig').val(),
				paisDestino: $('#destino').val(),
				asignacionList: presentacionesList
			}
			registrarOrdenVenta(ordenVenta);
		}
	});
	
	
	
});