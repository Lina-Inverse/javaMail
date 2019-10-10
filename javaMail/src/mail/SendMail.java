package mail;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class SendMail {

	private static final String sendMail = "810458068@qq.com";
	private static final String reviceMail = "4987035@qq.com";
	
	public static void main(String[] args) {
		
		Properties props = new Properties();
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.transport.protocol", "smtp");
		
		Session session = Session.getInstance(props);
		session.setDebug(true);
		Message message = new MimeMessage(session);
		
		try {
//			message.setSubject("这个一封电子邮件。。。。");
//			message.setText("这个邮件真的是没有点意思");
//			message.setFrom(new InternetAddress("810458068@qq.com"));

			message = createMimeMessage(session, sendMail, reviceMail);
			
			Transport transport = session.getTransport();
			transport.connect("smtp.qq.com", 25 , "810458068@qq.com", "kzmuprjqoondbdee");
			
			transport.sendMessage(message , new Address[]{new InternetAddress(reviceMail), new InternetAddress("15986658309@163.com")});
			 
			System.out.println("邮件发送成功...");
			transport.close();

			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 自定义发送邮件  可发送图片 附件
	 * @param session
	 * @param sendMail  	发件人邮箱地址
	 * @param reviceMail	收件人邮箱地址
	 * @return
	 */
	public static MimeMessage createMimeMessage(Session session, String sendMail, String reviceMail){
		
		//创建一个邮箱对象
		MimeMessage message = new MimeMessage(session);
		
		try {
			//设置发件人地址
			message.setFrom(new InternetAddress(sendMail, "feiyu", "UTF-8"));
			//设置收件人邮箱地址
			message.addRecipient(RecipientType.TO, new InternetAddress(reviceMail, "王勇刚123", "UTF-8"));
			//设置主题
			message.setSubject("java测试smtp邮件发送图片");
			
			//创建一个图片节点
			MimeBodyPart image = new MimeBodyPart();
			//读取本地文件
			DataHandler dh = new DataHandler(new FileDataSource("./image/SGS128.png"));
			//将图片添加到数据节点
			image.setDataHandler(dh);
			image.setContentID("image_fairy_tail");	//添加一个唯一个的id 后面会用到的
			
			//创建一个文本节点
			MimeBodyPart text = new MimeBodyPart();
			//这里添加是将整张图片都加到邮件的内容中	实际上也可以以http的形试添加网络图片
			text.setContent("这是一张图片<br/><img src='cid:image_fairy_tail'/>", "text/html;charset=UTF-8");
			
			//文本+图片 将文本和图片设置成一个混合节点
			MimeMultipart mmTextImage = new MimeMultipart();
			mmTextImage.addBodyPart(image);
			mmTextImage.addBodyPart(text);
			mmTextImage.setSubType("related");	//关联关系
		
			//将文本+图片的混合节点转成一个普通节点
			//最终添加到邮件的content中的要是一个普通节点
			MimeBodyPart imageText = new MimeBodyPart();
			imageText.setContent(mmTextImage);
			
			//创建一个附节点
			MimeBodyPart attachment = new MimeBodyPart();
			DataHandler dh2 = new DataHandler(new FileDataSource("./doc/default.doc"));	//读取本地文件
			//将附件添加到数据节节节点
			attachment.setDataHandler(dh2);
			attachment.setFileName(MimeUtility.encodeText(dh2.getName()));
			
			//设置文本+图牌片混合关系
			MimeMultipart mm = new MimeMultipart();
			mm.addBodyPart(imageText);
			mm.addBodyPart(attachment);
			mm.setSubType("mixed");	//混合关系
			
			//设置整 个邮件的关系 （将最终的混合节点做为邮件的内容）
			message.setContent(mm);
			
			//设置发件时间
			message.setSentDate(new Date());
			
			//保存上面的所有设置
			message.saveChanges();
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		return message;
	}
	
}
