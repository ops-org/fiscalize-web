package br.net.ops.fiscalize.execute;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import br.net.ops.fiscalize.domain.SingletonParametros;

@Component
public class InicializadorSpring {

	private static final String CONFIG_PATH = "classpath*:application-config.xml";

	// Teste a partir dessa classe. Ao iniciar o spring, o service anotado com @PostConstruct vai iniciar também. 
	public static void main(String[] args) {
		
		if(args.length>=3) {
			int i=0;
			String strTipo = args[i++];
			String strCaminhoXml = args[i++];
			String strCaminhoLog = args[i++];
			String strCaminhoSql = "";
			
			if(strTipo.equalsIgnoreCase(SingletonParametros.TIPO_SCRIPT)) {
				strCaminhoSql = args[i++];
			} else if(!strTipo.equalsIgnoreCase(SingletonParametros.TIPO_DATABASE)) {
				printUsage();
			}
			
			SingletonParametros sParametros = SingletonParametros.getInstance();
			sParametros.setTipo(strTipo);
			sParametros.setCaminhoXml(strCaminhoXml);
			sParametros.setCaminhoLog(strCaminhoLog);
			sParametros.setCaminhoSql(strCaminhoSql);
			
			new ClassPathXmlApplicationContext(CONFIG_PATH);
			
		} else {
			printUsage();
		}
		
	}
	
	public static void printUsage() {
		System.out.println("Impossível iniciar sincronizador! Passe 4 parâmetros, nesta ordem: "
				+ "\n1) Tipo de carga: '" + SingletonParametros.TIPO_DATABASE + "' para carregar o xml direto em banco, ou '" + SingletonParametros.TIPO_SCRIPT + "' para carregar em scripts sql."
				+ "\n2) Caminho completo do xml de entrada. "
				+ "\n4) Caminho completo do arquivo de log. "
				+ "\n3) Caminho completo do sql de saída (apenas para tipo de carga '" + SingletonParametros.TIPO_SCRIPT + "'). "
				+ "\n Exemplo: java -jar cargafiscalize.jar " + SingletonParametros.TIPO_SCRIPT + " c:\\carga.xml c:\\log.xml c:\\carga.sql");
	}

}
