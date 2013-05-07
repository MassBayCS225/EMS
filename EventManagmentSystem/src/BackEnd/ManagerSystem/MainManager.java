/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd.ManagerSystem;

import EMS_Database.DoesNotExistException;
import EMS_Database.impl.*;

/**
 * This class holds all the other Manager classes, to facilitate passing the
 * managers around the GUI.
 *
 * @author Julian Kuk
 */
public class MainManager {

    // private ObjectRebuilder objectRebuilder;
    private BudgetItemManager budgetItemManager;
    private BudgetManager budgetManager;
    private CommitteeManager committeeManager;
    private EventManager eventManager;
    private SubEventManager subEventManager;
    private TaskManager taskManager;
    private UserManager userManager;
    private LoginManager logInManager;

    public MainManager() {

        /*
         Committees_Table committees = new Committees_Table();
         Events_Table events = new Events_Table();
         Expense_Table expenses = new Expense_Table();
         Income_Table incomes = new Income_Table();
         SubEvent_Table subEvents = new SubEvent_Table();
         Tasks_Table tasks = new Tasks_Table();
         UserData_Table users = new UserData_Table();
         * */

        try {
            // objectRebuilder = new ObjectRebuilder();
            userManager = new UserManager();
            logInManager = new LoginManager(userManager.getUserList());
            taskManager = new TaskManager();
            subEventManager = new SubEventManager();
            committeeManager = new CommitteeManager(taskManager.getTasksTable());
            budgetItemManager = new BudgetItemManager();
            budgetManager = new BudgetManager(committeeManager.getCommitteesTable(), budgetItemManager.getIncomeTable(), budgetItemManager.getExpenseTable());

            eventManager = new EventManager(
                    userManager.getUserList(), userManager.getUsersTable(), taskManager.getTasksTable(),
                    subEventManager.getSubEventsTable(), committeeManager.getCommitteesTable(),
                    budgetItemManager.getIncomeTable(), budgetItemManager.getExpenseTable());

        } catch (DoesNotExistException e) {
            e.printStackTrace();
        }
    }

    private static class Main {

        public static MainManager instance = new MainManager();
    }

    public static MainManager getInstance() {
        return Main.instance;
    }

    public BudgetItemManager getBudgetItemManager() {
        return budgetItemManager;
    }

    public BudgetManager getBudgetManager() {
        return budgetManager;
    }

    public CommitteeManager getCommitteeManager() {
        return committeeManager;
    }

    public EventManager getEventManager() {
        return eventManager;
    }

    public SubEventManager getSubEventManager() {
        return subEventManager;
    }

    public TaskManager getTaskManager() {
        return taskManager;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public LoginManager getLogInManager() {
        return logInManager;
    }
}