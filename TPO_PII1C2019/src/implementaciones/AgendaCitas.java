package implementaciones;


import tdas.AgendaCitasTDA;
import tdas.ColaTDA;
import tdas.ConjuntoTDA;
import tdas.ArbolCitasTDA;

public class AgendaCitas implements AgendaCitasTDA {
	
	NodoAgenda primerAgenda;
	
	
	@Override
	public void inicializar() {//funciona
		primerAgenda = null;
	}
	
	
	@Override
	public void agregarNuevoDia(String abogado, String dia, String fecha) {//funciona
		if(primerAgenda == null) {
			primerAgenda = crearAbogado(abogado, dia, fecha);
		}else {
			NodoAgenda aux = buscarAbogado(abogado);
			if(aux == null) {//si no se encuentra el abogado
				aux = primerAgenda;
				while(aux.sigAbogado != null)
						aux = aux.sigAbogado;
				aux.sigAbogado = crearAbogado(abogado,dia, fecha);
			}else {
				NodoDia auxDia = buscarDia(aux, fecha);
				if(auxDia == null) {
					auxDia = aux.primeraFecha;
					while(auxDia.siguienteFecha != null)
						auxDia = auxDia.siguienteFecha;
					auxDia.siguienteFecha = crearDia(dia, fecha);
				}
			}
		}
	}
	
	@Override
	public void agregarNuevaCita(String abogado, String fecha, String hora, String cliente) {//funciona
		NodoAgenda auxAbogado = buscarAbogado(abogado);
		if(auxAbogado != null) {
			NodoDia auxDia = buscarDia(auxAbogado, fecha);
			if(auxDia != null && existeCita(abogado, fecha, hora) == false) {
				auxDia.turnos.agregar(hora, cliente);
			}
		}
	}
	
	@Override
	public void eliminarAbogado(String abogado) {//funciona
		if(primerAgenda != null) {
			if(primerAgenda.abogado.equalsIgnoreCase(abogado)) {
				primerAgenda = primerAgenda.sigAbogado;
			}else {
				NodoAgenda aux = primerAgenda;
				while(aux.sigAbogado != null && !aux.sigAbogado.abogado.equalsIgnoreCase(abogado)) {
					aux = aux.sigAbogado;
				}
				if(aux.sigAbogado != null) {
					aux.sigAbogado = aux.sigAbogado.sigAbogado;
				}
			}
		}
	}

	@Override
	public void eliminarFecha(String abogado, String fecha) {//testeada y funcionando
		if(primerAgenda != null) {
			NodoAgenda auxAgenda = buscarAbogado(abogado);
			if(auxAgenda != null) {
				eliminarNodoFecha(auxAgenda, fecha);
				if(auxAgenda.primeraFecha == null) {//si el abogado ya no tiene fechas, lo elimino
					if (primerAgenda.abogado.equalsIgnoreCase(abogado)) {
						primerAgenda = primerAgenda.sigAbogado;
					} else {
						NodoAgenda nAgenda = primerAgenda;
						while(!nAgenda.sigAbogado.abogado.equalsIgnoreCase(abogado)) {
							nAgenda = nAgenda.sigAbogado;
						}
						nAgenda.sigAbogado = auxAgenda.sigAbogado;
					}
				}
			}
		}
	}
	

	@Override
	public void eliminarCita(String abogado, String fecha, String hora, String cliente) {//funciona legal, pero.. bonito???
		NodoAgenda auxAbogado = buscarAbogado(abogado);
		if(auxAbogado != null) {
			NodoDia auxDia = buscarDia(auxAbogado, fecha);
			if(auxDia != null && existeCita(abogado, fecha, hora) ) {
				auxDia.turnos.eliminar(hora, cliente);
			}
		}

	}

