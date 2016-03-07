import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import javax.servlet.http.HttpSession; 
public class Qservlet extends HttpServlet {  
	String str1,str2,str3,str4,str5;
	public static Map<String, Integer> finalans = new HashMap<String, Integer>();
	public static Map<String, Integer> userans = new HashMap<String, Integer>();
	static int count=0,size=0;
	int i = 0;
	File fXmlFile;
	public static Question[] q=new Question[10];
	void reset(HttpServletRequest request)
		{
		count=0;
		size=0;
		finalans = new HashMap<String, Integer>();
		userans = new HashMap<String, Integer>();
		
		HttpSession session=request.getSession(false);
		if (session!=null)
		{
			String op=new String();
			if (request.getParameter("his")!=null)
			{
					op=new String("history");
			}
			else
			{
					op=new String("sports");
			}
			if (op==session.getAttribute("topic"))
			{}
			else
			{
				String n=(String)session.getAttribute("name");
				session.invalidate();
				session=request.getSession(true);
				session.setAttribute("name",n);
				if (request.getParameter("his")!=null)
				{
					session.setAttribute("topic","history");
				}
				else if(request.getParameter("spo")!=null)
				{
					session.setAttribute("topic","sports");
				}				
			}
		}
	}

   @Override
   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	   {
	  reset(request);
	  HttpSession session=request.getSession(false);
	  response.setContentType("text/html");
	  if (request.getParameter("logout")!= null)
	  {
		  session.invalidate();
			RequestDispatcher rd=request.getRequestDispatcher("index.html");
			rd.forward(request,response);
	  }
	  else
		   {
      try  
		  { 	
				if (request.getParameter("his")!= null)
					 {		 
							fXmlFile = new File("E:/myProject/apache-tomcat-8.0.26/webapps/quiznew/WEB-INF/classes/history.xml");
					 }
				else
					{
							fXmlFile = new File("E:/myProject/apache-tomcat-8.0.26/webapps/quiznew/WEB-INF/classes/sports.xml");
					}
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(fXmlFile);
				doc.getDocumentElement().normalize();			
				NodeList nList = doc.getElementsByTagName("question");
				size=nList.getLength();
				for (int temp = 0; temp < nList.getLength(); temp++) 
					{
						Node nNode = nList.item(temp);
						if (nNode.getNodeType() == Node.ELEMENT_NODE)
							{
								Element eElement = (Element) nNode;			
								str1=eElement.getElementsByTagName("quizquestion").item(0).getTextContent();
								str2=eElement.getElementsByTagName("answer1").item(0).getTextContent();
								str3=eElement.getElementsByTagName("answer2").item(0).getTextContent();
								str4=eElement.getElementsByTagName("answer3").item(0).getTextContent();
								str5=eElement.getElementsByTagName("answer4").item(0).getTextContent();
								q[temp] =new Question(str1,str2,str3,str4,str5);
								finalans.put(str1,Integer.parseInt(eElement.getElementsByTagName("correct").item(0).getTextContent()));
							}
					}	
				RequestDispatcher rd=request.getRequestDispatcher("display");
				rd.forward(request,response);		
			}
	  catch (Exception e)
		 {
			e.printStackTrace();
		 }
	}
   }
} 