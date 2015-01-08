package example;
import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import freemarker.template.*;

public class Hello extends HttpServlet{
	private Configuration cfg;

	public void init(){
		//初始化FreeMarker配置,创建一个Configuration实例
	    cfg = new Configuration();
	    //设置FreeMarker的模版文件位置
	    cfg.setServletContextForTemplateLoading(getServletContext(),"templates");
	}

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
	   //建立数据模型
	   Map root = new HashMap();
	   //放入对应数据key value
	   root.put("user","csdn");
	   //取得模版文件
	   Template t =cfg.getTemplate("hello.ftl");
	   //开始准备生成输出
	   //使用模版文件的charset作为本页面的charset
	   //使用text/html MIME-type
//	   response.setContentType("text/html; charset=" + t.getEncoding());
//	   PrintWriter out = response.getWriter();
	   
	   String htmlFilePath = "E:";		//存放路径
	   String htmlFileName = "a.jsp";		//文件名称
	  
	   //合并数据模型和模版，并将结果输出到out中
	   try{
           // 如果根路径存在,则递归创建子目录  
           this.creatDirs(htmlFilePath);  
           File afile = new File(htmlFilePath + "/" + htmlFileName);  
           Writer out = new BufferedWriter(new OutputStreamWriter(  
                   new FileOutputStream(afile)));  
		   
		   t.process(root,out);// 用模板来开发servlet可以只在代码里面加入动态的数据
		   out.flush();  
           out.close();
	   }catch(TemplateException e){
		   throw new ServletException("处理Template模版中出现错误", e);
	   }
	}
	
	 /** 
     * 创建多级目录 
     *  
     * @param path 
     *            String 
     * @return boolean 是否成功 
     */
	private boolean creatDirs(String path) {  
        File aFile = new File(path);  
        if (!aFile.exists()) {  
            return aFile.mkdirs();  
        } else {  
            return true;  
        }
	}
}