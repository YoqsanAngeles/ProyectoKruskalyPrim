/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.generacion_grafos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author darky
 */
public class Grafo {

  void Prueba(){
      ArrayList<Vertice> ListaAdj = new ArrayList();
      ArrayList<Arista> ListaAristas = new ArrayList();
      for (int i=1;i<7;i++){
       Vertice nodo = new Vertice(Integer.toString(i));
      //nodo1.addConections(nodo2);
       ListaAdj.add(nodo);
      }
      Vertice nodo = ListaAdj.get(0);
      Vertice nodo2 = ListaAdj.get(1);
      Vertice nodo3 = ListaAdj.get(2);
      nodo.addConections(nodo2);
      Arista conexion1 = new Arista(Integer.toString(1));
      conexion1.addAncestro(nodo);
      conexion1.addDescendiente(nodo2);
      conexion1.addPeso(2);
      Arista conexion2 = new Arista(Integer.toString(2));
      conexion2.addAncestro(nodo);
      conexion2.addDescendiente(nodo3);
      conexion2.addPeso(1);
      nodo.addConections(nodo3);
      nodo = ListaAdj.get(1);
      nodo2 = ListaAdj.get(3);
      nodo.addConections(nodo2);
      Arista conexion3 = new Arista(Integer.toString(3));
      conexion3.addAncestro(nodo);
      conexion3.addDescendiente(nodo2);
      conexion3.addPeso(1);
      nodo = ListaAdj.get(2);
      nodo2 = ListaAdj.get(3);
      nodo3 = ListaAdj.get(4);
      nodo.addConections(nodo2);
      Arista conexion4 = new Arista(Integer.toString(4));
      conexion4.addAncestro(nodo);
      conexion4.addDescendiente(nodo2);
      conexion4.addPeso(3);
      nodo.addConections(nodo3);
      Arista conexion5 = new Arista(Integer.toString(5));
      conexion5.addAncestro(nodo);
      conexion5.addDescendiente(nodo3);
      conexion5.addPeso(4);
      nodo = ListaAdj.get(3);
      nodo2 = ListaAdj.get(4);
      nodo3 = ListaAdj.get(5);
      nodo.addConections(nodo3);
      Arista conexion6 = new Arista(Integer.toString(6));
      conexion6.addAncestro(nodo);
      conexion6.addDescendiente(nodo3);
      conexion6.addPeso(2);
      nodo2.addConections(nodo3);
      Arista conexion7 = new Arista(Integer.toString(7));
      conexion7.addAncestro(nodo2);
      conexion7.addDescendiente(nodo3);
      conexion7.addPeso(2);
      ListaAristas.add(conexion1);
      ListaAristas.add(conexion2);
      ListaAristas.add(conexion3);
      ListaAristas.add(conexion4);
      ListaAristas.add(conexion5);
      ListaAristas.add(conexion6);
      ListaAristas.add(conexion7);
      
      for (int i=0;i<ListaAdj.size();i++){
        nodo = ListaAdj.get(i);
        ArrayList<Vertice> conexiones = nodo.getConections();
      //for (int j=0;j<conexiones.size();j++){
      //    System.out.print("Nodo " + nodo.getLabel() + " conectado con ");
      //    System.out.println(conexiones.get(j).getLabel());
      // }
      }
      
      for (int i=0;i<ListaAristas.size();i++){
       Arista conexiones = ListaAristas.get(i);
         // System.out.print("Arista " + conexiones.getLabel() + " conecta ");
         // nodo = conexiones.getAncestro();
         // System.out.print(nodo.getLabel() + " con ");
         // System.out.print(conexiones.getDescendiente().getLabel());
         // System.out.println(" pesa " + conexiones.getPeso());
      }
      String nodo_i = "1";
      String nodo_0 = nodo_i;
      String nodo_f = "6";
      int acumulado = 0;
      ArrayList<Arista> aristasporrevisar = new ArrayList();
      System.out.println("Empezamos ");
      int terminar = 1;
    while (terminar == 1) {
      ArrayList<Arista> aristas_n = findEdges(ListaAristas,nodo_i); //funcion para encontrar las aristas con el mismo ancestro
      for (int i=0;i<aristas_n.size();i++){
          Arista edge = aristas_n.get(i);
          edge.addAcumulado(acumulado);
          aristasporrevisar.add(edge);
      }
      int indice_vertex = findIndex(ListaAdj,nodo_i);
      nodo = ListaAdj.get(indice_vertex);
      nodo.addRevisado(1);
      int acumulado_anterior = 101;
      int  indice_arista = 0;
      for (int i=0;i<aristasporrevisar.size();i++){
          Arista edge = aristasporrevisar.get(i);
          //System.out.println("Revisando nodo " + edge.getDescendiente().getLabel());
          int weight = edge.getPeso();
          int  peso = edge.getAcumulado()+weight;
          //System.out.println("Valor acumulado " + peso);
          if  (peso < acumulado_anterior){
              acumulado_anterior = peso;
              indice_arista = i;
          }

      }
 
      Arista edge = aristasporrevisar.get(indice_arista);
      int weight = edge.getPeso();
      acumulado = edge.getAcumulado();
      edge.addAcumulado(weight+acumulado);
      acumulado = weight + acumulado;
      //System.out.println("Nuevo acumulado " + acumulado);
      //System.out.println("Ancestro " + edge.getAncestro().getLabel());
      nodo = aristasporrevisar.get(indice_arista).getDescendiente();
      nodo.addRevisado(1);
      nodo.addAncestrodij(edge.getAncestro());
      nodo_i = nodo.getLabel();      
      aristasporrevisar.remove(indice_arista);

      //si quedan aristas que van hacia el nuevo nodo inicial, las borramos
      for (int i = 0;i<aristasporrevisar.size();i++){
          edge = aristasporrevisar.get(i);
          Vertice destino = edge.getDescendiente();
          if (destino.getLabel().equals(nodo_i)){
              aristasporrevisar.remove(i);
          }
      }
      if(aristasporrevisar.isEmpty()){
          terminar = 0;
      }
      }
     System.out.println("Peso total " + acumulado);
     RastrearCamino(nodo,nodo_0);
   
  }
  
  public  ArrayList<Vertice> Encontrarnodos( ArrayList<Arista> Lista) {
      ArrayList<Vertice> ListaAdj = new ArrayList(); 
      Vertice nodo1,nodo2;
      Arista edge;
      int index;
      for(int i=0;i<Lista.size();i++){
          edge = Lista.get(i);
          nodo1 = edge.getAncestro();
          nodo2 = edge.getDescendiente();
          index = findIndex(ListaAdj,nodo1.getLabel());
          if (index<0){
              ListaAdj.add(nodo1);
          }
          index = findIndex(ListaAdj,nodo2.getLabel());
          if (index<0){
              ListaAdj.add(nodo2);
          }
      }
      return ListaAdj;
  }
  
