/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Arrays;

/**
 *
 * @author mga101
 */
public class Practica1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Paciente paciente = new Paciente(3);
        Paciente paciente2 = new Paciente(3);
        Paciente paciente3 = new Paciente(3);

        Habitacion habitaciones = new Habitacion(3, 3);
        

        System.out.println("Pacientes\n");
        
        if (!paciente.agregaSintoma("Fiebre")) System.out.println("Fail fiebre");
        System.out.println(Arrays.toString(paciente.getSintomas()));
        if (!paciente.agregaSintoma("Vomitos")) System.out.println("Fail vomitos");
        System.out.println(Arrays.toString(paciente.getSintomas()));
        if (!paciente.agregaSintoma("Diarrea")) System.out.println("Fail diarrea");
        System.out.println(Arrays.toString(paciente.getSintomas())+"\n");
        
        System.out.println(paciente.estimaGravedad()+"\n");
        
        if (paciente.padeceSintoma("Fiebre")) System.out.println("Padece fiebre");
        else System.out.println("No padece fiebre");
        if (paciente.padeceSintoma("Hemorragia")) System.out.println("Padece hemorragia\n");
        else System.out.println("No padece hemorragia\n");
        
        System.out.println(paciente.cura("Fiebre")+" borrado");
        System.out.println(Arrays.toString(paciente.getSintomas())+"\n");
        
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

        Clinica clinica = new Clinica("Pollos", 3);
        Clinica clinica2 = new Clinica("Polles", 3);
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
    }
	/*public static void main(String[] args) {
		Paciente p=new Paciente(3);
		Paciente p2=new Paciente(3);
		Habitacion h=new Habitacion(2,4);
		Clinica c=new Clinica("Doctor muerte",3);

		//Comprobaciones de Paciente
		System.out.println("-------PACIENTE-------");
		System.out.println(p.agregaSintoma("dolor de cabeza"));
		String[] s=p.getSintomas();
		System.out.println(p.agregaSintoma("sida"));
		System.out.println(p.agregaSintoma("hambre"));
		s=p.getSintomas();
		for(int i=0;i<s.length;i++)
			 System.out.println(i+" "+s[i]);
		System.out.println(p.estimaGravedad());
		System.out.println(p.padeceSintoma("hambre"));
		System.out.println(p.cura("HAMBRE"));
		
		//Comprobaciones de Habitacion
		System.out.println("-------HABITACION-------");
		System.out.println("Disponible: "+h.disponible());
		System.out.println("Ingreso: "+h.ingreso(p));
		System.out.println("Ingreso: "+h.ingreso(p));
		System.out.println("Ingreso: "+h.ingreso(p));
		System.out.println("Alta: "+h.alta(p));
		System.out.println("Ingreso: "+h.ingreso(p));
		System.out.println("Consulta: "+h.consulta(p));
		System.out.println("EstaIngresado: "+h.estaIngresado(p));
		
		//Comprobaciones de Clinica
		System.out.println("-------CLINICA-------");
		System.out.println(c.construyePlanta(2, 4));
		System.out.println(c.construyePlanta(0, 0));
		System.out.println(c.construyeHabitacion(0, 0));

	}*/
    
}
