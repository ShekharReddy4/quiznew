import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession; 
import javax.servlet.http.Cookie;  
import java.util.Date;
import java.net.URL;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.activation.DataSource;
import javax.activation.URLDataSource;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.*;
import java.io.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
public class Email extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();		
	if (request.getParameter("logout")!= null)
	  {
		HttpSession session=request.getSession(false);
			session.invalidate();
			RequestDispatcher rd=request.getRequestDispatcher("index.html");
			rd.forward(request,response);
	  }
		else
		{
		String e=request.getParameter("emailid");
		String host = "smtp.gmail.com";
        String port = "587";
        String mailFrom = "reddyshekhar619@gmail.com";
        String password = "blaseh0987";
        String mailTo = e;
        String subject = "Quiz";
        String message = "Quiz Results.";
		 try {
		final URL url;
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.user", mailFrom);
        properties.put("mail.password", password);
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailFrom, password);
            }
        };
        Session session = Session.getInstance(properties, auth);
         Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(mailFrom));
        InternetAddress[] toAddresses = { new InternetAddress(mailTo) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(message, "text/html");
         Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
		MimeBodyPart attachPart = new MimeBodyPart();
		 try {
					url = new URL("http://localhost:9999/quiznew/pdf");
					DataSource source = new URLDataSource(url);
					attachPart.setDataHandler(new DataHandler(source));
					attachPart.setFileName("hello");
					multipart.addBodyPart(attachPart);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
        msg.setContent(multipart);
		out.println("<html>");
		out.println("<body>");
		out.println("<form  method='post' action='http://localhost:9999/quiznew/mail'>");
		out.println("thank u for using our quiz application");
		out.println("An attachment sent to your mail, please check it out");
		out.println("<input type='submit' name='logout'value='Logout'/>");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
        Transport.send(msg);
        } catch (Exception ex) {
            System.out.println("Could not send email.");
            ex.printStackTrace();
        }
		}
	}
}