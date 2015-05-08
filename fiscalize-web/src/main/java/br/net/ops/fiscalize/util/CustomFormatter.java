package br.net.ops.fiscalize.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class CustomFormatter extends Formatter {

	public CustomFormatter() {
		super();
	}

	public String format(LogRecord record) {
		
		StringBuffer sb = new StringBuffer();
		
		Date date = new Date(record.getMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		sb.append(sdf.format(date));
		sb.append(" (" + record.getLevel().getName() + ") - ");
		sb.append(formatMessage(record));
		sb.append(System.getProperty( "line.separator" ));

		String retorno = sb.toString();
		
		return retorno;
	}
	
}
