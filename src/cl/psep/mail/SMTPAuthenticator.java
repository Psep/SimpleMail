package cl.psep.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * @author psep
 *
 */
public class SMTPAuthenticator extends Authenticator {
	
	private String	username;
	private	String	password;
	
	/**
	 * @param username
	 * @param password
	 */
	public SMTPAuthenticator(String username, String password){
		this.username = username;
		this.password = password;
	}

	/* (non-Javadoc)
	 * @see javax.mail.Authenticator#getPasswordAuthentication()
	 */
	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(this.username, this.password);
	}

}
