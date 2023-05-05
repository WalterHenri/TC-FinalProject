package com.trabalhodetc.lucas_marley_walter;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.swing.JFileChooser;
import java.awt.Desktop;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;

public class OpenWindowController {
    

    @FXML
    private Button buttonOpenJflap;

    @FXML
    private Label labelOpen;


    private Vector<Circle> circulos = new Vector<Circle>();

    private Vector<Line> setas = new Vector<Line>();
    private Vector<Polygon> arrows = new Vector<Polygon>();
    private Vector<Label> ids = new Vector<Label>();
    private Vector<Label> praOndeVai = new Vector<Label>();

    private Polygon inicial = new Polygon(0.0, 0.0, 30.0, -30.0, 30.0, 30.0);
    private Vector<Circle> finais = new Vector<Circle>();

    private com.trabalhodetc.lucas_marley_walter.Automato automato;

    private String path;


    @FXML
    public void OpenJflap() {
        File selected = new File(path);
        try{
            Desktop.getDesktop().open(selected);
        }catch (IOException e){
            System.out.println("voce deve colocar jflap como aplicativo padrao para abir jff");
        }
    }

    public void abrir(BorderPane borderPane){

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(fileChooser);
        path = fileChooser.getSelectedFile().getAbsolutePath();
        System.out.println(path);

        

        automato = Automato.loadFromJff(path);
        
        WAController.pathOpen = path;

        AnchorPane workArea;

        
        int radius = 30;
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        dropShadow.setOffsetX(3.0);
        dropShadow.setOffsetY(3.0);
        dropShadow.setColor(Color.BLACK);
       

        try {
            workArea = FXMLLoader.load(getClass().getResource("WAOpenInterface.fxml"));
            borderPane.setCenter(workArea);

            for (int i = 0; i < automato.getNumeroDeEstados(); i++) {

                double x = automato.getEstado(i).getPosition().x * 3;
                double y = automato.getEstado(i).getPosition().y;
                Circle c = new Circle(x, y, radius);
                c.setId(automato.getEstado(i).getNome());  
                c.setStroke(Color.BLACK);
                c.setStrokeWidth(3);
                c.setFill(Color.GOLDENROD);

                circulos.add(c);

                if(automato.getEstado(i).IsFinal()){
                    Circle cFinal = new Circle(x,y,radius - radius/5);
                    cFinal.setFill(Color.TRANSPARENT);
                    cFinal.setStroke(Color.BLACK);
                    cFinal.setStrokeWidth(3);
                    finais.add(cFinal);
                }
                Label id = new Label(automato.getEstado(i).getNome());
                id.setFont(new Font(radius/1.5));

                id.setTranslateX(x - radius/2);
                id.setTranslateY(y - radius/2);
                
                id.setTextFill(Color.BLACK);
            
                ids.add(id);

            }
            int inicio = automato.getInicial();
            inicial.setFill(Color.BLACK);
            inicial.setStroke(Color.BLACK);
            inicial.setTranslateX(circulos.get(inicio).getCenterX() - radius - 30);
            inicial.setTranslateY(circulos.get(inicio).getCenterY());
            inicial.setRotate(180);

            for (int i = 0; i < automato.getNumeroDeEstados(); i++) {
                for (String a : automato.getAlfabetoAsArray()) {
                    List<Integer> transas = automato.getEstado(i).getListaDeTransicao(a);
                    if(transas == null){
                        continue;
                    }
                    for (Integer j : transas) {
                        if(j == null){
                            continue;
                        }
                        double sX = circulos.get(i).getCenterX();
                        double sY = circulos.get(i).getCenterY();
                        double eX = circulos.get(j).getCenterX();
                        double eY = circulos.get(j).getCenterY();

                        Line seta = new Line(sX,sY,eX,eY);
                        
                        seta.setFill(Color.BLACK);
                        seta.setStroke(Color.BLACK);
                        seta.setStrokeWidth(radius/7);
                        Polygon arrow = new Polygon();
                        arrow.getPoints().addAll(0.0, 0.0, 20.0, 10.0, 0.0, 20.0);
                        arrow.setFill(Color.BLACK);
                        arrow.setStroke(Color.BLACK);
                        arrow.setEffect(dropShadow);
                        
                        // Position the arrow at the end of the line
                        double degrees = Math.toDegrees(Math.atan2(eY - sY, eX - sX));
                        
                        arrow.setTranslateX(eX - 12.5 - Math.cos(Math.toRadians(degrees))* radius);
                        arrow.setTranslateY(eY - 12.5 - Math.sin(Math.toRadians(degrees))* radius);

                        arrow.setRotate(degrees);
                        

                        Label simbol = new Label(a);
                        simbol.setTranslateX((sX - eX)/2 + eX);
                        simbol.setTranslateY((sY - eY)/2 + eY);
                        simbol.setFont(new Font(radius));
                        simbol.setTextFill(Color.BLACK);

                        praOndeVai.add(simbol);
                        arrows.add(arrow);
                        setas.add(seta);

                        
                    }

                }

            }
            
            workArea.getChildren().addAll(setas);
            workArea.getChildren().addAll(circulos);
            workArea.getChildren().addAll(arrows); 
            workArea.getChildren().addAll(ids);
            workArea.getChildren().addAll(praOndeVai);  
            workArea.getChildren().add(inicial);
            workArea.getChildren().addAll(finais);

        } catch (IOException e) {
            System.out.println("aff que vida dificil");
        }
    }
}