  int findTree(ArrayList<Arista> ListaAristas, Arista edge){
      int index1 = -1;
      int index2 = -1;
      int index;
      
      Vertice ancestro = edge.getAncestro();
      Vertice descendiente = edge.getDescendiente();
      
      for (int i=0;i<ListaAristas.size();i++){
         Arista  union = ListaAristas.get(i);
         Vertice ancestor = union.getAncestro();
         Vertice descendant = union.getDescendiente();
        if((ancestro == ancestor) || (ancestro == descendant)){
           index1 = 1;
       }
        if((descendiente == descendant) || (descendiente == ancestor)){
           index2 = 1;
       }
      }
      index = index1 + index2;
      return index;
  }
  ArrayList<Arista> ordenarAristasa(ArrayList<Arista> ListaAristas){
      ArrayList<Arista> NuevaLista = new ArrayList();
      Arista temporal;
      int peso1,peso2;
      for (int i=0;i<ListaAristas.size();i++){
          for (int j=0;j<ListaAristas.size()-1;j++){
              Arista edge1 = ListaAristas.get(j);
              Arista edge2 = ListaAristas.get(j+1);
              peso1 = edge1.getPeso();
              peso2 = edge2.getPeso();
              if (peso1 > peso2){
                  temporal = edge1;
                  ListaAristas.set(j,edge2);
                  ListaAristas.set(j+1,edge1);
              }
          }
      }
      return ListaAristas;
  }
  
    ArrayList<Arista> ordenarAristasd(ArrayList<Arista> ListaAristas){
      ArrayList<Arista> NuevaLista = new ArrayList();
      Arista temporal;
      int peso1,peso2;
      for (int i=0;i<ListaAristas.size();i++){
          for (int j=0;j<ListaAristas.size()-1;j++){
              Arista edge1 = ListaAristas.get(j);
              Arista edge2 = ListaAristas.get(j+1);
              peso1 = edge1.getPeso();
              peso2 = edge2.getPeso();
              if (peso1 < peso2){
                  temporal = edge1;
                  ListaAristas.set(j,edge2);
                  ListaAristas.set(j+1,edge1);
              }
          }
      }
      return ListaAristas;
  }

  
  void KruskalD(int nodo_end){
     //ArrayList<Arista> ListaAristas  =  ErdosRenyi(nodo_end,15);
     //ArrayList<Arista> ListaAristas = Gilbert(nodo_end,0.5);
     //ArrayList<Arista> ListaAristas = Geografico(nodo_end,0.8);
     ArrayList<Arista> ListaAristas = BarabasiAlbert(nodo_end,4);//problema a la hora de rastrear camino
    ArrayList<Vertice> ListaAdj = Encontrarnodos(ListaAristas);
    //ArrayList<Vertice> conjunto1 = new ArrayList();
  //  ArrayList<ArrayList<Vertice>> ListaAnidada = new ArrayList<>();

    /*for(int i=0;i<ListaAdj.size();i++){
        ArrayList<Vertice> Lista = new ArrayList();
        Lista.add(ListaAdj.get(i));
        ListaAnidada.add(Lista);
    }*/
    ArrayList<Arista> ListaOrdenada = ordenarAristasa(ListaAristas);
     ArrayList<Arista> Arbol = new ArrayList();  
    /*  System.out.println("Ordenadas");
     for(int i=0; i<ListaOrdenada.size();i++){
        Arista edge = ListaOrdenada.get(i);
        Vertice ancestro = edge.getAncestro();
        Vertice descendiente = edge.getDescendiente();
        System.out.println("Conexión de " + ancestro.getLabel() + " con " + descendiente.getLabel() );
    }*/

     System.out.println("Arbol");
   
    
    
     ArrayList<Arista> NuevaLista = ordenarAristasa(ListaAristas);
     ArrayList<Vertice> Agregados = new ArrayList();
     ArrayList<Arista> Kruskal = new ArrayList();
     
     for (int i=0;i<NuevaLista.size();i++){
        Arista edge = NuevaLista.get(i);
         System.out.println("Nodo " + edge.getAncestro().getLabel() + " con " + edge.getDescendiente().getLabel());
      }
       
    for(int epoca=0;epoca<2;epoca++){
     for (int i=0;i<NuevaLista.size();i++){
         Arista edge = NuevaLista.get(i);
         Vertice ancestro = edge.getAncestro();
         Vertice descendiente = edge.getDescendiente();
         //int indexa = findIndex(Agregados,ancestro.getLabel());
         //int indexd = findIndex(Agregados,descendiente.getLabel());
         int found1 = EncontrarNodo(Agregados,ancestro);
         int found2 = EncontrarNodo(Agregados,descendiente);
         System.out.println("Found1  " + found1 + " Found2 " + found2);
         
         if ((found1 == 0) && (found2==1)){
             Agregados.add(ancestro);
             Kruskal.add(edge);
         }
         if ((found1 == 1) && (found2 == 0)){
             Agregados.add(descendiente);
             Kruskal.add(edge);
         }
         
         if((found1 == 0) && (found2 == 0)){
          Kruskal.add(edge);    
         }
         
         if(i==0){
             Agregados.add(ancestro);
             Agregados.add(descendiente);
           }
       }
     }
      System.out.println("Arbol kruskal");
     for(int i=0;i<Kruskal.size();i++){
         Arista edge = Kruskal.get(i);
         System.out.println("Nodo " + edge.getAncestro().getLabel() + " con " + edge.getDescendiente().getLabel());
      
     }
     
     int contador = 0;
      for(int i=0;i<Kruskal.size();i++){
          Arista edge = Kruskal.get(i);
          contador = contador + edge.getPeso();
      }
      System.out.println("Peso total = " + contador);
     guardarAristasSinPeso(Kruskal,"KruskalDBarabasiAlbert.dot");
     
  }
  
  
  void KruskalI(int nodo_end){
     //ArrayList<Arista> ListaAristas  =  ErdosRenyi(nodo_end,nodo_end*3);
     //ArrayList<Arista> ListaAristas = Gilbert(nodo_end,0.5);
     ArrayList<Arista> ListaAristas = Geografico(nodo_end,0.8);
     //ArrayList<Arista> ListaAristas = BarabasiAlbert(nodo_end,4);//problema a la hora de rastrear camino
 
     ArrayList<Arista> NuevaLista = ordenarAristasd(ListaAristas);
     ArrayList<Arista> Arbol = new ArrayList();
     ArrayList<Arista> ListaAux = DeepEdgeCopy(NuevaLista);
     
     for (int i=0;i<NuevaLista.size();i++){
       Arista edge = NuevaLista.get(i);
         System.out.println("Arista " + edge.getLabel() + " conecta " + edge.getAncestro().getLabel() + " y " + edge.getDescendiente().getLabel());
     }

     
     for (int i=0;i<NuevaLista.size()-1;i++){
         ListaAux.remove(0);
         Arista edge = NuevaLista.get(i);
         int index = findTree(ListaAux,edge);
         System.out.println("Index " + index);
         if (index < 2){
           Arbol.add(edge);    
         }
      }
    
    int ind = NuevaLista.size()-1;
    Arista edge = NuevaLista.get(ind);
    Arbol.add(edge); 
    
     for(int i=0;i < Arbol.size();i++){
         edge = Arbol.get(i);
         System.out.println("Nodo " + edge.getAncestro().getLabel() + " y nodo " + edge.getDescendiente().getLabel());
     }

     int contador = 0;
      for(int i=0;i<Arbol.size();i++){
          edge = Arbol.get(i);
          contador = contador + edge.getPeso();
      }
      System.out.println("Peso total = " + contador);
     guardarAristasSinPeso(Arbol,"KruskalIGeografico.dot");
  }
  
