package it.polito.tdp.food.model;

public class Arco {

	private String tipo_porzione_1;
	private String tipo_porzione_2;
	private int peso;
	
	
	public Arco(String tipo_porzione_1, String tipo_porzione_2, int peso) {
		super();
		this.tipo_porzione_1 = tipo_porzione_1;
		this.tipo_porzione_2 = tipo_porzione_2;
		this.peso = peso;
	}
	
	public String getTipo_porzione_1() {
		return tipo_porzione_1;
	}
	public void setTipo_porzione_1(String tipo_porzione_1) {
		this.tipo_porzione_1 = tipo_porzione_1;
	}
	public String getTipo_porzione_2() {
		return tipo_porzione_2;
	}
	public void setTipo_porzione_2(String tipo_porzione_2) {
		this.tipo_porzione_2 = tipo_porzione_2;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	
	
}
