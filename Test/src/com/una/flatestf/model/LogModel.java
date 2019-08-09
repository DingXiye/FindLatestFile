package com.una.flatestf.model;

import java.io.File;

import org.apache.log4j.Logger;

public class LogModel {
	private static Logger logger = Logger.getLogger(LogModel.class);
	private int m_fileLevel=0;

	/**
	 *创建日志信息格式
	 * 
	 * @param name  文件名
	 * @param level 层级
	 * @return 文件名
	 */
	public String createPrintStr(String name, int level) {
		String printStr = "";
		for (int i = 0; i < level; i++) {
			printStr = printStr + " ";
		}
		printStr = printStr + "- " + name;
		return printStr;
	}
	
	/**
	 *遍历文件夹，输出文件信息
	 * 
	 * @param path 输出目录地址
	 */
	public void Traserve(String path) {
		File file = new File(path);
		File[] list = file.listFiles();
		for (int i = 0; i < list.length; i++) {
			if (list[i].isDirectory()) {
				logger.info(createPrintStr(list[i].getName(), m_fileLevel));
//				System.out.println(createPrintStr(list[i].getName(), m_fileLevel));
				m_fileLevel++;
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
