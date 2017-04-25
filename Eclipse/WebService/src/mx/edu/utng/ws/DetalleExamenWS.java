package mx.edu.utng.ws;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DetalleExamenWS {
	
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	private PreparedStatement ps;
	public DetalleExamenWS() {
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
	public int addDetalle(DetalleExamen detalleExamen){
		int result=0;
		if(connection!=null){
			try {
				ps=connection.prepareStatement("INSERT INTO detalle_examen (nom_pregunta,taxonomia,def_examen,resultado_aprendizaje) "
						+ " VALUES (?,?,?,?);");
				ps.setString(1, detalleExamen.getNomPregunta());
				ps.setString(2, detalleExamen.getTaxomania());
				ps.setString(3, detalleExamen.getDefExamen());
				ps.setString(4, detalleExamen.getResultados());
				
				result=ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	//para hacer un update
	public int updateDetalle(DetalleExamen detalleExamen){
		int result=0;
		if(connection!=null){
			try {
				ps=connection.prepareStatement("UPDATE detalle_examen  SET nom_pregunta= ?,taxonomia= ?,def_examen= ?,resultado_aprendizaje= ? WHERE id= ?;");
					
				ps.setString(1, detalleExamen.getNomPregunta());
				ps.setString(2, detalleExamen.getTaxomania());
				ps.setString(3, detalleExamen.getDefExamen());
				ps.setString(4, detalleExamen.getResultados());				
				
				ps.setInt(5, detalleExamen.getId());
				result=ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	//para borrar
	public int removeDetalle(int id){
		int result=0;
		if(connection!=null){
			try {
				ps=connection.prepareStatement("DELETE FROM detalle_examen WHERE id= ?;");
				ps.setInt(1, id);
				result=ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	//lista de detalle
	public DetalleExamen[] getDetalles(){
		DetalleExamen[] result = null;
		List <DetalleExamen> detalleExamens=new ArrayList<DetalleExamen>();
		if(connection!=null){
			try {
				statement=connection.createStatement();
				resultSet=statement.executeQuery("SELECT * FROM detalle_examen;");
				while(resultSet.next()){
					DetalleExamen detalleExamen=new DetalleExamen(
							resultSet.getInt("id"),
							resultSet.getString("nom_pregunta"),
							resultSet.getString("taxonomia"), 
							resultSet.getString("def_examen"), 
							resultSet.getString("resultado_aprendizaje"));
					detalleExamens.add(detalleExamen);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		result = detalleExamens.toArray(new DetalleExamen[detalleExamens.size()]);
		return result;
	}
	//
	public DetalleExamen getDetallebyId(int id){
		DetalleExamen detalleExamen=null;
		if(connection!=null){
			try {
				ps=connection.prepareStatement("SELECT * FROM detalle_examen WHERE id = ?;");
				ps.setInt(1, id);
				resultSet=ps.executeQuery();
				while(resultSet.next()){
					detalleExamen=new DetalleExamen(
							resultSet.getInt("id"),
							resultSet.getString("nom_pregunta"),
							resultSet.getString("taxonomia"), 
							resultSet.getString("def_examen"), 
							resultSet.getString("resultado_aprendizaje"));
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return detalleExamen;
	}

}
