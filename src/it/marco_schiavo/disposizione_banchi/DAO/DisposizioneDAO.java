package it.marco_schiavo.disposizione_banchi.DAO;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import it.marco_schiavo.disposizione_banchi.Model.Alunno;
import it.marco_schiavo.disposizione_banchi.Model.Aula;

public class DisposizioneDAO {
	//TODO:modificare i nomi delle relazioni da alunno in ?
	private static final String jdbcURL = "jdbc:mysql://localhost:3306/Alunno?user=root&password=LeonardoViola201";
	private static final String sqlQuery = "SELECT * FROM Alunno.alunno";
	private static final String update = "UPDATE Alunno.alunno set nome=?, cognome=? , sesso=? , comportamento=?,id_aula_FK=? where id=?";
	private static final String delete = "delete from Alunno.alunno where id=?";
	private static final String insert = "INSERT INTO Alunno.alunno (nome,cognome,sesso,comportamento,id_aula_FK) value (?,?,?,?,?)";
	private static ArrayList<Alunno> lista;
	private static final String createAula = "INSERT INTO Alunno.aula (id,classe,sezione) VALUE (?,?,?)";
	private static final String listaAula="SELECT * FROM Alunno.aula";
	public DisposizioneDAO() {
		
	}
	
	/**
	 * legge tutti gli alunni presenti nel DB
	 * @return: restituisce una ArrayList<Alunno>
	 */
	//readAll
	public static ArrayList<Alunno> getAll(){
		lista = new ArrayList<Alunno>();
		
		
		try {
			Connection conn = DriverManager.getConnection(jdbcURL);
			PreparedStatement std = conn.prepareStatement(sqlQuery);
			ResultSet result = std.executeQuery();
			while (result.next()) {
				Alunno p = new Alunno(result.getInt("id"),result.getString("nome"),
						result.getString("cognome"),result.getString("sesso"),result.getString("comportamento")
						,result.getInt("id_aula_FK"));
				lista.add(p);
			}
			conn.close();
			return lista;
			
		} catch (SQLException e) {

			e.printStackTrace();
			return null;
		}
		
		
	}
	
	/**
	 * aggiunge un nuovo alunno nel DB sono ammessi alunni con gli stessi parametri ma con ID differenti
	 * @param p: nuovo alunno
	 * @return: true se l'inserimento è andato a buon fine
	 */
	
	//create
	public static boolean createAlunno(Alunno p) {
		boolean ok = true;
		
		
			try {
				Connection conn = DriverManager.getConnection(jdbcURL);
				PreparedStatement std = conn.prepareStatement(insert);
				std.setString(1, p.getNome());
				std.setString(2, p.getCognome());
				std.setString(3, p.getSesso());
				std.setString(4, p.getComportamento());
				std.setInt(5, p.getIdAulaFK());
				
				std.execute();
				conn.close();
				
			} catch (SQLException e) {
			    e.printStackTrace();
				return ok=false;
			}
			
		
		return ok;
	}
	
	/**
	 * modifica i dati di un alunno all'interno del DB
	 * @param k: id alunno da modificare
	 * @param k: nuovo alunno con modifiche
	 * @return true: se la modifica è andata a buon fine
	 */
	//update
	public static boolean updateDB(int k,Alunno j) {
		boolean ok = false;
		
		ArrayList<Alunno> lista = DisposizioneDAO.getAll();
		for (Alunno temp : lista) {
			if (temp.getId()==k) {
				try {
					Connection conn = DriverManager.getConnection(jdbcURL);
					PreparedStatement std = conn.prepareStatement(update);
					std.setString(1, j.getNome());
					std.setString(2, j.getCognome());
					std.setString(3, j.getSesso());
					std.setString(4, j.getComportamento());
					std.setInt(5, j.getIdAulaFK());
					std.setInt(6, k);
					std.execute();
					ok=true;
					conn.close();
					return ok;
				} catch (SQLException e) {		
					e.printStackTrace();
					ok=false;
				}
				
			}else ok=false;
		}
		return ok;
	
	}
	
	/**
	 * rimuove un elemento dal DB
	 * @param k: ID dell'Alunno da rimuovere
	 * @return true se l'alunno è stato rimosso
	 */
	//delete
	public static boolean removeDB(int k) {
		boolean ok = false;
		ArrayList<Alunno> lista = DisposizioneDAO.getAll();
		for (Alunno j : lista) {
			if (k==j.getId()) {
				try {
					Connection conn = DriverManager.getConnection(jdbcURL);
					PreparedStatement std = conn.prepareStatement(delete);
					std.setInt(1, k);
					std.execute();
					ok=true;
					conn.close();
				}catch (SQLException e) {
					e.printStackTrace();
					ok=false;
				}
				
			}
		}
		return ok;
		
		
	}
	
	/**
	 * riassegna tutti gli id e l'auto increment ricomincia ordinato
	 * @return true se l'operazione è andata a buon fine
	 */
	
	public static boolean sortId() {
		boolean  ok = false;
		String cancella = "ALTER TABLE Alunno.alunno DROP id "; 
		String ricrea = " ALTER TABLE Alunno.alunno ADD id INT NOT NULL AUTO_INCREMENT FIRST ,ADD PRIMARY KEY (id)";
		
		try {
			Connection conn = DriverManager.getConnection(jdbcURL);
			Statement std = conn.createStatement();
			std.execute(cancella);
			std.execute(ricrea);
			conn.close();
			ok=true;
			
		}catch (SQLException e) {
			e.printStackTrace();
			ok=false;
		}
		
		
		return ok;	
	}
	
	
	/**
	 * crea una lista con tutte le aule presenti nel DB
	 * @return lista <Aula> oppure null se il DB è vuoto 
	 */
	
	public static ArrayList<Aula> aule(){
		ArrayList<Aula> lista = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(jdbcURL);
			PreparedStatement std = conn.prepareStatement(listaAula);
			ResultSet result = std.executeQuery();
			while (result.next()) {
				Aula aula = new Aula(result.getInt("id"),result.getInt("classe"),result.getString("sezione"));
				lista.add(aula);
			}
			conn.close();
			return lista;
		}catch (SQLException e) {
			e.printStackTrace();
			lista = null;
			
		}
		
		
		
		return lista;
	}
	
	/**
	 * aggiunge un'aula al DB
	 * @param id identificativo della classe
	 * @param classe classe che va da 1 a 3
	 * @param sezione carattere che va da A a Z
	 * @return
	 */

	public static boolean addAula(int id, int classe, String sezione) {
		boolean ok=false;
		
		if (!(classe>=1) || !(classe<=3)) {
			return ok;
		}
		
		ArrayList<Aula> aule = DisposizioneDAO.aule();
		for (Aula aula : aule) {
			if (aula.getAulaId()==id)
				return ok;
		}

		try {
		Connection conn = DriverManager.getConnection(jdbcURL);
		PreparedStatement std = conn.prepareStatement(createAula);
		std.setInt(1, id);
		std.setInt(2, classe);
		std.setString(3, sezione);
		std.execute();	
		conn.close();
		ok=true;
		}catch (SQLException e) {
			ok=false;
			e.printStackTrace();
		}
		
		
		return ok;
	}


	
	
	
	
	
	

}
