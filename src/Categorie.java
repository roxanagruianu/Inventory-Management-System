package proiect;


public class Categorie {
	String nume;
	String cod;
	public Categorie(String nume, String cod) {
		super();
		this.nume = nume;
		this.cod = cod;
	}
	@Override
	public String toString() {
		return "Categorie [nume=" + nume + ", cod=" + cod + "]";
	}
	public String getNume() {
		return nume;
	}
	public void setNume(String nume) {
		this.nume = nume;
	}
	public String getCod() {
		return cod;
	}
	public void setCod(String cod) {
		this.cod = cod;
	}

	
}

