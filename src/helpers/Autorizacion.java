package helpers;

public class Autorizacion {
	public static Boolean verificar(String usuario, String clave) {
		
		BDD base_datos = BDD.getBDD();
		String tclave = base_datos.getClave(usuario);
		if(tclave.contentEquals(clave)) {
			return true;
		}else {
			return false;
		}
	}
}
