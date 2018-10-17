var idPro = '';
var desc = '';
var cant = 0;
var productoList = [];
var cantTotal = 0;
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
	
	var eliminarProducto = function(tbody,table){
		$(tbody).on('click', 'span.btn', function () {
			var data = table.row($(this).parents("tr")).data();
			if(data != undefined) {
				var idPro = data.idProductoTerminado;
				var cant = data.cantidadTotal;
				var pos = productoList.findIndex(x => x.idProductoTerminado == idPro);
				productoList.splice(pos, 1);
				cantTotal = cantTotal - Number(cant);
				actualizarTablaProductoSeleccionado();
				actualizarCantidadTotal();
			}
		});
	}
	
	var actualizarTablaProductoSeleccionado = function(){
		var tabla=$('#productoSeleccionadoTabla').DataTable({
			destroy: true,
			data: productoList,
			order: [[ 0, "asc" ]],
			responsive: true,
			columns: [
				{data: "idProductoTerminado"},
				{data: "descripcion"},
				{data: "cantidadTotal"},
				{defaultContent: "<span class='btn btn-danger'>" + 
					             "Retirar <span class='fa fa-minus-circle'></span></span>" }
			],
			language: lenguaje
		});
		
		eliminarProducto('#productoSeleccionadoTabla tbody',tabla);
	}
	
	var agregarFila= function(tbody, table) {
		$(tbody).on("click", "span.btn", function(){
			var data = table.row($(this).parents("tr")).data();
			idPro = data.idProductoTerminado;			
			desc = data.descripcion;
			cant = data.cantidadTotal;
			var encontrado = productoList.find(x => x.idProductoTerminado == idPro);
			if(encontrado != undefined) {
				$('#mensajeError').text("Este producto terminado ya fue agregado.");
	        	$('#modalError').modal('show');
			} else {
	        	$('#modalAgregar').modal('show');
				$('#tituloModal').text(data.idProductoTerminado);		
			}	
		});
	}
	
	$('#botonAgregar').on("click", function() {
		var producto = {
			idProductoTerminado: idPro,
			descripcion: desc,
			cantidadTotal: cant
		}
		cantTotal = cantTotal + Number(cant);
		productoList.push(producto);
		actualizarCantidadTotal();
		actualizarTablaProductoSeleccionado();
		$('#modalAgregar').modal('hide');
	});
	
	$('#botonGuardar').on("click", function() {
		var esValido = validarRegistrarMuestra();
		if(esValido) {
			var obj = {
				muestra: {
					fechaCreacion: $('#fecha').val(),
					idLaboratorio: 1
					//nombreLaboratorio: $('#laboratorio').val()
				},
				productoTerminadoList: productoList
			}
			registrarMuestra(obj);
		}
	});
	
	var listar = function() {
		var tabla = $('#productoTerminado').DataTable({
			ajax: {
				url: "/RegistrarMuestra/listarProductoTerminado",
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
				{data: "idProductoTerminado"},
				{data: "descripcion"},
				{data: "cantidadTotal"},
				{defaultContent: "<span class='btn btn-success' data-toggle='modal'>" +
						"Agregar <span class='fa fa-plus-circle'></span></span>"}
			],
			language: lenguaje
		});
		
		agregarFila('#productoTerminado tbody', tabla);
	}
	
	function registrarMuestra(obj) {
		$.ajax({
	        type: "POST",
	        contentType: "application/json",
	        url: "/RegistrarMuestra/registrarMuestra",
	        data: JSON.stringify(obj),
	        success: function (data) {	        	
	            $('#modalTerminarRegistro').modal('show');
	            limpiarControles();
	            actualizarTablaProductoSeleccionado();
	    		actualizarCantidadTotal();
	        },
	        error: function (xhr, ajaxOptions, thrownError) {
	        	var response = JSON.parse(xhr.responseText);	   
	        	$('#mensajeError').text(response.message);
	        	$('#modalError').modal('show');
	        }
	    });
	}
	
	function limpiarControles() {
		$('#laboratorio').val("");
		$('#fecha').val("");
		productoList = [];
		cantTotal = 0;
	}
	
	function actualizarCantidadTotal() {
		$('#cantidadTotal').text('Cantidad Total: ' + cantTotal)
	}
	
	function validarRegistrarMuestra() {
		var mensaje = "";
		if($('#fecha').val() === "")
			mensaje = "La Fecha es requerida.";
		if($('#laboratorio').val() <= 0)
			mensaje = "El Laboratorio es requerido.";
		if(productoList.length === 0)
			mensaje = "La Lista de Productos Terminados no puede ser vacía.";
		if(mensaje != "")
		{
        	$('#mensajeError').text(mensaje);
        	$('#modalError').modal('show');
        	return false;	
		} else {
			return true;
		}
	}
	
	listar();
	
});