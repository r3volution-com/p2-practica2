
public class Hospital extends Clinica {
	private Box[] boxes;
	public Hospital(String nombre, int nPlantas, int nHabitaciones, int nBoxes, int nPlazas){
		super(nombre, nPlantas);
		if (nombre != null){ 
			//if (nPlantas <= 0) nPlantas = 1;
			if (nHabitaciones <= 0) nHabitaciones = 2;
			if (nBoxes <= 0) nBoxes = 3;
			if (nPlazas <= 0) nPlazas = 3;
			for (int i=0;i<nPlantas;i++){
				super.construyePlanta(nPlantas, nHabitaciones);
				for (int j=0;j<nHabitaciones;j++){
					super.construyeHabitacion(j, i);
				}
			}
			boxes = new Box[nBoxes];
			for (int j=0;j<nBoxes;j++){
				boxes[j]= new Box(j, nPlazas);
			}
		}
		
	}
	public boolean ingreso (Paciente p){
		if (p!=null && boxes != null){
			int disponible = -1;
			if (p instanceof Herido) {
				Herido h = (Herido)p;
				for (int i = 0; i<boxes.length && disponible == -1;i++){
					if (boxes[i].disponible()>0) disponible = i;
				}
				if (disponible != -1) {
					if (boxes[disponible].estaIngresado(h) == -1 && boxes[disponible].ingreso(h) != -1){
						h.confirmacion(this);
						return true;
					} else return false;
				} else {
					if (super.ingreso(p))
					return true;
					else return false;
				}
			} else {
				if (super.ingreso(p))
				return true;
				else return false;
			}
		} else return false;
	}
	public boolean ingresoUrgente(Herido p){
		int disponible = -1;
		int menorgravedad = 10, idmgi=0, idmgj=0;
		//int sumatorio = 0;
		if (boxes != null && p != null){
			for (int i = 0; i<boxes.length && disponible == -1;i++){
				if (boxes[i] == null) disponible = i;
			}
			if (disponible != -1) {
				if (boxes[disponible].estaIngresado(p) != -1 && boxes[disponible].ingreso(p) != -1){
					p.confirmacion(this);
					return true;
				} else return false;
			} else {
				for(int i = 0; i<boxes.length;i++){
					for (int j=0;j<boxes[i].plazas();j++){
						/*sumatorio=0;
						for (int k=0;k<boxes[i].visita(j).getLesiones().length;k++){
							sumatorio+=boxes[i].visita(j).getLesiones()[k].getGravedad();
						}*/
						if (boxes[i] != null && boxes[i].visita(j) != null && menorgravedad > boxes[i].visita(j).gravedad()){
							menorgravedad=boxes[i].visita(j).gravedad();
							idmgi=i;
							idmgj=j;
						}
					}
				}
				if (menorgravedad<p.gravedad()){
					Herido po = boxes[idmgi].visita(idmgj);
					if(boxes[disponible].estaIngresado(p) != -1 && boxes[idmgi].alta(po)){
						p.confirmacion(this);
						return true;
					}
					else return false;
				} else return false;
			}
		}else return false;
	}
	public boolean alta(Paciente p){
		if (boxes!= null && p != null){
			if(p.estaIngresado()){
				p.altaMedica(this);
				return true;
			}else {
				//Comprobar si es herido?
				return false;
			}
		} else return false;
	}
	public Datos consulta(Herido p){
		if (boxes!= null && p != null){
			Datos dato = null;
			for (int i=0;i<boxes.length;i++){
				if(boxes[i].estaIngresado(p) != -1) {
					dato = new Datos(-1, boxes[i].getNumero(), 0);
				}else return dato;
			}
			return dato;
			
		} else return null;
	}
	public int plazasLibre() {
		int suma=0;
		if (boxes!=null){
			for (int i=0;i<boxes.length;i++){
				suma += boxes[i].disponible();
			}
		}
		return suma;
	}
	/*public boolean traslado(String n, Hospital d){
		
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
