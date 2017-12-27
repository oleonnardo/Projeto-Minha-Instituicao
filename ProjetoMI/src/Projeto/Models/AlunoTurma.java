/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projeto.Models;

/**
 *
 * @author Leonardo
 */
public class AlunoTurma {
    
    private int id;
    private int matricula_aluno;
    private int id_turma;

    public AlunoTurma(int id, int id_turma, int matricula_aluno) {
        this.id = id;
        this.id_turma = id_turma;
        this.matricula_aluno = matricula_aluno;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMatriculaAluno() {
        return matricula_aluno;
    }

    public void setMatriculaAluno(int matricula_aluno) {
        this.matricula_aluno = matricula_aluno;
    }

    public int getIdTurma() {
        return id_turma;
    }

    public void setIdTurma(int id_turma) {
        this.id_turma = id_turma;
    }
    
}
