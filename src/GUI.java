package proiect;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

import javax.swing.UIManager;

import org.jfree.chart.ChartFactory;

import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import com.toedter.calendar.JDateChooser;



public class GUI extends JFrame {  
	File fisierProduse = new File("C:\\Users\\gruia\\Desktop\\Facultate\\Programare 3\\Proiect\\Proiect\\src\\fisierProduse.txt");
	File fisierCategorii = new File("C:\\Users\\gruia\\Desktop\\Facultate\\Programare 3\\Proiect\\Proiect\\src\\fisierCategorii.txt");
	List<Produs> listaProduse = getProduseDinBD();
	List<Categorie> listaCategorii = getCategoriiDinBD();
	Stoc stocProduse =new Stoc(listaProduse); 
	LocalDate localDate = LocalDate.now();
	
	GUI()	{  		
		final JPanel cards; 
		final JFrame frame = new JFrame("Inventory Management");
		
		//Main
		JPanel main = new JPanel();
		ImageIcon imagineMeniu = new ImageIcon("C:\\Users\\gruia\\Desktop\\Facultate\\Programare 3\\Proiect\\Proiect\\src\\imagineMeniu.jpg");
		JLabel imagine = new JLabel(imagineMeniu);
		JButton butonProduse = new JButton("Produse");
		JButton butonCategorii = new JButton("Categorii");
		JButton butonGestiune = new JButton("Gestiune");
		JButton butonRaport = new JButton("Raport");
		
		main.setLayout(null);
		butonProduse.setBounds(450,400,100,50);
		butonCategorii.setBounds(650,400,100,50);
		butonGestiune.setBounds(450,500,100,50);
		butonRaport.setBounds(650,500,100,50);
		imagine.setBounds(300,50,600,300);
		main.add(butonProduse);
		main.add(butonCategorii);
		main.add(butonGestiune);
		main.add(butonRaport);
		main.add(imagine);
		
		//Produse
		JPanel produse = new JPanel();
		//nume,pret,cod,cantitate,categorie
		JLabel numeProdus = new JLabel("Nume ");
		final TextField tfNumeProdus = new TextField(22);
		JLabel codProdus = new JLabel("Cod ");
		final TextField tfCodProdus = new TextField(22);
		JLabel cantitateProdus = new JLabel("Cantitate ");
		final TextField tfCantitateProdus = new TextField(22);
		JLabel pretProdus = new JLabel("Pret de vanzare ");
		final TextField tfPretProdus = new TextField(22);
		JLabel pretCumparare = new JLabel("Pret de cumparare ");
		final TextField tfPretCumparare = new TextField(22);
		JLabel categorieProdus = new JLabel("Categorie ");
		final TextField tfCategorieProdus = new TextField(22);
		JButton adaugaProdus = new JButton("Adauga");
		JButton editeazaProdus = new JButton("Editeaza");
		JButton stergeProdus = new JButton("Sterge");
		JButton meniuProdus = new JButton("Meniu");
		
		//populare tabel cu produse
		List<String> columnProduse = new ArrayList<String>();
		List<String[]> valoriProduse = new ArrayList<String[]>();
		columnProduse.add("Cod");
		columnProduse.add("Nume");
		columnProduse.add("Categorie");
		columnProduse.add("Pret de vanzare");
		columnProduse.add("Pret de cumparare");
		columnProduse.add("Cantitate");
		listaProduse = getProduseDinBD();
		for(Produs p: listaProduse) {
			valoriProduse.add(new String[] {p.getCod(),p.getNume(),p.getCategorie(),Double.toString(p.getPretDeVanzare()),Double.toString(p.getPretDeCumparare()),String.valueOf(p.getCantitate())});
		}
		final DefaultTableModel tableModelProduse = new DefaultTableModel(valoriProduse.toArray(new Object[][] {}),columnProduse.toArray());
		final JTable tabelProduse = new JTable(tableModelProduse);
		final JScrollPane sp =new JScrollPane(tabelProduse);
	
		//adaugare sectiune adaugare produs
		produse.setLayout(null);
		numeProdus.setBounds(100,50,100,50);
		tfNumeProdus.setBounds(220,60,200,30);
		produse.add(numeProdus);
		produse.add(tfNumeProdus);
		codProdus.setBounds(100,100,100,50);
		tfCodProdus.setBounds(220,110,200,30);
		produse.add(codProdus);
		produse.add(tfCodProdus);
		cantitateProdus.setBounds(100,150,100,50);
		tfCantitateProdus.setBounds(220,160,200,30);
		produse.add(cantitateProdus);
		produse.add(tfCantitateProdus);
		pretProdus.setBounds(100,200,100,50);
		tfPretProdus.setBounds(220,210,200,30);
		produse.add(pretProdus);
		produse.add(tfPretProdus);
		pretCumparare.setBounds(100,250,110,50);
		tfPretCumparare.setBounds(220,260,200,30);
		produse.add(pretCumparare);
		produse.add(tfPretCumparare);
		categorieProdus.setBounds(100,300,100,50);
		tfCategorieProdus.setBounds(220,310,200,30);
		produse.add(categorieProdus);
		produse.add(tfCategorieProdus);
		
		//adaugare butoane
		adaugaProdus.setBounds(100,380,100,20);
		editeazaProdus.setBounds(210,380,100,20);
		stergeProdus.setBounds(320,380,100,20);
		meniuProdus.setBounds(210,410,100,20);
		produse.add(adaugaProdus);
		produse.add(editeazaProdus);
		produse.add(stergeProdus);
		produse.add(meniuProdus);
		
		adaugaProdus.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(tfCodProdus.getText().equals("")||tfNumeProdus.getText().equals("")||tfCategorieProdus.getText().equals("")||tfPretCumparare.getText().equals("")||tfPretProdus.getText().equals("")||tfCantitateProdus.getText().equals("")) {
					JOptionPane.showMessageDialog(sp, "Exista campuri incomplete");
				}
				else {
				String produsIntrodus[] = {tfCodProdus.getText(),tfNumeProdus.getText(),tfCategorieProdus.getText(),tfPretProdus.getText(),tfPretCumparare.getText(),tfCantitateProdus.getText()};
				DefaultTableModel tbModel = (DefaultTableModel) tabelProduse.getModel();
				tbModel.addRow(produsIntrodus);
				Connection conn = null;
				try {
					String url = "jdbc:derby://localhost:1527/proiect";
					conn = DriverManager.getConnection(url,"USER","sql");
					Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
					ResultSet rs = stmt.executeQuery("select * from \"Produse\"");
					rs.moveToInsertRow();
					rs.updateInt("Cod", Integer.parseInt(tfCodProdus.getText()));
					rs.updateString("Nume", tfNumeProdus.getText());
					rs.updateInt("Cantitate", Integer.parseInt(tfCantitateProdus.getText()));
					rs.updateDouble("Pret de vanzare",Double.parseDouble(tfPretProdus.getText()));
					rs.updateDouble("Pret de cumparare", Double.parseDouble(tfPretCumparare.getText()));
					rs.updateString("Categorie", tfCategorieProdus.getText());
					rs.insertRow();
					rs.close();
					conn.commit();
					stmt.close();
					conn.close();
				}
				catch (SQLException e1) {
					e1.printStackTrace();
				}
				///stocProduse.getStoc().add(new Produs(tfCodProdus.getText(),tfNumeProdus.getText(),tfCategorieProdus.getText(),Double.parseDouble(tfPretCumparare.getText()),Double.parseDouble(tfPretProdus.getText()),Integer.parseInt(tfCantitateProdus.getText())));
				tfCodProdus.setText("");
				tfNumeProdus.setText("");
				tfCategorieProdus.setText("");
				tfPretProdus.setText("");
				tfPretCumparare.setText("");
				tfCantitateProdus.setText("");	
				}
				listaProduse = getProduseDinBD();
				stocProduse.stoc=listaProduse;
			}
		});
		
		tabelProduse.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				int row = tabelProduse.rowAtPoint(evt.getPoint());
				DefaultTableModel tbModel = (DefaultTableModel) tabelProduse.getModel();
				
				String codProdusDeEditat = tbModel.getValueAt(row, 0).toString();
				String numeProdusDeEditat = tbModel.getValueAt(row, 1).toString();
				String categorieProdusDeEditat = tbModel.getValueAt(row, 2).toString();
				String pretProdusDeEditat = tbModel.getValueAt(row, 3).toString();
				String pretCumparareDeEditat = tbModel.getValueAt(row, 4).toString();
				String cantitateProdusDeEditat = tbModel.getValueAt(row, 5).toString();
				
				tfCodProdus.setText(codProdusDeEditat);
				tfNumeProdus.setText(numeProdusDeEditat);
				tfCategorieProdus.setText(categorieProdusDeEditat);
				tfPretProdus.setText(pretProdusDeEditat);
				tfPretCumparare.setText(pretCumparareDeEditat);
				tfCantitateProdus.setText(cantitateProdusDeEditat);	
			}
		});
		
		editeazaProdus.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				DefaultTableModel tbModel = (DefaultTableModel) tabelProduse.getModel();
				
				String codProdusDeEditat = tfCodProdus.getText();	
				
				if(tfCodProdus.getText().equals("")||tfNumeProdus.getText().equals("")||tfCategorieProdus.getText().equals("")||tfPretCumparare.getText().equals("")||tfPretProdus.getText().equals("")||tfCantitateProdus.getText().equals("")) {
					JOptionPane.showMessageDialog(sp, "Exista campuri incomplete");
				}
				else {
				String produsEditat[] = {tfCodProdus.getText(),tfNumeProdus.getText(),tfCategorieProdus.getText(),tfPretProdus.getText(),tfPretCumparare.getText(),tfCantitateProdus.getText()};
				
				tbModel.setValueAt(produsEditat[0], tabelProduse.getSelectedRow(), 0 );
				tbModel.setValueAt(produsEditat[1],tabelProduse.getSelectedRow(), 1);
				tbModel.setValueAt(produsEditat[2],tabelProduse.getSelectedRow(), 2);
				tbModel.setValueAt(produsEditat[3],tabelProduse.getSelectedRow(), 3);
				tbModel.setValueAt(produsEditat[4],tabelProduse.getSelectedRow(), 4);
				tbModel.setValueAt(produsEditat[5],tabelProduse.getSelectedRow(), 5);
				
				Connection conn = null;
				try {
					String url = "jdbc:derby://localhost:1527/proiect";
					conn = DriverManager.getConnection(url,"USER","sql");
					Statement stmt = conn.createStatement();
					stmt.executeUpdate("update \"Produse\" SET \"Nume\"='" + tfNumeProdus.getText() + "', \"Cantitate\"=" + Integer.parseInt(tfCantitateProdus.getText()) + ", \"Pret de vanzare\"=" + Double.parseDouble(tfPretProdus.getText()) + ", \"Pret de cumparare\"=" + Double.parseDouble(tfPretCumparare.getText()) + ", \"Categorie\"='" + tfCategorieProdus.getText() + "' WHERE \"Cod\"="+ Integer.parseInt(codProdusDeEditat));
					conn.commit();
					stmt.close();
					conn.close();
				}
				catch (SQLException e1) {
					e1.printStackTrace();
				}
				}
			}
		});
		
		stergeProdus.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				DefaultTableModel tbModel = (DefaultTableModel) tabelProduse.getModel();
				String codProdusDeSters = tbModel.getValueAt(tabelProduse.getSelectedRow(), 0).toString();
				tbModel.removeRow(tabelProduse.getSelectedRow());
				Connection conn = null;
				try {
					String url = "jdbc:derby://localhost:1527/proiect";
					conn = DriverManager.getConnection(url,"USER","sql");
					Statement stmt = conn.createStatement();
					stmt.executeUpdate("delete from \"Produse\" WHERE \"Cod\"="+ Integer.parseInt(codProdusDeSters));
					conn.commit();
					stmt.close();
					conn.close();
				}
				catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				tfCodProdus.setText("");
				tfNumeProdus.setText("");
				tfCategorieProdus.setText("");
				tfPretProdus.setText("");
				tfPretCumparare.setText("");
				tfCantitateProdus.setText("");	
			}
			
		});
		
		meniuProdus.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				tfNumeProdus.setText("");
				tfCodProdus.setText("");
				tfCantitateProdus.setText("");
				tfPretProdus.setText("");
				tfPretCumparare.setText("");
				tfCategorieProdus.setText("");
				
			}
			
		});		
		
		//adaugare tabel
		sp.setBounds(480,50,700,500);
		produse.add(sp);
		
		//Categorii
		JPanel categorii = new JPanel();
		JButton adaugaCategorie = new JButton("Adauga");
		JButton editeazaCategorie = new JButton("Editeaza");
		JButton stergeCategorie = new JButton("Sterge");
		JButton meniuCategorii = new JButton("Meniu");
		JLabel codCategorie = new JLabel("Cod ");
		final TextField tfCodCategorie = new TextField(22);
		JLabel numeCategorie = new JLabel("Nume ");
		final TextField tfNumeCategorie = new TextField(22);
		List<String> columnCategorii = new ArrayList<String>();
		List<String[]> valoriCategorii = new ArrayList<String[]>();
		columnCategorii.add("Cod");
		columnCategorii.add("Nume");
		for(Categorie c: listaCategorii) {
			valoriCategorii.add(new String[] {c.getCod(),c.getNume()});
		}
		TableModel tableModelCategorii = new DefaultTableModel(valoriCategorii.toArray(new Object[][] {}),columnCategorii.toArray());
		final JTable tabelCategorie = new JTable(tableModelCategorii);
		JScrollPane spCategorie =new JScrollPane(tabelCategorie);
		
		//adaugare sectiune adaugare categorie
		categorii.setLayout(null);
		numeCategorie.setBounds(100,200,100,50);
		tfNumeCategorie.setBounds(200,210,200,30);
		codCategorie.setBounds(100,150,100,50);
		tfCodCategorie.setBounds(200,160,200,30);
		categorii.add(numeCategorie);
		categorii.add(tfNumeCategorie);
		categorii.add(codCategorie);
		categorii.add(tfCodCategorie);
		
		//adaugare butoane
		adaugaCategorie.setBounds(100,320,95,20);
		editeazaCategorie.setBounds(205,320,95,20);
		stergeCategorie.setBounds(305,320,95,20);
		meniuCategorii.setBounds(205,350,95,20);
		categorii.add(adaugaCategorie);
		categorii.add(editeazaCategorie);
		categorii.add(stergeCategorie);
		categorii.add(meniuCategorii);
		
		adaugaCategorie.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(tfCodCategorie.getText().equals("")||tfNumeCategorie.getText().equals("")) {
					JOptionPane.showMessageDialog(sp, "Exista campuri incomplete");
				}
				else {
				String categorieIntrodusa[] = {tfCodCategorie.getText(),tfNumeCategorie.getText()};
				DefaultTableModel tbModel = (DefaultTableModel) tabelCategorie.getModel();
				tbModel.addRow(categorieIntrodusa);
				Connection conn = null;
				try {
					String url = "jdbc:derby://localhost:1527/proiect";
					conn = DriverManager.getConnection(url,"USER","sql");
					Statement stmt = conn.createStatement();
					stmt.executeUpdate("insert into \"Categorie\" VALUES(" + Integer.parseInt(tfCodCategorie.getText()) + ", '" + tfNumeCategorie.getText() + "')");
					conn.commit();
					stmt.close();
					conn.close();
				}
				catch (SQLException e1) {
					e1.printStackTrace();
				}
				tfCodCategorie.setText("");
				tfNumeCategorie.setText("");
				}
			}
		});
		
		tabelCategorie.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				int row = tabelCategorie.rowAtPoint(evt.getPoint());
				DefaultTableModel tbModel = (DefaultTableModel) tabelCategorie.getModel();
				
				String codCategorieDeEditat = tbModel.getValueAt(row, 0).toString();
				String numeCategorieDeEditat = tbModel.getValueAt(row, 1).toString();
				
				tfCodCategorie.setText(codCategorieDeEditat);
				tfNumeCategorie.setText(numeCategorieDeEditat);
			}
		});
		
		editeazaCategorie.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				DefaultTableModel tbModel = (DefaultTableModel) tabelCategorie.getModel();
				
				String codCategorieDeEditat = tfCodCategorie.getText();
				
				if(tfCodCategorie.getText().equals("")||tfNumeCategorie.getText().equals("")) {
					JOptionPane.showMessageDialog(sp, "Exista campuri incomplete");
				}
				else {
				String categorieEditata[] = {tfCodCategorie.getText(),tfNumeCategorie.getText()};
				
				tbModel.setValueAt(categorieEditata[0], tabelCategorie.getSelectedRow(), 0);
				tbModel.setValueAt(categorieEditata[1], tabelCategorie.getSelectedRow(),1);
				
				Connection conn = null;
				try {
					String url = "jdbc:derby://localhost:1527/proiect";
					conn = DriverManager.getConnection(url,"USER","sql");
					Statement stmt = conn.createStatement();
					stmt.executeUpdate("update \"Categorie\" SET \"Nume\"='" + tfNumeCategorie.getText() + "'" + " WHERE \"Cod\"="+ Integer.parseInt(codCategorieDeEditat));
					conn.commit();
					stmt.close();
					conn.close();
				}
				catch (SQLException e1) {
					e1.printStackTrace();
				}
				}
			}
			
		});
		
		stergeCategorie.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				DefaultTableModel tbModel = (DefaultTableModel) tabelCategorie.getModel();
				String codCategorieDeSters = tbModel.getValueAt(tabelCategorie.getSelectedRow(), 0).toString();
				tbModel.removeRow(tabelCategorie.getSelectedRow());
				Connection conn = null;
				try {
					String url = "jdbc:derby://localhost:1527/proiect";
					conn = DriverManager.getConnection(url,"USER","sql");
					Statement stmt = conn.createStatement();
					stmt.executeUpdate("delete from \"Categorie\" WHERE \"Cod\"="+ Integer.parseInt(codCategorieDeSters));
					conn.commit();
					stmt.close();
					conn.close();
				}
				catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
		});
		
		meniuCategorii.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				tfNumeCategorie.setText("");
				tfCodCategorie.setText("");
			}
			
		});
		
		//adaugare tabel
		spCategorie.setBounds(500,50,670,500);
		categorii.add(spCategorie);
		
		//Gestiune
		JPanel gestiune = new JPanel();
		JTextPane valoareStoc = new JTextPane();
		valoareStoc.setText("Valoare Stoc");
		JTextPane stocMic = new JTextPane();
		stocMic.setText("Produse cu stoc scazut");
		JTextPane stocEpuizat = new JTextPane();
		stocEpuizat.setText("Produse cu stoc epuizat");
		JButton meniuStoc = new JButton("Meniu");
		JButton adaugareStoc = new JButton("Adaugare Stoc");
		JButton scadeStoc = new JButton("Scade din stoc");
		
		//populare tabel gestiune
		final List<String> columnGestiune = new ArrayList<String>();
		final List<String[]> valoriGestiune = new ArrayList<String[]>();
		columnGestiune.add("Cod");
		columnGestiune.add("Nume");
		columnGestiune.add("Categorie");
		columnGestiune.add("Pret de cumparare");
		columnGestiune.add("Pret de vanzare");
		columnGestiune.add("Cantitate");
		listaProduse = getProduseDinBD();
		stocProduse.stoc=listaProduse;
		for(Produs p : stocProduse.stoc) {
			valoriGestiune.add(new String[] {p.getCod(),p.getNume(),p.getCategorie(),Double.toString(p.getPretDeCumparare()),Double.toString(p.getPretDeVanzare()),String.valueOf(p.getCantitate())});
		}
		final DefaultTableModel tableModelGestiune = new DefaultTableModel(valoriGestiune.toArray(new Object[][] {}),columnGestiune.toArray());
		final JTable tabelStoc = new JTable(tableModelGestiune);
		final JScrollPane spStoc = new JScrollPane(tabelStoc);
				
		adaugareStoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
							
				if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					ArrayList<String> prod = new ArrayList<String>();
					File selectedFile = chooser.getSelectedFile();Connection conn = null;
					try {
						String url = "jdbc:derby://localhost:1527/proiect";
						conn = DriverManager.getConnection(url,"USER","sql");
						Statement stmt = conn.createStatement();
						Scanner scanner;
						try {
							scanner = new Scanner(selectedFile);
							while(scanner.hasNextLine()) {
								String line = scanner.nextLine();
								String[] l = line.split(",");
								ResultSet rs = stmt.executeQuery("select \"Cod\",\"Cantitate\" FROM \"Produse\" WHERE \"Cod\"= "+Integer.parseInt(l[0]));
								if(rs.next()) {
									int cant = rs.getInt("Cantitate");
									cant = cant + Integer.parseInt(l[1]);
									stmt.executeUpdate("update \"Produse\" SET \"Cantitate\"=" + cant + " WHERE \"Cod\"="+ Integer.parseInt(l[0]));
									
								}
								else {
									prod.add(l[0]);
								}
							}
							scanner.close();
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						}		
						conn.commit();
						stmt.close();
						conn.close();
					}
					catch (SQLException e1) {
						e1.printStackTrace();
					}				
					if(!prod.isEmpty()) { 
						JOptionPane.showMessageDialog(frame, "Produsele cu codul: " + prod + " nu au fost introduse corect");
					}
				}
				listaProduse = getProduseDinBD();
				stocProduse.stoc=listaProduse;
				tableModelGestiune.setRowCount(0);
				for(Produs p : stocProduse.stoc) {
					tableModelGestiune.addRow(new String[] {p.getCod(),p.getNume(),p.getCategorie(),Double.toString(p.getPretDeCumparare()),Double.toString(p.getPretDeVanzare()),String.valueOf(p.getCantitate())});
				}				
			}
		});
		
		scadeStoc.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
							
				if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					ArrayList<String> prodInexistente = new ArrayList<String>();
					ArrayList<String> prodFaraStoc = new ArrayList<String>();
					File selectedFile = chooser.getSelectedFile();Connection conn = null;
					try {
						String url = "jdbc:derby://localhost:1527/proiect";
						conn = DriverManager.getConnection(url,"USER","sql");
						Statement stmt = conn.createStatement();
						Scanner scanner;
						try {
							scanner = new Scanner(selectedFile);
							while(scanner.hasNextLine()) {
								String line = scanner.nextLine();
								String[] l = line.split(",");
								ResultSet rs = stmt.executeQuery("select \"Cod\",\"Cantitate\",\"Pret de cumparare\",\"Pret de vanzare\" FROM \"Produse\" WHERE \"Cod\"= "+Integer.parseInt(l[0]));
								if(rs.next()) {
									int cant = rs.getInt("Cantitate");
									double pretv = rs.getDouble("Pret de vanzare");
									double pretc = rs.getDouble("Pret de cumparare");
									if(cant<=0) {
										prodFaraStoc.add(l[0]);
									}
									else {
										cant = cant - Integer.parseInt(l[1]);
										stmt.executeUpdate("update \"Produse\" SET \"Cantitate\"=" + cant + " WHERE \"Cod\"="+ Integer.parseInt(l[0]));
										stmt.executeUpdate("insert into \"Vanzari\" VALUES(" + Integer.parseInt(l[0]) + ", DATE('" + localDate + "'), " + Integer.parseInt(l[1]) + ", " + pretv + ", " + pretc +")");
									}
								}
								else {
									prodInexistente.add(l[0]);
								}
							}
							scanner.close();
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						}		
						conn.commit();
						stmt.close();
						conn.close();
					}
					catch (SQLException e1) {
						e1.printStackTrace();
					}				
					if(!prodInexistente.isEmpty()) { 
						JOptionPane.showMessageDialog(frame, "Produsele cu codul: " + prodInexistente + " nu au fost introduse corect");
					}
					if(!prodFaraStoc.isEmpty()) {
						JOptionPane.showMessageDialog(frame, "Produsele cu codul: " + prodFaraStoc + " nu sunt in stoc");
					}
				}
				listaProduse = getProduseDinBD();
				stocProduse.stoc=listaProduse;
				tableModelGestiune.setRowCount(0);
				for(Produs p : stocProduse.stoc) {
					tableModelGestiune.addRow(new String[] {p.getCod(),p.getNume(),p.getCategorie(),Double.toString(p.getPretDeCumparare()),Double.toString(p.getPretDeVanzare()),String.valueOf(p.getCantitate())});
				}				
			}
		});
		
		//adaugare sectiune val stoc/prod
		gestiune.setLayout(null);
		valoareStoc.setBounds(100,50,200,70);
		stocMic.setBounds(450,50,200,70);
		stocEpuizat.setBounds(800,50,200,70);
		
		valoareStoc.setFont(new Font("Serif", Font.BOLD ,16));
		StyledDocument tpValoareStoc = valoareStoc.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		tpValoareStoc.setParagraphAttributes(0, tpValoareStoc.getLength(), center, false);
		String textValoareStoc = "\n" + stocProduse.getValoare() ;
		try {
			tpValoareStoc.insertString(tpValoareStoc.getLength(), textValoareStoc, null);
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
		
		stocMic.setFont(new Font("Serif", Font.BOLD ,16));
		StyledDocument tpStocMic = stocMic.getStyledDocument();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		tpStocMic.setParagraphAttributes(0, tpStocMic.getLength(), center, false);
		String textStocMic = "\n" + stocProduse.getProduseStocScazut() ;
		try {
			tpStocMic.insertString(tpStocMic.getLength(), textStocMic, null);
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
		
		stocEpuizat.setFont(new Font("Serif", Font.BOLD ,16));
		StyledDocument tpStocEpuizat = stocEpuizat.getStyledDocument();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		tpStocEpuizat.setParagraphAttributes(0, tpStocEpuizat.getLength(), center, false);
		String textStocEpuizat = "\n" + stocProduse.getProduseStocEpuizat() ;
		try {
			tpStocEpuizat.insertString(tpStocEpuizat.getLength(), textStocEpuizat, null);
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
		
		gestiune.add(valoareStoc);
		gestiune.add(stocMic);
		gestiune.add(stocEpuizat);
		
		//adaugare butoane
		meniuStoc.setBounds(100,170,90,20);
		adaugareStoc.setBounds(200,170,150,20);
		scadeStoc.setBounds(360,170,150,20);
		gestiune.add(meniuStoc);
		gestiune.add(adaugareStoc);
		gestiune.add(scadeStoc);
		
		//adaugare tabel
		spStoc.setBounds(100,200,1000,300);
		gestiune.add(spStoc);
		
		//Raport
		JPanel raport = new JPanel();
		JButton meniuRaport = new JButton("Meniu");
		final JDateChooser chooser1 = new JDateChooser();
		final JDateChooser chooser2 = new JDateChooser();
		JLabel interval = new JLabel("Interval");
		JButton generareRaport = new JButton("Generare Raport");
		chooser1.setLocale(Locale.ITALY);
		
		raport.setLayout(null);
		
		raport.add(meniuRaport);
		raport.add(chooser1);
		raport.add(chooser2);
		raport.add(interval);
		raport.add(generareRaport);		
		
		interval.setBounds(60,50,50,20);
		meniuRaport.setBounds(1000,50,95,20);
		chooser1.setBounds(120, 51, 95, 20);
		chooser2.setBounds(230,51,95,20);
		generareRaport.setBounds(340,50,148,20);
		
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		final DefaultPieDataset dataset1 = new DefaultPieDataset();
		final DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
		final String P1 = "Profit";
        final String P2 = "Vanzare"; 
		
		generareRaport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.util.Date data1 = chooser1.getDate();
				java.util.Date data2 = chooser2.getDate(); 	
				int ok=1;
				if(data1==null) {
					if(data2==null) {
						JOptionPane.showMessageDialog(frame, "Datele nu au fost selectate corect!");
						ok=0;
					}
					else {
						data1=data2;
					}
				}
				else {
					if(data2==null) {
						data2=data1;
					}
					else if(data1.after(data2)) {
						JOptionPane.showMessageDialog(frame, "Datele nu au fost selectate corect!");
						ok=0;
					}
				}
				if(ok!=0) {
					String d1 = sdf.format(data1);
					String d2 = sdf.format(data2);
					Connection conn = null;
				    try {
				      String url = "jdbc:derby://localhost:1527/proiect";
				      conn = DriverManager.getConnection(url,"USER","sql");
				      Statement stmt = conn.createStatement();
				      ResultSet rs = stmt.executeQuery("SELECT \"Cod produs\",SUM(\"Cantitate\") AS \"Suma\" \r\n"
				      		+ "FROM \"Vanzari\" \r\n"
				      		+ "WHERE (\"Data\" BETWEEN DATE('"+ d1 +"') AND DATE('"+ d2 +"'))\r\n"
				      		+ "GROUP BY \"Cod produs\" \r\n"
				      		+ "ORDER BY SUM(\"Cantitate\") DESC\r\n"
				      		+ "");
				      int col=0; //nr prod care se afiseaza in cele mai vandute produse
				      while(rs.next()) {
				    	  int cod=rs.getInt("Cod produs");
				    	  int cantitate = rs.getInt("Suma"); 
				    	  dataset1.setValue(Integer.toString(cod),cantitate);
				    	  col+=1;
				    	  if(col>=5) {break;}
				      }
				      rs = stmt.executeQuery("SELECT \"Data\",SUM(\"Pret de vanzare\") AS \"Vanzare\" ,SUM(\"Pret de vanzare\") - SUM(\"Pret de cumparare\") AS \"Profit\"\r\n"
				      		+ "FROM \"Vanzari\" \r\n"
				      		+ "WHERE (\"Data\" BETWEEN DATE('"+ d1 +"') AND DATE('"+ d2 +"'))\r\n"
				      		+ "GROUP BY \"Data\" \r\n"
				      		+ "");
				      while(rs.next()) {
				    	  double vanzare = rs.getDouble("Vanzare");
				    	  double profit = rs.getDouble("Profit");
				    	  Date dateRaport = rs.getDate("Data");
				    	  dataset2.addValue(profit,P1,dateRaport);
				    	  dataset2.addValue(vanzare,P2,dateRaport);
				      }
				      rs.close();
				      stmt.close();
				      conn.close();
				    } catch (SQLException e1) {
				        throw new Error("Problem", e1);
				    } finally {
				      try {
				        if (conn != null) {
				            conn.close();
				        }
				      } catch (SQLException ex) {
				          System.out.println(ex.getMessage());
				      }
				    }
				}
			}
			
		});

		JFreeChart chart = ChartFactory.createPieChart("Cele mai vandute produse", dataset1, true, true, true);
		PiePlot p = (PiePlot) chart.getPlot();
		p.setDirection(Rotation.CLOCKWISE);
		ChartPanel chartPanel = new ChartPanel(chart);
		raport.add(chartPanel);
		chartPanel.setBounds(60,100,400,400);    
             
        JFreeChart barChart = ChartFactory.createBarChart("", "", "Suma",dataset2, PlotOrientation.VERTICAL, true, true, false); 
        
        ChartPanel chart1 = new ChartPanel(barChart);
        raport.add(chart1);
        chart1.setBounds(500,100,650,400);
        
        
        
		
		//toate paginile	
		cards = new JPanel(new CardLayout());
		cards.add(main,"Menu");
		cards.add(produse,"Produse");
		cards.add(categorii,"Categorii");
		cards.add(gestiune,"Gestiune");
		cards.add(raport,"Raport");
		
		class ControlActionListener implements ActionListener{

			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)(cards.getLayout());
				String cmd = e.getActionCommand();
				if(cmd.equals("MAIN")) {
					cl.first(cards);
				}
				else if(cmd.equals("PRODUSE")) {
					tableModelProduse.setRowCount(0);
					listaProduse = getProduseDinBD();
					for(Produs p: listaProduse) {
						tableModelProduse.addRow(new String[] {p.getCod(),p.getNume(),p.getCategorie(),Double.toString(p.getPretDeVanzare()),Double.toString(p.getPretDeCumparare()),String.valueOf(p.getCantitate())});
					}
					cl.next(cards);
				}
				else if(cmd.equals("CATEGORII")) {
					cl.first(cards);
					cl.next(cards);
					cl.next(cards);
				}
				else if(cmd.equals("GESTIUNE")) {
					tableModelGestiune.setRowCount(0);
					listaProduse = getProduseDinBD();
					stocProduse.stoc=listaProduse;
					for(Produs p : stocProduse.stoc) {
						tableModelGestiune.addRow(new String[] {p.getCod(),p.getNume(),p.getCategorie(),Double.toString(p.getPretDeCumparare()),Double.toString(p.getPretDeVanzare()),String.valueOf(p.getCantitate())});
					}					
					cl.last(cards);
					cl.previous(cards);
				}
				else if(cmd.equals("RAPORT")) {
					cl.last(cards);
				}
			}
			
		}
		ControlActionListener cal = new ControlActionListener();
		//meniu principal
		butonProduse.setActionCommand("PRODUSE");
		butonProduse.addActionListener(cal);
		butonCategorii.setActionCommand("CATEGORII");
		butonCategorii.addActionListener(cal);
		butonGestiune.setActionCommand("GESTIUNE");
		butonGestiune.addActionListener(cal);
		butonRaport.setActionCommand("RAPORT");
		butonRaport.addActionListener(cal);
		
		//meniu produse
		meniuProdus.setActionCommand("MAIN");
		meniuProdus.addActionListener(cal);
		
		//meniu categorii
		meniuCategorii.setActionCommand("MAIN");
		meniuCategorii.addActionListener(cal);
		
		//meniu gestiune
		meniuStoc.setActionCommand("MAIN");
		meniuStoc.addActionListener(cal);
		
		//meniu raport
		meniuRaport.setActionCommand("MAIN");
		meniuRaport.addActionListener(cal);
		
		Container pane = frame.getContentPane();
		pane.add(cards, BorderLayout.CENTER);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1200,600);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
	}



	private List<Produs> getProduseDinBD() {
		List<Produs> produse = new ArrayList<Produs>();
		Connection conn = null;
	    try {
	      String url = "jdbc:derby://localhost:1527/proiect";
	      conn = DriverManager.getConnection(url,"USER","sql");
	      Statement stmt = conn.createStatement();
	      ResultSet rs = stmt.executeQuery("select * from \"Produse\"");
	      while(rs.next()) {
	    	  int cod=rs.getInt("Cod");
	    	  String nume = rs.getString("Nume");
	    	  int cantitate = rs.getInt("Cantitate");
	    	  double pretv = rs.getDouble("Pret de vanzare");
	    	  double pretc = rs.getDouble("Pret de cumparare");
	    	  String categorie = rs.getString("Categorie");
	    	  String codd = Integer.toString(cod); 
	    	  Produs p = new Produs(codd,nume,categorie,pretc,pretv,cantitate);
	    	  produse.add(p);
	      }
	      rs.close();
	      stmt.close();
	      conn.close();
	    } catch (SQLException e) {
	        throw new Error("Problem", e);
	    } finally {
	      try {
	        if (conn != null) {
	            conn.close();
	        }
	      } catch (SQLException ex) {
	          System.out.println(ex.getMessage());
	      }
	    }
		return produse;
	}
	
	private List<Categorie> getCategoriiDinBD() {
		List<Categorie> categorii = new ArrayList<Categorie>();
		Connection conn = null;
		try {
			String url = "jdbc:derby://localhost:1527/proiect";
			conn = DriverManager.getConnection(url,"USER","sql");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from \"Categorie\"");
			while(rs.next()) {
				int cod = rs.getInt("Cod");
				String nume = rs.getString("Nume");
				String codd = Integer.toString(cod);
				Categorie c = new Categorie(nume,codd);
				categorii.add(c);
			}
		rs.close();
		stmt.close();
		conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categorii;
	}
	
	
}


