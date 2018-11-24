package br.edu.unitri.posjava.tcc.med4you.jobs;

import br.edu.unitri.posjava.tcc.med4you.model.Reminder;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.HttpHeaders;

/**
 * Created by pauloho on 10/09/18.
 */
public class ReminderNotificationJob implements Job {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void execute(JobExecutionContext context)
            throws JobExecutionException {

        Reminder reminder = (Reminder) context.getMergedJobDataMap().get("REMINDER");
        //TROCAR AQUI PELA CHAMADA DO SERVICO REST QUE ENVIA AS NOTIFICAÇÕES
        logger.info("Notificação enviada com sucesso!");
        sendPost(reminder.getUser().getFirebaseToken(),"Hora de tomar remédio!",reminder.getDosage() + " de " + reminder.getMedicine().getName());




    }



    private void sendPost(String token, String titulo, String corpo) {
        try {
            String payload = "{\"notification\": {\"title\": \"" + titulo + "\",\"body\": \"" + corpo + "\"},\"to\": \"" + token + "\"}";
            System.out.println(payload);
            StringEntity entity = new StringEntity(payload, ContentType.APPLICATION_FORM_URLENCODED);
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost request = new HttpPost("https://fcm.googleapis.com/fcm/send");
            request.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
            request.setHeader(HttpHeaders.AUTHORIZATION, "key=AAAA_OPkdss:APA91bGCLEbMgDRBiPwJjrUxW9VBE7iDUEWkG6XEabOW4Z4_qxlymMPgw23akbKF_stVktGCxEVDUrNnOZqGgA7W89NkdtrdpYKGs8nloMgip-iAtoBvodbFeGJJ1t_2W8M3698gc7TO");

            request.setEntity(entity);
            HttpResponse response = httpClient.execute(request);
            System.out.println(response.getStatusLine().getStatusCode());
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            System.out.println(request.getAllHeaders());
            System.out.println(request.getEntity());
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
