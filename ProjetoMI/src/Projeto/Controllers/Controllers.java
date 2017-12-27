/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projeto.Controllers;

/**
 *
 * @author Leonardo
 */
interface Controllers {
    
    public void store(String[] dados);
    public void update(String[] dados, int id);    
    public void delete(int id);
    
}
