package com.sadss.csa.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.sadss.csa.controller.beans.Bono;

@Component
public class BonosWS {

	private static String urlBonos;

	@Value("#{'${ambiente}' == 'PROD' || '${ambiente}' == 'DEV' ? '${url.bonosWS}' : '${url.bonosWS}'}")
	public void setUrlBonos(String urlBonos) {
		BonosWS.urlBonos = urlBonos;
	}

	public static List<Bono> getBonosAgente(String claveAgente, String fechaInicio, String fechaFin) {

		RestTemplate template = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Accept", "application/json");

		String api_key = "35f4af0c4413ca24f1a7e56850595957";
		headers.add("apikey", api_key);

		String uri = urlBonos;
		uri = uri.replace("{agente}", claveAgente);
		uri = uri.replace("{fechaInicio}", fechaInicio);
		uri = uri.replace("{fechaFin}", fechaFin);
		
		ResponseEntity<List<Bono>> response = template.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<Bono>>() {
		});
		
		return response.getBody();

	}
}
