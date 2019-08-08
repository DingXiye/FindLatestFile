package com.una.flatestf.action;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import com.una.flatestf.controller.Inte_Copy;
import com.una.flatestf.controller.Inte_Filter;
/**
 * 复制按钮对应操作
 * @author Lenovo
 *
 */
public class CopyAction {
	private Inte_Filter m_rd ;
	private Inte_Copy m_ic;
	private String m_srcPath;
	private String m_destPath;
	private List<String> m_filters;
	public CopyAction() {
	}
	
	public CopyAction(Inte_Filter rd, Inte_Copy ic, String srcPath, String destPath, List<String> filters) {
		this.m_rd=rd;
		this.m_ic=ic;
		this.m_srcPath=srcPath;
		this.m_destPath=destPath;
		this.m_filters=filters;
	}

	/**
	 * 复制按钮操作，根据文件名前8位比较时间
	 */
	public void copyAction() {
		try {
			File log=new File(m_destPath+"\\log.txt");
			if(log.exists()) {//点击复制一次就会重新建立log文件
				log.delete();
			}
			List<String> pathList = m_rd.Filter(m_srcPath, m_filters);
			Map<Integer, String> map = null;
			int[] arr = new int[1000];
			for (String path : pathList) {//项目路径
				File file = new File(path);
				File[] files = file.listFiles();
				for (int i = 0; i < files.length; i++) {
					String[] paths = files[i].getPath().split("\\\\");
					int data = Integer.parseInt(paths[paths.length - 1].substring(0, 8));//安装时间处理
					map = new HashMap<Integer, String>();
					map.put(data, files[i].getPath());
					arr[i] = data;
				}
				Arrays.sort(arr);
				String copysrcpath = map.get(arr[arr.length - 1]);//得到该项目的最新版本路径
				m_ic.Copy(copysrcpath, m_destPath);
				m_rd.Logg(copysrcpath);
			}
				JOptionPane.showMessageDialog(null, "拷贝完成", "提示信息", JOptionPane.PLAIN_MESSAGE);
			}catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "路径未输入", "警告信息", JOptionPane.WARNING_MESSAGE);
				return ;
			}
	}
}
