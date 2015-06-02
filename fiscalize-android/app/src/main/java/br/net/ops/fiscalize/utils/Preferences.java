package br.net.ops.fiscalize.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import org.apache.commons.lang3.StringUtils;

import br.net.ops.fiscalize.exception.DeletarUsuarioException;
import br.net.ops.fiscalize.exception.ResgatarSuspeitasException;
import br.net.ops.fiscalize.exception.SalvarUsuarioException;
import br.net.ops.fiscalize.exception.ResgatarUsuarioException;
import br.net.ops.fiscalize.vo.Usuario;

public class Preferences {

private static final String TAG = "Preferences";

	public static final String LOGIN_PREFERENCES = "login_preferences";
	public static final String SUSPEITAS_PREFERENCES = "suspeitas_preferences";

    public static final String USUARIO_ID = "usuario_id";
	public static final String TOKEN_ID = "token_id";

    public static final String NOTAS_SUSPEITAS = "notas_suspeitas";
    public static final String NOTAS_LIMPAS = "notas_limpas";
    public static final String TODAS_NOTAS = "todas_notas";
    public static final String INICIO = "inicio";
    public static final String MES = "mes";

	private SharedPreferences loginPreferences = null;
	private SharedPreferences suspeitaPreferences = null;

	private Context context;
	
	public Preferences(Context context) {
		this.context = context;
	}
	
	private SharedPreferences getLoginPreferences() {
		if(loginPreferences==null) {
			return context.getSharedPreferences(LOGIN_PREFERENCES, Activity.MODE_PRIVATE);
		} else {
			return loginPreferences;
		}
	}

	private SharedPreferences getSuspeitaPreferences() {
		if(suspeitaPreferences==null) {
			return context.getSharedPreferences(SUSPEITAS_PREFERENCES, Activity.MODE_PRIVATE);
		} else {
			return suspeitaPreferences;
		}
	}

    /*public Usuario resgatarSuspeitas() throws ResgatarSuspeitasException {

        int notasSuspeitas = getSuspeitaPreferences().getInt(NOTAS_SUSPEITAS, 0);
        int notasLimpas = getSuspeitaPreferences().getInt(NOTAS_LIMPAS, 0);

        Integer usuarioId = getLoginPreferences().getInt(USUARIO_ID, 0);
        if(StringUtils.isEmpty(tokenId) || usuarioId<=0) {
            throw new ResgatarUsuarioException(TAG, context);
        } else {
            Usuario usuario = new Usuario();
            usuario.setUsuarioId(usuarioId);
            usuario.setTokenId(tokenId);
            return usuario;
        }
    }*/

	public Usuario resgatarUsuario() throws ResgatarUsuarioException {
		String tokenId = getLoginPreferences().getString(TOKEN_ID, "");
        Integer usuarioId = getLoginPreferences().getInt(USUARIO_ID, 0);
		if(StringUtils.isEmpty(tokenId) || usuarioId<=0) {
			throw new ResgatarUsuarioException(TAG, context);
		} else {
            Usuario usuario = new Usuario();
            usuario.setUsuarioId(usuarioId);
            usuario.setTokenId(tokenId);
			return usuario;
		}
	}
	
	public boolean salvarUsuario(Usuario usuario) throws SalvarUsuarioException {
		SharedPreferences.Editor editor = getLoginPreferences().edit();
		editor.putString(TOKEN_ID, usuario.getTokenId());
        editor.putInt(USUARIO_ID, usuario.getUsuarioId());
		if(!editor.commit()) {
			throw new SalvarUsuarioException(TAG, context);
		}
		return true;
	}
	
	public boolean deletarUsuario() throws DeletarUsuarioException {
		SharedPreferences.Editor editor = getLoginPreferences().edit();
		editor.remove(TOKEN_ID);
        editor.remove(USUARIO_ID);
		if(!editor.commit()) {
			throw new DeletarUsuarioException(TAG, context);
		}
		return true;
	}
	
}
