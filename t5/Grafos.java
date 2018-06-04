package grafos;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Rectangle;

public class Grafos extends Application{

    public static void main(String[] args) {
       launch(args); 
    }
    
    private Graph grafo = new Graph();
    private ArrayList<Aresta> arestas = new ArrayList<Aresta>();
    private Vertice vertice;
    private Aresta aresta;
    private Shape s;
    private int form = 0;
    private int flag = 0;
    private int inter = 0;
    private Color cor = Color.RED;
    private Alert alert;
    

    @Override
    public void start(Stage stage) throws Exception {
                
        //Instanciando Objetos que farão parte do layout
        BorderPane bord = new BorderPane();
        Pane tela = new Pane();
        HBox toolbar = new HBox();
        HBox menu = new HBox();
        VBox vv = new VBox();
        
        vv.setSpacing(5);
        vv.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
        menu.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
        toolbar.setBackground(new Background(new BackgroundFill(Color.GRAY,null,null)));

        //Intanciando botões de Menu
        Button graph = new Button("New Graph");
        Button save = new Button("Save");
        Button info = new Button("Info");
        Button exit = new Button("Exit");

        //Configura botões menu
        graph.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                tela.getChildren().clear();
                grafo = new Graph();
                arestas = new ArrayList<Aresta>();
            }
        });
        
        info.setOnAction(new EventHandler<ActionEvent>(){
           @Override
           public void handle(ActionEvent event){
               alert = new Alert(AlertType.INFORMATION);
               alert.setTitle("Características");
               alert.setHeaderText("Características do Grafo");
               alert.setContentText("Vertices: " +grafo.getSize() + "\nArestas: "+arestas.size()+" \nArestas Sobrepostas: "+inter);
               alert.showAndWait();
           }
        });
        
        exit.setOnAction(new EventHandler<ActionEvent>(){
           @Override
           public void handle(ActionEvent event){
               System.exit(0);
           }
        });

        //Instanciando botões da toolbar
        ToggleGroup group = new ToggleGroup();

        RadioButton vert = new RadioButton("Vertice");
        RadioButton ar = new RadioButton("Aresta");
        Button circ = new Button("Circulo");
        Button quad = new Button("Quadrado");
        ComboBox caixa = new ComboBox();
        
        caixa.getItems().addAll("Circulo","Quadrado");
        caixa.setValue("Circulo");  
        
        Slider tam = new Slider(5,50,15);

        vert.setToggleGroup(group);
        vert.setSelected(true);
        ar.setToggleGroup(group);

        final ColorPicker colorPicker = new ColorPicker(Color.RED);
        colorPicker.setOnAction(new EventHandler() {
            @Override
            public void handle(Event t) {
                cor = colorPicker.getValue();               
            }
        });
        
        menu.getChildren().addAll(graph,save,info,exit);
        toolbar.getChildren().addAll(vert,ar,colorPicker,caixa,tam);
        vv.getChildren().setAll(menu,toolbar);
        
        //Configurando botões toolbar
        vert.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                form = 0;
                toolbar.getChildren().add(caixa);
                toolbar.getChildren().add(tam);
            }
        });
        
        ar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                form = 1;
                toolbar.getChildren().remove(tam);
                toolbar.getChildren().remove(caixa);
            }
        });
        
        tela.setOnMousePressed(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {
            switch (form){
                case 0:
                    vertice = new Vertice(e.getX(),e.getY());
                    grafo.addVertice(vertice);
                    
                    if(caixa.getValue().equals("Circulo")){
                        s = new Circle(e.getX(), e.getY(), tam.getValue(), cor);
                        vertice.setShape(s);
                        tela.getChildren().add(vertice.getShape());
                    }
                    else{
                        s = new Rectangle(e.getX()-tam.getValue(), e.getY()-tam.getValue(), tam.getValue()*2, tam.getValue()*2);
                        s.setFill(cor);
                        vertice.setShape(s);
                        tela.getChildren().add(vertice.getShape());
                    }
                    break;

                case 1: 
                    if(grafo.getSize() < 2){
                        alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Aviso");
                        alert.setHeaderText(null);
                        alert.setContentText("Quantidade de vertices insuficiente.");
                        alert.showAndWait();
                    }
                    else if(flag == 0){
                        for(Vertice v : grafo.getNodes()){
                            if(v.isIn(e.getX(), e.getY(), tam.getValue())){
                                aresta = new Aresta(v.getX(),v.getY());
                                aresta.setStart(v);
                                flag = 1;
                            }
                        }
                    }
                    else{
                        for(Vertice v : grafo.getNodes()){
                            if(v.isIn(e.getX(), e.getY(), tam.getValue())){
                                int top = 1;
                                for(Aresta a : arestas){  
                                    if(((a.getStart().equals(aresta.getStart()) && a.getEnd().equals(v)) || (a.getStart().equals(v) && a.getEnd().equals(aresta.getStart())))){
                                        top = 0;
                                        flag = 0;
                                    }
                                }
                                if(top == 1){
                                    aresta.setEnd(v);
                                    aresta.setEndLine(v.getX(), v.getY());
                                    flag = 0;
                                    tela.getChildren().add(aresta.getLine());
                                    arestas.add(aresta);
                                }
                            }
                        }
                    }
                    break;
            }
        }
        });
        
        for(Aresta a1 : arestas){
            for(Aresta a2 : arestas){
                s = Shape.intersect(a1.getLine(), a2.getLine());
                if(s.getBoundsInLocal().getWidth() == -1)
                    inter++;
            }
        }
        
        //Definido posição dos elementos
        bord.setCenter(tela);
        bord.setTop(vv);
        
        Scene scene = new Scene(bord, 600, 500);
        stage.setScene(scene);
        stage.show();
    }
    
}
