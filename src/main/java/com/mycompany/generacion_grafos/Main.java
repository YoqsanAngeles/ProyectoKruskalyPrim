/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.generacion_grafos;
import java.util.ArrayList;

/**
 *
 * @author darky
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Grafo graph1 = new Grafo();
        //graph1.Prueba();
        //graph1.Dijkstra(30);//el nodoinicial siempre es cero el otro es el nodo final
        //graph1.KruskalD(30);
        graph1.KruskalI(10);
        //graph1.Prim(10);
        //graph1.ErdosRenyi(10,30);
        //Grafo graph2 = new Grafo();
        //graph2.Gilbert(500,0.5);
        //Grafo graph3 = new Grafo();
        //graph3.Geografico(500,0.6);
        //Grafo graph4 = new Grafo();
        //graph4.BarabasiAlbert(500,4);
        
    }
    
}
