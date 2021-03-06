package it.marco_schiavo.disposizione_banchi.DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import it.marco_schiavo.disposizione_banchi.Model.Alunno;
import it.marco_schiavo.disposizione_banchi.Model.Aula;

public class DisposizioneDAO {
	
	private static final String sqlQuery = "SELECT * FROM Alunno.alunno";
	private static final String readAlunni = "select * FROM Alunno.alunno, Alunno.aula WHERE  alunno.id_aula_FK=aula.id ";
	private static final String update = "UPDATE Alunno.alunno set nome=?, cognome=? , sesso=? , comportamento=?,id_aula_FK=? where id=?";
	private static final String delete = "delete from Alunno.alunno where id=?";
	private static final String insert = "INSERT INTO Alunno.alunno (nome,cognome,sesso,comportamento,id_aula_FK) value (?,?,?,?,?)";
	private static ArrayList<Alunno> lista;
	private static final String createAula = "INSERT INTO Alunno.aula (id,classe,sezione) VALUE (?,?,?)";
	private static final String listaAula="SELECT * FROM Alunno.aula";
	private static final String deleteAula = "DELETE FROM Alunno.aula WHERE (id = ?)";
	private static final String updateAula="UPDATE Alunno.aula set classe=? ,sezione=? WHERE id=?";
	private static final String alunniClasse = "SELECT * " + 
			"FROM Alunno.alunno, Alunno.aula " + 
			"WHERE  alunno.id_aula_FK=aula.id " + 
			"AND aula.id = ?";
	private static final String classe_sezione="SELECT * from aula where id=?";
	private static final String numero_alunni = "select Alunno.aula.id, classe,sezione,count(Alunno.id)as alunni from Alunno.aula left join Alunno.alunno on Alunno.alunno.id_aula_FK=Alunno.aula.id group by Alunno.aula.id";
	private static final String classeid = "select id from Alunno.aula where classe=? and sezione=?";
	private static final String ricreaAlunno = "select Alunno.alunno.id,Alunno.alunno.nome,Alunno.alunno.cognome,Alunno.alunno.sesso,Alunno.alunno.comportamento ,Alunno.alunno.id_aula_FK,Alunno.aula.classe,Alunno.aula.sezione  from Alunno.alunno,Alunno.aula where Alunno.alunno.id_aula_FK = Alunno.aula.id and Alunno.aula.sezione = ? and Alunno.aula.classe = ? and Alunno.alunno.nome =?  and Alunno.alunno.cognome=?";

	
	public DisposizioneDAO() {
		
	}
	
