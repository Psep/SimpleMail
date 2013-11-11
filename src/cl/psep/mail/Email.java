package cl.psep.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import cl.psep.bean.EmailBean;
import cl.psep.util.XMLUtil;

/**
 * @author psep
 *
 */
public class Email implements EmailInterface {
	
	private static Logger logger	= Logger.getLogger(Email.class);
	private static String nullBean	= "EmailBean object is null";
	private static String smtp		= "smtp";
	private static String smtpMail	= "mail." + smtp + ".";
	private static String format	= "text/html";
	private EmailBean mailBean;
	
	/**
	 * @param emailBean
	 */
	public Email(EmailBean emailBean){
		logger.info("Iniciando Email");
		this.mailBean = emailBean;
	}
	
	/**
	 * @throws Exception
	 * @param path
	 */
	public void loadFromXML(String path) throws Exception{
		if(path == null){
			throw new Exception("Error! No se encuentra ruta del path");
		}else if(this.mailBean == null){
			throw new Exception("Error! No existe instancia de EmailBean");
		}else{
			this.mailBean.setFromMail(XMLUtil.getConsultaXML(path, "fromMail"));
			this.mailBean.setServer(XMLUtil.getConsultaXML(path, "server"));
			this.mailBean.setPort(XMLUtil.getConsultaXML(path, "port"));
			this.mailBean.setUsername(XMLUtil.getConsultaXML(path, "username"));
			this.mailBean.setPassword(XMLUtil.getConsultaXML(path, "password"));
			this.mailBean.setPort(XMLUtil.getConsultaXML(path, "port"));
			this.mailBean.setAuth(Boolean.valueOf(XMLUtil.getConsultaXML(path, "auth")));
			this.mailBean.setDebug(Boolean.valueOf(XMLUtil.getConsultaXML(path, "debug")));
			this.mailBean.setFallback(Boolean.valueOf(XMLUtil.getConsultaXML(path, "fallback")));
			this.mailBean.setStarttls(Boolean.valueOf(XMLUtil.getConsultaXML(path, "starttls")));
		}
	}
	
	/**
	 * @throws Exception
	 * @return validation
	 */
	public Boolean sendMail() throws Exception{
		logger.info("Iniciando sendMail()");
		
		boolean result = false;
		
		if(this.mailBean == null){
			throw new Exception(nullBean);
		}else{
			Properties props= this.emailProps();
			Transport t		= null;
			Session session	= null;
			
			try{
				if(!this.mailBean.getAuth() || this.mailBean.getUsername() == null){
					session = Session.getDefaultInstance(props);
				}else{
					SMTPAuthenticator auth = new SMTPAuthenticator(this.mailBean.getUsername(), this.mailBean.getPassword());
					session = Session.getInstance(props, auth);
				}
				
				session.setDebug(this.mailBean.getDebug());
				
				logger.info("Format message");
				MimeMessage msg = new MimeMessage(session);
				msg.setFrom(new InternetAddress(this.mailBean.getFromMail()));
				msg.addRecipients(Message.RecipientType.TO, this.mailBean.getToMail());			
				msg.setSubject(this.mailBean.getSubject());
				msg.setContent(this.personalizeMessage(), format);
				
				t = session.getTransport(smtp);
				t.connect(this.mailBean.getFromMail(), this.mailBean.getPassword());
				
				logger.info("Sending message...");
				t.sendMessage(msg, msg.getAllRecipients());
				
				logger.info("Send message");
				result = true;
				
			}catch (Exception e) {
				logger.error(e, e);
			}finally{
				this.closeTransport(t);
			}
		}
		
		return result;
	}
	
	/**
	 * @return
	 */
	private Properties emailProps(){
		Properties props = new Properties();
		props.put(smtpMail + "user", this.mailBean.getFromMail());
		props.put(smtpMail + "host", this.mailBean.getServer());
		props.put(smtpMail + "port", this.mailBean.getPort());
		props.put(smtpMail + "starttls.enable", this.mailBean.getStarttls().toString());
		props.put(smtpMail + "auth", this.mailBean.getAuth().toString());
		
		return props;
	}
	
	/**
	 * @param t
	 */
	private void closeTransport(Transport t){
		if(t != null){
			try {
				t.close();
			} catch (MessagingException e) {
				logger.error(e, e);
			}
		}
	}
	
	/**
	 * @return msg
	 */
	private String personalizeMessage(){
		StringBuilder msg = new StringBuilder();
		msg.append(this.mailBean.getMessage());
		msg.append("<br />");
		msg.append("<p>Mensaje enviado por API SimpleMail para Java</p>");
		
		return msg.toString();
	}
}
