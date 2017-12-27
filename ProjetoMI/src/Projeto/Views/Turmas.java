/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Projeto.Views;

import Projeto.Classes.*;
import Projeto.Controllers.*;
import Projeto.Models.*;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Leonardo
 */
public class Turmas extends javax.swing.JInternalFrame {
        
    DisciplinaController disciplina;
    TurmaController turma;
    ProfessorController professor;
    AlunoTurmaController aluno_turma;
    
    String modo = "Criar";
 
    /** Creates new form jProfessores
     * @param disciplina 
     * @param turma 
     * @param professor 
     * @param aluno_turma */
    public Turmas(DisciplinaController disciplina, TurmaController turma, ProfessorController professor, AlunoTurmaController aluno_turma) {
        this.disciplina = disciplina;
        this.turma = turma;
        this.professor = professor;
        this.aluno_turma = aluno_turma;
        
        initComponents();
        carregaTabela();
        carregaCombo();
        controleCampos(false);
                
        jbEditar.setEnabled(false);
        jbRemover.setEnabled(false);
        jbSalvar.setEnabled(false);    
    }
    
    private void carregaTabela(){
        DefaultTableModel modelo = new DefaultTableModel(new Object[]{"ID", "Professor", "Disciplina", "Vagas"}, 0 ){
            @Override
            public boolean isCellEditable(int row, int column) { 
                return false;
            }
        };
        
        for( int i=0; i < turma.index().size(); i++){
            modelo.addRow(new Object[]{ 
                turma.index().get(i).getId(),
                professor.search(turma.index().get(i).getIdProfessor()).getNome(),
                disciplina.search(turma.index().get(i).getIdDisciplina()).getNome(),
                turma.index().get(i).getVagas(),
            });
        }
        TableTurma.setModel(modelo);
    }
    
