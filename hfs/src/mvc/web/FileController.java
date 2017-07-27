package mvc.web;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import utils.HttpClientUtils;


/**
 * @author PGIDWUY
 */

@Controller
@RequestMapping(value = "/file")
public class FileController {

	@RequestMapping(value = "")
	public String toIndexPage(HttpSession session) {
		return "index";
	}


	@RequestMapping(value = "method")
	@ResponseBody
	public JSONObject testMethod(){
		JSONObject json=new JSONObject();
		json.put("msg", "ok");
		return json;
	}
	
	
	@RequestMapping(value = "upload")
	@ResponseBody
	public String upload(@RequestParam(value = "file") MultipartFile[] files,
			HttpServletRequest request) throws IOException {
		
		String sessionid = request.getParameter("sessionid");
		String returntext;
		boolean isOK = false;
		
		
		if(files != null&&sessionid!=null){
			System.out.println(sessionid);
			String url="http://192.168.1.102:8080/staticfile/file/method";	
			Map<String,String> map=new HashMap<String,String>();
			map.put("sessionid", sessionid);
			String text=new HttpClientUtils().post(url, map);
			JSONObject result=JSONObject.fromObject(text);
			System.out.println(result);
			returntext=result.getString("msg");
			/*
			if(result.getInt("code")==1){
				isOK = true;
			}else{
				isOK = false;
			}
			
			if (isOK) {
				for (int i = 0; i < files.length; i++) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
					String realname = files[i].getOriginalFilename();
					String type = realname.substring(realname.indexOf("."));// 取文件格式后缀名
					String filename = System.currentTimeMillis() + type;// 取当前时间戳作为文件名
					String path = request.getSession().getServletContext().getRealPath("/upload/"+sdf.format(new Date())+"/" + filename);// 存放位置
					File destFile = new File(path);
					FileUtils.copyInputStreamToFile(files[i].getInputStream(), destFile);// 复制临时文件到指定目录下
				}
				returntext="success";
			}else{
				returntext = result.getString("text");
			}
			*/
			
		}else{
			returntext="no file or param error";
		}
		
		
		return returntext;
		
	}

}
