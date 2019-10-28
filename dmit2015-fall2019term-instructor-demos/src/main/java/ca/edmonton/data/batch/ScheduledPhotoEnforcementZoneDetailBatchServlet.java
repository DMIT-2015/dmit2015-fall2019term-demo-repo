package ca.edmonton.data.batch;

import java.io.IOException;
import java.io.PrintWriter;

import javax.batch.operations.JobOperator;
import javax.batch.operations.JobSecurityException;
import javax.batch.operations.JobStartException;
import javax.batch.runtime.BatchRuntime;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ScheduledPhotoEnforcementZoneDetailBatchServlet
 */
@WebServlet("/ScheduledPhotoEnforcementZoneDetailBatchServlet")
public class ScheduledPhotoEnforcementZoneDetailBatchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScheduledPhotoEnforcementZoneDetailBatchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			JobOperator jobOperator = BatchRuntime.getJobOperator();
			long jobId = jobOperator.start("csvToJsonJob", null);
			out.println("Job submitted: " + jobId);
		} catch(JobStartException | JobSecurityException ex) {
			out.println("Error submitting job! " + ex.getMessage());
			ex.printStackTrace();
		}
		out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
