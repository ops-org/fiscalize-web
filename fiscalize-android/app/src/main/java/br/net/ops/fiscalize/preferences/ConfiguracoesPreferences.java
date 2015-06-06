package br.net.ops.fiscalize.preferences;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import br.net.ops.fiscalize.exception.ConfiguracoesResgatarException;
import br.net.ops.fiscalize.exception.ConfiguracoesSalvarException;
import br.net.ops.fiscalize.vo.Configuracoes;

public class ConfiguracoesPreferences {

private static final String TAG = "Preferences";

	public static final String PREFERENCES = "configuracoes_preferences";

    public static final String PARLAMENTAR_ID = "parlamentar_id";
	public static final String PARTIDO_ID = "partido_id";

	private SharedPreferences preferences = null;

	private Context context;

	public ConfiguracoesPreferences(Context context) {
		this.context = context;
	}
	
	private SharedPreferences getPreferences() {
		if(preferences==null) {
			return context.getSharedPreferences(PREFERENCES, Activity.MODE_PRIVATE);
		} else {
			return preferences;
		}
	}

    public Configuracoes resgatar() throws ConfiguracoesResgatarException {
        int parlamentarId = getPreferences().getInt(PARLAMENTAR_ID, 0);
        int partidoId = getPreferences().getInt(PARTIDO_ID, 0);

        Configuracoes configuracoes = new Configuracoes();
        configuracoes.setParlamentarId(parlamentarId);
        configuracoes.setPartidoId(partidoId);
        return configuracoes;
    }

	public boolean salvar(Configuracoes configuracoes) throws ConfiguracoesSalvarException {
		SharedPreferences.Editor editor = getPreferences().edit();
		editor.putInt(PARLAMENTAR_ID, configuracoes.getParlamentarId());
        editor.putInt(PARTIDO_ID, configuracoes.getPartidoId());
		if(!editor.commit()) {
			throw new ConfiguracoesSalvarException(TAG, context);
		}
		return true;
	}
	
}
