package com.dayspass.datacenter.bean;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@XmlRootElement(name = "Resp")
public class BdStageBody implements Serializable {
	private static final long serialVersionUID = 1L;
	@XmlElement(name = "row")
	private List<BdStageParam> row;// xml标签i
	
	@XmlTransient	
	public List<BdStageParam> getRow() {
		return row;
	}
	public void setRow(List<BdStageParam> row) {
		this.row = row;
	} 
	
}
