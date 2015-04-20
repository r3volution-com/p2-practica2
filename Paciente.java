// DNI 77842527 GONZALEZ ALVARADO, MARIO

/**
 *
 * @author mga101
 */
public class Paciente {
    private String[] sintomas;
    private Clinica clinicaIngreso;
    private String datosIngreso;
    //Constructor
    public Paciente(int numSintomas) {
        if (numSintomas <= 0) sintomas = new String[3];
        else sintomas = new String[numSintomas];
    }
    //Ordena los sintomas
    private String[] ordenaSintomas(String[] sintomas){
    	String[] sintomasordenados = new String[sintomas.length];
    	String palabract = "";
    	int posact = 0;
		for (int i = 0; i < sintomas.length;i++){
           	for (int k = 0; k<sintomas.length; k++){
           		if (sintomas[k] != null && palabract.compareTo(sintomas[k])	< 0) palabract = sintomas[k];
           	}
   			for (int j = 0; j < sintomas.length;j++){
   				if (sintomas[j] != null && palabract.compareTo(sintomas[j])>=0) {
   					palabract = sintomas[j];
   					posact = j;
   				}
    		}
			sintomasordenados[i] = sintomas[posact];
   			sintomas[posact] = null;
		}
		return sintomasordenados;
    }
    //Anade un sintoma
    public boolean agregaSintoma(String nuevoSintoma) {
    	if (sintomas[sintomas.length-1] == null && nuevoSintoma != null) {
        	for (int j = 0; j<sintomas.length;j++){
        		if (sintomas[j] != null && sintomas[j].compareTo(nuevoSintoma.toLowerCase()) == 0) return false;
        	}
    		sintomas[sintomas.length-1] = nuevoSintoma.toLowerCase();
   			sintomas = ordenaSintomas(sintomas);
   			return true;
    	} else return false;
    }
    //Calcula la gravedad
    public double estimaGravedad() {
        double gravedad = 0, vocales = 0, consonantes = 0;
        for (int i=0;i<sintomas.length;i++){
        	for(int x=0;sintomas[i] != null && x<sintomas[i].length();x++) {
        		if(sintomas[i].charAt(x)>='a' && sintomas[i].charAt(x)<='z'){
        			if ((sintomas[i].charAt(x)=='a') || (sintomas[i].charAt(x)=='e') || (sintomas[i].charAt(x)=='i') || (sintomas[i].charAt(x)=='o') || (sintomas[i].charAt(x)=='u')) vocales++;
        			else consonantes++;
        		}
        	}
        }
        gravedad = consonantes/vocales;
        return gravedad;
    }
    //Comprueba si el enfermo padece un sintoma
    public boolean padeceSintoma(String sintoma) {
    	for (int i = 0;i<sintomas.length;i++){
    		if (sintomas[i] != null && sintoma != null && sintomas[i].compareTo(sintoma.toLowerCase())==0) return true;
    	}
        return false;
    }
    //Cura(Elimina) una enfermedad
    public String cura(String sintoma){
        for (int i = 0; i<sintomas.length; i++){
        	if (sintoma != null && sintomas[i] != null && sintomas[i].compareTo(sintoma.toLowerCase()) == 0) {
        		sintomas[i] = null;
        		sintomas = ordenaSintomas(sintomas);
        		return sintoma;
        	}
        }
        return "";
    }
    //Da el alta borrando ciertos datos
    public void altaMedica(Clinica c) {
    	if (c!= null && c.equals(clinicaIngreso)){
    		if(!c.estaIngresado(this)){
    			clinicaIngreso = null;
    			datosIngreso = "";
    		}
    	}
    }
    //Ingresa un paciente
    public boolean ingreso(Clinica c) { 
    	//System.out.println("ASDF: "+estimaGravedad());
    	if (clinicaIngreso != null && estimaGravedad() > 0){
    		if (c.ingreso(this)) {
    			clinicaIngreso = c;
    			Datos dato = c.consulta(this);
    			datosIngreso = clinicaIngreso.getNombre()+dato.getHabitacion()+dato.getPlanta();
    			return true;
    		}
    	}
        return false;
    }
    //Confirma si un paciente esta ingresado
    public void confirmacion(Clinica c) {
    	if (c.estaIngresado(this)){
    		clinicaIngreso = c;
			Datos dato = c.consulta(this);
			datosIngreso = clinicaIngreso.getNombre()+dato.getHabitacion()+dato.getPlanta();
    	}
    }
    //Dice si esta ingresado
    public boolean estaIngresado() {
        if (clinicaIngreso != null) return true;
        else return false;
    }
    public String[] getSintomas(){
        return sintomas;
    }
    public String getDatosIngreso(){
        return datosIngreso;
    }
}
