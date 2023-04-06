import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import junit.framework.Assert;

class teste {
	
	List<Produs> listaProduse = new ArrayList<Produs>();
	List<Categorie> listaCategorie = new ArrayList<Categorie>();
	Stoc stoc;
	
	@Test
	void testFuncStoc() {
		listaProduse.add(new Produs("144","Avion","Jucarie",22,28,20));
		listaProduse.add(new Produs("147","Borcan 0.25L","Borcan",2,3,0));
		listaProduse.add(new Produs("155","Borcan 0.37L","Borcan",3.5,5,5));
		listaProduse.add(new Produs("157","Tractor","Jucarie",26,40,4));
		listaProduse.add(new Produs("196","Masina de politie","Jucarie",15,30,0));
		stoc = new Stoc(listaProduse);
		assertEquals(2,stoc.getProduseStocEpuizat());
		assertEquals(2,stoc.getProduseStocScazut());
		assertEquals(745,stoc.getValoare());
	}
	
	@Test
	void testAdaugareProdusInFisier() {
		listaProduse.add(new Produs("155","Borcan 0.37L","Borcan",3.5,5,5));
		listaProduse.add(new Produs("157","Tractor","Jucarie",26,40,4));
		listaProduse.add(new Produs("196","Masina de politie","Jucarie",15,30,0));
		File file = new File("C:\\Users\\gruia\\Desktop\\Facultate\\Programare 3\\Proiect\\Proiect\\src\\fisierTest.txt");
		assertTrue(file.exists());
		try {
			new FileWriter(file,false).close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			FileOutputStream fos = new FileOutputStream(file,true);
			for(Produs p:listaProduse)
			{
				String adauga = p.getCod()+";"+p.getNume()+";"+p.getCategorie()+";"+p.getPretDeCumparare()+";"+p.getPretDeVanzare()+";"+p.getCantitate()+"\n";
				fos.write(adauga.getBytes());
			}
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			String content = Files.readString(file.toPath());
			assertTrue(content.contains("196;Masina de politie;Jucarie;15.0;30.0;0"));			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testEditatProdusInFisier() {
		File file = new File("C:\\Users\\gruia\\Desktop\\Facultate\\Programare 3\\Proiect\\Proiect\\src\\fisierTest.txt");
		assertTrue(file.exists());
		File tempFile = new File("C:\\Users\\gruia\\Desktop\\Facultate\\Programare 3\\Proiect\\Proiect\\src\\temp.txt");
		try {
			FileOutputStream temp = new FileOutputStream(tempFile);
			String codProdusDeEditat="196;";
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine())
			{
				String line = scanner.nextLine();
				if(line.contains(codProdusDeEditat)) {
					String lineEditat = "222" + line.substring(line.indexOf(";"));
					temp.write(lineEditat.getBytes());
					temp.write("\n".getBytes());
				}
				else {
					temp.write(line.getBytes());
					temp.write("\n".getBytes());
				}
			}
			scanner.close();
			temp.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		file.delete();
		tempFile.renameTo(file);
		String content;
		try {
			content = Files.readString(file.toPath());
			assertTrue(content.contains("222;"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	@Test
	void testStergereProdusDinFisier() {
		File file = new File("C:\\Users\\gruia\\Desktop\\Facultate\\Programare 3\\Proiect\\Proiect\\src\\fisierTest.txt");
		assertTrue(file.exists());
		File tempFile = new File("C:\\Users\\gruia\\Desktop\\Facultate\\Programare 3\\Proiect\\Proiect\\src\\temp.txt");
		try {
			FileOutputStream temp = new FileOutputStream(tempFile);
			String codProdusDeSters="157;";
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine())
			{
				String line = scanner.nextLine();
				if(line.contains(codProdusDeSters)) {
					continue;
				}
				else {
					temp.write(line.getBytes());
					temp.write("\n".getBytes());
				}
			}
			scanner.close();
			temp.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		file.delete();
		tempFile.renameTo(file);
		String content;
		try {
			content = Files.readString(file.toPath());
			assertFalse(content.contains("157;"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	@Test
	void testAdaugareCategorieInFisier() {
		listaCategorie.add(new Categorie("Jucarie","10"));
		listaCategorie.add(new Categorie("Borcane","22"));
		listaCategorie.add(new Categorie("Cutii","56"));
		File file = new File("C:\\Users\\gruia\\Desktop\\Facultate\\Programare 3\\Proiect\\Proiect\\src\\fisierTest.txt");
		assertTrue(file.exists());
		try {
			new FileWriter(file,false).close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			FileOutputStream fos = new FileOutputStream(file,true);
			for(Categorie c:listaCategorie)
			{
				String adauga = c.getCod()+";"+c.getNume()+"\n";
				fos.write(adauga.getBytes());
			}
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			String content = Files.readString(file.toPath());
			assertTrue(content.contains("10;Jucarie"));			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testEditatCategorieInFisier() {
		File file = new File("C:\\Users\\gruia\\Desktop\\Facultate\\Programare 3\\Proiect\\Proiect\\src\\fisierTest.txt");
		assertTrue(file.exists());
		File tempFile = new File("C:\\Users\\gruia\\Desktop\\Facultate\\Programare 3\\Proiect\\Proiect\\src\\temp.txt");
		try {
			FileOutputStream temp = new FileOutputStream(tempFile);
			String codCategorieDeEditat="22;";
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine())
			{
				String line = scanner.nextLine();
				if(line.contains(codCategorieDeEditat)) {
					String lineEditat = codCategorieDeEditat + "Masinute";
					temp.write(lineEditat.getBytes());
					temp.write("\n".getBytes());
				}
				else {
					temp.write(line.getBytes());
					temp.write("\n".getBytes());
				}
			}
			scanner.close();
			temp.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		file.delete();
		tempFile.renameTo(file);
		String content;
		try {
			content = Files.readString(file.toPath());
			assertTrue(content.contains("22;Masinute"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	@Test
	void testStergereCategorieDinFisier() {
		File file = new File("C:\\Users\\gruia\\Desktop\\Facultate\\Programare 3\\Proiect\\Proiect\\src\\fisierTest.txt");
		assertTrue(file.exists());
		File tempFile = new File("C:\\Users\\gruia\\Desktop\\Facultate\\Programare 3\\Proiect\\Proiect\\src\\temp.txt");
		try {
			FileOutputStream temp = new FileOutputStream(tempFile);
			String codCategorieDeSters="56;";
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine())
			{
				String line = scanner.nextLine();
				if(line.contains(codCategorieDeSters)) {
					continue;
				}
				else {
					temp.write(line.getBytes());
					temp.write("\n".getBytes());
				}
			}
			scanner.close();
			temp.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		file.delete();
		tempFile.renameTo(file);
		String content;
		try {
			content = Files.readString(file.toPath());
			assertFalse(content.contains("56;;"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
