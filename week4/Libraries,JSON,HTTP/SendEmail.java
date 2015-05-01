import org.apache.commons.mail.*;

public class SendEmail {

	public static void main(String[] args) {

		EmailAttachment attachment = new EmailAttachment();
		attachment.setPath("/home/georgi/Downloads/azbW3zq_460sa_v1.gif");
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		attachment.setDescription("Suarez bytes");
		attachment.setName("Suarez");

		try {
			MultiPartEmail email = new MultiPartEmail();

			email.setHostName("smtp.mail.bg");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator(
					"georgi_lyubenov@mail.bg", ""));
			email.setSSLOnConnect(true);

			email.addTo("georgi_lyubenov@mail.bg", "georgi Lyubenov");
			email.setFrom("georgi_lyubenov@mail.bg", "Me");
			email.setSubject("Gif of Suarez");
			email.setMsg("Look and that byte");

			email.attach(attachment);

			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}

	}

}
