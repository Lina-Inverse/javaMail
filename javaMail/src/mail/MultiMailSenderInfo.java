package mail;

public class MultiMailSenderInfo extends MailSendInfo {

	//邮件接收 可以是多个， 搞个数组
	private String[] revices;
	
	//邮件的抄送者 也可以是多个
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
