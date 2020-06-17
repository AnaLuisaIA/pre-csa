package com.sadss.csa.util;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FtpUtil {
	
	private static String user="";
	private static String password="";
	private static String ip="";
	//private static Integer port ;
	private static String urlCalendario = "";
	
	@Value("${ftp.user}")
	public static void setUser(String user) {
		FtpUtil.user = user;
	}
	
	@Value("${ftp.password}")
	public static void setPassword(String password) {
		FtpUtil.password = password;
	}
	
	@Value("#{'${ambiente}' == 'PROD' ? '${ftp.ipProd}' : '${ftp.ip}'}")
	public static void setIp(String ip) {
		FtpUtil.ip = ip;
	}
	
	/*@Value("${ftp.port}")
	public static void setPort(Integer port) {
		FtpUtil.port = port;
	}*/
	
	@Value("${ftp.url.Calendario}")
	public static void setUrlCalendario(String urlCalendario) {
		FtpUtil.urlCalendario = urlCalendario;
	}
	
	public static boolean cargarArchivoCalendario(String nombreArchivo, byte[] bytes) {
		
		boolean estatus = false;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		try {
			baos.write(bytes);
			
			URL urlFtp = new URL("ftp://"+user+":"+password+"@"+ip+urlCalendario+nombreArchivo+";type=i");
			URLConnection urlc = urlFtp.openConnection();
			OutputStream out = urlc.getOutputStream();
			
			baos.writeTo(out);
			out.flush();
			out.close();
			
			
			estatus=true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return estatus;
		
	}

}
