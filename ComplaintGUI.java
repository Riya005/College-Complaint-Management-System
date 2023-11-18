//import javafx.scene.text.Font;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class ComplaintGUI implements ActionListener, WindowListener {
    private JFrame win;
    private compFile cfile;
    private JButton menuBtns[];
    private final String password = "implementer";

    public ComplaintGUI() {
        win = new JFrame();
        String tmpPath = System.getProperty("java.io.tmpdir");
        cfile = new compFile(tmpPath + "comps.txt");

        win.setTitle("Complaint Box");
        win.setSize(500, 600);
        win.addWindowListener(this);
        win.setLayout(new GridLayout(5, 1));

        menuBtns = new JButton[5];
        // for (int i = 0; i < menuBtns.length; ++i) {
        //     menuBtns[i] = new JButton();
		// 	menuBtns[i].setFont(new Font("Sans Serif", Font.BOLD, 16));
        //     win.add(menuBtns[i]);
        //     menuBtns[i].addActionListener(this);
        // }
        for (int i = 0; i < menuBtns.length; ++i) {
            final int buttonIndex = i; // Create a final variable
        
            menuBtns[i] = new JButton();
            menuBtns[i].setFont(new Font("Sans Serif", Font.BOLD, 16));
            menuBtns[i].setBackground(new Color(128, 223, 255)); // Set background color
            menuBtns[i].setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, new Color(0, 37, 51))); // Border width in pixels
        
            // Add hover effect using the final variable
            menuBtns[i].addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    menuBtns[buttonIndex].setBackground(new Color(0, 204, 255)); // Hover color
                }
        
                public void mouseExited(MouseEvent e) {
                    menuBtns[buttonIndex].setBackground(new Color(128, 223, 255)); // Default color
                }
            });
            win.add(menuBtns[i]);
            menuBtns[i].addActionListener(this);
        }
        
        menuBtns[0].setText("MAIN MENU");
        menuBtns[1].setText("1. Lodge Complaint");
        menuBtns[2].setText("2. Complaint Status");
        menuBtns[3].setText("3. Check Complaint Filed");
        menuBtns[4].setText("4. Report");
        menuBtns[0].setEnabled(false);
        win.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (menuBtns[1] == e.getSource()) {
            new compRegister(cfile);
        } else if (menuBtns[2] == e.getSource()) {
            new compStatus(cfile);
        } else if (menuBtns[3] == e.getSource()) {
            String pwdEntered = JOptionPane.showInputDialog(win, "Enter Password: ");
            if (pwdEntered == null) {
                // do nothing
            } else if (pwdEntered.equals(password)) {
                new compCheck(cfile);
            } else {
                JOptionPane.showMessageDialog(win, "Wrong password");
            }
        } else if (menuBtns[4] == e.getSource()) {
            new compReport(cfile);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void windowClosing(WindowEvent e) {
        win.dispose();
    }

    @Override
    public void windowClosed(WindowEvent e) {
        cfile.exit();
    }

    @Override
    public void windowIconified(WindowEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void windowActivated(WindowEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        // TODO Auto-generated method stub
    }
}
