package com.academy.Enums;

public enum Turno {

	MANHA("MANHÃ"),
	TARDE("TARDE"),
	NOITE("NOITE");
	
	private String turno;
	
	private Turno(String turno) {
		this.turno=turno;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}
		
		
}
