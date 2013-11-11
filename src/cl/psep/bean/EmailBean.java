package cl.psep.bean;

import java.io.Serializable;

/**
 * @author psep
 *
 */
public class EmailBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3723112069409755509L;
	private String	toMail;
	private String	fromMail;
	private String	subject;
	private String	message;
	private String	username;
	private String	password;
	private	String	server;
	private	String	port;
	private	Boolean	starttls;
	private	Boolean	auth;
	private	Boolean fallback;
	private Boolean debug; 
	
	public String getToMail() {
		return toMail;
	}
	public void setToMail(String toMail) {
		this.toMail = toMail;
	}
	public String getFromMail() {
		return fromMail;
	}
	public void setFromMail(String fromMail) {
		this.fromMail = fromMail;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public Boolean getStarttls() {
		return starttls;
	}
	public void setStarttls(Boolean starttls) {
		this.starttls = starttls;
	}
	public Boolean getAuth() {
		return auth;
	}
	public void setAuth(Boolean auth) {
		this.auth = auth;
	}
	public Boolean getFallback() {
		return fallback;
	}
	public void setFallback(Boolean fallback) {
		this.fallback = fallback;
	}
	public Boolean getDebug() {
		return debug;
	}
	public void setDebug(Boolean debug) {
		this.debug = debug;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

}
