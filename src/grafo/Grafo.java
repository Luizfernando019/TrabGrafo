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
    List<Vertice> vertices = new ArrayList<Vertice>();
    List<Aresta> arestas = new ArrayList<Aresta>();
    private String tipo;

    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public void addVertice(String nome){
        if(getTipo() != null){
        Vertice v = new Vertice(nome);
        vertices.add(v);
        }
    }
    public void addAresta(String origem, String destino){
        Aresta a = new Aresta(origem, destino);
        if(getTipo() == "nOrientado")
        {
            for(Vertice v: vertices)
            {
                if(v.nome == origem)
                {
                    for(Vertice v1: vertices)
                    {
                        if(v1.nome == destino)
                        {
                            arestas.add(a);  
                        }
                    }  
                }
            }
        }
    }
    public String getAresta(){
        String r ="";
        for(Aresta a: arestas){
            r+=a.origem+""+a.destino+",";
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
        System.out.println(xml);
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
    /*public static void main(String[] args) {
        // TODO code application logic here
        Grafo g = new Grafo();
        g.setTipo("orientado");
        g.addVertice("a");
        g.addVertice("b");
        g.addVertice("c");
        
        g.addAresta("a","a");
        g.addAresta("a","b");
        g.addAresta("b","c");
        
        g.getAresta();
       g.salvar(g);
        System.out.println("**************************************");
        g.abrir(g);           

    }*/    
}
