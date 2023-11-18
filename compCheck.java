import java.awt.GridLayout;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class compCheck implements ActionListener {
    private JDialog win;
    private compFile cfile;
    private JTextArea taSoln;
    private JTextField tfCompNum;
    private JButton submitBtn;

    public compCheck(compFile cfile) {
        this.cfile = cfile;
        win = new JDialog();
        win.setModalityType(ModalityType.APPLICATION_MODAL);
        win.setTitle("Complaints Filed");
        win.setSize(500, 500);
        win.setLayout(new GridLayout(2, 1));

        JTable tableAllComps = cfile.returnTable();
        tableAllComps.setEnabled(false);

        tfCompNum = new JTextField(40);

        taSoln = new JTextArea(7, 40);

        submitBtn = new JButton("Submit");
        submitBtn.addActionListener(this);

        JPanel panel = new JPanel();
        panel.add(new JLabel("Complaint No. "));
        panel.add(tfCompNum);
        panel.add(new JLabel("Solution "));
        panel.add(new JScrollPane(taSoln));
        panel.add(submitBtn);

        win.add(new JScrollPane(tableAllComps));
        win.add(panel);

        win.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (submitBtn == (JButton) e.getSource()) {
            boolean flag = true;
            int cno = 0;
            String sol = taSoln.getText();
            try {
                cno = Integer.parseInt(tfCompNum.getText());
            } catch (Exception exc) {
                flag = false;
                JOptionPane.showMessageDialog(null, "Invalid Complaint No");
            }
            if (flag) {
                if (!cfile.findComp(cno)) {
                    JOptionPane.showMessageDialog(null, "No Complaint exists for the given Complaint No.");
                } else if (sol.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Solution can't be empty");
                } else {
                    // Everything is right
                    try {
                        // You need to modify this part to include the "Type" and "Priority" parameters
                        cfile.addSoln(cno, sol);
                        JOptionPane.showMessageDialog(null, "Successfully Added");
                    } catch (Exception e1) {
                        int opt = JOptionPane.showConfirmDialog(null, "Solution Already Exists. Overwrite?");
                        if (opt == JOptionPane.YES_OPTION) {
                            // You need to modify this part to include the "Type" and "Priority" parameters
                            cfile.overwriteSoln(cno, sol);
                            JOptionPane.showMessageDialog(null, "Successfully Overwritten");
                        }
                    }
                }
            }
            if (flag) {
                win.dispose();
            }
        }
    }
}
