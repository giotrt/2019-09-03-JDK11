package it.polito.tdp.food.model;

import java.util.ArrayList;
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
	private List<String> best;
	private double pesoMax;
	
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
	
	public void cercaCammino(String partenza, int N){
//		inizializzo la best e il peso max
		
		this.best = null;
		this.pesoMax = 0.0;
		
//		Creo la mia soluzione parziale
		List<String> parziale = new ArrayList<>();
		
//		ci aggiungo la partenza dato che la so
		
		parziale.add(partenza);
//		avvio la ricorsione
		
		cerca(parziale, 1, N);
		
		
		
	}

	private void cerca(List<String> parziale, int livello, int N) {
//		condizioni di terminazione
		if(livello == N+1) {
//			controllo se il peso che ho è maggiore di quello di prima
			double peso = trovaPeso(parziale);
			if(peso > this.getPesoMax()) {
				this.pesoMax = peso;
				best = new ArrayList<>(parziale);
			}
			return;
		}
		
		for(String s : Graphs.neighborListOf(this.grafo, parziale.get(livello-1))) {
			if(!parziale.contains(s)) {
				parziale.add(s);
				cerca(parziale, livello+1, N);
				parziale.remove(parziale.size()-1);
			}
		}
		
	}

	private double trovaPeso(List<String> parziale) {
		double peso = 0.0;
		for(int i=1; i<parziale.size(); i++) {
			double p = this.grafo.getEdgeWeight(this.grafo.getEdge(parziale.get(i-1), parziale.get(i)));
			peso += p;
		}
		return peso;
	}

	public double getPesoMax() {
		return pesoMax;
	}

	public List<String> getBest() {
		return best;
	}
	
	
}
