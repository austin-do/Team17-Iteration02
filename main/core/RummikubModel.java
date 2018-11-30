package core;

import java.util.ArrayList;

public class RummikubModel {

	
	private ArrayList<Player> players;
	private Table table;
	private Pile pile;
	private boolean timer;
	
	public RummikubModel() {
		table = new Table();
		pile = new Pile(); 
		timer = false;
	}
	
	
	//GETTERS and SETTERS
	
	public Table getTable() {
		return this.table;
	}
	
	public void setPlayers(ArrayList<Player> p) {
		players = p;
	}
	
	public void setTable(Table t) {
		table = t;
	}


	public ArrayList<Player> getPlayers() {
		return players;
	}

	public boolean getTimer() {
		return this.timer;
	}
	
	public void setTimer(boolean b) {
		this.timer = b;
		
	}
	
	
	
}