	@Override
	public boolean existeCita(String abogado, String fecha, String hora) {//funciona legal y bonito
		NodoAgenda auxAbogado = buscarAbogado(abogado);
		if(auxAbogado != null) {
			NodoDia auxDia = buscarDia(auxAbogado, fecha);
			if(auxDia != null && existeHora(auxDia.turnos, hora) ) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String clienteEnCita(String abogado, String fecha, String hora) {//funciona
		NodoAgenda auxAbogado = buscarAbogado(abogado);
		NodoDia auxDia = buscarDia(auxAbogado, fecha);
		return clienteCitado(auxDia.turnos, hora);
	}

	@Override
	public ConjuntoTDA abogados() {//funciona
		NodoAgenda aux = primerAgenda;
		ConjuntoTDA conjAbogados = new Conjunto();
		conjAbogados.inicializar();
		while(aux != null) {
			conjAbogados.agregar(aux.abogado);
			aux = aux.sigAbogado;
		}
		return conjAbogados;
	}

	@Override
	public ColaTDA turnos(String abogado, String fecha) {//funciona
		NodoAgenda auxAbogado = buscarAbogado(abogado);
		Cola auxCola = new Cola();
		auxCola.inicilizar();
		if(auxAbogado != null) {
			NodoDia auxDia = buscarDia(auxAbogado, fecha);
			if(auxDia != null) {
				colaTurnos(auxDia.turnos,auxCola);
			}
		}
		return auxCola;
	}

	@Override
	public ConjuntoTDA fechas(String abogado) {//funciona
		ConjuntoTDA auxConjunto = new Conjunto();
		NodoAgenda auxAbogado = buscarAbogado(abogado);
		auxConjunto.inicializar();
		if(auxAbogado != null) {
			NodoDia auxDia = auxAbogado.primeraFecha;
			while(auxDia != null) {
				auxConjunto.agregar(auxDia.fecha);
				auxDia = auxDia.siguienteFecha;
			}
		}
		return auxConjunto;
	}
//funciones auxiliares para agregar nuevo d�a
	private NodoAgenda crearAbogado(String abogado, String dia, String fecha) {
		NodoAgenda auxAgenda = new NodoAgenda();
		
		auxAgenda.abogado = abogado;
		auxAgenda.primeraFecha = crearDia(dia,fecha);
		auxAgenda.sigAbogado = null;
		
		return auxAgenda;
	}
	
	private NodoDia crearDia(String dia, String fecha) {
		NodoDia auxDia = new NodoDia();
		auxDia.dia = dia;
		auxDia.fecha = fecha;
		auxDia.turnos = new ArbolCitas();
		auxDia.turnos.inicializar();
		auxDia.siguienteFecha = null;
		return auxDia;
	}


//funcion auxiliar para eliminar fecha	
	private void eliminarNodoFecha(NodoAgenda abogado, String fecha) {	
		if(abogado.primeraFecha.fecha.equalsIgnoreCase(fecha)) {
			abogado.primeraFecha = abogado.primeraFecha.siguienteFecha;
		} else {
			NodoDia aux = abogado.primeraFecha;
			while(aux.siguienteFecha != null && !aux.siguienteFecha.fecha.equalsIgnoreCase(fecha))
				aux = aux.siguienteFecha;
			if (aux.siguienteFecha != null) {
				// hay que considerar el caso que no exista la fecha que se quiere eliminar
				aux.siguienteFecha = aux.siguienteFecha.siguienteFecha;
			}
		}	
	}
// 
	private NodoAgenda buscarAbogado(String abogado) {
		NodoAgenda aux = primerAgenda;
		while(aux != null && !aux.abogado.equalsIgnoreCase(abogado) )
			aux = aux.sigAbogado;
		return aux;
	} 
	
	private NodoDia buscarDia(NodoAgenda abogado, String fecha) {
		NodoDia aux = abogado.primeraFecha;
		while(aux != null && !aux.fecha.equalsIgnoreCase(fecha) )
			aux = aux.siguienteFecha;
		return aux;
	}
	
	public boolean existeHora(ArbolCitasTDA nArbol, String hora) {
		if(!nArbol.arbolVacio()) {
			if(nArbol.hora().equals(hora)) {
				return true;
			}
			if(nArbol.hora().compareTo(hora) < 0) {
				return existeHora(nArbol.hijoDerecho(), hora);
			}else{
				return existeHora(nArbol.hijoIzquierdo(), hora);
			}
		}
		return false;
	}
	
	private String clienteCitado(ArbolCitasTDA nArbol, String hora) {//recorrer arbol en orden
		if (!nArbol.arbolVacio()) {
			if(hora.equals(nArbol.hora())) {
				return nArbol.cliente();
			}else if(nArbol.hora().compareTo(hora) < 0) {
				return clienteCitado(nArbol.hijoDerecho(), hora);
			}else{
				return clienteCitado(nArbol.hijoIzquierdo(), hora);
			}
		}else{
			return "";
		}
	}
	
	private void colaTurnos(ArbolCitasTDA nArbol,ColaTDA cola) {
		if(!nArbol.arbolVacio()) {
			colaTurnos(nArbol.hijoIzquierdo(),cola);
			cola.acolar(nArbol.hora());
			colaTurnos(nArbol.hijoDerecho(),cola);
		}
	}
	
}









