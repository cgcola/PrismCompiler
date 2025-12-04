package com.prismx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Controller {
    @FXML private TextArea sourceCodeArea;
    @FXML private TextArea resultArea;
    @FXML private Button btnOpenFile;
    @FXML private Button btnLexical;
    @FXML private Button btnSyntax;
    @FXML private Button btnSemantic;
    @FXML private Button btnClear;
    @FXML private Circle statusLoaded;
    @FXML private Circle statusLexical;
    @FXML private Circle statusSyntax;
    @FXML private Circle statusSemantic;

    @FXML
    private void initialize() {
        resetStatuses();
    }

    @FXML
    private void btnOpenFileAction(){
        Stage stage = (Stage) sourceCodeArea.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Text File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    content.append(line).append("\n");
                }
                sourceCodeArea.setText(content.toString());
                resultArea.clear();
                resetStatuses();
                statusLoaded.setFill(Color.GREEN);
            } catch (IOException e) {
                showErrorAlert("File Load Error", "Could not load file: " + e.getMessage());
                statusLoaded.setFill(Color.RED);
            }
        }
    }

    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void resetStatuses() {
        statusLoaded.setFill(Color.GREY);
        statusLexical.setFill(Color.GREY);
        statusSyntax.setFill(Color.GREY);
        statusSemantic.setFill(Color.GREY);
    }


}