/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projeto.Controllers;

import Projeto.Models.AlunoTurma;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Leonardo
 */
public class AlunoTurmaController{
    
    ArrayList<AlunoTurma> aluno_turmas = new ArrayList();
            
    public ArrayList<AlunoTurma> index(){
        return this.aluno_turmas;
    }    
    
    public void store(Integer[] dados){
        // int id, int id_turma, int matricula_aluno
        AlunoTurma model = new AlunoTurma(dados[0], dados[1], dados[2]); 
        aluno_turmas.add(model);
    }
    
    public AlunoTurma show(int id){
        try{
            return aluno_turmas.get(id);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Busca não concluida!" + e, "Erro!", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    
    public AlunoTurma search(int id){
        for (int i = 0; i < index().size(); i++) {
            if( aluno_turmas.get(i).getId() == id )    
                return index().get(i);
        }
        return null;        
    }
    
    public void update(Integer[] dados, int id){
        aluno_turmas.get(id).setId(dados[0]); 
        aluno_turmas.get(id).setMatriculaAluno(dados[1]); 
        aluno_turmas.get(id).setIdTurma(dados[2]);     
    }
    
    public void delete(int id){
        for (int i = 0; i < index().size(); i++) {
            if( aluno_turmas.get(i).getId() == id ) // SE ENCONTROU O REGISTRO DA MATRICULA
                aluno_turmas.remove(i); // ELE REMOVE AQUELA POSIÇÃO
        }
    }
    
    public boolean buscaAluno(int idTurma, int idAluno){
        for (int i = 0; i < index().size(); i++) {
            if( aluno_turmas.get(i).getIdTurma() == idTurma ) // SE ENCONTROU A TURMA SELECIONADA
                if(aluno_turmas.get(i).getMatriculaAluno() == idAluno) // VERIRICA SE A MATRICULA ALUNO DAQUELA POSIÇÃO É A MESMA
                    return false;
        }
        return true;        
    }
    
    public boolean numeroMaximoTurmasPorAluno(int idAluno, int qtdeMaximaTurmas){
        int contador=0;
        for(int i=0; i < index().size(); i++){
            if( idAluno == index().get(i).getMatriculaAluno() ){
                contador++;
                if( contador == qtdeMaximaTurmas ) 
                    return false;
            }
        }                
        return true;
    }
    
    public boolean verificaSeTurmaEstaVazia(int idTurma){
        if(index().isEmpty()) return false;
        for (int i = 0; i < index().size(); i++) {
            if(idTurma == index().get(i).getIdTurma()) return true;
        }
        return false;
    }
}
