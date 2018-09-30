var idPre = '';
var desc = '';
var cant = 0;
var contenidoList = [];
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
	
	var agregarCantidad = function(tbody, table) {
		$(tbody).on("click", "span.btn", function(){
			$('#cantidad').val("");
			var data = table.row($(this).parents("tr")).data();
			idPre = data.idPresentacion;
			desc = data.descripcion;
			$('#exampleModalCenterTitle').text(data.idPresentacion);			
		});
	}
	
	var actualizarTablaContenido = function(){
		$('#contenidosTabla').DataTable({
			destroy: true,
			data: contenidoList,
			order: [[ 0, "asc" ]],
			responsive: true,
			columns: [
				{title: "Código SAP"},
				{title: "Descripcion"},
				{title: "Cantidad"},
				{defaultContent: "<button>H</button>"}
			],
			language: lenguaje
		});
	}
	
	$('#botonAgregar').on("click", function() {
		cant = $('#cantidad').val();
		var contenido = [
			idPre,
			desc,
			cant
		]
		contenidoList.push(contenido);
		actualizarTablaContenido();
		$('#modalAgregar').modal('hide');
	});
	
	var listar = function() {
		var tabla = $('#presentacionesTabla').DataTable({
			ajax: {
				url: "/RegistrarLote/listarPresentacion",
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
		
		agregarCantidad('#presentacionesTabla tbody',tabla)
	}
	
	listar();
	
});