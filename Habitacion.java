// DNI 77842527 GONZALEZ ALVARADO, MARIO

/**
 *
 * @author mga101
 */
public class Habitacion {
    private Paciente[] camas = new Paciente[2];
    private int planta;
    private int habitacion;
    //Constructor
    public Habitacion(int planta, int habitacion) {
        this.planta = planta;
        this.habitacion = habitacion;
    }
    //Comprueba si hay habitaciones disponibles
    public boolean disponible() {
    	for (int i = 0; i<camas.length;i++){
    		if (camas[i] == null) return true;
    	}
    	return true;
    }
    //Anade un paciente a la habitacion
    public boolean ingreso(Paciente paciente){
    	if (disponible() && !estaIngresado(paciente)){
    		for (int i = 0; i<camas.length;i++){
        		if (camas[i] == null) {
        			camas[i] = paciente;
            		return true;
        		}
        	}
    		return false;
    	}else return false;
    }
    //Elimina un paciente de la habitacion
    public boolean alta(Paciente paciente){
    	for (int i = 0; i<camas.length;i++){
    		if (paciente != null && paciente.equals(camas[i])){
    			camas[i] = null;
    			return true;
    		}
    	}
    	return false;
    }
    //Devuelve los datos de un paciente
    public Datos consulta(Paciente paciente) {
    	Datos dato = null;
    	for (int i = 0; i < camas.length; i++){
    		if (paciente != null && paciente.equals(camas[i])) dato = new Datos(planta, habitacion, i);
    	}
    	return dato;
    }
    //Comprueba si un paciente esta ingresado
    public boolean estaIngresado(Paciente paciente){
    	for (int i = 0; i<camas.length;i++){
    		if (paciente != null && paciente.equals(camas[i])){
    			return true;
    		}
    	}
    	return false;
    }
    public Paciente[] getCamas(){
    	return camas;
    }
    
}
