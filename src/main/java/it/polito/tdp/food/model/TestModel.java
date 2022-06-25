package it.polito.tdp.food.model;

public class TestModel {

	public static void main(String[] args) {
		Model m = new Model();
		m.creaGrafo(250);
		m.cercaCammino("baby ear", 10);
		for(String s : m.getBest()) {
			System.out.println(s);
		}
		
		}

}
