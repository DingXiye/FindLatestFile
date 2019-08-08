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
 * ���ư�ť��Ӧ����
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
	 * ���ư�ť�����������ļ���ǰ8λ�Ƚ�ʱ��
	 */
	public void copyAction() {
		try {
			File log=new File(m_destPath+"\\log.txt");
			if(log.exists()) {//�������һ�ξͻ����½���log�ļ�
				log.delete();
			}
			List<String> pathList = m_rd.Filter(m_srcPath, m_filters);
			Map<Integer, String> map = null;
			int[] arr = new int[1000];
			for (String path : pathList) {//��Ŀ·��
				File file = new File(path);
				File[] files = file.listFiles();
				for (int i = 0; i < files.length; i++) {
					String[] paths = files[i].getPath().split("\\\\");
					int data = Integer.parseInt(paths[paths.length - 1].substring(0, 8));//��װʱ�䴦��
					map = new HashMap<Integer, String>();
					map.put(data, files[i].getPath());
					arr[i] = data;
				}
				Arrays.sort(arr);
				String copysrcpath = map.get(arr[arr.length - 1]);//�õ�����Ŀ�����°汾·��
				m_ic.Copy(copysrcpath, m_destPath);
				m_rd.Logg(copysrcpath);
			}
				JOptionPane.showMessageDialog(null, "�������", "��ʾ��Ϣ", JOptionPane.PLAIN_MESSAGE);
			}catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "·��δ����", "������Ϣ", JOptionPane.WARNING_MESSAGE);
				return ;
			}
	}
}
