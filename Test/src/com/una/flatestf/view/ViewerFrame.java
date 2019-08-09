package com.una.flatestf.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.una.flatestf.controller.Inte_Controller;
import com.una.flatestf.impl.Inte_Controller_Impl;

public class ViewerFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JFileChooser fileChooser = new JFileChooser();;
	static ViewerFrame m_view;
	private JTextField srcJtf;
	private JTextField destJtf;
	private static String m_srcPath;
	private static String m_destPath;
	static List<String> m_filters = new ArrayList<String>();//��������

	public ViewerFrame() {
		this.setPreferredSize(new Dimension(480, 330));
		this.setTitle("�����ļ�����");
		this.setLocation(417, 177);
		this.setLayout(new GridLayout(4, 1, 20, 20));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		init();
	}

	public void init() {
		JPanel srcJp = new JPanel();
		JPanel destJp = new JPanel();
		JPanel buttonJp = new JPanel();
		JPanel msgJp = new JPanel();

		srcJp.setLayout(new FlowLayout());
		destJp.setLayout(new FlowLayout());
		buttonJp.setLayout(new FlowLayout());
		msgJp.setLayout(new GridLayout());

		JButton srcButton = new JButton("���");
		srcButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fileChooser.setDialogTitle("ѡ��Դ�ļ���");
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int i = fileChooser.showOpenDialog(getContentPane());// ��ʾ�ļ�ѡ��Ի���
				// �ж��û��������Ƿ�Ϊ���򿪡���ť
				if (i == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();// ���ѡ�е��ļ�����
					srcJtf.setText(selectedFile.getPath());// ��ʾѡ���ļ�������
					m_srcPath = selectedFile.getPath();
				}

			}
		});

		JButton destButton = new JButton("���");
		destButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fileChooser.setDialogTitle("ѡ��Ŀ���ļ���");
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int i = fileChooser.showOpenDialog(getContentPane());// ��ʾ�ļ�ѡ��Ի���
				// �ж��û��������Ƿ�Ϊ���򿪡���ť
				if (i == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();// ���ѡ�е��ļ�����
					destJtf.setText(selectedFile.getPath());// ��ʾѡ���ļ�������
					m_destPath = selectedFile.getPath();
				}

			}
		});
		srcJtf = new JTextField(15);
		srcJtf.setEditable(false);
		destJtf = new JTextField(15);
		destJtf.setEditable(false);
		
		JLabel srclabel = new JLabel("Դ�ļ�Ŀ¼");
		JLabel destlabel = new JLabel("Ŀ���ļ�Ŀ¼");
		srcJp.add(srclabel);
		srcJp.add(srcJtf);
		srcJp.add(srcButton);
		destJp.add(destlabel);
		destJp.add(destJtf);
		destJp.add(destButton);

		JButton copyButton = new JButton("����");
		copyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Inte_Controller controller=new Inte_Controller_Impl(m_srcPath,m_destPath);
				controller.Start();
			}
		});
		
		buttonJp.add(copyButton);

		this.add(srcJp);
		this.add(destJp);
		this.add(buttonJp);
		this.add(msgJp);
		this.pack();
		this.setVisible(true);
	}

	public static void main(String[] args) {
		m_view = new ViewerFrame();
	}
}