  ArrayList<Arista> EncontrarConexion(ArrayList<Arista> Lista,String nodo){
      ArrayList<Arista> NuevaLista = new ArrayList();
      for (int i=0;i<Lista.size();i++){
          Arista edge = Lista.get(i);
          Vertice ancestro = edge.getAncestro();
          Vertice descendiente = edge.getDescendiente();
          if((ancestro.getLabel().equals(nodo))||(descendiente.getLabel().equals(nodo))){
              NuevaLista.add(edge);
          }
      }
      return NuevaLista;
  }
  
  int EncontrarNodo(ArrayList<Vertice> Lista,Vertice nodo){
      int encontrado = 0;
       for (int i=0;i<Lista.size();i++){
           Vertice vertex = Lista.get(i);
           if (vertex == nodo){
               encontrado = 1;
           }
       }
      return encontrado;
  }
  
  int EncontrarArista(ArrayList<Arista> Lista,Arista arista){
      int index = 0;
      for(int i=0;i<Lista.size();i++){
          Arista edge = Lista.get(i);
          if (edge == arista){
              index = 1;
          }
      }
      return index;
  }
  
  void Prim(int nodo_end){
     //ArrayList<Arista> ListaAristas  =  ErdosRenyi(nodo_end,nodo_end*3);
     ArrayList<Arista> ListaAristas = Gilbert(nodo_end,0.5);
     //ArrayList<Arista> ListaAristas = Geografico(nodo_end,1.3);
     //ArrayList<Arista> ListaAristas = BarabasiAlbert(nodo_end,4);//problema a la hora de rastrear camino
     ArrayList<Vertice> ListaAdj = Encontrarnodos(ListaAristas);
     for(int i=0;i<ListaAdj.size();i++){
         System.out.println("Nodo " + ListaAdj.get(i).getLabel());
     }
     
     ArrayList<Arista> Arbol = new ArrayList();
     ArrayList<Vertice> Revisados = new ArrayList();
     ArrayList<Arista> PorRevisar = new ArrayList();
     String nodo_0 = "0";
     
     
     //Aqui estará el for o while
     int contador = 0;
     while(contador < ListaAdj.size()){
     //for (int epoca = 0;epoca < 31;epoca++){
       ArrayList<Arista> conexiones = EncontrarConexion(ListaAristas,nodo_0);
       for (int i=0;i<conexiones.size();i++){
           Arista edge = conexiones.get(i);
           Vertice nodo1 = edge.getAncestro();
           Vertice nodo2 = edge.getDescendiente();
           int encontrado1 = EncontrarNodo(Revisados,nodo1);
           int encontrado2 = EncontrarNodo(Revisados,nodo2);
//           System.out.println("Nodo " + nodo1.getLabel() + " Encontrado =  " + encontrado1);
//           System.out.println("Nodo " + nodo2.getLabel() + " Encontrado =  " + encontrado2);
           if(encontrado1+encontrado2 == 2){
               conexiones.remove(i);
           }    
          }
         System.out.println("Conexiones");
       for (int i=0;i<PorRevisar.size();i++){
          Arista edge = PorRevisar.get(i);
          Vertice nodo1 = edge.getAncestro();
          Vertice nodo2 = edge.getDescendiente();
          int encontrado1 = EncontrarNodo(Revisados,nodo1);
          int encontrado2 = EncontrarNodo(Revisados,nodo2);
//           System.out.println("Nodo " + nodo1.getLabel() + " Encontrado =  " + encontrado1);
//           System.out.println("Nodo " + nodo2.getLabel() + " Encontrado =  " + encontrado2);
          if(encontrado1+encontrado2 < 2){
            conexiones.add(edge);         
          }
       }
       
       PorRevisar = new ArrayList();
       ArrayList<Arista> ListaOrd = ordenarAristasa(conexiones);
     
       System.out.println("Ordenadas");
       for (int i = 0;i<ListaOrd.size();i++){
        Arista edge = ListaOrd.get(i);
         String ancestro = edge.getAncestro().getLabel();
         String descendiente = edge.getDescendiente().getLabel();
         System.out.println("Nodo " + ancestro + " conectado con " + descendiente);
     }
      System.out.println("Nodo inicial " + nodo_0);
      
     int index = ListaOrd.size()-1;
     for (int i=0;i<ListaOrd.size();i++){
      Arista edge = ListaOrd.get(i);
      Vertice nodo1 = edge.getAncestro();
      Vertice nodo2 = edge.getDescendiente();
      Vertice nodo;
      //Primero debemos saber si la conexión está en ancestro o en descendiente
      
      if(nodo1.getLabel().equals(nodo_0)){
          nodo  = nodo2;
          //Revisados.add(nodo1);
          
          int found = EncontrarNodo(Revisados,nodo1);
          if (found == 0){
            Revisados.add(nodo1);    
          }
      }
      else{
          int existente  = EncontrarNodo(Revisados,nodo1);
          if (existente == 0) {
            nodo = nodo1;
           }
          else {
              nodo = nodo2;
          }
      }
         System.out.println("Conectado con " + nodo.getLabel());
      //encontrado nos dirá si el nodo ya está revisado o no
      int encontrado = EncontrarNodo(Revisados,nodo);
      if(encontrado == 0){
          int found = EncontrarNodo(Revisados,nodo);
          if (found == 0){
            Revisados.add(nodo);    
          }
          Arbol.add(edge);
          index = i;
          nodo_0 = nodo.getLabel();
          System.out.println("Nuevo nodo inicial " + nodo_0);
          break;
      }
     }
      System.out.println("Index " + index);
      System.out.println("Revisados los nodos ");
      for(int i=0;i<Revisados.size();i++){
          Vertice nodo = Revisados.get(i);
          System.out.println(nodo.getLabel());
      }

    //Agregamos a la cola las aristas que no se revisaron
     for (int i=index+1;i<ListaOrd.size();i++){
        Arista edge = ListaOrd.get(i);
        Vertice nodo1 = edge.getAncestro();
        Vertice nodo2 = edge.getDescendiente();
        Vertice nodo;
      //Primero debemos saber si la conexión está en ancestro o en descendiente
        if(nodo1.getLabel().equals(nodo_0)){
          nodo  = nodo2;
        }
        else{
           int existente  = EncontrarNodo(Revisados,nodo1);
          if (existente == 0) {
            nodo = nodo1;
           }
          else {
              nodo = nodo2;
          }
        }
        System.out.println("Nodo " + nodo.getLabel());
        int encontrado = EncontrarNodo(Revisados,nodo);
         System.out.println("Encontrado = " + encontrado);
        if (encontrado == 0){
            int encontrado2 = EncontrarArista(PorRevisar,edge);
            if (encontrado2 == 0){
              PorRevisar.add(edge);
            }
        }
     }
     
      System.out.println("Por Revisar");
     for (int i = 0;i<PorRevisar.size();i++){
         Arista edge = PorRevisar.get(i);
         String ancestro = edge.getAncestro().getLabel();
         String descendiente = edge.getDescendiente().getLabel();
         System.out.println("Nodo " + ancestro + " conectado con " + descendiente);
     }
     
     contador = 0;
     for(int i =0;i<Revisados.size();i++){
         Vertice nodo_aux = Revisados.get(i);
         int found = EncontrarNodo(ListaAdj,nodo_aux);
         contador = contador +found;
     }
     
         System.out.println("Nodos agregados " + contador);
     
     
     
     }//terminan las epocas
     int MST = 0;
     for(int i=0;i<Arbol.size();i++){
         Arista edge = Arbol.get(i);
         MST = MST + edge.getPeso();
     }
      System.out.println("Peso total = " + MST);
     guardarAristasSinPeso(Arbol,"MSTPrimGilbert_10.dot");
  }
  
