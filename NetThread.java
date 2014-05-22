package william.learnitbyself.sampleapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;




import android.util.Log;

public class NetThread{
	public String url;
	
	public String method;	
	public int count;
	public String user_id;
	
	public String publisher_id;
	public String content;
	
	public int msg_id;
	
	public String username;
	public String password;

	public JSONObject result;
	JSONObject param = new JSONObject();
	
	NetThread(String m, int ct, String uid, String pid, String ctt,
			  int mid, String u, String p){
		method = m;
		count = ct;
		user_id = uid;
		publisher_id = pid;
		content = ctt;
		msg_id = mid;
		username = u;
		password = p;
		try{
			if (method != null) param.put("method", method);
			if (count >= 0) param.put("count", count);
			if (user_id != null) param.put("user_id", user_id);
			if (publisher_id != null) param.put("publisher_id", publisher_id);
			if (content != null) param.put("content", content);
			if (msg_id >= 0) param.put("msg_id", msg_id);
			if (username != null) param.put("username", username);
			if (password != null) param.put("password", password);
		} catch (Exception e) {
			e.printStackTrace();
        }		
	}
	
	public void BeginDeal(){
		try{
			Thread t = new Thread(connect);
			t.start();
			t.join();
		} catch (Exception e) {
            e.printStackTrace();
        }
	}

	public List<Map<String, Object>> getActivityData(){
		try {			
			int  i;
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			JSONArray jsonarr = new JSONArray(result);
			for (i = 0; i < jsonarr.length(); i++)	{
				JSONObject object = jsonarr.getJSONObject(i);
				Map<String, Object> map = new HashMap<String, Object>();
				for (int j = 0; j < Variable.activity.length; j++)
					map.put(Variable.activity[j], object.getString(Variable.activity[j]));		
				list.add(map);
			}
			
			return list;
		} catch (Exception e) {
            e.printStackTrace();
        }
		return null;
	}
	
	
	public Runnable connect = new Runnable(){
		@Override
		public void run(){
			try{
				HttpClient client = new DefaultHttpClient();
				HttpPost request = new HttpPost(url);
				
				Log.d("request json", param.toString());
				 
				// 绑定到请求 Entry
				StringEntity se = new StringEntity(param.toString()); 
				request.setEntity(se);
				
				// 发送请求
				HttpResponse httpResponse = client.execute(request);
				
				// 得到应答的字符串，这也是一个 JSON 格式保存的数据
				String retSrc = EntityUtils.toString(httpResponse.getEntity());
				Log.d("receive content", retSrc);
	
				// 生成 JSON 对象
				result = new JSONObject(retSrc);
			} catch (Exception e) {
                //Toast.makeText(MainActivity.this,"无法链接网络！", 1).show();
                e.printStackTrace();
            }
		}
		
	};
}
