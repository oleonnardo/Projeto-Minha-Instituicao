/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projeto.Controllers;

import Projeto.Classes.RequisitosFuncionais;
import Projeto.Models.Aluno;
import Projeto.Models.HistoricoAluno;
import java.util.*;

/**
 *
 * @author Leonardo
 */
public class HistoricoAlunoController implements Controllers{
     
    ArrayList<HistoricoAluno> historico_aluno  = new ArrayList();
    
    public ArrayList<HistoricoAluno> index(){
        return this.historico_aluno;
    }    
    
    @Override
    public void store(String[] dados){
        /*
            int id_turma_aluno, String professor, String disciplina, int id_aluno, 
            int id_turma, double nota1, double nota2, double nota3, double media, int id_status
        */
        HistoricoAluno model = new HistoricoAluno( 
            Integer.valueOf(dados[0]), // id_turma_aluno
            dados[1].toUpperCase(), // professor (Historico)
            dados[2].toUpperCase(), // disciplina (Historico)
            Integer.valueOf(dados[3]), // id_turma
            Integer.valueOf(dados[4]), // id_aluno
            Double.valueOf(dados[5]), // nota1
            Double.valueOf(dados[6]), // nota2
            Double.valueOf(dados[7]), // nota3
            Double.valueOf(dados[8]), // media
            Integer.valueOf(dados[9]) // id_status
        );
        historico_aluno.add(model);
    }
    
    public HistoricoAluno search(int id){
        for (int i = 0; i < index().size(); i++) {
            if( historico_aluno.get(i).getIdTurmaAluno() == id ) 
                return historico_aluno.get(i); 
        }
        return null;
    }
    
    @Override
    public void update(String[] dados, int id){
        for (int i = 0; i < index().size(); i++) {
            if( historico_aluno.get(i).getIdTurmaAluno() == id ){
                historico_aluno.get(i).setIdTurmaAluno(Integer.valueOf(dados[0]));
                historico_aluno.get(i).setProfessor(dados[1].toUpperCase());
                historico_aluno.get(i).setDisciplina(dados[2].toUpperCase());
                historico_aluno.get(i).setIdAluno(Integer.valueOf(dados[3]));
                historico_aluno.get(i).setIdTurma(Integer.valueOf(dados[4]));
                historico_aluno.get(i).setNota1(Double.valueOf(dados[5]));
                historico_aluno.get(i).setNota2(Double.valueOf(dados[6]));
                historico_aluno.get(i).setNota3(Double.valueOf(dados[7]));
                historico_aluno.get(i).setMedia(Double.valueOf(dados[8]));
                historico_aluno.get(i).setIdStatus(Integer.valueOf(dados[9]));
            }
        }
    }
    
    @Override
    public void delete(int id){
        for (int i = 0; i < index().size(); i++) {
            if( historico_aluno.get(i).getIdTurmaAluno() == id ) 
                historico_aluno.remove(i);
        }
    }
    
    public boolean verificaSeNotasEstaoFechadas(int idTurmaAluno, AlunoTurmaController aluno_turma, int idTurma){
        int contador=0;
        if(index().isEmpty()) return false;
        for (int k = 0; k < aluno_turma.index().size(); k++) {
            for (int i = 0; i < index().size(); i++) {
                if (aluno_turma.index().get(k).getId() == index().get(i).getIdTurmaAluno()
                    && aluno_turma.index().get(k).getIdTurma() == idTurma
                    && index().get(i).getIdStatus() == 3 )
                       return false;
            }
        }
        return true;        
    }
    
    public boolean consultaHistoricoTurmaAluno(String disciplina, int matriculaAluno){
        boolean status = true;
        for (int i = 0; i < index().size(); i++){
            if(matriculaAluno == index().get(i).getIdAluno() && 
                disciplina.equals(index().get(i).getDisciplina())){ 
                
                /*
                    verifica se a média do aluno é inferior a media mínima
                */
                status = (index().get(i).getMedia() < RequisitosFuncionais.mediaMinima) ? true : false;
                
                /*  
                    se o aluno tiver o status de "Reprovado" ou "Desistente",
                    o registro de historico e removido e um novo historico é criado para o mesmo  
                */
                if(index().get(i).getIdStatus() == 1 || index().get(i).getIdStatus() == 2) index().remove(i);
                
                return status;
            }
        }
        return status;
    }
    
}
