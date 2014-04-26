package com.ticketbooking.business.core.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.*;
import java.util.*;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.servlet.*;
import org.apache.commons.fileupload.disk.*;

import com.ticketbooking.business.core.constant.Constant;
import com.ticketbooking.util.StringUtil;

// Servlet 文件上传  
@WebServlet("/admin/upload")
public class UploadServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private String filePath; // 文件存放目录
	private String tempPath; // 临时文件目录
	private Long maxImageSize = 5 * 1024 * 1024L;// 5M
	private Long maxVideoSize = 100 * 1024 * 1024L; // 100M
	private String suffix = null; // 文件后缀 

	// doPost
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		res.setCharacterEncoding(Constant.ENCODER);
		res.setContentType(Constant.HTML_TYPE);
		String contextPath = req.getServletContext().getRealPath("/upload");
		Integer fileType = Integer.parseInt(req.getParameter("fileType"));
		filePath = contextPath;
		tempPath = contextPath + "/temp";
		File tmpFile = new File(filePath);
		if (!tmpFile.exists()) tmpFile.mkdirs();
		tmpFile = new File(tempPath);
		if (!tmpFile.exists()) tmpFile.mkdirs();
		PrintWriter pw = res.getWriter();
		try {
			DiskFileItemFactory diskFactory = new DiskFileItemFactory();
			// threshold 极限、临界值，即硬盘缓存 4M
			diskFactory.setSizeThreshold(4 * 1024);
			// repository 贮藏室，即临时文件目录
			diskFactory.setRepository(new File(tempPath));

			ServletFileUpload upload = new ServletFileUpload(diskFactory);
			
			// 解析HTTP请求消息头
			List<FileItem> fileItems = upload.parseRequest(req);
			Iterator<FileItem> iter = fileItems.iterator();
			String fileName = null;
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				if (item.isFormField()) {
					System.out.println("处理表单内容 ...");
					processFormField(item, pw);
				} else {
					System.out.println("处理上传的文件 ...");
					// 0是图片 1是视频 -1格式不对
					Integer type = this.checkFileName(item.getName());
					if (type < 0 || !fileType.equals(type)) {
						pw.println("文件格式不正确，请重新选择");
						pw.close();
						return;
					}
					Long maxSize = (type > 0) ? maxVideoSize : maxImageSize;
					Long fileSize = item.getSize();
					System.out.println("文件大小 : " + fileSize);
					if (fileSize > maxSize) {
						pw.println("文件超过限定大小，请重新选择");
						pw.close();
						return ;
					}
					fileName = processUploadFile(item, pw);
				}
			}// end while()
			if (fileName != null){
				fileName = req.getContextPath() + "/upload/" + fileName;
				pw.println(fileName);
				System.out.println("ret: " + fileName);
			} else {
				pw.println(-1);
			}
			pw.close();
		} catch (Exception e) {
			System.out.println("使用 fileupload 包时发生异常 ...");
			e.printStackTrace();
			res.getWriter().println(-1);
		}// end try ... catch ...
	}// end doPost()

	// 处理表单内容
	private void processFormField(FileItem item, PrintWriter pw)
			throws Exception {
		String name = item.getFieldName();
		String value = item.getString();
		System.out.println(name + ":" + value);
	}

	// 处理上传的文件
	private String processUploadFile(FileItem item, PrintWriter pw)
			throws Exception {
		// 此时的文件名包含了完整的路径，得注意加工一下
		String filename = item.getName();
		System.out.println("完整的文件名：" + filename);
		int index = filename.lastIndexOf("\\");
		filename = filename.substring(index + 1, filename.length());

		long fileSize = item.getSize();

		if ("".equals(filename) && fileSize == 0) {
			System.out.println("文件名为空 ...");
			return null;
		}
		filename = StringUtil.randString(15) + this.suffix;
		File uploadFile = new File(filePath + "/" + filename);
		item.write(uploadFile);
		return filename;
	}
	
	private Integer checkFileName(String checkName){
		checkName = checkName.toLowerCase();
		System.out.println("check文件名：" + checkName);
		String[] image = {".jpg", ".png", ".gif", ".avi"};
		String[] video = {".mkv", ".rm", ".rmvb", ".mp4", ".3gp"};
		for (int i = 0; i < image.length; i++) {
			if(checkName.endsWith(image[i])) {
				suffix = image[i];
				return 0;
			}
		}
		for (int i = 0; i < video.length; i++) {
			if(checkName.endsWith(video[i])) {
				suffix = video[i];
				return 1;
			}
		}
		System.out.println("文件格式不正确");
		return -1;
	}

	// doGet
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		doPost(req, res);
	}
}