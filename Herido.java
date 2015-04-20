
public class Herido extends Paciente{
	private String nombre;
	private Herida[] lesiones;
	public Herido(String nombre, int nSintomas, Herida[] lesiones) {
		super(nSintomas);
		if (nombre != null && lesiones != null){
			this.nombre = nombre;
			this.lesiones = lesiones;
		}
	}
	public int agregaLesion(Herida l){
		int presente = -1;
		if (lesiones != null && l != null){
			for (int i =0; i < lesiones.length && presente == -1; i++){
				if (lesiones[i] != null){
					if (lesiones[i].getLesion() != null && l.getLesion() != null){
						 if(lesiones[i].getLesion().compareTo(l.getLesion()) == 0) presente = i;
					} /*else {
						if (lesiones[i].getGravedad() == l.getGravedad()) presente = i;
					}*/
				}
			}
			if (presente != -1) {
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
	            }
	        }
			return moda;
		} else return 0;
	}
	public boolean paseo(int r){
		int suma = 0, media = 0;
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
		if (lesiones != null && s != null){
			for (int i = 0; i<lesiones.length && pos < 0;i++){
				if (lesiones[i] != null) {
					if (lesiones[i].getLesion() != null && lesiones[i].getLesion().compareTo(s.toLowerCase())==0) pos = i;
				}
			}
			if (pos >= 0) {
				if (lesiones[pos].sana()) {
					if (lesiones[pos].getGravedad() == 0) lesiones[pos] = null;
				} else lesiones[pos] = null;
				return s;
			} else {
				String sintomas[] = getSintomas();
				for (int i = 0; i<sintomas.length;i++){
					if (sintomas[i] != null && sintomas[i].compareTo(s.toLowerCase()) == 0) return sintomas[i];
				}
				return "";
			}
		} else return "";
	}
	private static void invertArray(char a[],int n){ 
		int j=n-1,i; 
		char aux; 
		for(i=0;i<=(n-1)/2;i++){ 
			aux=a[j]; 
			a[j]=a[i]; 
			a[i]=aux; 
			j--; 
		} 
	} 
	private char[] convertir_base_3(int terna) {
		int c=0,act=0; 
		char dec[]; 
		dec=new char[100]; 
		do{ 
			act=terna%3; 
			terna=terna/3; 
			switch(act){ 
				case 0: dec[c]='0';break; 
				case 1: dec[c]='1';break; 
				case 2: dec[c]='2';break; 
			}	
			c++; 
		}while(terna>0); 
		dec[c]=0; 
		invertArray(dec,c);
		return dec;
	} 
	public boolean altaVoluntaria(){
		int suma = 0, cont = 0;
		char[] ternario;
		if (lesiones != null) {
			if (lesiones.length == 0) {
				//Hospital.alta?
				return true;
			} else if (lesiones.length > 0) {
				for (int i = 0; i<lesiones.length;i++){
					if (lesiones[i] != null) suma += lesiones[i].getGravedad();
				}
				ternario = convertir_base_3(suma);
				for (int i = 0; i<ternario.length;i++){
					if (ternario[i] == 0 || ternario[i] == 1) cont++;
				}
				if (cont > 2){
					//Alta en hospital
					//Actualizar datos? (Creo que se puede usar altaMedica)
					return true;
				} else return false;
			} else return false;
		} else return false;
	}
	public void altaMedica(Hospital h){
		//Despues de hospital
	}
	public boolean consulta(Hospital h){
		//Despues de hospital
		return true;
	}
	public void confirmacion(Clinica h){
		//Referencia a super?
	}
	public String getNombre(){
		return nombre;
	}
	public Herida[] getLesiones(){
		return lesiones;
	}
}
