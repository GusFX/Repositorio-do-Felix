package grafos;

import javafx.scene.shape.Line;

public class Aresta {
    
    private Vertice start;
    private Vertice end;
    private Line l;
    
    public Aresta(double x, double y){
        l = new Line(x,y,x,y);
    }
    
    public void setStart(Vertice v){
        this.start = v;
    }
    
    public void setEnd(Vertice v){
        this.end = v;
    }
    
    public void setEndLine(double x, double y){
        this.l.setEndX(x);
        this.l.setEndY(y);
    }
    
    public Line getLine(){
        return this.l;
    }
    
    public Vertice getStart(){
        return this.start;
    }
    
    public Vertice getEnd(){
        return this.end;
    }
}
