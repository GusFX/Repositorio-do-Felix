package grafos;

import java.util.ArrayList;
import javafx.scene.shape.Shape;

public class Vertice {
    
    private ArrayList<Aresta> arestas;
    private double x;
    private double y;
    private Shape shape;
    
    public Vertice(){
        arestas = new ArrayList<>();
    }
    
    public Vertice(double _x, double _y){
        arestas = new ArrayList<>();
        this.x = _x;
        this.y = _y;
    }
    
    public void addAresta(Aresta a){
        this.arestas.add(a);
    }
    
    public void setShape(Shape s){
        this.shape = s;
    }
    
    public Shape getShape(){
        return this.shape;
    }
    
    public boolean isIn(double _x, double _y, double tam){
        return (Math.abs(this.x - _x) <= tam && Math.abs(this.y - _y) <= tam);
    }
    
    public double getX(){
        return this.x;
    }
    
    public double getY(){
        return this.y;
    }
}
