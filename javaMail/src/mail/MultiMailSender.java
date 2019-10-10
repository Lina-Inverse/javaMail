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
	 * 发送给带抄送者
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
			//发件者
			Address from = new InternetAddress(mailInfo.getFromAddress(), "feiyu", "UTF-8");
			message.setFrom(from);
			//收件者
			Address[] tos = new Address[mailInfo.getToAdderss().length];
			for (int i = 0; i < tos.length; i++) {
				Address to = new InternetAddress(mailInfo.getToAdderss()[i]);
				tos[i] = to;
			}
			message.setRecipients(RecipientType.TO, tos);
			
			//抄送者
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
	
	//测试下
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
		mailInfo.setSubject("测试java send email");
		mailInfo.setValidate(true);
		mailInfo.setContent("算求，算求，算求");
		
		boolean sendOK = false;
		sendOK = sendMailtoMultiCC(mailInfo);
//		sendOK = sendHtmlMail(mailInfo);
		
		if (sendOK) {
			System.out.println("发送成功");	
		} else {
			System.out.println("发送失败");
		}
		
	}
	
}
