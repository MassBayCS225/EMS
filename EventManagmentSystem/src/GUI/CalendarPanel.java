/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BackEnd.EventSystem.CalendarEvent;
import BackEnd.EventSystem.SubEvent;
import BackEnd.ManagerSystem.MainManager;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Karina
 */
public class CalendarPanel extends javax.swing.JPanel {

    private Calendar tempCalendar = Calendar.getInstance(); // gets the current day and time
    private int month = Calendar.getInstance().get(Calendar.MONTH);
    private int year = Calendar.getInstance().get(Calendar.YEAR);
    private MainManager manager;
    /**
     * Creates new form CalendarPanel
     */
    public CalendarPanel() {
        initComponents();
        manager = MainManager.getInstance();
        CalendarTable.getTableHeader().setReorderingAllowed(false);
        populateCalendar();
    }
    
    public void populateCalendar() {
        System.out.println(month);
        System.out.println(year);
        tempCalendar.set(year, month, 1); // sets the current month to its first day
        tempCalendar.set(Calendar.HOUR_OF_DAY, 0);
        tempCalendar.set(Calendar.MINUTE, 0);
        tempCalendar.set(Calendar.SECOND, 0);
        tempCalendar.set(Calendar.MILLISECOND, 0);

        // tempCalendar.set(Calendar.MONTH, Calendar.MARCH); // set the month to the one you want, first param will stay the same, second is the month

        int maxWeeksInMonth = tempCalendar.getActualMaximum(tempCalendar.WEEK_OF_MONTH); // gets the max number of weeks in a month
        int maxDaysInWeek = tempCalendar.getActualMaximum(tempCalendar.DAY_OF_WEEK); // gets the max number of days in a week, which should be 7 regardless

        int firstDayInMonth = tempCalendar.get(tempCalendar.DAY_OF_WEEK); // gets the ordinal value of where the day falls in the week
        int maxDaysInMonth = tempCalendar.getActualMaximum(tempCalendar.DAY_OF_MONTH); // gets the max number of days in a month

        for (int weekOfMonth = 0, calendarSlot = 0, day = 1; weekOfMonth < 6; weekOfMonth++) { // weekOfMonth is also the row number
            //System.out.println("WEEK: " + weekOfMonth);
            for (int dayOfWeek = 0; dayOfWeek < maxDaysInWeek; dayOfWeek++) { // dayOfWeek is also the column number

                if ((weekOfMonth == 0 && dayOfWeek + 1 >= firstDayInMonth) || // if in week 0, check to make sure you add only if at the proper point in the week
                        (weekOfMonth > 0 && day <= maxDaysInMonth)){ // or if week is not 0, make sure you don't go over the max number of days

                    /**
                     * This is the algorithm for determining whether a
                     * scheduleItem falls on a certain day. It goes through all
                     * the elements of say, a sub event list. If you want it to
                     * check just the main event, then you don't need the for
                     * loop.
                     */
                    ArrayList<SubEvent> events = new ArrayList<SubEvent>();
                     int tempMillis; // temporary value used in for loop that saves a time in milliseconds
                     for (int i = 0; i < manager.getEventManager().getSelectedEvent().getSubEventList().size(); i++){
                      tempMillis = (int)manager.getEventManager().getSelectedEvent().getSubEventList().get(i).getTimeSchedule().getStartDateTimeTimestamp().getTime(); // set the date for the current element
                      // if you want to check end time, use getEndTime() instead of getStartTime
                      if (tempMillis >= tempCalendar.getTimeInMillis()){ // if the current element's time greater than or equal to the tempCalendar's
                        tempCalendar.set(Calendar.DAY_OF_MONTH, tempCalendar.DAY_OF_MONTH + 1); // move calendar +1 day
                        if (tempMillis < tempCalendar.getTimeInMillis()){ // if the current element's time is less than tempCalendar's
                          events.add(manager.getEventManager().getSelectedEvent().getSubEventList().get(i));
                        }
                        tempCalendar.set(Calendar.DAY_OF_MONTH, tempCalendar.DAY_OF_MONTH - 1); // reset the day back to original
                      }
                    }
                     
                     CalendarEvent cEvent = new CalendarEvent(day, events);
                    //System.out.println(tempCalendar);
                    tempCalendar.set(Calendar.DAY_OF_MONTH, tempCalendar.get(tempCalendar.DAY_OF_MONTH) + 1);
                    //String dayString = "" + day;
                    day++;
                    System.out.println(cEvent);
                    CalendarTable.setValueAt(cEvent, weekOfMonth, dayOfWeek);

                } else {
                    CalendarTable.setValueAt("-", weekOfMonth, dayOfWeek);
                }
                calendarSlot++;
            }
        }
    }
    
    public void populateSubEvents()
    {
        ArrayList<SubEvent> subEventList = manager.getEventManager().getSelectedEvent().getSubEventList();
        subEventList.add(new SubEvent(12, "This is an event"));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        CalendarTable = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int collIndex) {
                return false;
            }
        };
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        monthLabel = new javax.swing.JLabel();
        lastMonthButton = new javax.swing.JButton();
        nextMonthButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(153, 204, 255));

        CalendarTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        CalendarTable.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        CalendarTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(CalendarTable);
        CalendarTable.getColumnModel().getColumn(0).setMinWidth(48);
        CalendarTable.getColumnModel().getColumn(1).setMinWidth(48);
        CalendarTable.getColumnModel().getColumn(2).setMinWidth(48);
        CalendarTable.getColumnModel().getColumn(3).setMinWidth(48);
        CalendarTable.getColumnModel().getColumn(4).setMinWidth(48);
        CalendarTable.getColumnModel().getColumn(5).setMinWidth(48);
        CalendarTable.getColumnModel().getColumn(6).setMinWidth(48);
        CalendarTable.getColumnModel().getColumn(6).setPreferredWidth(48);
        CalendarTable.getColumnModel().getColumn(6).setMaxWidth(48);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jLabel3.setText("The details of Calendar");

        jLabel4.setText("days will show up here.");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addContainerGap(351, Short.MAX_VALUE))
        );

        jButton1.setText("Add an Event");

        monthLabel.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        monthLabel.setForeground(new java.awt.Color(255, 255, 255));
        monthLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        monthLabel.setText("April");

        lastMonthButton.setText("<");
        lastMonthButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastMonthButtonActionPerformed(evt);
            }
        });

        nextMonthButton.setText(">");
        nextMonthButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextMonthButtonActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Details");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(lastMonthButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(monthLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nextMonthButton)
                                .addGap(12, 12, 12)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(200, 200, 200)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(nextMonthButton))
                    .addComponent(monthLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lastMonthButton, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lastMonthButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastMonthButtonActionPerformed
        if (month > 0)
            month--;
        else {
            year --;
            month = 11;
        }
        populateCalendar();
        monthLabel.setText("" + (month+1));
    }//GEN-LAST:event_lastMonthButtonActionPerformed

    private void nextMonthButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextMonthButtonActionPerformed
        if (month < 11)
            month++;
        else {
            year ++;
            month = 0;
        }
        populateCalendar();
        monthLabel.setText("" + (month+1));
    }//GEN-LAST:event_nextMonthButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable CalendarTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton lastMonthButton;
    private javax.swing.JLabel monthLabel;
    private javax.swing.JButton nextMonthButton;
    // End of variables declaration//GEN-END:variables
}