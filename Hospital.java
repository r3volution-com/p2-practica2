
public class Hospital extends Clinica {
	public Hospital(String nombre, int nPlantas, int nHabitaciones, int nBoxes, int nPlazas){	
		super(nombre, nPlantas);
		if (nHabitaciones <= 0) nHabitaciones = 2;
		if (nBoxes <= 0) nBoxes = 2;
		if (nPlazas <= 0) nPlazas = 2;
	}
}
