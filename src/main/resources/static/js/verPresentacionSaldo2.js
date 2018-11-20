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
	
	var presentacion = {
			idPresentacion: $('#codigo').val()
	}
	
	function listarLotes(presentacion) {
		var tabla = $('#lotesTabla').DataTable({
			ajax: {
				type: "POST",
				contentType: "application/json",
				url: "/VerSaldoPresentacion/listarLoteSaldo",
				beforeSend: function() {
					$('#imagenCarga').show();
				},
				complete: function() {
					$('#imagenCarga').hide();
				},
				data: function(data) {
					return JSON.stringify(presentacion);
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
				{data: "idLote"},
				{data: "fechaProduccion"},
				{data: "cantidadTotal"},
				{data: "comprometidoTotal"},
				{data: "saldo"},
			],
			language: lenguaje
		});
				
	}
	
	$('#botonConfirmar').on("click", function() {
		$('#modalConfirmar').modal('hide');
		 window.location.href = "/VerSaldoPresentacion/";
	});
	
	$('#anterior').on("click", function() {
		$('#modalConfirmar').modal('show');
	});
	
	listarLotes(presentacion);

});


	