import java.sql.*;
import javax.annotation.Resource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; 
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource; 
public class LoginDao {
public static boolean validate(String name,String pass){
boolean status=false;
try{
		Context initContext = new InitialContext();
		Context envContext = (Context) initContext.lookup("java:comp/env");
		DataSource ds = (DataSource) envContext.lookup("jdbc/ebookshop");
		Connection con = ds.getConnection();
	PreparedStatement ps=con.prepareStatement("select * from userreg where name=? and pass=?");
	ps.setString(1,name);
	ps.setString(2,pass);
	ResultSet rs=ps.executeQuery();
	status=rs.next();
}catch(Exception e){System.out.println(e);}
return status;
}
}
