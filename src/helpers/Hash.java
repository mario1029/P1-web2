package helpers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Hash {
	private static Hash hash=new Hash();
	public Hash() {
	}
	public static Hash getHash() {
		return hash;
	}
	public String encriptar(String clave) {
		String secreto = null;
		MessageDigest encrip = null;
		try {
			encrip = MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		encrip.update(clave.getBytes());
		
		byte[] encript_pass = encrip.digest();
		
		secreto = Base64.getEncoder().encodeToString(encript_pass);
		
		return secreto;
	}
}
