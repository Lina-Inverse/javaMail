package mail;

public class MultiMailSenderInfo extends MailSendInfo {

	//�ʼ����� �����Ƕ���� �������
	private String[] revices;
	
	//�ʼ��ĳ����� Ҳ�����Ƕ��
	private String[] ccs;
	
	public String[] getRevices() {
		return revices;
	}

	public void setRevices(String[] revices) {
		this.revices = revices;
	}

	public String[] getCcs() {
		return ccs;
	}

	public void setCcs(String[] ccs) {
		this.ccs = ccs;
	}
	
}
