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
public class Turma {
    
   private int id;
   private int id_disciplina;
   private int id_professor;
   private int vagas;

    public Turma(int id, int id_disciplina, int id_professor, int vagas) {
        this.id = id;
        this.id_disciplina = id_disciplina;
        this.id_professor = id_professor;
        this.vagas = vagas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdDisciplina() {
        return id_disciplina;
    }

    public void setIdDisciplina(int id_disciplina) {
        this.id_disciplina = id_disciplina;
    }

    public int getIdProfessor() {
        return id_professor;
    }

    public void setIdProfessor(int id_professor) {
        this.id_professor = id_professor;
    }
    
    public int getVagas() {
        return vagas;
    }

    public void setVagas(int vagas) {
        this.vagas = vagas;
    }
        
}
