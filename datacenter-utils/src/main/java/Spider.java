import java.util.Map;

public interface Spider {

	public void httpGet(String url);
	
	public void httpPost(String url, Map<String, String> param);
}
