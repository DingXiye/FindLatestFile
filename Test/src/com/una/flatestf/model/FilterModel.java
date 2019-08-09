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
	private String xmlPath = "�����ļ�/filter.xml";

	/**
	 * ����ģ���²���Ҫ����Ŀ
	 * 
	 * @param srcPath �ļ�·��
	 * @return pathList ����֮��ʣ�µ���Ŀ·��
	 */
	public List<String> Filter(String srcPath) {
		if (xmlPath == null) {
			logger.error("�����ļ�Ϊ��");
			return null;
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
			return m_pathList;
		}
	}
	
}
