package com.una.flatestf.impl;

import java.util.List;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import com.una.flatestf.controller.Inte_Controller;
import com.una.flatestf.model.CompareModel;
import com.una.flatestf.model.CopyModel;
import com.una.flatestf.model.FilterModel;

public class Inte_Controller_Impl implements Inte_Controller{
	private static Logger logger = Logger.getLogger(Inte_Controller_Impl.class);
    private	String m_srcPath=null;
	private String m_destPath=null;
	private List<String> m_filterList=null;
	private List<String> m_copyPathlist=null;
	public Inte_Controller_Impl(String srcPath,String destPath) {
		this.m_destPath=destPath;
		this.m_srcPath=srcPath;
	}
	
	/**
	 * 开始函数
	 */
	@Override
	public void Start() {
		FilterModel filterModel=new FilterModel();
		if(m_srcPath==null||m_destPath==null) { 
			logger.error("路径出错");
			return ;
		}
		m_filterList=filterModel.Filter(m_srcPath);
		CompareModel compareModel=new CompareModel(m_filterList);
		m_copyPathlist=compareModel.compare();
		if(m_copyPathlist==null) {
			logger.error("拷贝路径为空");
			return ;
		}
		CopyModel copyModel=new CopyModel(m_copyPathlist,m_destPath);
		copyModel.Copy();
	}
}
