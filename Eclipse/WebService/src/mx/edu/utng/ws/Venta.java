package mx.edu.utng.ws;

public class Venta {
	int id;
	int total;
	
	
	public Venta(){
		this(0,0);
	}
	
	public Venta(int id, int total) {
		super();
		this.id = id;
		this.total = total;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return "Venta [id=" + id + ", total=" + total + "]";
	}
}
