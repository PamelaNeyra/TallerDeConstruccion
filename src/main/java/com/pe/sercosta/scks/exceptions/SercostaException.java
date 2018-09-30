package com.pe.sercosta.scks.exceptions;

public class SercostaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String mensajeUsuario;
	private String mensajeAplicacion;
	
	public SercostaException(String mensajeUsuario, String mensajeAplicacion)
	{
		super(mensajeAplicacion);
		this.mensajeAplicacion = mensajeAplicacion;
		this.mensajeUsuario = mensajeUsuario;
	}

	public String getMensajeUsuario() {
		return mensajeUsuario;
	}

	public void setMensajeUsuario(String mensajeUsuario) {
		this.mensajeUsuario = mensajeUsuario;
	}

	public String getMensajeAplicacion() {
		return mensajeAplicacion;
	}

	public void setMensajeAplicacion(String mensajeAplicacion) {
		this.mensajeAplicacion = mensajeAplicacion;
	}
		
}
