/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import GUI.Dialog.TaskDialog;
import javax.swing.*;
import BackEnd.EventSystem.Committee;
import BackEnd.EventSystem.Task;
import BackEnd.ManagerSystem.MainManager;
import BackEnd.UserSystem.User;
import GUI.Dialog.NewTaskDialog;
/**
 *
 * @author Sid
 */
public class CommitteePanel extends javax.swing.JPanel {

    /**
     * Creates new form CommitteePanel
     */
    private MainManager manager;
    
    public CommitteePanel() {
        initComponents();
        manager = MainManager.getInstance();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        headerLabel = new javax.swing.JLabel();
        memberScrollPane = new javax.swing.JScrollPane();
        memberList = new javax.swing.JList();
        membersLabel = new javax.swing.JLabel();
        headLabel = new javax.swing.JLabel();
        headNameLabel = new javax.swing.JLabel();
        taskScrollPane = new javax.swing.JScrollPane();
        taskList = new javax.swing.JList();
        tasksLabel = new javax.swing.JLabel();
        taskProgressBar = new javax.swing.JProgressBar();
        budgetButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();
        addMemberButton = new javax.swing.JButton();
        removeMemberButton = new javax.swing.JButton();
        removeTaskButton = new javax.swing.JButton();
        addTaskButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        setMinimumSize(new java.awt.Dimension(387, 327));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        headerLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        headerLabel.setText("Committee Name");
        headerLabel.setPreferredSize(new java.awt.Dimension(200, 25));
        add(headerLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 22, -1, -1));

        memberList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        memberScrollPane.setViewportView(memberList);

        add(memberScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 97, 150));

        membersLabel.setText("Members");
        add(membersLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(109, 79, -1, -1));

        headLabel.setText("Head: ");
        add(headLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 57, -1, -1));

        headNameLabel.setText("committee head");
        add(headNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(48, 57, -1, -1));

        taskList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        taskList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                taskListMouseClicked(evt);
            }
        });
        taskScrollPane.setViewportView(taskList);

        add(taskScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, 97, 150));

        tasksLabel.setText("Tasks");
        add(tasksLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(257, 79, -1, -1));

        taskProgressBar.setOrientation(1);
        add(taskProgressBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 99, -1, -1));

        budgetButton.setText("Budget");
        budgetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                budgetButtonActionPerformed(evt);
            }
        });
        add(budgetButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 99, -1, -1));

        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });
        add(saveButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 293, -1, -1));

        closeButton.setText("Close");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });
        add(closeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(343, 293, -1, -1));

        addMemberButton.setText("+");
        addMemberButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addMemberButtonActionPerformed(evt);
            }
        });
        add(addMemberButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 250, -1, -1));

        removeMemberButton.setText("-");
        removeMemberButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeMemberButtonActionPerformed(evt);
            }
        });
        add(removeMemberButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 250, -1, -1));

        removeTaskButton.setText("-");
        removeTaskButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeTaskButtonActionPerformed(evt);
            }
        });
        add(removeTaskButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 250, -1, -1));

        addTaskButton.setText("+");
        addTaskButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addTaskButtonActionPerformed(evt);
            }
        });
        add(addTaskButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 250, -1, -1));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(224, 99, 20, 146));
    }// </editor-fold>//GEN-END:initComponents
 
    public void setCommittee(Committee c)
    {
        manager.getCommitteeManager().setSelectedCommittee(c);
        updateInfo();
    }
    
    private void updateInfo()
    {
        Committee c = manager.getCommitteeManager().getSelectedCommittee();
        
        headerLabel.setText(c.getTitle());
        
        if(c.getChair() != null){
            headNameLabel.setText(c.getChair().getFirstName() + " " + c.getChair().getLastName());
        }
        else{
            headNameLabel.setText("");
        }
            
        DefaultListModel tModel = new DefaultListModel();
        DefaultListModel mModel = new DefaultListModel();
        for(User m : c.getMemberList()){
            mModel.addElement(m.getFirstName() + " " + m.getLastName());
        }
        for(Task t : c.getTaskList()){
            tModel.addElement(t.getDescription());
        }
        taskList.setModel(tModel);
        memberList.setModel(mModel);
        
    }
    
    private void budgetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_budgetButtonActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "Not implemented yet.");
    }//GEN-LAST:event_budgetButtonActionPerformed

    private void taskListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_taskListMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount() == 2)
        {
            TaskDialog td = new TaskDialog((JFrame)SwingUtilities.windowForComponent(this), true, (String)taskList.getSelectedValue());
            td.setVisible(true);
        }
    }//GEN-LAST:event_taskListMouseClicked

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "Not implemented yet.");
    }//GEN-LAST:event_saveButtonActionPerformed

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "Not implemented yet.");
    }//GEN-LAST:event_closeButtonActionPerformed

    private void removeMemberButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeMemberButtonActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "Not implemented yet.");
    }//GEN-LAST:event_removeMemberButtonActionPerformed

    private void removeTaskButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeTaskButtonActionPerformed
        // TODO add your handling code here:
        try
        {
            manager.getCommitteeManager().getSelectedCommittee().getTaskList().remove(taskList.getSelectedIndex());
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        updateInfo();
    }//GEN-LAST:event_removeTaskButtonActionPerformed

    private void addMemberButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addMemberButtonActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "Not implemented yet.");
    }//GEN-LAST:event_addMemberButtonActionPerformed

    private void addTaskButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addTaskButtonActionPerformed
        // TODO add your handling code here:
        NewTaskDialog ntd = new NewTaskDialog((JFrame)SwingUtilities.windowForComponent(this), true);
        ntd.setVisible(true);
        if(ntd.getConfirm())
        {
            manager.getCommitteeManager().getSelectedCommittee().getTaskList().add(ntd.createTask());
        }
        updateInfo();
    }//GEN-LAST:event_addTaskButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addMemberButton;
    private javax.swing.JButton addTaskButton;
    private javax.swing.JButton budgetButton;
    private javax.swing.JButton closeButton;
    private javax.swing.JLabel headLabel;
    private javax.swing.JLabel headNameLabel;
    private javax.swing.JLabel headerLabel;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JList memberList;
    private javax.swing.JScrollPane memberScrollPane;
    private javax.swing.JLabel membersLabel;
    private javax.swing.JButton removeMemberButton;
    private javax.swing.JButton removeTaskButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JList taskList;
    private javax.swing.JProgressBar taskProgressBar;
    private javax.swing.JScrollPane taskScrollPane;
    private javax.swing.JLabel tasksLabel;
    // End of variables declaration//GEN-END:variables
}
