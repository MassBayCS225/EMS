/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import BackEnd.EventSystem.Event;
import BackEnd.ManagerSystem.EventManager;
import BackEnd.ManagerSystem.MainManager;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultEditorKit;
import javax.swing.undo.UndoManager;

/**
 *
 * @author Sid
 */
public class Home extends javax.swing.JFrame {

    
    /**
     * Creates new form Home
     */
    private UndoManager undo = new UndoManager();
    private MainManager manager;
    
    /**
     *
     */
    public Home() {
        initComponents();
        manager = MainManager.getInstance();
        //main1.setEventManager(manager);
    }
    public void setEvent(Event e){
        manager.getEventManager().setSelectedEvent(e);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        emsMenuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        printMenuItem = new javax.swing.JMenuItem();
        printPreviewMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        saveMenuItem = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        cutMenuItem = new javax.swing.JMenuItem(new DefaultEditorKit.CutAction());
        copyMenuItem = new javax.swing.JMenuItem(new DefaultEditorKit.CopyAction());
        pasteMenuItem = new javax.swing.JMenuItem(new DefaultEditorKit.PasteAction());
        toolsMenu = new javax.swing.JMenu();
        reportsMenu = new javax.swing.JMenu();
        committeeReportsMenuItem = new javax.swing.JMenuItem();
        registrationReportsMenuItem = new javax.swing.JMenuItem();
        budgetReportsMenuItem = new javax.swing.JMenuItem();
        databaseMenu = new javax.swing.JMenu();
        helpMenu = new javax.swing.JMenu();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fileMenu.setText("File");

        printMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        printMenuItem.setText("Print");
        printMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(printMenuItem);

        printPreviewMenuItem.setText("Print Preview");
        printPreviewMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printPreviewMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(printPreviewMenuItem);
        fileMenu.add(jSeparator1);

        saveMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        saveMenuItem.setText("Save");
        saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveMenuItem);
        fileMenu.add(jSeparator2);

        exitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        emsMenuBar.add(fileMenu);

        editMenu.setText("Edit");

        cutMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        cutMenuItem.setText("Cut");
        editMenu.add(cutMenuItem);

        copyMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        copyMenuItem.setText("Copy");
        editMenu.add(copyMenuItem);

        pasteMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        pasteMenuItem.setText("Paste");
        editMenu.add(pasteMenuItem);

        emsMenuBar.add(editMenu);

        toolsMenu.setText("Tools");

        reportsMenu.setText("Reports");

        committeeReportsMenuItem.setText("Committee");
        committeeReportsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                committeeReportsMenuItemActionPerformed(evt);
            }
        });
        reportsMenu.add(committeeReportsMenuItem);

        registrationReportsMenuItem.setText("Registration");
        registrationReportsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrationReportsMenuItemActionPerformed(evt);
            }
        });
        reportsMenu.add(registrationReportsMenuItem);

        budgetReportsMenuItem.setText("Budget");
        budgetReportsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                budgetReportsMenuItemActionPerformed(evt);
            }
        });
        reportsMenu.add(budgetReportsMenuItem);

        toolsMenu.add(reportsMenu);

        databaseMenu.setText("Database");
        toolsMenu.add(databaseMenu);

        emsMenuBar.add(toolsMenu);

        helpMenu.setText("Help");

        aboutMenuItem.setText("About");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(aboutMenuItem);

        emsMenuBar.add(helpMenu);

        setJMenuBar(emsMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void saveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMenuItemActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "Can't save yet!");
    }//GEN-LAST:event_saveMenuItemActionPerformed

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "Ask Julian...");
    }//GEN-LAST:event_aboutMenuItemActionPerformed

    private void registrationReportsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrationReportsMenuItemActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "Registration reports would be here");
    }//GEN-LAST:event_registrationReportsMenuItemActionPerformed

    private void printPreviewMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printPreviewMenuItemActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "You're still checking these?.");
    }//GEN-LAST:event_printPreviewMenuItemActionPerformed

    private void printMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printMenuItemActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "Not implemented yet");
    }//GEN-LAST:event_printMenuItemActionPerformed

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "You're stuck forever!");
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void committeeReportsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_committeeReportsMenuItemActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "Committee reports would be here if they existed...");
    }//GEN-LAST:event_committeeReportsMenuItemActionPerformed

    private void budgetReportsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_budgetReportsMenuItemActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "Why does saying budget report make me giggle?");
    }//GEN-LAST:event_budgetReportsMenuItemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem budgetReportsMenuItem;
    private javax.swing.JMenuItem committeeReportsMenuItem;
    private javax.swing.JMenuItem copyMenuItem;
    private javax.swing.JMenuItem cutMenuItem;
    private javax.swing.JMenu databaseMenu;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuBar emsMenuBar;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuItem pasteMenuItem;
    private javax.swing.JMenuItem printMenuItem;
    private javax.swing.JMenuItem printPreviewMenuItem;
    private javax.swing.JMenuItem registrationReportsMenuItem;
    private javax.swing.JMenu reportsMenu;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JMenu toolsMenu;
    // End of variables declaration//GEN-END:variables
}
