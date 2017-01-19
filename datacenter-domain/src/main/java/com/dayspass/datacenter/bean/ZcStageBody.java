package com.dayspass.datacenter.bean;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@XmlRootElement(name = "Resp")
public class ZcStageBody implements Serializable {
	private static final long serialVersionUID = 1L;
	@XmlElement(name = "row")
	private List<ZcStageParam> row;// xml标签i
	
	@XmlTransient	
	public List<ZcStageParam> getRow() {
		return row;
	}
	public void setRow(List<ZcStageParam> row) {
		this.row = row;
	} 
	
}
