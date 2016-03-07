import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession; 
public class WelcomeServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session=request.getSession();
		String n=request.getParameter("username");
		session.setAttribute("name",n);
		if (session!=null)
		{	
		out.println("Welcome "+(String)session.getAttribute("name"));
		RequestDispatcher rd1=request.getRequestDispatcher("index2.html");
		rd1.include(request,response);
		}
		out.close();
	}
}
