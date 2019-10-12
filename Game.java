import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * 
 * @author Alyza Diaz Rodriguez
 *
 */
public class Game {
	private String teamName;
	private String opponent;
	private String away;
	private Date day;
	private Time time;
	
	private ImageView teamView;
	private ImageView oppView;
	
	private GridPane gPane;
	private Scene scene;
	
	/**
	 * Initializes a new game day
	 * @param n - team name
	 * @param o - opponent team
	 * @param a - F = team is playing a home game, T = team is away
	 * @param d - date of the game
	 * @param t - time of the game
	 */
	public Game(String n, String o, String a, Date d, Time t) {
		teamName = n;
		opponent = o;
		away = a;
		day = d;
		time = t;
		
		teamView = new ImageView(new Image("logos/"+teamName.toLowerCase()+".png"));
		teamView.setFitWidth(200);
		teamView.setFitHeight(200);
		
		oppView = new ImageView(new Image("logos/"+opponent.toLowerCase()+".png"));
		oppView.setFitWidth(200);
		oppView.setFitHeight(200);
		
		gPane = new GridPane();
		scene = new Scene(gPane, 900, 900);
	}
	
	/**
	 * Sets the team name to something else
	 * @param n
	 */
	public void setTeamName(String n) {
		teamName = n;
	}
	
	/**
	 * Returns the team name
	 * @return
	 */
	public String getTeamName() {
		return teamName;
	}
	
	/**
	 * Sets the opponent's team name
	 * @param o
	 */
	public void setOpponent(String o) {
		opponent = o;
	}
	
	/**
	 * Returns the opponent's team name
	 * @return
	 */
	public String getOpponent() {
		return opponent;
	}
	
	/**
	 * Changes the location of a team's game
	 * @param a
	 */
	public void setAway(String a) {
		away = a;
	}
	
	/**
	 * Returns if the game is an away game or a home game
	 * @return
	 */
	public String getAway() {
		return away;
	}
	
	/**
	 * Changes the date of a game
	 * @param d
	 */
	public void setDay(Date d) {
		day = d;
	}
	
	/**
	 * Returns the date of the game
	 * @return
	 */
	public Date getDay() {
		return day;
	}
	
	/**
	 * Sets the time for the game
	 * @param t
	 */
	public void setTime(Time t) {
		time = t;
	}
	
	/**
	 * Returns the time for the game
	 * @return
	 */
	public Time getTime() {
		return time;
	}
	
	/**
	 * Sets up the information page for the next game
	 */
	@SuppressWarnings("static-access")
	public void setup() {
		gPane.setHgap(30);
		gPane.setVgap(50);
		gPane.setAlignment(Pos.CENTER);
		
		Font bankFont = Font.loadFont(getClass().getResourceAsStream("misc/F25_Bank_Printer.otf"), 25);
		Font brushFont = Font.loadFont(getClass().getResourceAsStream("misc/brush.otf"), 30);
		
		Text title = new Text("Next "+opponent+" Game:");
		title.setFont(bankFont);
		
		Text tn = new Text(teamName);
		tn.setFont(brushFont);
		
		Text at = new Text("at");
		at.setFont(bankFont);
		
		Text opp = new Text(opponent);
		opp.setFont(brushFont);
		
		SimpleDateFormat form = new SimpleDateFormat("MM/dd/yyyy");
		Text d = new Text(form.format(day));
		d.setFont(bankFont);
		
		/*
		 * F: A home game; Opponent at Team Name
		 * T: An away game; Team Name at Opponent
		 * 
		 */
		gPane.add(title, 1, 0);
		gPane.add(at, 1, 2);
		gPane.add(d, 1, 4);
		
		if(away.equals("F")) {
			gPane.add(oppView, 0, 2);
			gPane.add(opp, 0, 3);
			
			gPane.add(teamView, 2, 2);
			gPane.add(tn, 2, 3);
		}else {
			gPane.add(teamView, 0, 2);
			gPane.add(tn, 0, 3);
			
			gPane.add(oppView, 2, 2);
			gPane.add(opp, 2, 3);
		}
		
		for(Node n: gPane.getChildren()) {
			gPane.setHalignment(n, HPos.CENTER);
			gPane.setValignment(n, VPos.CENTER);
		}
	}
	
	/**
	 * Returns the game's scene
	 * @return
	 */
	public Scene getScene() {
		return scene;
	}
	
	/**
	 * Returns the game's grid pane
	 * @return
	 */
	public GridPane getGrid() {
		return gPane;
	}
}
