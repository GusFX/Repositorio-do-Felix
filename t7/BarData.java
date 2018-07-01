import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class BarData {
    private final CategoryAxis xAxis = new CategoryAxis();
    private final NumberAxis yAxis = new NumberAxis();
    private final BarChart<String,Number> bc = new BarChart<>(xAxis,yAxis);
    XYChart.Series series1 = new XYChart.Series();
    
    BarData(){
        bc.setTitle("Movimento por Linha");
        xAxis.setLabel("Linha");       
        yAxis.setLabel("Carros");
    }

    public static BarChart<String,Number> criaBarra(){
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc = new BarChart<>(xAxis,yAxis);
        return bc;
    }
}
