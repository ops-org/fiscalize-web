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
import br.net.ops.fiscalize.domain.Suspeita;
import br.net.ops.fiscalize.domain.Usuario;
import br.net.ops.fiscalize.exception.AdicionarSuspeitaException;
import br.net.ops.fiscalize.pojo.Erro;
import br.net.ops.fiscalize.pojo.Mensagem;
import br.net.ops.fiscalize.pojo.Retorno;
import br.net.ops.fiscalize.service.RestService;
import br.net.ops.fiscalize.webutil.BundleUtils;
import br.net.ops.fiscalize.webutil.Utilidade;
import br.net.ops.fiscalize.webutil.base.ControllerBase;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping(value = "/rest/suspeita")
public class APISuspeitaController extends ControllerBase {
	
	@Autowired
	private RestService restService;
	
	@ModelAttribute
	public Suspeita montarSuspeita(@RequestParam(required=false) Integer usuarioId, 
							  @RequestParam(required=false) Integer notaFiscalId,
							  @RequestParam(required=false) Boolean suspeita,
							  @RequestParam(required=false) Boolean suspeitaValor,
							  @RequestParam(required=false) Boolean suspeitaObjeto,
							  @RequestParam(required=false) Boolean suspeitaFornecedor,
							  @RequestParam(required=false) String comentarios) {
		
		
		Usuario usuario = new Usuario();
		usuario.setUsuarioId(usuarioId);

		NotaFiscal notaFiscal = new NotaFiscal();
		notaFiscal.setNotaFiscalId(notaFiscalId);
		
		Suspeita objSuspeita = new Suspeita();
		objSuspeita.setSuspeita(suspeita);
		objSuspeita.setSuspeitaValor(suspeitaValor);
		objSuspeita.setSuspeitaObjeto(suspeitaObjeto);
		objSuspeita.setSuspeitaFornecedor(suspeitaFornecedor);
		objSuspeita.setComentarios(comentarios);
		
		objSuspeita.setUsuario(usuario);
		objSuspeita.setNotaFiscal(notaFiscal);
		
		return objSuspeita;
	}
	
	@ResponseBody
	@RequestMapping(value="/adicionar", method=RequestMethod.POST)
    public void adicionar(HttpServletRequest request, HttpServletResponse response, Suspeita suspeita) {
		logger.log(Level.INFO, "POST - Adicionar...");

		response.setContentType(Utilidade.HTTP_HEADER_JSON);
		
		Retorno retorno;
		try {
			restService.adicionarSuspeita(suspeita);
			retorno = new Mensagem(BundleUtils.obterMensagem(BundleUtils.GLOBAL_MESSAGES, "success.rest.adicionar.suspeita"));
		} catch(AdicionarSuspeitaException e) {
			retorno = new Erro(e.getLocalizedMessage());
		}
		
		try {
			Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
			response.getWriter().write(gson.toJson(retorno));
		} catch(IOException e) {
			logger.log(Level.WARNING, "Impossível responder requisição! Admin: verificar!");
		}
		
    }
	
}
