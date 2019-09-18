package dmit2015.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

/**
 * Servlet implementation class QrCodeGeneratorServlet
 */
@WebServlet("/QrCodeGeneratorServlet")
public class QrCodeGeneratorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QrCodeGeneratorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String urlString = request.getParameter("url");
		if (urlString != null && !urlString.isEmpty()) {
			try {
				if (request.getParameter("download") != null) {
					response.setHeader("Content-disposition", "attachment;filename=customQrCode.png");
				}
				
				
				byte[] imageData = getQRCodeImage(urlString, 64, 64);
				response.setContentType("image/png");
				response.setContentLength(imageData.length);
				response.getOutputStream().write(imageData);
			} catch (WriterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response.getWriter().write("Servlet exception: " + e.getMessage());
			}
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	/* 
	This method takes the text to be encoded, the width and height of the QR Code, 
	and returns the QR Code in the form of a byte array.
	*/
	private byte[] getQRCodeImage(String text, int width, int height) throws WriterException, IOException {
	    QRCodeWriter qrCodeWriter = new QRCodeWriter();
	    BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
	    
	    ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
	    MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
	    byte[] pngData = pngOutputStream.toByteArray(); 
	    return pngData;
	}


}
