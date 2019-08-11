package com.una.flatestf.model;

import java.util.List;
/**
 * 返回信息类
 * id：100 路径错误
 *    101 获取过滤配置文件错误
 *    102 版本命名格式不正确导致的错误
 *    103 获得最新版本路径
 *    104 拷贝完成
 *    105 拷贝目标路径错误
 *    106 成功获得过滤信息
 *    
 * @author dingye
 *
 */
public class MsgModel {
	private String msg;
	private Integer id;
	private List<String> list;
	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public MsgModel(){
		
	}
	public MsgModel(String msg, Integer id, List<String> list) {
		super();
		this.msg = msg;
		this.id = id;
		this.list = list;
	}
}
