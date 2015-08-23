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
import br.net.ops.fiscalize.exception.UsuarioNaoAutorizadoException;
import br.net.ops.fiscalize.pojo.PedidoNota;
import br.net.ops.fiscalize.pojo.retorno.Erro;
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
	
	@ModelAttribute
	public PedidoNota newRequest(@RequestParam(required=true) String tokenId,
								@RequestParam(required=false) Integer partidoId,
								@RequestParam(required=false) Integer parlamentarId) {
		
		PedidoNota pedidoNota = new PedidoNota();
		
		pedidoNota.setTokenId(tokenId);
		pedidoNota.setPartidoId(partidoId==null?0:partidoId);
		pedidoNota.setParlamentarId(parlamentarId==null?0:parlamentarId);
		
		return pedidoNota;
		
	}
	
	@ResponseBody
	@RequestMapping(value="/recuperar", method=RequestMethod.POST)
    public void recuperarNotaFiscal(HttpServletResponse response, HttpServletRequest request, PedidoNota pedidoNota) {
		logger.log(Level.INFO, "GET - Nota Fiscal aleatória...");

		response.setContentType(Utilidade.HTTP_HEADER_JSON);
		
		try {

			Gson gson = new GsonBuilder()
				.setDateFormat("dd/MM/yyyy HH:mm:ss")
				.excludeFieldsWithoutExposeAnnotation().create();
			
			try {
				NotaFiscal notaFiscal = restService.recuperarNotaFiscal(pedidoNota);
			
				if(notaFiscal!=null) {
					response.getWriter().write(gson.toJson(notaFiscal));
				} else {
					Erro erro = new Erro(BundleUtils.obterMensagem(BundleUtils.EXCEPTION_MESSAGES, "erro.rest.nota.fiscal.nula"));
					response.getWriter().write(gson.toJson(erro));
				}
				
			} catch(UsuarioNaoAutorizadoException e) {
				Erro erro = new Erro(e.getLocalizedMessage());
				response.getWriter().write(gson.toJson(erro));
			}
			
		} catch(IOException e) {
			logger.log(Level.WARNING, "Impossível responder requisição! Admin: verificar!");
		}
		
    }
	
}
