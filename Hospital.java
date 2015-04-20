
public class Hospital extends Clinica {
	private Box[] boxes;
	public Hospital(String nombre, int nPlantas, int nHabitaciones, int nBoxes, int nPlazas){	
		super(nombre, nPlantas);
		if (nHabitaciones <= 0) nHabitaciones = 2;
		if (nBoxes <= 0) nBoxes = 2;
		if (nPlazas <= 0) nPlazas = 2;
	}
	/*public boolean ingreso (Paciente p){
		
	}
	public boolean ingresoUrgente(Herido p){
		
	}
	public boolean alta(Paciente p){
		
	}
	public Datos consulta(Herido p){
		
	}
	public int plazasLibrer() {
		
	}
	public boolean traslado(String n, Hospital d){
		
	}
	public boolean traslado(Herido h){
		
	}
	public double comparaGravedad(){
		
	}
	public double coeficienteCurtosis(){
		
	}
	public double mediaGeometrica(){
		
	}*/
}
