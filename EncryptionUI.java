import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EncryptionUI extends Application {

    @Override
    public void start(Stage stage) {

        Label title = new Label("AES Encryptor");

        Label inputLabel = new Label("Enter text");

        TextArea inputText = new TextArea();
        inputText.setPromptText("Type something...");
        inputText.setPrefHeight(150);

        Button encryptButton = new Button("🔒 Encrypt");

        encryptButton.setOnAction(event -> {
            String text = inputText.getText();

            if (text.trim().isEmpty()) {
                System.out.println("Please enter some text.");
                return;
            }

            try {
                String encryptedText = AESEncryption.encrypt(text);

                System.out.println("Encrypted text: " + encryptedText);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        VBox layout = new VBox(
                10,
                title,
                inputLabel,
                inputText,
                encryptButton
        );

        Scene scene = new Scene(layout, 500, 400);

        stage.setTitle("AES Encryptor");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}