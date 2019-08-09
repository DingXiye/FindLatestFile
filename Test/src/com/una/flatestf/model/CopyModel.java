package com.una.flatestf.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import com.una.flatestf.util.Close;

public class CopyModel {
	private List<String> m_copyPathList = null;
	private String m_destPath = null;
	private static Logger logger = Logger.getLogger(CopyModel.class);
	
	public CopyModel(List<String> copyPathlist, String destPath) {
		this.m_copyPathList = copyPathlist;
		this.m_destPath = destPath;
	}

	/**
	 * ����������Ҫ�İ汾
	 */
	public void Copy() {
		try {
			for (String copypath : m_copyPathList) {
				LogModel logModel=new LogModel();
				logModel.Log(copypath);
				File src = new File(copypath);
				File dest = new File(m_destPath);
				if (src.isDirectory()) {
					dest = new File(dest, src.getName());
				}
				copy(src, dest);
			}
			JOptionPane.showMessageDialog(null, "�������", "��ʾ��Ϣ", JOptionPane.PLAIN_MESSAGE);
		} catch (Exception e) {
			logger.error("�ļ�·��������");
		}
	}

	/**
	 * �������Ŀ¼
	 * 
	 * @param src  Դ�ļ�
	 * @param dest Ŀ���ļ�
	 */
	private static void copy(File src, File dest) {
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
	 * �����ļ�
	 * 
	 * @param src  Դ�ļ�
	 * @param dest Ŀ���ļ�
	 */
	public static void copyFile(File src, File dest) {
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
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Close.Closing(os, is);
	}

}
