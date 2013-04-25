/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd.ManagerSystem;

import BackEnd.Event;

/**
 *
 * @author Julian Kuk
 */
public class EventManager {

    private Event event;

    public EventManager() {
    }

    public void setSelectedEvent(Event selectedEvent) {
        this.selectedEvent = selectedEvent;
    }

    public Event getSelectedEvent() {
        return selectedEvent;
    }

    public void createEvent() {
        if (PrivilegeManager.hasEventCreationPrivilege()  {
            event = new Event();
            // write to database
        }
    }

    public void removeEvent() {
        if (PrivilegeManager.hasEventCreationPrivilege()  {
            event = new Event();
            // write to database
        }
    }
}
