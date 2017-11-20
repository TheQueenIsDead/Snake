package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

import static javafx.scene.paint.Color.*;

public class Main extends Application {

    Canvas canvas = new Canvas();
    GridPane mainPane = new GridPane();

    String direction = "RIGHT";
    Point2D head = new Point2D(10, 10);
    double increment = 10;

    @Override
    public void start(Stage primaryStage) throws Exception{

        mainPane.getChildren().add(canvas);
        primaryStage.setTitle("Snek");
        primaryStage.setScene(new Scene(mainPane, 300, 275));
        primaryStage.show();
        initiateKeyListeners();
        initiateCanvas();
        startGameLoop();
    }

    private void startGameLoop() {
        TimerTask gameLoop = new TimerTask() {
            @Override
            public void run() {
                double X = head.getX();
                double Y = head.getY();

                switch (direction){
                    case "LEFT":
                        head = new Point2D(X-increment, Y);
                        break;
                    case "RIGHT":
                        head = new Point2D(X+increment, Y);
                        break;
                    case "UP":
                        head = new Point2D(X, Y-increment);
                        break;
                    case "DOWN":
                        head = new Point2D(X, Y+increment);
                        break;
                }

                X = head.getX();
                Y = head.getY();

                canvas.getGraphicsContext2D().setFill(WHITE);
                canvas.getGraphicsContext2D().fillRect(0,0, canvas.getWidth(), canvas.getHeight());
                canvas.getGraphicsContext2D().setFill(BLACK);
                canvas.getGraphicsContext2D().fillRect(X, Y,10, 10);

            }
        };
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(gameLoop, 0, 500);
    }

    private void initiateCanvas() {
        canvas.widthProperty().bind(mainPane.widthProperty());
        canvas.heightProperty().bind(mainPane.heightProperty());
        canvas.getGraphicsContext2D().setFill(BLACK);
        canvas.getGraphicsContext2D().fillRect(20, 20,10, 10);
    }

    private void initiateKeyListeners() {
        canvas.setFocusTraversable(true);
        canvas.setOnKeyPressed(event -> keyPressed(event));
    }

    private void keyPressed(KeyEvent event) {
        switch (event.getCode().toString()){
            case "LEFT":
                direction = "LEFT";
                break;
            case "RIGHT":
                direction = "RIGHT";
                break;
            case "UP":
                direction = "UP";
                break;
            case "DOWN":
                direction = "DOWN";
                break;
        }
        System.out.println(direction);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
