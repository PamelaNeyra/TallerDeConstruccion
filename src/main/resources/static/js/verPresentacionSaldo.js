var idPresentacion = '';
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
	
	$('[data-toggle="tooltip"]').tooltip(); 
	
	$("#menu-toggle").click(function (e) {
		e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
	
	var verDetalle = function(tbody, table) {
		$(tbody).on("click", "#detalle", function(){
			var data = table.row($(this).parents("tr")).data();
			idPresentacion=data.idPresentacion;
	        $('#modalConfirmar').modal('show');
		    $('#tituloModal').text('idPresentacion: ' + data.idPresentacion);	
			
		});
	}	
	
	var verAsignacion = function(tbody, table) {
		$(tbody).on("click", "#asignacion", function(){
			var data = table.row($(this).parents("tr")).data();
			idPresentacion=data.idPresentacion;
	        $('#modalConfirmar1').modal('show');
		    $('#tituloModal1').text('idPresentacion: ' + data.idPresentacion);	
			
		});
	}	
	
	
	var listar = function() {
		var tabla = $('#presentacionesTabla').DataTable({
			ajax: {
				url: "/VerSaldoPresentacion/listarPresentacion",
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
			columns: [
				{data: "idPresentacion"},
				{data: "descripcion"},
				{data: "cantidadTotal"},
				{data: "comprometidoTotal"},
				{data: "saldo"},
				{defaultContent: "<span id='detalle' class='btn btn-success' data-toggle='modal'>" +
										"Ver Detalle <span class='fa fa-plus-circle'></span></span>", "sClass": "text-center"},
				{defaultContent: "<span id='asignacion' class='btn btn-primary' data-toggle='modal'>" +
					               "Ver Asignaciones <span class='fa fa-plus-circle'></span></span>", "sClass": "text-center"}
			],
			language: lenguaje
		});
		
		verDetalle('#presentacionesTabla tbody',tabla);
		verAsignacion('#presentacionesTabla tbody',tabla);
	}
	
	listar();

	$('#botonAceptar').on("click", function() {
		window.location.href = "/VerSaldoPresentacion/" + idPresentacion;
	});
	
	$('#botonAceptar1').on("click", function() {
		window.location.href = "/VerSaldoPresentacion/index3/" + idPresentacion;
	});


});