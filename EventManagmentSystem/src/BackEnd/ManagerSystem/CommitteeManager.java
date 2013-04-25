/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd.ManagerSystem;

import BackEnd.Committee;
import BackEnd.Task;
import BackEnd.User;

/**
 *
 * @author Julian Kuk
 */
public class CommitteeManager {
    private Committee selectedCommittee;
    
    public CommitteeManager(){
    }
    
    public void setSelectedCommittee(Committee selectedCommittee){
        this.selectedCommittee = selectedCommittee;
    }
    
    public Committee getSelectedCommittee(){
        return selectedCommittee;
    }
    
    public void editTitle(String title){
        if (PrivilegeManager.hasCommitteePrivilege()){
            selectedCommittee.setTitle(title);
            // write to database
        }
    }
    
    public void addMember(User member){
        if (PrivilegeManager.hasCommitteePrivilege()){
            selectedCommittee.getMemberList().add(member);
            // write to database
        }
    }
    
    public void removeMember(User member){
        if (PrivilegeManager.hasCommitteePrivilege()){
            selectedCommittee.getMemberList().remove(member);
            // write to database
        }
    }
    
    public void addTask(Task task){
        if (PrivilegeManager.hasCommitteePrivilege()){
            selectedCommittee.
    }
    
    public void removeTask(Task task){
    }
}
