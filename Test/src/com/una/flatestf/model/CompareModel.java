package com.una.flatestf.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * 比较获取最新版本
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
	 * 通过比较文件名前8位得到最新版本
	 */
	public MsgModel compare() {
		MsgModel msgModel=new MsgModel();
		Map<Integer, String> map = null;
		int[] arr = new int[1000];
		try {
			for (String path : m_filtersPathList) {
				File file = new File(path);
				File[] files = file.listFiles();
				for (int i = 0; i < files.length; i++) {
					String[] paths = files[i].getPath().split(File.separator+File.separator);
					int data = Integer.parseInt(paths[paths.length - 1].substring(0, 8));//比较
					map = new HashMap<Integer, String>();
					map.put(data, files[i].getPath());
					arr[i] = data;
				}
				Arrays.sort(arr);
				String copysrcpath = map.get(arr[arr.length - 1]);//要拷贝的地址
				m_copyPathList.add(copysrcpath);
			}
		} catch (Exception e) {
			logger.error("版本文件命名不规范，无法找到最新版本");
			msgModel.setId(102);
			msgModel.setMsg("版本命名不规范无法找到最新版本");
			return msgModel;
		}
		msgModel.setId(103);
		msgModel.setMsg("获取最新版本路径");
		msgModel.setList(m_copyPathList);
		return msgModel;
	}
}
