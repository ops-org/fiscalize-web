package br.net.ops.fiscalize.preferences;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.Calendar;
import java.util.Date;

import br.net.ops.fiscalize.exception.EstatisticasResgatarException;
import br.net.ops.fiscalize.exception.EstatisticasSalvarException;
import br.net.ops.fiscalize.vo.Estatisticas;
import br.net.ops.fiscalize.vo.Suspeita;

public class EstatisticasPreferences {

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

    public EstatisticasPreferences(Context context) {
        this.context = context;

        Calendar calendar = Calendar.getInstance();
        this.mes = calendar.get(Calendar.MONTH) + 1; // MONTH vai de 0 a 11, por isso somei 1
        this.ano = calendar.get(Calendar.YEAR);
    }

	public EstatisticasPreferences(Context context, int mes, int ano) {
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

    public Estatisticas resgatarEstatisticasGerais() {
        Estatisticas estatisticas;
        try {
            estatisticas = resgatar(getPreferences());
        } catch (EstatisticasResgatarException e) {
            estatisticas = Estatisticas.criarEstatisticas();
        }
        return estatisticas;
    }

    public Estatisticas resgatarEstatisticasMes() {
        Estatisticas estatisticas;
        try {
            estatisticas = resgatar(getPreferencesMes());
        } catch (EstatisticasResgatarException e) {
            estatisticas = Estatisticas.criarEstatisticas();
        }
        return estatisticas;
    }

    public void incrementarEstatisticas(Suspeita suspeita) throws EstatisticasSalvarException {
        Estatisticas estatisticasGerais = resgatarEstatisticasGerais();
        Estatisticas estatisticasMes = resgatarEstatisticasMes();

        if(suspeita.getSuspeita()) {
            estatisticasGerais.setQuantidadeSuspeitas(estatisticasGerais.getQuantidadeSuspeitas() + 1);
            estatisticasMes.setQuantidadeSuspeitas(estatisticasMes.getQuantidadeSuspeitas() + 1);
        } else {
            estatisticasGerais.setQuantidadeLimpas(estatisticasGerais.getQuantidadeLimpas() + 1);
            estatisticasMes.setQuantidadeLimpas(estatisticasMes.getQuantidadeLimpas() + 1);
        }
        estatisticasGerais.setQuantidadeTotal(estatisticasGerais.getQuantidadeTotal() + 1);
        estatisticasMes.setQuantidadeTotal(estatisticasMes.getQuantidadeTotal() + 1);

        salvarEstatisticas(getPreferences(), estatisticasGerais);
        salvarEstatisticas(getPreferencesMes(), estatisticasMes);
    }

	private boolean salvarEstatisticas(SharedPreferences preferences, Estatisticas estatisticas) throws EstatisticasSalvarException {
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
