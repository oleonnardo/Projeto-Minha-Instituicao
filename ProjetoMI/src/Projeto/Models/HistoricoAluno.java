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
public class HistoricoAluno {
    
    private int id_turma_aluno;
    private String professor;
    private String disciplina;
    private int id_aluno;
    private int id_turma;
    private double nota1;
    private double nota2;
    private double nota3;
    private double media;
    private int id_status;

    public HistoricoAluno(int id_turma_aluno, String professor, String disciplina, int id_aluno, int id_turma, double nota1, double nota2, double nota3, double media, int id_status) {
        this.id_turma_aluno = id_turma_aluno;
        this.professor = professor;
        this.disciplina = disciplina;
        this.id_aluno = id_aluno;
        this.id_turma = id_turma;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
        this.media = media;
        this.id_status = id_status;
    }

    public int getIdTurmaAluno() {
        return id_turma_aluno;
    }

    public void setIdTurmaAluno(int id_turma_aluno) {
        this.id_turma_aluno = id_turma_aluno;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public int getIdTurma() {
        return id_turma;
    }

    public void setIdTurma(int id_turma) {
        this.id_turma = id_turma;
    }

    public int getIdAluno() {
        return id_aluno;
    }

    public void setIdAluno(int id_aluno) {
        this.id_aluno = id_aluno;
    }

    public double getNota1() {
        return nota1;
    }

    public void setNota1(double nota1) {
        this.nota1 = nota1;
    }

    public double getNota2() {
        return nota2;
    }

    public void setNota2(double nota2) {
        this.nota2 = nota2;
    }

    public double getNota3() {
        return nota3;
    }

    public void setNota3(double nota3) {
        this.nota3 = nota3;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }

    public int getIdStatus() {
        return id_status;
    }

    public void setIdStatus(int id_status) {
        this.id_status = id_status;
    }
    
    
    
    
}
