package dao;

import modelos.Usuario;
import java.sql.*;

public class UsuarioDAO {
   
	public void save(Usuario user){
		Connection c = null;
	    Statement s = null;
		 try{   
			 Class.forName("org.postgresql.Driver");
			 c = DriverManager.getConnection("jdbc:postgresql://localhost/Jogo da Memoria","postgres","projeto_de_pesquisa");
			 if (c != null) {
				s = c.createStatement();
				s.executeUpdate("INSERT INTO USUARIOS(NOME, EMAIL, LOGIN, SENHA, NUM_JOGOS, DURACAO) VALUES ("+user.toInsert()+")");
				s.close();
			 }
			 c.close();
		 }catch (Exception e){
			 e.printStackTrace();
			 System.exit(1);
		 }
	}
	
	public int selectMax(){
		Connection c = null;
	    Statement s = null;
	    int result = -1;
		try{
			Class.forName("org.postgresql.Driver");
			 c = DriverManager.getConnection("jdbc:postgresql://localhost/Jogo da Memoria","postgres","projeto_de_pesquisa");
			 if (c != null) {
				s = c.createStatement();
				ResultSet rs = s.executeQuery("SELECT MAX(ID_USER) FROM USUARIOS");
				while(rs.next()){
					result = rs.getInt("max");
				}
				s.close();
			 }
			 c.close();
		}catch (Exception e){
			 e.printStackTrace();
			 System.exit(1);
		}
		return result;
	}
}
