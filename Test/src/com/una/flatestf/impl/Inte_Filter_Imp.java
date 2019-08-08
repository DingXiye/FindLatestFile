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
	// 文件所在的层数
	private int m_fileLevel;
	private List<String> m_pathList;
	private BufferedWriter bw;
	private String m_destP;

	public Inte_Filter_Imp(String destPath) {
		this.m_destP=destPath;
		
	}

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
	 * 过滤模块下不需要的项目
	 * 
	 * @param srcPath 文件路径
	 * @param filters 过滤的条件
	 * @return pathList 过滤之后剩下的项目路径
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
	 * 输出初始给定的目录 存储源路径日志
	 * 
	 * @param srcPath 给定的目录
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
				System.out.println(createPrintStr(list[i].getName(), m_fileLevel));
				try {
					bw.write(createPrintStr(list[i].getName(), m_fileLevel));
					bw.newLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
				m_fileLevel++;
				// 递归子目录
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
