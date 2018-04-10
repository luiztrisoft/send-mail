package email;

import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Não esqueça de desabilitar a segurança da conta de email:
 * 
 * @author Luiz Alberto Ricardo da Silva
 *
 */

public class App {

	public static void main(String[] args) {

		String to = "luiz.trisoft@gmail.com";
		String subject = "RECUPERAÇÃO DE ACESSO AO SISTEMA";
		String message = "Prezado cliente,\n" + "sua nova senha de acesso ao SISTEMA é: " + passwordGenerator(6)
				+ "\nTente acessar através do endereço http://localhost";

		App app = new App();
		app.sendMail(to, subject, message);

	}

	public void sendMail(String to, String subject, String message) {

		try {
			String host = "smtp.gmail.com";
			String user = "EMAIL";
			String pass = "SENHA";
			String from = "EMAIL";
			boolean sessionaDebug = true;

			Properties p = new Properties();
			p.put("mail.smtp.starttls.enable", "true");
			p.put("mail.smtp.host", host);
			p.put("mail.smtp.port", "587");
			p.put("mail.smtp.auth", "true");
			p.put("mail.smtp.starttls.required", "true");

			Session mailSession = Session.getDefaultInstance(p, null);
			mailSession.setDebug(sessionaDebug);
			Message msg = new MimeMessage(mailSession);
			msg.setFrom(new InternetAddress(from));
			InternetAddress[] address = { new InternetAddress(to) };
			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			msg.setText(message);

			Transport transport = mailSession.getTransport("smtp");
			transport.connect(host, user, pass);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
			System.out.println("MENSAGEM ENVIADA COM SUCESSO!");

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static String passwordGenerator(int len) {
		char[] chart = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
				'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D',
				'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
				'Z' };
		char[] senha = new char[len];
		int chartLenght = chart.length;
		Random rdm = new Random();
		for (int i = 0; i < len; i++)
			senha[i] = chart[rdm.nextInt(chartLenght)];
		return new String(senha);
	}
}
