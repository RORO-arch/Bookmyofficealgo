package com.project.bookmyseat;

public class Teams {

	private int managerid;
	private int team_size;
	
	public Teams() {
	
	}
	
	public Teams(int managerid, int team_size) {
		super();
		this.managerid = managerid;
		this.team_size = team_size;
	}
	public int getManagerid() {
		return managerid;
	}
	public void setManagerid(int managerid) {
		this.managerid = managerid;
	}
	public int getTeam_size() {
		return team_size;
	}
	public void setTeam_size(int team_size) {
		this.team_size = team_size;
	}
	
	

}