package com.sadss.csa.util;
import org.springframework.context.ApplicationContext;
import org.springframework.web.client.RestTemplate;

public class NotificationComponent implements Runnable {
   
   private Mensaje mensaje;
   
   private ApplicationContext appContext;
   
   public NotificationComponent() {
   }
   
   public NotificationComponent(Mensaje mensaje, ApplicationContext appContext) {
       this.mensaje = mensaje;
       this.appContext = appContext;
   }
   
   @Override
   public void run() {
       
	  // return; //Para pruebas de flujos [MFS 20181105] 
	   String ambiente = appContext.getMessage("ambiente",null,null);
	   String urlMail = "";
	   if(ambiente.equals("PROD") || ambiente.equals("DEV")) {
		   urlMail = appContext.getMessage("mail.ws.urlProd",null,null);
	   }else {
		   urlMail = appContext.getMessage("mail.ws.url",null,null);
	   }
       RestTemplate restTemplate = new RestTemplate();
       Object[] vacio = new Object[1];
       System.out.println("Enviando correo a: "+urlMail);
       System.out.println("Mensaje: "+ mensaje);
       restTemplate.postForLocation(urlMail+ "send/simple", mensaje, vacio);
	   
   }
}

