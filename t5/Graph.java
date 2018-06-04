package grafos;

import java.util.ArrayList;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;


public class Graph {
    private ArrayList<Vertice> nodes;
    
    public Graph(){
        nodes = new ArrayList<>(); 
    }
    
    public void addVertice(Vertice v){
        nodes.add(v);
    }
    
    public int getSize(){
        return nodes.size();
    }
    
    public ArrayList<Vertice> getNodes(){
        return this.nodes;
    }
    
}

