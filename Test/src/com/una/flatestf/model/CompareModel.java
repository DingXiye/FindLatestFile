package com.una.flatestf.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

/**
 * 复制按钮对应操作
 * 
 * @author Lenovo
 *
 */
public class CompareModel {
	private List<String> m_filtersPathList = new ArrayList<String>();
	private List<String> m_copyPathList = new ArrayList<String>();
	private static Logger logger = Logger.getLogger(CompareModel.class);

	public CompareModel() {
	}

	public CompareModel(List<String> filters) {
		this.m_filtersPathList = filters;
	}

	/**
	 * 根据文件名前8位比较时间,获得最新版本路径
	 */
	public List<String> compare() {
		Map<Integer, String> map = null;
		int[] arr = new int[1000];
		try {
			for (String path : m_filtersPathList) {// 项目路径
				File file = new File(path);
				File[] files = file.listFiles();
				for (int i = 0; i < files.length; i++) {
					String[] paths = files[i].getPath().split("\\\\");

					int data = Integer.parseInt(paths[paths.length - 1].substring(0, 8));// 安装时间处理
					map = new HashMap<Integer, String>();
					map.put(data, files[i].getPath());
					arr[i] = data;
				}
				Arrays.sort(arr);
				String copysrcpath = map.get(arr[arr.length - 1]);// 得到该项目的最新版本路径
				m_copyPathList.add(copysrcpath);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "所选文件命名不规范", "提示信息", JOptionPane.PLAIN_MESSAGE);
			logger.error("项目版本格式不正确");
			return null;
		}
		return m_copyPathList;
	}
}
