package mx.edu.utng.ws;

public class DetalleExamen {
	private int id;
	private String nomPregunta;
	private String taxomania;
	private String defExamen;
	private String resultados;
	
	public DetalleExamen(int id, String nomPregunta, String taxomania, String defExamen, String resultados) {
		super();
		this.id = id;
		this.nomPregunta = nomPregunta;
		this.taxomania = taxomania;
		this.defExamen = defExamen;
		this.resultados = resultados;
	}
	
	public DetalleExamen(){
		this(0, "", "", "", "");
	}
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNomPregunta() {
		return nomPregunta;
	}


	public void setNomPregunta(String nomPregunta) {
		this.nomPregunta = nomPregunta;
	}


	public String getTaxomania() {
		return taxomania;
	}


	public void setTaxomania(String taxomania) {
		this.taxomania = taxomania;
	}


	public String getDefExamen() {
		return defExamen;
	}


	public void setDefExamen(String defExamen) {
		this.defExamen = defExamen;
	}


	public String getResultados() {
		return resultados;
	}


	public void setResultados(String resultados) {
		this.resultados = resultados;
	}


	@Override
	public String toString() {
		return "DetalleExamen [id=" + id + ", nomPregunta=" + nomPregunta + ", taxomania=" + taxomania + ", defExamen="
				+ defExamen + ", resultados=" + resultados + "]";
	}
	
	
	
	

}
