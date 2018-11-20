var idLote;
var idPre;
var loteBody;
var retiroList = [];
var cant = 0;
var cantTotal = 0;
var fecha;

$(document).ready( function () {
	
	var eliminarRetiro = function(tbody,table){
		$(tbody).on('click', 'span.btn', function () {
			var data = table.row($(this).parents("tr")).data();
			if(data != undefined) {
				var idPre = data.idPresentacion;
				var cant = data.cantidad;
				var pos = retiroList.findIndex(x => x.idPresentacion == idPre && x.idLote == idLote);
				retiroList.splice(pos, 1);
				cantTotal = cantTotal - Number(cant);
				actualizarTablaRetiro();
				actualizarCantidadTotal();
			}
		});
	}
	
	var retirarContenido = function(tbody, table) {
		$(tbody).on("click", "span.btn", function(){
			var data = table.row($(this).parents("tr")).data();
			idPre = data.idPresentacion;
			saldo = data.saldo;
			var encontrado = retiroList.find(x => x.idPresentacion == idPre && x.idLote == idLote);
			if(encontrado != undefined) {
				$('#mensajeError').text("Este contenido ya fue seleccionado");
	        	$('#modalError').modal('show');
			} else {
	        	$('#modalCantidad').modal('show');
				alertaValidacion.remove();
				$('#cantidadRetirar').val("");
				$('#modalCantidad #tituloModal').text(data.idPresentacion);		
			}	
		});
	}
	
	$('#botonRetirar').on("click", function() {
		cant = $('#cantidadRetirar').val();
		if(cant > 0) {
			if(cant <= saldo) {
				alertaValidacion.remove();
				var retiro = {
					idLote: idLote,
					fechaProduccion: fecha,
					idPresentacion: idPre,
					cantidad: cant
				}
				cantTotal = cantTotal + Number(cant);
				retiroList.push(retiro);
				actualizarTablaRetiro();
				actualizarCantidadTotal();
				$('#modalCantidad').modal('hide');
			} else {
				alertaValidacion.appendTo($('#cuerpoRetirar'));
				$('#validacion').text("La cantidad no puede ser mayor al saldo actual.");
			}				
		} else {
			alertaValidacion.appendTo($('#cuerpoRetirar'));
			$('#validacion').text("La cantidad no puede ser negativa ni 0.");
		}
	});
	
	var actualizarTablaRetiro = function(){
		var tabla = $('#retiroTabla').DataTable({
			destroy: true,
			data: retiroList,
			order: [[ 0, "asc" ]],
			responsive: true,
			lengthMenu: tamañoMenu,
			dom: domTabla,
			buttons: [
				{
					extend: "excelHtml5",
					text: "<i class='fa fa-file-excel'></i> Excel",
					title: "Lista de Retiros",
					className: "btn btn-success",
					exportOptions: {
						columns: [0,1,2,3]
					}
				},
				{
	                extend: 'pdfHtml5',
	                text: "<i class='fa fa-file-pdf'></i> PDF",
	                title: 'Lista de Retiros',
	                className: "btn btn-danger",
	                exportOptions: {
						columns: [0,1,2,3]
					}
				}
		    ],
		    columns: [
				{data: "idLote"},
				{data: "fechaProduccion"},
				{data: "idPresentacion"},
				{data: "cantidad"},
				{defaultContent: "<span class='btn btn-danger'>" +
					"Eliminar <span class='fa fa-minus-circle'></span></span>", "sClass": "text-center"}
			],
			language: lenguaje
		});
		
		eliminarRetiro('#retiroTabla tbody',tabla);
	}
	
	var actualizarTablaContenido = function() {
		var tabla = $('#loteContenidoTabla').DataTable({
			destroy: true,
			ajax: {
				url: "/RetirarContenido/listarContenido",
				type: "POST",
				contentType: "application/json",
				data: function(data) {
					return JSON.stringify(loteBody);
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
					title: "Lista de Contenidos",
					className: "btn btn-success",
					exportOptions: {
						columns: [0,1,2,3]
					}
				},
				{
	                extend: 'pdfHtml5',
	                text: "<i class='fa fa-file-pdf'></i> PDF",
	                title: 'Lista de Contenidos',
	                className: "btn btn-danger",
	                exportOptions: {
						columns: [0,1,2,3]
					}
				}
		    ],
			columns: [
				{data: "idPresentacion"},
				{data: "cantidad"},
				{data: "comprometido"},
				{data: "saldo"},
				{defaultContent: "<span class='btn btn-danger' data-toggle='modal'>" +
						"Retirar <span class='fa fa-minus-circle'></span></span>", "sClass": "text-center"}
			],
			language: lenguaje
		});
		
		retirarContenido('#loteContenidoTabla tbody', tabla);
	}
		
	var elegirLote = function(tbody, table) {
		$(tbody).on("click", "span.btn", function(){
				var data = table.row($(this).parents("tr")).data();
				idLote = data.idLote;
				fecha = data.fechaProduccion;
				loteBody = {
						idLote: idLote
				};
				$('#modalLote #tituloModal').text(idLote);
	        	$('#modalLote').modal('show');
	        	actualizarTablaContenido();
		});
	}
	
	var actualizarTablaLote = function(){
		var tabla = $('#loteTabla').DataTable({
			ajax: {
				url: "/RetirarContenido/listarLote",
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
					title: "Lista de Lotes",
					className: "btn btn-success",
					exportOptions: {
						columns: [0,1]
					}
				},
				{
	                extend: 'pdfHtml5',
	                text: "<i class='fa fa-file-pdf'></i> PDF",
	                title: 'Lista de Lotes',
	                className: "btn btn-danger",
	                exportOptions: {
						columns: [0,1]
					}
				}
		    ],
			columns: [
				{data: "idLote"},
				{data: "fechaProduccion"},
				{defaultContent: "<span class='btn btn-success' data-toggle='modal'>" +
						"Elegir <span class='fa fa-check'></span></span>", "sClass": "text-center"}
			],
			language: lenguaje
		});
		
		elegirLote('#loteTabla tbody', tabla);
	}
	
	$('#terminarRegistro').on("click", function() {
		var esValido = validarRegistrarOrden();
		if(esValido) {
			var orden = {
				fechaRetiro: $('#fch_retiro').val(),
				idMotivo: $('#cboMotivo').val(),
				retiroList: retiroList	
			}
			registrarOrden(orden);
		}
	});
	
	function limpiarControles() {
		$('#fch_retiro').val("");
		retiroList = [];
		cantTotal = 0;
	}
	
	function actualizarCantidadTotal() {
		$('#cantidadTotal').text('Cantidad Total: ' + cantTotal)
	}
	
	function registrarOrden(orden) {
		$.ajax({
	        type: "POST",
	        contentType: "application/json",
	        url: "/RetirarContenido/registrarOrdenRetiro",
	        data: JSON.stringify(orden),
			beforeSend: function() {
				$('#imagenCarga').show();
			},
			complete: function() {
				$('#imagenCarga').hide();
			},
	        success: function (data) {	        	
	            $('#modalExito').modal('show');
	            limpiarControles();
	    		actualizarTablaRetiro();
	    		actualizarCantidadTotal();
	        },
	        error: function (xhr, ajaxOptions, thrownError) {
	        	var response = JSON.parse(xhr.responseText);	   
	        	$('#mensajeError').text(response.message);
	        	$('#modalError').modal('show');
	        }
	    });
	}
	
	function validarRegistrarOrden() {
		var mensaje = "";
		if($('#fch_retiro').val() === "")
			mensaje = "La Fecha de Retiro es requerida.";
		if(retiroList.length === 0)
			mensaje = "La Lista de Contenidos a retirar no puede ser vacía.";
		if(mensaje != "") {
        	$('#mensajeError').text(mensaje);
        	$('#modalError').modal('show');
        	return false;	
		} else {
			return true;
		}
	}
	
	actualizarTablaLote();	
	
});