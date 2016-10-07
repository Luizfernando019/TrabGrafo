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
    
    public void addVertice(String nome){
        Vertice v = new Vertice(nome);
        vertices.add(v);
    }
    public void addAresta(String origem, String destino){
        Aresta a = new Aresta(origem, destino);
        arestas.add(a);
    }
    public List<Aresta> getAresta(){
        for(Aresta a: arestas){
            System.out.println(a.origem +","+a.destino);
        }
        return arestas;
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
    public void abrir(Grafo nome){
        XStream xStream = new XStream();
        xStream.alias("Grafo",Grafo.class );
        
        File xmlFileLer = new File("arq.xml");
        Grafo  gf = (Grafo) xStream.fromXML(xmlFileLer);
        String xml2 = xStream.toXML(gf);
        System.out.println(xml2);
    }
    public static void main(String[] args) {
        // TODO code application logic here
        Grafo g = new Grafo();
        g.addVertice("a");
        g.addVertice("b");
        g.addVertice("c");
        
        g.addAresta("a","a");
        g.addAresta("a","b");
        g.addAresta("b","c");
        
        g.salvar(g);
        System.out.println("**************************************");
        g.abrir(g);           

    }
    
}
