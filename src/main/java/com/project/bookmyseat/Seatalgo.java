package com.project.bookmyseat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.bookmyseat.models.Bookmyoffice;
import com.project.bookmyseat.repository.Bookmyofficerepository;

@Repository
public class Seatalgo {
   
	@Autowired
	Bookmyofficerepository bookmyofficerepository;
	private int no_of_seats = 74;
	private static final int SEAT_PER_PORT = 25;
	private int floor = 1, port = 1, seats = 1;
	private List<Teams> teams = new ArrayList<>();
	private List<Seatleft> seatleft = new ArrayList<>();
	Date date = new Date(new Date().getTime() + 86400000);  
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
    String strDate= formatter.format(date); 
	
	private void setteams() {
		teams.add(new Teams(1, 9));
		teams.add(new Teams(2, 9));
		teams.add(new Teams(3, 7));
		teams.add(new Teams(4, 7));
		teams.add(new Teams(5, 5));
		teams.add(new Teams(6, 1));
		teams.add(new Teams(7, 3));
		teams.add(new Teams(8, 3));
		teams.add(new Teams(9, 1));
		teams.add(new Teams(10, 5));
		
		//getManagers();
		List<Bookmyoffice> result=bookmyofficerepository.getManagers(strDate);
		for(Bookmyoffice bm:result) {
			teams.add(new Teams(bm.getManagerId(),bookmyofficerepository.teamsize(bm.getManagerId(), strDate)));
		}
		
	}

	public void arrange() {
		setteams();
		if (!teams.isEmpty()) {
			System.out.println("M T f p s");
			for (Teams obj : teams) {
				Seatleft sl=remaining(obj.getTeam_size());
				if (sl==null) {
					if(seats+obj.getTeam_size()>SEAT_PER_PORT) {
						seatleft.add(new Seatleft(floor,port,seats,(SEAT_PER_PORT-seats)+1));
						seats=1;
						if(port==4) {
							port=1;
							floor+=1;
						}
						else {
							port+=1;
						}
						System.out.println(obj.getManagerid()+" "+obj.getTeam_size()+" "+floor+" "+port+" "+seats);
						assignSeat(obj.getManagerid(),floor,port,seats);
						seats+=obj.getTeam_size();
						no_of_seats-=obj.getTeam_size();
						System.out.println(obj.getManagerid()+" "+obj.getTeam_size()+" "+floor+" "+port+" "+seats);
						System.out.println("Done");
					}
					else {
						System.out.println(obj.getManagerid()+" "+obj.getTeam_size()+" "+floor+" "+port+" "+seats);
						assignSeat(obj.getManagerid(),floor,port,seats);
						seats+=obj.getTeam_size();
						no_of_seats-=obj.getTeam_size();
						System.out.println(obj.getManagerid()+" "+obj.getTeam_size()+" "+floor+" "+port+" "+seats);
						System.out.println("Done");
					}
				}
				else {
					System.out.println(obj.getManagerid()+" "+obj.getTeam_size()+" "+sl.getFloor()+" "+sl.getPort());
					assignSeat(obj.getManagerid(),sl.getFloor(),sl.getPort(),sl.getSeat());
					sl.setLeft(0);
					System.out.println("Done");
				}
			}
			
		} else {
			
		}

	}
		
	private Seatleft remaining(int size) {
		for(Seatleft s:seatleft) {
			if(s.getLeft()==size) {
				return s;
			}
		}
		return null;
		
	}
	private void assignSeat(int mgrid,int f,int p,int start) {
		bookmyofficerepository.updateSeat(mgrid,f,p,start,strDate);
	}
	 
	
	
	
	public static void main(String args[]) {
		Seatalgo seatalgo = new Seatalgo();
		seatalgo.arrange();
	}
}
