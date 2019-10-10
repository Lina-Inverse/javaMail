package mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 邮件身份认证器， 在发送邮件前认证
 * @author lenovo
 *
 */
public class MyAuthenticator extends Authenticator {

	//登录发送邮件服务器的用户名
	private String userName;
	//登录发送邮件服务器的密码
	private String password;
	
	public MyAuthenticator() {
		
	}
	
	public MyAuthenticator(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	
	/**
	 * 重写 获取密码认证器方法
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
