package banco;
import java.sql.*;

import DAO.Login;
import DAO.Usuario;

//COMPILAR: javac JDBCSelect.java
//EXECUTAR: java -cp .:./jdbc???.jar JDBCSelect

public class JDBCSelect
{   public static boolean loga(Login usuario) 
    {   Connection c = null;
        Statement s = null;
        ResultSet rs = null;
        DatabaseMetaData dbmd = null;
        ResultSetMetaData rsmd = null;
        int row = 1;

        try
        {   Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost/postgres","postgres","postgres");
            if (c != null)
            {   dbmd = c.getMetaData();
                System.out.println("Connection to "+dbmd.getDatabaseProductName()+" "+dbmd.getDatabaseProductVersion()+" successful.");
                s = c.createStatement();
                rs = s.executeQuery("SELECT login, senha FROM usuarios where login ='"+usuario.getLogin()+"' and senha='"+usuario.getSenha()+"'");
                rsmd = rs.getMetaData();
                s.close();
            }
            c.close();
            if(rsmd != null) return true;
        }
        catch (Exception e)
        {   e.printStackTrace();
            System.exit(1);
        }
		return false;
    }
}
