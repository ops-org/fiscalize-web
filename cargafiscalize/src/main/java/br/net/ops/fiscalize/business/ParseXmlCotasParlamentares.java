package br.net.ops.fiscalize.business;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stax.StAXSource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import br.net.ops.fiscalize.domain.Despesa;
import br.net.ops.fiscalize.domain.SingletonParametros;
import br.net.ops.fiscalize.exception.ParseXMLDespesaException;
import br.net.ops.fiscalize.util.Utilidade;

@Component
public class ParseXmlCotasParlamentares {

	//private static final String XML_ENCODING = "CP850";
	private static final String XML_ENCODING = "UTF-16";
	private static final String XML_DESPESA = "DESPESA";
	
	private Logger logger;
	private InputStreamReader inputStreamReader; 
	
	private List<Despesa> despesas = new ArrayList<Despesa>();
	private long inicio;
	
	public ParseXmlCotasParlamentares(){
		inicio = System.currentTimeMillis();
		logger = Utilidade.getLogger();
		
		String strCaminhoXml = SingletonParametros.getInstance().getCaminhoXml();
		try {
			// Abre arquivo XML
			File arquivoXml = new File(strCaminhoXml);
			inputStreamReader = new InputStreamReader(new FileInputStream(arquivoXml), XML_ENCODING);
		} catch(IOException e) {
			throw new RuntimeException("Impossível ler o arquivo! Verifique o caminho e tamanho do arquivo e assegure-se de ter descompactado corretamente! " + strCaminhoXml);
		}
	}
	
	public List<Despesa> parse() throws ParseXMLDespesaException {
		logger.log(Level.INFO, "Iniciando parse do xml de dados das cotas parlamentares...");
	
		try {
			XMLInputFactory xif = XMLInputFactory.newInstance();
	        XMLStreamReader xsr = xif.createXMLStreamReader(inputStreamReader);
	        
	        TransformerFactory tf = TransformerFactory.newInstance();
	        Transformer t = tf.newTransformer();
	        
	        long ultimoLog = System.currentTimeMillis();
	        int iteracao=0;
	        while(xsr.hasNext()) { // Loop em todos os nós iniciais <despesa>
	        	if(xsr.getEventType()==XMLStreamConstants.START_ELEMENT) {
	        		if(xsr.getLocalName().equalsIgnoreCase(XML_DESPESA)) { 
			            DOMResult result = new DOMResult();
			            
			            t.transform(new StAXSource(xsr), result);
			            Node domNode = result.getNode();
			            
			            popularObjetoDespesa(domNode, iteracao);
			            
			            iteracao++;
			            if(System.currentTimeMillis() - ultimoLog > Utilidade.LOG_INTERVAL) {
			            	logger.log(Level.INFO, "Lendo registro " + iteracao);
							ultimoLog = System.currentTimeMillis();
						}
	        		} // else: não é nó <despesa>, ignora
	        	} // else: não é nó inicial, ignora
	            xsr.next();
	        }
	        
	        logger.log(Level.INFO, "Tempo de leitura: " + (System.currentTimeMillis()-inicio)/1000 + " segundos");
	        logger.log(Level.INFO, "Registros lidos: " + iteracao);
	        logger.log(Level.INFO, "Registros válidos: " + despesas.size());
	        
	        return despesas;
		} catch(XMLStreamException | TransformerException e) {
			throw new ParseXMLDespesaException(e);
		} 
		    
	}
	
	private void popularObjetoDespesa(Node domNode, int iteracao) {
		NodeList nodeList = domNode.getChildNodes(); // nó <despesa>
        for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			NodeList nodeListDespesa = node.getChildNodes();
			
			Despesa despesa = new Despesa();
			for(int j = 0; j < nodeListDespesa.getLength(); j++) { // percorrendo nós internos
				Node nodeDespesa = nodeListDespesa.item(j);
				
				if(nodeDespesa!=null) {
					if(nodeDespesa.getFirstChild()!=null) {
						try {
							BeanUtils.setProperty(despesa, nodeDespesa.getNodeName(), nodeDespesa.getFirstChild().getTextContent());
						} catch(InvocationTargetException | IllegalAccessException e) {
							logger.log(Level.SEVERE, "Campo desconhecido no XML!\nIteração: " + iteracao + "\nNode: " + nodeDespesa.getNodeName() + "\nException: " + e.getMessage());
						}
					} // else: se o campo estiver nulo, não há o que preencher (do nothing) 
				} else {
					logger.log(Level.SEVERE, "Nó nulo! Favor verificar a consistência do xml! Iteração: " + iteracao);
				}
			}
			despesas.add(despesa);
		}
	}
	
}