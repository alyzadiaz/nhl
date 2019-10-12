import java.sql.Date;
import java.text.SimpleDateFormat;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * 
 * @author Alyza Diaz Rodriguez
 *
 */
public class Player {
	private String teamName;
	private String name;
	private int number;
	private String flag;
	private String position;
	private String height;
	private int weight;
	private int experience;
	private Date dob;
	private int age;
	
	private Image photo;
	private ImageView view;
	private ImageView copy;
	
	private Image country;
	private ImageView viewCountry;
	
	private Button button;
	private Button back;
	
	private VBox vbox;
	private Scene scene;
	
	private BackgroundImage background;
	
	/**
	 * Initializes a new player with the following variables:
	 * @param a - team name
	 * @param b - name
	 * @param c - jersey number
	 * @param d - country/flag
	 * @param f - position
	 * @param g - height
	 * @param h - weight
	 * @param i - years of experience
	 * @param j - birthday
	 * @param k - age
	 */
	public Player(String a, String b, int c, String d, String f, String g, int h, int i, Date j, int k) {
		teamName = a;
		name = b;
		number = c;
		flag = d;
		position = f;
		height = g;
		weight = h;
		experience = i;
		dob = j;
		age = k;
		
		button = new Button();
		back = new Button("Back");
		
		vbox = new VBox(10);
		vbox.setAlignment(Pos.CENTER);
		scene = new Scene(vbox, 900, 900);
		
		setCountry(flag);
		viewCountry = new ImageView(country);
	}
	
	/**
	 * Will change the name of the player's team to a.
	 * @param a - team name
	 */
	public void setTeam(String a) {
		teamName = a;
	}
	
	/**
	 * Returns the player's name
	 * @return
	 */
	public String getTeam() {
		return teamName;
	}
	
	/**
	 * Change the player's name to b
	 * @param b - name
	 */
	public void setName(String b) {
		name = b;
	}
	
	/**
	 * Returns the player's name
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the player's jersey number
	 * @param c - number
	 */
	public void setNumber(int c) {
		number = c;
	}
	
	/**
	 * Returns the player's jersey number
	 * @return
	 */
	public int getNumber() {
		return number;
	}
	
	/**
	 * Sets the player's country of origin
	 * @param d - flag
	 */
	public void setFlag(String d) {
		flag = d;
	}
	
	/**
	 * Gets the player's country of origin
	 * @return
	 */
	public String getFlag() {
		return flag;
	}
	
	/**
	 * Sets the player's game position
	 * @param f - position
	 */
	public void setPos(String f) {
		position = f;
	}
	
	/**
	 * Returns the player's game position
	 * @return
	 */
	public String getPos() {
		return position;
	}
	
	/**
	 * Sets player's height
	 * @param g - height
	 */
	public void setHeight(String g) {
		height = g;
	}
	
	/**
	 * Gets players height
	 * @return
	 */
	public String getHeight() {
		return height;
	}
	
	/**
	 * Sets the player's weight
	 * @param h - weight
	 */
	public void setWeight(int h) {
		weight = h;
	}
	
	/**
	 * Gets the player's weight
	 * @return
	 */
	public int getWeight() {
		return weight;
	}
	
	/**
	 * Sets the player's number of years of experience
	 * @param i - experience
	 */
	public void setExp(int i) {
		experience = i;
	}
	
	/**
	 * Gets the player's number of years of experience
	 * @return
	 */
	public int getExp() {
		return experience;
	}
	
	/**
	 * Sets the player's birthday
	 * @param j - dob
	 */
	public void setDOB(Date j) {
		dob = j;
	}
	
	/**
	 * Gets the player's birthday
	 * @return
	 */
	public Date getDOB() {
		return dob;
	}
	
	/**
	 * Sets the player's age
	 * @param k - age
	 */
	public void setAge(int k) {
		age = k;
	}
	
	/**
	 * Gets the player's age
	 * @return
	 */
	public int getAge() {
		return age;
	}
	
