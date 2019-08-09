package com.una.flatestf.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

/**
 * ���ư�ť��Ӧ����
 * 
 * @author Lenovo
 *
 */
public class CompareModel {
	private List<String> m_filtersPathList = new ArrayList<String>();
	private List<String> m_copyPathList = new ArrayList<String>();
	private static Logger logger = Logger.getLogger(CompareModel.class);

	public CompareModel() {
	}

	public CompareModel(List<String> filters) {
		this.m_filtersPathList = filters;
	}

	/**
	 * �����ļ���ǰ8λ�Ƚ�ʱ��,������°汾·��
	 */
	public List<String> compare() {
		Map<Integer, String> map = null;
		int[] arr = new int[1000];
		try {
			for (String path : m_filtersPathList) {// ��Ŀ·��
				File file = new File(path);
				File[] files = file.listFiles();
				for (int i = 0; i < files.length; i++) {
					String[] paths = files[i].getPath().split("\\\\");

					int data = Integer.parseInt(paths[paths.length - 1].substring(0, 8));// ��װʱ�䴦��
					map = new HashMap<Integer, String>();
					map.put(data, files[i].getPath());
					arr[i] = data;
				}
				Arrays.sort(arr);
				String copysrcpath = map.get(arr[arr.length - 1]);// �õ�����Ŀ�����°汾·��
				m_copyPathList.add(copysrcpath);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "��ѡ�ļ��������淶", "��ʾ��Ϣ", JOptionPane.PLAIN_MESSAGE);
			logger.error("��Ŀ�汾��ʽ����ȷ");
			return null;
		}
		return m_copyPathList;
	}
}
