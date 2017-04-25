package mx.edu.utng.ws;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AlumnoAsistenteWS {
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	private PreparedStatement ps;
	public AlumnoAsistenteWS() {
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
	public int addAlumno(AlumnoAsistencia alumnoAsistencia){
		int result=0;
		if(connection!=null){
			try {
				ps=connection.prepareStatement("INSERT INTO alumno_asistente (maestro, numero_evaluacion, asistente, grupo, materia) "
						+ " VALUES (?,?,?,?,?);");
				ps.setString(1, alumnoAsistencia.getMaestro());
				ps.setInt(2, alumnoAsistencia.getNumeroEvaluacion());
				ps.setString(3, alumnoAsistencia.getAsistente());
				ps.setInt(4, alumnoAsistencia.getGrupo());
				ps.setString(5, alumnoAsistencia.getMateria());
				
				result=ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	//para hacer un update
	public int updateAlumno(AlumnoAsistencia alumnoAsistencia){
		int result=0;
		if(connection!=null){
			try {
				ps=connection.prepareStatement("UPDATE alumno_asistente  SET maestro=?, numero_evaluacion=?, asistente=?, grupo=?, materia=? WHERE id= ?;");
					
				ps.setString(1, alumnoAsistencia.getMaestro());
				ps.setInt(2, alumnoAsistencia.getNumeroEvaluacion());
				ps.setString(3, alumnoAsistencia.getAsistente());
				ps.setInt(4, alumnoAsistencia.getGrupo());		
				ps.setString(5, alumnoAsistencia.getMateria());
				
				ps.setInt(6, alumnoAsistencia.getId());
				result=ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	//para borrar
	public int removeAlumno(int id){
		int result=0;
		if(connection!=null){
			try {
				ps=connection.prepareStatement("DELETE FROM alumno_asistente WHERE id= ?;");
				ps.setInt(1, id);
				result=ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	//lista de detalle
	public AlumnoAsistencia[] getAlumnos(){
		AlumnoAsistencia[] result = null;
		List <AlumnoAsistencia> alumnoAsistencias=new ArrayList<AlumnoAsistencia>();
		if(connection!=null){
			try {
				statement=connection.createStatement();
				resultSet=statement.executeQuery("SELECT * FROM alumno_asistente;");
				while(resultSet.next()){
					AlumnoAsistencia detalleExamen=new AlumnoAsistencia(
							resultSet.getInt("id"),
							resultSet.getString("maestro"),
							resultSet.getInt("numero_evaluacion"), 
							resultSet.getString("asistente"), 
							resultSet.getInt("grupo"), 
							resultSet.getString("materia"));
					
					alumnoAsistencias.add(detalleExamen);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		result = alumnoAsistencias.toArray(new AlumnoAsistencia[alumnoAsistencias.size()]);
		return result;
	}
	//
	public AlumnoAsistencia getAlumnobyId(int id){
		AlumnoAsistencia alumnoAsistencia =null;
		if(connection!=null){
			try {
				ps=connection.prepareStatement("SELECT * FROM alumno_asistente WHERE id = ?;");
				ps.setInt(1, id);
				resultSet=ps.executeQuery();
				while(resultSet.next()){
					alumnoAsistencia=new AlumnoAsistencia(
							resultSet.getInt("id"),
							resultSet.getString("maestro"),
							resultSet.getInt("numero_evaluacion"), 
							resultSet.getString("asistente"), 
							resultSet.getInt("grupo"), 
							resultSet.getString("materia"));
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return alumnoAsistencia;
	}

}
