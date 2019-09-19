package ca.nait.dmit.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.nait.dmit.domain.BMI;

/**
 * Servlet implementation class BmiServlet
 */
@WebServlet("/bmi")
public class BmiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BmiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String weightString = request.getParameter("weight");
		String heigtString = request.getParameter("height");
		if (weightString != null && !weightString.isBlank() 
				&& heigtString != null && !heigtString.isBlank()) {
			int weight = Integer.parseInt(weightString);
			int height = Integer.parseInt(heigtString);
			BMI bmiBean = new BMI();
			bmiBean.setWeight(weight);
			bmiBean.setHeight(height);
			
			response.setContentType("text/plain");
			PrintWriter out = response.getWriter();
			out.println(bmiBean.bmi());
			out.println(bmiBean.bmiCategory());
			out.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
