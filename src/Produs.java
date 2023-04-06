package proiect;


public class Produs {
	String cod;
	String nume;
	String categorie;
	double pretDeCumparare;
	double pretDeVanzare;
	int cantitate;
	public Produs(String cod, String nume, String categorie, double pretDeCumparare, double pretDeVanzare,
			int cantitate) {
		super();
		this.cod = cod;
		this.nume = nume;
		this.categorie = categorie;
		this.pretDeCumparare = pretDeCumparare;
		this.pretDeVanzare = pretDeVanzare;
		this.cantitate = cantitate;
	}
	@Override
	public String toString() {
		return "Produs [cod=" + cod + ", nume=" + nume + ", categorie=" + categorie + ", pretDeCumparare="
				+ pretDeCumparare + ", pretDeVanzare=" + pretDeVanzare + ", cantitate=" + cantitate + "]";
	}
	public String getCod() {
		return cod;
	}
	public void setCod(String cod) {
		this.cod = cod;
	}
	public String getNume() {
		return nume;
	}
	public void setNume(String nume) {
		this.nume = nume;
	}
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	public double getPretDeCumparare() {
		return pretDeCumparare;
	}
	public void setPretDeCumparare(double pretDeCumparare) {
		this.pretDeCumparare = pretDeCumparare;
	}
	public double getPretDeVanzare() {
		return pretDeVanzare;
	}
	public void setPretDeVanzare(double pretDeVanzare) {
		this.pretDeVanzare = pretDeVanzare;
	}
	public int getCantitate() {
		return cantitate;
	}
	public void setCantitate(int cantitate) {
		this.cantitate = cantitate;
	}
	
}
