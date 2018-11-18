var idPro = '';
var desc = '';
var cant = 0;
var productoList = [];
var cantTotal = 0;

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
			lengthMenu: tamañoMenu,
			dom: domTabla,
			buttons: [
				{
					extend: "excelHtml5",
					text: "<i class='fa fa-file-excel'></i> Excel",
					title: "Lista de Productos Terminados Seleccionados",
					className: "btn btn-success",
					exportOptions: {
						columns: [0,1,2]
					}
				},
				{
	                extend: 'pdfHtml5',
	                text: "<i class='fa fa-file-pdf'></i> PDF",
	                title: 'Lista de Productos Terminados Seleccionados',
	                className: "btn btn-danger",
					exportOptions: {
						columns: [0,1,2]
					}
				}
		    ],
			columns: [
				{data: "idProductoTerminado"},
				{data: "descripcion"},
				{data: "cantidadTotal"},
				{defaultContent: "<span class='btn btn-danger'>" + 
					             "Retirar <span class='fa fa-minus-circle'></span></span>", "sClass": "text-center" }
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
					title: "Lista de Productos Terminados",
					className: "btn btn-success",
					exportOptions: {
						columns: [0,1,2]
					}
				},
				{
	                extend: 'pdfHtml5',
	                text: "<i class='fa fa-file-pdf'></i> PDF",
	                title: 'Lista de Productos Terminados',
	                className: "btn btn-danger",
	                exportOptions: {
						columns: [0,1,2]
					}
				}
		    ],
			columns: [
				{data: "idProductoTerminado"},
				{data: "descripcion"},
				{data: "cantidadTotal"},
				{defaultContent: "<span class='btn btn-success' data-toggle='modal'>" +
						"Agregar <span class='fa fa-plus-circle'></span></span>", "sClass": "text-center"}
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
			beforeSend: function() {
				$('#imagenCarga').show();
			},
			complete: function() {
				$('#imagenCarga').hide();
			},
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