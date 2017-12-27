/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projeto.Views;

import Projeto.Controllers.*;
import Projeto.Models.*;
import Projeto.Classes.*;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Leonardo
 */
public class MatriculaNotasAlunos extends javax.swing.JInternalFrame {

    TurmaController turma;
    AlunoTurmaController aluno_turma;
    DisciplinaController disciplina;
    ProfessorController professor;
    AlunoController aluno;
    HistoricoAlunoController historico_aluno;

    int idTurma, idAlunoTurma, tfMatriculaAluno;
    double nota1, nota2, nota3, media;

    /**
     * Creates new form jAlunos
     * @param controllers
     */
    public MatriculaNotasAlunos(Object[] controllers)  {
        this.disciplina = (DisciplinaController) controllers[0];
        this.turma = (TurmaController) controllers[1];
        this.aluno_turma = (AlunoTurmaController) controllers[2];
        this.professor = (ProfessorController) controllers[3];
        this.aluno = (AlunoController) controllers[4];
        this.historico_aluno = (HistoricoAlunoController) controllers[5];

        initComponents();
        carregaComboTurma();
        carregaTabela();
        setarAba(0);
        
    }

    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }
    
    private void carregaTabela() {
        DefaultTableModel modelo = new DefaultTableModel(new Object[]{"ID", "Nome do Aluno"}, 0){
            @Override
            public boolean isCellEditable(int row, int column) { 
                return false;
            }
        };

        for (int i = 0; i < aluno_turma.index().size(); i++) {
            if (idTurma == aluno_turma.index().get(i).getIdTurma()) {
                modelo.addRow(new Object[]{
                    aluno_turma.index().get(i).getId(),
                    aluno.search(aluno_turma.index().get(i).getMatriculaAluno()).getNome(),
                });
            }
        }
        TableAlunosTurma.setModel(modelo);
    }

    private void carregaTabelaNotasAluno() {

        DefaultTableModel modelo2 = new DefaultTableModel(new Object[]{"ID", "Nome do Aluno", "Nota1", "Nota2", "Nota3"}, 0){
            @Override
            public boolean isCellEditable(int row, int column) { 
                return false;
            }
        };

        for (int k = 0; k < aluno_turma.index().size(); k++) {
            for (int i = 0; i < historico_aluno.index().size(); i++) {
                if (aluno_turma.index().get(k).getId() == historico_aluno.index().get(i).getIdTurmaAluno()
                        && aluno_turma.index().get(k).getIdTurma() == idTurma
                        && containStatus(historico_aluno.index().get(i).getIdStatus()) ) {

                    modelo2.addRow(new Object[]{
                        historico_aluno.index().get(i).getIdTurmaAluno(),
                        aluno.search(aluno_turma.index().get(k).getMatriculaAluno()).getNome(),
                        Numeros.formatDecimal(historico_aluno.index().get(i).getNota1()),
                        Numeros.formatDecimal(historico_aluno.index().get(i).getNota2()),
                        Numeros.formatDecimal(historico_aluno.index().get(i).getNota3())
                    });
                }
            }
        }
        TableHistoricoAluno.setModel(modelo2);
    }
    
    private boolean containStatus(int elemento){  
        for (int i = 0; i < RequisitosFuncionais.situacaoAluno.length; i++) {
            if( RequisitosFuncionais.situacaoAluno[i].equals(String.valueOf(elemento)) ) 
                return true;            
        }
        return true;
    }
    
    private void carregaComboTurma() {
        tfComboTurmas.removeAllItems();
        tfComboTurmas.setSelectedIndex(-1);
        for (int i = 0; i < turma.index().size(); i++) {
            String str = turma.index().get(i).getId() + ". Turma { " + 
                disciplina.search(turma.index().get(i).getIdDisciplina()).getNome() + "; " +
                professor.search(turma.index().get(i).getIdProfessor()).getNome() + " }";
            tfComboTurmas.addItem(str);
        }
        tfComboTurmas.setSelectedIndex(-1);
    }

    private void carregaComboAlunos() {
        tfComboAlunos.removeAllItems();
        tfComboAlunos.setSelectedIndex(-1);
        for (int i = 0; i < aluno.index().size(); i++) {
            String str = aluno.index().get(i).getMatricula() + " : " + aluno.index().get(i).getNome();
            tfComboAlunos.addItem(str);
        }
        tfComboAlunos.setSelectedIndex(-1);
    }

    private void exibeDadosTurma() {
        tfDadosTurma.setText("");
        idTurma = turma.pegaIdTurma(String.valueOf(tfComboTurmas.getSelectedItem()));
        Turma t = turma.search(idTurma);
        tfDadosTurma.append("INFORMAÇÕES DA TURMA:\n");
        tfDadosTurma.append("Código: " + t.getId() + "\n");
        tfDadosTurma.append("Professor: " + professor.search((t.getIdProfessor())).getNome() + "\n");
        tfDadosTurma.append("Disciplina: " + disciplina.search(t.getIdDisciplina()).getNome() + "\n");
        tfDadosTurma.append("Vagas: " + t.getVagas());
    }

    /*
        VERIFICA SE AINDA HÁ VAGAS NA TURMA
     */
    public boolean verificaVagasTurma() {
        Turma t = turma.search(idTurma);
        if (t != null && t.getVagas() > 0) {
            jbMatricularAluno.setEnabled(true);
            jbMatricularAluno.setText("Matricular");
            return true;
        }

        jbMatricularAluno.setEnabled(false);
        jbMatricularAluno.setText("Turma sem vagas");
        return false;
    }

    private void setarAba(int aba) {
        switch (aba) {
            case 0: {
                AbasMatricularTurma.setEnabledAt(0, true);
                AbasMatricularTurma.setEnabledAt(1, false);
                AbasMatricularTurma.setEnabledAt(2, false);
                AbasMatricularTurma.setSelectedIndex(0);
                tfDadosTurma.setText("");
                tituloJanela("Selecione a Turma", "turma");
                carregaComboTurma();
                break;
            }
            case 1: {
                AbasMatricularTurma.setEnabledAt(0, false);
                AbasMatricularTurma.setEnabledAt(1, true);
                AbasMatricularTurma.setEnabledAt(2, false);
                AbasMatricularTurma.setSelectedIndex(1);
                tituloJanela("Matricular Aluno", "matricular-aluno");
                modoAmbiente("add");
                carregaComboAlunos();
                carregaTabela();
                verificaVagasTurma();
                exibeDadosTurma();
                break;
            }
            case 2: {
                exibeDadosTurma();
                AbasMatricularTurma.setEnabledAt(0, false);
                AbasMatricularTurma.setEnabledAt(1, false);
                AbasMatricularTurma.setEnabledAt(2, true);
                AbasMatricularTurma.setSelectedIndex(2);
                carregaTabelaNotasAluno();
                tfNota1.setText("");
                tfNota2.setText("");
                tfNota3.setText("");
                tituloJanela("Controle de Notas", "controle-notas");
                ambienteNotasAlunos();        
                break;
            }
        }
    }
    
    private void tituloJanela(String titulo, String imagem){
        jbTituloJanela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Projeto/Public/images/"+imagem+".png")));
        jbTituloJanela.setText(titulo);
    }
    
    public void ambienteNotasAlunos(){
        if(historico_aluno.verificaSeNotasEstaoFechadas(idAlunoTurma, aluno_turma, idTurma)){
            jbSalvarNotasHistoricoAluno.setText("Turma fechada.");
            jbSalvarNotasHistoricoAluno.setEnabled(false);
            turma.delete(idTurma);
            Flash.info("Turma fechada e notas adicionadas ao histórico individual.");
            setarAba(0);
        }else{
            jbSalvarNotasHistoricoAluno.setText("Salvar Notas."); 
            jbSalvarNotasHistoricoAluno.setEnabled(true);                    
        } 
    }
    
    public void modoAmbiente(String opcao) {
        switch (opcao) {
            case "delete": {
                jbDesmatricularAluno.setEnabled(true);
                jbMatricularAluno.setEnabled(false);
                tfComboAlunos.setEnabled(false);
                break;
            }
            case "add": {
                jbDesmatricularAluno.setEnabled(false);
                jbMatricularAluno.setEnabled(true);
                tfComboAlunos.setEnabled(true);
                break;
            }
        }
    }

    public void fecharNotasDesistente(int id) { 
        for (int i = 0; i < historico_aluno.index().size(); i++) {
            //System.out.println(id + " == " + aluno_turma.index().get(i).getIdTurma());
            if (id == historico_aluno.index().get(i).getIdTurmaAluno()) {
                nota1 = historico_aluno.index().get(i).getNota1();
                nota2 = historico_aluno.index().get(i).getNota2();
                nota3 = historico_aluno.index().get(i).getNota3();
                media = (nota1 + nota2 + nota3) / 3;
                Turma t = turma.search(idTurma);
                
                String[] dados = {
                    String.valueOf(id),
                    professor.search( t.getIdProfessor() ).getNome(),
                    disciplina.search( t.getIdDisciplina() ).getNome(),
                    String.valueOf(tfMatriculaAluno),
                    String.valueOf(idTurma),
                    String.valueOf(nota1),
                    String.valueOf(nota2),
                    String.valueOf(nota3),
                    String.valueOf(media),
                    String.valueOf(2) //Desistente
                };

                historico_aluno.update(dados, id);
                break;
            }
        }
    }

    private String nomeDisciplina(int id){
        for (int i = 0; i < turma.index().size(); i++) {
            if( id == turma.index().get(i).getId() )
                return disciplina.search(turma.index().get(i).getIdDisciplina()).getNome();
        }
        return "NOT FOUND";
    }
    
    private void fecharNotasAlunos(){
        if (tfNota1.getText() != null || tfNota2.getText() != null) {

            nota1 = Double.valueOf(tfNota1.getText().replace(",","."));
            nota2 = Double.valueOf(tfNota2.getText().replace(",","."));
            nota3 = (tfNota3.getText() == null || tfNota3.getText().isEmpty()) ? 0.0 : Double.valueOf(tfNota3.getText().replace(",","."));
            media = (nota1 + nota2 + nota3) / 3;
            int situacaoAluno = (media >= RequisitosFuncionais.mediaMinima) ? 0 : 1;
            Turma t = turma.search(idTurma);
            
            String[] dados = {
                String.valueOf(idAlunoTurma),
                professor.search( t.getIdProfessor() ).getNome(),
                disciplina.search( t.getIdDisciplina() ).getNome(),
                String.valueOf(tfMatriculaAluno),
                String.valueOf(idTurma),
                String.valueOf(nota1),
                String.valueOf(nota2),
                String.valueOf(nota3),
                String.valueOf(media),
                String.valueOf(situacaoAluno)
            };
                    
            historico_aluno.update(dados, idAlunoTurma);
            aluno_turma.delete(idAlunoTurma);
            setarAba(2);
            
        } else {
            Flash.error("Selecione um aluno para atribuir notas.");
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jbTituloJanela = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tfDadosTurma = new javax.swing.JTextArea();
        AbasMatricularTurma = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        tfComboTurmas = new javax.swing.JComboBox<>();
        jlMatricula2 = new javax.swing.JLabel();
        jbProsseguirParaMatricula = new javax.swing.JButton();
        jbEncerrarTurmaAba0 = new javax.swing.JButton();
        jbRemoverTurma = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jbMatricularAluno = new javax.swing.JButton();
        tfComboAlunos = new javax.swing.JComboBox<>();
        jlMatricula = new javax.swing.JLabel();
        jbDesmatricularAluno = new javax.swing.JButton();
        jbSelecionarTurmaAba1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        TableAlunosTurma = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jbSelecionarTurmaAba2 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        TableHistoricoAluno = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jbSalvarNotasHistoricoAluno = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tfNota1 = new javax.swing.JFormattedTextField();
        tfNota2 = new javax.swing.JFormattedTextField();
        tfNota3 = new javax.swing.JFormattedTextField();

        setClosable(true);
        setTitle("Matricular Alunos");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(82, 6, 121));

        jbTituloJanela.setFont(new java.awt.Font("Trebuchet MS", 0, 36)); // NOI18N
        jbTituloJanela.setForeground(new java.awt.Color(255, 255, 255));
        jbTituloJanela.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jbTituloJanela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Projeto/Public/images/turma.png"))); // NOI18N
        jbTituloJanela.setText("Matricular Alunos");

        tfDadosTurma.setEditable(false);
        tfDadosTurma.setBackground(new java.awt.Color(82, 6, 121));
        tfDadosTurma.setColumns(20);
        tfDadosTurma.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        tfDadosTurma.setForeground(new java.awt.Color(255, 255, 255));
        tfDadosTurma.setRows(5);
        tfDadosTurma.setWrapStyleWord(true);
        tfDadosTurma.setBorder(null);
        tfDadosTurma.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jScrollPane2.setViewportView(tfDadosTurma);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jbTituloJanela, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jbTituloJanela))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        AbasMatricularTurma.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        tfComboTurmas.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        tfComboTurmas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tfComboTurmasItemStateChanged(evt);
            }
        });

        jlMatricula2.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jlMatricula2.setText("Primeiro selecione uma turma:");

        jbProsseguirParaMatricula.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jbProsseguirParaMatricula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Projeto/Public/icons/select.png"))); // NOI18N
        jbProsseguirParaMatricula.setText("Matricular Alunos");
        jbProsseguirParaMatricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbProsseguirParaMatriculaActionPerformed(evt);
            }
        });

        jbEncerrarTurmaAba0.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jbEncerrarTurmaAba0.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Projeto/Public/icons/close.png"))); // NOI18N
        jbEncerrarTurmaAba0.setText("Encerrar Turma");
        jbEncerrarTurmaAba0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEncerrarTurmaAba0ActionPerformed(evt);
            }
        });

        jbRemoverTurma.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jbRemoverTurma.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Projeto/Public/icons/substract.png"))); // NOI18N
        jbRemoverTurma.setText("Remover");
        jbRemoverTurma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRemoverTurmaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jlMatricula2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(tfComboTurmas, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 127, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jbProsseguirParaMatricula, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                            .addComponent(jbEncerrarTurmaAba0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbRemoverTurma, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(98, 98, 98))))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jbEncerrarTurmaAba0, jbProsseguirParaMatricula, jbRemoverTurma});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(jbProsseguirParaMatricula)
                .addGap(1, 1, 1)
                .addComponent(jlMatricula2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbEncerrarTurmaAba0)
                    .addComponent(tfComboTurmas, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(jbRemoverTurma)
                .addContainerGap(120, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jbEncerrarTurmaAba0, jbProsseguirParaMatricula, jbRemoverTurma});

        AbasMatricularTurma.addTab("Selecionar Turma", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jbMatricularAluno.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jbMatricularAluno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Projeto/Public/icons/checked.png"))); // NOI18N
        jbMatricularAluno.setText("Matricular");
        jbMatricularAluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbMatricularAlunoActionPerformed(evt);
            }
        });

        tfComboAlunos.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        tfComboAlunos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tfComboAlunosItemStateChanged(evt);
            }
        });

        jlMatricula.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jlMatricula.setText("Selecione o Aluno:");

        jbDesmatricularAluno.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jbDesmatricularAluno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Projeto/Public/icons/substract.png"))); // NOI18N
        jbDesmatricularAluno.setText("Desmatricular");
        jbDesmatricularAluno.setPreferredSize(new java.awt.Dimension(89, 25));
        jbDesmatricularAluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbDesmatricularAlunoActionPerformed(evt);
            }
        });

        jbSelecionarTurmaAba1.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jbSelecionarTurmaAba1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Projeto/Public/icons/select-turm.png"))); // NOI18N
        jbSelecionarTurmaAba1.setText("Selecionar Turma");
        jbSelecionarTurmaAba1.setPreferredSize(new java.awt.Dimension(89, 25));
        jbSelecionarTurmaAba1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSelecionarTurmaAba1ActionPerformed(evt);
            }
        });

        TableAlunosTurma.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        TableAlunosTurma.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableAlunosTurma.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableAlunosTurmaMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(TableAlunosTurma);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jlMatricula)
                            .addComponent(tfComboAlunos, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jbMatricularAluno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jbDesmatricularAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(jbSelecionarTurmaAba1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jlMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfComboAlunos, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbDesmatricularAluno, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbMatricularAluno))
                        .addGap(18, 18, 18)
                        .addComponent(jbSelecionarTurmaAba1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(53, Short.MAX_VALUE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jbDesmatricularAluno, jbMatricularAluno});

        AbasMatricularTurma.addTab("Matricular Aluno", jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jbSelecionarTurmaAba2.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jbSelecionarTurmaAba2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Projeto/Public/icons/select-turm.png"))); // NOI18N
        jbSelecionarTurmaAba2.setText("Selecionar Turma");
        jbSelecionarTurmaAba2.setPreferredSize(new java.awt.Dimension(89, 25));
        jbSelecionarTurmaAba2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSelecionarTurmaAba2ActionPerformed(evt);
            }
        });

        TableHistoricoAluno.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        TableHistoricoAluno.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableHistoricoAluno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableHistoricoAlunoMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(TableHistoricoAluno);

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel2.setText("Primeira Nota:");

        jbSalvarNotasHistoricoAluno.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jbSalvarNotasHistoricoAluno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Projeto/Public/icons/checked.png"))); // NOI18N
        jbSalvarNotasHistoricoAluno.setText("Salvar");
        jbSalvarNotasHistoricoAluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalvarNotasHistoricoAlunoActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel3.setText("Segunda Nota:");

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel4.setText("Terceira Nota:");

        tfNota1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.0"))));
        tfNota1.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N

        tfNota2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.0"))));
        tfNota2.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N

        tfNota3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.0"))));
        tfNota3.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jbSalvarNotasHistoricoAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbSelecionarTurmaAba2, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfNota1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfNota2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfNota3))
                        .addGap(22, 22, 22))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfNota1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(tfNota2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(tfNota3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbSalvarNotasHistoricoAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbSelecionarTurmaAba2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(59, 59, 59))))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jbSalvarNotasHistoricoAluno, jbSelecionarTurmaAba2});

        AbasMatricularTurma.addTab("Notas dos Alunos", jPanel5);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(AbasMatricularTurma, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AbasMatricularTurma))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbProsseguirParaMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbProsseguirParaMatriculaActionPerformed
        if (tfComboTurmas.getSelectedItem() != null) {
            setarAba(1);
        }
    }//GEN-LAST:event_jbProsseguirParaMatriculaActionPerformed

    private void jbSelecionarTurmaAba1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSelecionarTurmaAba1ActionPerformed
        setarAba(0);
    }//GEN-LAST:event_jbSelecionarTurmaAba1ActionPerformed

    private void jbMatricularAlunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbMatricularAlunoActionPerformed
        if (tfComboAlunos.getSelectedItem() != null) {
            int idAT = Generator.getProximoId();
            tfMatriculaAluno = Integer.valueOf(String.valueOf(tfComboAlunos.getSelectedItem()).substring(0,9));
            
            Integer[] dados = {
                idAT,
                idTurma,
                tfMatriculaAluno
            };
                        
            // VERIFICA SE EXISTE VAGAS
            if (verificaVagasTurma()) {
                
                // VERIFICA SE O ALUNO JÁ ESTÁ MATRICULADO NA TURMA
                if (aluno_turma.buscaAluno(idTurma, tfMatriculaAluno)) {
                    
                    // VERIFICA A QUANTIDADE DE TURMAS DO ALUNO
                    if (aluno_turma.numeroMaximoTurmasPorAluno(tfMatriculaAluno, RequisitosFuncionais.numeroMaximoTurmasPorAluno)) {
                        
                        // VERIFICA SE O ALUNO JA CURSOU A DISCIPLINA E SE A MEDIA É INFERIOR A MEDIA MINIMA
                        if( historico_aluno.consultaHistoricoTurmaAluno(nomeDisciplina(idTurma), tfMatriculaAluno)){

                            aluno_turma.store(dados);
                            Flash.info("Aluno matriculado.");
                            tfComboAlunos.setSelectedIndex(-1);
                            turma.controleDeVagas(idTurma, "remove");
                            exibeDadosTurma();
                            carregaTabela();
                            verificaVagasTurma();
                            Turma t = turma.search(idTurma);

                            // CRIA UM REGISTRO DE HISTORICO DO ALUNO
                            String[] historico = {
                                String.valueOf(idAT), 
                                professor.search( t.getIdProfessor() ).getNome(),
                                disciplina.search( t.getIdDisciplina() ).getNome(),
                                String.valueOf(tfMatriculaAluno),
                                String.valueOf(idTurma), 
                                "0.0", "0.0", "0.0", "0.0", 
                                String.valueOf(3)
                            };
                            
                            historico_aluno.store(historico);

                        } else {
                            Flash.warning("Aluno já cursou esta disciplina e foi aprovado.");
                        }
                    } else {
                        Flash.error("Aluno já está no numero máximo de turmas.");
                    }
                } else {
                    Flash.error("Aluno já está matriculado na turma");
                }
            } else {
                Flash.error("Turma sem vagas.");
            }
        } else {
            Flash.error("Selecione um aluno.");
        }
    }//GEN-LAST:event_jbMatricularAlunoActionPerformed

    private void jbEncerrarTurmaAba0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEncerrarTurmaAba0ActionPerformed
        if (tfComboTurmas.getSelectedItem() != null) {
            if (aluno_turma.verificaSeTurmaEstaVazia(idTurma)) {
                if ( ! historico_aluno.verificaSeNotasEstaoFechadas(idAlunoTurma, aluno_turma, idTurma)) {
                    Flash.info("É necessário fechar todas as notas para encerrar esta turma.");
                    setarAba(2);
                }
            } else {
                Flash.error("Turma sem alunos.");
                setarAba(1);
            }
        } else {
            Flash.error("Selecione uma turma.");
        }
    }//GEN-LAST:event_jbEncerrarTurmaAba0ActionPerformed

    private void jbRemoverTurmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbRemoverTurmaActionPerformed
        if (tfComboTurmas.getSelectedItem() != null) {
            if (Flash.confirm("Deseja remover essa turma?", "warning") == 0) {
                if (!aluno_turma.verificaSeTurmaEstaVazia(idTurma)) {
                    turma.delete(turma.pegaIdTurma(String.valueOf(tfComboTurmas.getSelectedItem())));
                    Flash.info("Turma removida.");
                    tfComboTurmas.setSelectedIndex(-1);
                    carregaComboTurma();
                } else {
                    Flash.error("Você não pode remover essa turma. Ela possui alunos.");
                }
            }
        } else {
            Flash.error("Selecione uma turma.");
        }
    }//GEN-LAST:event_jbRemoverTurmaActionPerformed

    private void jbSelecionarTurmaAba2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSelecionarTurmaAba2ActionPerformed
        setarAba(0);
    }//GEN-LAST:event_jbSelecionarTurmaAba2ActionPerformed

    private void jbSalvarNotasHistoricoAlunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalvarNotasHistoricoAlunoActionPerformed
        fecharNotasAlunos();
    }//GEN-LAST:event_jbSalvarNotasHistoricoAlunoActionPerformed

    private void tfComboTurmasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tfComboTurmasItemStateChanged
        String str = String.valueOf(tfComboTurmas.getSelectedItem());
        if( tfComboTurmas.getSelectedIndex() != -1 )
            idTurma = turma.pegaIdTurma(str);
    }//GEN-LAST:event_tfComboTurmasItemStateChanged

    private void jbDesmatricularAlunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDesmatricularAlunoActionPerformed
        tfMatriculaAluno = aluno.index().get(aluno_turma.search(idAlunoTurma).getMatriculaAluno()).getMatricula();
        fecharNotasDesistente(idAlunoTurma);
        turma.controleDeVagas(idTurma, "add");
        aluno_turma.delete(idAlunoTurma);
        modoAmbiente("add");
        setarAba(1);
    }//GEN-LAST:event_jbDesmatricularAlunoActionPerformed

    private void TableAlunosTurmaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableAlunosTurmaMouseClicked
        int linha = TableAlunosTurma.getSelectedRow();
        modoAmbiente("delete");
        this.idAlunoTurma = Integer.valueOf(TableAlunosTurma.getValueAt(linha, 0).toString());
    }//GEN-LAST:event_TableAlunosTurmaMouseClicked

    private void TableHistoricoAlunoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableHistoricoAlunoMouseClicked
        int linha = TableHistoricoAluno.getSelectedRow();

        if (linha >= 0 && linha < historico_aluno.index().size()) {
            idAlunoTurma = Integer.valueOf(TableHistoricoAluno.getValueAt(linha, 0).toString());
            
            Projeto.Models.HistoricoAluno ha = historico_aluno.search(idAlunoTurma);
            tfNota1.setText(String.valueOf(ha.getNota1()));
            tfNota2.setText(String.valueOf(ha.getNota2()));
            tfNota3.setText(String.valueOf(ha.getNota3()));

            this.idAlunoTurma = Integer.valueOf(TableHistoricoAluno.getValueAt(linha, 0).toString());
            tfMatriculaAluno = ha.getIdAluno();
        }
    }//GEN-LAST:event_TableHistoricoAlunoMouseClicked

    private void tfComboAlunosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tfComboAlunosItemStateChanged
        if(tfComboAlunos.getSelectedIndex() != -1) 
            tfMatriculaAluno = Integer.valueOf(String.valueOf(tfComboAlunos.getSelectedItem()).substring(0,9));
    }//GEN-LAST:event_tfComboAlunosItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane AbasMatricularTurma;
    private javax.swing.JTable TableAlunosTurma;
    private javax.swing.JTable TableHistoricoAluno;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JButton jbDesmatricularAluno;
    private javax.swing.JButton jbEncerrarTurmaAba0;
    private javax.swing.JButton jbMatricularAluno;
    private javax.swing.JButton jbProsseguirParaMatricula;
    private javax.swing.JButton jbRemoverTurma;
    private javax.swing.JButton jbSalvarNotasHistoricoAluno;
    private javax.swing.JButton jbSelecionarTurmaAba1;
    private javax.swing.JButton jbSelecionarTurmaAba2;
    private javax.swing.JLabel jbTituloJanela;
    private javax.swing.JLabel jlMatricula;
    private javax.swing.JLabel jlMatricula2;
    private javax.swing.JComboBox<String> tfComboAlunos;
    private javax.swing.JComboBox<String> tfComboTurmas;
    private javax.swing.JTextArea tfDadosTurma;
    private javax.swing.JFormattedTextField tfNota1;
    private javax.swing.JFormattedTextField tfNota2;
    private javax.swing.JFormattedTextField tfNota3;
    // End of variables declaration//GEN-END:variables

}
