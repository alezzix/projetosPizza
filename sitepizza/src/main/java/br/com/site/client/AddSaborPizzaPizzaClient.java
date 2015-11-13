package br.com.site.client;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

public class AddSaborPizzaPizzaClient {

	public void addSaborPizzaPizza(Integer idPizza, Integer idSabor)
			throws Exception {
		
		HttpClient httpClient = new HttpClient();
		GetMethod httpMethod = new GetMethod(
				"http://localhost:8080/pizza-service/service/rest/delivery/addSaboresPizzaPizza/"
						+ idPizza + "/" + idSabor);

		httpClient.executeMethod(httpMethod);
		httpMethod.releaseConnection();
	}

}
