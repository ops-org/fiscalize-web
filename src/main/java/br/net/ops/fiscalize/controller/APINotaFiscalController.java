package br.net.ops.fiscalize.controller;

import java.io.IOException;
import java.util.logging.Level;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.net.ops.fiscalize.domain.NotaFiscal;
import br.net.ops.fiscalize.domain.Usuario;
import br.net.ops.fiscalize.service.RestService;
import br.net.ops.fiscalize.webutil.BundleUtils;
import br.net.ops.fiscalize.webutil.Utilidade;
import br.net.ops.fiscalize.webutil.base.ControllerBase;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping(value = "/rest/notafiscal")
public class APINotaFiscalController extends ControllerBase {
	
	@Autowired
	private RestService restService;
	
	@ResponseBody
	@RequestMapping(value="/recuperar", method=RequestMethod.GET)
    public void recuperarNotaFiscal(HttpServletResponse response, HttpServletRequest request) {
		logger.log(Level.INFO, "GET - Nota Fiscal aleatória...");

		response.setContentType(Utilidade.HTTP_HEADER_JSON);
		
		NotaFiscal notaFiscal = restService.recuperarNotaFiscal();
		
		try {
			Gson gson = new GsonBuilder()
				.setDateFormat("dd/MM/yyyy HH:mm:ss")
				.excludeFieldsWithoutExposeAnnotation().create();
			
			response.getWriter().write(gson.toJson(notaFiscal));
		} catch(IOException e) {
			try {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, BundleUtils.obterMensagem(BundleUtils.EXCEPTION_MESSAGES, "erro.rest.notafiscal"));
			} catch (IOException e1) {
				logger.log(Level.WARNING, "Impossível responder requisição! Admin: verificar!");
			}
		}
		
    }
	
}
