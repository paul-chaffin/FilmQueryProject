package com.skilldistillery.filmquery.entities;

import java.util.Objects;

public class Actor {
	private int actor_id;
	private String actor_f_name;
	private String actor_l_name;
	
	public Actor() {};
	
	public Actor(int actor_id, String actor_f_name, String actor_l_name) {
		super();
		this.actor_id = actor_id;
		this.actor_f_name = actor_f_name;
		this.actor_l_name = actor_l_name;
	}

	public int getActor_id() {
		return actor_id;
	}

	public void setActor_id(int actor_id) {
		this.actor_id = actor_id;
	}

	public String getActor_f_name() {
		return actor_f_name;
	}

	public void setActor_f_name(String actor_f_name) {
		this.actor_f_name = actor_f_name;
	}

	public String getActor_l_name() {
		return actor_l_name;
	}

	public void setActor_l_name(String actor_l_name) {
		this.actor_l_name = actor_l_name;
	}

	@Override
	public String toString() {
		return "Actor [actor_id=" + actor_id + ", actor_f_name=" + actor_f_name + ", actor_l_name=" + actor_l_name
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(actor_f_name, actor_id, actor_l_name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Actor other = (Actor) obj;
		return Objects.equals(actor_f_name, other.actor_f_name) && actor_id == other.actor_id
				&& Objects.equals(actor_l_name, other.actor_l_name);
	}
	
	

}
