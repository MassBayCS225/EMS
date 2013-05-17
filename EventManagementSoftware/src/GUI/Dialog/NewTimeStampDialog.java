/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Dialog;

import BackEnd.EventSystem.TimeSchedule;
import javax.swing.DefaultComboBoxModel;
import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 *
 * @author Sid
 */


public class NewTimeStampDialog extends javax.swing.JDialog {

    /**
     * Creates new form NewTimeStampDialog
     */
    private boolean confirm;
    private TimeSchedule original;
    
    public NewTimeStampDialog(java.awt.Frame parent, boolean modal, TimeSchedule t) {
        super(parent, modal);
        initComponents();
        confirm = false;
        original = t;
        DefaultComboBoxModel yearModelS = new DefaultComboBoxModel();
        DefaultComboBoxModel dayModelS = new DefaultComboBoxModel();
        DefaultComboBoxModel minModelS = new DefaultComboBoxModel();
        DefaultComboBoxModel yearModelE = new DefaultComboBoxModel();
        DefaultComboBoxModel dayModelE = new DefaultComboBoxModel();
        DefaultComboBoxModel minModelE = new DefaultComboBoxModel();
        for(int i = 0; i < 32; i++)
        {
            if(i == 0)
            { 
                dayModelS.addElement("Day"); 
                dayModelE.addElement("Day");
            }
            else
            { 
                dayModelS.addElement(i); 
                dayModelE.addElement(i);
            }
        }
        yearModelS.addElement("Year");
        yearModelE.addElement("Year");
        for(int i = 2013; i < 2050; i++)
        {
            yearModelS.addElement(i);
            yearModelE.addElement(i);
        }
        for(int i = 0; i < 60; i++)
        {
            minModelS.addElement(i);
            minModelE.addElement(i);
        }
        startMinute.setModel(minModelS);
        endMinute.setModel(minModelE);
        startYear.setModel(yearModelS);
        endYear.setModel(yearModelE);
        startDay.setModel(dayModelS);
        endDay.setModel(dayModelE);
        setLocationRelativeTo(null);
        updateInfo(t);
    }
    
    public void updateInfo(TimeSchedule t)
    {
        Calendar start = t.getStartDateTimeCalendar();
        Calendar end = t.getEndDateTimeCalendar();
        startMinute.setSelectedIndex(start.get(Calendar.MINUTE));
        endMinute.setSelectedIndex(end.get(Calendar.MINUTE));
        if(start.get(Calendar.HOUR_OF_DAY) > 12)
        {
            int hour = start.get(Calendar.HOUR_OF_DAY) - 12;
            startHour.setSelectedIndex(hour-1);
            startPmRadioButton.setSelected(true);
        }
        else
        {
            startHour.setSelectedIndex(start.get(Calendar.HOUR_OF_DAY)-1);
            startAmRadioButton.setSelected(true);
        }
        if(end.get(Calendar.HOUR_OF_DAY) > 12)
        {
            int hour = end.get(Calendar.HOUR_OF_DAY) - 12;
            endHour.setSelectedIndex(hour-1);
            endPmRadioButton.setSelected(true);
        }
        else
        {
            endHour.setSelectedIndex(end.get(Calendar.HOUR_OF_DAY)-1);
            endAmRadioButton.setSelected(true);
        }
        startYear.setSelectedIndex(start.get(Calendar.YEAR)-2012);
        endYear.setSelectedIndex(end.get(Calendar.YEAR)-2012);
        startMonth.setSelectedIndex(start.get(Calendar.MONTH)+1);
        endMonth.setSelectedIndex(end.get(Calendar.MONTH)+1);
        startDay.setSelectedIndex(start.get(Calendar.DATE));
        endDay.setSelectedIndex(end.get(Calendar.DATE));
    }
    
    public boolean getConfirm()
    { return confirm; }
    
