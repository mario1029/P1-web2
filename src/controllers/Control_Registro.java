package controllers;

import helpers.BDD;
import helpers.Hash;

public class Control_Registro {
	public static String agregar(String usuario,String nombre, String apellido,String clave, String correo, String edad ) {
		System.out.println("Usuario a registrar:"+usuario);
		
		Hash encriptado = Hash.getHash();
		
		Object[] datos= {
			usuario,
			nombre,
			apellido,
			encriptado.encriptar(clave),
			correo,
			edad
		};
		
		BDD base_datos = BDD.getBDD();
		
		if(base_datos.existe(usuario)) {
			
			return"{\"message\": \"Usuario ya existe\", \"status\": 200 }";
			
		}else {
			
			base_datos.dbPrepareStatement("INSERT INTO users(username,nombre,apellido,pass,correo,edad) VALUES (?,?,?,?,?,?)", datos);
			return"{\"message\": \"Usuario creado exitosamente\", \"status\": 200 }";
		}
		
	}
}