    void Dijkstra(int nodo_end){
     String nodo_inicial = "0";
     Vertice nodo;
     ArrayList<Arista> ListaAristas; 
     //ListaAristas = ErdosRenyi(nodo_end,nodo_end*3);// Creamos el grafo de ErdosRenyu con el que trabajar
                                        //Esta función devuelve un array con todas las aristas 
     //ListaAristas = Gilbert(nodo_end,0.5);  
     ListaAristas = Geografico(nodo_end,0.8);
     //ListaAristas = BarabasiAlbert(nodo_end,4);//problema a la hora de rastrear camino
     
     
     
     String nodo_i = nodo_inicial;
     String nodo_0 = nodo_i;//Guardamos el nodo inicial porque lo usaremos para rastrear el resultado final
      String nodo_f = Integer.toString(nodo_end-1);
      ArrayList<Vertice> ListaAdj = Encontrarnodos(ListaAristas); //Con esta función creamos una lista con todos los nodos
      int indice_vertice_final = findIndex(ListaAdj,nodo_f);      //en base a las aristas que tenemos
      Vertice nodo_final = ListaAdj.get(indice_vertice_final); 
      int indice_vertice_inicial = findIndex(ListaAdj,nodo_i);      //en base a las aristas que tenemos
      Vertice nodo_de_inicio = ListaAdj.get(indice_vertice_inicial);
      int acumulado = 0;
      ArrayList<Arista> aristasporrevisar = new ArrayList();//Aquí guardaremos las aristas que ya revisamos pero no dieron el camino
                                                            //más corto
      int terminar = 1;
      int contador_final = 0;
//      for (int epoca=0;epoca<10;epoca++){
      while (terminar == 1) {
         ArrayList<Arista> aristas_n = findEdges(ListaAristas,nodo_i); //funcion para encontrar las aristas con el mismo ancestro
         for (int i=0;i<aristas_n.size();i++){
             Arista edge = aristas_n.get(i);
             edge.addAcumulado(acumulado);
             aristasporrevisar.add(edge);
         }
      
      int indice_vertex = findIndex(ListaAdj,nodo_i);
      int acumulado_anterior = 101;
      int  indice_arista = 0;
      for (int i=0;i<aristasporrevisar.size();i++){
          Arista edge = aristasporrevisar.get(i);
          if(edge.getDescendiente().getRevisado() == 0 ){
             //System.out.println("Revisando nodo " + edge.getDescendiente().getLabel());
          int weight = edge.getPeso();
          int  peso = edge.getAcumulado()+weight;
          //System.out.println("Valor acumulado " + peso);
          if  (peso < acumulado_anterior){
              acumulado_anterior = peso;
              indice_arista = i;
              Vertice nodo_peso = edge.getDescendiente();
              nodo_peso.addPeso(peso);
          }
        }
      }
 
      Arista edge = aristasporrevisar.get(indice_arista);
      int weight = edge.getPeso();
      acumulado = edge.getAcumulado();
      edge.addAcumulado(weight+acumulado);
      acumulado = weight + acumulado;
      //System.out.println("Nuevo acumulado " + acumulado);
      //System.out.println("Ancestro " + edge.getAncestro().getLabel());
      Vertice nodo_ancestro = edge.getAncestro();
      nodo_ancestro.addRevisado(1);
      nodo = aristasporrevisar.get(indice_arista).getDescendiente();
      nodo.addAncestrodij(edge.getAncestro());
      System.out.println(nodo.getAncestrodij().getLabel());
      nodo_i = nodo.getLabel();      
      aristasporrevisar.remove(indice_arista);
      System.out.println("Nuevo nodo inicial " + nodo_i);
      nodo.addRevisado(1);
      //System.out.println("Agotado el nodo " + nodo_i);
      //si quedan aristas que van hacia el nuevo nodo inicial, las borramos
      
   /*   System.out.println("Queda por revisar");
      for (int i=0;i<aristasporrevisar.size();i++){
          System.out.println("Nodo " + aristasporrevisar.get(i).getAncestro().getLabel() + " que conecta " + aristasporrevisar.get(i).getDescendiente().getLabel());    
      }*/
      
      for (int i = 0;i<aristasporrevisar.size();i++){
          edge = aristasporrevisar.get(i);
          Vertice destino = edge.getDescendiente();
          if ((destino.getLabel().equals(nodo_i)) || (destino.getRevisado() == 1)){
              aristasporrevisar.remove(i);
          }
      }

      if(aristasporrevisar.isEmpty()){// || (nodo_i.equals(nodo_f))){
          terminar = 0;
      }
      if (contador_final >= ListaAristas.size()*2){
          terminar = 0;
      }
      
      /*if(aristasporrevisar.isEmpty()){//Si no hay un camino que nos lleve de nodo_0 a nodo_final, se escoge el camino más corto
          nodo_final = nodo;          //hasta el último nodo al que se llegó
          System.out.println("No hay un camino del nodo inicial al nodo final");
          System.out.println("Se logró llegar hasta " + nodo_final.getLabel());
      }*/
      contador_final += 1;
      //System.out.println(contador_final);
      }
      
       System.out.println("Peso total " + acumulado);       
       System.out.println("Nodo inicial " + nodo_de_inicio.getLabel() + " Nodo final " + nodo_final.getLabel());
       //RastrearCamino(nodo_final,nodo_0);
//     guardarMascorto(ListaAristas,nodo_final,nodo_0,"Camino_mas_corto_Barabasi.dot",acumulado);
       GuardarArbol(ListaAdj,"ArbolGeografico.dot");
    }
  
