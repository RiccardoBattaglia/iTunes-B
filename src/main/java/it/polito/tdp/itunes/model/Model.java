package it.polito.tdp.itunes.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.jgrapht.graph.SimpleWeightedGraph;


import it.polito.tdp.itunes.db.ItunesDAO;


public class Model {
	

	
	private ItunesDAO dao;
	private Graph<Album, DefaultWeightedEdge> grafo;
	private Map<Album, Double> albumMap = new HashMap<>();
	private List<Album> albumList;
	
	public Model() {
		this.dao = new ItunesDAO();
		
		this.albumList = this.dao.getAllAlbums();
		
		for(Album i : albumList) {
			albumMap.put(i, this.dao.getDurataDaAlbum(i.getAlbumId()));
		}
		
	}
	

	public void creaGrafo(int n) {
		// TODO Auto-generated method stub

	this.grafo = new SimpleDirectedWeightedGraph<Album, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
	// Aggiunta VERTICI 
	List<Album> vertici=new LinkedList<>();
	
	for(Album i : this.albumMap.keySet()) {
		if(this.albumMap.get(i)>=n) {
			vertici.add(i);
		}
	}
	
	Graphs.addAllVertices(this.grafo, vertici);
	
	
//	this.retailersMap=new HashMap<>();
//	for(Retailers i : retailersList) {
//		this.retailersMap.put((i), this.dao.getProdottiPerRetailerInAnno(i, anno));
//	}

	
	// Aggiunta ARCHI
	
	for (Album v1 : vertici) {
		for (Album v2 : vertici) {
			if(this.albumMap.get(v1)!=this.albumMap.get(v2) && (this.albumMap.get(v1)+this.albumMap.get(v2))>(4*n)){ 
		      if(this.albumMap.get(v1)<this.albumMap.get(v2)) {
		        this.grafo.addEdge(v1,v2);
		        this.grafo.setEdgeWeight(this.grafo.getEdge(v1, v2), this.albumMap.get(v2)+this.albumMap.get(v1));
		      }
		      if(this.albumMap.get(v1)>this.albumMap.get(v2)) {
			        this.grafo.addEdge(v2,v1);
			        this.grafo.setEdgeWeight(this.grafo.getEdge(v2, v1), this.albumMap.get(v1)+this.albumMap.get(v2));
			      }
//		      this.grafo.setEdgeWeight(this.grafo.getEdge(v1, v2), this.dao.getPeso(v1, v2, anno));
			}
			}
			}

	}

public int nVertici() {
	return this.grafo.vertexSet().size();
}

public int nArchi() {
	return this.grafo.edgeSet().size();
}

public Set<Album> getVertici(){
	
	Set<Album> vertici=this.grafo.vertexSet();
	
	return vertici;
}

public Set<DefaultWeightedEdge> getArchi(){
	
	Set<DefaultWeightedEdge> archi=this.grafo.edgeSet();
	
	return archi;
}

//public List<Set<User>> getComponente() {
//	ConnectivityInspector<User, DefaultWeightedEdge> ci = new ConnectivityInspector<>(this.grafo) ;
//	return ci.connectedSets() ;
//}

public Set<Album> getComponente(Album v) {
	ConnectivityInspector<Album, DefaultWeightedEdge> ci = new ConnectivityInspector<>(this.grafo) ;
	return ci.connectedSetOf(v) ;
}

public List<String> getVerticiName(){
	
	List<String> nomi=new LinkedList<>();
	
	for(Album i : this.grafo.vertexSet()) {
		nomi.add(i.getTitle());
	}
	
	Collections.sort(nomi);
	
	return nomi;
}	 

public Double sommaPesi(Album a) {
	
	double sommaIn=0.0;
	double sommaOut=0.0;
	
	for(DefaultWeightedEdge i : this.grafo.incomingEdgesOf(a)) {
		sommaIn=sommaIn+this.grafo.getEdgeWeight(i);
	}
	
	for(DefaultWeightedEdge i : this.grafo.outgoingEdgesOf(a)) {
		sommaOut=sommaOut+this.grafo.getEdgeWeight(i);
	}
	
	return sommaIn-sommaOut;
	
}

public List<Dettaglio> calcolaAdiacenze(String albumT) {
	Album album=new Album(null, null);
	
	for(Album i : this.grafo.vertexSet()) {
		if(i.getTitle().equals(albumT)) {
			album=i;
		}
	}
	
	List<Album> successori = new LinkedList<>();
	List<Dettaglio> dettagli = new LinkedList<>();
	
	for(DefaultWeightedEdge i : this.grafo.outgoingEdgesOf(album)) {
		successori.add(this.grafo.getEdgeTarget(i));
	}
	
	System.out.println(successori);
	
	for(Album i : successori) {
		dettagli.add(new Dettaglio ( i , (this.sommaPesi(i)) ));
	}
	
	Collections.sort(dettagli);
	
	return dettagli;
}
	
}
