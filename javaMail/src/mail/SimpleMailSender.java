package mail;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * ���ʼ�������  ����������
 * @author lenovo
 *
 */
public class SimpleMailSender {

	/**
	 * �ı���ʽ�ķ���
	 * @param mailInfo
	 */
	public static boolean sendTextMail(MailSendInfo mailInfo) {
		//�ж��Ƿ���Ҫ������֤
		MyAuthenticator authenticator = null;
		
		if (mailInfo.isValidate()) {
			//�����Ҫ����֤��  ����һ��������֤��
			authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
		}
		
		try {
			//�����ʼ��Ự���Ժ�������֤������һ��session
			Session mailSession = Session.getInstance(mailInfo.getProperties(), authenticator);
			mailSession.setDebug(true);
			//����session����һ���ʼ���Ϣ
			Message message = new MimeMessage(mailSession);
			//�����ߵ�ַ
			Address from = new InternetAddress(mailInfo.getFromAddress(), mailInfo.getUserName(), "UTF-8");
			message.setFrom(from);
			//������
			Address[] tos = new Address[mailInfo.getToAdderss().length];
			for (int i =0; i < tos.length; i++) {
				Address to = new InternetAddress(mailInfo.getToAdderss()[i]);
				tos[i] = to;
			}
			message.setRecipients(RecipientType.TO, tos);
			
			//�����ʼ�����
			message.setSubject(mailInfo.getSubject());
			message.setText(mailInfo.getContent());
			message.setSentDate(new Date());
			
			return sendMial(mailSession, mailInfo, message);
			
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return false;
		
	}
	
	
	/**
	 * ��html�ĸ�ʽ����
	 * @param maiInfo
	 * @return
	 */
	public static boolean sendHtmlMail(MailSendInfo mailInfo){
		MyAuthenticator authenticator = null;
		//�ж��Ƿ���Ҫ������֤
		if (mailInfo.isValidate()) {
			authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
		}
		
		try {
			//����һ���ʼ�session
			Session session = Session.getInstance(mailInfo.getProperties(), authenticator);
			session.setDebug(true);
			//����һ���ʼ���Ϣ
			Message message = new MimeMessage(session);
			//�����˵�ַ
			Address from = new InternetAddress(mailInfo.getFromAddress(), mailInfo.getUserName(), "UTF-8");
			message.setFrom(from);
			//�ռ��˵�ַ
			Address[] tos = new Address[mailInfo.getToAdderss().length];
			for (int i =0; i < tos.length; i++) {
				Address to = new InternetAddress(mailInfo.getToAdderss()[i]);
				tos[i] = to;
			}
			message.setRecipients(RecipientType.TO, tos);
			
			//������Ϣ����ͷ���ʱ��
			message.setSubject(mailInfo.getSubject());
			message.setSentDate(new Date());
			
			//��html�ĸ�ʽ����
			//multipart�ǲ��������࣬ ����mimeBodyPat���͵Ķ���
			Multipart part = new MimeMultipart();
			//����һ��mimebodypart��html����
			BodyPart body = new MimeBodyPart();
			body.setContent(mailInfo.getContent(), "text/html;charset=UTF-8");
			//��body�������ӵ�������
			part.addBodyPart(body);
			
			message.setContent(part);
			//������Ϣ
			return sendMial(session, mailInfo, message);
			
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * �����ʼ�
	 * @param session	�ʼ���session
	 * @param mailInfo	�ʼ�����
	 * @param message	��Ϣ����
	 * @return
	 */
	public static boolean sendMial(Session session, MailSendInfo mailInfo, Message message) {
		Transport transport;
		try {
			transport = session.getTransport();
			transport.connect(mailInfo.getMailServerHost(), mailInfo.getMailServerPort(),
					mailInfo.getFromAddress(), mailInfo.getPassword());
			
			Address[] tos = new Address[mailInfo.getToAdderss().length];
			for (int i =0; i < tos.length; i++) {
				Address to = new InternetAddress(mailInfo.getToAdderss()[i]);
				tos[i] = to;
			}
			
			transport.sendMessage(message , tos);
			transport.close();
			
			return true;
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	//������
	public static void main(String[] args) {
		
		MailSendInfo mailInfo = new MailSendInfo();
		mailInfo.setMailServerHost("smtp.qq.com");
		mailInfo.setMailServerPort(25);
		mailInfo.setFromAddress("810458068@qq.com");
		mailInfo.setToAdderss(new String[]{"15986658309@163.com", "4987035@qq.com"});
		mailInfo.setPassword("jhvkxbbqntetbfib");
//		mailInfo.setPassword("kzmuprjqoondbdee");
		mailInfo.setUserName("feiyu");
		mailInfo.setSubject("����java send email");
		mailInfo.setValidate(true);
		mailInfo.setContent("������������");
		
		boolean sendOK = false;
		sendOK = sendTextMail(mailInfo);
//		sendOK = sendHtmlMail(mailInfo);
		if (sendOK) {
			System.out.println("���ͳɹ�");	
		} else {
			System.out.println("����ʧ��");
		}
		
	}
	
	
}