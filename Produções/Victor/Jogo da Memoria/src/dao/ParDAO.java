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
			PreparedStatement cmd = con.prepareStatement("SELECT nome, data, form_img, form_vid, nivel FROM pares " +
														  " WHERE id_user = ?" +
														  " LIMIT 6 OFFSET ?");
			cmd.setInt(1, id);
			cmd.setInt(2, offset);
			/*ResultSet rs = cmd.executeQuery();
			
			ArrayList<Par> array = new ArrayList<Par>();
			
			while (rs.next()) {
				Par par = new Par();
				par.setId(id);
				par.setNome(rs.getString("nome"));
				par.setData(rs.getString("data"));
				par.setForm_img(rs.getString("form_img"));
				par.setForm_vid(rs.getString("form_vid"));
				par.setNivel(rs.getInt("nivel"));
				array.add(par);
			}
			
			return array;*/
			
			ArrayList<Par> array = preencheArray(cmd);
			con.close();
			return array;
			
		} catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public ArrayList<Par> randomPares(int nivel, int quant, Integer id){
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
	
}