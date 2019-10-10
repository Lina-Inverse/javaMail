package mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * �ʼ������֤���� �ڷ����ʼ�ǰ��֤
 * @author lenovo
 *
 */
public class MyAuthenticator extends Authenticator {

	//��¼�����ʼ����������û���
	private String userName;
	//��¼�����ʼ�������������
	private String password;
	
	public MyAuthenticator() {
		
	}
	
	public MyAuthenticator(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	
	/**
	 * ��д ��ȡ������֤������
	 */
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(userName, password);
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
	

}
