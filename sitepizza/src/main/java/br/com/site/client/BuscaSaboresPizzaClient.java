package br.com.site.client;

import java.io.PrintStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;

import br.com.site.client.controller.bean.SaborPizzaBean;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class BuscaSaboresPizzaClient {
	
	private static final Logger logger = Logger.getLogger(BuscaSaboresPizzaClient.class);
	
	public List<SaborPizzaBean> buscaSaboresPizza() throws Exception {
	    HttpClient httpClient = new HttpClient();
	 
	    GetMethod httpMethod = 
	      new GetMethod("http://localhost:8080/pizza-service/service/rest/delivery/sabores");
	    
	  // String xml = "<SaborPizzaBean><idSabor>2</idSabor><nome>Camarao</nome><preco>43.5</preco></SaborPizzaBean>";	 
	   // httpMethod.addRequestHeader("Accept", "application/xml");
	    httpClient.executeMethod(httpMethod);
	    Scanner scan = 
	      new Scanner(httpMethod.getResponseBodyAsStream());
	    PrintStream ps = System.out;
	    
	    System.out.println(httpMethod.getResponseBodyAsString());
	   
	  //  SaborPizzaBean negocios = carrega(new StringReader(xml));
	    
	   
	    
	   
	    List<SaborPizzaBean> list = carregalist(new StringReader(httpMethod.getResponseBodyAsString().replace("collection", "list")));
	    
	    
	    
	  
	    httpMethod.releaseConnection();
	    
	    return list;
	  }
	

	public static List<SaborPizzaBean> carregalist(Reader fonte) {
	     XStream stream = new XStream(new DomDriver());
	   //  stream.fromXML(fonte);
	     try{
	    	 stream.alias("saborPizza", SaborPizzaBean.class);
	     return (List<SaborPizzaBean>) stream.fromXML(fonte);
	     }catch(Exception e){
	    	 logger.error("Erro ao carregar lista de sabores: "+e);
	    	 return null;
	     }
	   }
	
	 
		public static SaborPizzaBean carrega(Reader fonte) {
		     XStream stream = new XStream(new DomDriver());
		   //  stream.fromXML(fonte);
		     try{
		    	 stream.alias("SaborPizzaBean", SaborPizzaBean.class);
		     return (SaborPizzaBean) stream.fromXML(fonte);
		     }catch(Exception e){
		    	 logger.error(e);
		    	 return null;
		     }
		   }

}
