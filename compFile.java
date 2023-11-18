import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

public class compFile {
    private String compFName;
    private int totalComps;
    private List<complaint> compList;

    public compFile(String compFName) {
        this.compFName = compFName;
        initList();
        this.totalComps = compList.size();
    }

	public int getTotalComps() {
        return totalComps;
    }
	


    private void initList() {
        compList = new ArrayList<>();
        File f = new File(compFName);
        if (f.exists()) {
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(new FileInputStream(compFName));
                while (true) {
                    compList.add((complaint) ois.readObject());
                }
            } catch (EOFException eof) {
                if (ois != null) {
                    try {
                        ois.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void addComp(complaint comp) {
        compList.add(comp);
        this.totalComps++;
    }

    public void addSoln(int cNo, String soln) throws Exception {
        addSoln(cNo, soln, false);
    }

    public void overwriteSoln(int cNo, String soln) {
        try {
            addSoln(cNo, soln, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // private void addSoln(int cNo, String soln, boolean overWrite) throws Exception {
	// 	complaint comp = getComp(cNo);
	// 	if (comp.getSoln().isEmpty() || overWrite) {
	// 		complaint newComp = new complaint(comp.getcNo(), comp.getDept(), comp.getComp(), soln,
	// 				comp.getPriority(), comp.getType());
	// 		remove(comp);
	// 		addComp(newComp);
	// 	} else if (!comp.getSoln().isEmpty()) {
	// 		throw new Exception("Solution Already Exists");
	// 	}
	// }
    private void addSoln(int cNo, String soln, boolean overWrite) throws Exception {
        complaint comp = getComp(cNo);
        if (comp.getSoln().isEmpty() || overWrite) {
            // Provide values for all fields when creating a new complaint instance
            complaint newComp = new complaint(
                    comp.getcNo(), 
                    comp.getDept(), 
                    comp.getComp(), 
                    soln,
                    comp.getPriority(), 
                    comp.getType(),
                    comp.getEmail(),
                    comp.getAddress());  // Assuming getEmail() is a method in your complaint class
            remove(comp);
            addComp(newComp);
        } else if (!comp.getSoln().isEmpty()) {
            throw new Exception("Solution Already Exists");
        }
    }
    

	public String getSoln(int cNo) {
		complaint comp = getComp(cNo);
		if (comp != null) {
			return comp.getSoln();
		}
		return null;
	}
	
	public boolean findComp(int cNo) {
		return getComp(cNo) != null;
	}
	
	public complaint getComp(int cNo) {
		for (complaint comp : compList) {
			if (comp.getcNo() == cNo) {
				return comp;
			}
		}
		return null;
	}
	
    private void remove(complaint compTbr) {
        compList.remove(compTbr);
    }

       public JTable returnTable() {
        JTable table;
        Object columnNames[] = { "C.No.", "Profession", "Complaint", "Solution", "Priority", "Type", "Address" };
        Object rowData[][] = new Object[totalComps][columnNames.length];
        int i = 0;
        for (complaint comp : compList) {
            rowData[i][0] = comp.getcNo();
            rowData[i][1] = comp.getDept();
            rowData[i][2] = comp.getComp();
            rowData[i][3] = comp.getSoln();
            rowData[i][4] = comp.getPriority();
            rowData[i][5] = comp.getType();
            rowData[i][6] = comp.getAddress(); // Include the address field
            ++i;
        }
        table = new JTable(rowData, columnNames);
        return table;
    }

    public void exit() {
        try {
            FileWriter fw = new FileWriter(compFName);
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(compFName));
            oos.flush();
            for (complaint comp : compList) {
                oos.writeObject(comp);
                oos.flush();
            }
            oos.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
