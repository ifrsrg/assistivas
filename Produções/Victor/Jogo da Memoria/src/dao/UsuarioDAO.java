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
	
	public String selectUsuario(int id){
		
		try{
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection("jdbc:postgresql://localhost/Jogo da Memoria", "postgres", "projeto_de_pesquisa");
			if (c != null) {
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery("SELECT * FROM USUARIOS WHERE ID_USER =" + id);
				while(rs.next()){
					return preencheUser(rs).toString();
				}
				s.close();
			}
			c.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public int verificaUsuario(String login, String senha){
		
		try{
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection("jdbc:postgresql://localhost/Jogo da Memoria", "postgres", "projeto_de_pesquisa");
			if (c != null) {
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery("SELECT ID_USER FROM USUARIOS WHERE (LOGIN = '" + login + "' OR EMAIL = '" + login + "') AND SENHA = '" + senha+"'");
				while(rs.next()){
					return rs.getInt("id_user");
				}
				s.close();
			}
			c.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return -1;
	}
	
	public Usuario preencheUser(ResultSet dados){
		Usuario user = new Usuario();
		try {
			user.setId(dados.getInt("id_user"));
			user.setNome(dados.getString("nome"));
			user.setEmail(dados.getString("email"));
			user.setLogin(dados.getString("login"));
			user.setSenha(dados.getString("senha"));
			user.setNum_jogos(dados.getInt("num_jogos"));
			user.setDuracao(dados.getLong("duracao"));
			return user;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public String selectUsers(String login){
		Connection c = null;
	    Statement s = null;

	    String retorno = "";
	    
		try{
			Class.forName("org.postgresql.Driver");
			 c = DriverManager.getConnection("jdbc:postgresql://localhost/Jogo da Memoria","postgres","projeto_de_pesquisa");
			 if (c != null) {
				s = c.createStatement();
				ResultSet rs = s.executeQuery("SELECT LOGIN FROM USUARIOS WHERE LOGIN = '" + login + "'");
				while(rs.next()){
					retorno += rs.getString("login");
				}
				s.close();
			 }
			 c.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return retorno;
	}
}