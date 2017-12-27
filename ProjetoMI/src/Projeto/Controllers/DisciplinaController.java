/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projeto.Controllers;

import Projeto.Models.Disciplina;
import java.util.ArrayList;

/**
 *
 * @author Leonardo
 */
public class DisciplinaController implements Controllers {

   ArrayList<Disciplina> disciplina  = new ArrayList();
    
    public ArrayList<Disciplina> index(){
        return this.disciplina;
    }    
    
    public Disciplina search(int idDisciplina){
        for (int i = 0; i < index().size(); i++) {
            if(idDisciplina == index().get(i).getId()) 
                return index().get(i);
        }
        return null;
    }
    
    @Override
    public void store(String[] dados){
        // int id, String nome
        disciplina.add(new Disciplina(
            Integer.valueOf(dados[0]),
            dados[1].toUpperCase()
        ));
    }
        
    @Override
    public void update(String[] dados, int id){
        for (int i = 0; i < index().size(); i++) {
            if( id == index().get(i).getId() ){
                disciplina.get(i).setId(Integer.valueOf(dados[0]));
                disciplina.get(i).setNome(dados[1]);
            }
        }
    }
    
    @Override
    public void delete(int id){
        for (int i = 0; i < index().size(); i++) {
            if(id == index().get(i).getId()) 
            disciplina.remove(i); 
        }
    }    
    
    public int posicao(int idDisciplina){
        for (int i = 0; i < index().size(); i++) {
            if(idDisciplina == index().get(i).getId()) 
                return i;
        }
        return -1;
    }
    
    public boolean equalsId(int id){
        for (int i = 0; i < index().size(); i++) {
            if( id == index().get(i).getId() )    
                return true;
        }
        return false;        
    }
    
    public boolean equalsNome(String nome){
        for (int i = 0; i < index().size(); i++) {
            if( index().get(i).getNome().equals(nome.toUpperCase())) 
                return true;
        }
        return false;        
    }
}
