package controllers;

import javax.servlet.http.HttpServletRequest;


public class Control_Logout {
	@SuppressWarnings("null")
	public static String salir(String username, HttpServletRequest request) {
		if(username!=null || !username.contentEquals("")) {
			request.getSession().invalidate();
			return "{\"message\":\"Cierre de sesion exitoso\","
					+ "\"redirect\":\"login.html\","
					+ "\"status\":200}";
		}else {
			return "{\"message\":\"Cierre de sesion fallido\","
					+ "\"redirect\":\"perfil.html\","
					+ "\"status\":400}";
		}
		 
	}
}
