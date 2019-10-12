import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * 
 * @author Alyza Diaz Rodriguez
 *
 */
public class Rink extends Application{
	
	@SuppressWarnings("static-access")
	public void start(Stage primaryStage) {
		//================================================================================================================================================================
	    // SQL Connection
	    //================================================================================================================================================================
		Team bruins = new Team("Bruins");
		Team hawks = new Team("Blackhawks");
		
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String today = format.format(calendar.getTime());
		
		DateTimeFormatter clock = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalDateTime time = LocalDateTime.now();
		String now = clock.format(time);
		
		/*
		 * Bruins: 0
		 * Blackhawks: 1
		 * 
		 */
		ArrayList<Game> nextGames = new ArrayList<>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			
		} catch (InstantiationException e) {
			System.out.println("Unable to initialize.");
		} catch (IllegalAccessException e) {
			System.out.println("You do not have access to this server!");
		} catch (ClassNotFoundException e) {
			System.out.println("Server not found.");
		}
		
		/*
		 * After an instance of the MySQL driver is created, a connection to the database will be made.
		 * The getPlayers query will pull and create all of the players from the database and add them to their respective teams.
		 * The getBruinsDay query will pull the next closest Bruins game to today's date.
		 * The getHawksDay query will pull the next closest Blackhawks game to today's date.
		 * 
		 */
		try {
			Connection c = DriverManager.getConnection("database","username","password");
			String getPlayers = "SELECT * FROM player";
			String getBruinsDay = "SELECT * FROM game WHERE Day>='"+today+"' AND Time>'"+now+"' AND Opponent = 'Bruins' LIMIT 1";
			String getHawksDay = "SELECT * FROM game WHERE Day>='"+today+"' AND Time>'"+now+"'AND Opponent= 'Blackhawks' LIMIT 1"; 
			
			Statement p = c.createStatement();
			ResultSet ps = p.executeQuery(getPlayers);
			
			Statement b = c.createStatement();
			ResultSet bd = b.executeQuery(getBruinsDay);
			
			Statement h = c.createStatement();
			ResultSet hd = h.executeQuery(getHawksDay);

			while(ps.next()) {
				String teamName = ps.getString("TeamName");
				String name = ps.getString("Name");
				int number = ps.getInt("Number");
				String flag = ps.getString("Flag");
				String position = ps.getString("Position");
				String height = ps.getString("Height");
				int weight = ps.getInt("Weight");
				int experience = ps.getInt("Exp");
				Date dob = ps.getDate("DOB");
				int age = ps.getInt("Age");
				
				Player s = new Player(teamName, name, number, flag, position, height, weight, experience, dob, age);
				
				if(teamName.equals("Bruins")) {
					bruins.addPlayer(s);
				}else{
					hawks.addPlayer(s);
				}
			}
			
			while(bd.next()) {
				String teamName = bd.getString("TeamName");
				String opp = bd.getString("Opponent");
				String away = bd.getString("Away");
				Date day = bd.getDate("Day");
				Time timeNow = bd.getTime("Time");
				
				Game g = new Game(teamName, opp, away, day, timeNow);
				nextGames.add(g);
			}
			
			while(hd.next()) {
				String teamName = hd.getString("TeamName");
				String opp = hd.getString("Opponent");
				String away = hd.getString("Away");
				Date day = hd.getDate("Day");
				Time timeNow = hd.getTime("Time");
				
				Game g = new Game(teamName, opp, away, day, timeNow);
				nextGames.add(g);
			}
			
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
		}
		
		//================================================================================================================================================================
	    // Buttons
	    //================================================================================================================================================================
		Button credits = new Button("Credits");
		
		Button bostonBruins = new Button("Boston Bruins");
		bostonBruins.wrapTextProperty().setValue(true);
		
		Button chicagoHawks = new Button("Chicago Blackhawks");
		chicagoHawks.wrapTextProperty().setValue(true);
		
		Button bBack = new Button("Back");
		Button hBack = new Button("Back");
		
		Button bSwitch = new Button("Blackhawks");
		Button bSwitchSeason = new Button("Blackhawks");
		
		Button hSwitch = new Button("Bruins");
		Button hSwitchSeason = new Button("Bruins");
		
		Button bSeason = new Button("Next Game");
		Button bSeasonBack = new Button("Back");
		
		Button hSeason = new Button("Next Game");
		Button hSeasonBack = new Button("Back");
		
