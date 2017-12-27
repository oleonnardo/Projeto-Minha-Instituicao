/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Projeto.Views;

import Projeto.Classes.DataHora;
import Projeto.Classes.Flash;
import Projeto.Classes.Numeros;
import Projeto.Classes.RequisitosFuncionais;
import Projeto.Controllers.*;
import Projeto.Models.*;
import java.awt.Dimension;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leonardo
 */
public class HistoricoAluno extends javax.swing.JInternalFrame {
    
    String buscaAluno;
    int matriculaAluno, indiceAluno;
    Aluno modelAluno;
            
    AlunoController aluno;
    ProfessorController professor;
    DisciplinaController disciplina;
    TurmaController turma;
    AlunoTurmaController aluno_turma;
    HistoricoAlunoController historico_aluno;
    boolean fecharJanela = false;
    
    /** Creates new form jProfessores
     * @param buscaAluno
     * @param controllers */
    public HistoricoAluno(String buscaAluno, Object[] controllers) {
        initComponents();
        
        this.aluno = (AlunoController) controllers[0];
        this.professor = (ProfessorController) controllers[1];
        this.disciplina = (DisciplinaController) controllers[2];
        this.turma = (TurmaController) controllers[3];
        this.aluno_turma = (AlunoTurmaController) controllers[4];
        this.historico_aluno = (HistoricoAlunoController) controllers[5];  
        
        this.buscaAluno = buscaAluno;
        iniciarBusca();  
        
        jlDiaExtenso.setText(DataHora.diaDaSemana());
        jlDataHora.setText(DataHora.completa());
    }
    
    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }
  
    private void iniciarBusca(){
        int contador=0;
       
        // VERIFICA SE O INPUT DIALOG RECEBEU ALGUM DADO, CASO NEGATIVO A JANELA NÃO É ABERTA
        try{
            matriculaAluno = (buscaAluno==null) ? 0 : Integer.valueOf(buscaAluno);
        }catch(NumberFormatException e){}
        
        for (int i = 0; i < aluno.index().size(); i++) {
            if( aluno.index().get(i).getMatricula() == matriculaAluno ){
                modelAluno = aluno.index().get(i);
                indiceAluno = i;
                contador++;
                break;
            }                
        }
        
        if(contador>0){
            jlNomeAluno.setText(modelAluno.getNome());
            jlMatriculaAluno.setText(String.valueOf(modelAluno.getMatricula()));

            disciplinasMatriculadas();
            historicoDeTurmas();
            mediaGlobal();
        }else{
            alunoNaoEncontrado();
            Flash.error("Nenhum registro encontrado.");
        }
    }
    
    private void disciplinasMatriculadas(){
        for (int i = 0; i < historico_aluno.index().size(); i++) {
            if(historico_aluno.index().get(i).getIdAluno() == matriculaAluno && historico_aluno.index().get(i).getIdStatus() == 3){
                tfDisciplinasMatriculadas.append(historico_aluno.index().get(i).getDisciplina() + "\n");
                tfDisciplinasMatriculadas.append(historico_aluno.index().get(i).getProfessor() + "\n\n");
            }
        }
    }
    
    private void historicoDeTurmas(){
        int contador=0;
        
        for (int i = 0; i < historico_aluno.index().size(); i++) {
            if(historico_aluno.index().get(i).getIdAluno() == matriculaAluno){
                tfHistoricoDeTurmas.append(historico_aluno.index().get(i).getDisciplina() + " - ");
                tfHistoricoDeTurmas.append(historico_aluno.index().get(i).getProfessor() + "; ");
                tfHistoricoDeTurmas.append("{ " + Numeros.formatDecimal(historico_aluno.index().get(i).getMedia()) + ": ");
                tfHistoricoDeTurmas.append( RequisitosFuncionais.situacaoAluno[historico_aluno.index().get(i).getIdStatus()] + " }\n\n" );
                contador++;
            }
        }
        
        tfNumeroDeTurmas.setText(String.valueOf(contador));   
    }
    
    private void mediaGlobal(){
        int contador=0;
        double mg=0, sm=0;
        
        for (int i = 0; i < historico_aluno.index().size(); i++) {
            if(historico_aluno.index().get(i).getIdAluno() == matriculaAluno && historico_aluno.index().get(i).getMedia() != 0.0){
                sm += historico_aluno.index().get(i).getMedia();
                contador++;
            }
        }
        
        if(contador>0) mg = sm/contador;
        tfMediaGlobal.setText(Numeros.formatDecimal(mg));        
    }
    
    private void alunoNaoEncontrado(){
        tfMediaGlobal.setText("0,0");
        tfNumeroDeTurmas.setText("0,0");
        jlNomeAluno.setText("ALUNO NÃO ENCONTRADO");
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jlTituloJanela = new javax.swing.JLabel();
        jlMatriculaAluno = new javax.swing.JLabel();
        jlNomeAluno = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jlDiaExtenso = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tfDisciplinasMatriculadas = new javax.swing.JTextArea();
        jlDataHora = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        tfMediaGlobal = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        tfNumeroDeTurmas = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tfHistoricoDeTurmas = new javax.swing.JTextArea();

        setClosable(true);
        setTitle("Historico do Aluno");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(35, 103, 159));

        jlTituloJanela.setFont(new java.awt.Font("Trebuchet MS", 0, 36)); // NOI18N
        jlTituloJanela.setForeground(new java.awt.Color(255, 255, 255));
        jlTituloJanela.setText("HISTÓRICO DO ALUNO");

        jlMatriculaAluno.setFont(new java.awt.Font("Trebuchet MS", 2, 16)); // NOI18N
        jlMatriculaAluno.setForeground(new java.awt.Color(229, 229, 229));
        jlMatriculaAluno.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jlNomeAluno.setFont(new java.awt.Font("Trebuchet MS", 0, 22)); // NOI18N
        jlNomeAluno.setForeground(new java.awt.Color(255, 255, 255));
        jlNomeAluno.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jlTituloJanela, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 292, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jlNomeAluno, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                    .addComponent(jlMatriculaAluno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jlTituloJanela, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jlMatriculaAluno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlNomeAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(98, 105, 144));
        jPanel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("DISCIPLINAS MATRICULADAS");

        jlDiaExtenso.setFont(new java.awt.Font("Trebuchet MS", 0, 26)); // NOI18N
        jlDiaExtenso.setForeground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBackground(new java.awt.Color(98, 105, 144));
        jScrollPane1.setBorder(null);

        tfDisciplinasMatriculadas.setEditable(false);
        tfDisciplinasMatriculadas.setBackground(new java.awt.Color(98, 105, 144));
        tfDisciplinasMatriculadas.setColumns(20);
        tfDisciplinasMatriculadas.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        tfDisciplinasMatriculadas.setRows(5);
        tfDisciplinasMatriculadas.setWrapStyleWord(true);
        tfDisciplinasMatriculadas.setBorder(null);
        tfDisciplinasMatriculadas.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        tfDisciplinasMatriculadas.setEnabled(false);
        jScrollPane1.setViewportView(tfDisciplinasMatriculadas);

        jlDataHora.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jlDataHora.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlDataHora, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                            .addComponent(jlDiaExtenso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jlDataHora, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlDiaExtenso, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, 370));

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Projeto/Public/icons/test.png"))); // NOI18N

        tfMediaGlobal.setFont(new java.awt.Font("Trebuchet MS", 0, 20)); // NOI18N
        tfMediaGlobal.setForeground(new java.awt.Color(98, 105, 144));
        tfMediaGlobal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel5.setBackground(new java.awt.Color(118, 118, 118));
        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Média Global");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfMediaGlobal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(tfMediaGlobal, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, -1, -1));

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Projeto/Public/icons/turma.png"))); // NOI18N

        tfNumeroDeTurmas.setFont(new java.awt.Font("Trebuchet MS", 0, 20)); // NOI18N
        tfNumeroDeTurmas.setForeground(new java.awt.Color(98, 105, 144));
        tfNumeroDeTurmas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel8.setBackground(new java.awt.Color(118, 118, 118));
        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Nº de Turmas");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfNumeroDeTurmas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(tfNumeroDeTurmas, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 10, -1, -1));

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel8.setBackground(new java.awt.Color(60, 141, 188));

        jLabel9.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("HISTÓRICO DE TURMAS");
        jLabel9.setToolTipText("");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tfHistoricoDeTurmas.setEditable(false);
        tfHistoricoDeTurmas.setBackground(new java.awt.Color(240, 240, 240));
        tfHistoricoDeTurmas.setColumns(20);
        tfHistoricoDeTurmas.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        tfHistoricoDeTurmas.setRows(5);
        tfHistoricoDeTurmas.setWrapStyleWord(true);
        tfHistoricoDeTurmas.setBorder(null);
        tfHistoricoDeTurmas.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        tfHistoricoDeTurmas.setEnabled(false);
        jScrollPane2.setViewportView(tfHistoricoDeTurmas);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 120, 580, 260));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel jlDataHora;
    private javax.swing.JLabel jlDiaExtenso;
    private javax.swing.JLabel jlMatriculaAluno;
    private javax.swing.JLabel jlNomeAluno;
    private javax.swing.JLabel jlTituloJanela;
    private javax.swing.JTextArea tfDisciplinasMatriculadas;
    private javax.swing.JTextArea tfHistoricoDeTurmas;
    private javax.swing.JLabel tfMediaGlobal;
    private javax.swing.JLabel tfNumeroDeTurmas;
    // End of variables declaration//GEN-END:variables

}
