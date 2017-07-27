package utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClientUtils {

	public String post(String url,Map<String,String> param) throws ClientProtocolException, IOException{
		CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost=new HttpPost(url);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        for(String key:param.keySet()){
        	params.add(new BasicNameValuePair(key, param.get(key)));
        }
        httppost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
        HttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();
        String html = EntityUtils.toString(entity);
        httpclient.close();
		return html;
	}
	
	
}
