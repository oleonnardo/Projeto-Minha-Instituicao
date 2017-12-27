/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projeto.Classes;

import Projeto.Controllers.TurmaController;
/**
 *
 * @author Leonardo
 */
public class Generator {
    /*
        funções para gerar um sequencial para a matricula e id dos cadastros
    */
    private static int matricula = 171080120;
    private static int id = 400;

    public static int getProximoId() {
        return id++;
    }
    
    public static int getProximaMatricula() {
        return matricula++;
    }
    
}