  void GuardarArbol( ArrayList<Vertice> ListaAdj,String nombre){
       File f;
       FileWriter w;
       BufferedWriter bw;
       PrintWriter wr;
       try {
           f = new File(nombre);
           w = new FileWriter(f);
           bw = new BufferedWriter(w);
           wr = new PrintWriter(bw);
           wr.write("graph abstract {\n");
           for (int i=1;i<ListaAdj.size();i++){   
              Vertice nodo = ListaAdj.get(i);
              try{
              String etiqueta1 = nodo.getAncestrodij().getLabel();
              String etiqueta2 = nodo.getLabel();
              String etiqueta3 = Integer.toString(nodo.getPeso());
               wr.append("nodo_" + etiqueta1 + "->" + "nodo_" + etiqueta2 + "(" + etiqueta3 + ")" + ";\n");
               wr.append("nodo_" + etiqueta1 + "->" + "nodo_" + etiqueta2 + ";\n");
              } catch (Exception e) {
                  System.out.println("No hay caminoi al nodo");
              }
           }
           wr.append("}"); 
           wr.close();
           bw.close();
           
       } catch (IOException e){
           JOptionPane.showMessageDialog(null, "Error");
       }
   
  }  
    
  void RastrearCamino(Vertice nodo,String nodo_i){
      int terminar = 0;
      while (terminar == 0){
       System.out.println("Nodo " + nodo.getLabel());
       System.out.println("Ancestro " + nodo.getAncestrodij().getLabel());
       nodo = nodo.getAncestrodij();
       if (nodo.getLabel().equals(nodo_i)){
           terminar = 1;
       }
      }
  }
  
    ArrayList<Arista> ErdosRenyi(int nodos,int aristas){
        ArrayList<Vertice> ListaAdj = new ArrayList(); 
        ArrayList<Arista> ListaAristas = new ArrayList();
        int i = 0;
        for (int j=0;j<nodos;j++){
          Vertice nodo = new Vertice(Integer.toString(j));
          ListaAdj.add(nodo);
         }
        Arista edge;
        for (int j=0;j<aristas;j++){
         edge = new Arista(Integer.toString(j));
         ListaAristas.add(edge);
         }
        
        while ((i < aristas) && (i<nodos*(nodos-1))){
            Random rand = new Random();
            int n1 = rand.nextInt(nodos);
            int n2 = rand.nextInt(nodos);
            if (n1 != n2) {
                //System.out.println(i);
             Vertice nodo1,nodo2;
             int index1 = findIndex(ListaAdj,Integer.toString(n1)); //buscamos si ya existe el nodo
             if (index1 < 0){//si no existe, lo creamos
               nodo1 = new Vertice(Integer.toString(n1));
               ListaAdj.add(nodo1);
               index1 = ListaAdj.size()-1;
              }
             int index2 = findIndex(ListaAdj,Integer.toString(n2));
             if (index2 < 0){
               nodo2 = new Vertice(Integer.toString(n2));
               ListaAdj.add(nodo2);
               index2 = ListaAdj.size()-1;
              }
             nodo1 = ListaAdj.get(index1);
             nodo2 = ListaAdj.get(index2);
             int existente = nodo1.encontrarNodo(Integer.toString(n2));
             if (existente == 0) {
              nodo1.addConections(nodo2);
              nodo2.addConections(nodo1);
              edge =  ListaAristas.get(i);
              edge.addAncestro(nodo1);
              edge.addDescendiente(nodo2);
              edge.addPeso(rand.nextInt(9)+1);
              i = i+1;
             }
            } 
        }
        System.out.println("Grafo ErdosRenyi");
        guardarMatriz(ListaAdj,"ErdosRenyi.dot");
        //ListaAristas
        guardarAristas(ListaAristas,"ErdosRenyiAristas.dot");
        
//Esto es para el algoritmo DFS y BFS
/*        ArrayList<Vertice> Lista2 = DeepCopy(ListaAdj);
        ArrayList<Vertice> Lista3 = DeepCopy(ListaAdj);
        Vertice nodo0 = ListaAdj.get(0);
        ArrayList<Vertice> DFS_RList = DFS_Recursivo(nodo0);
        guardarMatriz(DFS_RList,"DFS_Recursivo_ErdosRenyi.dot");
        
        nodo0 = Lista2.get(0);
        System.out.println("Revisado " + nodo0.getRevisado());
        ArrayList<Vertice> DFS_IList = new ArrayList();
        DFS_Iterativo(Lista2,nodo0,DFS_IList);
        guardarMatriz(DFS_IList,"DFS_Iterativo_ErdosRenyi.dot");
        
        nodo0 = Lista3.get(0);
        ArrayList<Vertice> Cola = new ArrayList();
        Cola.add(nodo0);
        ArrayList<Vertice> BFS_RList = new ArrayList();
        BFS_Recursivo(Cola,BFS_RList);
        guardarMatriz(BFS_RList,"BFS_Recursivo_ErdosRenyi.dot");*/
    return ListaAristas;
    }
    
    //Método de gilbert, n nodos con probabilidad p de unirse.
    ArrayList<Arista> Gilbert(int nodos,double probabilidad){
        Random rand = new Random();
        ArrayList<Vertice> ListaAdj = new ArrayList(); 
        ArrayList<Arista> ListaAristas = new ArrayList();
        Vertice nodo;
        Vertice nodo1,nodo2;
        Arista edge;
        for (int i = 0;i<nodos;i++){ //Agregamos los n nodos a la lista de adjacencia
            nodo = new Vertice(Integer.toString(i));
            ListaAdj.add(nodo);
        }
        int contador_aristas = 0;
        for (int i=0;i<nodos;i++){
            nodo1 = ListaAdj.get(i);
            for (int j=0;j<nodos;j++){
                nodo2 = ListaAdj.get(j);
                double proba = Math.random();
                int existente = nodo1.encontrarNodo(Integer.toString(j));
                if ((proba < probabilidad) && (i != j) && (existente == 0)){
                    nodo1.addConections(nodo2);
                    nodo2.addConections(nodo1);
                    edge = new Arista(Integer.toString(contador_aristas));
                    edge.addAncestro(nodo1);
                    edge.addDescendiente(nodo2);
                    edge.addPeso(rand.nextInt(9)+1);
                    contador_aristas += 1;
                    ListaAristas.add(edge);
                }
            }
        }
        System.out.println("Grafo Gilbert");
        //imprimirLista(ListaAdj);
        guardarMatriz(ListaAdj,"Gilbert.dot");
        guardarAristas(ListaAristas,"GilbertAristas.dot");
        /*
        ArrayList<Vertice> Lista2 = DeepCopy(ListaAdj);
        ArrayList<Vertice> Lista3 = DeepCopy(ListaAdj);
        
        Vertice nodo0 = ListaAdj.get(0);
        ArrayList<Vertice> DFS_RList = DFS_Recursivo(nodo0);
        guardarMatriz(DFS_RList,"DFS_Recursivo_Gilbert.dot");
        
        nodo0 = Lista2.get(0);
        System.out.println("Revisado " + nodo0.getRevisado());
        ArrayList<Vertice> DFS_IList = new ArrayList();
        DFS_Iterativo(Lista2,nodo0,DFS_IList);
        guardarMatriz(DFS_IList,"DFS_Iterativo_Gilbert.dot");
        
        nodo0 = Lista3.get(0);
        ArrayList<Vertice> Cola = new ArrayList();
        Cola.add(nodo0);
        ArrayList<Vertice> BFS_RList = new ArrayList();
        BFS_Recursivo(Cola,BFS_RList);
        guardarMatriz(BFS_RList,"BFS_Recursivo_Gilbert.dot");*/
        return ListaAristas;
    }
    