		//================================================================================================================================================================
	    // Image Setup
	    //================================================================================================================================================================
		ArrayList<Player> bruinsRoster = bruins.getRoster();
		for(int i=0;i<bruinsRoster.size();i++) {
			String name = bruinsRoster.get(i).getName();
			String lastName = name.substring(name.lastIndexOf(" ")+1);
			lastName.toLowerCase();
			
			bruinsRoster.get(i).createPhoto("player photos/"+lastName+".jpg");
		}
		
		ArrayList<Player> hawksRoster = hawks.getRoster();
		for(int i=0;i<hawksRoster.size();i++) {
			String name = hawksRoster.get(i).getName();
			String lastName = name.substring(name.lastIndexOf(" ")+1);
			lastName.toLowerCase();
			
			hawksRoster.get(i).createPhoto("player photos/"+lastName+".jpg");
		}
		
		Image bruinsLogo = new Image("misc/bruins.png");
		ImageView viewBLogo = new ImageView(bruinsLogo);
		viewBLogo.setFitHeight(100);
		viewBLogo.setFitWidth(100);
		
		Image hawksLogo = new Image("misc/hawks.gif");
		ImageView viewHLogo = new ImageView(hawksLogo);
		viewHLogo.setFitHeight(100);
		viewHLogo.setFitWidth(100);
		
		//================================================================================================================================================================
	    // Backgrounds
	    //================================================================================================================================================================
		BackgroundImage menuBackground = new BackgroundImage(new Image("misc/menu.png", 900, 900,false,true),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
				BackgroundSize.DEFAULT);
		
		//================================================================================================================================================================
	    // Panes
	    //================================================================================================================================================================
		HBox menuHBox = new HBox(260);
		menuHBox.setAlignment(Pos.CENTER);
		menuHBox.getChildren().addAll(bostonBruins, credits, chicagoHawks);
		
		Pane creditPane = new Pane();
		creditPane.setBackground(new Background(new BackgroundFill(Color.PLUM, null, null)));
		
		GridPane bruinsGrid = new GridPane();
		bruinsGrid.setHgap(5);
		bruinsGrid.setVgap(20);
		bruinsGrid.setAlignment(Pos.CENTER);
		
		bruinsGrid.setBackground(new Background(new BackgroundFill(Color.GOLD, null, null)));
		
		bruinsGrid.add(viewBLogo, 3, 0);
		bruinsGrid.add(bSwitch, 6, 6);
		bruinsGrid.setHalignment(bSwitch, HPos.CENTER);
		
		bruinsGrid.add(bBack,0,6);
		bruinsGrid.setHalignment(bBack, HPos.CENTER);
		
		bruinsGrid.add(bSeason, 3, 6);
		bruinsGrid.setHalignment(bSeason, HPos.CENTER);
		
		GridPane hawksGrid = new GridPane();
		hawksGrid.setHgap(5);
		hawksGrid.setVgap(20);
		hawksGrid.setAlignment(Pos.CENTER);
		
		hawksGrid.setBackground(new Background(new BackgroundFill(Color.CRIMSON, null, null)));
		
		hawksGrid.add(viewHLogo, 3, 0);
		hawksGrid.add(hSwitch, 6, 6);
		hawksGrid.setHalignment(hSwitch, HPos.CENTER);
		
		hawksGrid.add(hBack,0,6);
		hawksGrid.setHalignment(hBack, HPos.CENTER);
		
		hawksGrid.add(hSeason, 3, 6);
		hawksGrid.setHalignment(hSeason, HPos.CENTER);
		
		//================================================================================================================================================================
	    // Next Game Setup
	    //================================================================================================================================================================
		nextGames.get(0).setup();
		nextGames.get(0).getGrid().add(bSeasonBack, 0, 5);
		nextGames.get(0).getGrid().add(bSwitchSeason, 2, 5);
		
		nextGames.get(0).getGrid().setHalignment(bSeasonBack, HPos.CENTER);
		nextGames.get(0).getGrid().setHalignment(bSwitchSeason, HPos.CENTER);
		
		nextGames.get(0).getGrid().setBackground(new Background(new BackgroundFill(Color.GOLD, null, null)));
		
		
		nextGames.get(1).setup();
		nextGames.get(1).getGrid().add(hSeasonBack, 0, 5);
		nextGames.get(1).getGrid().add(hSwitchSeason, 2, 5);
		
		nextGames.get(1).getGrid().setHalignment(hSeasonBack, HPos.CENTER);
		nextGames.get(1).getGrid().setHalignment(hSwitchSeason, HPos.CENTER);
		
		nextGames.get(1).getGrid().setBackground(new Background(new BackgroundFill(Color.CRIMSON, null, null)));

