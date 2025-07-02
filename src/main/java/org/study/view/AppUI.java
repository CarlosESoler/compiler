/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.study.view;


import org.study.domain.ArchiveController;
import org.study.domain.CompilerController;
import org.study.domain.SemanticController;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class AppUI extends JFrame {
    
    ArchiveController compileArchiveController = new ArchiveController();
    ArchiveController compiledArchiveController = new ArchiveController();

    /**
     * Creates new form AppUI
     */
    public AppUI() {
        initComponents();

        callShortHandMethod(newButton, "Novo", KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK);
        callShortHandMethod(openArchiveButton, "Abrir", KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK);
        callShortHandMethod(saveButton, "Salvar", KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK);
        callShortHandMethod(compileButton, "Compilar", KeyEvent.VK_F7, 0);
        callShortHandMethod(teamButton, "Equipe", KeyEvent.VK_F1, 0);
    }
    
    private void compile() throws IOException {
        CompilerController compilerController = new CompilerController();
        clearMessageArea();
        String text = editorArea.getText();

        if (text.isBlank()) {
            messageArea.append("Nada para compilar!");
            return;
        }
        String compiledText = compilerController.compile(text);
        messageArea.setText(compiledText);
        if(!compiledText.equals("Programa compilado com sucesso")) {
            return;
        }
        compiledArchiveController.generateIlFile(compileArchiveController.getFile().getAbsolutePath(), String.valueOf(SemanticController.programText));
    }

    private void clearAllAreas() {
        clearMessageArea();
        clearEditorArea();
        clearStatusArea();
    }

    private void showTeam() {
        clearMessageArea();
        messageArea.append("Equipe: Carlos Eduardo Soler e Richard Natan");
    }

    private void openFile() {
        clearMessageArea();
        clearStatusArea();
        compileArchiveController.chooseFile(this);
        if(compileArchiveController.getFile() != null && compileArchiveController.getFile().exists()) {
            String fileText = compileArchiveController.readFile().replaceAll("\r", "");
            editorArea.setText(fileText);
            updateStatusBar(compileArchiveController.getFile().getAbsolutePath());
            return;
        }
        updateStatusBar("Selecione um arquivo!");
    }

    private void clearMessageArea() {
        messageArea.setText("");
    }
    
    private void clearEditorArea() {
        editorArea.setText("");
    }
    
    private void clearStatusArea() {
        statusArea.setText("");
    }

    private void updateStatusBar(String text) {
        statusArea.setText(text);
    }

    private void saveFile() {
        clearMessageArea();
        try {
            compileArchiveController.saveFile(editorArea.getText(), this);
            updateStatusBar(compileArchiveController.getFile().getAbsolutePath());
        } catch (Exception e) {
            updateStatusBar("Nenhum arquivo selecionado para salvar!\n");
        }
    }

    private void copy() {
        copy(editorArea.getText());
    }

    private void copy(String texto) {
        StringSelection selection = new StringSelection(texto);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, null);
    }

    private void paste() {
        editorArea.paste();
    }

    private void cut() {
        String selected = editorArea.getText();
        copy(selected);
        editorArea.setText(editorArea.getText().replace(selected, ""));
    }

    private void callShortHandMethod(JButton button, String action, int key, int modifiers) {
        InputMap inputMap = button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke(key, modifiers), action);

        Action abstractAction = new AbstractAction(action) {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if (button == newButton) {
                    newButtonActionPerformed(e);
                } 
                if (button == openArchiveButton) {
                    openArchiveButtonActionPerformed(e);
                }
                if (button == saveButton) {
                    saveButtonActionPerformed(e);
                }
                if (button == compileButton) {
                    compileButtonActionPerformed(e);
                } 
                if (button == teamButton) {
                    teamButtonActionPerformed(e);
                }
            }
        };

        button.getActionMap().put(action, abstractAction);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is alwaysZ
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new JPanel();
        statusArea = new JLabel();
        jSplitPane1 = new JSplitPane();
        jScrollPane1 = new JScrollPane();
        editorArea = new JTextArea();
        jScrollPane2 = new JScrollPane();
        messageArea = new JTextArea();
        toolsArea = new JPanel();
        newButton = new JButton();
        openArchiveButton = new JButton();
        saveButton = new JButton();
        copyButton = new JButton();
        pasteButton = new JButton();
        cutButton = new JButton();
        compileButton = new JButton();
        teamButton = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Compiladores 2025");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(1500, 800));
        setPreferredSize(new java.awt.Dimension(1500, 800));
        setResizable(false);

        jPanel1.setMaximumSize(new java.awt.Dimension(32767, 25));
        jPanel1.setMinimumSize(new java.awt.Dimension(900, 25));
        jPanel1.setPreferredSize(new java.awt.Dimension(1500, 25));

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(statusArea, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(statusArea, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        jSplitPane1.setDividerLocation(300);
        jSplitPane1.setOrientation(JSplitPane.VERTICAL_SPLIT);

        jScrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setViewportView(editorArea);

        editorArea.setColumns(20);
        editorArea.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        editorArea.setRows(5);
        editorArea.setBorder(new NumberedBorder());
        jScrollPane1.setViewportView(editorArea);

        jSplitPane1.setTopComponent(jScrollPane1);

        jScrollPane2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setWheelScrollingEnabled(false);

        messageArea.setEditable(false);
        messageArea.setColumns(20);
        messageArea.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        messageArea.setRows(5);
        messageArea.setDragEnabled(true);
        jScrollPane2.setViewportView(messageArea);

        jSplitPane1.setBottomComponent(jScrollPane2);

        toolsArea.setMinimumSize(new java.awt.Dimension(900, 70));
        toolsArea.setPreferredSize(new java.awt.Dimension(1500, 70));

        newButton.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        newButton.setIcon(new ImageIcon(getClass().getResource("/images/new-document.png"))); // NOI18N
        newButton.setText("Novo [ctrl+n]");
        newButton.setToolTipText("");
        newButton.setActionCommand("buttonNovo");
        newButton.setHorizontalTextPosition(SwingConstants.CENTER);
        newButton.setMaximumSize(new java.awt.Dimension(110, 49));
        newButton.setPreferredSize(new java.awt.Dimension(95, 49));
        newButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        newButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newButtonActionPerformed(evt);
            }
        });

        openArchiveButton.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        openArchiveButton.setIcon(new ImageIcon(getClass().getResource("/images/folder.png"))); // NOI18N
        openArchiveButton.setText("Abrir [ctrl+o]");
        openArchiveButton.setActionCommand("buttonAbrir");
        openArchiveButton.setHorizontalTextPosition(SwingConstants.CENTER);
        openArchiveButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        openArchiveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openArchiveButtonActionPerformed(evt);
            }
        });

        saveButton.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        saveButton.setIcon(new ImageIcon(getClass().getResource("/images/save.png"))); // NOI18N
        saveButton.setText("Salvar [ctrl+s]");
        saveButton.setActionCommand("butttonSalvar");
        saveButton.setHorizontalTextPosition(SwingConstants.CENTER);
        saveButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        copyButton.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        copyButton.setIcon(new ImageIcon(getClass().getResource("/images/copy.png"))); // NOI18N
        copyButton.setText("Copiar [ctrl+c]");
        copyButton.setToolTipText("");
        copyButton.setActionCommand("buttonCopiar");
        copyButton.setHorizontalTextPosition(SwingConstants.CENTER);
        copyButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        copyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyButtonActionPerformed(evt);
            }
        });

        pasteButton.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        pasteButton.setIcon(new ImageIcon(getClass().getResource("/images/paste-as-text.png"))); // NOI18N
        pasteButton.setText("Colar [ctrl+v]");
        pasteButton.setToolTipText("");
        pasteButton.setActionCommand("buttonColar");
        pasteButton.setHorizontalTextPosition(SwingConstants.CENTER);
        pasteButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        pasteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pasteButtonActionPerformed(evt);
            }
        });

        cutButton.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        cutButton.setIcon(new ImageIcon(getClass().getResource("/images/cut.png"))); // NOI18N
        cutButton.setText("Recortar [ctrl+x]");
        cutButton.setActionCommand("buttonRecortar");
        cutButton.setHorizontalTextPosition(SwingConstants.CENTER);
        cutButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        cutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cutButtonActionPerformed(evt);
            }
        });

        compileButton.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        compileButton.setIcon(new ImageIcon(getClass().getResource("/images/play.png"))); // NOI18N
        compileButton.setText("Compilar [F7]");
        compileButton.setActionCommand("buttonCompilar");
        compileButton.setHorizontalTextPosition(SwingConstants.CENTER);
        compileButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        compileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compileButtonActionPerformed(evt);
            }
        });

        teamButton.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        teamButton.setIcon(new ImageIcon(getClass().getResource("/images/team.png"))); // NOI18N
        teamButton.setText("Equipe [F1]");
        teamButton.setActionCommand("buttonEquipe");
        teamButton.setHorizontalTextPosition(SwingConstants.CENTER);
        teamButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        teamButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teamButtonActionPerformed(evt);
            }
        });

        GroupLayout toolsAreaLayout = new GroupLayout(toolsArea);
        toolsArea.setLayout(toolsAreaLayout);
        toolsAreaLayout.setHorizontalGroup(
            toolsAreaLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(toolsAreaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(newButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(2, 2, 2)
                .addComponent(openArchiveButton)
                .addGap(2, 2, 2)
                .addComponent(saveButton)
                .addGap(2, 2, 2)
                .addComponent(copyButton)
                .addGap(2, 2, 2)
                .addComponent(pasteButton)
                .addGap(2, 2, 2)
                .addComponent(cutButton)
                .addGap(2, 2, 2)
                .addComponent(compileButton)
                .addGap(2, 2, 2)
                .addComponent(teamButton)
                .addContainerGap(126, Short.MAX_VALUE))
        );
        toolsAreaLayout.setVerticalGroup(
            toolsAreaLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(toolsAreaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(toolsAreaLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(cutButton, GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                    .addComponent(pasteButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(openArchiveButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(saveButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(copyButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(compileButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(teamButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(newButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(toolsArea, GroupLayout.PREFERRED_SIZE, 910, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, 910, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(toolsArea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSplitPane1, GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void teamButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teamButtonActionPerformed
        showTeam();
    }//GEN-LAST:event_teamButtonActionPerformed

    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButtonActionPerformed
        compileArchiveController.setFile(null);
        clearAllAreas();
    }//GEN-LAST:event_newButtonActionPerformed

    private void compileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compileButtonActionPerformed
        try {
            compile();
        } catch (Exception e) {

        }
    }//GEN-LAST:event_compileButtonActionPerformed

    private void openArchiveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openArchiveButtonActionPerformed
        openFile();
    }//GEN-LAST:event_openArchiveButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        saveFile();
    }//GEN-LAST:event_saveButtonActionPerformed

    private void copyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyButtonActionPerformed
        copy();
    }//GEN-LAST:event_copyButtonActionPerformed

    private void pasteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pasteButtonActionPerformed
        paste();
    }//GEN-LAST:event_pasteButtonActionPerformed

    private void cutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cutButtonActionPerformed
        cut();
    }//GEN-LAST:event_cutButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AppUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AppUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AppUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AppUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AppUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton compileButton;
    private JButton copyButton;
    private JButton cutButton;
    private JTextArea editorArea;
    private JPanel jPanel1;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JSplitPane jSplitPane1;
    private JTextArea messageArea;
    private JButton newButton;
    private JButton openArchiveButton;
    private JButton pasteButton;
    private JButton saveButton;
    private JLabel statusArea;
    private JButton teamButton;
    private JPanel toolsArea;
    // End of variables declaration//GEN-END:variables
}
