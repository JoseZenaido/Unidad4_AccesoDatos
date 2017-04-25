package mx.edu.utng.ws;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CartaCompromisoWS {
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	private PreparedStatement ps;
	
	public CartaCompromisoWS() {
		conectar();
	}
	
	//Crear la conexion
	private void conectar(){
		try {
			Class.forName("org.postgresql.Driver");
			connection=DriverManager.getConnection("jdbc:postgresql://localhost:5432/wstest","postgres","12345");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
		e.printStackTrace();
		}
	}
	
	//para crear un insert
	public int addCarta(CartaCompromiso cartaCompromiso){
		int result=0;
		if(connection!=null){
			try {
				ps=connection.prepareStatement("INSERT INTO carta_compromiso"
						+ "(folio,"
						+ " periodo,"
						+ " pagado, "
						
						+ " autorizo, "
						+ "fecha_pago,"
						+ " fecha_solicitud, "
						+ "persona_autorizo)"
						
						+ " VALUES ( ?, ?, ?, ?, ?, ?, ?);");
				
				ps.setInt(1, cartaCompromiso.getFolio());
				ps.setInt(2, cartaCompromiso.getPeriodo());
				ps.setInt(3, cartaCompromiso.getPagado());
				ps.setString(4, cartaCompromiso.getAutorizo());
				ps.setString(5, cartaCompromiso.getFechaPago());
				ps.setString(6, cartaCompromiso.getFechaSolicitud());
				ps.setString(7, cartaCompromiso.getPersonaAutorizo());
				
				result=ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	
	
	//para hacer un update
	public int updateCarta(CartaCompromiso cartaCompromiso){
		int result=0;
		if(connection!=null){
			try {
				ps=connection.prepareStatement("UPDATE carta_compromiso   SET  folio=?, periodo=?, pagado=?, autorizo=?, fecha_pago=?, fecha_solicitud=?, persona_autorizo=? WHERE id=?;");
				
				ps.setInt(1, cartaCompromiso.getFolio());
				ps.setInt(2, cartaCompromiso.getPeriodo());
				ps.setInt(3, cartaCompromiso.getPagado());
				ps.setString(4, cartaCompromiso.getAutorizo());
				ps.setString(5, cartaCompromiso.getFechaPago());
				ps.setString(6, cartaCompromiso.getFechaSolicitud());
				ps.setString(7, cartaCompromiso.getPersonaAutorizo());
	
				ps.setInt(8, cartaCompromiso.getId());
				result=ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	//para borrar
	public int removeCarta(int id){
		int result=0;
		if(connection!=null){
			try {
				ps=connection.prepareStatement("DELETE FROM carta_compromiso WHERE id= ?;");
				ps.setInt(1, id);
				result=ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	//lista de detalle
	public CartaCompromiso[] getCartas(){
		CartaCompromiso[] result = null;
		
		List <CartaCompromiso> cartaCompromisos=new ArrayList<CartaCompromiso>();
		if(connection!=null){
			try {
				statement=connection.createStatement();
				resultSet=statement.executeQuery("SELECT * FROM carta_compromiso;");
				while(resultSet.next()){
					CartaCompromiso cartaCompromiso=new CartaCompromiso(
							resultSet.getInt("id"),
							resultSet.getInt("folio"),
							resultSet.getInt("periodo"),
							resultSet.getInt("pagado"),
							resultSet.getString("autorizo"),
							resultSet.getString("fecha_pago"),
							resultSet.getString("fecha_solicitud"),
							resultSet.getString("persona_autorizo"));
							
					
					cartaCompromisos.add(cartaCompromiso);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		result = cartaCompromisos.toArray(new CartaCompromiso[cartaCompromisos.size()]);
		return result;
	}
	
	//
	public CartaCompromiso getCartabyId(int id){
		CartaCompromiso cartaCompromiso =null;
		if(connection!=null){
			try {
				ps=connection.prepareStatement("SELECT * FROM carta_compromiso WHERE id = ?;");
				ps.setInt(1, id);
				resultSet=ps.executeQuery();
				while(resultSet.next()){
					cartaCompromiso=new CartaCompromiso(
							resultSet.getInt("id"),
							resultSet.getInt("folio"),
							resultSet.getInt("periodo"),
							resultSet.getInt("pagado"),			
							resultSet.getString("autorizo"),
							resultSet.getString("fecha_pago"),
							resultSet.getString("fecha_solicitud"),
							resultSet.getString("persona_autorizo"));					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cartaCompromiso;
	}

}
