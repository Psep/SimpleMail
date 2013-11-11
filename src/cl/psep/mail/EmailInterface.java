package cl.psep.mail;

/**
 * @author psep
 *
 */
public interface EmailInterface {
	
	/**
	 * @return
	 * @throws Exception
	 */
	public Boolean sendMail() throws Exception;
	
	/**
	 * @param path
	 * @throws Exception 
	 */
	public void loadFromXML(String path) throws Exception;

}
