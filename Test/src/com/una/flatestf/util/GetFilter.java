package com.una.flatestf.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 从xml文件中获取过滤信息
 * 
 * @author Lenovo
 *
 */
@SuppressWarnings("unchecked")
public class GetFilter {
	private static Logger logger = Logger.getLogger(GetFilter.class);
	private String m_srcPath=null;
	private List<String> m_filterList=null;

	public GetFilter(String srcPath) {
		this.m_srcPath = srcPath;
	}

	public List<String> getFilter() {
		m_filterList = new ArrayList<String>();
		SAXReader reader = new SAXReader();
		Document document;
		try {
			document = reader.read(new File(m_srcPath));
			Element filters = document.getRootElement();
			Iterator<Element> filterItem=filters.elementIterator();
			while (filterItem.hasNext()) {
				Element filterElement=(Element) filterItem.next();
			
				Iterator<Element> filter=filterElement.elementIterator();
				while (filter.hasNext()) {
					Element e = (Element) filter.next();
					String filterstr=e.getStringValue();
					m_filterList.add(filterstr);
				}
				
			}
		} catch (DocumentException e) {
			e.printStackTrace();
			logger.error("读取过滤配置错误");
		}

		return m_filterList;
	}
}
