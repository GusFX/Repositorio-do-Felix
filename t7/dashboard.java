import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.Map;
import static javafx.application.Application.launch;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
  
public class dashboard extends Application {
    
    private final HttpJSONService http = new HttpJSONService();
    
    private static final XYChart.Series barData = new XYChart.Series();
    private static final ObservableList<Series<String,Number>> barData2 = FXCollections.observableArrayList();
    
    private final TableView<TableData> table;
    private static final PieChart pizza = new PieChart();
    
    private static final BarChart<String,Number> barra = BarData.criaBarra();   
    
    private static final ObservableList<TableData> data = FXCollections.observableArrayList();
    private static ObservableList<PieChart.Data> pizzaData = FXCollections.observableArrayList(
                new PieChart.Data("Em movimento", 1),
                new PieChart.Data("Parados", 1));
    
    
    private static int veiculos;
    private static Date date = new Date();
    
    private static final VBox vbox = new VBox();
    private static final VBox graficos = new VBox();
    private static final HBox hbox = new HBox();
    private static final HBox labels = new HBox();
    
    private List<List> dados;
    

    public dashboard() {
        this.table = new TableView<>();
    }
    
    private static void alertConnection(){
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Connection failed");
        alert.setContentText("Please check your Internet connection!");
        alert.showAndWait();
    }
    
    private static void atualizaTabela(List<List> dados){
        data.clear();
        
        for(List v : dados)
            data.add(new TableData((List) v));
        veiculos = dados.size();
        
    }
    
    private static void atualizaPizza(List<List> dados){
        pizzaData.clear();
        
        int notparados = 0;
        int parados = 0;
        
        //jogar essa parte para outra função
        if(dados != null){    
            for(List l : dados){
                if((double)l.get(5) > 0){
                    notparados++;
                }
            }
            parados = dados.size() - notparados;
        }
        pizzaData =
                FXCollections.observableArrayList(
                new PieChart.Data("Em movimento", notparados),
                new PieChart.Data("Parados", parados));
        
        
        pizza.setData(pizzaData);
    }
    
    public static void atualizaBarra(List<List> dados){
        int cont;
        String linha;
        ArrayList<String> ocorrencias = new ArrayList();
        XYChart.Series series = new XYChart.Series();
                
        series.setName("Carros em Movimento por Linha");
        
        for(List l : dados){
            linha = String.valueOf(l.get(2));
            if(!linha.equals("") && !ocorrencias.contains(linha)){
                cont = procuraOcorrencias(linha, dados);
                ocorrencias.add(linha);
                series.getData().add(new XYChart.Data(linha, cont));
            }
        }
        
        barra.getData().clear();
        barra.getData().addAll(series);
    }
    
    //jogar essa func para outro arquivo
    private static int procuraOcorrencias(String lin, List<List> dados) {
        int cont = 0;
        
        for(List l : dados){
            double vel = (double) l.get(5);
            if(String.valueOf(l.get(2)).equals(lin) && vel > 0){
                cont++;
            }
        }
        
        return cont;
    }
    
    private static void atualizaLabels(List<List>dados, Label bus, Label hAtual){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        veiculos = dados.size();
        bus.setText("Número de veículos obtidos: "+veiculos);
        hAtual.setText("Última Atualização: "+dateFormat.format(date));
    }
    
  
    @Override
    public void start(Stage stage) {
    
        Scene scene = new Scene(new Group());

        final Label titulo = new Label("Dash Board");
        titulo.setFont(new Font("Arial", 20));

        Label bus = new Label("Número de veículos obtidos: "+veiculos);
        Label hAtual = new Label("Última Atualização: ");
                
        TableColumn<TableData,String> col1 = new TableColumn<>("DataHora"); 
        col1.setCellValueFactory(cellData -> cellData.getValue().datahoraProperty());

        TableColumn<TableData,String> col2 = new TableColumn<>("Ordem");
        col2.setCellValueFactory(cellData -> cellData.getValue().ordemProperty());

        TableColumn<TableData,String> col3 = new TableColumn<>("Linha");
        col3.setCellValueFactory(cellData -> cellData.getValue().linhaProperty());

        TableColumn<TableData,String> col4 = new TableColumn<>("Latitude");
        col4.setCellValueFactory(cellData -> cellData.getValue().latitudeProperty());

        TableColumn<TableData,String> col5 = new TableColumn<>("Longitude");
        col5.setCellValueFactory(cellData -> cellData.getValue().longitudeProperty());

        TableColumn<TableData,String> col6 = new TableColumn<>("Velocidade");
        col6.setCellValueFactory(cellData -> cellData.getValue().velocidadeProperty());
    
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.getColumns().addAll(col1,col2,col3,col4,col5,col6);
        
        
        
        File off = new File("offline.json");

        table.setItems(data);  
        barra.getData().add(barData);
        pizza.setData(pizzaData);
                
        Button btn = new Button("Atualiza lista");
    
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Map json = null;
                try {
                    json = http.sendGet("http://dadosabertos.rio.rj.gov.br/apiTransporte/apresentacao/rest/index.cfm/obterTodasPosicoes");
                } catch (Exception e) {
                    alertConnection();
                }
                if(json != null){
                    dados = (List) json.get("DATA");
                    atualizaTabela(dados);
                    atualizaPizza(dados);
                    atualizaBarra(dados);
                    atualizaLabels(dados, bus, hAtual);
                }
            }
        });
    
        graficos.getChildren().addAll(hbox,barra);
        
        labels.getChildren().addAll(bus,hAtual);
        labels.setSpacing(50);
        
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        hbox.getChildren().addAll(pizza,table);

        vbox.applyCss();
        vbox.layout();
        
        vbox.getChildren().addAll(titulo,hbox, barra, btn, labels);

        stage.setTitle("DashBoar Super Maneiro");
        stage.setScene(new Scene(vbox, 1000, 800));
        stage.show();
    
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}




