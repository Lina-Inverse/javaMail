package mail;

import java.util.Properties;

public class MailSendInfo {

	//�����ʼ��ķ������Ͷ˿�
	private String mailServerHost = "";
	private int mailServerPort = 25;

	//�ʼ������ߵĵ�ַ
	private String fromAddress = "810458068@qq.com";
	//�ʼ������ߵĵ�ַ
	private String[] toAdderss;

	//��¼�ʼ����ͷ��������û���������
	private String userName;
	private String password;
	//�Ƿ���Ҫ��������֤
	private boolean validate = true;
	
	//�ʼ�����
	private String subject;
	//����
	private String content;
	//��������
	private String[] attachfileNames;
	
	
	/**
	 * ��ȡ�ʼ��Ự����
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