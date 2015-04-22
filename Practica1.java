/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


//import java.util.Arrays;

/**
 *
 * @author mga101
 */
public class Practica1 {
	public static void bleh(Herida[] heridas){
		//String[] pepe = new String[heridas.length];
		for (int i = 0; i < heridas.length; i++) if (heridas[i] != null) System.out.println(heridas[i].getLesion());
		//return pepe;
	}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Paciente paciente = new Paciente(3);
        Paciente paciente2 = new Paciente(3);
        Paciente paciente3 = new Paciente(3);

        Habitacion habitaciones = new Habitacion(0,0);
        

        System.out.println("Pacientes\n");
        if (!paciente.agregaSintoma("Fiebre")) System.out.println("Fail fiebre");
        //System.out.println(Arrays.toString(paciente.getSintomas()));
        if (!paciente.agregaSintoma("Vomitos")) System.out.println("Fail vomitos");
        //System.out.println(Arrays.toString(paciente.getSintomas()));
        if (!paciente.agregaSintoma("Diarrea")) System.out.println("Fail diarrea");
        //System.out.println(Arrays.toString(paciente.getSintomas())+"\n");
        
        System.out.println(paciente.estimaGravedad()+"\n");
        
        if (paciente.padeceSintoma("Fiebre")) System.out.println("Padece fiebre");
        else System.out.println("No padece fiebre");
        if (paciente.padeceSintoma("Hemorragia")) System.out.println("Padece hemorragia\n");
        else System.out.println("No padece hemorragia\n");
        System.out.println(paciente.cura("Fiebre")+" borrado");
        //System.out.println(Arrays.toString(paciente.getSintomas())+"\n");
        
        System.out.println("Habitaciones\n");

        System.out.println(habitaciones.ingreso(paciente));
        System.out.println(habitaciones.ingreso(paciente2)+"\n");

        System.out.println(habitaciones.estaIngresado(paciente));
        System.out.println(habitaciones.estaIngresado(paciente3)+"\n");

        Datos dato = habitaciones.consulta(paciente);
        if (dato != null) System.out.println(dato.getPlanta()+" - "+dato.getHabitacion()+" - "+dato.getCama()+"\n");
        
        System.out.println(habitaciones.alta(paciente));
        System.out.println(habitaciones.alta(paciente3)+"\n");

        Datos dato2 = habitaciones.consulta(paciente);
        if (dato2 != null) System.out.println(dato2.getPlanta()+" - "+dato2.getHabitacion()+" - "+dato2.getCama()+"\n");

        Clinica clinica = new Clinica("Pollos", 0);
       // Clinica clinica2 = new Clinica("Polles", 3);
        System.out.println("Clinica\n");
        System.out.println(clinica.construyePlanta(1, 2));
        System.out.println(clinica.construyePlanta(6, 1)+"\n");
        System.out.println(clinica.construyeHabitacion(1, 1));
        System.out.println(clinica.construyeHabitacion(1, 6));
        System.out.println(clinica.construyeHabitacion(6, 1)+"\n");
        System.out.println(clinica.ingreso(paciente));
        System.out.println(clinica.ingreso(paciente2)+"\n");
        System.out.println(clinica.alta(paciente));
        System.out.println(clinica.alta(paciente)+"\n");
        System.out.println(clinica.estaIngresado(paciente));
        System.out.println(clinica.estaIngresado(paciente2)+"\n");

        Datos dato3 = clinica.consulta(paciente2);
        if (dato3 != null) System.out.println(dato3.getPlanta()+" - "+dato3.getHabitacion()+" - "+dato3.getCama()+"\n");
        
        System.out.println(clinica.camasDisponibles());
        System.out.println(clinica.numeroHabitaciones());