		//================================================================================================================================================================
	    // Scene Initializations
	    //================================================================================================================================================================
		Scene scene = new Scene(menuHBox, 900, 900);
		Scene bruinsSelect = new Scene(bruinsGrid, 900, 900);
		Scene hawksSelect = new Scene(hawksGrid, 900, 900);
		Scene creditView = new Scene(creditPane, 900, 900);
		
		menuHBox.setBackground(new Background(menuBackground));
		
		primaryStage.setTitle("Bruins vs. Blackhawks");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
		
		//================================================================================================================================================================
	    // Credits
	    //================================================================================================================================================================
		Circle iconOutline = new Circle();
		iconOutline.setRadius(50);
		iconOutline.setFill(Color.TRANSPARENT);
		iconOutline.setStroke(Color.BLACK);
		iconOutline.setCenterX(450);
		iconOutline.setCenterY(300);
		
		Image cat = new Image("misc/cat.gif");
		ImageView viewCat = new ImageView(cat);
		viewCat.setFitWidth(100);
		viewCat.setFitHeight(100);
		viewCat.setLayoutX(400);
		viewCat.setLayoutY(250);
		
		Text author = new Text("©Alyza Diaz Rodriguez");
		author.setLayoutX(375);
		author.setLayoutY(400);
		
		Text images = new Text("Player photos were found on nhl.com\n"
				+ "Country flags were found on countryflags.com\n");
		images.setTextAlignment(TextAlignment.CENTER);
		images.setLayoutX(300);
		images.setLayoutY(550);
		
		Hyperlink linkedin = new Hyperlink("LinkedIn");
		linkedin.setTextFill(Color.DARKMAGENTA);
		linkedin.setLayoutX(420);
		linkedin.setLayoutY(425);
		linkedin.setOnAction(e ->{
			if(Desktop.isDesktopSupported()) {
				try {
					Desktop.getDesktop().browse(new URI("http://linkedin.com/in/alyzadiaz"));
				}catch(IOException f) {
					System.out.println("Unable to get to browser.");
				}catch(URISyntaxException g) {
					System.out.println("Invalid URL.");
				}
			}
		});
		
		Hyperlink github = new Hyperlink("GitHub");
		github.setTextFill(Color.DARKMAGENTA);
		github.setLayoutX(425);
		github.setLayoutY(450);
		github.setOnAction(e ->{
			if(Desktop.isDesktopSupported()) {
				try {
					Desktop.getDesktop().browse(new URI("http://github.com/alyzadiaz"));
				}catch(IOException f) {
					System.out.println("Unable to get to browser.");
				}catch(URISyntaxException g) {
					System.out.println("Invalid URL.");
				}
			}
		});
		
