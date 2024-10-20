package org.example.javafxdb_sql_shellcode;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.animation.FadeTransition;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class Main extends Application {

    private Stage primaryStage;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setResizable(false);
        showSplashScreen();
    }

    // Show splash screen and transition to main UI
    private void showSplashScreen() {
        try {
            // Load splash screen FXML
            Parent splashRoot = FXMLLoader.load(getClass().getResource("/org/example/javafxdb_sql_shellcode/splash_screen.fxml"));
            Scene splashScene = new Scene(splashRoot, 850, 560);
            // Load CSS for styling
            splashScene.getStylesheets().add(getClass().getResource("/org/example/javafxdb_sql_shellcode/style.css").toExternalForm());
            primaryStage.setScene(splashScene);
            primaryStage.show();

            // Create fade-out transition for splash screen
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), splashRoot);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setOnFinished(event -> showMainUI());  // After fade, show main UI
            fadeOut.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Load and show the main UI (after splash screen)
    private void showMainUI() {
        try {
            // Load the main UI FXML (db_interface_gui.fxml)
            Parent mainRoot = FXMLLoader.load(getClass().getResource("/org/example/javafxdb_sql_shellcode/db_interface_gui.fxml"));
            Scene mainScene = new Scene(mainRoot, 850, 560);
            // Load CSS for styling
            mainScene.getStylesheets().add(getClass().getResource("/org/example/javafxdb_sql_shellcode/style.css").toExternalForm());
            primaryStage.setScene(mainScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Example method to close the application if needed (for buttons in FXML)
    @FXML
    private void closeApplication(ActionEvent event) {
        primaryStage.close();
    }
}
