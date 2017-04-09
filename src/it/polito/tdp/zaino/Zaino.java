package it.polito.tdp.zaino;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Zaino {
	
	private int capienza;
	private List<Pezzo> pezzi;

	public Zaino(int capienza) {
		this.capienza = capienza;
		this.pezzi= new ArrayList<Pezzo>();
	}
	
	public void addPezzo(Pezzo p){
		if(!pezzi.contains(p))
			pezzi.add(p);
		else
			throw new IllegalArgumentException("Pezzo duplicato");
	}

	public Set<Pezzo> risolvi (){
		Set<Pezzo> parziale = new HashSet<Pezzo>();
		Set<Pezzo> best = new HashSet <Pezzo>();
		scegli(parziale,0,best);
		return best;
	}
	
	public void scegli(Set<Pezzo> parziale,int livello,Set<Pezzo> best){
		
		if(costo(parziale)>costo(best)){
			best.clear();
			best.addAll(parziale);
			System.out.println(parziale);
		}
		
		for(Pezzo p : pezzi){
			if(!parziale.contains(p))
				if(peso(parziale) + p.getPeso() <= capienza){
					parziale.add(p);
					scegli(parziale,livello+1,best);
					parziale.remove(p);
				}
					
		}
		
	}
	
	private int costo(Set<Pezzo> parziale){
		int ris = 0;
		
		for(Pezzo p: parziale)
			ris += p.getCosto();
		
		return ris;
	}
	
	private int peso(Set<Pezzo> parziale){
		int ris = 0;
		
		for(Pezzo p : parziale)
			ris += p.getPeso();
		
		return ris;
	}

	public static void main(String[] args) {
		
		Zaino z = new Zaino(15);
		
		z.addPezzo(new Pezzo(12,4,"verde"));
		z.addPezzo(new Pezzo(2,2,"Azzurro"));
		z.addPezzo(new Pezzo(1,1,"Salmone"));
		z.addPezzo(new Pezzo(4,10,"Giallo"));
		z.addPezzo(new Pezzo(1,2,"Grigio"));
		
		Set<Pezzo> soluzione = z.risolvi();
		
		System.out.println(soluzione);

	}

}
