var idPre = '';
var cant = 0;
var cantTotal = 0;
var contenidoList = [];
var bloque = 0;
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
var alertaValidacion = $('<div class="alert alert-danger" id="validacion"></div>');

$(document).ready( function () {
	
	var eliminarContenido = function(tbody,table){
		$(tbody).on('click', 'span.btn', function () {
			var data = table.row($(this).parents("tr")).data();
			idPre = data.idPresentacion;
			console.log(contenidoList.findIndex(x => x.idPresentacion == idPre));
		});
	}
	
	var eliminarFila = function(tbody,table){
		$(tbody).on('click', 'span.btn', function () {
			table
			.row( $(this).parents('tr') )
			.remove()
			.draw();
		});
	}
	
	var obtenerBloque = function(descripcion) {
		var posAux = descripcion.search("kg");
		var subDes = descripcion.slice(posAux + 2, descripcion.length);
		posAux = subDes.search("kg");
		return subDes.slice(posAux - 5, posAux).trim();
	}

	var agregarCantidad = function(tbody, table) {
		$(tbody).on("click", "span.btn", function(){
			alertaValidacion.remove();
			$('#cantidadAgregar').val("");
			var data = table.row($(this).parents("tr")).data();
			idPre = data.idPresentacion;
			bloque = obtenerBloque(data.descripcion);
			$('#tituloModal').text(data.idPresentacion);			
		});
	}
	
	var actualizarTablaContenido = function(){
		var tabla = $('#contenidosTabla').DataTable({
			destroy: true,
			data: contenidoList,
			order: [[ 0, "asc" ]],
			responsive: true,
			columns: [
				{data: "idPresentacion"},
				{data: "cantidad"},
				{defaultContent: "<span class='btn btn-danger'>" +
					"<span class='fa fa-minus-circle'></span></span>"}
			],
			language: lenguaje
		});
		eliminarFila('#contenidosTabla tbody',tabla)
		//eliminarContenido('#contenidosTabla tbody', tabla);
	}
	
	$('#botonAgregar').on("click", function() {
		cant = $('#cantidadAgregar').val();
		if(cant != 0)
		{
			if(esMultiplo(cant))
			{
				alertaValidacion.remove();
				var contenido = {
					idLote: "",
					idPresentacion: idPre,
					cantidad: cant
				}
				cantTotal = cantTotal + Number(cant);
				contenidoList.push(contenido);
				actualizarTablaContenido();
				actualizarCantidadTotal();
				$('#modalAgregar').modal('hide');
			} else {
				alertaValidacion.appendTo($('#cuerpoAgregar'));
				$('#validacion').text("La cantidad debe ser múltiplo de " + bloque + ".");
			}
		} else {
			alertaValidacion.appendTo($('#cuerpoAgregar'));
			$('#validacion').text("Elija la cantidad.");
		}
	});
	
	$('#botonConfirmar').on("click", function() {
		for(i = 0; i < contenidoList.length; i++) {
			contenidoList[i].idLote = $('#codigo').val();
		}
		var lote = {
			idLote: $('#codigo').val(),
			idPlanta: 2,
			fechaProduccion: $('#fecha').val(),
			cantidadRecepcion: $('#cantidad').val(),
			esReempaque: $('#reempaque').val(),
			contenidoList: contenidoList	
		}
		$('#modalConfirmar').modal('hide');
		registrarLote(lote);
	});
	
	$('#guardarLote').on("click", function() {
		var esValido = validarRegistrarLote();
		if(esValido) {
			$('#tituloModal').text($('#codigo').val());
			$('#modalConfirmar').modal('show');
		}
	});
	
	$('#reempaque').on("click", function() {
		var valor = $('#reempaque').val();
		if(valor === 'true') {
			$('#reempaque').val('false');
		} else {
			$('#reempaque').val('true');
		}
	});
	
	var listar = function() {
		var tabla = $('#presentacionesTabla').DataTable({
			ajax: {
				url: "/RegistrarLote/listarPresentacion",
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
				{defaultContent: "<span class='btn btn-success' data-toggle='modal' data-target='#modalAgregar'>" +
						"Agregar <span class='fa fa-plus-circle'></span></span>"}
			],
			language: lenguaje
		});
		
		agregarCantidad('#presentacionesTabla tbody', tabla);
	}
	
	function registrarLote(lote) {
		$.ajax({
	        type: "POST",
	        contentType: "application/json",
	        url: "/RegistrarLote/registrarLote",
	        data: JSON.stringify(lote),
	        success: function (data) {	        	
	            $('#modalExito').modal('show');
	            limpiarControles();
	    		actualizarTablaContenido();
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
		$('#codigo').val("");
		$('#fecha').val("");
		$('#cantidad').val("");
		contenidoList = [];
		cantTotal = 0;
	}
	
	function actualizarCantidadTotal() {
		$('#cantidadTotal').text('Cantidad Total: ' + cantTotal)
	}
	
	function validarRegistrarLote() {
		var mensaje = "";
		if($('#codigo').val() === "")
			mensaje = "El Código de Lote es requerido.";
		if($('#fecha').val() === "")
			mensaje = "La Fecha de Producción es requerida.";
		if($('#cantidad').val() <= 0)
			mensaje = "La Cantidad Recepcionada debe ser mayor a 0.";
		if(contenidoList.length === 0)
			mensaje = "La Lista de Contenidos no puede ser vacía.";
		if(mensaje != "")
		{
        	$('#mensajeError').text(mensaje);
        	$('#modalError').modal('show');
        	return false;	
		} else {
			return true;
		}
	}
	
	function esMultiplo(cantidad) {
		var resto = cantidad % bloque;   
	    if ( resto != 0 ){
	    	return false;
	    } else {
	    	return true;
	    }
	}
	
	listar();
	
});