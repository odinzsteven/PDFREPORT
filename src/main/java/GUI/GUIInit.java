package GUI;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUIInit implements Runnable{

        public void run() {
            // Create and configure the frame on the event dispatch thread
            JFrame frame = new JFrame("My Frame");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    System.exit(0); // Exit the application
                }
            });
            frame.setSize(400, 300);
            frame.setVisible(true);
        }
}
