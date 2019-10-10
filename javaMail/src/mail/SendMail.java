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
//			message.setSubject("���һ������ʼ���������");
//			message.setText("����ʼ������û�е���˼");
//			message.setFrom(new InternetAddress("810458068@qq.com"));

			message = createMimeMessage(session, sendMail, reviceMail);
			
			Transport transport = session.getTransport();
			transport.connect("smtp.qq.com", 25 , "810458068@qq.com", "kzmuprjqoondbdee");
			
			transport.sendMessage(message , new Address[]{new InternetAddress(reviceMail), new InternetAddress("15986658309@163.com")});
			 
			System.out.println("�ʼ����ͳɹ�...");
			transport.close();

			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * �Զ��巢���ʼ�  �ɷ���ͼƬ ����
	 * @param session
	 * @param sendMail  	�����������ַ
	 * @param reviceMail	�ռ��������ַ
	 * @return
	 */
	public static MimeMessage createMimeMessage(Session session, String sendMail, String reviceMail){
		
		//����һ���������
		MimeMessage message = new MimeMessage(session);
		
		try {
			//���÷����˵�ַ
			message.setFrom(new InternetAddress(sendMail, "feiyu", "UTF-8"));
			//�����ռ��������ַ
			message.addRecipient(RecipientType.TO, new InternetAddress(reviceMail, "���¸�123", "UTF-8"));
			//��������
			message.setSubject("java����smtp�ʼ�����ͼƬ");
			
			//����һ��ͼƬ�ڵ�
			MimeBodyPart image = new MimeBodyPart();
			//��ȡ�����ļ�
			DataHandler dh = new DataHandler(new FileDataSource("./image/SGS128.png"));
			//��ͼ�����ӵ����ݽڵ�
			image.setDataHandler(dh);
			image.setContentID("image_fairy_tail");	//����һ��Ψһ����id ������õ���
			
			//����һ���ı��ڵ�
			MimeBodyPart text = new MimeBodyPart();
			//���������ǽ�����ͼƬ���ӵ��ʼ���������	ʵ����Ҳ������http��������������ͼƬ
			text.setContent("����һ��ͼƬ<br/><img src='cid:image_fairy_tail'/>", "text/html;charset=UTF-8");
			
			//�ı�+ͼƬ ���ı���ͼƬ���ó�һ����Ͻڵ�
			MimeMultipart mmTextImage = new MimeMultipart();
			mmTextImage.addBodyPart(image);
			mmTextImage.addBodyPart(text);
			mmTextImage.setSubType("related");	//������ϵ
		
			//���ı�+ͼƬ�Ļ�Ͻڵ�ת��һ����ͨ�ڵ�
			//�������ӵ��ʼ���content�е�Ҫ��һ����ͨ�ڵ�
			MimeBodyPart imageText = new MimeBodyPart();
			imageText.setContent(mmTextImage);
			
			//����һ�����ڵ�
			MimeBodyPart attachment = new MimeBodyPart();
			DataHandler dh2 = new DataHandler(new FileDataSource("./doc/default.doc"));	//��ȡ�����ļ�
			//���������ӵ����ݽڽڽڵ�
			attachment.setDataHandler(dh2);
			attachment.setFileName(MimeUtility.encodeText(dh2.getName()));
			
			//�����ı�+ͼ��Ƭ��Ϲ�ϵ
			MimeMultipart mm = new MimeMultipart();
			mm.addBodyPart(imageText);
			mm.addBodyPart(attachment);
			mm.setSubType("mixed");	//��Ϲ�ϵ
			
			//������ ���ʼ��Ĺ�ϵ �������յĻ�Ͻڵ���Ϊ�ʼ������ݣ�
			message.setContent(mm);
			
			//���÷���ʱ��
			message.setSentDate(new Date());
			
			//�����������������
			message.saveChanges();
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		return message;
	}
	
}