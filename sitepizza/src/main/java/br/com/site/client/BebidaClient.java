package br.com.site.client;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import br.com.site.client.controller.bean.BebidaBean;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class BebidaClient {

	public List<BebidaBean> buscaBebidas() throws Exception {
		 HttpClient httpClient = new HttpClient();
		 
		    GetMethod httpMethod = 
		      new GetMethod("http://localhost:8080/pizza-service/service/rest/delivery/bebidas");
		    httpClient.executeMethod(httpMethod);
		    
		   
		    List<BebidaBean> list = carregalist(new StringReader(httpMethod.getResponseBodyAsString().replace("collection", "list")));
		    httpMethod.releaseConnection();
		    
		    return list;
		  }
		

		public static List<BebidaBean> carregalist(Reader fonte) {
		     XStream stream = new XStream(new DomDriver());
		     try{
		    	 stream.alias("bebida", BebidaBean.class);
		     return (List<BebidaBean>) stream.fromXML(fonte);
		     }catch(Exception e){
		    	 return null;
		     }
		   }
}
