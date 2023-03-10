package UI;

import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class ApplicationUI {

    public static void launchUI() {
        // Create a WebView component
        WebView webView = new WebView();

        // Load an HTML file
        webView.getEngine().load("/home/tone/IdeaProjects/practice-enterprise/src/UI/webUI.html");

        // Add the WebView to the scene
        Scene scene = new Scene(webView, 800, 600);

        // Set the stage title and show the scene
        Stage primaryStage = new Stage();
        primaryStage.setTitle("My UI");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}

