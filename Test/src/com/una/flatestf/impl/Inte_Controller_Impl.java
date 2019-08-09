package com.una.flatestf.impl;

import java.util.List;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import com.una.flatestf.controller.Inte_Controller;
import com.una.flatestf.model.CompareModel;
import com.una.flatestf.model.CopyModel;
import com.una.flatestf.model.FilterModel;
import com.una.flatestf.model.LogModel;

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
	 * 开始项目
	 */
	@Override
	public void Start() {
		FilterModel filterModel=new FilterModel();//过滤
		if(m_srcPath==null||m_destPath==null) {
			JOptionPane.showMessageDialog(null, "未输入正确路径", "警告信息", JOptionPane.ERROR_MESSAGE);
			logger.error("路径错误");
			return ;
		}
		m_filterList=filterModel.Filter(m_srcPath);
		CompareModel compareModel=new CompareModel(m_filterList);//比较
		m_copyPathlist=compareModel.compare();
		if(m_copyPathlist==null) {
			return ;
		}
		CopyModel copyModel=new CopyModel(m_copyPathlist,m_destPath);//拷贝
		copyModel.Copy();
	}
}
