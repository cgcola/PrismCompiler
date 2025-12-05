package com.prismx.controller;

import com.prismx.model.lexicalAction;
import com.prismx.model.semanticsAction;
import com.prismx.model.syntaxAction;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private File selectedFile;

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

    private boolean loadIsSuccessful = false;
    private boolean lexicalIsSuccessful = false;
    private boolean syntaxIsSuccessful = false;

    private HashMap<Integer, String> processedLexical;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resetStatuses();
        disableButtons();
        sourceCodeArea.setEditable(false);
        resultArea.setEditable(false);
    }

    @FXML
    public void btnOpenFileAction(){
        Stage stage = (Stage) sourceCodeArea.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Text File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            resetStatuses();
            try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
                StringBuilder content = new StringBuilder();
                String line;
                for(int i=1;(line=br.readLine())!=null;i++){
                    content.append(i+"   "+line).append("\n");
                }
                if(content.toString().isEmpty()||content.toString().isBlank()){
                    sourceCodeArea.setText("");
                    resultArea.setText("UnexpectedEmptyStringError: The text file does not contain string(s).");
                }
                else {
                    sourceCodeArea.setText(content.toString());
                    resultArea.clear();
                    resetStatuses();
                    statusLoaded.setFill(Color.GREEN);
                    loadIsSuccessful = true;
                    enableButtons();
                    resultArea.setText("File \"" + selectedFile.getName() + "\" successfully loaded.");
                }

            } catch (IOException e) {
                showErrorAlert("File Load Error", "Could not load file: " + e.getMessage());
                statusLoaded.setFill(Color.RED);
            }
        }
    }

    @FXML
    public void btnLexicalAction() throws IOException {
        lexicalAction la=new lexicalAction(selectedFile);
        la.lexicalAnalysis();
        if(!la.lexicalSuccessStatus()){
            statusLoaded.setFill(Color.RED);
            resultArea.setText(">>> ERROR: Lexical Analysis attempt failed.");
        }
        else {
            lexicalIsSuccessful = true;
            processedLexical=la.getLexicalDict();
            enableButtons();
            statusLexical.setFill(Color.GREEN);
            resultArea.setText(">>> Lexical Analysis attempt successful.");
        }
    }

    @FXML
    public void btnSyntaxAction(){
        syntaxAction syn=new syntaxAction(processedLexical);
    }

    @FXML
    public void btnSemanticAction(){

    }

    @FXML
    public void btnClearAction() {
        sourceCodeArea.clear();
        resultArea.clear();
        resetStatuses();
    }

    public void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void enableButtons(){
        if(loadIsSuccessful){
            btnLexical.setDisable(false);
        }
        if(lexicalIsSuccessful){
            btnSyntax.setDisable(false);
        }
        if(syntaxIsSuccessful){
            btnSemantic.setDisable(false);
        }

    }

    public void disableButtons(){
        if(!loadIsSuccessful){
            btnLexical.setDisable(true);
        }
        if(!lexicalIsSuccessful){
            btnSyntax.setDisable(true);
        }
        if(!syntaxIsSuccessful){
            btnSemantic.setDisable(true);
        }


    }
    public void resetStatuses() {
        statusLoaded.setFill(Color.GREY);
        statusLexical.setFill(Color.GREY);
        statusSyntax.setFill(Color.GREY);
        statusSemantic.setFill(Color.GREY);
        loadIsSuccessful=false;
        lexicalIsSuccessful=false;
        syntaxIsSuccessful=false;
        disableButtons();

    }




}