
public class Herida {
	String lesion;
	int gravedad;
	public Herida(String nombre, int gravedad) {
		if (gravedad < 1 || gravedad > 9) gravedad = 3;
		lesion = nombre;
		this.gravedad = gravedad;
	}
	public boolean sana() {
		if (gravedad > 0) {
			gravedad--;
			return true;
		} else return false;
	}
	public boolean agrava() {
		if (gravedad < 9) {
			gravedad++;
			return true;
		} else return false;
	}
	public String getLesion(){
		return lesion;
	}
	public int getGravedad() {
		return gravedad;
	}
}
