package com.sadss.csa.util;

import java.util.ArrayList;
import java.util.List;


public class Mensaje {

	private String app;
	private List<String> to;
	private String from;
	private String asunto;
	private String ccp;
	private String cco;
	private String body;

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public List<String> getTo() {
		return to;
	}

	public void setTo(List<String> to) {
		this.to = to;
	}
	
	public void setTo(String to){
		this.to = new ArrayList<String>();
		this.to.add(to);
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getCcp() {
		return ccp;
	}

	public void setCcp(String ccp) {
		this.ccp = ccp;
	}

	public String getCco() {
		return cco;
	}

	public void setCco(String cco) {
		this.cco = cco;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String toString() {
		final int maxLen = 10;
		StringBuilder builder = new StringBuilder();
		builder.append("Mensaje [");
		if (app != null) {
			builder.append("app=");
			builder.append(app);
			builder.append(", ");
		}
		if (to != null) {
			builder.append("to=");
			builder.append(to.subList(0, Math.min(to.size(), maxLen)));
			builder.append(", ");
		}
		if (from != null) {
			builder.append("from=");
			builder.append(from);
			builder.append(", ");
		}
		if (asunto != null) {
			builder.append("asunto=");
			builder.append(asunto);
			builder.append(", ");
		}
		if (ccp != null) {
			builder.append("ccp=");
			builder.append(ccp);
			builder.append(", ");
		}
		if (cco != null) {
			builder.append("cco=");
			builder.append(cco);
			builder.append(", ");
		}
		if (body != null) {
			builder.append("body=");
			builder.append(body);
		}
		builder.append("]");
		return builder.toString();
	}
	
	
	
	

}
