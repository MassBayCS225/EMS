/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BackEnd.EventSystem.CalendarEvent;
import BackEnd.EventSystem.SubEvent;
import BackEnd.ManagerSystem.MainManager;
import GUI.Dialog.NewSubEventDialog;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;
import java.awt.event.MouseEvent;
import java.text.DateFormatSymbols;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

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
        detailsArea.setEditable(false);
        for (int i = 0; i < 7; i++)
        {
            CalendarTable.setRowHeight( i, 64 );
            CalendarTable.getColumnModel().getColumn(i).setCellRenderer(new TextTableRenderer());
        }
        
        CalendarTable.setCellSelectionEnabled(true);
    ListSelectionModel cellSelectionModel = CalendarTable.getSelectionModel();
    cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    
    CalendarTable.addMouseListener(new MouseAdapter()  
        {  
            public void mouseClicked(MouseEvent e)  
              {  
                if (e.getClickCount() == 1)  
                {  
                     String selectedData = null;  
                       
                    JTable target = (JTable)e.getSource();  
                    
                  int selectedRow = target.getSelectedRow();  
                   int selectedColumn = target.getSelectedColumn();   
                  
                 Object selectedCellValue=target.getValueAt(selectedRow, selectedColumn);  
                 detailsArea.setText(selectedCellValue.toString());
  }  
}  
});
    
            

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
                     int tempMonth;
                     for (int i = 0; i < manager.getEventManager().getSelectedEvent().getSubEventList().size(); i++){
                      tempMillis = manager.getEventManager().getSelectedEvent().getSubEventList().get(i).getTimeSchedule().getStartDateTimeCalendar().get(manager.getEventManager().getSelectedEvent().getSubEventList().get(i).getTimeSchedule().getStartDateTimeCalendar().DAY_OF_MONTH); // set the date for the current element
                      tempMonth = manager.getEventManager().getSelectedEvent().getSubEventList().get(i).getTimeSchedule().getStartDateTimeCalendar().get(manager.getEventManager().getSelectedEvent().getSubEventList().get(i).getTimeSchedule().getStartDateTimeCalendar().MONTH);
                      //System.out.println("Event Days: " + tempMillis);
                      // if you want to check end time, use getEndTime() instead of getStartTime
                      if (tempMillis == day && tempMonth == tempCalendar.get(tempCalendar.MONTH)){
                          //System.out.println("YES!");
                            events.add(manager.getEventManager().getSelectedEvent().getSubEventList().get(i));
                        tempCalendar.set(Calendar.DAY_OF_MONTH, tempCalendar.get(tempCalendar.DAY_OF_MONTH - 1)); // reset the day back to original
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
        monthLabel.setText("" + getMonthString(month));
        yearLabel.setText("" + year);
    }
    
    public void populateSubEvents()
    {
        ArrayList<SubEvent> subEventList = manager.getEventManager().getSelectedEvent().getSubEventList();
        subEventList.add(new SubEvent(12, "This is an event"));
    }
    
    public String getMonthString(int month) {
    return new DateFormatSymbols().getMonths()[month];
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
        jScrollPane2 = new javax.swing.JScrollPane();
        detailsArea = new javax.swing.JTextArea();
        addEventButton = new javax.swing.JButton();
        monthLabel = new javax.swing.JLabel();
        lastMonthButton = new javax.swing.JButton();
        nextMonthButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        yearLabel = new javax.swing.JLabel();

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

        detailsArea.setColumns(20);
        detailsArea.setLineWrap(true);
        detailsArea.setRows(5);
        jScrollPane2.setViewportView(detailsArea);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
        );

        addEventButton.setText("Add an Event");
        addEventButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addEventButtonActionPerformed(evt);
            }
        });

        monthLabel.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        monthLabel.setForeground(new java.awt.Color(255, 255, 255));
        monthLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        monthLabel.setText("Month");

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

        yearLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        yearLabel.setForeground(new java.awt.Color(255, 255, 255));
        yearLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        yearLabel.setText("year");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(addEventButton)
                        .addGap(200, 200, 200))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(19, 19, 19)
                                    .addComponent(lastMonthButton)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(yearLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(nextMonthButton)
                                    .addGap(12, 12, 12)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addComponent(monthLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(47, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(nextMonthButton))
                            .addComponent(lastMonthButton, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(monthLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(yearLabel)
                        .addGap(0, 4, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(addEventButton))
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
    }//GEN-LAST:event_lastMonthButtonActionPerformed

    private void nextMonthButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextMonthButtonActionPerformed
        if (month < 11)
            month++;
        else {
            year ++;
            month = 0;
        }
        populateCalendar();
    }//GEN-LAST:event_nextMonthButtonActionPerformed

    private void addEventButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addEventButtonActionPerformed
        // TODO add your handling code here:
        NewSubEventDialog nsed = new NewSubEventDialog((JFrame)SwingUtilities.windowForComponent(this), true);
        nsed.setVisible(true);
        if(nsed.getConfirm())
        {
            try
            {
                manager.getEventManager().createSubEvent(nsed.createEvent(), manager.getLogInManager().getLoggedInUser());
                populateCalendar();
            }
            catch (Exception e)
            {
                System.out.println("Can't create Sub Event");
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_addEventButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable CalendarTable;
    private javax.swing.JButton addEventButton;
    private javax.swing.JTextArea detailsArea;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton lastMonthButton;
    private javax.swing.JLabel monthLabel;
    private javax.swing.JButton nextMonthButton;
    private javax.swing.JLabel yearLabel;
    // End of variables declaration//GEN-END:variables


private class TextTableRenderer extends JTextArea implements TableCellRenderer {
public TextTableRenderer() {
setOpaque(true);
setLineWrap(true);
setWrapStyleWord(true);
}

public Component getTableCellRendererComponent(JTable table,
Object value, boolean isSelected, boolean hasFocus, int row,
int column) {

if (isSelected) {
setForeground(Color.BLACK);
setBackground(table.getSelectionBackground());
} else {
setForeground(Color.BLACK);
setBackground(table.getBackground());
}

setText((value == null)
? ""
: value.toString());
return this;
}
    }
}
