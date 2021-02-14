package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Registro
 */
@WebServlet("/Registro")
public class Registro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		response.setContentType("text/html");
		PrintWriter output = response.getWriter();
		String html = new String();
		
		//Se hace el hashing (encriptado)
		
		String pass = request.getParameter("password");
		MessageDigest encrip = null;
		try {
			encrip = MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		encrip.update(pass.getBytes());
		
		byte[] encript_pass = encrip.digest();
		
		String secret_pass = Base64.getEncoder().encodeToString(encript_pass);
		//Se crea el html de respuesta
		html+="<html>";
		html+="<head>";
		html+="<link href=\"css.css\" rel=\"stylesheet\">";
		html+="</head>";
		html+="<body>";
		html+="<div id=\"caja\">";
		html+="<div align=\"center\"><h1>Proyecto 1 Web 2</h1>\r\n"
			+ "<h2>Por Mario González C.I:27.969.414</h2><br></div>";
		String name = request.getParameter("nombre");
		String correo = request.getParameter("correo");
		String edad = request.getParameter("edad");
		String usuario = request.getParameter("username");
		if(name.isEmpty()||(name.length()>30)) {
			html+="<br><h3>Nombre invalido nulo o mas de 30 caracteres.</h3>";
		}else if(!correo.contains("@")) {
			html+="<br><h3>Correo Invalido</h3>";
		}else if(pass.isEmpty()){
			html+="<br><h3>Debe ingresar una contraseña</h3>";
		}else if(edad.isEmpty()) {
			html+="<br><h3>Debe ingresar su edad</h3>";
		}else if(usuario.isEmpty()){
			html+="<br><h3>Falta nombre de usuario</h3>";
		}else {
			html+="Nombre introducido:"+name;
			html+="<br><br>";
			html+="Apellido introducido:"+request.getParameter("apellido");
			html+="<br><br>";
			html+="Edad introducida:"+request.getParameter("edad");
			html+="<br><br>";
			html+="Usuario:"+request.getParameter("username");
			html+="<br><br>";
			html+="Encripted Pass:";
			html+="<br>"+secret_pass+"<br><br>";
			html+="Correo:"+correo;
			html+="</div>";
			html+="</body></html>";
		}
		output.println(html);
	}

}
