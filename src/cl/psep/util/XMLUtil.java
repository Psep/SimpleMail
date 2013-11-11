package cl.psep.util;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;

/**
 * @author psep
 *
 */
public final class XMLUtil {
	
	/**
	 * 
	 */
	private static Logger logger = Logger.getLogger(XMLUtil.class);

	/**
	 * @param archivo
	 * @param tagConsulta
	 * @return
	 * @throws Exception
	 */
	public static String getConsultaXML(String archivo, String tagConsulta) throws Exception {
		String consulta = null;
		try {
			File file = new File(archivo);
			if (file.exists()) {
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document documento = builder.parse(file);
				consulta = documento.getElementsByTagName(tagConsulta).item(0).getTextContent();
				return consulta;
			}
		} catch (Exception e) {
			logger.error(e, e);
		}
		return null;
	}
	
}
