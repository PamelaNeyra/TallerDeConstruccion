var ot = '';
var fechaMuestreado = '';
var nombreLaboratorio = '';
var cantidad = '';


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
			ot =data.ot;
			fechaMuestreado = data.fechaMuestreado;
			nombreLaboratorio = data.nombreLaboratorio;
			cantidad = data.cantidad;
	        $('#modalConfirmar').modal('show');
		    $('#tituloModal').text('OT: ' + data.ot);	
			
		});
	}	
	

	var listar = function() {
		var tabla = $('#muestreosTabla').DataTable({
			ajax: {
				url: "/VerSaldoOt/listarMuestreoOt",
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
				{data: "ot"},
				{data: "fechaMuestreado"},
				{data: "nombreLaboratorio"},
				{data: "cantidad"},
				{defaultContent: "<span id='detalle' class='btn btn-success' data-toggle='modal'>" +
										"Ver Detalle <span class='fa fa-plus-circle'></span></span>", "sClass": "text-center"},
			],		
			language: lenguaje
								
		});
		
		verDetalle('#muestreosTabla tbody',tabla);

	}
	
	listar();

	$('#botonAceptar').on("click", function() {
		window.location.href = "/VerSaldoOt/" + ot + "/" + fechaMuestreado + "/" + nombreLaboratorio + "/" + cantidad ;
	});
	



});