	/**
	 * Prints out all of a player's information
	 */
	public void print() {
		System.out.printf("%s, %s #%d, %s, %s, height: %s, %d lbs, %d years of exp., ", 
				teamName, name, number, flag, position, height, weight, experience);
		System.out.printf("%1$tA, %1$tB %1$tY,", dob);
		System.out.printf(" %d years old\n", age);
	}
	
	/**
	 * Creates and sets the player's photo
	 */
	public void createPhoto(String URL) {
		photo = new Image(URL);
		
		view = new ImageView(photo);
		view.setFitWidth(100);
		view.setFitHeight(100);
		
		//a copy of the player's headshot
		copy = new ImageView(photo);
		copy.setFitWidth(250);
		copy.setFitHeight(250);
	}
	
	/**
	 * Gets the player's photo
	 * @return
	 */
	public ImageView getPhoto() {
		return view;
	}
	
	/**
	 * Matches the player with their country flag image
	 * @param f - country abbreviation
	 */
	private void setCountry(String f) {
		switch(f) {
		case "CAN":
			country = new Image("flags/can.png");
			break;
		case "CZE":
			country = new Image("flags/cze.png");
			break;
		case "FIN":
			country = new Image("flags/fin.png");
			break;
		case "SVK":
			country = new Image("flags/svk.png");
			break;
		case "SWE":
			country = new Image("flags/swe.png");
			break;
		case "USA":
			country = new Image("flags/usa.png");
		}
	}
	
	/**
	 * Displays the player's country flag
	 * @return
	 */
	public ImageView getCountryFlag() {
		return viewCountry;
	}
	
	/**
	 * Sets the player's button
	 * @param b
	 */
	public void setButton(Button b) {
		button = b;
	}
	
	/**
	 * Returns the player's button
	 * @return
	 */
	public Button getButton() {
		return button;
	}
	
	/**
	 * Returns the player's back button
	 * @return
	 */
	public Button getBack() {
		return back;
	}
	
	/**
	 * Returns the player's VBox
	 * @return
	 */
	public VBox getBox() {
		return vbox;
	}
	
	/*
	 * Returns the background image
	 */
	public BackgroundImage getBackground() {
		return background;
	}
	
	/**
	 * Sets up the player's background with their action shot
	 */
	private void createBackG() {
		String team = "";
		switch(teamName) {
		case "Bruins":
			team = "bruins background.png";
			break;
		case "Blackhawks":
			team = "hawks background.png";
			break;
		}
		
		background = new BackgroundImage(new Image("misc/"+team, 900, 900, false, true),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
				BackgroundSize.DEFAULT);
		
		vbox.setBackground(new Background(background));
	}
	
	/**
	 * Sets up the player's information page
	 */
	public void setup() {
		Font bankFont = Font.loadFont(getClass().getResourceAsStream("misc/F25_Bank_Printer.otf"), 15);
		Font brushFont = Font.loadFont(getClass().getResourceAsStream("misc/brush.otf"), 50);
		
		Text tn = new Text(teamName);
		tn.setFont(bankFont);
		Text n = new Text(name);
		n.setFont(brushFont);
		Text j = new Text("#"+Integer.toString(number));
		j.setFont(bankFont);
		Text f = new Text("Country of Origin: "+flag);
		f.setFont(bankFont);
		Text p = new Text("Position: "+position);
		p.setFont(bankFont);
		Text h = new Text("Height: "+height);
		h.setFont(bankFont);
		Text e = new Text("Years of experience: "+Integer.toString(experience));
		e.setFont(bankFont);
		
		SimpleDateFormat form = new SimpleDateFormat("MM/dd/yyyy");
		Text b = new Text("Birthday: "+form.format(dob));
		b.setFont(bankFont);
		
		Text a = new Text("Age: "+Integer.toString(age));
		a.setFont(bankFont);
		
		createBackG();
		
		vbox.getChildren().addAll(viewCountry, copy, tn, n, j, f, p, h, e, b, a, back);
	}
	
	/**
	 * Returns the player's scene
	 * @return
	 */
	public Scene getScene() {
		return scene;
	}
}
