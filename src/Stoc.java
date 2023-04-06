package proiect;

import java.util.ArrayList;
import java.util.List;

public class Stoc{
	List<Produs> stoc = new ArrayList<Produs>();
	double valoare;
	int produseStocScazut;
	int produseStocEpuizat;
	public double valoareStoc() {
		valoare=0;
		for(Produs produs: stoc) {
			valoare+= produs.getPretDeVanzare() * produs.getCantitate();
		}
		return valoare;	
	}
	public int nrProduseStocScazut() {
		produseStocScazut = 0 ;
		for(Produs produs : stoc) {
			if(produs.getCantitate()<10&&produs.getCantitate()>0) {
				produseStocScazut +=1;
			}
		}
		return produseStocScazut;
	}
	public int nrProduseStocEpuizat() {
		produseStocEpuizat = 0 ;
		for(Produs produs : stoc) {
			if(produs.getCantitate()==0) {
				produseStocEpuizat +=1;
			}
		}
		return produseStocEpuizat;
	}
	
	public Stoc(List<Produs> stoc) {
		super();
		this.stoc = stoc;
		this.valoare = this.valoareStoc();
		this.produseStocScazut = this.nrProduseStocScazut();
		this.produseStocEpuizat = this.nrProduseStocEpuizat();
	}
	@Override
	public String toString() {
		return "Stoc [stoc=" + stoc + ", valoare=" + valoare + ", produseStocScazut=" + produseStocScazut
				+ ", produseStocEpuizat=" + produseStocEpuizat + "]";
	}
	public List<Produs> getStoc() {
		return stoc;
	}
	public void setStoc(List<Produs> stoc) {
		this.stoc = stoc;
	}
	public double getValoare() {
		return valoare;
	}
	public void setValoare(double valoare) {
		this.valoare = valoare;
	}
	public int getProduseStocScazut() {
		return produseStocScazut;
	}
	public void setProduseStocScazut(int produseStocScazut) {
		this.produseStocScazut = produseStocScazut;
	}
	public int getProduseStocEpuizat() {
		return produseStocEpuizat;
	}
	public void setProduseStocEpuizat(int produseStocEpuizat) {
		this.produseStocEpuizat = produseStocEpuizat;
	}
	
	
}

