/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BackEnd.ManagerSystem.MainManager;
import BackEnd.ManagerSystem.UserManager;
import BackEnd.UserSystem.IllegalCharacterException;
import BackEnd.UserSystem.PasswordMismatchError;
import BackEnd.UserSystem.PhoneNumber;
import BackEnd.UserSystem.User;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author sara
 */
public class Signup extends javax.swing.JPanel {

    UserManager userManager;
    JDialog parentDialog;
    /**
     * Creates new form Signup
     */
    public Signup(JDialog parentDialog) {
        initComponents();

        this.parentDialog = parentDialog;
        MainManager mainManager = MainManager.getInstance();
        userManager = mainManager.getUserManager();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        signinLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        phoneNumberLabel = new javax.swing.JLabel();
        firstNameField = new javax.swing.JTextField();
        lastNameField = new javax.swing.JTextField();
        emailLabel = new javax.swing.JLabel();
        emailField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        passwordField = new javax.swing.JTextField();
        reenterPasswordLabel = new javax.swing.JLabel();
        reenterPassWordField = new javax.swing.JTextField();
        phoneNumberField = new javax.swing.JTextField();
        addressLabel = new javax.swing.JLabel();
        streetField = new javax.swing.JTextField();
        cityField = new javax.swing.JTextField();
        stateField = new javax.swing.JTextField();
        countryField = new javax.swing.JTextField();
        zipcodeField = new javax.swing.JTextField();
        signupLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        signupButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(800, 600));

        signinLabel.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N
        signinLabel.setText("How would you like to sign in?");

        nameLabel.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N
        nameLabel.setText("Name");

        phoneNumberLabel.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N
        phoneNumberLabel.setText("Phone Number");

        firstNameField.setText("First");
        firstNameField.setPreferredSize(new java.awt.Dimension(380, 28));

