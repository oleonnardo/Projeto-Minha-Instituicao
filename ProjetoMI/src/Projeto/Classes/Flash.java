/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projeto.Classes;

import java.sql.Driver;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Leonardo
 */
public class Flash {
    
    /*
        Classe com funções para a utilização do JOptionPane
    */
    
    public static void success(String message){
        JOptionPane.showMessageDialog(null, message, "Sucesso!", 0, new ImageIcon(Driver.class.getResource("/Projeto/Public/flash/success.png")));  
    }
    
    public static void info(String message){
        JOptionPane.showMessageDialog(null, message, "Informação!", 0, new ImageIcon(Driver.class.getResource("/Projeto/Public/flash/info.png"))); 
    }
    
    public static void warning(String message){
        JOptionPane.showMessageDialog(null, message, "Notificação!", 0, new ImageIcon(Driver.class.getResource("/Projeto/Public/flash/warning.png")));
    }
    
    public static void error(String message){
        JOptionPane.showMessageDialog(null, message, "Erro!", 0, new ImageIcon(Driver.class.getResource("/Projeto/Public/flash/error.png")));        
    }
    
    public static Object input(String message, String icon){
        icon = "/Projeto/Public/flash/"+icon+".png";
        return JOptionPane.showInputDialog(null, message, "", 0, new ImageIcon(Driver.class.getResource(icon)), null, "");
    }
    
    public static int confirm(String message, String icon){
        icon = "/Projeto/Public/flash/"+icon+".png";
        return JOptionPane.showConfirmDialog(null, message, "", JOptionPane.YES_NO_OPTION, 0, new ImageIcon(Driver.class.getResource(icon)));
    }
    
}
