/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd.ManagerSystem;

import BackEnd.Location;
import BackEnd.Task;
import BackEnd.User;

/**
 *
 * @author Julian Kuk
 */
public class TaskManager {
    
    private Task selectedTask;
    
    public TaskManager(){
    }
    
    public void setSelectedTask(Task selectedTask){
        this.selectedTask = selectedTask;
    }
    
    public Task getSelectedTask(){
        return selectedTask;
    }
    
    public void editDescription(String description){
        if (PrivilegeManager.hasTaskPrivilege()){
            selectedTask.setDescription(description);
            // write to database
        }
    }
    
    public void editDate(Calendar date){
        if (PrivilegeManager.hasTaskPrivilege()){
            selectedTask.setDate(date);
            // write to database
        }
    }
    
    public void editStartTime(Calendar startTime){
        if (PrivilegeManager.hasTaskPrivilege()){
            selectedTask.setStarTime(startTime);
            // write to database
        }
    }
    
    public void editEndTime(Calendar endTime){
        if (PrivilegeManager.hasTaskPrivilege()){
            selectedTask.setEndTime(endTime);
            // write to database
        }
    }
    
    public void editLocation(Location location){
        if (PrivilegeManager.hasTaskPrivilege()){
            selectedTask.setLocation(location);
            // write to database
        }
    }

    public void addResponsible(User responsible) {
        if (PrivilegeManager.hasTaskPrivilege()) {
            selectedTask.getResponsibleList().add(responsible);
            // write to database
        }
    }

    public void removeResponsible(User responsible){
        if (PrivilegeManager.hasTaskPrivilege()){
            selectedTask.getResponsibleList().remove(responsible);
            // write to database
        }
    }
    
    public void editCompleted(boolean completed){
        if (PrivilegeManager.hasTaskPrivilege()){
            selectedTask.setCompleted(completed);
            // write to database
        }
    }
}
