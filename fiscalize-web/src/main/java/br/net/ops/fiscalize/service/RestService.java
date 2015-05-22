package br.net.ops.fiscalize.service;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.net.ops.fiscalize.dao.NotaFiscalDao;
import br.net.ops.fiscalize.dao.SuspeitaDao;
import br.net.ops.fiscalize.dao.UsuarioDao;
import br.net.ops.fiscalize.domain.NotaFiscal;
import br.net.ops.fiscalize.domain.Suspeita;
import br.net.ops.fiscalize.domain.Usuario;
import br.net.ops.fiscalize.webutil.Utilidade;
import br.net.ops.fiscalize.webutil.base.ServiceBase;

@Service
public class RestService extends ServiceBase {

	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private NotaFiscalDao notaFiscalDao;

	@Autowired
	private SuspeitaDao suspeitaDao;

	
	private Logger logger = Utilidade.getLogger();

	@Transactional
	public boolean isAutorizado(String tokenId) {
		logger.log(Level.CONFIG, "Verificando autorização...");
		
		Usuario usuario = new Usuario();
		usuario.setTokenId(tokenId);

		boolean autorizado;
		List<Usuario> usuarios = usuarioDao.findByExample(usuario);
		if(usuarios.size()==1) {
			autorizado = true;
		} else {
			autorizado = false;
		}
		
		return autorizado;
	}
	
	@Transactional
	public Usuario cadastrarAutorizar(Usuario usuario) {
		boolean autorizado;
		
		autorizado = isAutorizado(usuario.getTokenId());
		
		if(autorizado) {
			return usuario; // Está cadastrado e autorizado
		} else {
			return usuarioDao.criarAutorizar(); // criar e autorizar
		}		
		
	}
	
	@Transactional
	public NotaFiscal recuperarNotaFiscal() {
		logger.log(Level.CONFIG, "Recuperando nota fiscal...");
		return notaFiscalDao.pegarRandomica();
	}
	
	@Transactional
	public boolean adicionarSuspeita(Suspeita suspeita) {
		logger.log(Level.CONFIG, "Adicionando suspeita...");
		
		suspeita.setDataInclusao(new Date(System.currentTimeMillis()));
		Integer id = suspeitaDao.save(suspeita);
		
		return id>0; // se tem Id, foi sucesso
	}
	
}
