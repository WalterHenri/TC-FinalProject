package com.trabalhodetc.lucas_marley_walter;

import javax.swing.JFileChooser;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


public class WAController {

    Automato automato;
    Automato automato2;
    static public String pathOpen;

    String path;

    @FXML
    private Label label01Unity;

    @FXML
    private Label label02Unity;

    @FXML
    private Button saveUnity;

    @FXML
    private Button select01Unity;

    @FXML
    private Button select02Unity;

    @FXML
    private Button afnButton;

    @FXML
    private Button afdButton;

    public int optionUnitySelected = 1; //inicializa afn pre selecionado

    @FXML
    public void afnSelectButton() {
        optionUnitySelected = 1;
        changeColors();
    }

    @FXML
    public void afdSelectButton() {
        optionUnitySelected = 2;
        changeColors();
    }
    private void changeColors(){
        if(optionUnitySelected == 1){
            afdButton.setStyle("-fx-background-color: #272727;");
            afnButton.setStyle("-fx-background-color: #717171;");
        }else if(optionUnitySelected == 2){
            afnButton.setStyle("-fx-background-color: #272727;");
            afdButton.setStyle("-fx-background-color: #717171;");
        }
    }

    com.trabalhodetc.uniao_afds_afns.Automato at1 = new com.trabalhodetc.uniao_afds_afns.Automato();
    com.trabalhodetc.uniao_afds_afns.Automato at2 = new com.trabalhodetc.uniao_afds_afns.Automato();

    @FXML
    public void select01Unity() {
        JFileChooser fileChooser = new JFileChooser();
        path = getPath(fileChooser);
        at1.carregaDados(path);
        label01Unity.setText(path);
    }

    @FXML
    public void select02Unity() {
        JFileChooser fileChooser = new JFileChooser();
        path = getPath(fileChooser);
        at2.carregaDados(path);
        label02Unity.setText(path);
    }