    ArrayList<Arista> Geografico(int nodos,double r){
        ArrayList<Vertice> ListaAdj = new ArrayList(); 
        ArrayList<Arista> ListaAristas = new ArrayList();
        Vertice nodo;
        Vertice nodo1,nodo2;
        double distancia;
        double coordenadas[][] = new double[nodos][2]; //Creamos una matriz de coordenadas asociada a cada nodo
        Random random = new Random();
        Arista edge;
        Random rand = new Random();
        for (int j=0;j<nodos;j++){
          Double x = random.nextGaussian();
          Double y = random.nextGaussian();
          coordenadas[j][0]=x;
          coordenadas[j][1]=y;
        }
        
        for (int i = 0;i<nodos;i++){ //Agregamos los n nodos a la lista de adjacencia
            nodo = new Vertice(Integer.toString(i));
            ListaAdj.add(nodo);
        }
        int contador_aristas = 0;
         for (int i=0;i<nodos;i++){
          nodo1 = ListaAdj.get(i);
          for (int j=0;j<nodos;j++){
              nodo2 = ListaAdj.get(j);
              double x1 = coordenadas[i][0];
              double y1 = coordenadas[i][1];
              double x2 = coordenadas[j][0];
              double y2 = coordenadas[j][1];
              distancia = Math.sqrt(Math.pow(x1-x2,2)+ Math.pow(y1-y2,2));
              int existente = nodo1.encontrarNodo(Integer.toString(j));
              if ((distancia < r) && (i != j) && (existente == 0)) {
                 nodo1.addConections(nodo2);
                 nodo2.addConections(nodo1);
                 edge = new Arista(Integer.toString(contador_aristas));
                 edge.addAncestro(nodo1);
                 edge.addDescendiente(nodo2);
                 edge.addPeso(rand.nextInt(9)+1);
                 contador_aristas += 1;
                 ListaAristas.add(edge);
              }
         }  
       }
       System.out.println("Grafo Geográfico");
       //imprimirLista(ListaAdj); 
       guardarMatriz(ListaAdj,"Geografico.dot");
       guardarAristas(ListaAristas,"GeograficoAristas.dot");
       
       /*
       ArrayList<Vertice> Lista2 = DeepCopy(ListaAdj);
       ArrayList<Vertice> Lista3 = DeepCopy(ListaAdj);
       Vertice nodo0 = ListaAdj.get(0);
       ArrayList<Vertice> DFS_RList = DFS_Recursivo(nodo0);
       guardarMatriz(DFS_RList,"DFS_Recursivo_Geografico.dot");
        nodo0 = Lista2.get(0);
        System.out.println("Revisado " + nodo0.getRevisado());
        ArrayList<Vertice> DFS_IList = new ArrayList();
        DFS_Iterativo(Lista2,nodo0,DFS_IList);
        guardarMatriz(DFS_IList,"DFS_Iterativo_Geografico.dot");
        
        nodo0 = Lista3.get(0);
        ArrayList<Vertice> Cola = new ArrayList();
        Cola.add(nodo0);
        ArrayList<Vertice> BFS_RList = new ArrayList();
        BFS_Recursivo(Cola,BFS_RList);
        guardarMatriz(BFS_RList,"BFS_Recursivo_Geografico.dot");*/

    return ListaAristas;
    }
    
    ArrayList<Arista> BarabasiAlbert(int nodos,int d){
        ArrayList<Arista> ListaAristas = new ArrayList();
        ArrayList<Vertice> ListaAdj = new ArrayList();
        Vertice nodo = new Vertice(Integer.toString(0));
        ListaAdj.add(nodo);
        double p,proba;
        int grado;
        Arista edge;
        Random rand = new Random();
        Vertice nodo1,nodo2;
        int contador_aristas = 0;
        for (int i=1;i<nodos;i++){
            nodo2 = new Vertice(Integer.toString(i));
            for (int j=0;j<i;j++){
                nodo1 = ListaAdj.get(j);
                proba = Math.random(); //generamos un número aleatorio para comparar con p
                grado = nodo1.getConections().size();
                float div = (float)grado/d;
                p = 1 - div;
                if((p >= proba) && (i != j)){
                 nodo1.addConections(nodo2);
                 nodo2.addConections(nodo1);
                 edge = new Arista(Integer.toString(contador_aristas));
                 edge.addAncestro(nodo1);
                 edge.addDescendiente(nodo2);
                 edge.addPeso(rand.nextInt(9)+1);
                 contador_aristas += 1;
                 ListaAristas.add(edge);
                }
            }
            ListaAdj.add(nodo2);
        }
        System.out.println("BarabásiAlbert");
        guardarMatriz(ListaAdj,"Barabasi.dot");
        guardarAristas(ListaAristas,"BarabasiAlbertAristas.dot");
        /*
        ArrayList<Vertice> Lista2 = DeepCopy(ListaAdj);
        ArrayList<Vertice> Lista3 = DeepCopy(ListaAdj);
        
        Vertice nodo0 = ListaAdj.get(0);
        ArrayList<Vertice> DFS_RList = DFS_Recursivo(nodo0);
        guardarMatriz(DFS_RList,"DFS_Recursivo_Barabasi.dot");
        
        nodo0 = Lista2.get(0);
        System.out.println("Revisado " + nodo0.getRevisado());
        ArrayList<Vertice> DFS_IList = new ArrayList();
        DFS_Iterativo(Lista2,nodo0,DFS_IList);
        guardarMatriz(DFS_IList,"DFS_Iterativo_Barabasi.dot");
        
        nodo0 = Lista3.get(0);
        ArrayList<Vertice> Cola = new ArrayList();
        Cola.add(nodo0);
        ArrayList<Vertice> BFS_RList = new ArrayList();
        BFS_Recursivo(Cola,BFS_RList);
        guardarMatriz(BFS_RList,"BFS_Recursivo_Barabasi.dot");*/
        return ListaAristas;
    }
    
