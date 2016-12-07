package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelos.Par;

public class ParDAO {

	private ConnectionFactory factory = new ConnectionFactory();
	
	public void save(Par par){
		Connection con = factory.getConnection();
		try {
			PreparedStatement cmd = con.prepareStatement("INSERT INTO pares VALUES(?,?,?,?,?,?)");
			cmd.setInt(1, par.getId());
			cmd.setString(2, par.getNome());
			cmd.setString(3, par.getData());
			cmd.setString(4, par.getForm_img());
			cmd.setString(5, par.getForm_vid());
			cmd.setInt(6, par.getNivel());
			cmd.execute();
			cmd.close();
			con.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public ArrayList<Par> offsets(int offset, int id){
		Connection con = factory.getConnection();
		try{
			PreparedStatement cmd = con.prepareStatement("SELECT * FROM pares " +
														  " WHERE id_user = ?" +
														  " LIMIT 6 OFFSET ?");
			cmd.setInt(1, id);
			cmd.setInt(2, offset);
			ArrayList<Par> array = preencheArray(cmd);
			con.close();
			return array;
			
		} catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public ArrayList<Par> randomPares(Integer id, int nivel, int quant){
		Connection con = factory.getConnection();
		String sql = "SELECT * FROM pares WHERE nivel = ? AND id_user = ? ORDER BY RANDOM() LIMIT ?;";
				
		try {
			PreparedStatement cmd = con.prepareStatement(sql);
			cmd.setInt(1, nivel);
			cmd.setInt(2, id);
			cmd.setInt(3, quant);
			ArrayList<Par> array = preencheArray(cmd);
			con.close();
			return array;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public ArrayList<Par> randomPares(int nivel, int quant) {
		Connection con = factory.getConnection();
		String sql = "SELECT * FROM pares WHERE nivel = ? ORDER BY RANDOM() LIMIT ? ;";
		
		try {
			PreparedStatement cmd = con.prepareStatement(sql);
			cmd.setInt(1, nivel);
			cmd.setInt(2, quant);
			ArrayList<Par> array = preencheArray(cmd);
			con.close();
			return array;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public ArrayList<Par> randomPares(int quant){
		Connection con = factory.getConnection();
		String sql = "SELECT * FROM pares ORDER BY RANDOM() LIMIT ?;";
		
		try{
			PreparedStatement cmd = con.prepareStatement(sql);
			cmd.setInt(1, quant);
			ArrayList<Par> array = preencheArray(cmd);
			con.close();
			return array;
		} catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	public ArrayList<Par> randomParesUser(int id_user, int quant){
		Connection con = factory.getConnection();
		String sql = "SELECT * FROM pares WHERE id_user = ? ORDER BY RANDOM() LIMIT ?;";
		
		try{
			PreparedStatement cmd = con.prepareStatement(sql);
			cmd.setInt(1, id_user);
			cmd.setInt(2, quant);
			ArrayList<Par> array = preencheArray(cmd);
			con.close();
			return array;
		} catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	protected ArrayList<Par> preencheArray(PreparedStatement cmd){
		try {
			ResultSet rs = cmd.executeQuery();
			ArrayList<Par> array = new ArrayList<Par>();
			while(rs.next()){
				array.add(new Par(rs));
			}
			cmd.close();
			rs.close();
			return array;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Boolean delete(String id) {
		Connection con = factory.getConnection();
		try {
			PreparedStatement cmd = con.prepareStatement("DELETE FROM pares WHERE data = ?;");
			cmd.setString(1, id);
			cmd.execute();
			cmd.close();
			con.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Par selectByData(String data) {
		Connection con = factory.getConnection();
		
		try{
			PreparedStatement cmd = con.prepareStatement("SELECT * FROM pares WHERE data = ?");
			cmd.setString(1, data);
			ResultSet rs = cmd.executeQuery();
			if (rs.next())
				return new Par(rs);
		} catch(Exception e){
			throw new RuntimeException(e);
		}
		return null;
	}
	
	public void update(Par par){
		Connection con = factory.getConnection();
		
		try{
			String sql = "UPDATE pares SET nome = ?, nivel = ?";
			if (par.getForm_img() != null) {
				sql += ", form_img = ?";
			}
			sql += " WHERE data = ?;";
			PreparedStatement cmd = con.prepareStatement(sql);
			cmd.setString(1, par.getNome());
			cmd.setInt(2, par.getNivel());
			if (par.getForm_img() != null) {
				cmd.setString(3, par.getForm_img());
				cmd.setString(4, par.getData());
			}else{
				cmd.setString(3, par.getData());
			}
			
			cmd.execute();
			cmd.close();
			con.close();
		} catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	public Object selectAll(Integer id_user) {
		Connection con = factory.getConnection();
		ArrayList<String> retorno = new ArrayList<String>();
		try {
			PreparedStatement cmd = con.prepareStatement("SELECT nome FROM pares WHERE id_user = ?");
			cmd.setInt(1, id_user);
			ResultSet rs = cmd.executeQuery();
			while(rs.next()){
				retorno.add(rs.getString("nome"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return retorno;
	}
	
}