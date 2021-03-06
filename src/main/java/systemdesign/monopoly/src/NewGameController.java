package systemdesign.monopoly.src;

import systemdesign.monopoly.src.data.ConfigHandler;
import systemdesign.monopoly.src.data.FileManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Screen;
import javafx.stage.Stage;
import systemdesign.monopoly.src.model.BoardConfiguration;
import systemdesign.monopoly.src.model.GamePace;
import systemdesign.monopoly.src.model.player.AICharacteristic;
import systemdesign.monopoly.src.model.session.GameSession;
import systemdesign.monopoly.src.model.session.GameSessionManager;
import systemdesign.monopoly.src.model.tiles.property.Color;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class NewGameController implements Initializable {
    @FXML
    private final int MAX_NUM_PLAYERS = 6;
    @FXML
    private final int MIN_NUM_PLAYERS = 2;
    @FXML
    private Spinner<Integer> botSpinner;
    @FXML
    private Spinner<Integer> humanSpinner;

    @FXML
    private int humanPlayers = 2;
    @FXML
    private int botPlayers = 0;
    @FXML
    private int maxHumanPlayers = 6;
    @FXML
    private int maxBotPlayers = 5;
    @FXML
    private int minBotPlayers = 0;
    @FXML
    private int minHumanPlayers = 2;

    private int numOfPlayers;
    private ConfigHandler configHandler = new ConfigHandler();
    String configFileName = "board_template.json";
    ArrayList<String> boardNames = new ArrayList<>();
    private String selectedAIChar = "BALANCED";
    private String selectedPace = "MEDIUM";

    //change String into Board when Board Model class is implemented
    @FXML
    private ComboBox<String> comboBoard;
    private ObservableList<String> comboBoardData = FXCollections.observableArrayList();

    @FXML
    private ComboBox<String> comboPace;
    private ObservableList<String> comboPaceData = FXCollections.observableArrayList();

    //change String into AICharacteristic object when it is implemented
    @FXML
    private ComboBox<String> comboAI;
    private ObservableList<String> comboAIData = FXCollections.observableArrayList();

    SpinnerValueFactory.IntegerSpinnerValueFactory spinnerValueFactoryHuman = new SpinnerValueFactory.IntegerSpinnerValueFactory(minHumanPlayers, maxHumanPlayers);
    SpinnerValueFactory.IntegerSpinnerValueFactory spinnerValueFactoryBot= new SpinnerValueFactory.IntegerSpinnerValueFactory(minBotPlayers, maxBotPlayers);

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {


        //spinner management, makes sure the player number is at most 6
        humanSpinner.setValueFactory(spinnerValueFactoryHuman);
        botSpinner.setValueFactory(spinnerValueFactoryBot);

        //spinner listeners
        humanSpinner.valueProperty().addListener((obs, oldValue, newValue) -> {
            humanPlayers = newValue;
            if(humanPlayers+botPlayers>=2){
                minBotPlayers =0;
                minHumanPlayers = 1;
            }
            maxBotPlayers = MAX_NUM_PLAYERS - humanPlayers;
            spinnerValueFactoryBot.setMax(maxBotPlayers);
            spinnerValueFactoryBot.setMin(minBotPlayers);
            spinnerValueFactoryHuman.setMin(minHumanPlayers);
            });

        botSpinner.valueProperty().addListener((obs, oldValue, newValue) -> {
            botPlayers = newValue;
            if(botPlayers+humanPlayers>=2){
                minBotPlayers =0;
                minHumanPlayers = 1;
            }
            maxHumanPlayers = MAX_NUM_PLAYERS - botPlayers;
            spinnerValueFactoryHuman.setMax(maxHumanPlayers);
            spinnerValueFactoryBot.setMin(minBotPlayers);
            spinnerValueFactoryHuman.setMin(minHumanPlayers);});

        //combobox for board initialization
        //fill this from stored board templates
        for(String board: FileManager.getBoardConfigNames()){
            JSONObject config = configHandler.getConfig(board);
            JSONObject boardConfig = (JSONObject) config.get("boardConfig");
            comboBoardData.add((String) boardConfig.get("boardName"));
            boardNames.add((String) boardConfig.get("boardName"));
        }

        //set the data
        comboBoard.setItems(comboBoardData);
        comboBoard.setValue("Classic");

        //combobox for pace initialization with string representations
        GamePace paces[] = GamePace.values();
        for(GamePace pace: paces) {
            comboPaceData.add(String.valueOf(pace));
        }
        //set the data
        comboPace.setItems(comboPaceData);
        comboPace.setValue("MEDIUM");

        //combobox for ai characteristic initialization
        AICharacteristic characteristics[] = AICharacteristic.values();
        for(AICharacteristic charac: characteristics) {
            comboAIData.add(String.valueOf(charac));
        }

        //set the data
        comboAI.setItems(comboAIData);
        comboAI.setValue("BALANCED");

    }

    @FXML
    public void goToMainMenu( ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("mainmenu.fxml"));

        Scene tableViewScene = new Scene(tableViewParent);

        // Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        tableViewParent.setId("pane");
//        GameBoardController controller = fxmlLoader.<GameBoardController>getController();
        window.setScene(tableViewScene);

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        window.setX((screenBounds.getWidth() - window.getWidth()) / 2);
        window.setY((screenBounds.getHeight() - window.getHeight()) / 2);

        window.show();
    }

    @FXML
    //strings will be changed to Board objects
    private void handleComboBoardAction() {
        String selectedBoard = comboBoard.getSelectionModel().getSelectedItem();
        boolean found = false;
        int index = 0;
        ArrayList<String> configFiles = FileManager.getBoardConfigNames();

        while(!found){
            if(selectedBoard.equals(boardNames.get(index))){
                found = true;
                configFileName = configFiles.get( index);
            }
            index++;
        }
    }

    @FXML
    private void handleComboPaceAction() {
        selectedPace = comboPace.getSelectionModel().getSelectedItem();
    }

    @FXML
    //Strings will be changed to AIChar objects
    private void handleComboAIAction() {
        selectedAIChar = comboAI.getSelectionModel().getSelectedItem();
    }

    @FXML
    //where we give/save these settings to boardconfig object (model)
    private void handleStartGame(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GameBoard.fxml"));
        Parent root = (Parent)fxmlLoader.load();

        GameBoardController controller = fxmlLoader.<GameBoardController>getController();

        BoardConfiguration boardConfiguration = new BoardConfiguration();
        boardConfiguration.setGamePace( GamePace.valueOf(selectedPace));
        boardConfiguration.setAiCharacteristic(AICharacteristic.valueOf(selectedAIChar));
        boardConfiguration.setHumanPlayerCount( humanPlayers);
        numOfPlayers = humanPlayers + botPlayers;
        boardConfiguration.setMaxPlayerCount( numOfPlayers);

        GameSessionManager sessionManager = new GameSessionManager();
        sessionManager.setConfigFileName(configFileName);
        sessionManager.newGame(boardConfiguration);
        GameSession session = sessionManager.getGame();
        controller.setGameSession(session);
        controller.init();

        Scene scene = new Scene(root);
        // Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        window.setX((screenBounds.getWidth() - window.getWidth()) / 2);
        window.setY((screenBounds.getHeight() - window.getHeight()) / 2);
        window.show();
    }
}