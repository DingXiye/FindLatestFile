package com.una.flatestf.controller;

import java.util.List;
/**
 * ��ӡ��־�Լ����˲�Ҫ����Ŀ
 * @author Lenovo
 *
 */
public interface Inte_Filter {
	void Logg(String destPath);
	List<String> Filter(String srcPath,List<String> filters);
}
