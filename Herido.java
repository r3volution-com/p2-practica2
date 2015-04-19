
public class Herido extends Paciente{
	String nombre;
	Herida[] lesiones;
	public Herido(String nombre, int nSintomas, Herida[] lesiones) {
		super(nSintomas);
		if (nombre != null){
			this.nombre = nombre;
			this.lesiones = lesiones;
		}
	}
	public int agregaLesion(Herida l){
		int presente = 0;
		if (l != null){
			for (int i =0; i < lesiones.length && presente == 0; i++){
				if (lesiones[i].lesion != null && lesiones[i].lesion.compareTo(l.lesion) == 0){
					presente = i;
				}
			}
			if (presente != 0) {
				if(lesiones[presente].agrava()){
					return 0;
				} else return -2;
			} else {
				for (int i =0; i < lesiones.length; i++){
					if (lesiones[i] == null){
						lesiones[i] = l;
						return 1;
					}
				}
				return -1;
			}
		} return -2;
	}
	public void empeora (int i) {
		if (i > 0) {
			//Comprobar que no esta ingresado
			lesiones = new Herida[i];
		}
	}
	public int gravedad() {
		int arraygravedad[] = new int[lesiones.length];
		for (int i = 0; i<lesiones.length;i++){
			arraygravedad[i] = lesiones[i].getGravedad();
		}
		int frecuenciaTemp, frecuenciaModa = 0, moda = -1; 
        
        for (int i=0; i < arraygravedad.length-1; i++){
            frecuenciaTemp = 1;
            for(int j = i+1 ; j< arraygravedad.length; j++){
                if(arraygravedad[i] == arraygravedad[j])
                    frecuenciaTemp ++;                
            }
            if(frecuenciaTemp > frecuenciaModa){
                frecuenciaModa = frecuenciaTemp;
                moda = arraygravedad[i];
            }
        }
       // System.out.println("la moda es: " + moda + "nsu frecuencia: " + frecuenciaModa);
		return moda;
	}
	public boolean paseo(int r){
		int suma = 0, media = 0;
		//Comprueba si esta ingresado
		for (int i = 0; i<lesiones.length;i++){
			suma += lesiones[i].getLesion().length();
		}
		media = suma / lesiones.length;
		if (media > r) return false;
		else return true;
	}
	public String cura(String s){
		int pos = -1;
		if (s != null){
			for (int i = 0; i<lesiones.length && pos < 0;i++){
				if (lesiones[i].getLesion().compareTo(s.toLowerCase())==0) pos = i;
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
	public boolean altaVoluntaria(){
		if (lesiones != null && lesiones.length > 0) {
			return true;
		} else return false;
	}
	public void altaMedica(Hospital h){
		
	}
	public boolean consulta(Hospital h){
		return true;
	}
	public void confirmacion(Clinica h){
		
	}
	public String getNombre(){
		return nombre;
	}
	public Herida[] getLesiones(){
		return lesiones;
	}
}
