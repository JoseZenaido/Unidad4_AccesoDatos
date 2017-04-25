package mx.edu.utng.ws;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CocheWS {
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	private PreparedStatement ps;
	
	public CocheWS() {
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
	public int addCoche(Coche coche){
		int result=0;
		if(connection!=null){
			try {
				ps=connection.prepareStatement("INSERT INTO coche (marca, color, matricula, precio, descuento, modelo) "
						+ " VALUES (?, ?, ?, ?, ?, ?);");
				
				ps.setString(1, coche.getMarca());
				ps.setString(2, coche.getColor());
				ps.setString(3, coche.getMatricula());
				ps.setInt(4, coche.getPrecio());
				ps.setInt(5, coche.getDescuento());
				ps.setInt(6, coche.getModelo());
				result=ps.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	//para hacer un update
	public int updateCoche(Coche coche){
		int result=0;
		if(connection!=null){
			try {
				ps=connection.prepareStatement("UPDATE coche  SET  marca=?, color=?, matricula=?, precio=?, descuento=?, modelo=? WHERE id= ?;");
					
				ps.setString(1, coche.getMarca());
				ps.setString(2, coche.getColor());
				ps.setString(3, coche.getMatricula());
				ps.setInt(4, coche.getPrecio());	
				ps.setInt(5, coche.getDescuento());
				ps.setInt(6, coche.getModelo());
				
				ps.setInt(7, coche.getId());
				result=ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	//para borrar
	public int removeCoche(int id){
		int result=0;
		if(connection!=null){
			try {
				ps=connection.prepareStatement("DELETE FROM coche WHERE id= ?;");
				ps.setInt(1, id);
				result=ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	//lista de coche
	public Coche[] getCoches(){
		Coche[] result = null;
		List <Coche> coches=new ArrayList<Coche>();
		if(connection!=null){
			try {
				statement=connection.createStatement();
				resultSet=statement.executeQuery("SELECT * FROM coche;");
				while(resultSet.next()){
					Coche coche=new Coche(
							resultSet.getInt("id"),
							resultSet.getString("marca"),
							resultSet.getString("color"), 
							resultSet.getString("matricula"), 
							resultSet.getInt("precio"),
							resultSet.getInt("descuento"),
							resultSet.getInt("modelo"));
					coches.add(coche);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		result = coches.toArray(new Coche[coches.size()]);
		return result;
	}
	//
	public Coche getCochebyId(int id){
		Coche coche=null;
		if(connection!=null){
			try {
				ps=connection.prepareStatement("SELECT * FROM coche WHERE id = ?;");
				ps.setInt(1, id);
				resultSet=ps.executeQuery();
				while(resultSet.next()){
					coche=new Coche(
							resultSet.getInt("id"),
							resultSet.getString("marca"),
							resultSet.getString("color"), 
							resultSet.getString("matricula"), 
							resultSet.getInt("precio"),
							resultSet.getInt("descuento"),
							resultSet.getInt("modelo"));
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return coche;
	}

}
