import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public class EncryptionUI extends Application {

    @Override
    public void start(Stage stage) {

        Label title = new Label("AES Encryptor");

        Label inputLabel = new Label("Enter text");

        TextArea inputText = new TextArea();
        inputText.setPromptText("Type something...");
        inputText.setPrefHeight(150);

        Label characterCount = new Label("Characters: 0");

        inputText.textProperty().addListener((observable, oldText, newText) -> {
        characterCount.setText("Characters: " + newText.length());
        });

        Label outputLabel = new Label("Encrypted text");

        TextArea outputText = new TextArea();
        Button copyButton = new Button("Copy encrypted text");

        copyButton.setOnAction(event -> {
        String encrypted = outputText.getText();

        if (!encrypted.isEmpty()) {
        ClipboardContent content = new ClipboardContent();
        content.putString(encrypted);

        Clipboard.getSystemClipboard().setContent(content);
        copyButton.setText("Copied!");
        }
        });
        outputText.setPromptText("Your encrypted text will appear here...");
        outputText.setEditable(false);
        outputText.setPrefHeight(100);

        Button encryptButton = new Button("🔒 Encrypt");

        encryptButton.setOnAction(event -> {
            String text = inputText.getText();

            if (text.trim().isEmpty()) {
                System.out.println("Please enter some text.");
                return;
            }

            try {
                String encryptedText = AESEncryption.encrypt(text);

                outputText.setText(encryptedText);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        VBox layout = new VBox(
                10,
                title,
                inputLabel,
                inputText,
                encryptButton,
                characterCount,
                outputLabel,
                outputText,
                copyButton     
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