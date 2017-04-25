package mx.edu.utng.ws;

public class CartaCompromiso {
	private int id;
	private int folio;
	private int periodo;
	private int pagado;
	private String autorizo;
	private String fechaPago;
	private String fechaSolicitud;
	private String personaAutorizo;
	
	public CartaCompromiso(int id, int folio, int periodo, int pagado, String autorizo, String fechaPago,
			String fechaSolicitud, String personaAutorizo) {
		super();
		this.id = id;
		this.folio = folio;
		this.periodo = periodo;
		this.pagado = pagado;
		this.autorizo = autorizo;
		this.fechaPago = fechaPago;
		this.fechaSolicitud = fechaSolicitud;
		this.personaAutorizo = personaAutorizo;
	}
	
	
	public CartaCompromiso(){
		this(0,0,0,0,"","","","" );
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getFolio() {
		return folio;
	}


	public void setFolio(int folio) {
		this.folio = folio;
	}


	public int getPeriodo() {
		return periodo;
	}


	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}


	public int getPagado() {
		return pagado;
	}


	public void setPagado(int pagado) {
		this.pagado = pagado;
	}


	public String getAutorizo() {
		return autorizo;
	}


	public void setAutorizo(String autorizo) {
		this.autorizo = autorizo;
	}


	public String getFechaPago() {
		return fechaPago;
	}


	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}


	public String getFechaSolicitud() {
		return fechaSolicitud;
	}


	public void setFechaSolicitud(String fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}


	public String getPersonaAutorizo() {
		return personaAutorizo;
	}


	public void setPersonaAutorizo(String personaAutorizo) {
		this.personaAutorizo = personaAutorizo;
	}


	@Override
	public String toString() {
		return "CartaCompromiso [id=" + id + ", folio=" + folio + ", periodo=" + periodo + ", pagado=" + pagado
				+ ", autorizo=" + autorizo + ", fechaPago=" + fechaPago + ", fechaSolicitud=" + fechaSolicitud
				+ ", personaAutorizo=" + personaAutorizo + "]";
	}
	
	
	

}
