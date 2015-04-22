import java.util.Arrays;

// DNI 77842527 GONZALEZ ALVARADO, MARIO

public class Clinica {
	private String nombre;
	private Habitacion[][] plantas;
	
	//Constructor
	public Clinica(String nombre, int plantas){
		if (nombre != null) {
			this.nombre = nombre;
			if (plantas <= 0) plantas = 2;
			this.plantas = new Habitacion[plantas][];
		}
	}
	//Construye una planta
	public boolean construyePlanta(int nPlanta, int nHabitaciones){
		if (nHabitaciones <= 0) nHabitaciones = 2;
    	//System.out.println("KAS: "+nPlanta+">="+plantas.length);
		if (plantas == null || nPlanta < 0 || nPlanta >= plantas.length || plantas[nPlanta] != null) return false;
    	//System.out.println("BLE "+this.plantas.length);
		plantas[nPlanta] = new Habitacion[nHabitaciones];
		return true;
	}
	//Construye una habitacion
	public boolean construyeHabitacion(int nPlanta, int nHabitacion){
		if (nPlanta < 0 || plantas == null || nPlanta >= plantas.length || plantas[nPlanta] == null || nHabitacion < 0 || nHabitacion >= plantas[nPlanta].length || plantas[nPlanta][nHabitacion] != null) return false;
		plantas[nPlanta][nHabitacion] = new Habitacion(nPlanta, nHabitacion);
		return true;
	}
	//Anade un paciente
	public boolean ingreso(Paciente paciente){
		if (plantas == null) return false;
		for (int i = 0; i<plantas.length;i++){
			if (plantas[i] != null){
				for (int j = 0; j<plantas[i].length; j++){
					if (plantas[i][j] != null && plantas[i][j].disponible()){
						if(!plantas[i][j].ingreso(paciente))return false;
						paciente.confirmacion(this);
						return true;
					}
				}
			}
		}
		return false;
	}
	public boolean ingresoRapido(int nPlanta, Paciente paciente) {
		if (nPlanta > 0 && paciente != null && plantas != null && plantas[nPlanta] != null){
			for (int i = 0; i < plantas[nPlanta].length;i++){
				if (plantas[nPlanta][i] != null && plantas[nPlanta][i].disponible()){
					if (plantas[nPlanta][i].ingreso(paciente)) {
						paciente.confirmacion(this);
						return true;
					}
				}
			}
			return false;
		} else return false;
	}
	//Elimina un paciente
	public boolean alta(Paciente paciente){
		if (plantas == null) return false;
		for (int i = 0; i<plantas.length;i++){
			if (plantas[i] != null){
				for (int j = 0; j<plantas[i].length; j++){
					if (plantas[i][j] != null && plantas[i][j].estaIngresado(paciente)){
						if (!plantas[i][j].alta(paciente)) return false;
						paciente.altaMedica(this);
						return true;
					}
				}
			}
		}
		return false;
	}
	//Comprueba si esta ingresado
	public boolean estaIngresado(Paciente paciente){
		if (plantas == null) return false;
		for (int i = 0; i<plantas.length;i++){
			if (plantas[i] != null){
				for (int j = 0; j<plantas[i].length; j++){
					if (plantas[i][j] != null && plantas[i][j].estaIngresado(paciente)){
						return true;
					}
				}
			}
		}
		return false;
	}
	public Datos consulta(Paciente paciente) {
		Datos datos = null;
		if (plantas == null) return datos;
		for (int i = 0; i<plantas.length;i++){
			if (plantas[i] != null){
				for (int j = 0; j<plantas[i].length; j++){
					if (plantas[i][j] != null && plantas[i][j].estaIngresado(paciente)){
						return plantas[i][j].consulta(paciente);
					}
				}
			}
		}
		return datos;
	}
	//Dice las camas disponibles
	public int camasDisponibles() {
		int cont = 0;
		if (plantas == null) return cont;
		for (int i = 0; i<plantas.length;i++){
			if (plantas[i] != null){
				for (int j = 0; j<plantas[i].length; j++){
					if (plantas[i][j] != null && plantas[i][j].disponible()){
						Paciente[] camas = plantas[i][j].getCamas();
						for (int k = 0; k < camas.length; k++){
							if (camas[k] == null) cont++;
						}
					}
				}
			}
		}
		return cont;
	}
	//Dice las habitaciones disponibles
	public int numeroHabitaciones(){
		int cont = 0;
		if (plantas == null) return cont;
		for (int i = 0; i<plantas.length;i++){
			if (plantas[i] != null){
				cont += (plantas[i].length);
			}
		}
		return cont;
	}
	public String getNombre(){
		return nombre;
	}
	public Habitacion[][] getPlantas(){
		return plantas;
	}
}
