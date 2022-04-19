package presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

/** UI for the operations on the Client table
 * @author Cosarca Ioan-Cristian */
public class ViewClient extends JFrame {
    private JLabel StudentName;
    protected JTextField SName;
    private JLabel StudentAddress;
    protected JTextField Address;
    private JLabel StudentEmail;
    protected JTextField Email;
    private JLabel StudentAge;
    protected JTextField Age;
    private JLabel StudentID;
    protected JTextField SID;
    protected JButton StudentInsert;
    protected JButton StudentUpdate;
    protected JButton StudentDelete;
    protected JButton StudentFindName;
    protected JButton StudentFindID;
    protected JButton StudentFindAll;
    protected JButton GoBack;

    /** Sets the view parameters and adds the required buttons. */
    public ViewClient()
    {
        getContentPane().setBounds(0,0,600,500);
        this.setSize(1200, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Client Operations");
        getContentPane().setLayout(null);

        Font FontBtn    = new Font("", Font.PLAIN,18);

        StudentName     = new JLabel("Name");
        SName           = new JTextField();
        StudentAddress  = new JLabel("Address");
        Address         = new JTextField();
        StudentEmail    = new JLabel("Email");
        Email           = new JTextField();
        StudentAge      = new JLabel("Age");
        Age             = new JTextField();
        StudentID       = new JLabel("ID");
        SID             = new JTextField();
        StudentInsert   = new JButton("INSERT");
        StudentUpdate   = new JButton("UPDATE");
        StudentDelete   = new JButton("DELETE");
        StudentFindName = new JButton("FIND NAME");
        StudentFindID   = new JButton("FIND ID");
        StudentFindAll  = new JButton("VIEW ALL");
        GoBack          = new JButton("GO BACK");

        JPanel StudentData = new JPanel();
        getContentPane().add(StudentData);
        StudentData.setLayout(new GridLayout(4,2));
        StudentData.setBounds(75,30,450,200);
        StudentData.add(StudentName);
        SName.setFont(FontBtn);
        StudentData.add(SName);
        StudentData.add(StudentAddress);
        Address.setFont(FontBtn);
        StudentData.add(Address);
        StudentData.add(StudentEmail);
        Email.setFont(FontBtn);
        StudentData.add(Email);
        StudentData.add(StudentAge);
        Age.setFont(FontBtn);
        StudentData.add(Age);

        StudentID.setBounds(75,250,100,50);
        getContentPane().add(StudentID);
        SID.setBounds(300,250,225,50);
        SID.setFont(FontBtn);
        getContentPane().add(SID);

        JPanel QueryStudent = new JPanel();
        getContentPane().add(QueryStudent);
        QueryStudent.setLayout(new GridLayout(2,3));
        QueryStudent.setBounds(25,325,550,50);
        StudentInsert.setFont(FontBtn);
        QueryStudent.add(StudentInsert);
        StudentUpdate.setFont(FontBtn);
        QueryStudent.add(StudentUpdate);
        StudentDelete.setFont(FontBtn);
        QueryStudent.add(StudentDelete);
        StudentFindName.setFont(FontBtn);
        QueryStudent.add(StudentFindName);
        StudentFindID.setFont(FontBtn);
        QueryStudent.add(StudentFindID);
        StudentFindAll.setFont(FontBtn);
        QueryStudent.add(StudentFindAll);

        GoBack.setBounds(225, 400, 150, 50);
        getContentPane().add(GoBack);

        this.setVisible(false);
    }
    /** Creates a JTable with the given parameters and shows it. */
    public void CreateTable(ArrayList<String> column, Object[][] data)
    {
        DefaultTableModel model = new DefaultTableModel(data, column.toArray());
        JTable table            = new JTable(model);
        JScrollPane slider      = new JScrollPane(table);

        slider.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        slider.setBounds(600,25,590,440);
        getContentPane().add(slider);
    }
}
