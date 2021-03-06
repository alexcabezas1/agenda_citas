package algoritmos;
import java.util.Arrays;

import tdas.AgendaCitasTDA;
import tdas.ConjuntoTDA;
import tdas.ColaPrioridadTDA;
import tdas.ColaTDA;

public class TestAgendaCitas {
	
	public static void main(String[] args) {
		Algoritmo algo = new Algoritmo();
		
		System.out.println(algo.sumarDia("2019/01/01"));
		System.out.println(algo.sumarDia("2019/01/29"));
		System.out.println(algo.sumarDia("2019/01/30"));
		System.out.println(algo.sumarDia("2019/01/31"));
		System.out.println(algo.sumarDia("2019/02/01"));
		System.out.println(algo.sumarDia("2019/02/28"));
		System.out.println(algo.sumarDia("2019/03/01"));
		System.out.println(algo.sumarDia("2019/03/30"));
		System.out.println(algo.sumarDia("2019/03/31"));
		System.out.println(algo.sumarDia("2019/04/01"));
		System.out.println(algo.sumarDia("2019/04/30"));
		System.out.println(algo.sumarDia("2019/05/01"));
		System.out.println(algo.sumarDia("2019/05/31"));
		System.out.println(algo.sumarDia("2019/06/01"));
		System.out.println(algo.sumarDia("2019/06/30"));
		System.out.println(algo.sumarDia("2019/07/01"));
		System.out.println(algo.sumarDia("2019/07/31"));
		System.out.println(algo.sumarDia("2019/08/01"));
		System.out.println(algo.sumarDia("2019/08/31"));
		System.out.println(algo.sumarDia("2019/09/01"));
		System.out.println(algo.sumarDia("2019/09/30"));
		System.out.println(algo.sumarDia("2019/10/01"));
		System.out.println(algo.sumarDia("2019/10/31"));
		System.out.println(algo.sumarDia("2019/11/01"));
		System.out.println(algo.sumarDia("2019/11/30"));
		System.out.println(algo.sumarDia("2019/12/01"));
		System.out.println(algo.sumarDia("2019/12/31"));
		System.out.println("--");
		for (String[] dias : algo.diasSemana("2019/01/28") ) {
			for (String dia : dias) {
				System.out.println(dia);
			}
		}
		String[][] dias = algo.diasSemana("2019/01/28");
		System.out.println(Arrays.asList(dias[0]).contains("2019/02/03"));
		System.out.println(Arrays.asList(dias[1]).contains("miercoles"));
		Integer index = Arrays.asList(dias[0]).indexOf("2019/02/01");
		System.out.println(dias[0][index] + " - " + dias[1][index]);
		
		String[][] citas = new String[][] {
			{ "martes", "14:00", "otro cliente mas" },
			{ null, null, null },
			{ "martes", "14:30", "otro cliente" },
			{ "miercoles", "10:30", "un cliente" },
			{ null, null, null }
		};
		String[][] compacto = algo.compactarArreglo(citas);
		for (String[] f : compacto) {
			for (String c : f) {
				System.out.println(c);
			}
		}
		
		String[][] citas2 = new String[][] {
			{ "martes", "14:30", "otro cliente" },
			{ "miercoles", "10:30", "un cliente" },
			{ "martes", "14:00", "otro cliente mas" }
		};
		algo.ordenarArreglo(citas2, 0, 1);
		System.out.println("--");
		for (String[] f : citas2) {
			for (String c : f) {
				System.out.println(c);
			}
		}
		
		/*
		AgendaCitasTDA citas = new implementaciones.AgendaCitas();
		ConjuntoTDA abogados = new implementaciones.Conjunto();
		ConjuntoTDA fechas = new implementaciones.Conjunto();
		ColaTDA cola = new implementaciones.Cola();
		String aux = "lol";
		//aux = null;
		abogados.inicializar();
		citas.inicializar();
		
		citas.agregarNuevoDia("Alberto", "hoy", "23/06/2019");		
		citas.agregarNuevoDia("juan", "hoy", "23/06/2019");
		citas.agregarNuevoDia("Pedro", "hoy", "23/06/2019");
		citas.agregarNuevoDia("Ramon", "hoy", "23/06/2019");
		
		citas.agregarNuevoDia("Alberto", "ma�ana", "24/06/2019");		
		citas.agregarNuevoDia("juan", "ma�ana", "24/06/2019");
		citas.agregarNuevoDia("Pedro", "ma�ana", "24/06/2019");
		citas.agregarNuevoDia("Ramon", "ma�ana", "24/06/2019");
		
		
		citas.agregarNuevoDia("Alberto", "pasado", "25/06/2019");		
		citas.agregarNuevoDia("juan", "pasado", "25/06/2019");
		citas.agregarNuevoDia("Pedro", "pasado", "25/06/2019");
		citas.agregarNuevoDia("Ramon", "pasado", "25/06/2019");
		
		
		citas.agregarNuevaCita("Alberto", "24/06/2019", "1300", "Cliente1");
		citas.agregarNuevaCita("Alberto", "24/06/2019", "1200", "Cliente2");
		citas.agregarNuevaCita("Alberto", "24/06/2019", "1100", "Cliente3");
		citas.agregarNuevaCita("Alberto", "24/06/2019", "1000", "Cliente4");
		
		citas.agregarNuevaCita("Alberto", "25/06/2019", "1000", "Cliente5");
		citas.agregarNuevaCita("Alberto", "25/06/2019", "1100", "Cliente6");
		citas.agregarNuevaCita("Alberto", "25/06/2019", "1200", "Cliente7");
		
		citas.agregarNuevaCita("juan", "23/06/2019", "1500", "Cliente3");
		citas.agregarNuevaCita("juan", "23/06/2019", "1500", "Cliente4");
		citas.agregarNuevaCita("Pedro", "23/06/2019", "1000", "Cliente5");
		citas.agregarNuevaCita("Ramon", "24/06/2019", "1030", "Cliente6");
<<<<<<< HEAD
		
		/*Prueba de conjunto Fechas*/

		/*Prueba de conjunto Fechas
>>>>>>> 168987e37154210a2968119fe73f89aeb4c87921
		fechas = citas.fechas("Alberto");
		while(!fechas.conjuntoVacio()) {
			aux = fechas.elegir();
			System.out.println(aux);
			fechas.sacar(aux);
		}
		
		
		
		/*Prueba de Cola de turnos
		cola = citas.turnos("Alberto", "24/06/2019");
		
		while(!cola.colaVacia()) {
			System.out.println(cola.primero());
			cola.desacolar();
		}
		
		*/
		
		/*
		citas.eliminarFecha("Alberto", "24/06/2019");
		
		if(citas.existeCita( "Alberto", "24/06/2019", "1100")){
			System.out.println(citas.clienteEnCita( "Alberto", "24/06/2019", "1100"));//cliente1
		}else{
			System.out.println("FechaBorrada");
		}

		
		if(citas.existeCita( "Alberto", "25/06/2019", "1200")){
			System.out.println(citas.clienteEnCita( "Alberto", "25/06/2019", "1200"));
		}else {
			System.out.println("Borro cualquiera");
		}
		*/
		
		
		
		/*
		if(citas.existeCita( "Alberto", "24/06/2019", "1100")){
			System.out.println(citas.clienteEnCita( "Alberto", "24/06/2019", "1100"));//cliente1
		}

		if(citas.existeCita( "Alberto", "24/06/2019", "1300")){
			System.out.println(citas.clienteEnCita( "Alberto", "24/06/2019", "1300"));
		}
		
		if(citas.existeCita( "Ramon", "24/06/2019", "1030")){
			System.out.println(citas.clienteEnCita( "Ramon", "24/06/2019", "1030"));//cliente3
		}
		
		
		citas.eliminarCita("Alberto", "24/06/2019", "1100","Cliente2");
		
		if(citas.existeCita( "Alberto", "24/06/2019", "1100")){
			System.out.println(citas.clienteEnCita( "Alberto", "24/06/2019", "1100"));//cliente1
		}else {
			System.out.println("Cita Eliminada");
		}//cliente1}
		*/

		//System.out.println("-----------------------------------------------------");
		//citas.eliminarAbogado("pedro");
		//citas.eliminarAbogado("alberto");
		//citas.eliminarAbogado("ramon");
		//abogados = citas.abogados();
		
		/*
		while(!abogados.conjuntoVacio()){
			aux = abogados.elegir();
			System.out.println(aux);
			abogados.sacar(aux);
		}
		
		
		System.out.println("Terminado");
		*/
		

	}

}
