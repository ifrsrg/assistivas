package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelos.Usuario;

public class UsuarioDAO {
   
	private ConnectionFactory factory = new ConnectionFactory();
	
	public void save(Usuario user){
		 try{   
		 	Connection con = factory.getConnection();
		 	
			String sql = "INSERT INTO USUARIOS(NOME, EMAIL, LOGIN, SENHA) VALUES (?, ?, ?, ?)";
			PreparedStatement cmd = con.prepareStatement(sql);
			cmd.setString(1, user.getNome());
			cmd.setString(2, user.getEmail());
			cmd.setString(3, user.getLogin());
			cmd.setString(4, user.getSenha());
			cmd.execute();
			cmd.close();
			con.close();
		 }catch (Exception e){
			 e.printStackTrace();
			 System.exit(1);
		 }
	}
	
	public int selectMax(){
	    int result = -1;
		try{
			Connection con = factory.getConnection();
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("SELECT MAX(ID_USER) FROM USUARIOS");
			while(rs.next()){
				result = rs.getInt("max");
			}
			s.close();
			con.close();
		}catch (Exception e){
			 e.printStackTrace();
			 System.exit(1);
		}
		return result;
	}
	
	public String selectUsuario(int id){
		
		try{
			Connection con = factory.getConnection();
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM USUARIOS WHERE ID_USER =" + id);
			while(rs.next()){
				return preencheUser(rs).toString();
			}
			s.close();
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public int verificaUsuario(String login, String senha){
		
		try{
			Connection con = factory.getConnection();
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("SELECT ID_USER FROM USUARIOS WHERE (LOGIN = '" + login + "' OR EMAIL = '" + login + "') AND SENHA = '" + senha+"'");
			while(rs.next()){
				return rs.getInt("id_user");
			}
			s.close();
			con.close();
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
			return user;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public String selectLogin(String login){

		String retorno = new String();
		
		try{
			Connection con = factory.getConnection();
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("SELECT LOGIN FROM USUARIOS WHERE LOGIN = '" + login + "'");
			
			if(rs.next()){
				retorno = rs.getString("login");
			}
			s.close();
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return retorno;
	}
}