    @FXML
    public void saveUnity() {
        com.trabalhodetc.uniao_afds_afns.Automato result = new com.trabalhodetc.uniao_afds_afns.Automato();
        if(optionUnitySelected == 1){
            result = result.uniaoAFD(at1, at2);
        }else{
            result = result.uniaoAFD(at1, at2);
        }
        try {
            com.trabalhodetc.uniao_afds_afns.AutomatoWriter.escreveAutomato(result, path);
        } catch (Exception e) {
            System.out.println("culpa de jonathan");
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("JFlap volume 2");
        alert.setContentText("Your automaton has already been saved.");
        alert.setHeaderText("Completed union!");
        alert.setGraphic(new ImageView(this.getClass().getResource("../images/logoIcon.png").toString()));
        alert.showAndWait();
    }



    @FXML
    private Label label01Intersection;

    @FXML
    private Label label02Intersection;

    @FXML
    private Button saveIntersection;

    @FXML
    private Button select01Intersection;

    @FXML
    private Button select02Intersection;

    @FXML
    void saveIntersection() {
        
        Automato.saveInJff(path, Intersecionador.Intersecionar(automato, automato2));

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("JFlap volume 2");
        alert.setContentText("Your automaton has already been saved.");
        alert.setHeaderText("Completed intersection!");
        alert.setGraphic(new ImageView(this.getClass().getResource("../images/logoIcon.png").toString()));
        alert.showAndWait();
    }
    String path01;
    @FXML
    public void select01Intersection() {
        JFileChooser fileChooser = new JFileChooser();
        path = getPath(fileChooser);
        automato = Automato.loadFromJff(path);
        label01Intersection.setText(path);
    }

    @FXML
    public void select02Intersection() {
        JFileChooser fileChooser = new JFileChooser();
        path = getPath(fileChooser);
        automato2 = Automato.loadFromJff(path);
        label02Intersection.setText(path);
    }

    @FXML
    private Label label01Concatenation;

    @FXML
    private Label label02Concatenation;

    @FXML
    private Button saveConcatenation;

    @FXML
    private Button select01Concatenation;

    @FXML
    private Button select02Concatenation;


    com.trabalhodetc.AutomatoG3.Automato auto1 = new com.trabalhodetc.AutomatoG3.Automato();
    com.trabalhodetc.AutomatoG3.Automato auto2 = new com.trabalhodetc.AutomatoG3.Automato(); 

    @FXML
    public void saveConcatenation() {
        
        com.trabalhodetc.AutomatoG3.DocumentoXML doc = new com.trabalhodetc.AutomatoG3.DocumentoXML(); //objeto doc criado da classe "DocumentoXML"
        
        doc.concatenacao(auto1, auto2,path); 

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("JFlap volume 2");
        alert.setContentText("Your automaton has already been saved.");
        alert.setHeaderText("Completed concatenation!");
        alert.setGraphic(new ImageView(this.getClass().getResource("../images/logoIcon.png").toString()));
        alert.showAndWait();
    }

    @FXML
    public void select01Concatenation() {
        
        JFileChooser fileChooser = new JFileChooser();
        path = getSavePath(fileChooser); 
        auto1.setLocalArquivo(path);
        label01Concatenation.setText(path);
    }

    @FXML
    public void select02Concatenation() {
        
        JFileChooser fileChooser = new JFileChooser();
        path = getSavePath(fileChooser); 
        auto2.setLocalArquivo(path);
        label02Concatenation.setText(path);
    }





    @FXML
    private Label label01Complement;

    @FXML
    private Label label02Complement;

    @FXML
    private Button saveComplement;

    @FXML
    private Button select01Complement;

    @FXML
    private Button select02Complement;

    @FXML
    void saveComplement(MouseEvent event) {
        System.out.println("bateu certo");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("JFlap volume 2");
        alert.setContentText("Your automaton has already been saved.");
        alert.setHeaderText("Completed complement!");
        alert.setGraphic(new ImageView(this.getClass().getResource("../images/logoIcon.png").toString()));
        alert.showAndWait();
    }

    @FXML
    void select01Complement(MouseEvent event) {
        System.out.println("bateu certo");
    }

    @FXML
    void select02Complement(MouseEvent event) {
        System.out.println("bateu certo");
    }





    @FXML
    private Label labelConvert;

    @FXML
    private Button saveConvert;

    @FXML
    private Button selectConvert;

    @FXML
    public void saveConvert() {

        Automato afd = Conversor.converter(automato);
        Automato.saveInJff(path, afd);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("JFlap volume 2");
        alert.setContentText("Your automaton has already been saved.");
        alert.setHeaderText("Completed conversion!");
        alert.setGraphic(new ImageView(this.getClass().getResource("../images/logoIcon.png").toString()));
        alert.showAndWait();
        
    }

    @FXML
    public void selectConvert() {
        JFileChooser fileChooser = new JFileChooser();
        path = getSavePath(fileChooser);
        automato = Automato.loadFromJff(path);
        labelConvert.setText(path);
    }




    @FXML
    private Label labelMinimization;

    @FXML
    private Button saveMinimization;

    @FXML
    private Button selectMinimization;

    @FXML
    public void saveMinimization() { 
        Automato.saveInJff(path, automato);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("JFlap volume 2");
        alert.setContentText("Your automaton has already been saved.");
        alert.setHeaderText("Completed minimization!");
        alert.setGraphic(new ImageView(this.getClass().getResource("../images/logoIcon.png").toString()));
        alert.showAndWait();
    }

    @FXML
    public void selectMinimization() {
        JFileChooser fileChooser = new JFileChooser();
        path = getPath(fileChooser);
        automato = Automato.loadFromJff(path);
        //Minimizador.minimizar(automato);
        
        labelMinimization.setText(path);
    }


    static String getPath(JFileChooser fileChooser) {
        if (fileChooser.showOpenDialog(fileChooser) != JFileChooser.APPROVE_OPTION) {
            return null;
        }
        return fileChooser.getSelectedFile().getAbsolutePath();
    }

    static String getSavePath(JFileChooser fileChooser) {
        if (fileChooser.showSaveDialog(fileChooser) != JFileChooser.APPROVE_OPTION) {
            return null;
        }
        return fileChooser.getSelectedFile().getAbsolutePath();
    }
}