	public static Alunno ricrea_alunno(int classe, String sezione,String nome,String cognome) {
		Alunno alunno = new Alunno();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement std = conn.prepareStatement(ricreaAlunno);
			std.setInt(2, classe);
			std.setString(1, sezione);
			std.setString(3,nome);
			std.setString(4, cognome);
			
			ResultSet res = std.executeQuery();
			while (res.next()) {
				alunno.setId(res.getInt("id"));
				alunno.setNome(nome);
				alunno.setCognome(cognome);
				alunno.setClasse(classe);
				alunno.setSezione(sezione);
				alunno.setComportamento(res.getString("comportamento"));
				alunno.setIdAulaFK(res.getInt("id_aula_FK"));
				alunno.setSesso(res.getString("sesso"));
			}
			conn.close();
			return alunno;
		
		}catch (SQLException e) {
			e.printStackTrace();
			return alunno;
		}
		
		
		
	}
	
	public static ArrayList<Aula> getnumero_alunni() {
		ArrayList<Aula> aule = new ArrayList<>();
		try {
		Connection conn = ConnectDB.getConnection();
		Statement std = conn.createStatement();
		
		ResultSet res = std.executeQuery(numero_alunni);
		while (res.next()) {
			Aula aula = new Aula(res.getInt("id"),res.getInt("classe"),res.getString("sezione"),
					res.getInt("alunni"));
			aule.add(aula);	
		}
		conn.close();
		return aule;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	public static String classe_sezione(int id) {
		String classezione=null;
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement std = conn.prepareStatement(classe_sezione);
			std.setInt(1, id);
			ResultSet res = std.executeQuery();
			while (res.next()) {
				Aula a = new Aula(res.getInt("id"),res.getInt("classe"),res.getString("sezione"));
				classezione = a.getClasse()+a.getSezione();
			}
			
			conn.close();
			return classezione;
		} catch (SQLException e) {
			e.printStackTrace();
			return classezione = "0";
		}
	}
	
	
	/**
	 * legge tutti gli alunni presenti nel DB
	 * @return: restituisce una ArrayList<Alunno>
	 */
	//readAll
	public static ArrayList<Alunno> getAll(){
		lista = new ArrayList<Alunno>();
		
		
		try {
			Connection conn = ConnectDB.getConnection();
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
	
	public static ArrayList<Alunno> readAll(){
		lista = new ArrayList<Alunno>();
		
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement std = conn.prepareStatement(readAlunni);
			ResultSet result = std.executeQuery();
			while (result.next()) {
				Alunno p = new Alunno(result.getInt("id"),result.getString("nome"),
						result.getString("cognome"),result.getString("sesso"),result.getString("comportamento")
						,result.getInt("classe"),result.getString("sezione"));
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
				Connection conn = ConnectDB.getConnection();
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
					Connection conn = ConnectDB.getConnection();
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
					Connection conn = ConnectDB.getConnection();
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
			Connection conn = ConnectDB.getConnection();
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
			Connection conn = ConnectDB.getConnection();
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
		
		if (!(classe>=1 && classe<=3)) {
			return ok;
		}
		
		ArrayList<Aula> aule = DisposizioneDAO.aule();
		for (Aula aula : aule) {
			if (aula.getAulaId()==id)
				return ok;
		}

		try {
		Connection conn = ConnectDB.getConnection();
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
	
	/**
	 * cancella un'aula
	 * @param id identificatore aula
	 * @return true ->se l'operazione è riuscita
	 */
	
	public static boolean deleteAula(int id) {
		boolean ok=false;
		int flag=0;
		
		
		ArrayList<Aula> aule = DisposizioneDAO.aule();
		for (Aula aula:aule) {
			if(aula.getAulaId()==id) {
				flag++;
			}
		}
		
		//TODO: gestire l'errore dell'ID non trovato
		if (flag==0)
			ok=false;
		
		if (flag!=0) {
			try {
				Connection conn = ConnectDB.getConnection();
				PreparedStatement std = conn.prepareStatement(deleteAula);
				std.setInt(1, id);
				std.execute();
				conn.close();
				ok=true;
				
			}catch (SQLException e) {
				e.printStackTrace();
				ok=false;
			}
			
		}
		
		return ok;
	}
	
	/**
	 * aggiorna aula
	 * @param id -> aula da aggiornare
	 * @param classe -> aggiorna classe compresa tra 1 e 3
	 * @param sezione -> aggiorna sezione
	 * @return true settutto è andato a buon fine
	 */
	
	public static boolean updateAula(int id,int classe,String sezione) {
		boolean ok=false;
		int flag=0;
		
		if (!(classe>=1 && classe<=3)) {
			return ok;
		}
		
		
		ArrayList<Aula> lista = DisposizioneDAO.aule();
		for (Aula aula : lista) {
			if (aula.getAulaId()==id)
				flag++;
		}
		
		//TODO: gestire l'errore dell'ID non trovato
		if (flag==0)
			ok=false;
		
		
				
		
		if (flag!=0) {
			try {
				Connection conn = ConnectDB.getConnection();
				PreparedStatement std = conn.prepareStatement(updateAula);
				std.setInt(1, classe);
				std.setString(2, sezione);
				std.setInt(3, id);
				std.execute();
				conn.close();
				ok=true;
			}catch (SQLException e) {
				e.printStackTrace();
				ok=false;
			}	
		}

		return ok;
	}
	
	/**
	 * mi restituisce una lista di alunni di una determinata classe
	 * @param id <- id dell'aula
	 * @return lista di alunni
	 */
	
	public static ArrayList<Alunno> alunniClasse(int id){
		lista = new ArrayList<>();
	

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement std = conn.prepareStatement(alunniClasse);
			std.setInt(1, id);
			ResultSet result = std.executeQuery();
			while (result.next()) {
				Alunno alunno = new Alunno(result.getInt("id"),result.getString("nome"),
						result.getString("cognome"),result.getString("sesso"),result.getString("comportamento")
						,result.getInt("classe"),result.getString("sezione"));
				lista.add(alunno);
			}
			conn.close();
			return lista;
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
		
	}
	
	public static int classeid(int classe,String sezione) {
		int k=0;
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement std = conn.prepareStatement(classeid);
			std.setInt(1,classe);
			std.setString(2, sezione);
			ResultSet res = std.executeQuery();
			while (res.next()) {
				k = res.getInt("id");
			}
			conn.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return k;
	}


	
	
	
	
	
	

}
