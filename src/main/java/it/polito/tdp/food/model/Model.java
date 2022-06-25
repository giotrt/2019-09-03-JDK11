package it.polito.tdp.food.model;

import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.food.db.FoodDao;

public class Model {
	
	private Graph<String, DefaultWeightedEdge> grafo;
	private FoodDao dao;
	private Map<Integer, Portion> idMap;
	private List<String> vertici;
	
	public Model() {
		dao = new FoodDao();
		dao.getAllPortions(idMap);
	}
	
	public void creaGrafo(double c) {
		
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		this.vertici = dao.getAllVertici( c);
		
		Graphs.addAllVertices(this.getGrafo(), getVertici());
		
		System.out.println("#VERTICI: " + getGrafo().vertexSet().size());
		
		List<Arco> archi = dao.getAllArchi();
		
		for(Arco a : archi) {
			if(getVertici().contains(a.getTipo_porzione_1()) && getVertici().contains(a.getTipo_porzione_2())) {
				Graphs.addEdge(this.getGrafo(), a.getTipo_porzione_1(), a.getTipo_porzione_2(), a.getPeso());
			}
		}
		System.out.println("#ARCHI: " + getGrafo().edgeSet().size());

	}
	
	public List<String> getComponenteConnessa(String s){
		
		List<String> connessa = Graphs.neighborListOf(this.getGrafo(), s);
		
		return connessa;
		
	}

	public List<String> getVertici() {
		return vertici;
	}
	
	public int getNumeroVertici() {
		return this.getGrafo().vertexSet().size();
	}
	public int getNumeroArchi() {
		return this.getGrafo().edgeSet().size();
	}

	public Graph<String, DefaultWeightedEdge> getGrafo() {
		return grafo;
	}
	
	
}
