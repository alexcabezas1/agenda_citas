package algoritmos;

public class TestColaDinamica {

	public static void main(String[] args) {
		implementaciones.Cola cola = new implementaciones.Cola();
		cola.inicilizar();
		cola.acolar("Este");
		cola.acolar("es");
		cola.acolar("un");
		cola.acolar("mensaje");
		cola.acolar("completo");
		
		cola.acolar("agregado1");
		
		while(!cola.colaVacia()) {
			System.out.println(cola.primero());
			cola.desacolar();
		}
		
		System.out.println("Fin de la cola");
	}

}