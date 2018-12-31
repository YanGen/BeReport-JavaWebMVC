package com.report.server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONObject;

import com.report.dao.impl.ReportDAOJdbcimpl;
import com.report.domain.Report;
import com.report.file.ExcelProcess;


/**
 * Servlet implementation class upload
 */
@WebServlet("/upload")
public class upload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public upload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		// 得到FileItem集合 
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1024*500);
		File tempDirectory = new File(session.getServletContext().getRealPath("/WEB-INF/tempDirectory"));
		factory.setRepository(tempDirectory);
		// 设置总大小,上传总大小不得超过5M
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		upload.setSizeMax(1024 * 1024 * 5);
		String type = "";
		String title = "";
		String unit = "";
		try {
			List<FileItem> items = upload.parseRequest(request);
			// 遍历Items
			for(FileItem item : items){
				// if(一般的表单域)
				if(item.isFormField()){
					String name = item.getFieldName();
					String value = item.getString();
					if(name.equals("type")){
						type = new String(value.getBytes("iso-8859-1"),"utf-8");
					}
					if(name.equals("title")){
						title = new String(value.getBytes("iso-8859-1"),"utf-8");
					}
					if(name.equals("unit")){
						unit = new String(value.getBytes("iso-8859-1"),"utf-8");
					}
					System.out.println(name+":"+new String(value.getBytes("iso-8859-1"),"utf-8"));
				}
				}
			for(FileItem item : items){
				if(!item.isFormField()){
					String fieldName = item.getFieldName();
					String fileName = item.getName();
					if(fileName.toString().equals("excelfile")){
						response.getWriter().write("允许上传仅为excel文件");
					}
					else{
						System.out.println("文件类型:"+fieldName);
						System.out.println("文件名:"+fileName);
						InputStream in = item.getInputStream();
						byte [] buffer = new byte[1024];
						int len = 0;
						String savePath = this.getServletContext().getRealPath("/WEB-INF/upfiles");
						String filePath = savePath+"/"+fileName;
						String userFilePath = savePath;
						File userDir = new File(userFilePath);
						System.out.println("存储路径:"+userFilePath);
						JugeDirExists(userDir);
						OutputStream out = new FileOutputStream(filePath);
						while((len = in.read(buffer)) != -1){
							out.write(buffer, 0, len);
						}
						out.close();
						in.close();
						response.getWriter().write("上传成功,正在处理");
						
						SimpleDateFormat tdf = new SimpleDateFormat("yyyy-MM-dd");//时间格式
						Date nowDate = new Date();//得到当前时间
						String tday = tdf.format(nowDate );
						SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");//时间格式
						String tsecond = sdf.format(nowDate );
						
						ExcelProcess excelProcess = new ExcelProcess();
						Map<String, String> mapAllData = null;
						if(type.equals("LineChart")){
							mapAllData = excelProcess.LineChart(filePath, title, unit);
						}else if (type.equals("Histogram")) {
							mapAllData = excelProcess.LineChart(filePath, title, unit);
						}else if (type.equals("PieChart")){
							mapAllData = excelProcess.PieChart(filePath, title, unit);
						}else if (type.equals("ScatterDiagram")){
							mapAllData = excelProcess.ScatterDiagram(filePath, title, unit);
						}else if (type.equals("FunnelChart")){
							mapAllData = excelProcess.FunnelChart(filePath, title, unit);
						}else{
							response.getWriter().write("暂无后台处理方案~");
						}
						response.getWriter().write("->处理完成,正在保存");
						JSONObject jsonAllData = new JSONObject(mapAllData);
						Report report = new Report();
						report.setUsername(session.getAttribute("LoginUser").toString());
						report.setType(type);
						report.setJdata(jsonAllData.toString().replace("\"", ""));
						ReportDAOJdbcimpl daoJdbcimpl = new ReportDAOJdbcimpl();
						report.setTitle(title);
						report.setTday(tday);
						report.setTsecond(tsecond);
						daoJdbcimpl.save(report);
						response.sendRedirect(request.getContextPath()+"/timeline");
							
					}
				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	// 此方法判断文件夹是否存在，不存在则创建
	public void JugeDirExists(File file){
		 if (file.exists()) {
             if (file.isDirectory()) {
            	 
            } else {
                 System.out.println("the same name file exists, can not create dir");
             }
		 	} else {
             file.mkdir();
          }
	}

}
