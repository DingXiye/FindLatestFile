package com.una.flatestf.model;

import java.io.File;

import org.apache.log4j.Logger;

public class LogModel {
	private static Logger logger = Logger.getLogger(LogModel.class);
	private int m_fileLevel=0;

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
				logger.info(createPrintStr(list[i].getName(), m_fileLevel));
//				System.out.println(createPrintStr(list[i].getName(), m_fileLevel));
				m_fileLevel++;
				// �ݹ���Ŀ¼
				Traserve(list[i].getPath());
				m_fileLevel--;
			} else {
//				System.out.println(createPrintStr(list[i].getName(), m_fileLevel));
				logger.info(createPrintStr(list[i].getName(), m_fileLevel));
			}
		}
	}
	
	public void Log(String path) {
		File file = new File(path);
		logger.info(createPrintStr(file.getName(), 0));
//		System.out.println(createPrintStr(file.getName(), 0));
		m_fileLevel++;
		Traserve(path);
	}
}
