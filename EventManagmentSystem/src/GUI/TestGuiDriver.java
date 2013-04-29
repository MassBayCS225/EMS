/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javax.swing.JFrame;

/**
 *
 * @author Karina
 */
public class TestGuiDriver {
    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        Main main = new Main();
        frame.add(main);
        frame.pack();
        frame.setVisible(true);
    }
}
