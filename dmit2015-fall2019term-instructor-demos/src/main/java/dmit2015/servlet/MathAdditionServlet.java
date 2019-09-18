package dmit2015.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MathAdditionServlet
 */
@WebServlet("/MathAdditionServlet")
public class MathAdditionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MathAdditionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("GET is not supported by free version.");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String num1String = request.getParameter("num1");
		String num2String = request.getParameter("num2");
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head><title>MathAdditionServlet</title></head>");
			out.println("<body>");
			
			out.println("<h2>Context Path: " + request.getContextPath() + "</h2>");		
			try {
				int result = Integer.parseInt(num1String) + Integer.parseInt(num2String);
				String message = String.format("%s + %s = %d", num1String, num2String, result);
				out.println("<p>Solution: <strong>" + message + "</strong></p>");
			} catch(NumberFormatException ex) {
				out.println("<p>Please enter integers only. Try again.</p>");
			}
			
			out.println("</body>");			
			out.println("</html>");
		} finally {
			out.close();
		}
	}

}
