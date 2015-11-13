package br.com.site.client;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import br.com.site.client.controller.bean.PedidoBean;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class PedidoClient {

	public String addPedido(Double total) throws Exception {
		HttpClient httpClient = new HttpClient();

		GetMethod httpMethod = new GetMethod(
				"http://localhost:8080/pizza-service/service/rest/delivery/addPedido/"
						+ total);

		httpClient.executeMethod(httpMethod);
		String id = httpMethod.getResponseBodyAsString();

		httpMethod.releaseConnection();

		System.out.println("****IDPedido: " + id);

		return id;
	}

	public void addPedidoPizza(Integer idPedido, Integer idPizza)
			throws Exception {
		HttpClient httpClient = new HttpClient();

		GetMethod httpMethod = new GetMethod(
				"http://localhost:8080/pizza-service/service/rest/delivery/addPedidoPizza/"
						+ idPedido + "/" + idPizza);

		httpClient.executeMethod(httpMethod);
		httpMethod.releaseConnection();

	}
	
	public void addPedidoBebida(Integer idPedido, Integer idBebida, Integer qtd)
			throws Exception {
		HttpClient httpClient = new HttpClient();

		GetMethod httpMethod = new GetMethod(
				"http://localhost:8080/pizza-service/service/rest/delivery/addPedidoBebida/"
						+ idPedido + "/" + idBebida+"/"+qtd);

		httpClient.executeMethod(httpMethod);
		httpMethod.releaseConnection();

	}
	
	public void addPedidoSobremesa(Integer idPedido, Integer idSobremesa, Integer qtd)
			throws Exception {
		HttpClient httpClient = new HttpClient();

		GetMethod httpMethod = new GetMethod(
				"http://localhost:8080/pizza-service/service/rest/delivery/addPedidoSobremesa/"
						+ idPedido + "/" + idSobremesa+"/"+qtd);

		httpClient.executeMethod(httpMethod);
		httpMethod.releaseConnection();

	}
	
	public List<PedidoBean> buscaPedidos() throws Exception {
		 HttpClient httpClient = new HttpClient();
		 
		    GetMethod httpMethod = 
		      new GetMethod("http://localhost:8080/pizza-service/service/rest/delivery/listaPedidos");
		    httpClient.executeMethod(httpMethod);
		    
		   
		    List<PedidoBean> list = carregalistPedidos(new StringReader(httpMethod.getResponseBodyAsString().replace("collection", "list")));
		    httpMethod.releaseConnection();
		    
		    return list;
		  }
		

		public static List<PedidoBean> carregalistPedidos(Reader fonte) {
		     XStream stream = new XStream(new DomDriver());
		     try{
		    	 stream.alias("pedido", PedidoBean.class);
		     return (List<PedidoBean>) stream.fromXML(fonte);
		     }catch(Exception e){
		    	 return null;
		     }
		   }
		
		public void finalizarPedido(Integer idPedido) throws Exception {
			HttpClient httpClient = new HttpClient();

			GetMethod httpMethod = new GetMethod(
					"http://localhost:8080/pizza-service/service/rest/delivery/finalizarPedido/"
							+ idPedido);

			httpClient.executeMethod(httpMethod);
			httpMethod.releaseConnection();

		}
		
}