  ArrayList<Vertice> DFS_Recursivo(Vertice nodo_inicial){
      ArrayList<Vertice> ListaAdj = new ArrayList();
      ArrayList<Vertice> Lista2 = new ArrayList();
      //Lista2 = DFS_Recursivo_aux(nodo_inicial,ListaAdj);
      DFS_Recursivo_aux(nodo_inicial,ListaAdj);
      return ListaAdj;
  }

void DFS_Recursivo_aux(Vertice nodo_inicial,ArrayList<Vertice> ListaAdj){
//  ArrayList<Vertice> DFS_Recursivo_aux(Vertice nodo_inicial,ArrayList<Vertice> ListaAdj){
        nodo_inicial.addRevisado(1);
        ArrayList<Vertice> nivel_actual = nodo_inicial.getConections();
        Vertice nodo1,nodo2;
        for (int i=0;i<nivel_actual.size();i++){
            Vertice nodo = nivel_actual.get(i);
            if (nodo.getRevisado() == 0){
                int index = findIndex(ListaAdj,nodo_inicial.getLabel());
                if(index<0){
                    nodo1 = new Vertice(nodo_inicial.getLabel());
                    ListaAdj.add(nodo1);
                }
                else {
                    nodo1 = ListaAdj.get(index);
                }
                index = findIndex(ListaAdj,nodo.getLabel());
                if(index<0){
                    nodo2 = new Vertice(nodo.getLabel());
                    ListaAdj.add(nodo2);
                 //   System.out.println("Agegando nodo " + nodo2.getLabel());
                }
                else {
                    nodo2 = ListaAdj.get(index);
                }
                nodo1.addConections(nodo2);
                nodo2.addConections(nodo1); 
                DFS_Recursivo_aux(nodo,ListaAdj);
            }
            //return DFS_Recursivo_aux(nodo,ListaAdj);
        }
        //return ListaAdj;
      //  return 0;
   }
    
    void DFS_Iterativo(ArrayList<Vertice> Lista,Vertice nodo_inicial,ArrayList<Vertice> ListaAdj){
        //ArrayList<Vertice> Lista2 = new ArrayList();
        nodo_inicial.addRevisado(1);
         String nombre = "1",nombre_antiguo = "2";
         int terminar = 0;
         Vertice nodo1,nodo2;
         //System.out.println("Nodo inicial " + nodo_inicial.getLabel());
         while (terminar < Lista.size()){
           nombre_antiguo = nombre;
           ArrayList<Vertice> nivel_actual = nodo_inicial.getConections();
           for (int i=0;i<nivel_actual.size();i++){
             Vertice nodo = nivel_actual.get(i);
             //System.out.println("Revisado " + nodo.getRevisado());
             if (nodo.getRevisado() == 0){
                 //nodo.addRevisado(1);
                 int index = findIndex(ListaAdj,nodo_inicial.getLabel());
                 if(index<0){
                    nodo1 = new Vertice(nodo_inicial.getLabel());
                    ListaAdj.add(nodo1);
                 }
                else {
                    nodo1 = ListaAdj.get(index);
                }
                index = findIndex(ListaAdj,nodo.getLabel());
                if(index<0){
                    nodo2 = new Vertice(nodo.getLabel());
                    ListaAdj.add(nodo2);
                }
                else {
                    nodo2 = ListaAdj.get(index);
                }
                nodo1.addConections(nodo2);
                nodo2.addConections(nodo1); 
                 
                 System.out.println("Nodo " + nodo_inicial.getLabel() + " conectado con " + nodo.getLabel());
                 nodo.addRevisado(1);
                 nodo.addAncestro(nodo_inicial);
                 nodo_inicial = nodo;
                 terminar += 1;
                 nombre = nodo_inicial.getLabel();    
                 break;
             }
             if (i==nivel_actual.size()-1){
                 System.out.println("Final de la rama");
                 nodo_inicial = nodo.getAncestro();
                 terminar += 1;
                 System.out.println("Nuevo nodo inicial " + nodo_inicial.getLabel());
             }
           }
        }
        
    }
    
    void BFS_Recursivo(ArrayList<Vertice> Lista,ArrayList<Vertice> AdjList){
        if (Lista.size()>0){
         ArrayList<Vertice> Cola;//nodo_inicial.getConections();
         Vertice nodo = Lista.get(0);
         nodo.addRevisado(1);
         Lista.remove(0);
         Cola = nodo.getConections();
         Vertice nodo1,nodo2;
         for (int i=0;i<nodo.getConections().size();i++){
           Vertice nuevo_nodo = Cola.get(i);
           if (nuevo_nodo.getRevisado() == 0){
              nuevo_nodo.addRevisado(1);
              Lista.add(nuevo_nodo);
              
              int index = findIndex(AdjList,nodo.getLabel());
                 if(index<0){
                    nodo1 = new Vertice(nodo.getLabel());
                    AdjList.add(nodo1);
                 }
                else {
                    nodo1 = AdjList.get(index);
                }
                index = findIndex(AdjList,nuevo_nodo.getLabel());
                if(index<0){
                    nodo2 = new Vertice(nuevo_nodo.getLabel());
                    AdjList.add(nodo2);
                }
                else {
                    nodo2 = AdjList.get(index);
                }
                nodo1.addConections(nodo2);
                nodo2.addConections(nodo1); 
                 
              System.out.println("Nodo " + nodo.getLabel() + " conectado con " + nuevo_nodo.getLabel());
              
           }
        }
         BFS_Recursivo(Lista,AdjList);
      }

    }

     public ArrayList<Vertice> DeepCopy( ArrayList<Vertice> Lista1){
         //System.out.println("Iniciando copia");
         ArrayList<Vertice> Lista2 = new ArrayList();
         ArrayList<Vertice> nivel = new ArrayList();
         // System.out.println("Tamaño de la lista1 " + Lista1.size());
         for (int i=0;i<Lista1.size();i++){
           Vertice nodo = new Vertice(Lista1.get(i).getLabel());
           Lista2.add(nodo);
          }
         //System.out.println("Lista 1");
         for (int i=0;i<Lista1.size();i++){
           Vertice nodo = Lista1.get(i);
           Vertice nodo_aux = Lista2.get(i);
           //System.out.print("Nodo " + nodo.getLabel() + " conectado con ");
           nivel = nodo.getConections();
           for(int j=0;j<nodo.getConections().size();j++){
               Vertice nodo2 = nivel.get(j);
               Vertice nodo2_aux = Lista2.get(Integer.parseInt(nodo2.getLabel()));
            //   System.out.print(" " + nodo2.getLabel());
               nodo_aux.addConections(nodo2_aux);
           }
          // System.out.println(" ");
          }
        // System.out.println("Fin de la lista");
         
         /*System.out.println("Lista 2");
         for (int i=0;i<Lista2.size();i++){
           Vertice nodo = Lista2.get(i);
           System.out.print("Nodo " + nodo.getLabel() + " conectado con ");
           nivel = nodo.getConections();
           for(int j=0;j<nodo.getConections().size();j++){
               Vertice nodo2 = nivel.get(j);
               System.out.print(" " + nodo2.getLabel());
           }
           System.out.println(" ");
          }*/
          return Lista2;
     }
     public void imprimirLista(ArrayList<Vertice> Lista) {
      for (int i = 0;i<Lista.size();i++){
          Vertice nodo = Lista.get(i);
          String etiqueta = nodo.getLabel();
          System.out.println("nodo " + etiqueta );
         //int val = nodo.encontrarNodo(nodo.getConections(), "1");
          System.out.print("Conectado con: ");
          ArrayList<Vertice> conections = nodo.getConections();
          for (int j = 0;j<conections.size();j++){
              System.out.print(conections.get(j).getLabel() + " ");
          }
          System.out.println(" ");
      }
    }
    
