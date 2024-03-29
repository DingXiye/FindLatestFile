package com.una.flatestf.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.log4j.Logger;

import com.una.flatestf.util.Close;

/**
 * 错误提示信息使用一个实体类
 * 可以通过返回值返回错误信息
 * @author Lenovo
 *
 */
public class CopyModel {
	private List<String> m_copyPathList = null;
	private String m_destPath = null;
	private static Logger logger = Logger.getLogger(CopyModel.class);
	private String m_dirName;
	public CopyModel(List<String> copyPathlist, String destPath) {
		this.m_copyPathList = copyPathlist;
		this.m_destPath = destPath;
	}

	/**
	 *拷贝操作
	 */
	public MsgModel Copy() {
		MsgModel msgModel=new MsgModel();
		try {
			for (String path : m_copyPathList) {
				LogModel logModel=new LogModel();
				logModel.Log(path);
				String[] paths=path.split(File.separator+File.separator);
				m_dirName=paths[paths.length-2];
				File src = new File(path);
				File dest = new File(m_destPath);//创建目标目录
				if (src.isDirectory()) {
					dest = new File(dest, m_dirName);//创建项目目录
					dest=new File(dest,src.getName());//创建版本目录
				}
				copy(src, dest);
			}
			msgModel.setId(104);
			msgModel.setMsg("拷贝完成");
			return msgModel;
		} catch (Exception e) {
			logger.error("拷贝地址错误");
			msgModel.setId(105);
			msgModel.setMsg("拷贝目标地址出错");
			return msgModel;
		}
	}

	/**
	 * 
	 * 拷贝文件夹
	 * @param src  源文件地址
	 * @param dest  目标文件地址
	 */
	private void copy(File src, File dest) {
		if (src.isFile()) {
			copyFile(src, dest);
		} else if (src.isDirectory()) {
			dest.mkdirs();
			for (File de : src.listFiles()) {
				copy(de, new File(dest, de.getName()));
			}
		}
	}

	/**
	 * 拷贝文件
	 * 
	 * @param src  源文件地址
	 * @param dest 目标文件地址
	 */
	public void copyFile(File src, File dest) {
		OutputStream os = null;
		InputStream is = null;
		try {
			os = new FileOutputStream(dest);
			is = new FileInputStream(src);
			int len = 0;
			byte[] b = new byte[1024];
			while (-1 != (len = is.read(b))) {
				os.write(b, 0, len);
			}

		} catch (FileNotFoundException e) {
			logger.error("文件未找到");
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("文件拷贝错误");
			e.printStackTrace();
		}
		Close.Closing(os, is);
	}

}
