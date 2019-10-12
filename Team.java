import java.util.ArrayList;

/**
 * 
 * @author Alyza Diaz Rodriguez
 *
 */
public class Team {
	private String teamName;
	private ArrayList<Player> roster;
	
	/**
	 * Initializes a new team
	 * @param n
	 */
	public Team(String n) {
		teamName = n;
		roster = new ArrayList<>();
	}
	
	/**
	 * Returns the team's name
	 * @return team name
	 */
	public String getTeamName() {
		return teamName;
	}
	
	/**
	 * Returns all of the players on a team in an ArrayList
	 * @return team roster
	 */
	public ArrayList<Player> getRoster(){
		return roster;
	}
	
	/**
	 * Adds a player to the team
	 * @param p
	 */
	public void addPlayer(Player p) {
		roster.add(p);
	}
	
	/**
	 * Removes a player from a team
	 * @param p
	 */
	public void removePlayer(Player p) {
		int index = playerIndex(p);
		roster.remove(index);
	}
	
	/**
	 * Finds a player within the team by looking for their name
	 * @param name
	 * @return the player
	 */
	public Player findPlayer(String name) {
		for(int i=0;i<roster.size();i++) {
			if(roster.get(i).getName().equals(name)) {
				return roster.get(i);
			}
		}
		
		return null;
	}
	
	/**
	 * Finds a player's index
	 * @param p
	 * @return index
	 */
	public int playerIndex(Player p) {
		String name = p.getName();
		for(int i=0;i<roster.size();i++) {
			if(roster.get(i).getName().equals(name)) {
				return i;
			}
		}
		
		return -1;
	}
	
	/**
	 * Prints all the information of all players on a team
	 */
	public void printRoster() {
		for(int i=0;i<roster.size();i++) {
			roster.get(i).print();
		}
	}
}
