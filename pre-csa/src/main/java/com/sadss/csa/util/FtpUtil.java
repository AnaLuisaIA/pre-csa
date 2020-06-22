package com.sadss.csa.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FtpUtil {

	private static String user = "";
	private static String password = "";
	private static String ip = "";
	private static Integer port;
	private static String urlCalendario = "";

	@Value("${ftp.user}")
	public static void setUser(String user) {
		FtpUtil.user = user;
	}

	@Value("${ftp.password}")
	public static void setPassword(String password) {
		FtpUtil.password = password;
	}

	@Value("${ftp.ip}")
	public static void setIp(String ip) {
		FtpUtil.ip = ip;
	}

	@Value("${ftp.port}")
	public static void setPort(Integer port) {
		FtpUtil.port = port;
	}

	@Value("${ftp.url.Calendario}")
	public static void setUrlCalendario(String urlCalendario) {
		FtpUtil.urlCalendario = urlCalendario;
	}

	/**
	 * Almacena archivo Excel de Calendario
	 * 
	 * @param nombreArchivo
	 * @param bytes
	 * @return
	 */
	public static boolean cargarArchivoCalendario(String nombreArchivo, byte[] bytes) {

		boolean estatus = false;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {
			baos.write(bytes);

			URL urlFtp = new URL(
					"ftp://" + user + ":" + password + "@" + ip + urlCalendario + nombreArchivo + ";type=i");

			System.out.println(urlFtp.toString());

			URLConnection urlc = urlFtp.openConnection();
			OutputStream out = urlc.getOutputStream();

			baos.writeTo(out);
			out.flush();
			out.close();

			estatus = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return estatus;

	}

	/**
	 * Elimina el archivo del Calendario
	 * 
	 * @param nombreArchivo
	 * @throws IOException
	 */
	public static void eliminarCalendario(String nombreArchivo) throws IOException {
		FTPClient client = new FTPClient();

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
