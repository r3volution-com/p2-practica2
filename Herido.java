// DNI 77842527 GONZALEZ ALVARADO, MARIO
public class Herido extends Paciente{
	private String nombre;
	private Herida[] lesiones;
	public Herido(String nombre, int nSintomas, Herida[] lesiones) {
		super(nSintomas);
		if (nombre != null && lesiones != null){
			this.nombre = nombre;
			this.lesiones = new Herida[lesiones.length];
			for (int i=0;i<lesiones.length;i++){
				if (lesiones[i] != null){
					this.lesiones[i]= new Herida(lesiones[i].getLesion(), lesiones[i].getGravedad());
				}
			}
			//this.lesiones = lesiones;
		}
	}
	public int agregaLesion(Herida l){
		int presente = -1;
		if (lesiones != null && l != null){
			for (int i =0; i < lesiones.length && presente == -1; i++){
				if (lesiones[i] != null){
					//System.out.println(lesiones[i].getLesion());
					//System.out.println(l.getLesion());
					if (l.getLesion() != null){
						 if(lesiones[i].getLesion() != null && lesiones[i].getLesion().equalsIgnoreCase(l.getLesion())) presente = i;
					} else {
						if (lesiones[i].getLesion() == null) presente = i;
					}
				}
			}
			if (presente != -1) {
				//System.out.println("2: "+lesiones[presente].getGravedad());
				lesiones[presente].agrava();
				return 0;
				/*if(lesiones[presente].agrava()){
					return 0;
				} else return -1;*/
			} else {
				for (int i =0; i < lesiones.length; i++){
					if (lesiones[i] == null){
						lesiones[i] = l;
						return 1;
					}
				}
				return -1;
			}
		} return -1;
	}
	public void empeora (int i) {
		Herida[] alt;
		if (lesiones != null && i > 0) {
			alt = new Herida[lesiones.length+i];
			for (int j = 0; j<lesiones.length;j++){
				alt[j] = lesiones[j];
			}
			if (!estaIngresado()) lesiones = alt;
		}
	}
	public int gravedad() {
		if (lesiones != null){
			int arraygravedad[] = new int[lesiones.length];
			for (int i = 0; i<lesiones.length;i++){
				if (lesiones[i] != null) {
					arraygravedad[i] = lesiones[i].getGravedad();
					//System.out.println(lesiones[i].getLesion()+": "+lesiones[i].getGravedad());
				}
			}
			int frecuenciaTemp = 0, frecuenciaModa = 0, moda = -1; 

	        for (int i=0; i < arraygravedad.length-1; i++){
	            frecuenciaTemp = 1;
	            for(int j = i+1 ; j< arraygravedad.length; j++){
	                if(arraygravedad[i] != 0 && arraygravedad[i] == arraygravedad[j])
	                    frecuenciaTemp ++;                
	            }
	            if(frecuenciaTemp > frecuenciaModa){
	                frecuenciaModa = frecuenciaTemp;
	                moda = arraygravedad[i];
	                //System.out.println("2: "+i);
	            }
	        }
			return moda;
		} else return 0;
	}
	public boolean paseo(int r){
		double suma = 0, media = 0;
		if (lesiones != null && estaIngresado()){ //Esta ingresado en boxes?
			for (int i = 0; i<lesiones.length;i++){
				if (lesiones[i] != null && lesiones[i].getLesion()!= null) suma += lesiones[i].getLesion().length();
				else suma += 0;
			}
			media = suma / lesiones.length;
			if (media > r) return false;
			else return true;
		} else return false;
	}
	public String cura(String s){
		int pos = -1;
		if (lesiones != null){
			for (int i = 0; i<lesiones.length && pos < 0;i++){
				if (lesiones[i] != null) {
					if (lesiones[i].getLesion() != null) {
						 if(lesiones[i].getLesion().equalsIgnoreCase(s)) pos = i;
					} else {
						 if(s == null) pos = i;
					}
				}
			}
			if (pos >= 0) {
				if (lesiones[pos].sana()) {
					if (lesiones[pos].getGravedad() == 0) lesiones[pos] = null;
				} else lesiones[pos] = null;
				return s;
			} else {
				if (padeceSintoma(s)) {
					return cura(s);
				}
				/*String sintomas[] = getSintomas();
				for (int i = 0; i<sintomas.length;i++){
					if (sintomas[i] != null && sintomas[i].equalsIgnoreCase(s)) {
						cura(sintomas[i]);
						return sintomas[i];
					}
				}*/
				return "";
			}
		} else return "";
	}
	public boolean altaVoluntaria(){
		int suma = 0, resto = 0, cont = 0, cont2=0;
		char[] ternario;
		boolean todonull = true;
		Hospital h = (Hospital) super.getClinicaIngreso();
		if (lesiones != null && h != null && (super.estaIngresado() || h.estaIngresado(this))) {
			for (int i=0;i<lesiones.length;i++) {
				if (lesiones[i] != null) todonull = false;
			}
			if (todonull== true) {
				if (h.alta(this)) return true;
				else return false;
			} else {
				for (int i = 0; i<lesiones.length;i++){
					if (lesiones[i] != null) suma += lesiones[i].getGravedad();
				}
				//System.out.println("Suma: "+suma);
				while (suma > 0){
					resto = suma % 3;
					switch(resto) {
						case 0:
							cont++;
							break;
						case 1:
							cont++;
							break;
						case 2:
							cont2++;
							break;
					}
					suma = suma / 3;
				}
				if (cont > cont2){
					Clinica c = super.getClinicaIngreso();
					if (c.alta(this)) return true;
					else return false;
					//Actualizar datos? (Creo que se puede usar altaMedica)
					//return true;
				} else return false;
			}
		} else return false;
	}
	public void altaMedica(Hospital h){
		if (h!= null && lesiones != null) {
			Hospital h2 = (Hospital) super.getClinicaIngreso();
			if (h2 != null && h2.equals(h)/* && h.estaIngresado(this)*/){
				super.altaMedica(h);
			}
		}
	}
	public void confirmacion(Clinica h){
		if (h!=null/* && h.estaIngresado(this)*/){
			super.setClinicaIngreso(h);
			super.setDatosIngreso(nombre+";"+h.getNombre());
		}
	}
	public boolean consulta(Hospital h){
		if (h!=null && super.estaIngresado() == false) {
			if(h.ingresoUrgente(this)){
				//System.out.println("Ble");
				confirmacion(h);
				return true;
			} else return false;
		} else return false;
	}
	public String getNombre(){
		return nombre;
	}
	public Herida[] getLesiones(){
		return lesiones;
	}
}
