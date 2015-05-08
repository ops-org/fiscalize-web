package br.net.ops.fiscalize.controller;

import java.util.logging.Level;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.net.ops.fiscalize.service.UfService;
import br.net.ops.fiscalize.utils.base.ControllerBase;

@Controller
@RequestMapping(value = "/api")
public class MainController extends ControllerBase {
	
	@Autowired
	private UfService ufService;
	
	@RequestMapping(value="/rest", method=RequestMethod.GET)
    public String playlist(HttpServletResponse response, HttpServletRequest request) {
		
		logger.log(Level.INFO, "Entrou INDEX");

		int numeroUfs = ufService.numeroUfs();
		
		request.setAttribute("variavel", numeroUfs);
		
		return "index";
		
    }
	
}
