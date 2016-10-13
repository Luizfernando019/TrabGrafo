/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafo;

import com.thoughtworks.xstream.XStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Anah
 */
public class Grafo {

    /**
     * @param args the command line arguments
     */
    private List<Vertice> vertices = new ArrayList<Vertice>();
    private List<Aresta> arestas = new ArrayList<Aresta>();
    private String tipo;
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public void addVertice(int qt){
       int i = 0;
       int guant = qt;
       for(i =0; i<= guant;i++){
        Vertice v = new Vertice(i);
        vertices.add(v);
       }
       
    }
    public void addAresta(int origem, int destino){
        int i=0;
        Aresta a = new Aresta(origem, destino);
        if(getTipo().equals("orientado")){
            for(Vertice v1: vertices){
                if(v1.nome != destino && v1.nome == origem){
                    arestas.add(a);
                }
                       
            }   
        }else if(getTipo().equals("nOrientado")){
            Aresta a1 = new Aresta (destino,origem);
            arestas.add(a);
            arestas.add(a1);
        }
    }
    public String getAresta(){
        String r ="";
        for(Aresta a: arestas){
            r+=a.origem+","+a.destino+",";
            //System.out.println(a.origem+","+a.destino);
        }
        return r;
    }
    public String getVertice(){
        String r ="";
        for(Vertice v: vertices){
            r+= v.nome+",";
        }
        return r;
    }
    public void salvar(Grafo nome){
        XStream xStream = new XStream();
        xStream.alias("Grafo",Grafo.class );
        String xml = xStream.toXML(nome);
        try{
            File xmlFile = new File("arq.xml");
            xStream.toXML(nome, new FileWriter(xmlFile));
            
        }catch(IOException ex){
        
        }
    }
    public String abrir(Grafo nome){
        
        XStream xStream = new XStream();
        xStream.alias("Grafo",Grafo.class );
        
        File xmlFileLer = new File("arq.xml");
        Grafo  g = (Grafo) xStream.fromXML(xmlFileLer);
        String xml2 = xStream.toXML(g);
        return xml2;
    }    
}