    public int findIndex(ArrayList<Vertice> conections,String vertex){
      int index = -1;
          for (int j = 0;j<conections.size();j++){
            Vertice nodo = conections.get(j);
            String nombre = nodo.getLabel();
            if (nombre.equals(vertex)){
                index = j;
            }
          }      
      return index;
  }
    
     public ArrayList<Arista> findEdges(ArrayList<Arista> conections,String vertex){
       ArrayList<Arista> aristas_n = new ArrayList();
       
       for (int j = 0;j<conections.size();j++){
            Arista conexiones = conections.get(j);
            Vertice nodo = conexiones.getAncestro();
            String nombre = nodo.getLabel();
            if (nombre.equals(vertex)){
                aristas_n.add(conexiones);
            }
          }      
      return aristas_n;
  }
     
   public  ArrayList<Arista> DeepEdgeCopy(ArrayList<Arista> Lista){
       ArrayList<Arista> NuevaLista = new ArrayList();
       for (int i=0;i<Lista.size();i++){
           Arista edgeLista = Lista.get(i);
           String name = edgeLista.getLabel(); 
           Arista edge = new Arista(name);
           
           Vertice ancestro = edgeLista.getAncestro();
           Vertice descendiente = edgeLista.getDescendiente();
           int peso = edge.getPeso();
           
           edge.addAncestro(ancestro);
           edge.addDescendiente(descendiente);
           edge.addPeso(peso);
           NuevaLista.add(edge);
       }
       return NuevaLista;
   } 
     
    
    public void guardarMatriz(ArrayList<Vertice> Lista, String nombre){
       File f;
       FileWriter w;
       BufferedWriter bw;
       PrintWriter wr;
       try {
           f = new File(nombre);
           w = new FileWriter(f);
           bw = new BufferedWriter(w);
           wr = new PrintWriter(bw);
           wr.write("graph abstract {\n");
          
         for (int i = 0;i<Lista.size();i++){
            Vertice nodo = Lista.get(i);
            String etiqueta = nodo.getLabel();
            int n1 = Integer.parseInt(etiqueta);
            ArrayList<Vertice> conections = nodo.getConections();
            for (int j=0;j<conections.size();j++){
                String etiqueta2 = conections.get(j).getLabel();
                int n2 = Integer.parseInt(etiqueta2);
                if (n1 < n2){
                 wr.append("nodo_" + etiqueta + "->" + "nodo_" + etiqueta2 + ";\n");
                }
            }
         }

           wr.append("}"); 
           wr.close();
           bw.close();
           
       } catch (IOException e){
           JOptionPane.showMessageDialog(null, "Error");
       }
    }
    
    public void guardarAristas(ArrayList<Arista> Lista, String nombre){
       File f;
       FileWriter w;
       BufferedWriter bw;
       PrintWriter wr;
       try {
           f = new File(nombre);
           w = new FileWriter(f);
           bw = new BufferedWriter(w);
           wr = new PrintWriter(bw);
           wr.write("graph abstract {\n");
           
         for (int i = 0;i<Lista.size();i++){
            Arista edge = Lista.get(i);
            Vertice nodo1 = edge.getAncestro();
            Vertice nodo2 = edge.getDescendiente();
            String etiqueta1 = nodo1.getLabel();
            String etiqueta2 = nodo2.getLabel();
            int peso = edge.getPeso();
            String weight = Integer.toString(peso);
            
            int n1 = Integer.parseInt(etiqueta1);
            int n2 = Integer.parseInt(etiqueta2);
            
            wr.append("nodo_" + etiqueta1 + "->" + "nodo_" + etiqueta2 + " peso " + weight + ";\n");
             
         }

           wr.append("}"); 
           wr.close();
           bw.close();
           
       } catch (IOException e){
           JOptionPane.showMessageDialog(null, "Error");
       }
    }
    
    public void guardarAristasSinPeso(ArrayList<Arista> Lista, String nombre){
       File f;
       FileWriter w;
       BufferedWriter bw;
       PrintWriter wr;
       try {
           f = new File(nombre);
           w = new FileWriter(f);
           bw = new BufferedWriter(w);
           wr = new PrintWriter(bw);
           wr.write("graph abstract {\n");
           
         for (int i = 0;i<Lista.size();i++){
            Arista edge = Lista.get(i);
            Vertice nodo1 = edge.getAncestro();
            Vertice nodo2 = edge.getDescendiente();
            String etiqueta1 = nodo1.getLabel();
            String etiqueta2 = nodo2.getLabel();
            int n1 = Integer.parseInt(etiqueta1);
            int n2 = Integer.parseInt(etiqueta2);
            
            wr.append("nodo_" + etiqueta1 + "->" + "nodo_" + etiqueta2 + ";\n");
             
         }

           wr.append("}"); 
           wr.close();
           bw.close();
           
       } catch (IOException e){
           JOptionPane.showMessageDialog(null, "Error");
       }
    }
   
     public void guardarMascorto(ArrayList<Arista> Lista, Vertice nodo,String nodo_i,String nombre,int peso_total){
      int terminar = 0;
      
       File f;
       FileWriter w;
       BufferedWriter bw;
       PrintWriter wr;
       try {
           f = new File(nombre);
           w = new FileWriter(f);
           bw = new BufferedWriter(w);
           wr = new PrintWriter(bw);
           wr.write("graph abstract {\n");
           
           int contador = 0;
         while (terminar == 0){
            nodo.getLabel();
            String etiqueta1 = nodo.getLabel();
            if(contador == 0){
                etiqueta1 = etiqueta1 + "(" + Integer.toString(peso_total) + ")";
            }
            String etiqueta2 = nodo.getAncestrodij().getLabel();
            wr.append("nodo_" + etiqueta1 + "->" + "nodo_" + etiqueta2 + ";\n");
            nodo = nodo.getAncestrodij();
            if (nodo.getLabel().equals(nodo_i)){
               terminar = 1;
              }
            contador += 1;
         }

           wr.append("}"); 
           wr.close();
           bw.close();
           
       } catch (IOException e){
           JOptionPane.showMessageDialog(null, "Error");
       }
    }
}
