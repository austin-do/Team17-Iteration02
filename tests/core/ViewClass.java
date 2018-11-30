package core;


import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionModel;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.FlowPane;

@SuppressWarnings("restriction")
public class ViewClass {
	private Pane view;
	private RummikubController controller;
	private RummikubModel model;
	
	public ViewClass() {
		model = new RummikubModel();
		controller = new RummikubController(model);
	}
	
	public void buildAndShowGui(final Stage stage) {
		Button button1 = new Button("Play");
		Button button2 = new Button("Options");
		
		button1.setOnAction(e-> handleStartButton(stage));
		if(model.getPlayers() == null) {
			button2.setOnAction(e-> promptNumPlayers(stage));
		}
		else {
			button2.setOnAction(e -> handleOptionsButtonAction(stage));
		}
		VBox box = new VBox(button1, button2);
		Scene scene = new Scene(box, 1000, 1000);
		
		box.setSpacing(10);
		
		stage.setScene(scene);
		stage.setTitle("Rummikub");
		stage.show();
	}
	

	private void handleOptionsButtonAction(Stage stage) {
		
		Pane root = new Pane();
		HBox box1 = new HBox(10);
		
		ComboBox<Integer> numPlayer = new ComboBox<Integer>();
		numPlayer.getItems().addAll(2, 3, 4);
		numPlayer.setPromptText("Select Number of players:");
		numPlayer.setLayoutX(150);
		
		System.out.println(numPlayer.getEditor().getText());
		
		Button button1 = new Button("Select");
		
		Button timer = new Button("Timer");
		
		box1.getChildren().add(numPlayer);
		box1.getChildren().add(button1);
		box1.getChildren().add(timer);
		
		VBox box2 = new VBox(10);
		box2.setLayoutY(50);
		
		for (int x = 0; x < model.getPlayers().size(); x++) {
			Label label;
			ArrayList<Player> players = model.getPlayers();
			Player player = players.get(x);
			int position = x;
			
			if (!player.isBot()) {
				label = new Label("Player " + (x+1));
								
		        ComboBox<String> playerType = new ComboBox<String>();
		        playerType.getSelectionModel().selectFirst();
		        playerType.setPromptText("Type");
		        playerType.getItems().addAll("Human", "AI");
		        playerType.setLayoutX(150);

		        
		        playerType.setOnAction(e-> {
		        	if(playerType.getValue() == "AI") {
		 
		        	controller.updatePlayerTypeBot(players, position);
		        	}
					handleOptionsButtonAction(stage);});
		        
		        box2.getChildren().add(label);
		        box2.getChildren().add(playerType);

			}
			if(player.isBot()) {
				label = new Label("Player " + (x+1) + " (BOT)");
				
				ComboBox<String> stratChoice = new ComboBox<String>();
		        stratChoice.getSelectionModel().selectFirst();
		        if(((AI) player).stratNum == 1) {
		        	stratChoice.setPromptText("Strategy 1");
		        }
		        if(((AI) player).stratNum == 2) {
		        	stratChoice.setPromptText("Strategy 2");
		        }
		        if(((AI) player).stratNum == 3) {
		        	stratChoice.setPromptText("Strategy 3");
		        }
		        if(((AI) player).stratNum == 4) {
		        	stratChoice.setPromptText("Strategy 4");
		        }
		      
		        stratChoice.getItems().addAll("Strategy 1", "Strategy 2", "Strategy 3", "Strategy 4");
		        stratChoice.setLayoutX(150);
		        
		        ComboBox<String> playerType = new ComboBox<String>();
		        playerType.getSelectionModel().selectFirst();
		        playerType.setPromptText("Type");
		        playerType.getItems().addAll("Human", "AI");
		        playerType.setLayoutX(150);
		        
		        playerType.setOnAction(e-> {
		        	if(playerType.getValue() == "Human") {
		        		controller.updatePlayerTypeHuman(players, position);
		        	}
					handleOptionsButtonAction(stage);});
		        
		        stratChoice.setOnAction(e-> {
		        	controller.updatePlayerStrat(players, position, stratChoice.getValue());	
				});
		        
		        box2.getChildren().add(label);
		        box2.getChildren().add(playerType);
		        box2.getChildren().add(stratChoice);
			}
			
		}
		Button confirmButton = new Button("Back");
		box2.getChildren().add(confirmButton);
		
		root.getChildren().addAll(box1);
		root.getChildren().addAll(box2);
		
		
        Scene scene = new Scene(root, 1000, 1000);
		stage.setScene(scene);
		stage.setTitle("Rummikub");
		stage.show();
<<<<<<< HEAD
		
		
		int numBots;
		if(numPlayer.getValue() == "1") {
			numBots = 1;
		}else if(numPlayer.getValue() == "2") {
			numBots = 2;
		}else{
			System.out.println(numPlayer.getValue());
			numBots = 3;
		}
		
=======
>>>>>>> 4a47ac3fea66b6b9f1b4fb886cce46511b813030

		button1.setOnAction(e-> {
			controller.updatePlayers((int) (numPlayer.getValue()));
			handleOptionsButtonAction(stage);});
<<<<<<< HEAD
		//playerType.setOnAction(e-> handlePlayerTypeAction(e));

		button1.setOnAction(e-> handleOptionsButtonAction(stage, numBots));


=======
		confirmButton.setOnAction(e -> buildAndShowGui(stage));

>>>>>>> 4a47ac3fea66b6b9f1b4fb886cce46511b813030
	}
	

