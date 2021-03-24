package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helpers.BDD;

public class Control_Perfil {
	public static String perfil(HttpServletRequest request, HttpServletResponse response, String usuario) {
		BDD base_datos = BDD.getBDD();
		if(base_datos.existe(usuario)) {
			
			String[] data = base_datos.getData(usuario);
			
			return "{\"message\":\"Perfil encontrado\","
				   + "\"usuario\":\""+usuario+"\","
				   + "\"nombre\":\""+data[1]+"\","
				   + "\"apellido\":\""+data[2]+"\","
				   + "\"correo\":\""+data[3]+"\","
				   + "\"edad\":\""+data[4]+"\","
				   + "\"telf\":\""+data[5]+"\","
				   + "\"descripcion\":\""+data[6]+"\","
				   + "\"pais\":\""+data[7]+"\","
				   + "\"estado\":\""+data[8]+"\","
				   + "\"ciudad\":\""+data[9]+"\","
				   + "\"status\":200}";
		}else {
			return "{\"message\":\"Perfil no encontrado\","
					+ "\"status\":400}";
		}
			
		
	}
}
