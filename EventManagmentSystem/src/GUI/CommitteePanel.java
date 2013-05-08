/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import GUI.Dialog.TaskDialog;
import javax.swing.*;
import BackEnd.EventSystem.Committee;
import BackEnd.EventSystem.Event;
import BackEnd.EventSystem.Task;
import BackEnd.EventSystem.TimeSchedule;
import BackEnd.ManagerSystem.MainManager;
import BackEnd.UserSystem.User;
import GUI.Dialog.BudgetDialog;
import GUI.Dialog.EmailExceptionDialog;
import GUI.Dialog.FindMemberDialog;
import GUI.Dialog.NewTaskDialog;
import java.util.Calendar;
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
        addMemberButton = new javax.swing.JButton();
        removeMemberButton = new javax.swing.JButton();
        removeTaskButton = new javax.swing.JButton();
        addTaskButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        committeeChangeButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(153, 204, 255));
        setMinimumSize(new java.awt.Dimension(387, 327));

        headerLabel.setFont(new java.awt.Font("Candara", 1, 24)); // NOI18N
        headerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        headerLabel.setText("Committee Name");
        headerLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        memberList.setFont(new java.awt.Font("Candara", 0, 12)); // NOI18N
        memberList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        memberScrollPane.setViewportView(memberList);

        membersLabel.setFont(new java.awt.Font("Candara", 0, 12)); // NOI18N
        membersLabel.setText("Members");

        headLabel.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        headLabel.setText("Head: ");

        headNameLabel.setFont(new java.awt.Font("Candara", 1, 16)); // NOI18N
        headNameLabel.setText("committee head");

        taskList.setFont(new java.awt.Font("Candara", 0, 12)); // NOI18N
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

        tasksLabel.setFont(new java.awt.Font("Candara", 0, 12)); // NOI18N
        tasksLabel.setText("Tasks");

        taskProgressBar.setOrientation(1);

        budgetButton.setFont(new java.awt.Font("Candara", 0, 11)); // NOI18N
        budgetButton.setText("View Budget");
        budgetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                budgetButtonActionPerformed(evt);
            }
        });

        addMemberButton.setFont(new java.awt.Font("Candara", 0, 11)); // NOI18N
        addMemberButton.setText("+");
        addMemberButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addMemberButtonActionPerformed(evt);
            }
        });

        removeMemberButton.setFont(new java.awt.Font("Candara", 0, 11)); // NOI18N
        removeMemberButton.setText("-");
        removeMemberButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeMemberButtonActionPerformed(evt);
            }
        });

        removeTaskButton.setFont(new java.awt.Font("Candara", 0, 11)); // NOI18N
        removeTaskButton.setText("-");
        removeTaskButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeTaskButtonActionPerformed(evt);
            }
        });

        addTaskButton.setFont(new java.awt.Font("Candara", 0, 11)); // NOI18N
        addTaskButton.setText("+");
        addTaskButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addTaskButtonActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        committeeChangeButton.setFont(new java.awt.Font("Candara", 0, 11)); // NOI18N
        committeeChangeButton.setText("change");
        committeeChangeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                committeeChangeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(headerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(membersLabel)
                                .addGap(227, 227, 227)
                                .addComponent(tasksLabel))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(memberScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(addMemberButton)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(removeMemberButton)))
                                    .addComponent(budgetButton))
                                .addGap(60, 60, 60)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(55, 55, 55)
                                            .addComponent(committeeChangeButton))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(headLabel)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(headNameLabel)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(40, 40, 40)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(taskScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(addTaskButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(removeTaskButton)))
                                        .addGap(10, 10, 10)
                                        .addComponent(taskProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(headerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(headLabel)
                    .addComponent(headNameLabel)
                    .addComponent(budgetButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(committeeChangeButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(membersLabel)
                    .addComponent(tasksLabel))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(memberScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(removeMemberButton)
                            .addComponent(addMemberButton)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(taskProgressBar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(taskScrollPane, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(removeTaskButton)
                            .addComponent(addTaskButton))))
                .addContainerGap(10, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
 
    public void setCommittee(Committee c)
    {
        manager.getCommitteeManager().setSelectedCommittee(c);
        updateInfo();
    }
    
    public void updateInfo()
    {
        Committee c = manager.getCommitteeManager().getSelectedCommittee();
        if(c.getTitle() != null)
        {
            headerLabel.setText(c.getTitle());
        }
        
        headNameLabel.setText(c.getChair().getFirstName() + " " + c.getChair().getLastName());
            
        DefaultListModel tModel = new DefaultListModel();
        DefaultListModel mModel = new DefaultListModel();
        for(User m : c.getMemberList()){
            mModel.addElement(m);
        }
        for(Task t : c.getTaskList()){
            tModel.addElement(t);
        }
        taskList.setModel(tModel);
        memberList.setModel(mModel);
        taskProgressBar.setValue(manager.getCommitteeManager().getSelectedCommittee().getCompletePercent());
    }
    
    private void budgetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_budgetButtonActionPerformed
        // TODO add your handling code here:
        manager.getBudgetManager().setSelectedBudget(manager.getCommitteeManager().getSelectedCommittee().getBudget());
        BudgetDialog bd = new BudgetDialog((JFrame)SwingUtilities.windowForComponent(this), true);
        bd.setVisible(true);
    }//GEN-LAST:event_budgetButtonActionPerformed

    private void taskListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_taskListMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount() == 2)
        {
            manager.getTaskManager().setSelectedTask(manager.getCommitteeManager().getSelectedCommittee().getTaskList().get(taskList.getMaxSelectionIndex()));
            TaskDialog td = new TaskDialog((JFrame)SwingUtilities.windowForComponent(this), true);
            td.setVisible(true);
            if(td.getConfirm())
            {
                //UPDATE ALL TASK INFO
                Task t = td.createTask();
                User u = manager.getLogInManager().getLoggedInUser();
                Event e = manager.getEventManager().getSelectedEvent();
                Committee c = manager.getCommitteeManager().getSelectedCommittee();
                try
                {
                    manager.getTaskManager().editCompleted(t.getCompleted(), u, e, c);
                    manager.getTaskManager().editTitle(t.getTitle(), u, e, c);
                    manager.getTaskManager().editDescription(t.getDescription(), u, e, c);
//                    for(User us : t.getResponsibleList())
//                    {
//                        manager.getTaskManager().addResponsible(us, u, e, c);
//                    }
                    TimeSchedule ts = t.getTimeSchedule();
                    int year = ts.getStartDateTimeCalendar().get(Calendar.YEAR);
                    int month = ts.getStartDateTimeCalendar().get(Calendar.MONTH)+1;
                    int day = ts.getStartDateTimeCalendar().get(Calendar.DAY_OF_MONTH);
                    int hour = ts.getStartDateTimeCalendar().get(Calendar.HOUR);
                    int minute = ts.getStartDateTimeCalendar().get(Calendar.MINUTE);
                    manager.getTaskManager().editStartDateTime(year, month, day, hour, minute, u, e, c);
                    year = ts.getEndDateTimeCalendar().get(Calendar.YEAR);
                    month = ts.getEndDateTimeCalendar().get(Calendar.MONTH)+1;
                    day = ts.getEndDateTimeCalendar().get(Calendar.DAY_OF_MONTH);
                    hour = ts.getEndDateTimeCalendar().get(Calendar.HOUR);
                    minute = ts.getEndDateTimeCalendar().get(Calendar.MINUTE);  
                    manager.getTaskManager().editEndDateTime(year, month, day, hour, minute, u, e, c);
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        updateInfo();
        }
        
    }//GEN-LAST:event_taskListMouseClicked

    private void removeMemberButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeMemberButtonActionPerformed
        // TODO add your handling code here:
        try
        {
        manager.getCommitteeManager().removeMember(manager.getCommitteeManager().getSelectedCommittee().getMemberList().get(memberList.getSelectedIndex()), manager.getUserManager().getSelectedUser(), manager.getEventManager().getSelectedEvent());
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        updateInfo();
    }//GEN-LAST:event_removeMemberButtonActionPerformed

    private void removeTaskButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeTaskButtonActionPerformed
        // TODO add your handling code here:
        try
        {
            manager.getCommitteeManager().getSelectedCommittee().getTaskList().remove(taskList.getSelectedIndex());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        updateInfo();
        if(taskList.getModel().getSize() >= 0)
        {
            taskList.setSelectedIndex(0);
        }
    }//GEN-LAST:event_removeTaskButtonActionPerformed

    private void addMemberButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addMemberButtonActionPerformed
        // TODO add your handling code here:
        FindMemberDialog fmd = new FindMemberDialog((JFrame)SwingUtilities.windowForComponent(this), true);
        fmd.setVisible(true);
        if(fmd.getConfirm())
        {
            try
            {
                manager.getCommitteeManager().addMember(fmd.createUser(), manager.getUserManager().getSelectedUser(), manager.getEventManager().getSelectedEvent());
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        updateInfo();
    }//GEN-LAST:event_addMemberButtonActionPerformed

    private void addTaskButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addTaskButtonActionPerformed
        // TODO add your handling code here:
        NewTaskDialog ntd = new NewTaskDialog((JFrame)SwingUtilities.windowForComponent(this), true);
        ntd.setVisible(true);
        if(ntd.getConfirm())
        {
            try
            {
                Task t = ntd.createTask();
                User u = manager.getLogInManager().getLoggedInUser();
                Event e = manager.getEventManager().getSelectedEvent();
                Committee c = manager.getCommitteeManager().getSelectedCommittee();
                manager.getTaskManager().setSelectedTask(manager.getCommitteeManager().createTask(t, u, e));
                manager.getTaskManager().editTitle(t.getTitle(), u, e, c);
                manager.getTaskManager().editCompleted(t.getCompleted(), u, e, c);
                manager.getTaskManager().editDescription(t.getDescription(), u, e, c);
                manager.getTaskManager().addResponsible(u, u, e, c);
                    TimeSchedule ts = t.getTimeSchedule();
                    int year = ts.getStartDateTimeCalendar().get(Calendar.YEAR);
                    int month = ts.getStartDateTimeCalendar().get(Calendar.MONTH)+1;
                    int day = ts.getStartDateTimeCalendar().get(Calendar.DAY_OF_MONTH);
                    int hour = ts.getStartDateTimeCalendar().get(Calendar.HOUR);
                    int minute = ts.getStartDateTimeCalendar().get(Calendar.MINUTE);
                    manager.getTaskManager().editStartDateTime(year, month, day, hour, minute, u, e, c);
                    year = ts.getEndDateTimeCalendar().get(Calendar.YEAR);
                    month = ts.getEndDateTimeCalendar().get(Calendar.MONTH)+1;
                    day = ts.getEndDateTimeCalendar().get(Calendar.DAY_OF_MONTH);
                    hour = ts.getEndDateTimeCalendar().get(Calendar.HOUR);
                    minute = ts.getEndDateTimeCalendar().get(Calendar.MINUTE);  
                    manager.getTaskManager().editEndDateTime(year, month, day, hour, minute, u, e, c);                
            }
            catch (Exception e)
            {
                System.out.println("Error in adding task to committee.");
                e.printStackTrace();
            }
        }
        updateInfo();
    }//GEN-LAST:event_addTaskButtonActionPerformed

    private void committeeChangeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_committeeChangeButtonActionPerformed
        // TODO add your handling code here:
        FindMemberDialog fmd = new FindMemberDialog((JFrame)SwingUtilities.windowForComponent(this), true);
        fmd.setVisible(true);
        if(fmd.getConfirm())
        {
            try
            {
            manager.getCommitteeManager().editChair(fmd.createUser(), manager.getLogInManager().getLoggedInUser(), manager.getEventManager().getSelectedEvent());
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        updateInfo();
    }//GEN-LAST:event_committeeChangeButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addMemberButton;
    private javax.swing.JButton addTaskButton;
    private javax.swing.JButton budgetButton;
    private javax.swing.JButton committeeChangeButton;
    private javax.swing.JLabel headLabel;
    private javax.swing.JLabel headNameLabel;
    private javax.swing.JLabel headerLabel;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JList memberList;
    private javax.swing.JScrollPane memberScrollPane;
    private javax.swing.JLabel membersLabel;
    private javax.swing.JButton removeMemberButton;
    private javax.swing.JButton removeTaskButton;
    private javax.swing.JList taskList;
    private javax.swing.JProgressBar taskProgressBar;
    private javax.swing.JScrollPane taskScrollPane;
    private javax.swing.JLabel tasksLabel;
    // End of variables declaration//GEN-END:variables
}
