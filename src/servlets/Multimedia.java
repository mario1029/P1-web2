package servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import helpers.Archivos;

/**
 * Servlet implementation class Multimedia
 */
@MultipartConfig
@WebServlet("/Multimedia")
public class Multimedia extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Multimedia() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("image/jpeg");
		
		Part filePart = request.getPart("archivo1");
		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
		System.out.println("Archivo recibido:"+request.getParameter("nombre")+" -de valor:"+fileName);
		ServletOutputStream out = response.getOutputStream();
		InputStream fileContent = filePart.getInputStream();
		
		HttpSession sesion = request.getSession();
		
		File carpeta = new File("archivos"+"/"+
								sesion.getAttribute("user")+"/"+
								request.getParameter("nombre"));
		
		if(carpeta.mkdirs()) {
			System.out.println("Carpeta del usuario:"+sesion.getAttribute("user")+" creada exitosamente");
		}
		
		System.out.println("Direccion del archivo:"+carpeta.getAbsolutePath()+"/"+request.getParameter("nombre"));
		
		FileOutputStream output = new FileOutputStream(carpeta.getAbsolutePath()+"/"+request.getParameter("nombre"));
		
		Archivos.upload(fileContent, output);
	}

}
