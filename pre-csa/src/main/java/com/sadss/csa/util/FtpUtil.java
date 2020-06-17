package com.sadss.csa.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Component;

@Component
public class FtpUtil {
	
	private static String user = "transfer";
	private static String password = "Grup01nd";
	private static String ip = "192.168.0.216";
	private static Integer port = 21 ;
	private static String urlCalendario = "/RepositorioWeb/SCA/Calendarios/";
	
	/**
	 * Almacena archivo Excel de Calendario
	 * @param nombreArchivo
	 * @param bytes
	 * @return
	 */
	public static boolean cargarArchivoCalendario(String nombreArchivo, byte[] bytes) {
		
		boolean estatus = false;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		try {
			baos.write(bytes);
			
			URL urlFtp = new URL("ftp://"+user+":"+password+"@"+ip+urlCalendario+nombreArchivo+";type=i");
			
			System.out.println(urlFtp.toString());
			
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
	
	/**
	 * Elimina el archivo del Calendario
	 * @param nombreArchivo
	 * @throws IOException
	 */
	public static void eliminarCalendario(String nombreArchivo) throws IOException {
		FTPClient client = new FTPClient();
		
		System.out.println(user);
		System.out.println(password);
		System.out.println(urlCalendario);
		System.out.println(port);
		
		try {
			client.connect(ip, port);
			client.login(user, password);
			client.deleteFile(urlCalendario.concat(nombreArchivo));
			client.logout();
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			client.disconnect();
		}

	}

}
