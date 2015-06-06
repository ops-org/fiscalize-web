package br.net.ops.fiscalize.preferences;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import org.apache.commons.lang3.StringUtils;

import br.net.ops.fiscalize.exception.UsuarioDeletarException;
import br.net.ops.fiscalize.exception.UsuarioResgatarException;
import br.net.ops.fiscalize.exception.UsuarioSalvarException;
import br.net.ops.fiscalize.vo.Usuario;

public class UsuarioPreferences {

private static final String TAG = "UsuarioPreferences";

	private static final String PREFERENCES = "login_preferences";

    private static final String USUARIO_ID = "usuario_id";
    private static final String TOKEN_ID = "token_id";

	private SharedPreferences preferences = null;

	private Context context;
	
	public UsuarioPreferences(Context context) {
		this.context = context;
	}
	
	private SharedPreferences getPreferences() {
		if(preferences ==null) {
			return context.getSharedPreferences(PREFERENCES, Activity.MODE_PRIVATE);
		} else {
			return preferences;
		}
	}

	public Usuario resgatar() throws UsuarioResgatarException {
		String tokenId = getPreferences().getString(TOKEN_ID, "");
        Integer usuarioId = getPreferences().getInt(USUARIO_ID, 0);
		if(StringUtils.isEmpty(tokenId) || usuarioId<=0) {
			throw new UsuarioResgatarException(TAG, context);
		} else {
            Usuario usuario = new Usuario();
            usuario.setUsuarioId(usuarioId);
            usuario.setTokenId(tokenId);
			return usuario;
		}
	}
	
	public boolean salvar(Usuario usuario) throws UsuarioSalvarException {
		SharedPreferences.Editor editor = getPreferences().edit();
		editor.putString(TOKEN_ID, usuario.getTokenId());
        editor.putInt(USUARIO_ID, usuario.getUsuarioId());
		if(!editor.commit()) {
			throw new UsuarioSalvarException(TAG, context);
		}
		return true;
	}
	
	public boolean deletar() throws UsuarioDeletarException {
		SharedPreferences.Editor editor = getPreferences().edit();
		editor.remove(TOKEN_ID);
        editor.remove(USUARIO_ID);
		if(!editor.commit()) {
			throw new UsuarioDeletarException(TAG, context);
		}
		return true;
	}
	
}
