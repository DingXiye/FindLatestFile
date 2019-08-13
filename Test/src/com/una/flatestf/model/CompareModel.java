package com.una.flatestf.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
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
	private Integer data = null;

	public CompareModel() {
	}

	public CompareModel(List<String> filters) {
		this.m_filtersPathList = filters;
	}

	/**
	 * 通过比较文件名前8位得到最新版本
	 */
	public MsgModel compare() {
		MsgModel msgModel = new MsgModel();
		Map<Integer, String> map = null;
		List<Integer> list;
		for (String path : m_filtersPathList) {
			list = new ArrayList<Integer>();
			File file = new File(path);
			if(file.isFile()) {
				continue;
			}
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				String[] pathsplit = files[i].getPath().split(File.separator + File.separator);
				try {
					data = Integer.parseInt(pathsplit[pathsplit.length - 1].substring(0, 8));
				} catch (Exception e) {
					logger.error(pathsplit[pathsplit.length - 2] + "下的版本文件命名不规范，无法找到最新版本");
					msgModel.setId(102);
					msgModel.setMsg(pathsplit[pathsplit.length - 2] + "下版本命名不规范无法找到最新版本");
					continue;
				}
				map = new HashMap<Integer, String>();
				map.put(data, files[i].getPath());
				list.add(data);
			}
			Collections.sort(list);
			String copysrcpath = map.get(list.get(list.size() - 1));// 得到要拷贝的地址
			m_copyPathList.add(copysrcpath);
		}
		msgModel.setId(103);
		msgModel.setMsg("获取最新版本路径");
		msgModel.setList(m_copyPathList);
		return msgModel;
	}
}
