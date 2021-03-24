package helpers;

import java.sql.*;

public class BDD {
	private static BDD base_datos= new BDD();
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private String driverDB = "org.postgresql.Driver";
	private String dbName = "dfunlmvqir0fs7?sslmode=require";//users
	private String urlDB = "jdbc:postgresql://ec2-52-22-161-59.compute-1.amazonaws.com:5432/"+this.dbName;//jdbc:postgresql://localhost:5432/
	private String userDB = "yijkdugqbuvsee";//postgres
	private String passDB = "2dca28ad80cb1e2fb91ba135c4660bde84e42a5fc2ec6de086daa39b604a3594";//apwmwg.ga
	

	//


	public BDD() {
		try {
			Class.forName(driverDB);
			this.conn = DriverManager.getConnection(urlDB,userDB,passDB);
		}catch (ClassNotFoundException| SQLException e) {
			e.printStackTrace();  
		}
	}
	
	public static BDD getBDD() {
		return base_datos;
	}
	
	public void dbClose() {
		try {
			this.conn.close();
			System.out.println("Conexion cerrada");
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean existe(String sample) {
		try {
			this.stmt = this.conn.createStatement();
			this.rs = this.stmt.executeQuery("select username from users");
			while(rs.next()) {
				String clave=rs.getString("username");
				if(clave.contentEquals(sample)) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public void dbPrepareStatement(String query, Object[] obj) {

			try {
				this.pstmt=this.conn.prepareStatement(query);
				for(int i=0;i<5;i++) {
					this.pstmt.setString((i+1), (String)obj[i]);	
				}
				this.pstmt.setInt(6, Integer.parseInt((String) obj[5]));
				this.pstmt.executeUpdate();	
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					this.pstmt.close();
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}
	
	}
	public String getClave(String usuario) {
		try {
			this.stmt = this.conn.createStatement();
			this.rs = this.stmt.executeQuery("select * from users");
			while(rs.next()) {
				String user=rs.getString("username");
				if(user.contentEquals(usuario)) {
					return rs.getString("pass");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
		}
	public String[] getData(String username) {
		
		try {
			this.stmt = this.conn.createStatement();
			this.rs = this.stmt.executeQuery("select * from users");
			while(rs.next()) {
				if(username.contentEquals(rs.getString("username"))) {
					String[] data= {rs.getString("username"),
									rs.getString("nombre"),
									rs.getString("apellido"),
									rs.getString("correo"),
									rs.getString("edad"),
									rs.getString("telf"),
									rs.getString("descripcion"),
									rs.getString("pais"),
									rs.getString("estado"),
									rs.getString("ciudad")};
					return data;
				}				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	               
}
