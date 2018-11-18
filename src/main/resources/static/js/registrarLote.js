var idPre = '';
var cant = 0;
var cantTotal = 0;
var contenidoList = [];
var bloque = 0;

$(document).ready( function () {	
	
	//Elimina el registro de la segunda tabla
	var eliminarContenido = function(tbody,table){
		$(tbody).on('click', 'span.btn', function () {
			var data = table.row($(this).parents("tr")).data();
			if(data != undefined) {
				var idPre = data.idPresentacion;
				var cant = data.cantidad;
				var pos = contenidoList.findIndex(x => x.idPresentacion == idPre);
				contenidoList.splice(pos, 1);
				cantTotal = cantTotal - Number(cant);
				actualizarTablaContenido();
				actualizarCantidadTotal();
			}
		});
	}
	
	//Obtiene el bloque de la presentación
	var obtenerBloque = function(descripcion) {
		var posAux = descripcion.search("kg");
		var subDes = descripcion.slice(posAux + 2, descripcion.length);
		posAux = subDes.search("kg");
		return subDes.slice(posAux - 5, posAux).trim();
	}

	//Muestra el modal para agregar la cantidad
	var agregarCantidad = function(tbody, table) {
		$(tbody).on("click", "span.btn", function(){
			var data = table.row($(this).parents("tr")).data();
			idPre = data.idPresentacion;
			var encontrado = contenidoList.find(x => x.idPresentacion == idPre);
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
	
	//Actualiza la segunda tabla
	var actualizarTablaContenido = function(){
		var tabla = $('#contenidosTabla').DataTable({
			destroy: true,
			data: contenidoList,
			order: [[ 0, "asc" ]],
			responsive: true,
			lengthMenu: tamañoMenu,
			dom: domTabla,
			buttons: [
				{
					extend: "excelHtml5",
					text: "<i class='fa fa-file-excel'></i> Excel",
					title: "Lista de Contenidos",
					className: "btn btn-success",
					exportOptions: {
						columns: [0,1]
					}
				},
				{
	                extend: 'pdfHtml5',
	                text: "<i class='fa fa-file-pdf'></i> PDF",
	                title: 'Lista de Contenidos',
	                className: "btn btn-danger",
	                exportOptions: {
						columns: [0,1]
					}
				}
		    ],
		    columns: [
				{data: "idPresentacion"},
				{data: "cantidad"},
				{defaultContent: "<span class='btn btn-danger'>" +
					"Retirar <span class='fa fa-minus-circle'></span></span>", "sClass": "text-center"}
			],
			language: lenguaje
		});
		
		eliminarContenido('#contenidosTabla tbody',tabla);
	}
	
	//Evento cuando se da clic en agregar
	$('#botonAgregar').on("click", function() {
		cant = $('#cantidadAgregar').val();
		if(cant > 0) {
			if(esMultiplo(cant)) {
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
			$('#validacion').text("La cantidad no puede ser negativa ni 0.");
		}
	});
	
	//Evento cuando se da clic en confirmar
	$('#botonConfirmar').on("click", function() {
		for(i = 0; i < contenidoList.length; i++) {
			contenidoList[i].idLote = $('#codigo').val();
		}
		var lote = {
			idLote: $('#codigo').val(),
			fechaProduccion: $('#fecha').val(),
			cantidadRecepcion: $('#cantidad').val(),
			esReempaque: $('#reempaque').val(),
			contenidoList: contenidoList	
		}
		$('#modalConfirmar').modal('hide');
		registrarLote(lote);
	});
	
	//Evento cuando se da clic en guardar
	$('#guardarLote').on("click", function() {
		var esValido = validarRegistrarLote();
		if(esValido) {
			$('#tituloModal').text($('#codigo').val());
			$('#modalConfirmar').modal('show');
		}
	});
	
	//Evento cuando se marca el check de reempaque
	$('#reempaque').on("click", function() {
		var valor = $('#reempaque').val();
		if(valor === 'true') {
			$('#reempaque').val('false');
		} else {
			$('#reempaque').val('true');
		}
	});
	
	//Llenar la tabla 1
	var listar = function() {
		var tabla = $('#presentacionesTabla').DataTable({
			ajax: {
				url: "/RegistrarLote/listarPresentacion",
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
			lengthMenu: [5, 10, 15, 20],
			dom: domTabla,
			buttons: [
				{
					extend: "excelHtml5",
					text: "<i class='fa fa-file-excel'></i> Excel",
					title: "Lista de Presentaciones",
					className: "btn btn-success",
					exportOptions: {
						columns: [0,1]
					}
				},
				{
	                extend: 'pdfHtml5',
	                text: "<i class='fa fa-file-pdf'></i> PDF",
	                title: 'Lista de Presentaciones',
	                className: "btn btn-danger",
	                exportOptions: {
						columns: [0,1]
					}
				}
		    ],
			columns: [
				{data: "idPresentacion"},
				{data: "descripcion"},
				{defaultContent: "<span class='btn btn-success' data-toggle='modal'>" +
						"Agregar <span class='fa fa-plus-circle'></span></span>", "sClass": "text-center"}
			],
			language: lenguaje
		});
		
		agregarCantidad('#presentacionesTabla tbody', tabla);
	}
	
	//Registra el Lote
	function registrarLote(lote) {
		$.ajax({
	        type: "POST",
	        contentType: "application/json",
	        url: "/RegistrarLote/registrarLote",
	        data: JSON.stringify(lote),
			beforeSend: function() {
				$('#imagenCarga').show();
			},
			complete: function() {
				$('#imagenCarga').hide();
			},
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
	
	//Limpia los controles
	function limpiarControles() {
		$('#codigo').val("");
		$('#fecha').val("");
		$('#cantidad').val("");
		contenidoList = [];
		cantTotal = 0;
	}
	
	//Actualiza la cantidad total
	function actualizarCantidadTotal() {
		$('#cantidadTotal').text('Cantidad Total: ' + cantTotal)
	}
	
	//Valida los campos
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
		if(mensaje != "") {
        	$('#mensajeError').text(mensaje);
        	$('#modalError').modal('show');
        	return false;	
		} else {
			return true;
		}
	}
	
	//Pregunta si es múltiplo
	function esMultiplo(cantidad) {
		var resto = cantidad % bloque;   
	    if ( resto != 0 ){
	    	return false;
	    } else {
	    	return true;
	    }
	}
	
	//Inicializa la tabla 1
	listar();
	
});