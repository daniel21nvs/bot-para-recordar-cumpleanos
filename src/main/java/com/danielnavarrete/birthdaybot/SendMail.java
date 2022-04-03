package com.danielnavarrete.birthdaybot;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import java.io.IOException;

public class SendMail {



    public void sendMail() throws IOException {
        // Ingresar email para from y to
        Email from = new Email("");
        Email to = new Email("");
        String subject = "Sending with Twilio SendGrid is fun";
        Content content = new Content("text/plain","and easy to do anywhere, even with Java!");
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(""); //Ingresar API_KEY
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException e) {
            throw e;
        }
    }

}
