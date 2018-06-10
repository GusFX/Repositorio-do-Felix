package meupaint;

import javafx.application.Application;
import javafx.scene.input.MouseEvent;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.control.ColorPicker;


public class MeuPaint extends Application {

   private Circle c;
   private Circle aux;
   private Line l;
   private int form = 0;
   private Color cor =  Color.WHITE;

   @Override
   public void start(Stage stage) {
      
      //Bordas e Caixas cena
      BorderPane bord = new BorderPane();
      Pane pane = new Pane();
      VBox toolbar = new VBox();
      VBox cores = new VBox();

      //Botões de Comando
      Button circ = new Button("Circulo");
      Button line = new Button("Linha");
      Button lab = new Button("Chama");


      //Configuração Botão Circulo
      circ.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
              form = 0;
          }
      });

      //Configuração Botão Linha
      line.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event){
          form = 1; 
        }
      });

      final ColorPicker colorPicker = new ColorPicker();
      colorPicker.setValue(Color.WHITE);

      colorPicker.setOnAction(new EventHandler() {
      		@Override
            public void handle(Event t) {
                cor = colorPicker.getValue();               
            }
        });

      //Atribuindo seçoes na tela
      bord.setCenter(pane);
      bord.setLeft(toolbar);
      bord.setTop(cores);

      //Configurando pane
      pane.setOnMousePressed(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {
          switch (form){
            case 0:
              aux = new Circle(e.getX(), e.getY(), 12, Color.BLACK);
              c = new Circle(e.getX(), e.getY(), 10, cor);
              pane.getChildren().addAll(aux,c);
              break;

            case 1: 
              l = new Line(e.getX(), e.getY(), e.getX(), e.getY());
              pane.getChildren().add(l);
              break;
          }
        }
      });

      pane.setOnMouseDragged(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {
          switch(form){
            case 0:
              aux.setRadius(Math.sqrt(Math.pow((e.getX() - c.getCenterX()),2) + Math.pow((e.getY() - c.getCenterY()),2))+2 );
              c.setRadius(Math.sqrt(Math.pow((e.getX() - c.getCenterX()),2) + Math.pow((e.getY() - c.getCenterY()),2)) );
              break;

            case 1: 
              l.setEndX(e.getX());
              l.setEndY(e.getY());
              break;
          }
        }
      });
      
      //Configurando toolbar
      toolbar.setSpacing(1);
      toolbar.getChildren().setAll(circ, line);
      toolbar.setBackground(new Background(new BackgroundFill(Color.rgb(180,180,180),null,null)));

      //Configurando cores
      cores.getChildren().setAll(colorPicker);
      cores.setAlignment(Pos.TOP_LEFT);

      //Gerando cena
      Scene scene = new Scene(bord, 600, 500);
      stage.setScene(scene);
      stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
