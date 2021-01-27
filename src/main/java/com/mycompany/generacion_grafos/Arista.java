/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.generacion_grafos;

/**
 *
 * @author darky
 */
public class Arista {
    private String label; //Etiqueta de la arista
    private Vertice ancestro; //nodo ancestro
    private Vertice descendiente;  //nodo descendiente
    private int revisado = 0;
    private int peso;
    private int acumulado = 0;
    
    public Arista(String label) {
        this.label = label;
    }
    
    public void addAncestro(Vertice ancestro) {
      this.ancestro = ancestro;
    }
    
    public void addDescendiente(Vertice descendiente) {
      this.descendiente = descendiente;
    }
    
    public void addRevisado(int revisado) {
      this.revisado = revisado;
    }
    
    public void addPeso(int peso) {
      this.peso = peso;
    }
    
    public void addAcumulado(int acumulado) {
      this.acumulado = acumulado;
    }
    
    public String getLabel (){
      return label;
  }  
  public Vertice getAncestro (){
      return ancestro;
  }
  public Vertice getDescendiente (){
      return descendiente;
  }
  
  public int getPeso (){
      return peso;
  }
  
  public int getAcumulado (){
      return acumulado;
  }
  
}
