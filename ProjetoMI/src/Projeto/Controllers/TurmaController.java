/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projeto.Controllers;

import Projeto.Models.Turma;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Leonardo
 */
public class TurmaController  {
     
    ArrayList<Turma> turmas  = new ArrayList();
    
    // recupera todas as posições do array list
    public ArrayList<Turma> index(){
        return this.turmas;
    }    
    
    // armazena turma no arraylist
    public void store(Integer[] dados){
        // int id, int id_disciplina, int id_professor, int vagas
        turmas.add(new Turma(dados[0], dados[1], dados[2], dados[3]));       
    }
    
    // busca turma pelo id
    public Turma search(int id){
        for (int i = 0; i < index().size(); i++) {
            if(index().get(i).getId() == id)
                return index().get(i);
        }
        return null;
    }
    
    // atualiza turma pelo id
    public void update(Integer[] dados, int id){
        for (int i = 0; i < index().size(); i++) {
            if(index().get(i).getId() == id){
                turmas.get(i).setId(dados[0]);
                turmas.get(i).setIdDisciplina(dados[1]);
                turmas.get(i).setIdProfessor(dados[2]);
                turmas.get(i).setVagas(dados[3]);
            }
        }
    }
    
    // remove turma
    public void delete(int id){
        for (int i = 0; i < index().size(); i++) {
            if(index().get(i).getId() == id) turmas.remove(i);
        }
    }
    
    // controle de número de professores nas turmas
    public boolean numeroMaximoTurmasPorProfessor(int idProfessor, int qtdeMaximaTurmas){
        int contador=0;
        for(int i=0; i < index().size(); i++){
            if( idProfessor == index().get(i).getIdProfessor() ){
                contador++;
                if( contador == qtdeMaximaTurmas ) 
                    return false;
            }
        }                
        return true;
    }
    
    // Busca pelo id da turma em uma string
    public int pegaIdTurma(String str){
        str = str.substring(0, 3);
        return Integer.valueOf(str.trim());
    }
    
    // controle de vagas em uma turma
    public void controleDeVagas(int idTurma, String opcao){
        try{
            switch(opcao){
                case "add":
                    for (int i = 0; i < index().size(); i++)
                        if( idTurma == index().get(i).getId() )
                            turmas.get(i).setVagas( turmas.get(i).getVagas() + 1 );
                    break;
                case "remove":
                    for (int i = 0; i < index().size(); i++)
                        if( idTurma == index().get(i).getId() )
                            turmas.get(i).setVagas( turmas.get(i).getVagas() - 1 );
                    break;
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Turma não encontrada!\n" + e, "Erro!", JOptionPane.ERROR_MESSAGE);
        }
    }  
    
    // busca pelo professor na turma
    public boolean buscaProfessor(int idProfessor){
        if( turmas.isEmpty() ) return true;
        for (int i = 0; i < index().size(); i++) {
            if(idProfessor == turmas.get(i).getIdProfessor()) 
                return false;
        }
        return true;
    }
    
    // busca pela disciplina na turma
    public boolean buscaDisciplina(int idDisciplina){
        if( turmas.isEmpty() ) return true;
        for (int i = 0; i < turmas.size(); i++) {
            if(idDisciplina == turmas.get(i).getIdDisciplina()) 
                return false;
        }
        return true;
    }
    
}
