package com.una.flatestf.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.una.flatestf.controller.Inte_Controller;
import com.una.flatestf.model.CompareModel;
import com.una.flatestf.model.CopyModel;
import com.una.flatestf.model.FilterModel;
import com.una.flatestf.model.MsgModel;
import com.una.flatestf.util.GetFilter;

public class Inte_Controller_Impl implements Inte_Controller{
	private static Logger logger = Logger.getLogger(Inte_Controller_Impl.class);
    private	String m_srcPath=null;
	private String m_destPath=null;
	private List<String> m_filterList=null;
	private List<String> m_copyPathlist=null;
	private String m_xmlPath = "配置文件/filter.xml";
	
	public Inte_Controller_Impl(String srcPath,String destPath) {
		this.m_destPath=destPath;
		this.m_srcPath=srcPath;
	}
	
	/**
	 * 开始函数
	 */
	@Override
	public MsgModel Start() {
		MsgModel msgModel=new MsgModel();
		if(m_srcPath==null||m_destPath==null) { 
			logger.error("路径出错");
			msgModel.setId(100);
			msgModel.setMsg("路径出错");
			return msgModel;
		}

		FilterModel filterModel=new FilterModel();
		if (m_xmlPath == null) {
			logger.error("配置文件未找到");
			msgModel.setId(101);
			msgModel.setMsg("配置文件未找到");
			return msgModel;
		} 
		List<String> filterList=new GetFilter(m_xmlPath).getFilter();
		msgModel=filterModel.Filter(m_srcPath,filterList);
		if(msgModel.getId()==101){
			return msgModel;
		}
		
		m_filterList=msgModel.getList();
		CompareModel compareModel=new CompareModel(m_filterList);
		msgModel=compareModel.compare();
		if(msgModel.getId()==102) {
			return msgModel;
		}
		
		m_copyPathlist=msgModel.getList();
		CopyModel copyModel=new CopyModel(m_copyPathlist,m_destPath);
		msgModel=copyModel.Copy();
		if(msgModel.getId()==105){
			return msgModel;
		}
		return msgModel;
	}
}
