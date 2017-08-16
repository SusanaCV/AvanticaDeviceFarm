
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author susan
 */
// File Name SendHTMLEmail.java
import java.util.*;
import javax.mail.*;   
import javax.mail.internet.*;
            
public class SendHTMLEmail {

    private final static String style = "style=\"margin: auto;width :90%; position:absolute; border: solid 1px;	padding: 10px;"
            + "background: rgba(255,255,255,1);"
            + "background: -moz-linear-gradient(45deg, rgba(255,255,255,1) 0%, rgba(255,255,255,1) 59%, rgba(232,150,63,1) 72%, rgba(255,51,0,1) 84%, rgba(255,217,207,1) 97%, rgba(255,255,255,1) 100%);"
            + "background: -webkit-gradient(left bottom, right top, color-stop(0%, rgba(255,255,255,1)), color-stop(59%, rgba(255,255,255,1)), color-stop(72%, rgba(232,150,63,1)), color-stop(84%, rgba(255,51,0,1)), color-stop(97%, rgba(255,217,207,1)), color-stop(100%, rgba(255,255,255,1)));"
            + "background: -webkit-linear-gradient(45deg, rgba(255,255,255,1) 0%, rgba(255,255,255,1) 59%, rgba(232,150,63,1) 72%, rgba(255,51,0,1) 84%, rgba(255,217,207,1) 97%, rgba(255,255,255,1) 100%);"
            + "background: -o-linear-gradient(45deg, rgba(255,255,255,1) 0%, rgba(255,255,255,1) 59%, rgba(232,150,63,1) 72%, rgba(255,51,0,1) 84%, rgba(255,217,207,1) 97%, rgba(255,255,255,1) 100%);"
            + "background: -ms-linear-gradient(45deg, rgba(255,255,255,1) 0%, rgba(255,255,255,1) 59%, rgba(232,150,63,1) 72%, rgba(255,51,0,1) 84%, rgba(255,217,207,1) 97%, rgba(255,255,255,1) 100%);"
            + "background: linear-gradient(45deg, rgba(255,255,255,1) 0%, rgba(255,255,255,1) 59%, rgba(232,150,63,1) 72%, rgba(255,51,0,1) 84%, rgba(255,217,207,1) 97%, rgba(255,255,255,1) 100%);"
            + "filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#ffffff', endColorstr='#ffffff', GradientType=1 );\"";

    public static void sendMail(String to,String name, String message) {
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        // Get a Properties object
        Properties props = System.getProperties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.auth", "true");
      //  props.put("mail.debug", "true");
        props.put("mail.store.protocol", "pop3");
        props.put("mail.transport.protocol", "smtp");
        final String username = "susanacorrales03@gmail.com";//
        final String password = "02122009";
        try {
            Session session = Session.getDefaultInstance(props,
                    new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            // -- Create a new message --
            Message msg = new MimeMessage(session);

            // -- Set the FROM and TO fields --
            msg.setFrom(new InternetAddress("valverde.alfaro.jimmy@gmail.com"));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            msg.setSubject("Hello");
            msg.setContent(
                    "<div " + style + " >"
                    + "	<h1 style=\"width:100%;\" align=\"center\" >Hola "+name+"</h1> <br>"
                  //  + "	<img style=\"width:40%;height:auto; float: left;margin: 0 20px 20px 0;\" src=\"http://images.samsung.com/is/image/samsung/p5/mx/smartphones/mx_SM-N920GZSATCE_001_Front_silver.png?$ORIGIN_PNG$\">"
                    + "	<p style=\"font-size:18px ;text-align: justify;\">" + message
                    + "\n Has click <a href=\"http://localhost:8383/AvanticaDeviceFarm/index.html\" target=\"_blank\">aqui</a> para acceder a nuestra pagina web</p>"
                    + "<br><br><br>"
                    + "	<table>"
                    + "		<tr>"
                    + "             <td><b>Device farm service</b></td>"
                    + "		</tr>"
                    + "		<tr>"
                    + "             <td>phone: CR 506.xxxx.xxxx | x xxxx</td>"
                    + "		</tr>"
                    + "		<tr>"
                    + "             <td>skype: xxx.xx</td>"
                    + "		</tr>"
                    + "		<tr>"
                    + "             <td><a href=\"www.avantica.net\" target=\"_blank\">www.avantica.net</a></td>"
                    + "		</tr>"
                    + "		<tr>"
                    + "             <td>"
                    + "             <img src=\"https://secure.aadcdn.microsoftonline-p.com/dbd5a2dd-6pcamfaswd3hz-rjnxajgvsmxjalfn8lcuhei5ql2is/logintenantbranding/0/bannerlogo?ts=635775057089320587?ts=\">"
                    + "             </td>"
                    + "		</tr>"
                    + "	</table>"
                    + "</div>"
                    + "", "text/html");
            msg.setSentDate(new Date());
            Transport.send(msg);
            System.out.println("Message sent.");
        } catch (MessagingException e) {            
            System.out.println("Message failed");
        }
    }
}