	private void promptNumPlayers(Stage stage) {
		HBox box = new HBox(10);
		
		ComboBox<Integer> numPlayers = new ComboBox<Integer>();
		numPlayers.getSelectionModel().selectFirst();
		numPlayers.getItems().addAll(2, 3, 4);
		numPlayers.setPromptText("Select Number of players:");
		numPlayers.setLayoutX(150);
		
		Button button1 = new Button("Select");
		
		box.getChildren().add(numPlayers);
		box.getChildren().add(button1);
		
        Scene scene = new Scene(box, 1000, 1000);
		stage.setScene(scene);
		stage.setTitle("Rummikub");
		stage.show();
		
		
		button1.setOnAction(e-> {
			controller.updatePlayers((int) (numPlayers.getValue()));
			handleOptionsButtonAction(stage);});
	}


	private void handleStartButton(Stage stage) {
		//GameView(stage);
	}

<<<<<<< HEAD

	private void GameView(Stage stage) {
		// TODO Auto-generated method stub
		
		Pane gameBoard = new Pane();
		gameBoard.setStyle("-fx-background-color: green");
		Scene board = new Scene(gameBoard);
		stage.setScene(board);
		stage.show();

		for(int i = 0; i<this.model.getTable().getMelds().size(); i++) {
			for(int j=0; j<this.model.getTable().getMelds().get(i).getTiles().size(); j++) {
				
				try {
					displayTile(this.model.getTable().getMelds().get(i).getTiles().get(j).toString());
				}catch(FileNotFoundException e) {
					e.printStackTrace();
				}
				
			}
			
		}
		
	}
=======
//	private void GameView(Stage stage) {
//		// TODO Auto-generated method stub
//
//		for(int i = 0; i<this.model.getTable().getMelds(); i++) {
//			for(int j=0; j<this.model.getTable().getMelds().get(i); j++) {
//				
//				try {
//					displayTile(this.model.getTable().getMelds()get(i)get(j).toString());
//				}catch(FileNotFoundException e) {
//					e.printStackTrace();
//				}
//				
//			}
//			
//		}
//		
//	}
>>>>>>> 4a47ac3fea66b6b9f1b4fb886cce46511b813030
	
	private ImageView displayTile(String tile) {
		
		ImageView image = new ImageView(new Image("/Tiles/" + tile.toLowerCase() + ".jpg"));
		image.setFitHeight(50);
		image.setFitWidth(50);
		image.setPreserveRatio(true);
		
		return image;
	}

	

}
