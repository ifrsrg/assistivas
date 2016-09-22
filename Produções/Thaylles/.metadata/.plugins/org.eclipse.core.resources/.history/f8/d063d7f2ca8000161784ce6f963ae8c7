package banco;
import java.sql.*;

import DAO.Usuario;

public class insertBanco{
	
	public static void adiciona(Usuario usuario)
    {   Connection c = null;
    	PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "CREATE TABLE IF NOT EXISTS usuarios (login varchar(20) UNIQUE not null, email varchar(50) not null, senha varchar(15) not null)";
        Statement stmt;

        try
        {   Class.forName("org.postgresql.Driver");
        c = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "Thaylles");
            if (c != null){  
            	stmt = c.createStatement();
            	stmt.executeUpdate(query);
            	stmt.close();
            	ps = c.prepareStatement("INSERT INTO usuarios (login, email, senha) VALUES (?,?,?)");
                ps.setString(1, usuario.getLogin());
                ps.setString(2, usuario.getEmail());
                ps.setString(3, usuario.getSenha());
                
                System.out.println(ps.getUpdateCount()+" rows were affected by this statement.");
                ps.close();
            }
            c.close();
        }
        catch (Exception e)
        {   e.printStackTrace();
            System.exit(1);
        }
    }
}
