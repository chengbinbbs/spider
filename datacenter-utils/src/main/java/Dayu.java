import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSONObject;
import com.dayspass.datacenter.common.util.StringUtils;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

public class Dayu {

	private static final String appkey = "23595971";
    private static final String secret = "7885ab1efa5d1e1ae24a9694fd3638ec";
    private static final String url = "http://gw.api.taobao.com/router/rest";
    private static final String signName = "得仕";
    private static final String templateCode = "SMS_41735150";
    
	public static void sendSMS(String mobile, String code){
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend("123456");
		req.setSmsType("normal");
		req.setSmsFreeSignName(signName);
		JSONObject json = new JSONObject();
		json.put("code", code);
		req.setSmsParamString(json.toString());
		req.setRecNum(mobile);
		req.setSmsTemplateCode(templateCode);
		AlibabaAliqinFcSmsNumSendResponse rsp;
		try {
			rsp = client.execute(req);
			System.out.println(rsp.getBody());
			JSONObject rspJson = JSONObject.parseObject(rsp.getBody());
			JSONObject send_response = (JSONObject) rspJson.get("alibaba_aliqin_fc_sms_num_send_response");
			if(!StringUtils.isEmpty(send_response)){
				JSONObject result = (JSONObject) send_response.get("result");
				if(result.getIntValue("err_code") == 0)
				{
					//发送短信成功
					System.out.println(result.get("success"));
				}
			}
			else
			{
				//异常情况
				JSONObject result = (JSONObject) rspJson.get("error_response");
				System.out.println(result.get("sub_code") +":" + result.get("sub_msg"));
			}
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		
//		sendSMS("1502684274","357642");
		 Pattern pattern = Pattern.compile("(?<=\\()[^\\)]+");
         Matcher matcher = pattern.matcher("【银行卡绑定】您的验证码是（222534）请妥善保管，切勿向他人泄露.");
         String code = null; //利用正则表达式取出括号中的验证码
         while(matcher.find()){
             code = matcher.group();
         }
		
		System.out.println(code);
	}
}
