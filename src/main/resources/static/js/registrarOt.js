var idMuestra = '';
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
	
	$("#menu-toggle").click(function (e) {
		e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
	
	var elegirMuestra = function(tbody, table) {
		$(tbody).on("click", "span.btn", function(){
			var data = table.row($(this).parents("tr")).data();
			idMuestra=data.idMuestra;
			if(data.estado === 'Muestreado') {
	        	$('#mensajeError').text('Muestra ya tiene asignado OT');   //Muestreada --> ot
	        	$('#modalError').modal('show');
			} else {
	        	$('#modalConfirmar').modal('show');
				$('#tituloModal').text('idMuestra: ' + data.idMuestra);	
			}
		});
	}	
	
	var listar = function() {
		var tabla = $('#muestrasTabla').DataTable({
			ajax: {
				url: "/RegistrarOt/listarMuestraOt",
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
				{data: "idMuestra"},  
				{data: "ot"},
				{data: "fechaCreacion"},
				{data: "fechaMuestreado"},
				{data: "idLaboratorio"},
				{data: "cantidadTotal"},
				{data: "estado"},
				{defaultContent: "<span class='btn btn-success' data-toggle='modal'>" +
										"Registrar <span class='fa fa-plus-circle'></span></span>", "sClass": "text-center"}
			],
			language: lenguaje
		});
		
		elegirMuestra('#muestrasTabla tbody',tabla);
	}
	
	listar();

	$('#botonAceptar').on("click", function() {
		window.location.href = "/RegistrarOt/" + idMuestra;
	});

});