/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projeto.Classes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author Leonardo
 */
public class DataHora {
    
    /*
        retorna da data em formato completo
    */
    public static String completa(){
        Calendar c = Calendar.getInstance();
        Date data = c.getTime();
        DateFormat f = DateFormat.getDateInstance(DateFormat.LONG);
        return f.format(data); 
    }
    
    /*
        retorna somente o dia da semana
    */
    public static String diaDaSemana(){
        Calendar c = Calendar.getInstance();
        Date data = c.getTime();
        DateFormat f = new SimpleDateFormat("EEEE", new Locale("pt", "BR"));
        return f.format(data);
    }
    
}