    public TimeSchedule createTimeSchedule()
    {
        TimeSchedule t = new TimeSchedule();
        int hour = startHour.getSelectedIndex();
        if(startPmRadioButton.isSelected())
        {
            hour += 12;
        }
        t.setStartDateTime(startYear.getSelectedIndex()+2012, startMonth.getSelectedIndex(), startDay.getSelectedIndex(), hour+1, startMinute.getSelectedIndex());
        hour = endHour.getSelectedIndex();
        if(endPmRadioButton.isSelected())
        {
            hour += 12;
        }
        t.setEndDateTime(endYear.getSelectedIndex()+2012, endMonth.getSelectedIndex(), endDay.getSelectedIndex(), hour+1, endMinute.getSelectedIndex());
        return t;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        endTimeAmPmButtonGroup = new javax.swing.ButtonGroup();
        startTimeAmPmButtonGroup = new javax.swing.ButtonGroup();
        startHour = new javax.swing.JComboBox();
        startMinute = new javax.swing.JComboBox();
        startYear = new javax.swing.JComboBox();
        startDay = new javax.swing.JComboBox();
        startMonth = new javax.swing.JComboBox();
        endHour = new javax.swing.JComboBox();
        endMinute = new javax.swing.JComboBox();
        endYear = new javax.swing.JComboBox();
        endDay = new javax.swing.JComboBox();
        endMonth = new javax.swing.JComboBox();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        saveButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();
        endPmRadioButton = new javax.swing.JRadioButton();
        endAmRadioButton = new javax.swing.JRadioButton();
        startPmRadioButton = new javax.swing.JRadioButton();
        startAmRadioButton = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        startHour.setFont(new java.awt.Font("Candara", 0, 11)); // NOI18N
        startHour.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

        startMinute.setFont(new java.awt.Font("Candara", 0, 11)); // NOI18N
        startMinute.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Minute" }));

        startYear.setFont(new java.awt.Font("Candara", 0, 11)); // NOI18N
        startYear.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Year" }));

        startDay.setFont(new java.awt.Font("Candara", 0, 11)); // NOI18N
        startDay.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Day" }));

        startMonth.setFont(new java.awt.Font("Candara", 0, 11)); // NOI18N
        startMonth.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Month", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));

        endHour.setFont(new java.awt.Font("Candara", 0, 11)); // NOI18N
        endHour.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

        endMinute.setFont(new java.awt.Font("Candara", 0, 11)); // NOI18N
        endMinute.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Minute" }));

        endYear.setFont(new java.awt.Font("Candara", 0, 11)); // NOI18N
        endYear.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Year" }));

        endDay.setFont(new java.awt.Font("Candara", 0, 11)); // NOI18N
        endDay.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Day" }));

        endMonth.setFont(new java.awt.Font("Candara", 0, 11)); // NOI18N
        endMonth.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Month", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel1.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        jLabel1.setText("Start Time");

        jLabel2.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        jLabel2.setText("End Time");

        jCheckBox1.setFont(new java.awt.Font("Candara", 0, 11)); // NOI18N
        jCheckBox1.setText("Use Current Time");

        saveButton.setFont(new java.awt.Font("Candara", 0, 11)); // NOI18N
        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        closeButton.setFont(new java.awt.Font("Candara", 0, 11)); // NOI18N
        closeButton.setText("Close");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        endTimeAmPmButtonGroup.add(endPmRadioButton);
        endPmRadioButton.setText("PM");

        endTimeAmPmButtonGroup.add(endAmRadioButton);
        endAmRadioButton.setText("AM");

        startTimeAmPmButtonGroup.add(startPmRadioButton);
        startPmRadioButton.setText("PM");

        startTimeAmPmButtonGroup.add(startAmRadioButton);
        startAmRadioButton.setText("AM");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(57, 57, 57)
                            .addComponent(startHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(startMinute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(startMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(startDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel1)
                                .addComponent(jCheckBox1, javax.swing.GroupLayout.Alignment.LEADING))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(startYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(saveButton)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(startAmRadioButton)
                        .addGap(2, 2, 2)
                        .addComponent(startPmRadioButton)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(endMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(endDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(endYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(closeButton))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(68, 68, 68)
                                        .addComponent(endHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(endMinute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(endAmRadioButton)
                                        .addGap(2, 2, 2)
                                        .addComponent(endPmRadioButton))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(54, 54, 54)
                                        .addComponent(jLabel2)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCheckBox1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(startMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(startDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(startYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(startMinute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(startHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(startPmRadioButton)
                                    .addComponent(startAmRadioButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(saveButton, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(closeButton, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(endMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(endDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(endYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(endHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(endMinute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(endAmRadioButton)
                                    .addComponent(endPmRadioButton))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        // TODO add your handling code here:
        if(startDay.getSelectedIndex() == 0 || endDay.getSelectedIndex() == 0)
        {
            JOptionPane.showMessageDialog(null, "Please select a day.", "No Day Selected", JOptionPane.ERROR_MESSAGE);
        }
        else if (startMonth.getSelectedIndex() == 0 || endMonth.getSelectedIndex() == 0)
        {
            JOptionPane.showMessageDialog(null, "Please select a month.", "No Month  Selected", JOptionPane.ERROR_MESSAGE);            
        }
        else if(startYear.getSelectedIndex() == 0 || endYear.getSelectedIndex() == 0)
        {
               JOptionPane.showMessageDialog(null, "Please select a year.", "No Year Selected", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            confirm = true;
            this.dispose();
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed

        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_closeButtonActionPerformed

    /**
     * @param args the command line arguments
     */
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JRadioButton endAmRadioButton;
    private javax.swing.JComboBox endDay;
    private javax.swing.JComboBox endHour;
    private javax.swing.JComboBox endMinute;
    private javax.swing.JComboBox endMonth;
    private javax.swing.JRadioButton endPmRadioButton;
    private javax.swing.ButtonGroup endTimeAmPmButtonGroup;
    private javax.swing.JComboBox endYear;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton saveButton;
    private javax.swing.JRadioButton startAmRadioButton;
    private javax.swing.JComboBox startDay;
    private javax.swing.JComboBox startHour;
    private javax.swing.JComboBox startMinute;
    private javax.swing.JComboBox startMonth;
    private javax.swing.JRadioButton startPmRadioButton;
    private javax.swing.ButtonGroup startTimeAmPmButtonGroup;
    private javax.swing.JComboBox startYear;
    // End of variables declaration//GEN-END:variables
}