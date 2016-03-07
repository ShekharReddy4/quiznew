import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.lang.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession; 
public class DisplayServlet extends HttpServlet
	{
		public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
		{		
			PrintWriter writer = response.getWriter();
			response.setContentType("text/html");
			HttpSession session=request.getSession();  
			if ((request.getParameter("his")!= null || request.getParameter("spo")!= null))
				{
					writer.println("<html>");
					writer.println("<body>");
					writer.println("<form method='post' action='http://localhost:9999/quiznew/display'>");
					writer.println("<fieldset>");
					writer.println("<p>" +""+ Qservlet.q[Qservlet.count].quizquestion+ "</p>");
					writer.println("<ul>");
					writer.println("<input type='radio' name="+"'"+Qservlet.count+"'"+" value='1'>"+ Qservlet.q[Qservlet.count].answer1 +"<br/>");
					writer.println("<input type='radio' name="+"'"+Qservlet.count+"'"+" value='2'>"+ Qservlet.q[Qservlet.count].answer2+"<br/>");
					writer.println("<input type='radio' name="+"'"+Qservlet.count+"'"+" value='3'>"+ Qservlet.q[Qservlet.count].answer3+"<br/>");
					writer.println("<input type='radio' name="+"'"+Qservlet.count+"'"+" value='4'>"+ Qservlet.q[Qservlet.count].answer4+"<br/>");					
					writer.println("</ul>");
					writer.println("</fieldset>");
					writer.println("<input type='submit' name='next'value='Next'/>"); 
					writer.println("<input type='submit' name='prev'value='Previous' disabled/>"); 
					writer.println("<input type='submit' name='fin'value='Finish'/>");
					writer.println("<input type='submit' name='logout'value='Logout'/>");
					writer.println("</form>"); 
					writer.println("</body></html>");
					 }
		else
				{
					if (request.getParameter("next")!= null)
						{
							if(request.getParameter(""+Qservlet.count)!=null)
								{
									session.setAttribute(Qservlet.q[Qservlet.count].quizquestion,Integer.parseInt(request.getParameter(""+Qservlet.count)));
								}
							else
								{
									session.setAttribute(Qservlet.q[Qservlet.count].quizquestion,99);
								}
							Qservlet.count++;
							writer.println("<html>");
							writer.println("<body>");
							writer.println("<form method='post' action='http://localhost:9999/quiznew/display'>"); 
							writer.println("<fieldset>");
							writer.println("<p>" +""+ Qservlet.q[Qservlet.count].quizquestion+ "</p>");
							writer.println("<ul>");
							writer.println("<input type='radio' id="+"'"+Qservlet.count+""+1+"'"+" name="+"'"+Qservlet.count+"'"+" value='1'>"+ Qservlet.q[Qservlet.count].answer1 +"<br/>");
							writer.println("<input type='radio' id="+"'"+Qservlet.count+""+2+"'"+" name="+"'"+Qservlet.count+"'"+" value='2'>"+ Qservlet.q[Qservlet.count].answer2+"<br/>"); 
							writer.println("<input type='radio' id="+"'"+Qservlet.count+""+3+"'"+" name="+"'"+Qservlet.count+"'"+" value='3'>"+ Qservlet.q[Qservlet.count].answer3+"<br/>");
							writer.println("<input type='radio' id="+"'"+Qservlet.count+""+4+"'"+" name="+"'"+Qservlet.count+"'"+" value='4'>"+ Qservlet.q[Qservlet.count].answer4+"<br/>");
							writer.println("</ul>"); 
							writer.println("</fieldset>");	
							if(Qservlet.count==((Qservlet.size)-1))
								{
									writer.println("<input type='submit' name='next'value='Next' disabled />"); 
								}
							else if (Qservlet.count!=((Qservlet.size)-1))
								{
									writer.println("<input type='submit' name='next'value='Next'/>"); 
								}
							writer.println("<input type='submit' name='prev'value='Previous'/>"); 
							writer.println("<input type='submit' name='fin'value='Finish'/>"); 
							writer.println("<input type='submit' name='logout'value='Logout'/>");
							writer.println("</form>"); 
							writer.println("</body>");
							writer.println("<script>");
							if (session.getAttribute(Qservlet.q[Qservlet.count].quizquestion)!=null)
							{
								writer.println("document.getElementById("+"'"+Qservlet.count+""+session.getAttribute(Qservlet.q[Qservlet.count].quizquestion)+"'"+").checked = true;");
							}
							writer.println("</script>");
							writer.println("</html>");
						}
					else if(request.getParameter("prev")!= null)
						{
							if(request.getParameter(""+Qservlet.count)!=null)
								{
									session.setAttribute(Qservlet.q[Qservlet.count].quizquestion,Integer.parseInt(request.getParameter(""+Qservlet.count)));
								}
							else
								{
									session.setAttribute(Qservlet.q[Qservlet.count].quizquestion,99);
								}
							Qservlet.count--;
							writer.println("<html>");
							writer.println("<body>");
							writer.println("<form method='post' action='http://localhost:9999/quiznew/display'>"); 
							writer.println("<fieldset>");
							writer.println("<p>" +""+ Qservlet.q[Qservlet.count].quizquestion+ "</p>");
							writer.println("<ul>");
							writer.println("<input type='radio' id="+"'"+Qservlet.count+""+1+"'"+" name="+"'"+Qservlet.count+"'"+" value='1'>"+ Qservlet.q[Qservlet.count].answer1 +"<br/>");
							writer.println("<input type='radio' id="+"'"+Qservlet.count+""+2+"'"+" name="+"'"+Qservlet.count+"'"+" value='2'>"+ Qservlet.q[Qservlet.count].answer2+"<br/>"); 
							writer.println("<input type='radio' id="+"'"+Qservlet.count+""+3+"'"+" name="+"'"+Qservlet.count+"'"+" value='3'>"+ Qservlet.q[Qservlet.count].answer3+"<br/>");
							writer.println("<input type='radio' id="+"'"+Qservlet.count+""+4+"'"+" name="+"'"+Qservlet.count+"'"+" value='4'>"+ Qservlet.q[Qservlet.count].answer4+"<br/>");
							writer.println("</ul>"); 
							writer.println("</fieldset>");
							writer.println("<input type='submit' name='next'value='Next'/>");
							if(Qservlet.count==0)
								{
									writer.println("<input type='submit' name='prev'value='Previous' disabled/>"); 
								}
							else if (Qservlet.count!=0)
								{
									writer.println("<input type='submit' name='prev'value='Previous'/>"); 
								}
							writer.println("<input type='submit' name='fin'value='Finish'/>"); 
							writer.println("<input type='submit' name='logout'value='Logout'/>");
							writer.println("</form>"); 
							writer.println("</body>");
							writer.println("<script>");
							if (session.getAttribute(Qservlet.q[Qservlet.count].quizquestion)!=null)
							{
								writer.println("document.getElementById("+"'"+Qservlet.count+""+session.getAttribute(Qservlet.q[Qservlet.count].quizquestion)+"'"+").checked = true;");
							}
							writer.println("</script>");
							writer.println("</html>");
					    }
					else if (request.getParameter("fin")!=null)
						{
							int finalcount=0;
							if (request.getParameter(""+Qservlet.count)!=null)
							{
								session.setAttribute(Qservlet.q[Qservlet.count].quizquestion,Integer.parseInt(request.getParameter(""+Qservlet.count)));
							}
							for (Qservlet.count=0;Qservlet.count<Qservlet.size; (Qservlet.count)++)
							{
								if (session.getAttribute(Qservlet.q[Qservlet.count].quizquestion)!=null)
								{ 
									Qservlet.userans.put(Qservlet.q[Qservlet.count].quizquestion,(int)session.getAttribute(Qservlet.q[Qservlet.count].quizquestion));
								}
								else
								{
									session.setAttribute(Qservlet.q[Qservlet.count].quizquestion,99);
									Qservlet.userans.put(Qservlet.q[Qservlet.count].quizquestion,99);
								}
								if ((int)session.getAttribute(Qservlet.q[Qservlet.count].quizquestion)==Qservlet.finalans.get(Qservlet.q[Qservlet.count].quizquestion))
								{
									finalcount++;
								}		
							}							
								writer.println("<html>");
								writer.println("<body>");
								writer.println("<form method='post' action='http://localhost:9999/quiznew/mail'>"); 
								writer.println(""+finalcount+"       out of     "+Qservlet.size+"       are correct");
								writer.println("<br>");
								writer.println("email:<input type='text' name='emailid'/>");
								writer.println("<input type='submit' name='fin'value='Mailme!'/>"); 
								writer.println("<input type='submit' name='logout'value='Logout'/>");
								writer.println("</form>");
								writer.println("</body>");	
								writer.println("</html>");														
						} 
						else if (request.getParameter("logout")!= null)
						{
							session.invalidate();
							RequestDispatcher rd=request.getRequestDispatcher("index.html");
							rd.forward(request,response);
						}
				}
		}
	}