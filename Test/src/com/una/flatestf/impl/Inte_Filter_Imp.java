package com.una.flatestf.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.una.flatestf.controller.Inte_Filter;
import com.una.flatestf.util.Close;

public class Inte_Filter_Imp implements Inte_Filter {
	// �ļ����ڵĲ���
	private int m_fileLevel;
	private List<String> m_pathList;
	private BufferedWriter bw;
	private String m_destP;

	public Inte_Filter_Imp(String destPath) {
		this.m_destP=destPath;
		
	}

	/**
	 * ���������ʽ
	 * 
	 * @param name  ������ļ�����Ŀ¼��
	 * @param level ������ļ�������Ŀ¼�����ڵĲ��
	 * @return ������ַ���
	 */
	public String createPrintStr(String name, int level) {
		// �����ǰ׺
		String printStr = "";
		// ����ν�������
		for (int i = 0; i < level; i++) {
			printStr = printStr + " ";
		}
		printStr = printStr + "- " + name;
		return printStr;
	}

	/**
	 * ����ģ���²���Ҫ����Ŀ
	 * 
	 * @param srcPath �ļ�·��
	 * @param filters ���˵�����
	 * @return pathList ����֮��ʣ�µ���Ŀ·��
	 */
	@Override
	public List<String> Filter(String srcPath, List<String> filters) {
		m_pathList = new ArrayList<String>();
			File file = new File(srcPath);
			File[] filelist = file.listFiles();
			for (int i = 0; i < filelist.length; i++) {
				if (!filters.contains(filelist[i].getName())) {
					m_pathList.add(filelist[i].getPath());
				}
			}
		return m_pathList;
	}
	

	/**
	 * �����ʼ������Ŀ¼ �洢Դ·����־
	 * 
	 * @param srcPath ������Ŀ¼
	 * @throws IOException
	 */
	@Override
	public void Logg(String destPath) {
		try {
			bw = new BufferedWriter(new FileWriter(m_destP+"\\log.txt",true));
			bw.write("=====================================================================");
			bw.newLine();
			bw.write(createPrintStr(destPath, 0));
			bw.newLine();
		} catch (IOException e) {
			e.printStackTrace();
			Close.Closing(bw);
		}
		Traserve(destPath);
		Close.Closing(bw);
	}
	

	/**
	 * �ݹ�����ļ���
	 * �������Ŀ¼�µ��ļ���������Ŀ¼�е��ļ�
	 * 
	 * @param dirPath ������Ŀ¼
	 */
	public void Traserve(String path) {
		File file = new File(path);
		// ȡ�ô���Ŀ¼�������ļ���File��������
		File[] list = file.listFiles();
		// ����file����
		for (int i = 0; i < list.length; i++) {
			if (list[i].isDirectory()) {
				System.out.println(createPrintStr(list[i].getName(), m_fileLevel));
				try {
					bw.write(createPrintStr(list[i].getName(), m_fileLevel));
					bw.newLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
				m_fileLevel++;
				// �ݹ���Ŀ¼
				Traserve(list[i].getPath());
				m_fileLevel--;
			} else {
				System.out.println(createPrintStr(list[i].getName(), m_fileLevel));
				try {
					bw.write(createPrintStr(list[i].getName(), m_fileLevel));
					bw.newLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
