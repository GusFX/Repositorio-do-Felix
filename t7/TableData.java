import java.util.List;
import javafx.beans.property.SimpleStringProperty;

class TableData {
    private final SimpleStringProperty datahora;
    private final SimpleStringProperty ordem;
    private final SimpleStringProperty linha;
    private final SimpleStringProperty latitude;
    private final SimpleStringProperty longitude;
    private final SimpleStringProperty velocidade;
    
    TableData(List colums) {
        this.datahora = new SimpleStringProperty((String) colums.get(0));
        this.ordem = new SimpleStringProperty((String) colums.get(1));
        this.linha = new SimpleStringProperty(String.valueOf(colums.get(2)));
        this.latitude = new SimpleStringProperty(String.valueOf(colums.get(3)));
        this.longitude = new SimpleStringProperty(String.valueOf(colums.get(4)));
        this.velocidade = new SimpleStringProperty(String.valueOf(colums.get(5)));
    }
    
    
    public SimpleStringProperty datahoraProperty() {
        return datahora;
    }
    public String getDatahora() {
        return datahora.get();
    }
    public void setDatahora(String dh) {
        this.datahora.set(dh);
    }
        
    
    public SimpleStringProperty ordemProperty() {
        return ordem;
    }
    public String getOrdem() {
        return ordem.get();
    }
    public void setOrdem(String od) {
        this.ordem.set(od);
    }
    
    
    public SimpleStringProperty linhaProperty() {
        return linha;
    }
    public String getLinha() {
        return linha.get();
    }
    public void setLinha(String ln) {
        this.linha.set(ln);
    }
    
    
    public SimpleStringProperty latitudeProperty() {
        return latitude;
    }
    public String getLatitude() {
        return latitude.get();
    }
    public void setLatitude(String lat) {
        this.latitude.set(lat);
    }
    
    
    public SimpleStringProperty longitudeProperty() {
        return longitude;
    }
    public String getLongitude() {
        return longitude.get();
    }
    public void setLongitude(String lo) {
        this.longitude.set(lo);
    }
    
    
    public SimpleStringProperty velocidadeProperty() {
        return velocidade;
    }
    public String getVelocidade() {
        return velocidade.get();
    }
    public void setVelocidade(String vel) {
        this.velocidade.set(vel);
    }
    
}
