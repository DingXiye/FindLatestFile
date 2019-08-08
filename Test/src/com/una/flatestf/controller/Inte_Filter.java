package com.una.flatestf.controller;

import java.util.List;
/**
 * 打印日志以及过滤不要的项目
 * @author Lenovo
 *
 */
public interface Inte_Filter {
	void Logg(String destPath);
	List<String> Filter(String srcPath,List<String> filters);
}
