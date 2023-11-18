import java.io.Serializable;

@SuppressWarnings("serial")
public class complaint implements Serializable {
    private int cNo;
    private String dept;
    private String comp;
    private String soln;
    private int priority;
    private String type;
    private String email;
    private String address; // Added address field

    public complaint(int cNo, String dept, String comp, String soln, int priority, String type, String email, String address) {
        this.cNo = cNo;
        this.dept = dept;
        this.comp = comp;
        this.soln = soln;
        this.priority = priority;
        this.type = type;
        this.email = email;
        this.address = address; // Added line to initialize the address field
    }

    // Add getAddress method
    

    public int getcNo() {
        return cNo;
    }

    public String getDept() {
        return dept;
    }

    public String getComp() {
        return comp;
    }

    public String getSoln() {
        return soln;
    }

    public int getPriority() {
        return priority;
    }

    public String getType() {
        return type;
    }
    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Complaint No: " + cNo + "\nDepartment: " + dept + "\nComplaint: " + comp +
                "\nSolution: " + soln + "\nPriority: " + priority + "\nType: " + type ;
    }
}