    private void carregaCombo(){
        tfIdProfessor.setSelectedIndex(-1);
        tfIdDisciplina.setSelectedIndex(-1);
        
        for(int i = 0;i <disciplina.index().size(); i++ ){  
            String str = disciplina.index().get(i).getId() + ". " + disciplina.index().get(i).getNome();  
            tfIdDisciplina.addItem(str);  
        }
   
        for(int i = 0;i < professor.index().size(); i++ ){  
            String str = professor.index().get(i).getMatricula() + ". "+ professor.index().get(i).getNome();
            tfIdProfessor.addItem(str);  
        }
        
        tfIdProfessor.setSelectedIndex(-1);
        tfIdDisciplina.setSelectedIndex(-1);
    }
        
    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }
  
    private void limpaCampos(){
        tfId.setText("");
        tfIdProfessor.setSelectedIndex(-1);
        tfIdDisciplina.setSelectedIndex(-1);
        tfVagas.setText("");
    }
    
    private void controleCampos(boolean status){
        tfId.setEnabled(false);
        tfIdProfessor.setSelectedIndex(-1);
        tfIdProfessor.setEnabled(status);
        tfIdDisciplina.setSelectedIndex(-1);
        tfIdDisciplina.setEnabled(status);
        tfVagas.setEnabled(status);
    }
    
    private void cadastrarEditar(){
        if( !tfVagas.getText().equals("") && tfIdDisciplina.getSelectedItem() != null && tfIdProfessor.getSelectedItem() != null ){  
            
            int idProfessor = Integer.valueOf(String.valueOf(tfIdProfessor.getSelectedItem()).substring(0, 9));
            
            Integer[] dados = {
                Integer.valueOf(tfId.getText()),
                Integer.valueOf(String.valueOf(tfIdDisciplina.getSelectedItem()).substring(0, 3)),
                idProfessor,
                Integer.valueOf(tfVagas.getText()),
            };
            
            if( turma.numeroMaximoTurmasPorProfessor(idProfessor, RequisitosFuncionais.numeroMaximoTurmasPorProfessor) ){
                if(modo.equals("Criar")){
                    turma.store(dados);
                    Flash.success("Turma criada.");
                    controleCampos(false);
                    
                }else if(modo.equals("Editar")){
                    turma.update(dados, Integer.valueOf(TableTurma.getValueAt(TableTurma.getSelectedRow(), 0).toString()));    
                    Flash.success("Informações salvas.");
                    controleCampos(false);
                    
                    jbSalvar.setText("Criar Turma");
                }

                carregaTabela();  
                limpaCampos();
                controleCampos(false);

                jbSalvar.setEnabled(false);
                jbNovo.setEnabled(true);
                jbEditar.setEnabled(false);
                jbRemover.setEnabled(false);
            }else{
                Flash.error("Professor está no limite permitido de turmas.");
            }
        }else{
            Flash.error("Alguns campos não devem ficar vazios.");
        }
    }
     
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableTurma = new javax.swing.JTable();
        jbRecaregarTabela = new javax.swing.JButton();
        jbRemover = new javax.swing.JButton();
        jbEditar = new javax.swing.JButton();
        jbNovo = new javax.swing.JButton();
        tfVagas = new javax.swing.JTextField();
        jbSalvar = new javax.swing.JButton();
        jlDisciplina = new javax.swing.JLabel();
        tfIdDisciplina = new javax.swing.JComboBox<>();
        jlProfessor = new javax.swing.JLabel();
        tfIdProfessor = new javax.swing.JComboBox<>();
        jlNumeroTurma = new javax.swing.JLabel();
        jlVagas = new javax.swing.JLabel();
        tfId = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Turmas");
        setToolTipText("");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        TableTurma.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        TableTurma.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TableTurma.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableTurmaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TableTurma);

        jbRecaregarTabela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Projeto/Public/icons/refresh.png"))); // NOI18N
        jbRecaregarTabela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRecaregarTabelaActionPerformed(evt);
            }
        });

        jbRemover.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jbRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Projeto/Public/icons/substract.png"))); // NOI18N
        jbRemover.setText("Remover");
        jbRemover.setPreferredSize(new java.awt.Dimension(89, 25));
        jbRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRemoverActionPerformed(evt);
            }
        });

        jbEditar.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jbEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Projeto/Public/icons/edit.png"))); // NOI18N
        jbEditar.setText("Editar");
        jbEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEditarActionPerformed(evt);
            }
        });

        jbNovo.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jbNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Projeto/Public/icons/checked.png"))); // NOI18N
        jbNovo.setText("Novo");
        jbNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbNovoActionPerformed(evt);
            }
        });

        tfVagas.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        tfVagas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfVagasKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfVagasKeyTyped(evt);
            }
        });

        jbSalvar.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jbSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Projeto/Public/icons/add.png"))); // NOI18N
        jbSalvar.setText("Salvar");
        jbSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalvarActionPerformed(evt);
            }
        });

        jlDisciplina.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jlDisciplina.setText("Disciplina:");

        tfIdDisciplina.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N

        jlProfessor.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jlProfessor.setText("Professor:");

        tfIdProfessor.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N

        jlNumeroTurma.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jlNumeroTurma.setText("Nº da Turma:");

        jlVagas.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jlVagas.setText("Vagas:");

        tfId.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(33, 50, 86));

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Projeto/Public/images/turma.png"))); // NOI18N
        jLabel1.setText("  Turmas");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 856, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jlNumeroTurma)
                            .addComponent(jlDisciplina)
                            .addComponent(jlProfessor)
                            .addComponent(jlVagas))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfIdProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfIdDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfId, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfVagas, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jbNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jbRemover, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbEditar, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(345, 345, 345)
                        .addComponent(jbRecaregarTabela))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jbEditar, jbNovo, jbRemover, jbSalvar});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {tfId, tfIdDisciplina, tfIdProfessor, tfVagas});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tfId, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlNumeroTurma))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jlDisciplina)
                                    .addComponent(tfIdDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tfIdProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlProfessor))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tfVagas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlVagas))
                                .addGap(30, 30, 30)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jbNovo)
                                    .addComponent(jbEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jbSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jbRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jbRecaregarTabela)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49))))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {tfId, tfIdDisciplina, tfIdProfessor, tfVagas});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jbEditar, jbNovo, jbRemover, jbSalvar});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEditarActionPerformed
        int linha = TableTurma.getSelectedRow();
        
        if( linha>=0 && linha < turma.index().size()){
            controleCampos(true);
            limpaCampos();
            Turma t = turma.search(Integer.valueOf(TableTurma.getValueAt(linha, 0).toString()));
            tfId.setText( String.valueOf(t.getId()) );
            tfIdProfessor.setSelectedIndex(professor.posicao(t.getIdProfessor()));
            tfIdDisciplina.setSelectedIndex(disciplina.posicao(t.getIdDisciplina()));
            tfVagas.setText(String.valueOf(t.getVagas()));
            modo = "Editar"; 
            jbSalvar.setText("Modificar");
            
            jbSalvar.setEnabled(true);
            jbNovo.setEnabled(false);
            jbEditar.setEnabled(true);
            jbRemover.setEnabled(true);
        }
        
    }//GEN-LAST:event_jbEditarActionPerformed

    private void jbRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbRemoverActionPerformed
        int linha = TableTurma.getSelectedRow();
        if( linha>=0 && linha<turma.index().size() ){
            if (Flash.confirm("Deseja remover essa turma?", "warning") == 0) {
                if(!aluno_turma.verificaSeTurmaEstaVazia( Integer.valueOf(TableTurma.getValueAt(linha, 0).toString()) )){
                    turma.delete( Integer.valueOf(TableTurma.getValueAt(linha, 0).toString()) );
                    carregaTabela();
                    controleCampos(false);

                    jbSalvar.setEnabled(false);
                    jbNovo.setEnabled(true);
                    jbEditar.setEnabled(false);
                    jbRemover.setEnabled(false);
                    Flash.success("Turma removida.");
                }else{
                    Flash.error("A turma possui alunos, você não pode removê-la.");
                }
            }
        }
    }//GEN-LAST:event_jbRemoverActionPerformed

    private void jbNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbNovoActionPerformed
        jbSalvar.setEnabled(true);
        jbNovo.setEnabled(false);
        jbEditar.setEnabled(false);
        jbRemover.setEnabled(false);
        controleCampos(true);        
        jbSalvar.setText("Criar Turma");
                    
        limpaCampos();    
        modo = "Criar";
        tfId.setText( String.valueOf(Generator.getProximoId()) );
    }//GEN-LAST:event_jbNovoActionPerformed

    private void TableTurmaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableTurmaMouseClicked
        jbSalvar.setEnabled(false);
        jbNovo.setEnabled(true);
        jbEditar.setEnabled(true);
        jbRemover.setEnabled(true);
        limpaCampos();
        controleCampos(false);
    }//GEN-LAST:event_TableTurmaMouseClicked

    private void jbRecaregarTabelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbRecaregarTabelaActionPerformed
        carregaTabela();
    }//GEN-LAST:event_jbRecaregarTabelaActionPerformed

    private void jbSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalvarActionPerformed
        cadastrarEditar();        
    }//GEN-LAST:event_jbSalvarActionPerformed

    private void tfVagasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfVagasKeyTyped
        if(tfVagas.getText().length() > 2) evt.consume();
    }//GEN-LAST:event_tfVagasKeyTyped

    private void tfVagasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfVagasKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) cadastrarEditar();
    }//GEN-LAST:event_tfVagasKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TableTurma;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton jbEditar;
    private javax.swing.JButton jbNovo;
    private javax.swing.JButton jbRecaregarTabela;
    private javax.swing.JButton jbRemover;
    private javax.swing.JButton jbSalvar;
    private javax.swing.JLabel jlDisciplina;
    private javax.swing.JLabel jlNumeroTurma;
    private javax.swing.JLabel jlProfessor;
    private javax.swing.JLabel jlVagas;
    private javax.swing.JTextField tfId;
    private javax.swing.JComboBox<String> tfIdDisciplina;
    private javax.swing.JComboBox<String> tfIdProfessor;
    private javax.swing.JTextField tfVagas;
    // End of variables declaration//GEN-END:variables

}
