package bibliotecas.controladorfrontal;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.Logger;

import bibliotecas.inyecciondependencias.ContenedorInyeccionDependencias;
import bibliotecas.json.JsonHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cf/*")
public class ControladorFrontal extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(ControladorFrontal.class.getName());
	private static final JsonHelper JSON = (JsonHelper) ContenedorInyeccionDependencias.obtenerObjeto("jsonhelper");

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. RECOGER LOS DATOS DE LA PETICIÓN
		String metodo = request.getMethod();
		String ruta = request.getPathInfo();
		Map<String, String[]> argumentos = request.getParameterMap();

		log.info(metodo);
		log.info(ruta);

		argumentos.entrySet().stream().map(e -> String.format("%s=%s", e.getKey(), Arrays.toString(e.getValue())))
				.forEach(log::info);

		// 2. CONVERTIR LOS QUE SEAN NECESARIOS

		// 3. EMPAQUETAR EN OBJETOS
		EntradaControladorFrontal entrada = new EntradaControladorFrontal(metodo, ruta, argumentos);

		// 4. EJECUTAR LÓGICA DE NEGOCIO
		@SuppressWarnings("unchecked")
		Function<EntradaControladorFrontal, Object> procesador = (Function<EntradaControladorFrontal, Object>) ContenedorInyeccionDependencias
				.obtenerObjeto(metodo + ruta);

		Object resultado = procesador.apply(entrada);

		// 5. EMPAQUETAR INFORMACIÓN PARA LA SIGUIENTE VISTA
		String json = JSON.objetoAJson(resultado);

		// 6. DEVOLVER EL RESULTADO EN JSON AL CLIENTE
		response.setContentType("application/json");
		response.getWriter().append(json);

		// super.service(request, response);
	}
}
