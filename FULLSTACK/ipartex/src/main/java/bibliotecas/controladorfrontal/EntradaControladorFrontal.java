package bibliotecas.controladorfrontal;

import java.util.Map;

public record EntradaControladorFrontal(String metodo, String ruta, Map<String, String[]> argumentos) {

}