        lastNameField.setText("Last");
        lastNameField.setPreferredSize(new java.awt.Dimension(360, 28));
        lastNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastNameFieldActionPerformed(evt);
            }
        });

        emailLabel.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N
        emailLabel.setText("Email");

        emailField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailFieldActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N
        jLabel7.setText("What is your information?");

        passwordLabel.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N
        passwordLabel.setText("Create a password");

        passwordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordFieldActionPerformed(evt);
            }
        });

        reenterPasswordLabel.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N
        reenterPasswordLabel.setText("Reenter password");

        reenterPassWordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reenterPassWordFieldActionPerformed(evt);
            }
        });

        phoneNumberField.setText("(XXX) XXX - XXXX");
        phoneNumberField.setPreferredSize(new java.awt.Dimension(380, 28));

        addressLabel.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N
        addressLabel.setText("Address");

        streetField.setText("Street");
        streetField.setPreferredSize(new java.awt.Dimension(380, 28));
        streetField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                streetFieldActionPerformed(evt);
            }
        });

        cityField.setText("City");
        cityField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cityFieldActionPerformed(evt);
            }
        });

        stateField.setText("State");

        countryField.setText("Country");
        countryField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                countryFieldActionPerformed(evt);
            }
        });

        zipcodeField.setText("Zip Code");

        signupLabel.setFont(new java.awt.Font("Candara", 1, 16)); // NOI18N
        signupLabel.setText("Sign Up");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        signupButton.setText("Sign up");
        signupButton.setActionCommand("signup");
        signupButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signupButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(signupLabel)
                    .add(jLabel7)
                    .add(nameLabel)
                    .add(phoneNumberLabel)
                    .add(addressLabel)
                    .add(layout.createSequentialGroup()
                        .add(cityField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 185, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(stateField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 77, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(zipcodeField))
                    .add(streetField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(phoneNumberField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(lastNameField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(firstNameField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(countryField))
                .add(18, 18, 18)
                .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 21, Short.MAX_VALUE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(signinLabel)
                            .add(emailLabel)
                            .add(passwordLabel)
                            .add(passwordField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                            .add(reenterPasswordLabel)
                            .add(reenterPassWordField))
                        .add(emailField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 350, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, signupButton))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(47, 47, 47)
                .add(signupLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel7)
                            .add(signinLabel))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, emailLabel)
                            .add(nameLabel))
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(emailField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 51, Short.MAX_VALUE)
                                .add(passwordLabel)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(passwordField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(reenterPasswordLabel)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(reenterPassWordField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                .add(signupButton)
                                .add(269, 269, 269))
                            .add(layout.createSequentialGroup()
                                .add(firstNameField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(lastNameField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(18, 18, 18)
                                .add(phoneNumberLabel)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(phoneNumberField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(18, 18, 18)
                                .add(addressLabel)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(streetField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                    .add(cityField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(stateField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(zipcodeField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(countryField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .add(layout.createSequentialGroup()
                        .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 318, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(0, 0, Short.MAX_VALUE))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lastNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastNameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lastNameFieldActionPerformed

    private void emailFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailFieldActionPerformed

    private void passwordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordFieldActionPerformed

    private void reenterPassWordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reenterPassWordFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_reenterPassWordFieldActionPerformed

    private void streetFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_streetFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_streetFieldActionPerformed

    private void cityFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cityFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cityFieldActionPerformed

    private void countryFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_countryFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_countryFieldActionPerformed

    private void signupButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signupButtonActionPerformed
        String message = "";
        
        if (firstNameField.getText().equals("") || firstNameField.getText().equals("First")){
            message += "\nMissing first name.";}
        
        if (lastNameField.getText().equals("") || lastNameField.getText().equals("Last")){
            message += "\nMissing last name.";}
        
        if (emailField.getText().equals("")){
            message += "\nMissing email address.";}
        
        if (passwordField.getText().equals("")){
            message += "\nMissing password.";}
        
        if (reenterPassWordField.getText().equals("")){
            message += "\nMissing reenter password.";}
        
        if(!passwordField.getText().equals(reenterPassWordField.getText())){
            message += "\nPasswords don't match.";}
        
        if(phoneNumberField.getText().equals("") || phoneNumberField.getText().equals("(XXX) XXX - XXXX")){
            message += "\nMissing phone number.";}    
        
        if(streetField.getText().equals("") || streetField.getText().equals("Street")){
            message += "\nMissing street.";}
        
        if(cityField.getText().equals("") || cityField.getText().equals("City")){
            message += "\nMissing city.";}
        
        if(stateField.getText().equals("") || stateField.getText().equals("State")){
            message += "\nMissing state.";}
        
        if(zipcodeField.getText().equals("") || zipcodeField.getText().equals("Zip Code")){
            message += "\nMissing zip code.";}
        
        if(countryField.getText().equals("") || countryField.getText().equals("Country")){
            message += "\nMissing country.";}
        
        if (!message.equals("")){
            JOptionPane.showMessageDialog(this, message);  
        }
        else{
        try {
            User newUser = new User(firstNameField.getText(),
                    lastNameField.getText(),
                    emailField.getText(),
                    passwordField.getText(),
                    reenterPassWordField.getText());
            newUser.getAddress().setStreet(streetField.getText());
            newUser.getAddress().setCity(cityField.getText());
            newUser.getAddress().setState(stateField.getText());
            newUser.getAddress().setZipCode(zipcodeField.getText());
            newUser.getAddress().setCountry(countryField.getText());
            newUser.setPhoneNumber(new PhoneNumber(phoneNumberField.getText()));  
            
            userManager.getUserList().add(userManager.createUser(newUser));
            parentDialog.dispose();
            
        } catch (PasswordMismatchError e) {
        } catch (IllegalCharacterException error) {
        }
        }
        

    }//GEN-LAST:event_signupButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addressLabel;
    private javax.swing.JTextField cityField;
    private javax.swing.JTextField countryField;
    private javax.swing.JTextField emailField;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JTextField firstNameField;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField lastNameField;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JTextField phoneNumberField;
    private javax.swing.JLabel phoneNumberLabel;
    private javax.swing.JTextField reenterPassWordField;
    private javax.swing.JLabel reenterPasswordLabel;
    private javax.swing.JLabel signinLabel;
    private javax.swing.JButton signupButton;
    private javax.swing.JLabel signupLabel;
    private javax.swing.JTextField stateField;
    private javax.swing.JTextField streetField;
    private javax.swing.JTextField zipcodeField;
    // End of variables declaration//GEN-END:variables
}
