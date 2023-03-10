package UI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class ApplicationUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a WebView component
        WebView webView = new WebView();

        // Load an HTML file
        webView.getEngine().load("file:///path/to/myfile.html");

        // Add the WebView to the scene
        Scene scene = new Scene(webView, 800, 600);

        // Set the stage title and show the scene
        primaryStage.setTitle("My UI");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

