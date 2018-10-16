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

var alertaValidacion = $('<div class="alert alert-danger" id="validacion"></div>');

$(document).ready( function () {
	
	
	
	var obtenerBloque = function(descripcion) {
		var posAux = descripcion.search("kg");
		var subDes = descripcion.slice(posAux + 2, descripcion.length);
		posAux = subDes.search("kg");
		return subDes.slice(posAux - 5, posAux).trim();
	}

	
	
	var eliminarFila = function(tbody,table){
		$(tbody).on('click', 'span.btn', function () {
			var data = table.row($(this).parents("tr")).data();
			if(data != undefined) {
				console.log(data);
				var idPre = data.idPresentacion;
				var cant = data.cantidad;
				var pos = presentacionesList.findIndex(x => x.idPresentacion == idPre);
				presentacionesList.splice(pos, 1);
				actualizarTablaPresentacionesComprometidas();
			}
		});
	}
	
	var actualizarTablaPresentacionesComprometidas = function(){
		var tabla = $('#presentacionesTablaComprometidas').DataTable({
			destroy: true,
			data: presentacionesList,
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
		
		eliminarFila('#presentacionesTablaComprometidas tbody',tabla);
	}

	
	
	$('#botonAgregar').on("click", function() {
		cant = $('#cantidadAgregar').val();
		if(cant >= 0) {
			if(esMultiplo(cant)) {
				alertaValidacion.remove();
				var presentacionComprometida = {
					idPresentacion: idPre,
					cantidad: cant
				}
				presentacionesList.push(presentacionComprometida);
				actualizarTablaPresentacionesComprometidas();
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
	
	var actualizarTablaPresentaciones = function(){
		$('#presentacionesTabla').DataTable({
			destroy: true,
			data: presentacionesList,
			order: [[ 0, "asc" ]],
			responsive: true,
			columns: [
				{data: "idPresentacion"},
				{data: "descripcion"},
				{data: "comprometidoTotal"},
				{data: "saldo"},
				{defaultContent: "<span class='btn btn-success' data-toggle='modal' data-target='#modalAgregar'>" +
				"<span class='fa fa-plus-circle'></span></span>"}
			],
			language: lenguaje
		});
		
	}
	
	$('#botonQuitar').on("click", function() {
		var presentacion = {
				idPresentacion: idPre,
				descripcion: desc,
				comprometidoTotal: comproTotal,
				saldo: sal
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
			saldoPre = data.saldo;
			var encontrado = presentacionesList.find(x => x.idPresentacion == idPre);
			if(encontrado != undefined) {
				$('#mensajeError').text("Esta presentación ya fue agregada");
	        	$('#modalError').modal('show');
			} else {
	        	$('#modalAgregar').modal('show');
				alertaValidacion.remove();
				$('#cantidadAgregar').val("");
				bloque = obtenerBloque(data.descripcion);
				$('#tituloModal').text(data.idPresentacion);		
			}	
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
				{data: "comprometidoTotal"},
				{data: "saldo"},
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
				paisDestino: $('#destino').val()
			}
			
			var obj = {
					ordenVenta: ordenVenta,
					listaPresentacion: presentacionesList
			}
			
			registrarOrdenVenta(obj);
		}
	});
	
	function esMultiplo(cantidad) {
		var resto = cantidad % bloque;   
	    if ( resto != 0 ){
	    	return false;
	    } else {
	    	return true;
	    }
	}
	
});