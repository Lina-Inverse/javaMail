package mail;

import java.util.Properties;

public class MailSendInfo {

	//发送邮件的服务器和端口
	private String mailServerHost = "";
	private int mailServerPort = 25;

	//邮件发送者的地址
	private String fromAddress ;
	//邮件接收者的地址
	private String[] toAdderss;

	//登录邮件发送服务器的用户名和密码
	private String userName;
	private String password;
	//是否需要自身份验证
	private boolean validate = true;
	
	//邮件主题
	private String subject;
	//内容
	private String content;
	//附件名称
	private String[] attachfileNames;
	
	
	/**
	 * 获取邮件会话属性
	 * @return
	 */
	public Properties getProperties(){
		
		Properties prop = new Properties();
		prop.put("mail.smtp.host", this.mailServerHost);
		prop.put("mail.smtp.port", this.mailServerPort);
		prop.put("mail.smtp.auth", this.validate);
		return prop;
	}


	public String getMailServerHost() {
		return mailServerHost;
	}

	public void setMailServerHost(String mailServerHost) {
		this.mailServerHost = mailServerHost;
	}

	public int getMailServerPort() {
		return mailServerPort;
	}

	public void setMailServerPort(int mailServerPort) {
		this.mailServerPort = mailServerPort;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String[] getToAdderss() {
		return toAdderss;
	}

	public void setToAdderss(String[] toAdderss) {
		this.toAdderss = toAdderss;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String[] getAttachfileNames() {
		return attachfileNames;
	}

	public void setAttachfileNames(String[] attachfileNames) {
		this.attachfileNames = attachfileNames;
	}
	
}