		Button creditsBack = new Button("Back");
		creditsBack.setLayoutX(25);
		creditsBack.setLayoutY(850);
		creditsBack.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent click) {
				primaryStage.setScene(scene);
			}
		});
		
		creditPane.getChildren().addAll(iconOutline, viewCat, author, linkedin, github, images, creditsBack);
		
		//================================================================================================================================================================
	    // Music
	    //================================================================================================================================================================
		String bruinsSong = Rink.class.getResource("music/bruins.mp3").toString();
		String hawksSong = Rink.class.getResource("music/hawks.mp3").toString();
		String cheers = Rink.class.getResource("music/cheers.mp3").toString();
		
		Media ch = new Media(cheers);
		Media b = new Media(bruinsSong);
		Media h = new Media(hawksSong);
		
		MediaPlayer cSong = new MediaPlayer(ch);
		MediaPlayer bSong = new MediaPlayer(b);
		MediaPlayer hSong = new MediaPlayer(h);
		
		cSong.setOnEndOfMedia(new Runnable(){
			public void run(){
				cSong.seek(Duration.ZERO);
			}
		});
		
		bSong.setOnEndOfMedia(new Runnable(){
			public void run(){
				bSong.seek(Duration.ZERO);
			}
		});
		
		hSong.setOnEndOfMedia(new Runnable(){
			public void run(){
				hSong.seek(Duration.ZERO);
			}
		});
		
		cSong.play();
		
		//================================================================================================================================================================
	    // Button Actions
	    //================================================================================================================================================================
		credits.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent click) {
				primaryStage.setScene(creditView);
			}
		});
		
		bostonBruins.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent click) {
				primaryStage.setScene(bruinsSelect);
				cSong.stop();
				
				bSong.play();
			}
		});
		
		chicagoHawks.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent click) {
				primaryStage.setScene(hawksSelect);
				cSong.stop();
				
				hSong.play();
			}
		});
		
		bBack.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent click) {
				primaryStage.setScene(scene);
				bSong.stop();
				
				cSong.play();
			}
		});
		
		hBack.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent click) {
				primaryStage.setScene(scene);
				hSong.stop();
				
				cSong.play();
			}
		});
		
		bSwitch.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent click) {
				primaryStage.setScene(hawksSelect);
				bSong.stop();
				
				hSong.play();
			}
		});
		
		hSwitch.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent click) {
				primaryStage.setScene(bruinsSelect);
				hSong.stop();
				
				bSong.play();
			}
		});
		
		bSeason.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent click) {
				primaryStage.setScene(nextGames.get(0).getScene());
			}
		});
		
		hSeason.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent click) {
				primaryStage.setScene(nextGames.get(1).getScene());
			}
		});
		
		bSeasonBack.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent click) {
				primaryStage.setScene(bruinsSelect);
			}
		});
		
		hSeasonBack.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent click) {
				primaryStage.setScene(hawksSelect);
			}
		});
	
		bSwitchSeason.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent click) {
				primaryStage.setScene(nextGames.get(1).getScene());
				bSong.stop();
				
				hSong.play();
			}
		});
		
		hSwitchSeason.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent click) {
				primaryStage.setScene(nextGames.get(0).getScene());
				hSong.stop();
				
				bSong.play();
			}
		});
		
		//================================================================================================================================================================
	    // Player Select Setup
	    //================================================================================================================================================================
		int iBruins = 0;
		while(iBruins<bruinsRoster.size()) {
			for(int c=0;c<7;c++) {
				for(int r=1;r<5;r++) {
					Player bP = bruinsRoster.get(iBruins);
					Button bPB = new Button(bP.getName());
					bP.setButton(bPB);
					bP.setup();
					
					bPB.setGraphic(bP.getPhoto());
					bPB.setContentDisplay(ContentDisplay.TOP);
					bPB.wrapTextProperty().setValue(true);
					bPB.setTextAlignment(TextAlignment.CENTER);
					bPB.setStyle("-fx-font-size:12");
					
					bruinsGrid.add(bPB, c, r);
					iBruins++;
				}
			}
		}
		
		for(int i=0;i<bruinsRoster.size();i++) {
			Player p = bruinsRoster.get(i);
			p.getButton().setOnAction(new EventHandler<ActionEvent>() {
				@Override public void handle(ActionEvent click) {
					primaryStage.setScene(p.getScene());
				}
			});
			
			p.getBack().setOnAction(new EventHandler<ActionEvent>() {
				@Override public void handle(ActionEvent click) {
					primaryStage.setScene(bruinsSelect);
				}
			});
		}
		
		int iHawks = 0;
		while(iHawks<hawksRoster.size()) {
			for(int c=0;c<7;c++) {
				for(int r=1;r<4;r++) {
					Player hP = hawksRoster.get(iHawks);
					Button hPB = new Button(hP.getName());
					hP.setButton(hPB);
					hP.setup();
					
					hPB.setGraphic(hP.getPhoto());
					hPB.setContentDisplay(ContentDisplay.TOP);
					hPB.wrapTextProperty().setValue(true);
					hPB.setTextAlignment(TextAlignment.CENTER);
					hPB.setStyle("-fx-font-size:12");
					
					
					hawksGrid.add(hPB, c, r);
					iHawks++;
				}
			}
			
			for(int c=2;c<5;c++) {
				Player hP = hawksRoster.get(iHawks);
				Button hPB = new Button(hP.getName());
				hP.setButton(hPB);
				hP.setup();
				
				hPB.setGraphic(hP.getPhoto());
				hPB.setContentDisplay(ContentDisplay.TOP);
				hPB.wrapTextProperty().setValue(true);
				hPB.setTextAlignment(TextAlignment.CENTER);
				hPB.setStyle("-fx-font-size:12");
				
				hawksGrid.add(hPB, c, 4);
				iHawks++;
			}
		}
		
		for(int i=0;i<hawksRoster.size();i++) {
			Player p = hawksRoster.get(i);
			p.getButton().setOnAction(new EventHandler<ActionEvent>() {
				@Override public void handle(ActionEvent click) {
					primaryStage.setScene(p.getScene());
				}
			});
			
			p.getBack().setOnAction(new EventHandler<ActionEvent>() {
				@Override public void handle(ActionEvent click) {
					primaryStage.setScene(hawksSelect);
				}
			});
		}
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}
