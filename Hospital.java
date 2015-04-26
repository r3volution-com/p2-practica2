// DNI 77842527 GONZALEZ ALVARADO, MARIO
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
				super.construyePlanta(i, nHabitaciones);
				for (int j=0;j<nHabitaciones;j++){
					super.construyeHabitacion(i, j);
					//System.out.println(super.numeroHabitaciones());
				}
			}
			boxes = new Box[nBoxes];
			for (int j=0;j<nBoxes;j++){
				boxes[j]= new Box(j, nPlazas);
				//System.out.println("4: "+nPlazas);
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
					if (boxes[disponible].estaIngresado(h) == -1 && p.estaIngresado() == false && boxes[disponible].ingreso(h) != -1){
						h.confirmacion(this);
						//System.out.println("1p"+boxes[disponible].plazas());
						return true;
					} else return false;
				} else {
					//System.out.println("2");
					if (super.ingreso(p)){
						return true;
					}
					else return false;
				}
			} else {
				//System.out.println("3");
				if (super.ingreso(p)){
					return true;
				}
				else return false;
			}
		} else return false;
	}
	public boolean ingresoUrgente(Herido p){
		int disponible = -1;
		int menorgravedad = 10, idmgi=0, idmgj=0;
		//int sumatorio = 0;
		if (boxes != null && p != null && p.getNombre() != null){
			for (int i = 0; i<boxes.length && disponible == -1;i++){
				if (boxes[i] != null && boxes[i].disponible() >0) disponible = i;
				//if (boxes[i] != null) System.out.println(boxes[i].plazas()+"a: "+boxes[i].disponible());
			}
			if (disponible != -1) {
				if (boxes[disponible] != null && boxes[disponible].estaIngresado(p) == -1 && boxes[disponible].ingreso(p) != -1){

					p.confirmacion(this);
					return true;
				} else return false;
			} else {
				for(int i = 0; i<boxes.length;i++){
					if (boxes[i] != null){
						for (int j=0;j<boxes[i].plazas();j++){
							//if (boxes[i].visita(j) != null) System.out.println("f"+boxes[i].visita(j).getNombre());
							/*sumatorio=0;
							for (int k=0;k<boxes[i].visita(j).getLesiones().length;k++){
								sumatorio+=boxes[i].visita(j).getLesiones()[k].getGravedad();
							}*/
							if (boxes[i].visita(j) != null && menorgravedad > boxes[i].visita(j).gravedad()){
								menorgravedad=boxes[i].visita(j).gravedad();
								idmgi=i;
								idmgj=j;
							}
						}
					}
				}
				//System.out.println("b "+menorgravedad+" "+p.gravedad());
				if (menorgravedad>p.gravedad()){
					Herido po = boxes[idmgi].visita(idmgj);
					if(boxes[idmgi].estaIngresado(p) == -1 && boxes[idmgi].estaIngresado(po) != -1){
						if (boxes[idmgi].alta(po)){
							po.altaMedica(this);
							if (boxes[idmgi].ingreso(p) != -1){
								p.confirmacion(this);
								return true;
							} else return false;
						} else return false;
					} else return false;
				} else return false;
			}
		}else return false;
	}
	public boolean alta(Paciente p){
		if (boxes!= null && p != null){
			if(p.estaIngresado()){
				if (super.alta(p)){
					p.altaMedica(this);
					return true;
				} else return false;
			}else {
				for (int i = 0; i < boxes.length; i++){
					if (boxes[i] != null && boxes[i].estaIngresado((Herido)p) != -1){
						if (boxes[i].alta((Herido)p)){
							p.altaMedica(this);
							return true;
						}
					}
				}
				//Comprobar si es lesionado?
				return false;
			}
		} else return false;
	}
	public Datos consulta(Herido p){
		if (boxes!= null && p != null){
			Datos dato = null;
			for (int i=0;i<boxes.length;i++){
				if(boxes[i] != null && boxes[i].estaIngresado(p) != -1) {
					dato = new Datos(-1, boxes[i].getNumero(), boxes[i].estaIngresado(p));
					return dato;
				}else return null;
			}
			return null;
			
		} else return null;
	}
	public int plazasLibres() {
		int suma=0;
		if (boxes!=null){
			for (int i=0;i<boxes.length;i++){
				if (boxes[i] != null) suma += boxes[i].disponible();
			}
		}
		return suma;
	}
	public boolean estaIngresado(Herido herido){
		if (boxes == null || herido == null) return false;
		for (int i = 0; i<boxes.length;i++){
			if (boxes[i] != null){
				if (boxes[i].estaIngresado(herido) != -1){
					return true;
				}
			}
		}
		return false;
	}
	public boolean traslado(Herido h){
		if (h != null && boxes != null && this.getPlantas() != null && h.estaIngresado() == false) {
			for (int i = this.getPlantas().length-1; i >=0; i--) {
				if (super.ingresoRapido(i, h)) {
					h.confirmacion(this);
					return true;
				}
			}
			return false;
		} else return false;
	}
	public boolean traslado(String n, Hospital d){
		if (d != null && boxes != null && n != null) {
			boolean tipo = false;
			Herido h = null;
			for (int i=0; i<boxes.length;i++){
				if (boxes[i] != null){
					for (int j=0;j<boxes[i].plazas();j++){
						if (boxes[i].visita(j) != null && boxes[i].visita(j).getNombre() != null){
							if (boxes[i].visita(j).getNombre().equalsIgnoreCase(n)){
								h=boxes[i].visita(j);
								if (boxes[i].alta(h)){
									//h.altaMedica(this);
									//System.out.println("b: "+this.traslado(h));
									if (this.traslado(h))return true;
									else {
										if (boxes[i].ingreso(h) != -1) return false;
									}
								}
							}
						}
					}
				}
			}
		} 
		return false;
	}
	public double comparaGravedad(){
		double max = 0, min = 9;
		double gravedad = 0;
		if (boxes != null){
			for (int i=0;i<boxes.length;i++){
				if (boxes[i]!=null){
					for (int j=0; i<boxes[i].plazas();j++){
						if (boxes[i].visita(j).gravedad()>max){
							max=boxes[i].visita(j).gravedad();
						}
						if (boxes[i].visita(j).gravedad()<min){
							min=boxes[i].visita(j).gravedad();
						}
					}
				}
			}
			if (min!=0 && max != 0){
				gravedad = max/min;
				return gravedad;
			} else
			return 0;
		} else return 0;
	}
	public double gravedadMedia() {
		double media= 0.0, suma = 0, contador = 0;
		if (boxes != null){
			for (int i = 0; i<boxes.length; i++){
				if (boxes[i] != null){
					for (int j = 0; j<boxes[i].plazas();j++){
						//System.out.println(boxes[i].visita(j));
						if (boxes[i].visita(j) != null){
							suma += boxes[i].visita(j).gravedad();
							//System.out.println(boxes[i].visita(j).getNombre());
							contador++;
						}
						//System.out.println(i+" je "+j);
					}
				}
			}
			media = suma/contador;
		}
		return media;
	}
	public double sumatorio(int potencia){
		double suma = 0.0;
		if (boxes != null){
			for (int i = 0; i<boxes.length; i++){
				if (boxes[i] != null){
					for (int j = 0; j<boxes[i].plazas();j++){
						if (boxes[i].visita(j) != null){
							suma += Math.pow((boxes[i].visita(j).gravedad()-gravedadMedia()), potencia);
						}
					}
				}
			}
		}
		return suma;
	}
	public int contador(){
		int contador = 0;
		if (boxes != null){
			for (int i = 0; i<boxes.length; i++){
				if (boxes[i] != null){
					for (int j = 0; j<boxes[i].plazas();j++){
						if (boxes[i].visita(j) != null){
							contador ++;
						}
					}
				}
			}
		}
		return contador;
	}
	public double coeficienteCurtosis(){
		if (contador() > 0){
			double courtois = (sumatorio(4)/(contador()*Math.pow(Math.sqrt((sumatorio(2)/contador())), 4)))-3;
			return courtois;
		} else return 0.0;
	}
	public double mediaGeometrica(){
		
		return 1.0;
	}
	public Box[] getBoxes(){
		return boxes;
	}
}
