package mail;

import java.io.UnsupportedEncodingException;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class MultiMailSender {

	/**
	 * ���͸���������
	 * @param mailInfo
	 * @return
	 */
	public static boolean sendMailtoMultiCC(MultiMailSenderInfo mailInfo) {
		
		MyAuthenticator authenticator = null;
		
		if (mailInfo.isValidate()) {
			authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
		}
		
		try {
			Session session = Session.getInstance(mailInfo.getProperties(), authenticator);
			session.setDebug(true);
			Message message = new MimeMessage(session);
			//������
			Address from = new InternetAddress(mailInfo.getFromAddress(), "feiyu", "UTF-8");
			message.setFrom(from);
			//�ռ���
			Address[] tos = new Address[mailInfo.getToAdderss().length];
			for (int i = 0; i < tos.length; i++) {
				Address to = new InternetAddress(mailInfo.getToAdderss()[i]);
				tos[i] = to;
			}
			message.setRecipients(RecipientType.TO, tos);
			
			//������
			Address[] ccs = new Address[mailInfo.getCcs().length];
			for (int i = 0; i < ccs.length; i++) {
				Address cc = new InternetAddress(mailInfo.getCcs()[i]);
				ccs[i] = cc;
			}
			message.setRecipients(RecipientType.CC, ccs);
			
			Multipart part = new MimeMultipart();
			BodyPart body = new MimeBodyPart();
			body.setContent(mailInfo.getContent(), "text/html;charset=UTF-8");
			part.addBodyPart(body);
			
 			message.setSubject(mailInfo.getSubject());
  			message.setContent(part);
  			
  			return SimpleMailSender.sendMial(session, mailInfo, message);
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	//������
	public static void main(String[] args) {
		
		MultiMailSenderInfo mailInfo = new MultiMailSenderInfo();
		mailInfo.setMailServerHost("smtp.qq.com");
		mailInfo.setMailServerPort(25);
		mailInfo.setFromAddress("810458068@qq.com");
		mailInfo.setToAdderss(new String[]{"15986658309@163.com", "4987035@qq.com"});
		mailInfo.setCcs(new String[]{"290201659@qq.com"});
		mailInfo.setPassword("jhvkxbbqntetbfib");
//		mailInfo.setPassword("kzmuprjqoondbdee");
		mailInfo.setUserName("feiyu");
		mailInfo.setSubject("����java send email");
		mailInfo.setValidate(true);
		mailInfo.setContent("������������");
		
		boolean sendOK = false;
		sendOK = sendMailtoMultiCC(mailInfo);
//		sendOK = sendHtmlMail(mailInfo);
		
		if (sendOK) {
			System.out.println("���ͳɹ�");	
		} else {
			System.out.println("����ʧ��");
		}
		
	}
	
}