package com.una.flatestf.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import com.una.flatestf.util.GetFilter;

public class FilterModel {
	private static Logger logger = Logger.getLogger(CompareModel.class);
	private List<String> m_pathList = null;
	private List<String> m_filterStr = null;
	private String xmlPath = "配置文件/filter.xml";

	/**
	 *过滤不需要的文件夹
	 * 
	 * @param srcPath 源目录地址
	 * @return pathList 过滤之后的地址集合
	 */
	public MsgModel Filter(String srcPath) {
		MsgModel msgModel=new MsgModel();
		if (xmlPath == null) {
			logger.error("配置文件未找到");
			msgModel.setId(101);
			msgModel.setMsg("配置文件未找到");
			return msgModel;
		} else {
			m_filterStr = new GetFilter(xmlPath).getFilter();
			m_pathList = new ArrayList<String>();
			File file = new File(srcPath);
			File[] filelist = file.listFiles();
			for (int i = 0; i < filelist.length; i++) {
				if (!m_filterStr.contains(filelist[i].getName())) {
					m_pathList.add(filelist[i].getPath());
				}
			}
			msgModel.setId(106);
			msgModel.setList(m_pathList);
			msgModel.setMsg("获取过滤成功");
			return msgModel;
		}
	}
	
}
