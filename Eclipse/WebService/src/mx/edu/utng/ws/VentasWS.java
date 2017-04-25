package mx.edu.utng.ws;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VentasWS {
	
private Connection connection;
private Statement statement;
private ResultSet resultSet;
private PreparedStatement ps;

public VentasWS() {
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
public int addVenta(Venta venta){
	int result=0;
	if(connection!=null){
		try {
			ps=connection.prepareStatement("INSERT INTO venta (total) "
					+ " VALUES (?);");
			
			ps.setInt(1, venta.getTotal());
			
			result=ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	return result;
}
//para hacer un update
public int updateVenta(Venta  venta){
	int result=0;
	if(connection!=null){
		try {
			ps=connection.prepareStatement("UPDATE venta  SET total= ? WHERE id= ?;");
				
			ps.setInt(1, venta.getTotal());
			ps.setInt(2, venta.getId());
			
			result=ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	return result;
}
//para borrar
public int removeVenta(int id){
	int result=0;
	if(connection!=null){
		try {
			ps=connection.prepareStatement("DELETE FROM venta WHERE id= ?;");
			ps.setInt(1, id);
			
			result=ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	return result;
}
//lista de movie
public Venta[] getVentas(){
	Venta[] result = null;
	List <Venta> ventas=new ArrayList<Venta>();
	if(connection!=null){
		try {
			statement=connection.createStatement();
			resultSet=statement.executeQuery("SELECT * FROM venta;");
			while(resultSet.next()){
				Venta venta=new Venta(
						resultSet.getInt("id"),
						resultSet.getInt("total"));
				ventas.add(venta);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	result = ventas.toArray(new Venta[ventas.size()]);
	return result;
}
//
public Venta getVentabyId(int id){
	Venta venta=null;
	if(connection!=null){
		try {
			ps=connection.prepareStatement("SELECT * FROM venta WHERE id = ?;");
			ps.setInt(1, id);
			resultSet=ps.executeQuery();
			while(resultSet.next()){
				venta=new Venta(
						resultSet.getInt("id"),
						resultSet.getInt("total"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	return venta;
}

}
