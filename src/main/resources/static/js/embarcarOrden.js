var idOrden = '';
var desc = '';
var cant = 0;
var ClienteList = [];
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
	
			
	var listar = function() {
		var tabla = $('#ordenesTabla').DataTable({
			ajax: {
				url: "EmbarcarOrden/listarOrdenVenta",
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
				{data: "Codigo_De_Orden"},  
				{data: "Cliente"},
				{data: "Fecha_Asignacion"},
				{data: "Cantidad_Total"},
				{data: "Estado"}				
			],
			language: lenguaje
		});
		
		//agregarCantidad('#ordenesTabla tbody',tabla)
	}
	
	listar();
	
	$('#botonEmbarcar').on("click", function() {
		idOrden ;/*Obtener la el idOrden del click EN LA TABLA*/
		/*ENVIAR EL IDORDEN A LA SIGUIENTE PANATALLA*/		
	});
	
	var listarA = function(idOrden) { /*DEBE DE PASAR COMO PARAMETRO EL IDORDEN CAPATADO*/
		var tabla = $('#asignacionesTabla').DataTable({
			ajax: {
				url: "EmbarcarOrden/listarAsignacion", 
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
				{data: "Codigo_De_Orden"},  
				{data: "Cliente"},
				{data: "Fecha_Asignacion"},
				{data: "Cantidad_Total"},
				{data: "Estado"}				
			],
			language: lenguaje
		});
		
		//agregarCantidad('#ordenesTabla tbody',tabla)
	}
	
	listarA();
	
});