package GUI;

import javax.swing.*;
import java.awt.*;


public class GUIInit implements Runnable{

        public void run() {

            JFrame frame = new JFrame("My Frame");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Get the screen size
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

            // Set the default size of the frame to half of the screen size
            int frameWidth = screenSize.width / 2;
            int frameHeight = screenSize.height / 2;
            frame.setSize(frameWidth, frameHeight);

            // Set the location of the frame to the center of the screen
            int frameX = (screenSize.width - frameWidth) / 2;
            int frameY = (screenSize.height - frameHeight) / 2;
            frame.setLocation(frameX, frameY);

            // Create three JPanel components for the three parts of the frame
            JPanel panel1 = new JPanel();
            JPanel panel2 = new JPanel();
            JPanel panel3 = new JPanel();

            // Set the layout managers for the three panels
            panel1.setLayout(new BorderLayout());
            panel2.setLayout(new BorderLayout());
            panel3.setLayout(new BorderLayout());

            // Set the background color for the three panels
            panel1.setBackground(Color.RED);
            panel2.setBackground(Color.GREEN);
            panel3.setBackground(Color.BLUE);

            // Add the three panels to the frame
            frame.add(panel1, BorderLayout.NORTH);
            frame.add(panel2, BorderLayout.CENTER);
            frame.add(panel3, BorderLayout.SOUTH);

            // Set the preferred sizes for the three panels
            panel1.setPreferredSize(new Dimension(frameWidth, frameHeight / 4));
            panel2.setPreferredSize(new Dimension(frameWidth, frameHeight / 2));
            panel3.setPreferredSize(new Dimension(frameWidth, frameHeight / 8));

            // Set the minimum size of the frame
            Dimension minimumSize = new Dimension(frameWidth / 2, frameHeight / 2);
            frame.setMinimumSize(minimumSize);

            frame.setResizable(true); // Allow frame to be resized by the user
            frame.pack(); // Pack the components in the frame
            frame.setVisible(true);
        }
    public static void main(String[] args) {
        // Create an instance of MyFrame and run it in a separate thread
        GUIInit myFrame = new GUIInit();
        Thread thread = new Thread(myFrame);
        thread.start();
    }
}
