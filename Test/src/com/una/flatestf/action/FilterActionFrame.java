package com.una.flatestf.action;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.una.flatestf.view.ViewerFrame;

/**
 * 窗口过滤，页面上显示所有项目名，勾选不想要的项目
 * 
 * @author Lenovo
 *
 */
public class FilterActionFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String m_srcFilterPath;
	/* 存储选中的复选框 */
	private List<String> m_filterList=new ArrayList<String>();;//记录确定按钮的复选框状态
	JPanel jp = new JPanel();
	JPanel jp2 = new JPanel();

	public FilterActionFrame(String srcPath, List<String> filters) {
		this.setTitle("过滤");
		this.setSize(400, 300);
		this.setLocation(417, 177);
		this.setLayout(new GridLayout(2, 1, 20, 20));
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		this.m_srcFilterPath = srcPath;
		this.m_filterList = filters;
		
		try {
			if (!m_srcFilterPath.equals(" ") && m_srcFilterPath != null) {
				File file = new File(m_srcFilterPath);
				File[] files = file.listFiles();
				for (int i = 0; i < files.length; i++) {
					JCheckBox cb = new JCheckBox(files[i].getName());
					if (filters.contains(files[i].getName())) {
						cb.setSelected(true);
					}
					cb.addChangeListener(new ChangeListener() {
						@Override
						public void stateChanged(ChangeEvent e) {
							JCheckBox jk = (JCheckBox) e.getSource();
							if (jk.isSelected() && !m_filterList.contains(jk.getText())) {
								m_filterList.add(jk.getText());
							} else if (!jk.isSelected() && m_filterList.contains(jk.getText())) {
								m_filterList.remove(jk.getText());
							}
						}
					});
					jp.add(cb);
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "源路径未输入", "警告信息", JOptionPane.WARNING_MESSAGE);
			return ;
		}

		JButton sureButton = new JButton("确定");
		sureButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ViewerFrame.setFilter(m_filterList);
				dispose();
			}
		});
		
		jp.setLayout(new FlowLayout());
		jp2.setLayout(new FlowLayout());

		jp2.add(sureButton);
		this.add(jp);
		this.add(jp2);
		this.setVisible(true);
	}

}
