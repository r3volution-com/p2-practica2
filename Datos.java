// DNI 77842527 GONZALEZ ALVARADO, MARIO

/**
 *
 * @author mga101
 */
public class Datos {
	private int planta;
	private int habitacion;
	private int cama;
	//Constructor
    public Datos(int planta, int habitacion, int cama) {
        this.planta = planta;
        this.habitacion = habitacion;
        this.cama = cama;
    }
	public int getPlanta() {
		return planta;
	}
	public int getHabitacion() {
		return habitacion;
	}
	public int getCama() {
		return cama;
	}
}
