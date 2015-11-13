package br.com.site.client;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import br.com.site.client.controller.bean.PizzaBean;
import br.com.site.client.controller.bean.SaborPizzaPizzaBean;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class PizzaClient {

	public String addPizza(Double maiorVlr) throws Exception {
		HttpClient httpClient = new HttpClient();

		GetMethod httpMethod = new GetMethod("http://localhost:8080/pizza-service/service/rest/delivery/addPizza/"+maiorVlr);

		httpClient.executeMethod(httpMethod);
		String id = httpMethod.getResponseBodyAsString();

		httpMethod.releaseConnection();
		
		System.out.println("****IDPIZZA: "+id);

		return id;
	}
	
	public void limpar() throws Exception {
		HttpClient httpClient = new HttpClient();

		GetMethod httpMethod = new GetMethod("http://localhost:8080/pizza-service/service/rest/delivery/limpar");

		httpClient.executeMethod(httpMethod);

		httpMethod.releaseConnection();
		
	}
	
	public void excluirPizza(Integer idPizza) throws Exception {
		HttpClient httpClient = new HttpClient();

		GetMethod httpMethod = new GetMethod("http://localhost:8080/pizza-service/service/rest/delivery/excluirPizza/"+idPizza);

		httpClient.executeMethod(httpMethod);

		httpMethod.releaseConnection();
		
	}
	
	public List<PizzaBean> buscaPizzas() throws Exception {
		 HttpClient httpClient = new HttpClient();
		 
		    GetMethod httpMethod = 
		      new GetMethod("http://localhost:8080/pizza-service/service/rest/delivery/listaPizzas");
		    httpClient.executeMethod(httpMethod);
		    
		   
		    List<PizzaBean> list = carregalist(new StringReader(httpMethod.getResponseBodyAsString().replace("collection", "list")));
		    httpMethod.releaseConnection();
		    
		    return list;
		  }
		

		public static List<PizzaBean> carregalist(Reader fonte) {
		     XStream stream = new XStream(new DomDriver());
		     try{
		    	 stream.alias("pizza", PizzaBean.class);
		     return (List<PizzaBean>) stream.fromXML(fonte);
		     }catch(Exception e){
		    	 return null;
		     }
		   }
		
		
		public List<SaborPizzaPizzaBean> buscaPizzasPizza() throws Exception {
			 HttpClient httpClient = new HttpClient();
			 
			    GetMethod httpMethod = 
			      new GetMethod("http://localhost:8080/pizza-service/service/rest/delivery/listaSaborPizzasPizzas1");
			    httpClient.executeMethod(httpMethod);
			    
			   
			    List<SaborPizzaPizzaBean> list = carregalistPizzaPizza(new StringReader(httpMethod.getResponseBodyAsString().replace("collection", "list")));
			    httpMethod.releaseConnection();
			    
			    return list;
			  }
			

			public static List<SaborPizzaPizzaBean> carregalistPizzaPizza(Reader fonte) {
			     XStream stream = new XStream(new DomDriver());
			     try{
			    	 stream.alias("saborPizzaPizza", SaborPizzaPizzaBean.class);
			     return (List<SaborPizzaPizzaBean>) stream.fromXML(fonte);
			     }catch(Exception e){
			    	 return null;
			     }
			   }


}
