import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession; 
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.darwinsys.spdf.PDF;
import com.darwinsys.spdf.Page;
import com.darwinsys.spdf.Text;
import com.darwinsys.spdf.MoveTo;
public class Pdf extends HttpServlet {
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("application/pdf");
		HttpSession session=request.getSession(false);
		response.setHeader("Content-disposition","inline; filename='Quiz-webapp'");
		PDF p = new PDF(out);
		Page p1 = new Page(p);
		p1.add(new MoveTo(p, 20, 790));
		int i=0;
		for (i=0;i<Qservlet.size ;i++ )
		{	
			p1.add(new Text(p,"question"+(i+1)));
			p1.add(new Text(p,Qservlet.q[i].quizquestion));
			switch (Qservlet.userans.get(Qservlet.q[i].quizquestion))
			{
			case 1:	 p1.add(new Text(p,"Your Answer::"+Qservlet.q[i].answer1));
						 break;
			case 2:	 p1.add(new Text(p,"Your Answer::"+Qservlet.q[i].answer2));
						 break;
			case 3:	 p1.add(new Text(p,"Your Answer::"+Qservlet.q[i].answer3));
						 break;
			case 4:	 p1.add(new Text(p,"Your Answer::"+Qservlet.q[i].answer4));
						 break;
			default: p1.add(new Text(p,"Your Answer::not Answered"));
						  break;
			}
			switch (Qservlet.finalans.get(Qservlet.q[i].quizquestion))
			{
			case 1:	 p1.add(new Text(p,"Answer::"+Qservlet.q[i].answer1));
						 p1.add(new Text(p,""));
						 break;
			case 2:	 p1.add(new Text(p,"Answer::"+Qservlet.q[i].answer2));
						 p1.add(new Text(p,""));
						 break;
			case 3:	 p1.add(new Text(p,"Answer::"+Qservlet.q[i].answer3));
						 p1.add(new Text(p,""));
						 break;
			case 4:	 p1.add(new Text(p,"Answer::"+Qservlet.q[i].answer4));
						 p1.add(new Text(p,""));
						 break;
			default: p1.add(new Text(p,"Answer::not Answered"));
						  p1.add(new Text(p,""));
						  break;
			}
		}
		p.add(p1);
		p.setAuthor("QUIZ_GSOC");
		p.writePDF();
	}
}