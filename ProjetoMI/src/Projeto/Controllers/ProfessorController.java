/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projeto.Controllers;

import Projeto.Models.Professor;
import java.util.*;
/**
 *
 * @author Leonardo
 */
public class ProfessorController implements Controllers {
    
    ArrayList<Professor> professor  = new ArrayList();
    
    // retorna o arraylist completo
    public ArrayList<Professor> index(){
        return this.professor;
    }    
    
    // busca pelo aluno, através da matrícula
    public Professor search(int idProfessor){
        for (int i = 0; i < index().size(); i++) {
            if(idProfessor == index().get(i).getMatricula()) 
                return index().get(i);
        }
        return null;
    }  
    
    // grava o professor no arraylist
    @Override
    public void store(String[] dados){
        // int matricula, String nome
        professor.add(new Professor(
            Integer.valueOf(dados[0]),
            dados[1].toUpperCase()
        ));
    }
    
    // modifica dados do professor
    @Override
    public void update(String[] dados, int id){
        for (int i = 0; i < index().size(); i++) {
            if(id == index().get(i).getMatricula()){
                professor.get(i).setMatricula( Integer.valueOf(dados[0]));
                professor.get(i).setNome(dados[1].toUpperCase());
            }
        }
    }
    
    //remove o professor
    @Override
    public void delete(int id){
        for (int i = 0; i < index().size(); i++) {
            if(id == index().get(i).getMatricula()){
                professor.remove(i);
            }
        }
    }
    
    // recupera uma posição do arraylist apartir da matricula do professor
    public int posicao(int id){
        for (int i = 0; i < index().size(); i++) {
            if(id == index().get(i).getMatricula()) 
                return i;
        }
        return -1;
    }
    
}
