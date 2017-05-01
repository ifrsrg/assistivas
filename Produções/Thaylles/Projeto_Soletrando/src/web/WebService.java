package web;

import spark.ResponseTransformer;

public abstract class WebService {
	
	public final String contentType;
	public final ResponseTransformer responseTransformer;
	
	protected WebService(String contentType, ResponseTransformer responseTrasformer) {
		this.contentType = contentType;
		this.responseTransformer = responseTrasformer;
	}
	
}
