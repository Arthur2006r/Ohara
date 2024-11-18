package br.com.ohara.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import br.com.ohara.model.Email;
import jakarta.mail.Address;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class EnviarEmailService {

    @Value(value = "${spring.mail.host}")
    private String hostEmail;

    @Value(value = "${spring.mail.port}")
    private String portaEmail;

    @Value(value = "${spring.mail.username}")
    private String nomeUsuarioEmail;

    @Value(value = "${spring.mail.password}")
    private String senhaEmail;

    @Value(value = "${spring.mail.properties.mail.smtp.auth}")
    private String autentificacaoSmtpEmail;

    @Value(value = "${spring.mail.properties.mail.smtp.starttls.enable}")
    private String starttlsHabilitadoEmail;

    private final JavaMailSender remetenteEmail;

    @Autowired
    public EnviarEmailService(JavaMailSender remetenteEmail) {
        this.remetenteEmail = remetenteEmail;
    }

    public void enviarMensagemSimples(Email email) {
        SimpleMailMessage mensagem = new SimpleMailMessage();
        mensagem.setFrom(this.hostEmail);
        mensagem.setTo(email.getPara());
        mensagem.setSubject(email.getAssunto());
        mensagem.setText(email.getTexto());
        remetenteEmail.send(mensagem);
    }

    public void enviarMensagemComAnexo(String para, String assunto, String texto, String caminhoParaAnexo) throws MessagingException {
        MimeMessage mensagem = remetenteEmail.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mensagem, true);

        helper.setFrom(this.hostEmail);
        helper.setTo(para);
        helper.setSubject(assunto);
        helper.setText(texto);

        FileSystemResource arquivo = new FileSystemResource(new File(caminhoParaAnexo));
        helper.addAttachment("Fatura", arquivo);

        remetenteEmail.send(mensagem);
    }

    public void enviarEmail(String para, String assunto, String texto) throws AddressException, MessagingException, IOException {
        Properties propriedades = new Properties();
        propriedades.put("mail.smtp.auth", this.autentificacaoSmtpEmail);
        propriedades.put("mail.smtp.starttls.enable", this.starttlsHabilitadoEmail);
        propriedades.put("mail.smtp.host", this.hostEmail);
        propriedades.put("mail.smtp.port", this.portaEmail);

        Session sessao = Session.getInstance(propriedades, new jakarta.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EnviarEmailService.this.nomeUsuarioEmail, EnviarEmailService.this.senhaEmail);
            }
        });
        Message msg = new MimeMessage(sessao);
        msg.setFrom(new InternetAddress(this.nomeUsuarioEmail, false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(para));
        msg.setSubject(assunto);
        msg.setContent(texto, "text/html");
        msg.setSentDate(new Date());

        Transport.send(msg);
    }

    public void enviarEmail2(String para, String assunto, String texto) {
        Properties propriedades = new Properties();
        propriedades.put("mail.smtp.host", "smtp.gmail.com");
        propriedades.put("mail.smtp.port", "465");
        propriedades.put("mail.smtp.auth", "true");
        propriedades.put("mail.smtp.socketFactory.port", "465");
        propriedades.put("mail.smtp.socketFactory.class", "jakarta.net.ssl.SSLSocketFactory");

        Session sessao = Session.getDefaultInstance(propriedades,
                new jakarta.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(EnviarEmailService.this.nomeUsuarioEmail, "ciaprofursjvgzfm");
                    }
                });

        sessao.setDebug(true);

        try {
            Message mensagem = new MimeMessage(sessao);
            mensagem.setFrom(new InternetAddress(this.nomeUsuarioEmail));

            Address[] paraUsuario = InternetAddress.parse(para);
            mensagem.setRecipients(Message.RecipientType.TO, paraUsuario);
            mensagem.setSubject("Enviando email com JavaMail");
            mensagem.setText("Enviei este email utilizando JavaMail com minha conta GMail!");

            Transport.send(mensagem);
            System.out.println("Feito!!!");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
