package com.una.flatestf.model;

import java.io.File;

import org.apache.log4j.Logger;

public class LogModel {
	private static Logger logger = Logger.getLogger(LogModel.class);
	private int m_fileLevel=0;

	/**
	 * 生成输出格式
	 * 
	 * @param name  输出的文件名或目录名
	 * @param level 输出的文件名或者目录名所在的层次
	 * @return 输出的字符串
	 */
	public String createPrintStr(String name, int level) {
		// 输出的前缀
		String printStr = "";
		// 按层次进行缩进
		for (int i = 0; i < level; i++) {
			printStr = printStr + " ";
		}
		printStr = printStr + "- " + name;
		return printStr;
	}
	
	/**
	 * 递归遍历文件夹
	 * 输出给定目录下的文件，包括子目录中的文件
	 * 
	 * @param dirPath 给定的目录
	 */
	public void Traserve(String path) {
		File file = new File(path);
		// 取得代表目录中所有文件的File对象数组
		File[] list = file.listFiles();
		// 遍历file数组
		for (int i = 0; i < list.length; i++) {
			if (list[i].isDirectory()) {
				logger.info(createPrintStr(list[i].getName(), m_fileLevel));
//				System.out.println(createPrintStr(list[i].getName(), m_fileLevel));
				m_fileLevel++;
				// 递归子目录
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
