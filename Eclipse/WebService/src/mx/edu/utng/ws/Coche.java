package mx.edu.utng.ws;

public class Coche {
	private int id;
	private String marca;
	private String color;
	private String matricula;
	private int precio;
	private int descuento;
	private int modelo;
	
	public Coche(int id, String marca, String color, String matricula, int precio, int descuento, int modelo) {
		super();
		this.id = id;
		this.marca = marca;
		this.color = color;
		this.matricula = matricula;
		this.precio = precio;
		this.descuento = descuento;
		this.modelo = modelo;
	}
	public Coche() {
		this(0, "", "", "", 0,0,0);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	public int getDescuento() {
		return descuento;
	}
	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}
	public int getModelo() {
		return modelo;
	}
	public void setModelo(int modelo) {
		this.modelo = modelo;
	}
	@Override
	public String toString() {
		return "Coche [id=" + id + ", marca=" + marca + ", color=" + color + ", matricula=" + matricula + ", precio="
				+ precio + ", descuento=" + descuento + ", modelo=" + modelo + "]";
	}	
}