        System.out.println("--HERIDA-- \n"); 
    	Herida herida[] = new Herida[2];
    	herida[0] = new Herida("Hematoma", 5);
    	herida[1] = new Herida(null, -2);
    	Herida herida2[] = new Herida[5];
    	herida2[0] = new Herida("Derrame cerebral", 8);
    	herida2[1] = new Herida(null, -1);
    	for (int i = 0; i<herida.length; i++){
    		System.out.println("\n-ITEM "+i+"-");
	    	System.out.println("Lesion original: "+herida[i].getGravedad());
	    	System.out.println(herida[i].sana());
	    	System.out.println("Lesion sana: "+herida[i].getGravedad());
	    	System.out.println(herida[i].agrava());
	    	System.out.println("Lesion agrava: "+herida[i].getGravedad());
	    	System.out.println(herida[i].getLesion());
    	}
    	System.out.println("\n --HERIDO-- \n");
    	Herido herido[] = new Herido[3];
    	herido[0] = new Herido("Pepe", 5, herida);
    	herido[1] = new Herido("Paco", 3, herida2);
    	//System.out.println(herido[1].ingreso(clinica));
    	herido[2] = new Herido(null, -3, null);
    	for (int i = 0; i<herido.length; i++){
    		System.out.println("\n-ITEM "+i+"-");
    		System.out.println(herido[i].agregaLesion(herida[0]));
    		System.out.println(herido[i].agregaLesion(herida[1]));
    		System.out.println(herido[i].agregaLesion(herida2[0]));
    		System.out.println(herido[i].agregaLesion(herida2[1]));
    		System.out.println(herido[i].agregaLesion(null));
    		if (herido[i].getLesiones() != null) System.out.println("Tam: "+herido[i].getLesiones().length);
    		herido[i].empeora(0);
    		if (herido[i].getLesiones() != null) System.out.println("Tam: "+herido[i].getLesiones().length);
    		herido[i].empeora(2);
    		if (herido[i].getLesiones() != null) System.out.println("Tam: "+herido[i].getLesiones().length);
    		System.out.println("Gravedad: "+herido[i].gravedad());
    		System.out.println("Paseo: "+herido[i].paseo(0)); //Revisar 
    		System.out.println("Paseo: "+herido[i].paseo(10)); //Revisar
    		System.out.println("Cura: "+herido[i].cura("Derrame cerebral")); //Revisar 
    		System.out.println("Cura: "+herido[i].cura("Derrame cerebral")); //Revisar
    		System.out.println("Cura: "+herido[i].cura("Cancer")); //Revisar
    		System.out.println("Cura: "+herido[i].cura(null)); //Revisar
    		System.out.println(herido[i].altaVoluntaria());
    	}
    	System.out.println("\n --Box-- \n");
    	Box box[] = new Box[3];
    	box[0] = new Box(0, 1);
    	box[1] = new Box(1, 0);
    	box[2] = new Box(2, -1);
    	for (int i = 0; i<box.length; i++){
    		System.out.println("\n-ITEM "+i+"-");
    		System.out.println("Disponible: "+box[i].disponible());
    		System.out.println("Plazas: "+box[i].plazas());
    		System.out.println("Ingreso "+i+": "+box[i].ingreso(herido[0]));
    		System.out.println("Ingreso "+i+": "+box[i].ingreso(herido[1]));
    		System.out.println("Ingreso "+i+": "+box[i].ingreso(null));
    		System.out.println("Esta Ingreso "+i+": "+box[i].estaIngresado(herido[0]));
    		System.out.println("Esta Ingreso "+i+": "+box[i].estaIngresado(herido[1]));
    		System.out.println("Esta Ingreso "+i+": "+box[i].estaIngresado(null));
    		if (box[i].visita(0) != null) System.out.println("Puedes visitarlo");
    		else System.out.println("No puedes visitarlo");
    		if (box[i].visita(1) != null) System.out.println("Puedes visitarlo");
    		else System.out.println("No puedes visitarlo");
    		System.out.println(box[i].alta(herido[0]));
    		System.out.println(box[i].alta(herido[1]));
    		System.out.println(box[i].alta(null));
    		System.out.println("Esta Ingreso "+i+": "+box[i].estaIngresado(herido[0]));
    		System.out.println("Esta Ingreso "+i+": "+box[i].estaIngresado(herido[1]));
    		System.out.println("Esta Ingreso "+i+": "+box[i].estaIngresado(null));
    	}
    	System.out.println("\n --Hospitales-- \n");
    	Hospital hospital[] = new Hospital[2];
    	hospital[0] = new Hospital("Quiron", 1, 1, 1, 1);
    	hospital[1] = new Hospital(null, -1, 0, -1, 0);
    	for (int i = 0; i<hospital.length; i++){
    		System.out.println("\n-ITEM "+i+"-");
    		System.out.println("Ingreso: "+hospital[i].ingreso(herido[0]));
    		System.out.println("Ingreso: "+hospital[i].ingreso(herido[0]));
    		System.out.println("Ingreso: "+hospital[i].ingreso(herido[1]));
    		System.out.println("Ingreso: "+hospital[i].ingreso(herido[1]));
    		System.out.println("Ingreso: "+hospital[i].ingreso(null));
    		System.out.println("IngresoUrgente: "+hospital[i].ingresoUrgente(herido[0]));
    		System.out.println("IngresoUrgente: "+hospital[i].ingresoUrgente(herido[0]));
    		System.out.println("IngresoUrgente: "+hospital[i].ingresoUrgente(herido[1]));
    		System.out.println("IngresoUrgente: "+hospital[i].ingresoUrgente(herido[1]));
    		System.out.println("IngresoUrgente: "+hospital[i].ingresoUrgente(null));
    		System.out.println("Consulta: "+hospital[i].consulta(herido[0]));
    		System.out.println("Consulta: "+hospital[i].consulta(herido[1]));
    		System.out.println("Alta: "+hospital[i].alta(herido[0]));
    		System.out.println("Alta: "+hospital[i].alta(herido[0]));
    		System.out.println("Alta: "+hospital[i].alta(herido[1]));
    		System.out.println("Alta: "+hospital[i].alta(herido[1]));
    		System.out.println("Alta: "+hospital[i].alta(null));
    		System.out.println("Consulta: "+hospital[i].consulta(herido[0]));
    		System.out.println("Consulta: "+hospital[i].consulta(herido[1]));
    		System.out.println("Consulta: "+hospital[i].consulta(null));
    		System.out.println("Consulta: "+hospital[i].plazasLibre());
    	}
    }
    
}