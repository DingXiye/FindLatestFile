package com.una.flatestf.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class FilterModel {
	private static Logger logger = Logger.getLogger(CompareModel.class);
	private List<String> m_pathList = null;

	/**
	 * 过滤不需要的文件夹
	 * 
	 * @param srcPath 源目录地址
	 * @return pathList 过滤之后的地址集合
	 */
	public MsgModel Filter(String srcPath, List<String> filterList) {
		MsgModel msgModel = new MsgModel();
		m_pathList = new ArrayList<String>();
		File file = new File(srcPath);
		File[] filelist = file.listFiles();
		for (int i = 0; i < filelist.length; i++) {
			if (!filterList.contains(filelist[i].getName())) {
				m_pathList.add(filelist[i].getPath());
			}
		}
		logger.info("获取过滤成功");
		msgModel.setId(106);
		msgModel.setList(m_pathList);
		msgModel.setMsg("获取过滤成功");
		return msgModel;

	}
}
