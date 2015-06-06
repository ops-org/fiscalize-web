package br.net.ops.fiscalize.preferences;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.Date;

import br.net.ops.fiscalize.exception.EstatisticasResgatarException;
import br.net.ops.fiscalize.exception.EstatisticasSalvarException;
import br.net.ops.fiscalize.vo.Estatisticas;

public class EstatiscasPreferences {

private static final String TAG = "EstatiscasPreferences";

    private static final String PREFERENCES = "estatisticas_preferences";

    private static final String QUANTIDADE_SUSPEITAS = "quantidade_suspeitas";
    private static final String QUANTIDADE_LIMPAS = "quantidade_limpas";
    private static final String QUANTIDADE_TOTAL = "quantidade_total";
    private static final String DATA_INICIO = "data_inicio";

    private int mes;
    private int ano;

	private SharedPreferences preferences = null;
    private SharedPreferences preferencesMes = null;

	private Context context;

	public EstatiscasPreferences(Context context, int mes, int ano) {
		this.context = context;
        this.mes = mes;
        this.ano = ano;
	}
	
	private SharedPreferences getPreferences() {
		if(preferences ==null) {
			return context.getSharedPreferences(PREFERENCES, Activity.MODE_PRIVATE);
		} else {
			return preferences;
		}
	}

    private SharedPreferences getPreferencesMes() {
        if(preferencesMes ==null) {
            String strAno = String.format("%04d", ano);
            String strMes = String.format("%02d", mes);
            return context.getSharedPreferences(PREFERENCES + strAno + strMes, Activity.MODE_PRIVATE);
        } else {
            return preferencesMes;
        }
    }

    private Estatisticas resgatar(SharedPreferences preferences) throws EstatisticasResgatarException {
        int quantidadeSuspeitas = preferences.getInt(QUANTIDADE_SUSPEITAS, 0);
        int quantidadeLimpas = preferences.getInt(QUANTIDADE_LIMPAS, 0);
        int quantidadeTotal = preferences.getInt(QUANTIDADE_TOTAL, 0);
        long dataInicio = preferences.getLong(DATA_INICIO, System.currentTimeMillis());

        Estatisticas estatisticas = new Estatisticas();
        estatisticas.setQuantidadeSuspeitas(quantidadeSuspeitas);
        estatisticas.setQuantidadeLimpas(quantidadeLimpas);
        estatisticas.setQuantidadeTotal(quantidadeTotal);
        estatisticas.setDataInicio(new Date(dataInicio));

        return estatisticas;
    }

    private Estatisticas resgatarEstatisticasGerais() throws EstatisticasResgatarException {
        return resgatar(getPreferences());
    }

    private Estatisticas resgatarEstatisticasMes() throws EstatisticasResgatarException {
        return resgatar(getPreferencesMes());
    }

	public boolean salvarEstatisticas(SharedPreferences preferences, Estatisticas estatisticas) throws EstatisticasSalvarException {
		SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(QUANTIDADE_SUSPEITAS, estatisticas.getQuantidadeSuspeitas());
        editor.putInt(QUANTIDADE_LIMPAS, estatisticas.getQuantidadeLimpas());
        editor.putInt(QUANTIDADE_TOTAL, estatisticas.getQuantidadeTotal());
        editor.putLong(DATA_INICIO, estatisticas.getDataInicio().getTime());
		if(!editor.commit()) {
			throw new EstatisticasSalvarException(TAG, context);
		}
		return true;
	}
	
}
