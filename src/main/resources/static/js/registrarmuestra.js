var idPro = '';
var desc = '';
var cant = 0;
var productoList = [];
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
	
	var agregarCantidad = function(tbody, table) {
		$(tbody).on("click", "span.btn", function(){
			$('#cantidad').val("");
			var data = table.row($(this).parents("tr")).data();
			idPro = data.idProducto;
			desc = data.descripcion;
			$('#exampleModalCenterTitle1').text(data.idProducto);			
		});
	}
	
	var actualizarTablaProductoSeleccionado = function(){
		var tabla=$('#productoSeleccionadoTabla').DataTable({
			destroy: true,
			data: productoList,
			order: [[ 0, "asc" ]],
			responsive: true,
			columns: [
				{title: "Código de Producto Terminado Seleccionado"},
				{title: "Descripción"},
				{title: "Cantidad a Muestrear"},
				{defaultContent:"<span class='btn btn-danger'>" + 
					             "<span class='fa fa-tras'></span></span>" }
			],
			language: lenguaje
		});
		eliminarFila('#productoSeleccionadoTabla tbody',tabla)
	}
	
	$('#botonAgregar').on("click", function() {
		cant = $('#cantidad').val();
		var producto = [
			idPro,
			desc,
			cant
		]
		productoList.push(producto);
		actualizarTablaProductoSeleccionado();
		$('#modalAgregar').modal('hide');
	});
	
	$('#botonAceptar').on("click",function()){
		var table = $('#productoSeleccionadoTabla').DataTable();
		var rows = table
		.rows()
		.remove()
		.draw();
	}
	
	var listar = function() {
		var tabla = $('#productoTerminado').DataTable({
			ajax: {
				url: "/RegistrarMuestra/listarProductoTerminado",
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
				{data: "idProducto"},
				{data: "descripcion"},
				{data: "cantidad_total"},
				{defaultContent: "<span class='btn btn-success' data-toggle='modal' data-target='#modalAgregar'>" +
						"<span class='fa fa-plus-circle'></span></span>"}
			],
			language: lenguaje
		});
		
		agregarCantidad('#productoTerminado tbody',tabla)
	}
	
	listar();
	
});