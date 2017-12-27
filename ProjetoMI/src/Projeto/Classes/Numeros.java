/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projeto.Classes;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Leonardo
 */
public class Numeros extends PlainDocument{
    
    /*
        Classe que controla a exibição e formatação de jTextFields nas interfaces e 
        controle de casas decimais em números do tipo float
    */
    
    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        super.insertString(offs, str.replaceAll("[^0-9]", ""), a); 
    }    
    
    public static String formatDecimal(Double nota){
        NumberFormat nf = new DecimalFormat ("0.00");
        return nf.format(nota);
    }    
    
}
