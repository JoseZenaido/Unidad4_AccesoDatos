package mx.edu.utng.ws;

public class AlumnoAsistencia {
	private int id;
	private int numeroEvaluacion;
	private int grupo;
	
	private String maestro;
	private String asistente;
	private String materia;
	
	public AlumnoAsistencia(int id, String maestro, int numeroEvaluacion, String asistente, int grupo, String materia) {
		super();
		this.id = id;
		this.maestro = maestro;
		this.numeroEvaluacion = numeroEvaluacion;
		this.asistente = asistente;
		this.grupo = grupo;
		this.materia = materia;
	}
	
	public AlumnoAsistencia(){
		this(0,"",0,"",0,"");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumeroEvaluacion() {
		return numeroEvaluacion;
	}

	public void setNumeroEvaluacion(int numeroEvaluacion) {
		this.numeroEvaluacion = numeroEvaluacion;
	}

	public int getGrupo() {
		return grupo;
	}

	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}

	public String getMaestro() {
		return maestro;
	}

	public void setMaestro(String maestro) {
		this.maestro = maestro;
	}

	public String getAsistente() {
		return asistente;
	}

	public void setAsistente(String asistente) {
		this.asistente = asistente;
	}

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

	@Override
	public String toString() {
		return "AlumnoAsistencia [id=" + id + ", numeroEvaluacion=" + numeroEvaluacion + ", grupo=" + grupo
				+ ", maestro=" + maestro + ", asistente=" + asistente + ", materia=" + materia + "]";
	}
	
	

}
