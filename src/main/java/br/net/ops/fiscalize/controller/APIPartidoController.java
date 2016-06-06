package br.net.ops.fiscalize.controller;

import br.net.ops.fiscalize.domain.Parlamentar;
import br.net.ops.fiscalize.domain.Partido;
import br.net.ops.fiscalize.domain.Usuario;
import br.net.ops.fiscalize.service.RestService;
import br.net.ops.fiscalize.webutil.Utilidade;
import br.net.ops.fiscalize.webutil.base.ControllerBase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

@Controller
@RequestMapping(value = "/rest/partido")
public class APIPartidoController extends ControllerBase {
	
	@Autowired
	private RestService restService;

	@ResponseBody
	@RequestMapping(value="/listar", method=RequestMethod.GET)
    public void autorizar(HttpServletRequest request, HttpServletResponse response, Usuario usuario) {
		logger.log(Level.INFO, "Lista de partidos...");

		response.setContentType(Utilidade.HTTP_HEADER_JSON);

		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		try {
			List<Partido> partidos = restService.recuperarPartidos();
			response.getWriter().write(gson.toJson(partidos));
		} catch(IOException e) {
			logger.log(Level.WARNING, "Impossível responder requisição! Admin: verificar!");
		}

    }
	
}
