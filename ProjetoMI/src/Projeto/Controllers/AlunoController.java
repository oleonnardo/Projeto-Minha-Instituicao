/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projeto.Controllers;

import Projeto.Models.Aluno;
import java.util.*;

/**
 *
 * @author Leonardo
 */
public class AlunoController implements Controllers{
     
    ArrayList<Aluno> alunos  = new ArrayList();
    
    // retorna o arraylist completo
    public ArrayList<Aluno> index(){
        return this.alunos;
    } 
    
    // busca pelo aluno, através da matrícula
    public Aluno search(int idAluno){
        for (int i = 0; i < index().size(); i++) {
            if(idAluno == index().get(i).getMatricula()) 
                return index().get(i);
        }
        return null;
    }      
    
    // grava o aluno no arraylist
    @Override
    public void store(String[] dados){
        //int matricula, String nome
        alunos.add(new Aluno(
            Integer.valueOf(dados[0]),
            dados[1].toUpperCase()
        ));
    }
    
    // modifica dados do aluno
    @Override
    public void update(String[] dados, int id){
        for (int i = 0; i < index().size(); i++) {
            if(id == index().get(i).getMatricula()){
                alunos.get(i).setMatricula(Integer.valueOf(dados[0]));
                alunos.get(i).setNome(dados[1].toUpperCase());   
            }
        }
    }
    
    //remove o aluno
    @Override
    public void delete(int id){
        for (int i = 0; i < index().size(); i++) {
            if(id == index().get(i).getMatricula()){
                alunos.remove(i);  
            }
        }
    }
    
    // recupera uma posição do arraylist apartir da matricula do aluno
    public int posicao(int idAluno){
        for (int i = 0; i < index().size(); i++) {
            if(idAluno == index().get(i).getMatricula()) 
                return i;
        }
        return -1;
    }
  
    // função para verificar se existe uma matricula no array list
    public boolean equalsMatricula(int id){
        for (int i = 0; i < index().size(); i++) {
            if( id == index().get(i).getMatricula() )    
                return true;
        }
        return false;        
    }    
}
