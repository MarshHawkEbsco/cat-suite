package com.marshhawk.mvc.model;

import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Mij {
	
	@Id
	public ObjectId _id;
	private String leader;
	private List<Map<String, Object>> fields;
	
	public Mij() {}
	 
	  public Mij(ObjectId _id, String leader, List<Map<String, Object>> fields) 
	  {
	    this._id = _id;
	    this.leader = leader;
	    this.fields = fields;
	  }

	public List<Map<String, Object>> getFields() {
		return fields;
	}

	public void setFields(List<Map<String, Object>> fields) {
		this.fields = fields;
	}

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public String getLeader() {
		return leader;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}

}
