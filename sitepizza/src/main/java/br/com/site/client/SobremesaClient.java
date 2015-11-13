package br.com.site.client;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import br.com.site.client.controller.bean.SobremesaBean;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class SobremesaClient {
	public List<SobremesaBean> buscaSobremesas() throws Exception {
		 HttpClient httpClient = new HttpClient();
		 
		    GetMethod httpMethod = 
		      new GetMethod("http://localhost:8080/pizza-service/service/rest/delivery/sobremesas");
		    httpClient.executeMethod(httpMethod);
		    
		   
		    List<SobremesaBean> list = carregalist(new StringReader(httpMethod.getResponseBodyAsString().replace("collection", "list")));
		    httpMethod.releaseConnection();
		    
		    return list;
		  }
		

		public static List<SobremesaBean> carregalist(Reader fonte) {
		     XStream stream = new XStream(new DomDriver());
		     try{
		    	 stream.alias("sobremesa", SobremesaBean.class);
		     return (List<SobremesaBean>) stream.fromXML(fonte);
		     }catch(Exception e){
		    	 return null;
		     }
		   }
}
