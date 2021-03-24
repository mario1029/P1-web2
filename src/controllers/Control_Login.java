package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import helpers.Autorizacion;
import helpers.BDD;
import helpers.Hash;

public class Control_Login {
	public static String inciarSesion(String usuario, String clave, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		System.out.println("Loger iniciado para:"+usuario);
		
		Hash encriptado = Hash.getHash();
		BDD base_datos = BDD.getBDD();
		clave = encriptado.encriptar(clave);
		
		if(base_datos.existe(usuario)) {
			if(Autorizacion.verificar(usuario, clave)) {
				HttpSession sesion = request.getSession();
                sesion.setAttribute("user", usuario);
                Cookie loginCookie = new Cookie("user",usuario);
    			//setting cookie to expiry in 20 mins
    			loginCookie.setMaxAge(20*60);
    			response.addCookie(loginCookie);
    			System.out.println("Usuario logeado exitosamente");
				return "{\"message\":\"Inicio de sesion exitoso\","
						   + "\"redirect\":\"perfil.html\","
						   + "\"status\":200}";
				
			}else {
				System.out.println("credenciales");
				return "{\"message\":\"Clave invalida\","
						   + "\"redirect\":\"login.html\","
						   + "\"status\":200}";
			}
		}else { 
			System.out.println("usuario no encontrado");
			return "{\"message\":\"Usuario no encontrado\","
					   + "\"redirect\":\"login.html\","
					   + "\"status\":200}";
		}
	}
}
