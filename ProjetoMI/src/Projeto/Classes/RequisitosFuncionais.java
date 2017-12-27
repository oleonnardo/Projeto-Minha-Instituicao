/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projeto.Classes;

/**
 *
 * @author Leonardo
 */
public class RequisitosFuncionais {
    
    /*
        Classe que possui todos os requisitos do projeto Minha Instituição, 
        com alguns deles é possivel manipular ações da aplicação
    */
    
    // ---- REQUISITOS FUNCIONAIS DO ALUNO -------------------------------------
    
    // Número máximo de alunos
    public static int quantidadeAlunos = 500;
    
    // [RFA01] um aluno não pode estar matriculado em mais de três turmas
    public static int numeroMaximoTurmasPorAluno = 3;
    
    // [RFA02] um aluno não pode estar matriculado em mais de uma turma da mesma disciplina
    public static int numeroMaximoDisciplinaPorAluno = 1;
    
    // [RFA03] um aluno deve possuir um histórico de disciplinas já cursadas, contendo sua média em cada disciplina.
    
    // [FRA04] um aluno deve poder ver o seu histórico com as suas médias por disciplina e sua média global
    
    // [RFA05] um aluno deve ter uma lista de disciplinas sendo cursadas no momento
    
    // [FRA06] um aluno só poderá se matricular numa disciplina que já foi cursada se ele foi  reprovado na mesma (média < 5)
    public static double mediaMinima = 5.0;
    
    
    // ---- REQUISITOS FUNCIONAIS DO PROFESSOR ---------------------------------
    
    // Número de vagas de Professores
    public static int quantidadeProfessores = 50;
    
    // [RFP01] um professor pode ter até duas turmas
    public static int numeroMaximoTurmasPorProfessor = 2;
    
    // [RFP02] um professor só poderá ser desmatriculado da instituição se todas as suas turmas tiverem todas as notas
    public static boolean removerProfessorSeTurmasTiveremNotas = true;
    
    
    // ---- REQUISITOS FUNCIONAIS DO DISCIPLINA --------------------------------
    
    // Número máximo de alunos
    public static int quantidadeDisciplinas = 50;
    
    // [RFD01] não poderão ser criadas disciplinas repetidas
    public static boolean permiteDisciplinasIguais = false;
    
    
    // ---- REQUISITOS FUNCIONAIS DO TURMA -------------------------------------
    
    // Número de Turmas disponiveis
    public static int quantidadeTurmas = 50;
    
    // [RFT01] uma turma deve estar associada a uma disciplina
    // [RFT02] uma turma deve conter um professor responsável e vários alunos matriculados
    // [RFT03] uma turma deve ser criada apenas se for fornecido o professor responsável no momento da sua criação
    
    // [RFT04] uma turma só poderá ser encerrada se todos os alunos possuírem médias
    public static boolean turmaEncerradaComMediasProntas = true;
    
    // [RFT05] ao encerrar uma turma, os alunos nela matriculados terão sua média adicionada ao seu histórico individual.
    public static boolean addMediaHistorioIndividual = true;
    
    // Situação dos alunos durante as notas
    public static String[] situacaoAluno = {
        "Aprovado",
        "Reprovado",
        "Desistente",
        "Andamento"
    };
    
    public static String iconFrame = "/Projeto/Public/logos/favicon.png";
    
}
