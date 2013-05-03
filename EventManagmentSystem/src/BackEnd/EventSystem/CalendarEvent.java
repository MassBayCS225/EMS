/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd.EventSystem;

import java.util.ArrayList;

/**
 *
 * @author Karina
 */
public class CalendarEvent {
    
    int day;
    ArrayList<SubEvent> subEventList;
    
    public CalendarEvent(int d, ArrayList<SubEvent> sList)
    {
        setDay(d);
        setSubEventList(sList);
    }

    private void setDay(int d) {
        day = d;
    }

    private void setSubEventList(ArrayList<SubEvent> sList) {
        subEventList = sList;
    }
    
    private int getDay()
    {
        return day;
    }
    
    private ArrayList<SubEvent> getSubEventList()
    {
        return subEventList;
    }
    
    public String toString()
    {
        String events = "";
        if (subEventList.size() <= 3)
        {
            for (int i = 0; i < subEventList.size(); i++)
            {
                events += "/n" + subEventList.get(i).getDescription();
            }
        }
        else
        {
            for (int i = 0; i < 3; i++)
            {
                events += "/n" + subEventList.get(i).getDescription();
            }
            events += "\n...";
        }
        return "" + day + events;
    }
    
    
    
}
