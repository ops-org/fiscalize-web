package br.net.ops.fiscalize.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.net.ops.fiscalize.dao.*;
import br.net.ops.fiscalize.domain.*;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.net.ops.fiscalize.exception.AdicionarSuspeitaException;
import br.net.ops.fiscalize.exception.UsuarioNaoAutorizadoException;
import br.net.ops.fiscalize.pojo.PedidoNota;
import br.net.ops.fiscalize.webutil.Utilidade;
import br.net.ops.fiscalize.webutil.base.ServiceBase;

@Service
public class RestService extends ServiceBase {

	private static final int NUMERO_PADRAO_LISTA_NOTAS = 3; 
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private NotaFiscalDao notaFiscalDao;

	@Autowired
	private SuspeitaDao suspeitaDao;

	@Autowired
	private ParlamentarDao parlamentarDao;

	@Autowired
	private PartidoDao partidoDao;

	private Logger logger = Utilidade.getLogger();

	@Transactional
	public Usuario getUsuarioAutorizado(String tokenId) {
		logger.log(Level.CONFIG, "Verificando autorização...");
		
		Usuario usuario = new Usuario();
		usuario.setTokenId(tokenId);

		Usuario autorizado;
		List<Usuario> usuarios = usuarioDao.findByExample(usuario);
		if(usuarios.size()==1) {
			autorizado = usuarios.get(0);
		} else {
			autorizado = null;
		}
		
		return autorizado;
	}
	
	@Transactional
	public Usuario cadastrarAutorizar(Usuario usuario) {
		Usuario autorizado = getUsuarioAutorizado(usuario.getTokenId()); 
		if(autorizado!=null) {
			return autorizado; // Está cadastrado e autorizado
		} else {
			return usuarioDao.criar(); // criar (e autorizar)
		}		
	}
	
	@Transactional
	public NotaFiscal recuperarNotaFiscal(PedidoNota pedidoNota) throws UsuarioNaoAutorizadoException {
		logger.log(Level.CONFIG, "Recuperando nota fiscal...");
		Usuario autorizado = getUsuarioAutorizado(pedidoNota.getTokenId());
		if(autorizado!=null) {		
			return notaFiscalDao.pegarRandomica(pedidoNota);
		} else {
			throw new UsuarioNaoAutorizadoException();
		}
	}
	
	@Transactional
	public List<NotaFiscal> recuperarListaNotaFiscal(PedidoNota pedidoNota) throws UsuarioNaoAutorizadoException {
		logger.log(Level.CONFIG, "Recuperando notas fiscais...");
		Usuario autorizado = getUsuarioAutorizado(pedidoNota.getTokenId());
		
		if(autorizado!=null) {		
			if(pedidoNota.getQuantidade()<=0) pedidoNota.setQuantidade(NUMERO_PADRAO_LISTA_NOTAS);
			List<NotaFiscal> retorno = new ArrayList<NotaFiscal>();
			for(int i=0; i<pedidoNota.getQuantidade(); i++) {
				retorno.add(notaFiscalDao.pegarRandomica(pedidoNota));
			}
			return retorno;
		} else {
			throw new UsuarioNaoAutorizadoException();
		}
	}

	@Transactional
	public List<Parlamentar> recuperarParlamentares() {
		logger.log(Level.CONFIG, "Recuperando lista de parlamentares...");

		return parlamentarDao.recuperarParlamentares();
	}

	@Transactional
	public List<Partido> recuperarPartidos() {
		logger.log(Level.CONFIG, "Recuperando lista de partidos...");

		return partidoDao.recuperarPartidos();
	}
	
	@Transactional(rollbackFor=AdicionarSuspeitaException.class)
	public void adicionarSuspeita(Suspeita suspeita) throws AdicionarSuspeitaException {
		logger.log(Level.CONFIG, "Adicionando suspeita...");
		
		suspeita.setDataInclusao(new Date(System.currentTimeMillis()));
		try {
			suspeitaDao.save(suspeita);
		} catch(ConstraintViolationException e) {
			throw new AdicionarSuspeitaException(e);
		}
		
	}
	
}
